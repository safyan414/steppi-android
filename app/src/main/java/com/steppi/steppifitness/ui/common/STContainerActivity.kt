package com.steppi.steppifitness.ui.common

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.PopupMenu
import butterknife.OnClick
import com.steppi.steppifitness.BuildConfig
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.app.STConstants.IS_NEW_MESSAGE
import com.steppi.steppifitness.network.response.category.RedeemRewardData
import com.steppi.steppifitness.network.response.category.STCategory
import com.steppi.steppifitness.network.response.category.STMerchantStoresListData
import com.steppi.steppifitness.network.response.category.STStoresRewardsListData
import com.steppi.steppifitness.network.response.category.data.STMerchantData
import com.steppi.steppifitness.network.response.challenges.ChallengeData
import com.steppi.steppifitness.network.response.challenges.STChallengeDetailsResponse
import com.steppi.steppifitness.network.response.challenges.data.STChallengesListData
import com.steppi.steppifitness.network.response.challenges.data.STParticipantTeams
import com.steppi.steppifitness.network.response.challenges.data.STCreatePrivateChallengeData
import com.steppi.steppifitness.network.response.challenges.data.STMVPTeamList
import com.steppi.steppifitness.ui.base.STBaseActivity
import com.steppi.steppifitness.ui.base.STBaseFragment
import com.steppi.steppifitness.ui.challenges.*
import com.steppi.steppifitness.ui.challenges.dfcmyteam.STDFCMyTeamTabsFragment
import com.steppi.steppifitness.ui.challenges.private_challenge.*
import com.steppi.steppifitness.ui.challenges.publicTeamChallenge.STFragmentMvpParticipant
import com.steppi.steppifitness.ui.challenges.publicTeamChallenge.STFragmentMyDailySteps
import com.steppi.steppifitness.ui.challenges.publicTeamChallenge.STFragmentMyTeamLeaderBoard
import com.steppi.steppifitness.ui.challenges.publicTeamChallenge.STPublicTeamChallengeFragment
import com.steppi.steppifitness.ui.fitness_analytics.STFitnessAnalyticsFragment
import com.steppi.steppifitness.ui.home.STHomeActivity
import com.steppi.steppifitness.ui.home.STWhatsNewFragment
import com.steppi.steppifitness.ui.profile.STEditProfileFragment
import com.steppi.steppifitness.ui.profile.corporate.STCorporateFragment
import com.steppi.steppifitness.ui.profile.mobile.STEditMobileFragment
import com.steppi.steppifitness.ui.profile.otp.STOtpFragment
import com.steppi.steppifitness.ui.profile.password.STChangePasswordFragment
import com.steppi.steppifitness.ui.profile.redeem.STRedeemedFragment
import com.steppi.steppifitness.ui.profile.settings.STChangeLanguageFragment
import com.steppi.steppifitness.ui.profile.settings.STNotificationFragment
import com.steppi.steppifitness.ui.profile.settings.STSettingsFragment
import com.steppi.steppifitness.ui.profile.settings.STUnitConversionFragment
import com.steppi.steppifitness.ui.rewards.STRewardRedeemFragment
import com.steppi.steppifitness.ui.rewards.STRewardsDetailsFragment
import com.steppi.steppifitness.ui.rewards.STStoreLocationSelectionFragment
import com.steppi.steppifitness.ui.rewards.search.STSearchListFragment
import com.steppi.steppifitness.ui.team_detail.STTeamDetailsFragment
import com.steppi.steppifitness.utils.STUtils
import com.steppi.steppifitness.utils.gone
import com.steppi.steppifitness.utils.startActivityFinishAll
import com.steppi.steppifitness.utils.visible
import kotlinx.android.synthetic.main.header_layout.*
import java.util.ArrayList

class STContainerActivity : STBaseActivity() {
    private var fragmentId: Int = 0
    private var fragmentTitle: String? = null
    private var merchantList: STMerchantData? = null
    private var selectedMerchantStore: STMerchantStoresListData? = null
    private var merchantRewardsData: STStoresRewardsListData? = null
    private var challengesData: STChallengesListData? = null
    private var challengeDetailsData: STChallengeDetailsResponse? = null
    private var challengesDataId: String? = null
    private var challengeType: String? = null
    private var challengeIsPrivate: Boolean? = false
    private var searchCategoryList: STCategory? = null
    private var createPrivateChallengeData: STCreatePrivateChallengeData? = null
    private var privateChallengeData: ChallengeData? = null
    private var selectedTheme: String? = null
    private var privateChallengeJoinCode: String? = null
    private var isChallengeStarted: Boolean = false
    private var isChallengeCompleted: Boolean = false
    private var isNewMessage = false
    /*private var merchantId: String? = null
    private var storeId: String? = null*/

