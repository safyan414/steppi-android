package com.steppi.steppifitness.ui.challenges.publicTeamChallenge

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import butterknife.OnClick
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.STImageViewPager
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.challenges.data.STChallengeTeamData
import com.steppi.steppifitness.network.response.challenges.data.STChallengesListData
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesController
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesIntent
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesState
import com.steppi.steppifitness.ui.common.STContainerActivity
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.fragment_public_challenge_two.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class STPublicTeamChallengeFragment :
    STBaseViewModelFragment<STChallengesIntent, STChallengesState, STChallengesController>(
        STChallengesController::class.java
    ) {
    private val SELECT_TEAM = 100
    private var challengeId: String? = null
    private var challengesData: STChallengesListData? = null
    private var selectedTeam: STChallengeTeamData? = null
    private var selectedTeamPos: Int = -1
    private var publicJoinDialog: Dialog? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_public_challenge_two
    }

    override fun initViews() {
        challengeId = arguments?.getString(STConstants.CHALLENGES_DATA_ID)
        challengesData =
            arguments?.getSerializable(STConstants.CHALLENGES_DATA) as? STChallengesListData
        STUtils.setValueToView(challengeName, challengesData?.name)
        STUtils.setValueToView(challengeDesc, challengesData?.description)
        challengesData?.name?.let {
            (activityContext as STContainerActivity).setHeader(it)
        } ?: kotlin.run {
            (activityContext as STContainerActivity).setHeader(activityContext.getString(R.string.app_name))
        }
        challengesData?.logo?.let {
            if (it != "") {
                logo.load(activityContext, it) {
                    logo.setImageResource(R.drawable.steppi_icon)
                }
            } else {
                logo.setImageResource(R.drawable.steppi_icon)
            }
        }
        initViewPager()
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
            is STChallengesState.JoinPublicTeamChallenge -> {
                requestDidFinish()
                goToDetailPage(state.challengeDetailsResponse.data)
            }
        }
    }

    override fun onViewModelReady() {
    }

    private fun goToDetailPage(challengeData: STChallengesListData?) {
        if (STUtils.singleClick()) {
            STConstants.UPDATE_STEPPI_CHALLENGE_LIST = true
            val container = Intent(activityContext, STContainerActivity::class.java)
            container.putExtra(
                STConstants.FRAGMENT_NAME,
                ""
            )
            container.putExtra(
                STConstants.FRAGMENT_ID,
                STConstants.FRAGMENT_ID_CHALLENGE_DETAILS
            )
            container.putExtra(STConstants.CHALLENGES_DATA, challengeData)
            container.putExtra(STConstants.CHALLENGES_TYPE, challengeData?.challengeType)
            container.putExtra(STConstants.CHALLENGES_ISPRIVATE, challengeData?.isPrivate)
            challengeData?.joinCode?.let {
                container.putExtra(STConstants.PRIVATE_CHALLENGE_JOIN_CODE, it)
            }
            container.putExtra(STConstants.CHALLENGES_DATA_ID, challengeData?.id)
            startActivity(container)
            activityContext.finish()
        }
    }

    @OnClick(R.id.selectTeam)
    fun selectTeam() {
        val intent = Intent(activityContext, STSelectTeamActivity::class.java)
        intent.putExtra(STConstants.CHALLENGES_DATA_ID, challengeId)
        intent.putExtra(STConstants.SELECTED_TEAM_POS, selectedTeamPos)
        startActivityForResult(intent, SELECT_TEAM)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SELECT_TEAM && resultCode == Activity.RESULT_OK) {
            data?.let {
                selectedTeamPos = it.getIntExtra(STConstants.SELECTED_TEAM_POS, -1)
                selectedTeam =
                    it.getSerializableExtra(STConstants.SELECTED_TEAM) as? STChallengeTeamData
                selectedTeam?.name?.let { selectedTeam ->
                    STUtils.setValueToView(selectTeam, selectedTeam)
                    if (STPreference.getLanguageCode(activityContext) == STConstants.ARABIC_CODE) {
                        selectTeam.setCompoundDrawablesWithIntrinsicBounds(
                            null,
                            null,
                            ContextCompat.getDrawable(activityContext, R.drawable.participants),
                            null
                        )
                    } else {
                        selectTeam.setCompoundDrawablesWithIntrinsicBounds(
                            ContextCompat.getDrawable(activityContext, R.drawable.participants),
                            null,
                            null,
                            null
                        )
                    }
                }
                btnJoinNow.setBackgroundResource(R.drawable.button_selector_enabled)
            }
        }
    }

    private fun initViewPager() {
        val adapter = STImageViewPager(activityContext)
        adapter.setItemImage(challengesData?.images)
        STUtils.setImageSize(activityContext, pager, 0)
        pager?.adapter = adapter
        pager.requestLayout()
        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                setNextPreviousButton(position)
            }

            override fun onPageSelected(position: Int) {
            }
        })
        indicator.setViewPager(pager)
    }

    private fun setNextPreviousButton(position: Int) {
        challengesData?.images?.let {
            if (it.size > 1) {
                if (position in 0..it.size - 2) {
                    if (position <= 0) {
                        previous.setImageResource(R.color.transparent)
                    } else {
                        previous.setImageResource(R.drawable.view_pager_previous_button)
                    }
                    next.setImageResource(R.drawable.view_pager_next_button)
                } else {
                    if (position == it.size - 1) {
                        previous.setImageResource(R.drawable.view_pager_previous_button)
                        next.setImageResource(R.color.transparent)
                    }
                }
            } else {
                previous.setImageResource(R.color.transparent)
                next.setImageResource(R.color.transparent)
            }
        }
    }

    @OnClick(R.id.btnJoinNow)
    fun onJoinClick() {

        if (selectedTeam == null) showToast(getString(R.string.please_select_team))
        else {
            if (challengesData?.publicJoinCode!!) joinTeamWithCodeChallengeDialog()
            else {
                val successDialog: Dialog = showJoinChallengeDialog {
                    clickToAgree {
                    }
                    onTCClick()
                    clickCheckboxLayout {}
                    dialogOK {
                        if (isPleaseAgree()) {
                            if (challengeId != null && selectedTeam?.id != null) {
                                invokeIntent(
                                    STChallengesIntent.JoinPublicChallengeTeam(
                                        challengeId!!,
                                        selectedTeam?.id!!,
                                        ""
                                    )
                                )
                            }
                            dismissDialog()
                        }
                    }
                }
                successDialog.setSize(activityContext)
                successDialog.show()
            }
        }
    }


    /*



    todo: join public team challenge   */

    private fun joinTeamWithCodeChallengeDialog() {
        publicJoinDialog = showJoinDFCChallengeDialog {
            clickToAgree()
            dialogOK {
                if (isPleaseAgree()) {
                    joinPublicChallenge(getChallengeJoinCode()!!)
                    dismissDialog()
                }
            }
        }
        publicJoinDialog?.setSize(activityContext)
        publicJoinDialog?.show()
    }

    private fun joinPublicChallenge(challengeJoinCode: String) {

        requestDidStart()
        //Public Chanllenge with code
        Log.d("TAG", "joinPublicChallenge: ")
        checkNetwork(challengeJoinCode)
    }

    private fun checkNetwork(challengeJoinCode: String) {
        if (STUtils.isInternetOn(activityContext)) {         //network On

            requestDidStart()
            CoroutineScope(Dispatchers.IO).launch {
                checkJoinCode(challengeJoinCode)
            }

        } else showToast(resources.getString(R.string.no_internet))  //no network
    }

    private suspend fun checkJoinCode(challengeJoinCode: String) {

        withContext(Dispatchers.IO) {
            if (challengeId != null && selectedTeam?.id != null) {
                invokeIntent(
                    STChallengesIntent.JoinPublicChallengeTeam(
                        challengeId!!,
                        selectedTeam?.id!!,
                        challengeJoinCode
                    )
                )
            }
        }
    }
}
