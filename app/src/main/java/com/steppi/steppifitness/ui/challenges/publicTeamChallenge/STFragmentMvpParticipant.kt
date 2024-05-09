package com.steppi.steppifitness.ui.challenges.publicTeamChallenge

import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.challenge.STMvpParticipantListAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.challenges.data.STMVPTeamList
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesController
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesIntent
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesState
import com.steppi.steppifitness.utils.setVerticalItemDecoration
import com.steppi.steppifitness.utils.setVerticalLayoutManager
import com.steppi.steppifitness.utils.showToast
import kotlinx.android.synthetic.main.fragment_mvp_participants.*
import java.util.ArrayList

class STFragmentMvpParticipant :
    STBaseViewModelFragment<STChallengesIntent, STChallengesState, STChallengesController>(
        STChallengesController::class.java
    ) {
    private var mvps: ArrayList<STMVPTeamList>? = null
    private var adapter: STMvpParticipantListAdapter? = null
    private var challengeId: String? = null
    private var isChallengeStarted: Boolean = false
    private var isChallengeCompleted: Boolean = false

    override fun getLayoutId(): Int {
        return R.layout.fragment_mvp_participants
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
            is STChallengesState.CheerChallengeUser -> {
            }
        }
    }

    override fun onViewModelReady() {
    }

    override fun initViews() {
        getIntentData()
        adapter = STMvpParticipantListAdapter(activityContext)
        mvps?.let { mvps ->
            mvpParticipantList.setVerticalLayoutManager()
            mvpParticipantList.setVerticalItemDecoration(
                activityContext.resources.getDimension(R.dimen.margin_small_xx).toInt(),
                0
            )
            adapter?.setData(mvps)
            adapter?.isChallengeStarted(isChallengeStarted)
            adapter?.isChallengeCompleted(isChallengeCompleted)
            mvpParticipantList.adapter = adapter
            adapter!!.setClickListener(object :
                STMvpParticipantListAdapter.OnItemClickListener {
                override fun onItemClick(mvpTeamList: STMVPTeamList?) {
                }

                override fun onCheerClick(mvpTeamList: STMVPTeamList, position: Int) {
                    invokeIntent(
                        STChallengesIntent.CheerChallengeUser(
                            challengeId!!,
                            mvpTeamList.id!!
                        )
                    )
                }
            })
        }
    }

    private fun getIntentData() {
        arguments?.getSerializable(STConstants.MVP_PARTICIPANT)?.let {
            mvps = it as ArrayList<STMVPTeamList>
        }
        arguments?.getString(STConstants.CHALLENGES_DATA_ID)?.let {
            challengeId = it
        }
        arguments?.getBoolean(STConstants.IS_CHALLENGE_STARTED, false)?.let {
            isChallengeStarted = it
        }
        arguments?.getBoolean(STConstants.IS_CHALLENGE_COMPLETED, false)?.let {
            isChallengeCompleted = it
        }
    }
}