    override fun getLayoutId(): Int {
        return R.layout.activity_container
    }

    override fun initViews() {
        fragmentId = intent.getIntExtra(STConstants.FRAGMENT_ID, 0)
        fragmentTitle = intent.getStringExtra(STConstants.FRAGMENT_NAME)

        intent.getSerializableExtra(STConstants.MERCHANT_LIST)?.let {
            merchantList = it as STMerchantData
        }

        intent.getSerializableExtra(STConstants.SELECTED_MERCHANT_STORE)?.let {
            selectedMerchantStore = it as STMerchantStoresListData
        }

        intent.getSerializableExtra(STConstants.SELECTED_REWARD)?.let {
            merchantRewardsData = it as STStoresRewardsListData
        }
        intent?.getSerializableExtra(STConstants.SEARCH_CATEGORY_LIST)?.let {
            searchCategoryList = it as? STCategory
        }
        intent?.getSerializableExtra(STConstants.SELECTED_PRIVATE_CHALLENGE)?.let {
            createPrivateChallengeData = it as? STCreatePrivateChallengeData
        }
        intent?.getStringExtra(STConstants.SELECTED_THEME)?.let {
            selectedTheme = it
        }
        intent?.getSerializableExtra(STConstants.PRIVATE_CHALLENGE_DATA)?.let {
            privateChallengeData = it as? ChallengeData
        }
        intent.getSerializableExtra(STConstants.PRIVATE_CHALLENGE_DETAILS_DATA)?.let {
            challengeDetailsData = it as STChallengeDetailsResponse
        }
        intent?.getStringExtra(STConstants.PRIVATE_CHALLENGE_JOIN_CODE)?.let {
            privateChallengeJoinCode = it
        }
        intent?.getBooleanExtra(STConstants.IS_CHALLENGE_STARTED, false)?.let {
            isChallengeStarted = it
        }
        intent?.getBooleanExtra(STConstants.IS_CHALLENGE_COMPLETED, false)?.let {
            isChallengeCompleted = it
        }
        intent.getSerializableExtra(STConstants.CHALLENGES_DATA)?.let {
            challengesData = it as STChallengesListData

//            challengesData?.let { challengeDetailsData ->
//
//                challengeDetailsData.corporation?.let { corporationData ->
//                    fragmentTitle = corporationData.name
//                } ?: kotlin.run {
//                    fragmentTitle = challengeDetailsData.challengeType
//                }
//
//
//            }
        }

        intent.getStringExtra(STConstants.CHALLENGES_DATA_ID)?.let {
            challengesDataId = it
        }
        intent.getStringExtra(STConstants.CHALLENGES_TYPE)?.let {
            challengeType = it
        }

        intent.getBooleanExtra(STConstants.IS_NEW_MESSAGE, false).let { isNewMessage = it }

        intent.getBooleanExtra(STConstants.CHALLENGES_ISPRIVATE, false).let {
            challengeIsPrivate = it
        }


        /* intent.getStringExtra(STConstants.STORE_ID)?.let {
            storeId = it
        }*/

        STUtils.setValueToView(header_name, fragmentTitle)
        addFragment()
        backStackListener()
    }

