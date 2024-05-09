package com.steppi.steppifitness.ui.home

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import butterknife.OnClick
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.request.STBaseRequest
import com.steppi.steppifitness.network.response.category.STCategory
import com.steppi.steppifitness.network.response.challenges.data.STChallengesListData
import com.steppi.steppifitness.ui.base.STBaseFragment
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelActivity
import com.steppi.steppifitness.ui.challenges.STChallengesFragment
import com.steppi.steppifitness.ui.common.STContainerActivity
import com.steppi.steppifitness.ui.home.callbacks.IRefreshHomeActivityCallback
import com.steppi.steppifitness.ui.home.mvi.STHomeController
import com.steppi.steppifitness.ui.home.mvi.STHomeIntent
import com.steppi.steppifitness.ui.home.mvi.STHomeState
import com.steppi.steppifitness.ui.profile.STMyProfileFragment
import com.steppi.steppifitness.ui.rewards.STRewardsFragment
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.header_layout_home.*

class STHomeActivity :
    STBaseViewModelActivity<STHomeIntent, STHomeState, STHomeController>(STHomeController::class.java),
    IRefreshHomeActivityCallback {
    private var selectedReward: STCategory? = null
    private var selectedPosition: Int? = 0
    private var categoryList: ArrayList<STCategory>? = null
    private val baseRequest = STBaseRequest()

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun initViews() {
        getDataIntent(intent)
        registerNotificationCountReceiver()
//        intent.getBooleanExtra(STConstants.LANGUAGE_CHANGE, false).let {
//            if (it) {
//                bottomMenu.selectedItemId = R.id.profile
//                selectedPosition = 0
//                selectedReward = null
//                changeFragment(container.id, STMyProfileFragment())
//            } else {
//                changeFragment(container.id, STHomeFragment())
//            }
//        }
        changeFragment(container.id, STHomeFragment())
        bottomMenu.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        loadProfileImage()
        requestLocationPermission()
    }


    // todo refresh activity
    override fun refreshHome() {
        startActivity(intent)
        overridePendingTransition(0, 0);
        finish()
    }



    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        getDataIntent(intent)
    }

    private fun getDataIntent(intent: Intent?) {
        intent?.let {
            val isFromNotification = intent.getBooleanExtra(STConstants.IS_NOTIFICATION, false)
            val type = intent.getStringExtra(STConstants.NOTIFICATION_TYPE)
            val challengeId = intent.getStringExtra(STConstants.NOTIFICATION_CHALLENGE_ID)
            val challengeType = intent.getStringExtra(STConstants.NOTIFICATION_CHALLENGE_TYPE)
            val rewardId = intent.getStringExtra(STConstants.NOTIFICATION_REWARD_ID)
            val rewardType = intent.getStringExtra(STConstants.NOTIFICATION_REWARD_TYPE)
            val isPrivate = intent.getStringExtra(STConstants.CHALLENGES_ISPRIVATE_NOTIFICATION)
            val challengeJoinCode =
                intent.getStringExtra(STConstants.PRIVATE_CHALLENGE_JOIN_CODE_NOTIFICATION)
            val isDeepLinkingNotification =
                intent.getBooleanExtra(STConstants.IS_DEEP_LINKING_NOTIFICATION, false)
            when {
                isFromNotification -> {
                    when {
                        rewardId != null -> {
                            openRewardDetail(rewardId, rewardType)
                        }
                        challengeId != null -> {
                            openChallengeDetail(
                                challengeId,
                                challengeType,
                                isPrivate,
                                challengeJoinCode,
                                type
                            )
                        }
                        else -> {
                        }
                    }
                }
                isDeepLinkingNotification -> {
                    when {
                        rewardId != null -> {
                            openRewardDetail(rewardId, rewardType)
                        }
                        challengeId != null -> {
                            openChallengeDetail(
                                challengeId,
                                challengeType,
                                isPrivate,
                                challengeJoinCode,
                                type
                            )
                        }
                        else -> {
                        }
                    }
                }
                else -> {
                }
            }
        }
    }

    private fun openRewardDetail(rewardId: String?, rewardType: String?) {
        val container = Intent(activityContext, STContainerActivity::class.java)
        container.putExtra(STConstants.FRAGMENT_NAME, getString(R.string.details))
        container.putExtra(STConstants.FRAGMENT_ID, STConstants.FRAGMENT_ID_REWARDS_REDEEM)
        container.putExtra(STConstants.NOTIFICATION_REWARD_ID, rewardId)
        container.putExtra(STConstants.NOTIFICATION_REWARD_TYPE, rewardType)
        container.putExtra(STConstants.IS_NOTIFICATION, true)
        startActivity(container)
    }

    private fun openChallengeDetail(
        challengeId: String?, challengeType: String?,
        isPrivate: String?, challengeJoinCode: String?, type: String?
    ) {
        val challengesData: STChallengesListData? = STChallengesListData()
        challengesData?.id = challengeId
        challengesData?.challengeType = challengeType
        challengesData?.type = type
        challengesData?.isPrivate = isPrivate == "true"
        val container = Intent(activityContext, STContainerActivity::class.java)
        container.putExtra(STConstants.FRAGMENT_NAME, ""/*getString(R.string.challenge_details)*/)
        container.putExtra(STConstants.FRAGMENT_ID, STConstants.FRAGMENT_ID_CHALLENGE_DETAILS)
        container.putExtra(STConstants.CHALLENGES_DATA_ID, challengeId)
        container.putExtra(STConstants.CHALLENGES_TYPE, challengeType)
        if (isPrivate == "true") {
            container.putExtra(STConstants.CHALLENGES_ISPRIVATE, true)
        } else {
            container.putExtra(STConstants.CHALLENGES_ISPRIVATE, false)
        }
        challengeJoinCode?.let { challengeJoinCodeValue ->
            container.putExtra(STConstants.PRIVATE_CHALLENGE_JOIN_CODE, challengeJoinCodeValue)
        }
        container.putExtra(STConstants.IS_NOTIFICATION, true)
        container.putExtra(STConstants.CHALLENGES_DATA, challengesData)
        startActivity(container)
    }

    override fun processState(state: STHomeState) {
        when (state) {
            is STHomeState.Loading -> requestDidStart()
            is STHomeState.Error -> {
                requestDidFinish()
                showToast(state.errorData?.message)
                manageError(state.errorData?.statusCode)
            }
            is STHomeState.GetUnreadNotificationCount -> {
                STConstants.MARK_ALL_NOTIFICATION_READ = false
                state.unreadNotificationCountResponse.data?.count?.let {
                    STConstants.NOTIFICATION_COUNT = it
                    if (it > 0) notificationCount.visible()
                    else notificationCount.gone()
                    notificationCount.text = it.toString()
                    val frag =
                        supportFragmentManager.findFragmentById(R.id.container) as? STBaseFragment
                    if (frag is STMyProfileFragment) {
                        frag.updateNotificationCount()
                    }
                } ?: run {
                    notificationCount.gone()
                }
                baseRequest.deviceId = STUtils.getDeviceId(activityContext)
                baseRequest.deviceType = STConstants.DEVICE_TYPE
                baseRequest.deviceToken = STPreference.getDeviceId(activityContext)
                invokeIntent(STHomeIntent.DeviceTokenUpdate(baseRequest))
            }
            is STHomeState.DeviceTokenUpdateResponse -> {
            }
        }
    }

    override fun onViewModelReady() {
        invokeIntent(STHomeIntent.GetUnreadNotificationCount)
    }

    private fun registerNotificationCountReceiver() {
        LocalBroadcastManager.getInstance(activityContext)
            .registerReceiver(
                broadCastReceiver,
                IntentFilter(STConstants.BROADCAST_NOTIFICATION_UPDATE)
            )
    }

    private fun unregisterNotificationCountReceiver() {
        LocalBroadcastManager.getInstance(activityContext).unregisterReceiver(broadCastReceiver)
    }

    private val broadCastReceiver = object : BroadcastReceiver() {
        override fun onReceive(contxt: Context?, intent: Intent?) {
            when (intent?.action) {
                STConstants.BROADCAST_NOTIFICATION_UPDATE -> {
                    //notificationCountCall()
                    invokeIntent(STHomeIntent.GetUnreadNotificationCount)
                }
            }
        }
    }

    private fun requestLocationPermission() {
        when {
            checkPermission(
                activityContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) -> {
            }
            else -> ActivityCompat.requestPermissions(
                activityContext,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE),
                STConstants.MAKE_PERMISSION_REQUEST_CODE
            )
        }
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            search.invisible()
            editProfile.invisible()

            when (menuItem.itemId) {
                R.id.home -> {
                    selectedPosition = 0
                    selectedReward = null
                    if (getCurrentFragment() !is STHomeFragment) {
                        changeFragment(container.id, STHomeFragment())
                    }
                }
                R.id.rewards -> {
                    if (getCurrentFragment() !is STRewardsFragment) {
                        search.visible()
                        changeFragment(
                            container.id,
                            STRewardsFragment().apply {
                                arguments = Bundle().apply {
                                    putSerializable(
                                        STConstants.REWARD_TYPE, selectedReward
                                    )
                                    putSerializable(
                                        STConstants.REWARD_SELECTED_POSITION, selectedPosition
                                    )
                                    putSerializable(
                                        STConstants.CATEGORY_LIST, categoryList
                                    )
                                }
                            })
                    }
                }
                R.id.challenegs -> {
                    selectedPosition = 0
                    selectedReward = null
                    if (getCurrentFragment() !is STChallengesFragment) {
                        changeFragment(container.id, STChallengesFragment())
                    }
                }
                R.id.profile -> {
                    selectedPosition = 0
                    selectedReward = null
                    if (getCurrentFragment() !is STMyProfileFragment) {
                        //editProfile.visible()
                        changeFragment(container.id, STMyProfileFragment())
                    }
                }
            }
            false
        }

    @OnClick(R.id.editProfile)
    fun editProfile() {
        val container = Intent(activityContext, STContainerActivity::class.java)
        container.putExtra(STConstants.FRAGMENT_NAME, resources.getString(R.string.edit_profile))
        container.putExtra(STConstants.FRAGMENT_ID, STConstants.FRAGMENT_ID_EDIT_PROFILE)
        startActivity(container)
    }

    @OnClick(R.id.notificationLayout)
    fun notificationPage() {
        val container = Intent(activityContext, STContainerActivity::class.java)
        container.putExtra(STConstants.FRAGMENT_NAME, resources.getString(R.string.notifications))
        container.putExtra(STConstants.FRAGMENT_ID, STConstants.FRAGMENT_ID_NOTIFICATION)
        startActivity(container)
    }

    @OnClick(R.id.search)
    fun search() {
        val container = Intent(activityContext, STContainerActivity::class.java)
        container.putExtra(STConstants.FRAGMENT_NAME, resources.getString(R.string.search))
        container.putExtra(STConstants.SEARCH_CATEGORY_LIST, searchCategory)
        container.putExtra(STConstants.FRAGMENT_ID, STConstants.FRAGMENT_ID_SEARCH)
        startActivity(container)
    }

    private var searchCategory: STCategory? = null
    fun setSelectedReward(category: STCategory?) {
        searchCategory = category
    }

    fun showReward(
        rewardMenuModel: STCategory,
        selectedPosition: Int,
        needToSetBottomMenu: Boolean = true
    ) {
        this.selectedReward = rewardMenuModel
        this.selectedPosition = selectedPosition
        if (needToSetBottomMenu)
            bottomMenu.selectedItemId = R.id.rewards
    }

    fun showDfcChallenge(needToSetBottomMenu: Boolean = true) {
        if (needToSetBottomMenu)
            bottomMenu.selectedItemId = R.id.challenegs
        selectedPosition = 0
        selectedReward = null
        STConstants.IS_DFC_CHALLENGE_ENABLED = true
        if (getCurrentFragment() !is STChallengesFragment) {
            changeFragment(container.id, STChallengesFragment())
        }
    }

    fun loadProfileImage() {
        bottomMenu.itemIconTintList = null

        val profilePic = STPreference.getProfilePic(activityContext)

        if (profilePic != null && profilePic.isNotEmpty()) {
            val menu = bottomMenu.menu
            val menuItem = menu.findItem(R.id.profile)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                menuItem?.iconTintList = null
                menuItem?.iconTintMode = null
            }

            Glide.with(this)
                .asBitmap()
                .load(STPreference.getProfilePic(activityContext))
                //.apply(RequestOptions .circleCropTransform().placeholder(R.drawable.profile_normal))
                .apply(
                    RequestOptions.bitmapTransform(RoundedCorners(160))
                        .placeholder(R.drawable.profile_normal)
                )
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        /*val roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(resources, resource)
                        val roundPx = resource.width.toFloat() * 0.06f
                         dr.cornerRadius = menuItem?.icon?.toBitmap()!!.width.coerceAtLeast(menuItem.icon?.toBitmap()!!.height) / 2.0f
                       roundedBitmapDrawable.isCircular = true*/

                        menuItem?.icon = BitmapDrawable(resources, resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {}
                })
        }
    }

    private fun getCurrentFragment(): STBaseFragment {
        return supportFragmentManager.findFragmentById(container.id) as STBaseFragment
    }

    fun setCategoryList(categoryList: ArrayList<STCategory>) {
        this.categoryList = categoryList
    }


    override fun onRequestPermissionsResult(
        requestCode: Int, @NonNull permissions: Array<String>,
        @NonNull grantResults: IntArray
    ) {
        when (requestCode) {
            STConstants.MAKE_PERMISSION_REQUEST_CODE -> {
                when {
                    grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED
                    -> {
                    }
                }
                return
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterNotificationCountReceiver()
    }

    override fun onResume() {
        super.onResume()
        when {
            STConstants.MARK_ALL_NOTIFICATION_READ -> {
                invokeIntent(STHomeIntent.GetUnreadNotificationCount)
            }
            STConstants.IS_LANGUAGE_CHANGED -> {
                STConstants.IS_LANGUAGE_CHANGED = false
                val newIntent = Intent(activityContext, STHomeActivity::class.java)
                newIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                newIntent.putExtra(STConstants.LANGUAGE_CHANGE, true)
                startActivity(newIntent)
            }
            else -> {
                baseRequest.deviceId = STUtils.getDeviceId(activityContext)
                baseRequest.deviceType = STConstants.DEVICE_TYPE
                baseRequest.deviceToken = STPreference.getDeviceId(activityContext)
                invokeIntent(STHomeIntent.DeviceTokenUpdate(baseRequest))
            }
        }
    }

    override fun onBackPressed() {
        backPressAgainDialog()
    }

    private fun backPressAgainDialog() {
        showOkayCancelDialog {
            setMessage(getString(R.string.exit_app_message))
            setOkayText(activityContext.resources.getString(R.string.ok))
            setCancelText(activityContext.resources.getString(R.string.cancel))
            isLogoVisible(true)
            isCancelVisible(true)
            okayClickListener {
                dismissDialog()
                super.onBackPressed()
            }
            cancelClickListener { dismissDialog() }
        }.show()
    }
}
