package com.steppi.steppifitness.ui.challenges.private_challenge

import android.content.Intent
import androidx.core.widget.doOnTextChanged
import butterknife.OnClick
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.STAPIConstants
import com.steppi.steppifitness.network.response.challenges.STChallengeDetailsResponse
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesController
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesIntent
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesState
import com.steppi.steppifitness.ui.common.STContainerActivity
import com.steppi.steppifitness.utils.STUtils
import com.steppi.steppifitness.utils.showToast
import kotlinx.android.synthetic.main.fragment_add_private_challenge.*

class STAddPrivateChallengeFragment :
    STBaseViewModelFragment<STChallengesIntent, STChallengesState, STChallengesController>(
        STChallengesController::class.java
    ) {

    override fun getLayoutId(): Int {
        return R.layout.fragment_add_private_challenge
    }

    override fun initViews() {
        setViews()
    }

    override fun processState(state: STChallengesState) {
        when (state) {
            is STChallengesState.Loading -> {
                requestDidStart()
            }
            is STChallengesState.Error -> {
                requestDidFinish()
                manageError(state.errorData?.statusCode)
                when {
                    state.errorData?.statusCode != STAPIConstants.STATUS_CODE_SESSION_EXPIRED &&
                            state.errorData?.statusCode != STAPIConstants.STATUS_CODE_INVALID_SESSION &&
                            state.errorData?.statusCode != STAPIConstants.STATUS_CODE_MALFORMED_REQUEST
                    -> {
                        showNoPrivateChallenge()
                    }
                    else -> {
                        showToast(state.errorData.message)
                    }
                }
            }
            is STChallengesState.FindChallenge -> {
                requestDidFinish()
                getPrivateChallengeDetails(state.challengeDetailsResponse)
            }
        }
    }

    private fun showNoPrivateChallenge() {
        val container = Intent(activityContext, STContainerActivity::class.java)
        container.putExtra(STConstants.FRAGMENT_NAME, getString(R.string.join_challenge))
        container.putExtra(
            STConstants.FRAGMENT_ID,
            STConstants.FRAGMENT_ID_PRIVATE_CHALLENGE_NOT_FOUND
        )
        startActivity(container)
    }

    private fun getPrivateChallengeDetails(privateChallengeDetailsResponse: STChallengeDetailsResponse?) {
        privateChallengeDetailsResponse?.data?.let { privateChallengeData ->
            val container = Intent(activityContext, STContainerActivity::class.java)
            container.putExtra(
                STConstants.FRAGMENT_NAME, ""/*getString(R.string.private_challenge_header)*/
            )
            container.putExtra(
                STConstants.FRAGMENT_ID,
                STConstants.FRAGMENT_ID_CHALLENGE_DETAILS
            )
            container.putExtra(
                STConstants.PRIVATE_CHALLENGE_DETAILS_DATA,
                privateChallengeDetailsResponse
            )
            container.putExtra(STConstants.CHALLENGES_TYPE, privateChallengeData.challengeType)
            container.putExtra(STConstants.CHALLENGES_ISPRIVATE, privateChallengeData.isPrivate)
            container.putExtra(
                STConstants.PRIVATE_CHALLENGE_JOIN_CODE,
                privateChallengeData.joinCode
            )
            container.putExtra(STConstants.CHALLENGES_DATA_ID, privateChallengeData.id)
            startActivity(container)
        }
    }

    override fun onViewModelReady() {
    }

    private fun setViews() {
        challengeCode.doOnTextChanged { text, start, count, after ->
            if (text?.length!! > 0) {
                findChallenge.setBackgroundResource(R.drawable.button_selector_enabled)
            } else {
                findChallenge.setBackgroundResource(R.drawable.button_selector_normal)
            }
        }
    }

    @OnClick(R.id.findChallenge)
    fun findChallenge() {
        if (STUtils.singleClick()) {
            if (STUtils.getValueFromView(challengeCode).isNullOrEmpty()) {
                showToast(getString(R.string.please_enter_challenge_code))
            } else {
                invokeIntent(STChallengesIntent.FindChallenge(STUtils.getValueFromView(challengeCode)!!))
            }
        }
    }

    @OnClick(R.id.createChallenge)
    fun createChallenge() {
        if (STUtils.singleClick()) {
            val container = Intent(activityContext, STContainerActivity::class.java)
            container.putExtra(
                STConstants.FRAGMENT_NAME,
                getString(R.string.create_private_challenge)
            )
            container.putExtra(
                STConstants.FRAGMENT_ID,
                STConstants.FRAGMENT_ID_CREATE_PRIVATE_CHALLENGE
            )
            startActivity(container)
        }
    }
}