    private fun addFragment() {
        when (fragmentId) {
            STConstants.FRAGMENT_ID_DISTANCE -> {
                showFitnessAnalytics(STConstants.ANALYTICS_TYPE_DISTANCE)
            }
            STConstants.FRAGMENT_ID_MINUTES -> {
                showFitnessAnalytics("minutes_values")
                showNotMatching.visible()
            }
            STConstants.FRAGMENT_ID_CALORIES -> {
                showFitnessAnalytics(STConstants.ANALYTICS_TYPE_CALORIES)
            }
            STConstants.FRAGMENT_ID_STEPS -> {
                showFitnessAnalytics(STConstants.ANALYTICS_TYPE_STEPS)
            }
            STConstants.FRAGMENT_ID_REWARDS_DETAILS -> {
                showRewardsDetails()
            }
            STConstants.FRAGMENT_ID_REWARDS_REDEEM -> {
                showRewardsRedeem()
            }
            STConstants.FRAGMENT_ID_EDIT_PROFILE -> {
                editProfile()
            }
            STConstants.FRAGMENT_ID_VIEW_CORPORATE -> {
                viewCorporate()
            }
            STConstants.FRAGMENT_ID_REDEEM_DETAILS -> {
                redeemDetails()
            }
            STConstants.FRAGMENT_ID_CHALLENGE_DETAILS -> {
                if (challengeIsPrivate!!) {
                    challengeDetailTabsList()
                } else {
                    challengeType?.let { challengeType ->
                        when (challengeType) {
                            STConstants.STEPPI_CHALLENGE -> {
                                if (challengesData?.type == STConstants.STEPPI_CHALLENGE_2) {
                                    if (challengesData?.joined != null && !challengesData?.joined!!) {
                                        publicChallenge2()
                                    } else {
                                        challengeDetailTabsList()
                                    }
                                } else if (challengesData?.type == STConstants.TOUGH_MUDDER_CHALLENGE) {
                                    toughMudderChallengeDetails()
                                } else {
                                    challengeDetailTabsList()
                                }
                            }
                            else -> {
                                challengeDetailTabsList()                           //in case of new message of corporate
                            }
                        }
                    } ?: kotlin.run {
                        challengeDetails()
                    }
                }
            }
            STConstants.FRAGMENT_ID_NOTIFICATION -> {
                notification()
            }
            STConstants.FRAGMENT_ID_EDIT_MOBILE -> {
                editMobile()
            }
            STConstants.FRAGMENT_ID_VALIDATE_OTP -> {
                validateOtp()
            }
            STConstants.FRAGMENT_ID_CHANGE_PASSWORD -> {
                changePassword()
            }
            STConstants.FRAGMENT_ID_CHALLENGE_STATUS -> {
                challengeStatus()
            }
            STConstants.FRAGMENT_ID_TEAM_DETAILS -> {
                showTeamDetails()
            }
            STConstants.FRAGMENT_ID_LOCATION_SELECTION -> {
                showLocationSelection()
            }
            STConstants.FRAGMENT_ID_SETTINGS -> {
                showSettings()
            }
            STConstants.FRAGMENT_ID_UNIT_CONVERSION -> {
                showUnitConversion()
            }
            STConstants.FRAGMENT_ID_SEARCH -> {
                showSearchList()
            }

            STConstants.FRAGMENT_ID_VIEW_ALL_LEADER_BOARD -> {
                showViewAllLeaderboard()
            }
            STConstants.FRAGMENT_CHANGE_LANGUAGE -> {
                showChangeLanguage()
            }
            STConstants.FRAGMENT_ID_ADD_PRIVATE_CHALLENGE -> {
                addPrivateChallenge()
            }
            STConstants.FRAGMENT_ID_CREATE_PRIVATE_CHALLENGE -> {
                createPrivateChallenge()
            }
            STConstants.FRAGMENT_ID_PRIVATE_CHALLENGE_CHOOSE_THEME -> {
                createPrivateChallengeChooseTheme()
            }
            STConstants.FRAGMENT_ID_PRIVATE_CHALLENGE_NOT_FOUND -> {
                privateChallengeNotFound()
            }
            STConstants.FRAGMENT_ID_MY_TEAM_LEADER_BOARD_DFC -> {
                dfcMyTeamLeaderBoard()
            }
            STConstants.FRAGMENT_ID_MY_TEAM_LEADERBOARD -> {
                myTeamLeaderBoard()
            }
            STConstants.FRAGMENT_ID_MVP_PARTICIPANT -> {
                mvpParticipant()
            }
            STConstants.FRAGMENT_ID_DAILY_STEP -> {
                myDailySteps()
            }
            STConstants.FRAGMENT_ID_WHATS_NEW -> {
                whatsNewPage()
            }
            STConstants.FRAGMENT_ID_VIEW_ALL_LEADER_BOARD_PC4 -> {
                viewAllLeaderBoardPc4()
            }
        }
    }

