package com.steppi.steppifitness.ui.profile

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.view.Window
import android.view.WindowManager
import butterknife.OnClick
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.model.STCountryData
import com.steppi.steppifitness.model.STUser
import com.steppi.steppifitness.network.response.user.ProfileData
import com.steppi.steppifitness.network.response.user.STUserData
import com.steppi.steppifitness.ui.base.STBaseActivity
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.common.STContainerActivity
import com.steppi.steppifitness.ui.country.STCountrySelectionActivity
import com.steppi.steppifitness.ui.profile.mvi.STProfileController
import com.steppi.steppifitness.ui.profile.mvi.STProfileIntent
import com.steppi.steppifitness.ui.profile.mvi.STProfileState
import com.steppi.steppifitness.utils.*
import com.tsongkha.spinnerdatepicker.DatePicker
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.android.synthetic.main.update_profile_success_dialog.*
import okhttp3.MultipartBody
import java.io.File
import java.util.*

class STEditProfileFragment :
    STBaseViewModelFragment<STProfileIntent, STProfileState, STProfileController>(
        STProfileController::class.java
    ), com.tsongkha.spinnerdatepicker.DatePickerDialog.OnDateSetListener {
    private var imageFile: File? = null
    private var gender: Int = 0
    private var countryId: String? = null
    private var selectedNationality: STCountryData? = null
    private var user: STUser? = null
    private val dpd = SpinnerDatePickerDialogBuilder()
    private val calendarDefault = Calendar.getInstance()
    private var yearDefault = calendarDefault.get(Calendar.YEAR)
    private var monthDefault = calendarDefault.get(Calendar.MONTH)
    private var dayDefault = calendarDefault.get(Calendar.DAY_OF_MONTH)


    override fun getLayoutId(): Int {
        return R.layout.fragment_edit_profile
    }

    override fun initViews() {
        userPassword.setPasswordTransformationMethod()
    }

    override fun onViewModelReady() {
        getProfile()
        invokeIntent(STProfileIntent.GetCountries)
    }

    fun getProfile() {
        invokeIntent(STProfileIntent.GetProfile)
    }

    override fun processState(state: STProfileState) {
        when (state) {
            is STProfileState.Loading -> {
                requestDidStart()
            }
            is STProfileState.GetCountries -> {
                setCountryData()
            }
            is STProfileState.Error -> {
                requestDidFinish()
                showToast(state.errorData?.message)
                manageError(state.errorData?.statusCode)
            }
            is STProfileState.GetProfile -> {
                requestDidFinish()
                setUserDataValues(state.userDataResponse.data)
            }
            is STProfileState.UpdateProfilePic -> {
                requestDidFinish()
                setProfilePicData(state.updatePicResponse.data)
                STConstants.PROFILE_UPDATE = true
            }
            is STProfileState.UpdateProfile -> {
                requestDidFinish()
                showProfileSuccessDialog()
                STConstants.PROFILE_UPDATE = true
            }
            is STProfileState.UpdateMobile -> {
                requestDidFinish()
                showProfileSuccessDialog()
                STConstants.PROFILE_UPDATE = true
            }
        }
    }

    private fun showProfileSuccessDialog() {
        val dialog = Dialog(activityContext, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.update_profile_success_dialog)
        dialog.okay.visible()
        dialog.okay.setOnClickListener {
            dialog.dismiss()
            activityContext.finish()
        }
        dialog.show()
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window?.attributes)
        lp.width = STUtils.getDeviceWidth(activityContext)
        lp.height = STUtils.getDeviceHeight(activityContext)
        dialog.window?.attributes = lp
        /*Handler().postDelayed({
            dialog.dismiss()
        }, 5000)*/
    }

    private fun setProfilePicData(profileData: ProfileData?) {
        STConstants.PROFILE_UPDATE = true
        STPreference.setProfilePic(activityContext, profileData?.picture)
        userImage.load(
            activityContext,
            profileData?.picture,
            STUtils.getDimen(activityContext, R.dimen.margin_small)
        )
    }

    private fun setCountryData() {
    }

    private fun setUserDataValues(stUserData: STUserData?) {
        stUserData?.let {
            scrollLayout.visible()
            it.user?.let { user ->
                this.user = user
                countryId = user.countryId
                userImage.load(
                    activityContext,
                    user.picture,
                    STUtils.getDimen(activityContext, R.dimen.margin_small)
                )
                STUtils.setValueToView(name, user.name)
                STUtils.setValueToView(mobileNumber, user.phoneNumber)
                STUtils.setValueToView(personalEmail, user.email)
                STUtils.setValueToView(
                    userDob,
                    STUtils.getDate(user.dob, "yyyy-MM-dd", "dd-MMM-yyyy")//dd-MM-yyyy
                )
                STUtils.setValueToView(nationality, user.country?.name)
                nationalityFlag.load(activityContext, user.country?.flag)
                when (user.gender) {
                    STConstants.MALE -> {
                        setMaleData()
                    }
                    else -> {
                        setFeMaleData()
                    }
                }

            }
        }
    }

    @OnClick(R.id.male)
    fun setMaleData() {
        gender = STConstants.MALE
        male.background = STUtils.getDrawable(activityContext, R.drawable.button_bg_enabled)
        feMale.background = STUtils.getDrawable(activityContext, R.drawable.edit_text_bg)
        male.setTextColor(STUtils.getColor(activityContext, R.color.theme_color))
        feMale.setTextColor(STUtils.getColor(activityContext, R.color.white))
        STUtils.setBoldFont(activityContext, male)
        STUtils.setLightFont(activityContext, feMale)
    }

    @OnClick(R.id.feMale)
    fun setFeMaleData() {
        gender = STConstants.FEMALE
        male.background = STUtils.getDrawable(activityContext, R.drawable.edit_text_bg)
        feMale.background = STUtils.getDrawable(activityContext, R.drawable.button_bg_enabled)
        male.setTextColor(STUtils.getColor(activityContext, R.color.white))
        feMale.setTextColor(STUtils.getColor(activityContext, R.color.theme_color))
        STUtils.setBoldFont(activityContext, feMale)
        STUtils.setLightFont(activityContext, male)
    }

    @OnClick(R.id.userDob)
    fun addDateOfBirth() {
//        val c = Calendar.getInstance()
//        val year = c.get(Calendar.YEAR)
//        val month = c.get(Calendar.MONTH)
//        val day = c.get(Calendar.DAY_OF_MONTH)
//
//        val dpd = DatePickerDialog(
//            activityContext,
//            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
//                STUtils.setValueToView(
//                    userDob,
//                    STUtils.getDate(
//                        "" + year + "-" + (monthOfYear + 1) + "-" + dayOfMonth,
//                        "yyyy-MM-dd",
//                        "dd-MMM-yyyy" //dd-MM-yyyy
//                    )
//                )
//            },
//            year,
//            month,
//            day
//        )
//        dpd.show()

        val today = Calendar.getInstance()
        dpd.context(activityContext)
            .callback(this)
            .spinnerTheme(R.style.NumberPickerStyle)
            .defaultDate(yearDefault, monthDefault, dayDefault)
            .maxDate(
                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH)
            )
            .build()
            .show()
    }

    override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        yearDefault = year
        monthDefault = monthOfYear
        dayDefault = dayOfMonth
        STUtils.setValueToView(
            userDob,
            STUtils.getDate(
                "" + year + "-" + (monthOfYear + 1) + "-" + dayOfMonth,
                "yyyy-MM-dd",
                "dd-MMM-yyyy"// dd-MM-yyyy
            )
        )
    }

    @OnClick(R.id.changeUserImage, R.id.imageLayout)
    fun changeImage() {
        (activityContext as STBaseActivity).pickImage()
    }


    fun setImage(resultUri: Uri?) {
        resultUri?.let {
            imageFile = File(STUtils.getRealPathFromURI(activityContext, it))
            var requestOptions = RequestOptions()
            requestOptions = requestOptions.transform(
                CenterCrop(),
                RoundedCorners(STUtils.getDimen(activityContext, R.dimen.margin_small))
            )
            Glide.with(this)
                .load(resultUri)
                .apply(requestOptions)
                .into(userImage)
            var imagePart: MultipartBody.Part? = null
            if (null != imageFile) {
                imagePart = STUtils.prepareFilePart(imageFile!!)
            }
            invokeIntent(STProfileIntent.UpdateProfilePic(imagePart))
        }

    }

    private fun validate(): Boolean {
        return true
    }

    fun saveProfile() {
        if (validate()) {
            val updateProfileRequest = STUser()
            updateProfileRequest.id = user?.id
            updateProfileRequest.dob = STUtils.getDateEn(
                STUtils.getValueFromView(userDob), "dd-MMM-yyyy"/*dd-MM-yyyy*/,
                "yyyy-MM-dd"
            )

            updateProfileRequest.email = STUtils.getValueFromView(personalEmail)
            updateProfileRequest.gender = gender
            updateProfileRequest.name = STUtils.getValueFromView(name)
            updateProfileRequest.countryId = countryId
            invokeIntent(STProfileIntent.UpdateProfile(updateProfileRequest))
        }
    }

    @OnClick(R.id.nationality)
    fun selectNationality() {
        val countryIntent = Intent(activityContext, STCountrySelectionActivity::class.java)
        countryIntent.putExtra(STConstants.NEED_MOBILE_CODE, false)
        countryIntent.putExtra(STConstants.SELECTED_COUNTRY_DATA, selectedNationality)
        startActivityForResult(
            countryIntent,
            STConstants.REQUEST_CODE_NATIONALITY
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (Activity.RESULT_OK == resultCode) {
            if (STConstants.REQUEST_CODE_NATIONALITY == requestCode) {
                data?.let {
                    selectedNationality =
                        it.getSerializableExtra(STConstants.COUNTRY_DATA) as STCountryData
                    countryId = selectedNationality?.id
                    STUtils.setValueToView(nationality, selectedNationality?.name)
                    nationalityFlag.load(activityContext, selectedNationality?.flag)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    @OnClick(R.id.editPassword)
    fun editPassword() {
        val container = Intent(activityContext, STContainerActivity::class.java)
        container.putExtra(STConstants.FRAGMENT_NAME, resources.getString(R.string.edit_password))
        container.putExtra(STConstants.FRAGMENT_ID, STConstants.FRAGMENT_ID_CHANGE_PASSWORD)
        startActivity(container)
    }

    @OnClick(R.id.editMobile)
    fun editMobile() {
        val container = Intent(activityContext, STContainerActivity::class.java)
        container.putExtra(STConstants.FRAGMENT_NAME, resources.getString(R.string.edit_mobile))
        container.putExtra(STConstants.FRAGMENT_ID, STConstants.FRAGMENT_ID_EDIT_MOBILE)
        startActivity(container)
    }
}
