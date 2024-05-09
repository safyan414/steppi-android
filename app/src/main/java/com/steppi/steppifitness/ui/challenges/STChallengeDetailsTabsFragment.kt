package com.steppi.steppifitness.ui.challenges

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.redeemed.STRedeemedViewPagerAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.app.STConstants.IS_NEW_MESSAGE
import com.steppi.steppifitness.network.response.challenges.STChallengeDetailsResponse
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.challenges.leaderboard.STChallengeLeaderBoardFragment
import com.steppi.steppifitness.ui.challenges.leaderboard.STDFCChallengeLeaderBoardFragment
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesController
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesIntent
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesState
import com.steppi.steppifitness.utils.STUtils
import com.steppi.steppifitness.utils.gone
import com.steppi.steppifitness.utils.showToast
import com.steppi.steppifitness.utils.visible
import kotlinx.android.synthetic.main.fragment_challenge_details_tab.*
import java.util.*
import kotlin.collections.ArrayList

class STChallengeDetailsTabsFragment :
    STBaseViewModelFragment<STChallengesIntent, STChallengesState, STChallengesController>(
        STChallengesController::class.java
    ) {
    private var challengesId: String? = null
    private var challengeIsPrivate: Boolean? = false
    private var challengeDetailsData: STChallengeDetailsResponse? = null
    private var privateChallengeJoinCode: String? = null
    private var challengeDetailsFragment: Fragment? = null
    private var isNewMessage = false

    override fun getLayoutId(): Int {
        return R.layout.fragment_challenge_details_tab
    }

    override fun initViews() {
        initData()
        initPager()
    }

    private fun initPager() {
    }

    private fun initData() {
        arguments?.getSerializable(STConstants.PRIVATE_CHALLENGE_DETAILS_DATA)?.let {
            challengeDetailsData = it as STChallengeDetailsResponse
        }
        arguments?.getBoolean(STConstants.CHALLENGES_ISPRIVATE, false)?.let {
            challengeIsPrivate = it
        }
        arguments?.getString(STConstants.PRIVATE_CHALLENGE_JOIN_CODE)?.let {
            privateChallengeJoinCode = it
        }
        challengesId = arguments?.getString(STConstants.CHALLENGES_DATA_ID)
        arguments?.getBoolean(IS_NEW_MESSAGE)?.let {
            isNewMessage = it
        }
    }

    override fun onViewModelReady() {
        challengeDetailsData?.let {
            //            setChallengeDetailsData(it)
            if (challengeIsPrivate!!) {
                invokeIntent(
                    STChallengesIntent.GetPrivateChallengeDetails(
                        challengesId!!,
                        privateChallengeJoinCode!!
                    )
                )
            }
        } ?: kotlin.run {
            if (challengeIsPrivate!!) {
                privateChallengeJoinCode?.let { privateChallengeJoinCode ->
                    invokeIntent(
                        STChallengesIntent.GetPrivateChallengeDetails(
                            challengesId!!,
                            privateChallengeJoinCode
                        )
                    )
                } ?: kotlin.run {
                    getChallengeDetailsApiCall()
                }
            } else {
                getChallengeDetailsApiCall()
            }
        }
    }

    private fun getChallengeDetailsApiCall() {
        challengesId?.let {
            invokeIntent(STChallengesIntent.GetChallengeDetails(it))
        }
    }

    override fun processState(state: STChallengesState) {
        when (state) {
            is STChallengesState.Loading -> {
                requestDidStart()
            }
            is STChallengesState.Error -> {
                requestDidFinish()
                showToast(state.errorData?.message)
                manageError(state.errorData?.statusCode)
                activityContext.finish()
            }
            is STChallengesState.GetChallengeDetails -> {
                requestDidFinish()
                setChallengeDetailsData(state.challengeDetailsResponse)
            }
            is STChallengesState.GetPrivateChallengeDetails -> {
                requestDidFinish()
                setChallengeDetailsData(state.challengeDetailsResponse)
            }
        }
    }

    fun leaveChallengeDialog() {
        when (challengeDetailsFragment) {
            is STPublicChallengeDetailsFragment ->
                (challengeDetailsFragment as? STPublicChallengeDetailsFragment)?.leaveChallengeDialog()
            is STChallengeDetailsFragment ->
                (challengeDetailsFragment as? STChallengeDetailsFragment)?.leaveChallengeDialog()

        }
    }

    private fun setChallengeDetailsData(challengeDetailsResponse: STChallengeDetailsResponse?) {
        challengeTabLayout.visible()
        val fragmentList = ArrayList<Fragment>()
        val mFragmentTitleList = ArrayList<String>()
        val leaderBoardData = Bundle()
        challengeDetailsResponse?.let {
            leaderBoardData.putSerializable(STConstants.CHALLENGES_DATA_LIST, it)
            challengeDetailsFragment = when (it.data?.challengeType) {
                STConstants.STEPPI_CHALLENGE -> {
                    STPublicChallengeDetailsFragment()
                }
                else -> {
                    STChallengeDetailsFragment()
                }
            }
        } ?: kotlin.run {
            challengeDetailsFragment = STPublicChallengeDetailsFragment()
        }
        challengesId?.let {
            leaderBoardData.putString(STConstants.CHALLENGES_DATA_ID, it)
        }
        challengeDetailsResponse?.let {
//            if (it.data?.theme == STConstants.CHALLENGE_THEME_1) {
            if (it.data?.type == STConstants.DUBAI_FITNESS_CHALLENGE) {
                val leaderBoardFragment = STDFCChallengeLeaderBoardFragment()
                leaderBoardFragment.arguments = leaderBoardData
                fragmentList.add(leaderBoardFragment)
                mFragmentTitleList.add(activityContext.resources.getString(R.string.leader_board))
            } else {
                setNormalLeaderBoard(fragmentList, mFragmentTitleList, leaderBoardData)
            }
//            } else if (it.data?.theme == STConstants.CHALLENGE_THEME_2) {
//                if (it.data?.type == STConstants.DUBAI_FITNESS_CHALLENGE) {
//                    val leaderBoardFragment = STDFCChallengeLeaderBoardFragment()
//                    leaderBoardFragment.arguments = leaderBoardData
//                    fragmentList.add(leaderBoardFragment)
//                    mFragmentTitleList.add(activityContext.resources.getString(R.string.leader_board))
//                } else {
//                    setNormalLeaderBoard(fragmentList, mFragmentTitleList, leaderBoardData)
//                }
//            } else {
//                setNormalLeaderBoard(fragmentList, mFragmentTitleList, leaderBoardData)
//            }
        } ?: kotlin.run {
            setNormalLeaderBoard(fragmentList, mFragmentTitleList, leaderBoardData)
        }
        val data = Bundle()
        challengeDetailsResponse?.let {
            data.putSerializable(STConstants.CHALLENGES_DATA_LIST, it)
        }
        challengeDetailsResponse?.data?.let {
            data.putBoolean(
                STConstants.CHALLENGES_ISPRIVATE,
                it.isPrivate!!
            )
            it.joinCode?.let { joinCode ->
                data.putString(STConstants.PRIVATE_CHALLENGE_JOIN_CODE, joinCode)
            }
        }
        challengesId?.let {
            data.putString(STConstants.CHALLENGES_DATA_ID, it)
        }
        (challengeDetailsFragment as? STPublicChallengeDetailsFragment)?.setTestCallback {
            challengeDetailsTabLayout.visible()
        }
        data.putBoolean(IS_NEW_MESSAGE, isNewMessage)
        challengeDetailsFragment?.arguments = data
        challengeDetailsFragment?.let { fragmentList.add(it) }
        mFragmentTitleList.add(activityContext.resources.getString(R.string.info))
        challengeDetailsViewpager.adapter =
            STRedeemedViewPagerAdapter(fragmentManager!!, fragmentList, mFragmentTitleList)
        challengeDetailsTabLayout.setupWithViewPager(challengeDetailsViewpager)
        challengeDetailsResponse?.data?.let { challengeDetailsData ->
            if (challengeDetailsData.type == STConstants.STEPPI_CHALLENGE_3) {
                challengeDetailsViewpager.currentItem = 1
                challengeDetailsTabLayout.gone()
            } else {
                if (!challengeDetailsData.joined!!) {
                    challengeDetailsViewpager.currentItem = 1
                    challengeDetailsTabLayout.gone()
                } else {
                    challengeDetailsData.startDate?.let { startDate ->
                        val currentDate = STUtils.getFormattedDate(
                            Calendar.getInstance(),
                            "yyyy-MM-dd"
                        )
                        when (STUtils.compareDate(startDate, currentDate)) {
                            STConstants.CHALLENGE_NOT_STARTED -> {
                                challengeDetailsViewpager.currentItem = 1
                                challengeDetailsTabLayout.gone()
                            }
                            STConstants.CHALLENGE_STARTED -> {
                                when (challengeDetailsData.theme) {
                                    STConstants.CHALLENGE_THEME_1 -> {          //space theme for corporate challenge
                                        challengeDetailsViewpager.currentItem = 0
                                        challengeDetailsTabLayout.visible()

                                        if (isNewMessage)
                                            challengeDetailsViewpager.currentItem = 1

                                    }
                                    STConstants.CHALLENGE_THEME_2 -> {          //theme for dfc challenge
                                        challengeDetailsViewpager.currentItem = 0
                                        challengeDetailsTabLayout.visible()
                                    }
                                    else -> {           //No Theme from server
                                        challengeDetailsViewpager.currentItem = 1
                                        challengeDetailsTabLayout.gone()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setNormalLeaderBoard(
        fragmentList: ArrayList<Fragment>,
        mFragmentTitleList: ArrayList<String>,
        leaderBoardData: Bundle
    ) {
        val leaderBoardFragment = STChallengeLeaderBoardFragment()
        leaderBoardFragment.arguments = leaderBoardData
        fragmentList.add(leaderBoardFragment)
        mFragmentTitleList.add(activityContext.resources.getString(R.string.leader_board))
    }
}