    private fun viewAllLeaderBoardPc4() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        val data = Bundle()
        data.putBoolean(STConstants.IS_CHALLENGE_STARTED, isChallengeStarted)
        data.putString(STConstants.CHALLENGES_DATA_ID, challengesDataId)
        data.putSerializable(STConstants.CHALLENGES_DATA, challengesData)
        val fragment = STViewAllLeaderBoardDFCFragment()
        fragment.arguments = data
        ft.add(
            R.id.fragmentContainer,
            fragment,
            "viewAllLeaderBoardDFCFragment"
        )
        ft.addToBackStack("viewAllLeaderBoardDFCFragment")
        ft.commit()
    }

    private fun toughMudderChallengeDetails() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        val data = Bundle()
        data.putString(STConstants.CHALLENGES_DATA_ID, challengesDataId)
        data.putSerializable(STConstants.CHALLENGES_DATA, challengesData)
        val fragment = STToughMudderChallengeDetailsFragment()
        fragment.arguments = data
        ft.add(
            R.id.fragmentContainer,
            fragment,
            "toughMudderChallengeDetailsFragment"
        )
        ft.addToBackStack("toughMudderChallengeDetailsFragment")
        ft.commit()
    }

    private fun backStackListener() {
        supportFragmentManager.addOnBackStackChangedListener {
            if (activeFrag is STEditProfileFragment) {
                saveEditProfile.visible()
            } else {
                saveEditProfile.gone()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (activeFrag is STEditProfileFragment) {
            if (STConstants.PROFILE_UPDATE) {
                STConstants.PROFILE_UPDATE = false
                (activeFrag as STEditProfileFragment).getProfile()
            }
        } else if (activeFrag is STCreatePrivateChallengeFragment) {
            if (STConstants.IS_PRIVATE_CHALLENGE_CREATED) {
                STConstants.IS_PRIVATE_CHALLENGE_CREATED = false
                activityContext.finish()
            }
        }
    }

    private fun privateChallengeNotFound() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()

//        val data = Bundle()
//        data.putSerializable(STConstants.PRIVATE_CHALLENGE_DATA, privateChallengeData)

        val fragment = STNoPrivateChallengeFoundFragment()
//        fragment.arguments = data
        ft.add(
            R.id.fragmentContainer,
            fragment,
            "noPrivateChallengeFoundFragment"
        )
        ft.addToBackStack("noPrivateChallengeFoundFragment")
        ft.commit()
    }

    private fun dfcMyTeamLeaderBoard() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()

        val data = Bundle()
//        data.putSerializable(STConstants.PRIVATE_CHALLENGE_DATA, privateChallengeData)

        intent.getSerializableExtra(STConstants.PARTICIPANT_TEAM)?.let {
            data.putSerializable(STConstants.PARTICIPANT_TEAM, it as STParticipantTeams)
        }
        intent.getIntExtra(STConstants.PARTICIPANT_TEAM_RANK_POSITION, 0).let {
            data.putInt(STConstants.PARTICIPANT_TEAM_RANK_POSITION, it)
        }
        intent.getStringExtra(STConstants.PARTICIPANT_TEAM_ID)?.let {
            data.putString(STConstants.PARTICIPANT_TEAM_ID, it)
        }
        data.putBoolean(STConstants.IS_CHALLENGE_STARTED, isChallengeStarted)
        data.putBoolean(STConstants.IS_CHALLENGE_COMPLETED, isChallengeCompleted)
        data.putString(STConstants.CHALLENGES_DATA_ID, challengesDataId)
        intent.getStringExtra(STConstants.CHALLENGES_END_DATE)?.let {
            data.putString(STConstants.CHALLENGES_END_DATE, it)
        }
        intent.getStringExtra(STConstants.CHALLENGES_START_DATE)?.let {
            data.putString(STConstants.CHALLENGES_START_DATE, it)
        }
        intent.getSerializableExtra(STConstants.CHALLENGES_DATA)?.let {
            data.putSerializable(STConstants.CHALLENGES_DATA, it as STChallengesListData)
        }
        val fragment = STDFCMyTeamTabsFragment()
        fragment.arguments = data
        ft.add(
            R.id.fragmentContainer,
            fragment,
            "DFCMyTeamTabsFragment"
        )
        ft.addToBackStack("DFCMyTeamTabsFragment")
        ft.commit()
    }

    private fun myTeamLeaderBoard() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()

        val data = Bundle()
