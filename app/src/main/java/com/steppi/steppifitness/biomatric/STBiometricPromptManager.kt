package com.steppi.steppifitness.biomatric

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.edit
import androidx.fragment.app.FragmentActivity
import com.steppi.steppifitness.R
import java.security.Key
import java.security.KeyStore
import java.util.concurrent.Executors
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

class STBiometricPromptManager(private val activity: FragmentActivity) {
    private val sharedPreferences: SharedPreferences =
        activity.getSharedPreferences("EncryptionPreference", Context.MODE_PRIVATE)
    private val keyStore: KeyStore = KeyStore.getInstance(KEYSTORE).apply { load(null) }

    @RequiresApi(Build.VERSION_CODES.M)
    fun decryptPrompt(failedAction: (String) -> Unit, successAction: (ByteArray) -> Unit) {
        try {
            authenticateBiometric(
                failedAction = { failedAction(it) },
                successAction = {
                    val secretKey = getKey()
                    val initializationVector = getInitializationVector()
                    if (secretKey != null && initializationVector != null) {
                        val cipher = getDecryptCipher(secretKey, initializationVector)
                        handleDecrypt(cipher, failedAction, successAction)
                    } else {
                        failedAction(activity.getString(R.string.not_found_encrypted_data)) // "decrypt failed"
                    }
                }
            )
        } catch (e: Exception) {
            Log.d(TAG, "Decrypt BiometricPrompt exception", e)
            failedAction(activity.getString(R.string.decrypt_biometric_prompt) + e.message)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun encryptPrompt(
        data: ByteArray,
        failedAction: (String) -> Unit,
        successAction: (ByteArray) -> Unit
    ) {
        try {
            authenticateBiometric(
                failedAction = { failedAction(it) },
                successAction = {
                    val secretKey = createKey()
                    val cipher = getEncryptCipher(secretKey)
                    handleEncrypt(cipher, data, failedAction, successAction)
                }
            )
        } catch (e: Exception) {
            Log.d(TAG, "Encrypt BiometricPrompt exception", e)
            failedAction(activity.getString(R.string.encrypt_biometric_prompt) + e.message)
        }
    }

    private fun getKey(): Key? = keyStore.getKey(KEY_NAME, null)

    @RequiresApi(Build.VERSION_CODES.M)
    private fun createKey(): SecretKey {
        val keyGenerator = KeyGenerator.getInstance(ALGORITHM, KEYSTORE)
        val keyGenParameterSpec =
            KeyGenParameterSpec.Builder(
                KEY_NAME,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            )
                .setBlockModes(BLOCK_MODE)
                .setEncryptionPaddings(PADDING)
                .setUserAuthenticationRequired(true)
                .build()

        keyGenerator.init(keyGenParameterSpec)
        return keyGenerator.generateKey()
    }

    private fun getInitializationVector(): ByteArray? {
        val iv = sharedPreferences.getString(INITIALIZATION_VECTOR, null)
        return when {
            iv != null -> Base64.decode(iv, Base64.DEFAULT)
            else -> null
        }
    }

    private fun getEncryptedData(): ByteArray? {
        val iv = sharedPreferences.getString(DATA_ENCRYPTED, null)
        return when {
            iv != null -> Base64.decode(iv, Base64.DEFAULT)
            else -> null
        }
    }

    private fun saveEncryptedData(dataEncrypted: ByteArray, initializationVector: ByteArray) {
        sharedPreferences.edit {
            putString(DATA_ENCRYPTED, Base64.encodeToString(dataEncrypted, Base64.DEFAULT))
            putString(
                INITIALIZATION_VECTOR,
                Base64.encodeToString(initializationVector, Base64.DEFAULT)
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun getDecryptCipher(key: Key, iv: ByteArray): Cipher =
        Cipher.getInstance(keyTransformation())
            .apply { init(Cipher.DECRYPT_MODE, key, IvParameterSpec(iv)) }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun getEncryptCipher(key: Key): Cipher =
        Cipher.getInstance(keyTransformation()).apply { init(Cipher.ENCRYPT_MODE, key) }

    private fun handleDecrypt(
        cipher: Cipher,
        failedAction: (String) -> Unit,
        successAction: (ByteArray) -> Unit
    ) {
        val executor = Executors.newSingleThreadExecutor()
        val biometricPrompt =
            BiometricPrompt(activity, executor, object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    result.cryptoObject?.cipher?.let { cipher ->
                        val encrypted = getEncryptedData()
                        val data = cipher.doFinal(encrypted)
                        activity.runOnUiThread { successAction(data) }
                    }
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Log.d(TAG, "Authentication error. $errString ($errorCode)")
                    activity.runOnUiThread { failedAction("${activity.getString(R.string.biometric_error_auth_failed)}$errString ($errorCode)") }
                }
            })
        val promptInfo = biometricPromptInfo()
        biometricPrompt.authenticate(promptInfo, BiometricPrompt.CryptoObject(cipher))
    }

    private fun handleEncrypt(
        cipher: Cipher,
        data: ByteArray,
        failedAction: (String) -> Unit,
        successAction: (ByteArray) -> Unit
    ) {
        val executor = Executors.newSingleThreadExecutor()
        val biometricPrompt =
            BiometricPrompt(activity, executor, object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    result.cryptoObject?.cipher?.let { resultCipher ->
                        val iv = resultCipher.iv
                        val encryptedData = resultCipher.doFinal(data)
                        saveEncryptedData(encryptedData, iv)
                        activity.runOnUiThread { successAction(encryptedData) }
                    }
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Log.d(TAG, "Authentication error. $errString ($errorCode)")
                    activity.runOnUiThread { failedAction("${activity.getString(R.string.biometric_error_auth_failed)}$errString ($errorCode)") }
                }
            })
        val promptInfo = biometricPromptInfo()
        biometricPrompt.authenticate(promptInfo, BiometricPrompt.CryptoObject(cipher))
    }

    private fun biometricPromptInfo(): BiometricPrompt.PromptInfo {
        return BiometricPrompt.PromptInfo.Builder()
            .setTitle(activity.getString(R.string.steppi_authentication)) // required  "My App's Authentication"
            .setSubtitle("") // "Please login to get access"
            .setDescription(activity.getString(R.string.biometric_enabled_description)) // "My App is using Android biometric authentication"
            .setNegativeButtonText(activity.getString(android.R.string.cancel)) // required
            .setConfirmationRequired(false)
            .build()
    }

    companion object {
        private const val TAG = "BiometricPrompt"
        private const val KEYSTORE = "AndroidKeyStore"
        private const val KEY_NAME = "MY_KEY"
        private const val DATA_ENCRYPTED = "DATA_ENCRYPTED"
        private const val INITIALIZATION_VECTOR = "INITIALIZATION_VECTOR"

        @RequiresApi(Build.VERSION_CODES.M)
        private const val ALGORITHM = KeyProperties.KEY_ALGORITHM_AES

        @RequiresApi(Build.VERSION_CODES.M)
        private const val BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC

        @RequiresApi(Build.VERSION_CODES.M)
        private const val PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7

        @RequiresApi(Build.VERSION_CODES.M)
        private fun keyTransformation() =
            listOf(ALGORITHM, BLOCK_MODE, PADDING).joinToString(separator = "/")
    }

    fun normalBiometricPrompt(
        failedAction: (String) -> Unit,
        successAction: (String) -> Unit
    ) {
        authenticateBiometric(
            failedAction = { failedAction(it) },
            successAction = {
                biometricPrompt(failedAction, successAction)
            }
        )
    }

    private fun biometricPrompt(failedAction: (String) -> Unit, successAction: (String) -> Unit) {
        val executor = Executors.newSingleThreadExecutor()
        val biometricPrompt =
            BiometricPrompt(activity, executor, object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    activity.runOnUiThread { successAction(activity.getString(R.string.auth_was_successful)) }
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Log.d(TAG, "Authentication error. $errString ($errorCode)")
                    activity.runOnUiThread { failedAction("${activity.getString(R.string.biometric_error_auth_failed)}$errString ($errorCode)") }
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    activity.runOnUiThread { failedAction(activity.getString(R.string.biometric_error_auth_failed)) }
                }
            })
        val promptInfo = biometricPromptInfo()
        biometricPrompt.authenticate(promptInfo)
    }

    private fun authenticateBiometric(failedAction: (String) -> Unit, successAction: () -> Unit) {
        val biometricManager = BiometricManager.from(activity)
        when (biometricManager.canAuthenticate()) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                successAction()
                Log.d("BiometricAuthentication", "App can authenticate using biometrics.")
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                failedAction(activity.getString(R.string.biometric_error_no_hardware))
            }

            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                failedAction(activity.getString(R.string.biometric_error_hw_unavailable))
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                failedAction(
                    activity.getString(R.string.biometric_error_none_enrolled)
                )
            }
        }
    }
}
