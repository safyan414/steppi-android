package com.steppi.steppifitness.ui.challenges.private_challenge

import android.content.Intent
import butterknife.OnClick
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.challenge.STSelectThemeAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.challenges.STChallengeDetailsResponse
import com.steppi.steppifitness.network.response.challenges.data.STCreatePrivateChallengeData
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesController
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesIntent
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesState
import com.steppi.steppifitness.ui.common.STContainerActivity
import com.steppi.steppifitness.utils.STUtils
import com.steppi.steppifitness.utils.setVerticalItemDecoration
import com.steppi.steppifitness.utils.setVerticalLayoutManager
import com.steppi.steppifitness.utils.showToast
import kotlinx.android.synthetic.main.fragment_select_theme.*
import okhttp3.MediaType
import okhttp3.RequestBody
import java.util.*

class STSelectThemeFragment :
    STBaseViewModelFragment<STChallengesIntent, STChallengesState, STChallengesController>(
        STChallengesController::class.java
    ) {
    private var themeAdapter: STSelectThemeAdapter? = null
    private var selectedTheme: String = STConstants.CHALLENGE_THEME_NONE
    private var selectedData: STCreatePrivateChallengeData? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_select_theme
    }

    override fun initViews() {
        initData()
        themeRecyclerView.setVerticalLayoutManager()
        themeRecyclerView.setVerticalItemDecoration(
            activityContext.resources.getDimension(R.dimen.margin_small_xx).toInt(),
            0
        )
        themeAdapter = STSelectThemeAdapter()
        themeRecyclerView.adapter = themeAdapter
        themeAdapter!!.setClickListener(object : STSelectThemeAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                selectedTheme = when (position) {
                    0 -> {
                        STConstants.CHALLENGE_THEME_NONE
                    }
                    else -> {
                        STConstants.CHALLENGE_THEME_1
                    }
                }
            }
        })
    }

    private fun initData() {
        selectedData =
            arguments?.getSerializable(STConstants.SELECTED_PRIVATE_CHALLENGE) as? STCreatePrivateChallengeData
    }

    override fun onViewModelReady() {
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
            }
            is STChallengesState.CreatePrivateChallenge -> {
                requestDidFinish()
                STConstants.IS_PRIVATE_CHALLENGE_CREATED = true
                getPrivateChallengeDetails(state.challengeDetailsResponse)
                activityContext.finish()
            }
        }
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

    @OnClick(R.id.themeNextButton)
    fun nextButtonClick() {
        if (STUtils.singleClick()) {
            selectedData?.let { selectedData ->
                selectedData.id?.let { templateId ->
                    val currentDate = RequestBody.create(
                        MediaType.parse("text/plain"),
                        STUtils.getFormattedDate(Calendar.getInstance(), "MM/dd/yyyy")
                    )
                    val theme = RequestBody.create(
                        MediaType.parse("text/plain"),
                        selectedTheme
                    )
                    invokeIntent(
                        STChallengesIntent.CreatePrivateChallenge(
                            templateId,
                            currentDate,
                            theme
                        )
                    )
                }
            }
        }
    }
}