//        data.putSerializable(STConstants.PRIVATE_CHALLENGE_DATA, privateChallengeData)

        intent.getSerializableExtra(STConstants.PARTICIPANT_TEAM)?.let {
            data.putSerializable(STConstants.PARTICIPANT_TEAM, it as STParticipantTeams)
        }
        intent.getIntExtra(STConstants.PARTICIPANT_TEAM_RANK_POSITION, 0).let {
            data.putInt(STConstants.PARTICIPANT_TEAM_RANK_POSITION, it)
        }
        intent.getStringExtra(STConstants.PARTICIPANT_TEAM_ID)?.let {
            data.putString(STConstants.PARTICIPANT_TEAM_ID, it)
        }
        data.putBoolean(STConstants.IS_CHALLENGE_STARTED, isChallengeStarted)
        data.putBoolean(STConstants.IS_CHALLENGE_COMPLETED, isChallengeCompleted)
        data.putString(STConstants.CHALLENGES_DATA_ID, challengesDataId)
        intent.getStringExtra(STConstants.CHALLENGES_END_DATE)?.let {
            data.putString(STConstants.CHALLENGES_END_DATE, it)
        }
        intent.getStringExtra(STConstants.CHALLENGES_START_DATE)?.let {
            data.putString(STConstants.CHALLENGES_START_DATE, it)
        }
        val fragment = STFragmentMyTeamLeaderBoard()
        fragment.arguments = data
        ft.add(
            R.id.fragmentContainer,
            fragment,
            "MyTeamLeaderboard"
        )
        ft.addToBackStack("MyTeamLeaderboard")
        ft.commit()
    }


    private fun mvpParticipant() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()

        val data = Bundle()
        intent.getSerializableExtra(STConstants.MVP_PARTICIPANT)?.let {
            data.putSerializable(STConstants.MVP_PARTICIPANT, it as ArrayList<STMVPTeamList>)
        }
        data.putBoolean(STConstants.IS_CHALLENGE_STARTED, isChallengeStarted)
        data.putBoolean(STConstants.IS_CHALLENGE_COMPLETED, isChallengeCompleted)
