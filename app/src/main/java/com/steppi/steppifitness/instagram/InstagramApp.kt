package com.steppi.steppifitness.instagram

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Message
import android.util.Log
import org.json.JSONObject
import org.json.JSONTokener
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
/**
 * @author Thiago Locatelli <thiago.locatelli></thiago.locatelli>@gmail.com>
 * @author Lorensius W. L T <lorenz></lorenz>@londatiga.net>
 */
class InstagramApp(private val mCtx: Context, private val mClientId: String,
                   private val mClientSecret: String, callbackUrl: String) {
    private val mSession: InstagramSession
    private val mDialog: InstagramDialog
    private var mListener: OAuthAuthenticationListener? = null
    //    private ProgressDialog mProgress;
    val userInfo = HashMap<String, String>()
    private val mAuthUrl: String
    private val mTokenUrl: String
    private var mAccessToken: String? = null

    private val mHandler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            if (msg.what == WHAT_ERROR) {
                //                mProgress.dismiss();
                if (msg.arg1 == 1) {
                    mListener!!.onFail("Failed to get access token")
                } else if (msg.arg1 == 2) {
                    mListener!!.onFail("Failed to get user information")
                }
            } else if (msg.what == WHAT_FETCH_INFO) {
                // fetchUserName();
                //                mProgress.dismiss();
                mListener!!.onSuccess()
            }
        }
    }

    val userName: String
        get() = mSession.username!!
    val id: String
        get() = mSession.id!!
    val name: String
        get() = mSession.name!!
    val tOken: String
        get() = mSession.accessToken!!

    init {
        mSession = InstagramSession(mCtx)
        mAccessToken = mSession.accessToken
        mCallbackUrl = callbackUrl
        mTokenUrl = (TOKEN_URL + "?client_id=" + mClientId + "&client_secret="
                + mClientSecret + "&redirect_uri=" + mCallbackUrl
                + "&grant_type=authorization_code")
        mAuthUrl = (AUTH_URL
                + "?client_id="
                + mClientId
                + "&redirect_uri="
                + mCallbackUrl
                + "&response_type=code&display=touch")/*&scope=likes+comments+relationships*/

        val listener = object : InstagramDialog.OAuthDialogListener {
            override fun onComplete(accessToken: String) {
                getAccessToken(accessToken)
            }

            override fun onError(error: String) {
                mListener!!.onFail("Authorization failed")
            }
        }

        mDialog = InstagramDialog(mCtx, mAuthUrl, listener)
        //        mProgress = new ProgressDialog(context);
        //        mProgress.setCancelable(false);
    }

    private fun getAccessToken(code: String) {
        //        mProgress.setMessage("Getting access token ...");
        //        mProgress.show();
        object : Thread() {
            override fun run() {
                Log.i(TAG, "Getting access token")
                var what = WHAT_FETCH_INFO
                try {
                    val url = URL(TOKEN_URL)
                    // URL url = new URL(mTokenUrl + "&code=" + code);
                    Log.i(TAG, "Opening Token URL $url")
                    val urlConnection = url
                            .openConnection() as HttpURLConnection
                    urlConnection.requestMethod = "POST"
                    urlConnection.doInput = true
                    urlConnection.doOutput = true
                    // urlConnection.connect();
                    val writer = OutputStreamWriter(
                            urlConnection.outputStream)
                    writer.write("client_id=" + mClientId + "&client_secret="
                            + mClientSecret + "&grant_type=authorization_code"
                            + "&redirect_uri=" + mCallbackUrl + "&code=" + code)
                    writer.flush()
                    val response = InstagramUtils.streamToString(urlConnection
                            .inputStream)
                    Log.i(TAG, "response $response")
                    val jsonObj = JSONTokener(response).nextValue() as JSONObject
                    mAccessToken = jsonObj.getString("access_token")
                    Log.i(TAG, "Got access token: " + mAccessToken!!)
                    val id = jsonObj.getJSONObject("user").getString("id")
                    val user = jsonObj.getJSONObject("user").getString(
                            "username")
                    val name = jsonObj.getJSONObject("user").getString(
                            "full_name")

                    mSession.storeAccessToken(mAccessToken!!, id, user, name)

                } catch (ex: Exception) {
                    what = WHAT_ERROR
                    ex.printStackTrace()
                }

                mHandler.sendMessage(mHandler.obtainMessage(what, 1, 0))
            }
        }.start()
    }

    fun fetchUserName(handler: Handler) {
        //        mProgress = new ProgressDialog(mCtx);
        //        mProgress.setMessage("Loading ...");
        //        mProgress.show();
        object : Thread() {
            override fun run() {
                Log.i(TAG, "Fetching user info")
                var what = WHAT_FINALIZE
                try {
                    val url = URL(
                        API_URL + "/users/" + mSession.id
                            + "/?access_token=" + mAccessToken)
                    Log.d(TAG, "Opening URL $url")
                    val urlConnection = url
                            .openConnection() as HttpURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.doInput = true
                    urlConnection.connect()
                    val response = InstagramUtils.streamToString(urlConnection
                            .inputStream)
                    println(response)
                    val jsonObj = JSONTokener(response).nextValue() as JSONObject

                    // String name = jsonObj.getJSONObject("data").getString(
                    // "full_name");
                    // String bio =
                    // jsonObj.getJSONObject("data").getString("bio");
                    // Log.i(TAG, "Got name: " + name + ", bio [" + bio + "]");
                    val data_obj = jsonObj.getJSONObject(TAG_DATA)
                    userInfo[TAG_ID] = data_obj.getString(TAG_ID)

                    userInfo[TAG_PROFILE_PICTURE] = data_obj.getString(TAG_PROFILE_PICTURE)

                    userInfo[TAG_USERNAME] = data_obj.getString(TAG_USERNAME)

                    userInfo[TAG_BIO] = data_obj.getString(TAG_BIO)

                    userInfo[TAG_WEBSITE] = data_obj.getString(TAG_WEBSITE)

                    val counts_obj = data_obj.getJSONObject(TAG_COUNTS)

                    userInfo[TAG_FOLLOWS] = counts_obj.getString(TAG_FOLLOWS)

                    userInfo[TAG_FOLLOWED_BY] = counts_obj.getString(TAG_FOLLOWED_BY)

                    userInfo[TAG_MEDIA] = counts_obj.getString(TAG_MEDIA)

                    userInfo[TAG_FULL_NAME] = data_obj.getString(TAG_FULL_NAME)

                    val meta_obj = jsonObj.getJSONObject(TAG_META)

                    userInfo[TAG_CODE] = meta_obj.getString(TAG_CODE)
                } catch (ex: Exception) {
                    what = WHAT_ERROR
                    ex.printStackTrace()
                }
                //                mProgress.dismiss();
                handler.sendMessage(handler.obtainMessage(what, 2, 0))
            }
        }.start()
    }

    fun hasAccessToken(): Boolean {
        return if (mAccessToken == null) false else true
    }

    fun setListener(listener: OAuthAuthenticationListener) {
        mListener = listener
    }

    fun authorize() {
        // Intent webAuthIntent = new Intent(Intent.ACTION_VIEW);
        // webAuthIntent.setData(Uri.parse(AUTH_URL));
        // mCtx.startActivity(webAuthIntent);
        mDialog.show()
    }

    fun resetAccessToken() {
        if (mAccessToken != null) {
            mSession.resetAccessToken()
            mAccessToken = null
        }
    }

    interface OAuthAuthenticationListener {
        fun onSuccess()

        fun onFail(error: String)
    }

    companion object {

        var WHAT_FINALIZE = 0
        internal var WHAT_ERROR = 1
        private val WHAT_FETCH_INFO = 2

        /**
         * Callback url, as set in 'Manage OAuth Costumers' page
         * (https://developer.github.com/)
         */

        var mCallbackUrl = ""
        private val AUTH_URL = "https://api.instagram.com/oauth/authorize/"
        private val TOKEN_URL = "https://api.instagram.com/oauth/access_token"
        private val API_URL = "https://api.instagram.com/v1"

        private val TAG = "InstagramAPI"

        val TAG_DATA = "data"
        val TAG_ID = "id"
        val TAG_PROFILE_PICTURE = "profile_picture"
        val TAG_USERNAME = "username"
        val TAG_BIO = "bio"
        val TAG_WEBSITE = "website"
        val TAG_COUNTS = "counts"
        val TAG_FOLLOWS = "follows"
        val TAG_FOLLOWED_BY = "followed_by"
        val TAG_MEDIA = "media"
        val TAG_FULL_NAME = "full_name"
        val TAG_META = "meta"
        val TAG_CODE = "code"
    }
}
