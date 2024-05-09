package com.steppi.steppifitness.ui.base

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Optional
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.dialogs.STDeviceInfoDialog
import com.steppi.steppifitness.dialogs.STOkayCancelDialog
import com.steppi.steppifitness.dialogs.STUpdateAppDialog
import com.steppi.steppifitness.network.STAPIConstants
import com.steppi.steppifitness.ui.common.STContainerActivity
import com.steppi.steppifitness.ui.home.STHomeFragment
import com.steppi.steppifitness.ui.home.STWhatsNewActivity
import com.steppi.steppifitness.ui.login.STLoginActivity
import com.steppi.steppifitness.ui.register.STRegisterActivity
import com.steppi.steppifitness.utils.*
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.header_layout.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

abstract class STBaseActivity : AppCompatActivity() {
    lateinit var activityContext: AppCompatActivity
    private var progressDialogue: Dialog? = null

    // for image picker
    private var imageUri: Uri? = null
    private var mCropImageUri: Uri? = null

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        super.onCreate(savedInstanceState)
        activityContext = this
        setContentView(getLayoutId())
        ButterKnife.bind(this)
        initViews()
    }

    abstract fun getLayoutId(): Int
    abstract fun initViews()

    /*
      Method to get facebook hash-key
   */
    @SuppressLint("PackageManagerGetSignatures")
    fun facebookHashKey(context: Context) {
        try {
            val info = packageManager.getPackageInfo(
                "com.steppi.steppifitness",
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(
            STUtils.setAppLanguage(
                newBase,
                STPreference.getLanguageCode(newBase!!)
            )
        )
    }

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration?) {
        if (overrideConfiguration != null) {
            val uiMode = overrideConfiguration.uiMode
            overrideConfiguration.setTo(baseContext.resources.configuration)
            overrideConfiguration.uiMode = uiMode
        }
        super.applyOverrideConfiguration(overrideConfiguration)
    }

    fun setHeaderName(header: String) {
        STUtils.setValueToView(header_name, header)
    }

    @Optional
    @OnClick(R.id.header_back)
    open fun onBack() {
        if (activityContext is STWhatsNewActivity) {
            (activityContext as STWhatsNewActivity).onBackPressed()
        } else {
            finish()
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    fun requestDidStart() {
        /**
         * Starting the progressing indicator
         */
        if (progressDialogue != null) {
            when {
                progressDialogue!!.isShowing -> {
                }
            }
        } else {
            try {
                progressDialogue = STUtils.showProgressDialog(this)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun requestDidFinish() {
        /**
         * Finishing the progressing indicator
         */
        if (progressDialogue != null) {
            if (progressDialogue!!.isShowing) {
                try {
                    progressDialogue!!.dismiss()
                    progressDialogue = null
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun pickImage() {
        CropImage.activity()
            .setGuidelines(CropImageView.Guidelines.ON)
            .setAspectRatio(1, 1)
            .start(this)
    }

    @SuppressLint("NewApi")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // handle result of pick image chooser
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            imageUri = CropImage.getPickImageResultUri(this, data)
            // For API >= 23 we need to check specifically that we have permissions to read external storage.
            if (CropImage.isReadExternalStoragePermissionsRequired(this, imageUri!!)) {
                // request permissions and handle the result in onRequestPermissionsResult()
                mCropImageUri = imageUri
                requestPermissions(
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE
                )
            } else {
                // no permissions required or already grunted, can start crop image activity
                startCropImageActivity(imageUri!!)
            }
        } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                val resultUri = result.uri
                if (activityContext is STRegisterActivity) {
                    (activityContext as STRegisterActivity).setImage(resultUri)
                } else if (activityContext is STContainerActivity) {
                    (activityContext as STContainerActivity).setImage(resultUri)
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                result.error
            }
        } else if (requestCode == STConstants.GOOGLE_FIT_PERMISSIONS_REQUEST_CODE || requestCode == STConstants.FITBIT_PERMISSIONS_REQUEST_CODE) {
            val frag = supportFragmentManager.findFragmentById(R.id.container) as? STBaseFragment
            if (frag is STHomeFragment) {
                frag.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    fun logout() {
        STPreference.setProfilePic(activityContext, null)
        STPreference.setUserId(activityContext, null)
        STPreference.setName(activityContext, null)
        STPreference.setEmail(activityContext, null)
        STPreference.setCorporateEmail(activityContext, null)
        STPreference.saveAccessToken(activityContext, null)
        STPreference.saveRegToken(activityContext, null)
        STPreference.setUserLevel(activityContext, -1)//STConstants.ACTIVE_LEVEL_OTP
        when (STPreference.getFitnessDevice(activityContext)) {
            STUtils.EnumFitnessDevice.GOOGLE_FIT.name -> {
                STUtils.logoutFromFitBit(activityContext)
                STPreference.saveFitnessDevice(activityContext, null)
            }
            STUtils.EnumFitnessDevice.HUAWEI_HEALTH.name -> {
                STUtils.logoutFromHuaweiHealth(activityContext)
                STPreference.saveFitnessDevice(activityContext, null)
            }
            STUtils.EnumFitnessDevice.FITBIT.name -> {
                STUtils.logoutFromFitBit(activityContext)
                STPreference.saveFitnessDevice(activityContext, null)
            }
            STUtils.EnumFitnessDevice.GARMIN_HEALTH.name -> {
                STUtils.logoutFromGarmin(activityContext)
                STPreference.saveFitnessDevice(activityContext, null)
            }
            else -> {
                STUtils.logoutFromGoogleFit(activityContext)
                STUtils.logoutFromFitBit(activityContext)
                STUtils.logoutFromGarmin(activityContext)
                STPreference.saveFitnessDevice(activityContext, null)
            }
        }
        startActivity(Intent(activityContext, STLoginActivity::class.java))
        ActivityCompat.finishAffinity(activityContext)
    }

    private fun startCropImageActivity(imageUri: Uri) {
        CropImage.activity(imageUri).setAspectRatio(1, 1)
            .setCropShape(CropImageView.CropShape.RECTANGLE).start(this)
    }

    fun animateLayout(headerContainer: View, mainContainer: View) {
        headerContainer.invisible()
        mainContainer.invisible()
        animateTogether(
            arrayListOf(
                headerContainer.animateTranslationVertical(-400f, 0f, 0f, 1f, 300),
                mainContainer.animateTranslationVertical(1500f, 0f, 0f, 1f, 300)
            ), 500
        )
    }

    inline fun showOkayCancelDialog(func: STOkayCancelDialog.() -> Unit): AlertDialog =
        STOkayCancelDialog(activityContext).apply {
            func()
        }.create()

    inline fun updateAppDialog(func: STUpdateAppDialog.() -> Unit): AlertDialog =
        STUpdateAppDialog(activityContext).apply {
            func()
        }.create()

    inline fun deviceInfoDialog(func: STDeviceInfoDialog.() -> Unit): AlertDialog =
        STDeviceInfoDialog(activityContext).apply {
            func()
        }.create()

    fun manageError(statusCode: Int?) {
        Log.e("Error Status Code", "Error Status Code : $statusCode")
        when (statusCode) {
            STAPIConstants.STATUS_CODE_SESSION_EXPIRED,
            STAPIConstants.STATUS_CODE_INVALID_SESSION,
            STAPIConstants.STATUS_CODE_MALFORMED_REQUEST -> logout()
        }
    }
}