//        data.putSerializable(STConstants.PRIVATE_CHALLENGE_DATA, privateChallengeData)
        data.putString(STConstants.CHALLENGES_DATA_ID, challengesDataId)
        val fragment = STFragmentMvpParticipant()
        fragment.arguments = data
        ft.add(
            R.id.fragmentContainer,
            fragment,
            "MvpParticipant"
        )
        ft.addToBackStack("MvpParticipant")
        ft.commit()
    }

    private fun whatsNewPage() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()

        val data = Bundle()
        intent.getSerializableExtra(STConstants.WHATS_NEW_SCREEN_LIST_DATA)?.let {
            data.putSerializable(STConstants.WHATS_NEW_SCREEN_LIST_DATA, it as ArrayList<String>)
        }

        val fragment = STWhatsNewFragment()
        fragment.arguments = data
        ft.add(
            R.id.fragmentContainer, fragment, "whatsNewFragment"
        )
        ft.addToBackStack("whatsNewFragment")
        ft.commit()
    }

    private fun myDailySteps() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()

        val data = Bundle()
        data.putSerializable(STConstants.CHALLENGES_DATA_ID, challengesDataId)
        intent.getIntExtra(STConstants.RANK, 0).let {
            data.putInt(STConstants.RANK, it)
        }
        data.putBoolean(STConstants.IS_CHALLENGE_STARTED, isChallengeStarted)
        data.putBoolean(STConstants.IS_CHALLENGE_COMPLETED, isChallengeCompleted)
        intent.getStringExtra(STConstants.CHALLENGES_END_DATE)?.let {
            data.putString(STConstants.CHALLENGES_END_DATE, it)
        }
        intent.getStringExtra(STConstants.CHALLENGES_START_DATE)?.let {
            data.putString(STConstants.CHALLENGES_START_DATE, it)
        }
        intent.getStringExtra(STConstants.CHALLENGES_TYPE)?.let {
            data.putString(STConstants.CHALLENGES_TYPE, it)
        }
        val fragment = STFragmentMyDailySteps()
        fragment.arguments = data
        ft.add(
            R.id.fragmentContainer,
            fragment,
            "MyDailySteps"
        )
        ft.addToBackStack("MyDailySteps")
        ft.commit()
    }

    private fun createPrivateChallengeChooseTheme() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()

        val data = Bundle()
        data.putSerializable(STConstants.SELECTED_PRIVATE_CHALLENGE, createPrivateChallengeData)

        val fragment = STSelectThemeFragment()
        fragment.arguments = data
        ft.add(
            R.id.fragmentContainer, fragment, "selectThemeFragment"
        )
        ft.addToBackStack("selectThemeFragment")
        ft.commit()
    }

    private fun showSearchList() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()

        val data = Bundle()
        data.putSerializable(STConstants.SEARCH_CATEGORY_LIST, searchCategoryList)

        val fragment = STSearchListFragment()
        fragment.arguments = data
        ft.add(
            R.id.fragmentContainer, fragment, "searchListFragment"
        )
        ft.addToBackStack("searchListFragment")
        ft.commit()
    }

    private fun showSettings() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.add(
            R.id.fragmentContainer, STSettingsFragment(), "settingsFragment"
        )
        ft.addToBackStack("settingsFragment")
        ft.commit()
    }

    private fun showUnitConversion() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.add(
            R.id.fragmentContainer, STUnitConversionFragment(), "unitConversionFragment"
        )
        ft.addToBackStack("unitConversionFragment")
        ft.commit()
    }

    private fun createPrivateChallenge() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.add(
            R.id.fragmentContainer, STCreatePrivateChallengeFragment(), "createPrivateChallenge"
        )
        ft.addToBackStack("createPrivateChallenge")
        ft.commit()
    }

    private fun addPrivateChallenge() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.add(
            R.id.fragmentContainer, STAddPrivateChallengeFragment(), "addPrivateChallengeFragment"
        )
        ft.addToBackStack("addPrivateChallengeFragment")
        ft.commit()
    }

    private fun showChangeLanguage() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.add(
            R.id.fragmentContainer, STChangeLanguageFragment(), "changeLanguageFragment"
        )
        ft.addToBackStack("changeLanguageFragment")
        ft.commit()
    }

    private fun challengeStatus() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.add(
            R.id.fragmentContainer,
            STChallengeStatusFragment(),
            "challengeStatusFragment"
        )
        ft.addToBackStack("challengeStatusFragment")
        ft.commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.clear()
    }

    private fun challengeDetails() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()

        val data = Bundle()
        data.putString(STConstants.CHALLENGES_DATA_ID, challengesDataId)
        val fragment = STChallengeDetailsFragment()
        fragment.arguments = data
        ft.add(
            R.id.fragmentContainer,
            fragment,
            "myChallengesDetailsFragment"
        )
        ft.addToBackStack("myChallengesDetailsFragment")
        ft.commit()
    }

    private fun publicChallenge2() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()

        val data = Bundle()
        data.putString(STConstants.CHALLENGES_DATA_ID, challengesDataId)
        data.putSerializable(STConstants.CHALLENGES_DATA, challengesData)

        val fragment = STPublicTeamChallengeFragment()
        fragment.arguments = data
        ft.add(
            R.id.fragmentContainer,
            fragment,
            "PublicChallengeTwoFragment"
        )
        ft.addToBackStack("PublicChallengeTwoFragment")
        ft.commit()
    }

    private fun challengeDetailTabsList() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()

        val data = Bundle()
        data.putSerializable(STConstants.PRIVATE_CHALLENGE_DETAILS_DATA, challengeDetailsData)
        data.putString(STConstants.CHALLENGES_DATA_ID, challengesDataId)
        data.putString(STConstants.PRIVATE_CHALLENGE_JOIN_CODE, privateChallengeJoinCode)
        data.putBoolean(STConstants.CHALLENGES_ISPRIVATE, challengeIsPrivate!!)
        if (isNewMessage)
            data.putBoolean(IS_NEW_MESSAGE, isNewMessage)
        val fragment = STChallengeDetailsTabsFragment()
        fragment.arguments = data
        ft.add(
            R.id.fragmentContainer,
            fragment,
            "STChallengeDetailsTabsFragment"
        )
        ft.addToBackStack("STChallengeDetailsTabsFragment")
        ft.commit()
    }


    private fun showViewAllLeaderboard() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()

        val data = Bundle()
        data.putString(STConstants.CHALLENGES_DATA_ID, challengesDataId)
        data.putBoolean(STConstants.IS_CHALLENGE_STARTED, isChallengeStarted)
        data.putBoolean(STConstants.IS_CHALLENGE_COMPLETED, isChallengeCompleted)
        data.putBoolean(STConstants.CHALLENGES_ISPRIVATE, challengeIsPrivate!!)
        data.putString(STConstants.CHALLENGES_TYPE, challengeType)
        data.putSerializable(STConstants.CHALLENGES_DATA, challengesData)
        val fragment = STViewAllLeaderBoardFragment()
        fragment.arguments = data
        ft.add(
            R.id.fragmentContainer,
            fragment,
            "STViewAllLeaderBoardFragment"
        )
        ft.addToBackStack("STViewAllLeaderBoardFragment")
        ft.commit()
    }

    private fun redeemDetails() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.add(
            R.id.fragmentContainer,
            STRedeemedFragment(),
            "redeemedFragment"
        )
        ft.addToBackStack("redeemedFragment")
        ft.commit()
    }

    private fun viewCorporate() {
//        notificationLayout.visible()
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.add(
            R.id.fragmentContainer,
            STCorporateFragment(),
            "corporateFragment"
        )
        ft.addToBackStack("corporateFragment")
        ft.commit()
    }

    private fun editProfile() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.add(
            R.id.fragmentContainer,
            STEditProfileFragment(),
            "editProfileFragment"
        )
        ft.addToBackStack("editProfileFragment")
        ft.commit()
    }

    private fun editMobile() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.add(
            R.id.fragmentContainer,
            STEditMobileFragment(),
            "editMobileFragment"
        )
        ft.addToBackStack("editProfileFragment")
        ft.commit()
    }

    private fun validateOtp() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.add(
            R.id.fragmentContainer,
            STOtpFragment(),
            "editOtpFragment"
        )
        ft.addToBackStack("editOtpFragment")
        ft.commit()
    }

    private fun changePassword() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.add(
            R.id.fragmentContainer,
            STChangePasswordFragment(),
            "changePassword"
        )
        ft.addToBackStack("changePassword")
        ft.commit()
    }

    private fun showRewardsDetails() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        val data = Bundle()
        data.putSerializable(STConstants.MERCHANT_LIST, merchantList)
        val fragment = STRewardsDetailsFragment()
        fragment.arguments = data
        ft.add(
            R.id.fragmentContainer,
            fragment, "rewardsDetailsFragment"
        )
        ft.addToBackStack("rewardsDetailsFragment")
        ft.commit()
    }

    private fun showRewardsRedeem() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        val data = Bundle()

        val isFromNotification = intent.getBooleanExtra(STConstants.IS_NOTIFICATION, false)

        if (isFromNotification) {
            intent.getStringExtra(STConstants.NOTIFICATION_REWARD_ID)?.let {
                data.putString(STConstants.NOTIFICATION_REWARD_ID, it)
            }
            intent.getStringExtra(STConstants.NOTIFICATION_REWARD_TYPE)?.let {
                data.putString(STConstants.NOTIFICATION_REWARD_TYPE, it)
            }
            data.putBoolean(STConstants.IS_NOTIFICATION, true)
        } else {
            data.putSerializable(STConstants.MERCHANT_LIST, merchantList)
            data.putSerializable(STConstants.SELECTED_REWARD, merchantRewardsData)
            data.putSerializable(STConstants.SELECTED_MERCHANT_STORE, selectedMerchantStore)

            intent.getSerializableExtra(STConstants.DIGITAL_REWARD_REDEEM_DATA)?.let {
                data.putSerializable(STConstants.DIGITAL_REWARD_REDEEM_DATA, it as RedeemRewardData)
            }
        }

        /*data.putString(STConstants.MERCHANT_ID, merchantId)
        data.putString(STConstants.STORE_ID, storeId)*/

        shareReward.visibility = View.VISIBLE
        val fragment = STRewardRedeemFragment()
        fragment.arguments = data
        ft.add(
            R.id.fragmentContainer,
            fragment,
            "rewardRedeemFragment"
        )
        ft.addToBackStack("rewardRedeemFragment")
        ft.commit()
    }

    private fun showFitnessAnalytics(analyticsType: String) {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        val data = Bundle()
        data.putString(STConstants.ANALYTICS_TYPE, analyticsType)
        val fragment = STFitnessAnalyticsFragment()

        fragment.arguments = data
        ft.add(
            R.id.fragmentContainer, fragment, "fitnessAnalyticsFragment"
        )
        ft.addToBackStack("fitnessAnalyticsFragment")
        ft.commit()
    }

    private fun notification() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.add(
            R.id.fragmentContainer,
            STNotificationFragment(),
            "notificationFragment"
        )
        ft.addToBackStack("notificationFragment")
        ft.commit()
    }


    private fun showTeamDetails() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        val data = Bundle()
        intent.getSerializableExtra(STConstants.PARTICIPANT_TEAM)?.let {
            data.putSerializable(STConstants.PARTICIPANT_TEAM, it as STParticipantTeams)
        }

        intent.getStringExtra(STConstants.CHALLENGE_ID)?.let {
            data.putString(STConstants.CHALLENGE_ID, it)
        }

        intent.getBooleanExtra(STConstants.CHALLENGE_STARTED, false).let {
            data.putBoolean(STConstants.CHALLENGE_STARTED, it)
        }

        intent.getBooleanExtra(STConstants.CHALLENGE_COMPLETED, false).let {
            data.putBoolean(STConstants.CHALLENGE_COMPLETED, it)
        }

        if (isNewMessage)
            data.putBoolean(IS_NEW_MESSAGE, isNewMessage)

        val fragment = STTeamDetailsFragment()
        fragment.arguments = data
        ft.add(
            R.id.fragmentContainer,
            fragment,
            "teamDetailsFragment"
        )
        ft.addToBackStack("teamDetailsFragment")
        ft.commit()
    }


    private fun showLocationSelection() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        val data = Bundle()

        val isFromNotification = intent.getBooleanExtra(STConstants.IS_NOTIFICATION, false)

        if (isFromNotification) {
            intent.getStringExtra(STConstants.NOTIFICATION_REWARD_ID)?.let {
                data.putString(STConstants.NOTIFICATION_REWARD_ID, it)
            }
            data.putBoolean(STConstants.IS_NOTIFICATION, true)
        }

        data.putSerializable(STConstants.MERCHANT_LIST, merchantList)
        data.putSerializable(STConstants.SELECTED_MERCHANT_STORE, selectedMerchantStore)

        val fragment = STStoreLocationSelectionFragment()
        fragment.arguments = data
        ft.add(
            R.id.fragmentContainer,
            fragment,
            "locationSelectionFragment"
        )
        ft.addToBackStack("locationSelectionFragment")
        ft.commit()


        /*  val fm = supportFragmentManager
          val ft = fm.beginTransaction()
          val data = Bundle()
          data.putSerializable(STConstants.MERCHANT_LIST, merchantList)
          val fragment = STRewardsDetailsFragment()
          fragment.arguments = data
          ft.add(
              R.id.fragmentContainer,
              fragment, "rewardsDetailsFragment"
          )
          ft.addToBackStack("rewardsDetailsFragment")
          ft.commit()*/
    }


    private val activeFrag: STBaseFragment?
        get() {
            return supportFragmentManager.findFragmentById(R.id.fragmentContainer) as? STBaseFragment
        }

    fun setImage(resultUri: Uri?) {
        resultUri?.let {
            val mmBaseFragment = activeFrag
            if (mmBaseFragment is STEditProfileFragment) {
                mmBaseFragment.setImage(it)
            }
        }
    }

    @OnClick(R.id.saveEditProfile)
    fun saveEditProfile() {
        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as STBaseFragment
        if (currentFragment is STEditProfileFragment) {
            currentFragment.saveProfile()
        }
    }

    @OnClick(R.id.showNotMatching)
    fun notShowingActiveMinutesView() {
        val learnMoreIntent = Intent(activityContext, STWebViewActivity::class.java)
        learnMoreIntent.putExtra(
            STConstants.WEB_URL,
            BuildConfig.API_URL + STConstants.NOT_SHOWING_ACTIVE_MINUTES
        )
        learnMoreIntent.putExtra(
            STConstants.WEB_HEADER,
            activityContext.resources.getString(R.string.not_matching)
        )
        startActivity(learnMoreIntent)
    }

    @OnClick(R.id.shareReward)
    fun shareReward() {
        val frag =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as? STRewardRedeemFragment
        frag?.let {
            frag.shareReward()
        }
    }

    @OnClick(R.id.moreMenu)
    fun moreMenuClick() {
        val frag =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as? STChallengeDetailsFragment
        frag?.let {
            val popupMenu: PopupMenu = PopupMenu(this, moreMenu)
            popupMenu.menuInflater.inflate(R.menu.menu_challenge_detail, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menu_leave_challenge -> frag.leaveChallengeDialog()
                }
                true
            }
            popupMenu.show()
        }
    }

    fun manageMoreMenuVisibility(joined: Boolean, completed: Boolean) {
        if (joined && !completed) moreMenu.visible() else moreMenu.gone()
    }

    @OnClick(R.id.moreMenuPrivateChallenge)
    fun moreMenuPrivateChallengeClick() {
        val frag =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as? STChallengeDetailsTabsFragment
        frag?.let {
            val popupMenu: PopupMenu = PopupMenu(this, moreMenuPrivateChallenge)
            popupMenu.menuInflater.inflate(R.menu.menu_challenge_detail, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menu_leave_challenge -> frag.leaveChallengeDialog()
                }
                true
            }
            popupMenu.show()
        }
    }

    fun manageMoreMenuPrivateChallengeVisibility(joined: Boolean, completed: Boolean) {
        if (joined && !completed) moreMenuPrivateChallenge.visible() else moreMenuPrivateChallenge.gone()
    }

    private val activeFragmentCount: Int
        get() {
            val fragmentManager = supportFragmentManager
            return if (fragmentManager.backStackEntryCount == 0) {
                0
            } else fragmentManager.backStackEntryCount
        }

    override fun onBackPressed() {
        if (activeFragmentCount == 1) {
            if (isTaskRoot) { //isFromNotification
                finish()
                startActivityFinishAll(Intent(activityContext, STHomeActivity::class.java))
            } else {
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
        } else {
        }
    }

    @OnClick(R.id.header_back)
    override fun onBack() {
        onBackPressed()
    }

    fun setHeader(title: String?) {
        title?.let {
            STUtils.setValueToView(header_name, title)
        }
    }
}
