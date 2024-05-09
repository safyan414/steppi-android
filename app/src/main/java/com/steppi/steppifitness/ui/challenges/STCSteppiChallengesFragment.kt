package com.steppi.steppifitness.ui.challenges

import android.app.Dialog
import android.content.Intent
import android.text.SpannableStringBuilder
import android.util.Log
import androidx.core.text.bold
import androidx.core.text.color
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.OnClick
import com.steppi.steppifitness.BuildConfig
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.STSteppiChallengesListAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.STAPIConstants
import com.steppi.steppifitness.network.STAPIServices
import com.steppi.steppifitness.network.response.challenges.STChallengeDetailsResponse
import com.steppi.steppifitness.network.response.challenges.STSteppiChallengeListResponse
import com.steppi.steppifitness.network.response.challenges.data.STChallengesListData
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesController
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesIntent
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesState
import com.steppi.steppifitness.ui.common.STContainerActivity
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.challenge_type_layout.*
import kotlinx.android.synthetic.main.fragment_steppi_chalenges.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class STCSteppiChallengesFragment :
    STBaseViewModelFragment<STChallengesIntent, STChallengesState, STChallengesController>(
        STChallengesController::class.java
    ), SwipeRefreshLayout.OnRefreshListener {
    private var steppiChallengeListAdapter: STSteppiChallengesListAdapter? = null
    private var isLoading = false
    private var isLastPage = false
    private var listTotal = 0
    private var offset = 0
    private var isRefreshList = true

    private var isAll: Boolean? = true
    private var isDfc: Boolean? = false
    private var isPc1: Boolean? = false
    private var isPc2: Boolean? = false
    private var isPc3: Boolean? = false
    private var isToughMudder: Boolean? = false

    private var joinChallengeData: STChallengesListData? = null
    private var joinChallengeItemPosition: Int? = null
    private var dfcDialog: Dialog? = null
    private var publicJoinDialog: Dialog? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_steppi_chalenges
    }

    override fun initViews() {
        allChallengeCell.isSelected = true
        if (STConstants.IS_DFC_CHALLENGE_ENABLED) {
            STConstants.IS_DFC_CHALLENGE_ENABLED = false
            allChallengeCell.isSelected = false
            dfcCell.isSelected = true
            dfcCell.visible()
            isAll = false
            isDfc = true
        }
        initSteppiChallengesList()
    }

    override fun onViewModelReady() {
        shimmer_view_container.visible()
        shimmer_view_container.startShimmerAnimation()
        when {
            isAll!! -> {
                invokeIntent(
                    STChallengesIntent.GetSteppiChallengesList(
                        STConstants.API_END_POINT_ALL,
                        offset
                    )
                )
            }
            isDfc!! -> {
                invokeIntent(
                    STChallengesIntent.GetSteppiChallengesList(
                        STConstants.API_END_POINT_DFC,
                        offset
                    )
                )
            }
            isPc1!! -> invokeIntent(
                STChallengesIntent.GetSteppiChallengesList(STConstants.API_END_POINT_PC_1, offset)
            )
            isPc2!! -> invokeIntent(
                STChallengesIntent.GetSteppiChallengesList(STConstants.API_END_POINT_PC_2, offset)
            )
            isToughMudder!! -> invokeIntent(STChallengesIntent.ToughMudderChallengeList)
            else -> invokeIntent(
                STChallengesIntent.GetSteppiChallengesList(
                    STConstants.API_END_POINT_PC_3,
                    offset
                )
            )
        }
    }

    override fun onRefresh() {
        clearAdapter()
        noData.gone()
        when {
            isAll!! -> invokeIntent(
                STChallengesIntent.GetSteppiChallengesList(STConstants.API_END_POINT_ALL, offset)
            )
            isDfc!! -> invokeIntent(
                STChallengesIntent.GetSteppiChallengesList(
                    STConstants.API_END_POINT_DFC,
                    offset
                )
            )
            isPc1!! -> invokeIntent(
                STChallengesIntent.GetSteppiChallengesList(STConstants.API_END_POINT_PC_1, offset)
            )
            isPc2!! -> invokeIntent(
                STChallengesIntent.GetSteppiChallengesList(STConstants.API_END_POINT_PC_2, offset)
            )
            isToughMudder!! -> invokeIntent(STChallengesIntent.ToughMudderChallengeList)
            else -> invokeIntent(
                STChallengesIntent.GetSteppiChallengesList(
                    STConstants.API_END_POINT_PC_3,
                    offset
                )
            )
        }
    }

    override fun processState(state: STChallengesState) {
        when (state) {
//            is STChallengesState.Loading -> requestDidStart()
            is STChallengesState.Error -> {
//                requestDidFinish()
                showToast(state.errorData?.message)
                manageError(state.errorData?.statusCode)
            }
            is STChallengesState.GetSteppiChallengeList -> {
//                requestDidFinish()
                challengeTabLayout.visible()
                shimmer_view_container.stopShimmerAnimation()
                shimmer_view_container.gone()
                listTotal = state.steppiChallengeListResponse?.total!!
                state.steppiChallengeListResponse.isToughMudderAvailable?.let { isToughMudderAvailable ->
                    if (isToughMudderAvailable) {
                        toughMudderChallengeCell.visible()
                        state.steppiChallengeListResponse.toughMudderTab?.let { toughMudderTabData ->
                            toughMudderTabData.logo?.let { logo ->
                                toughMudderChallengeImageDefault.gone()
                                toughMudderChallengeImage.visible()
                                toughMudderChallenge.visible()
                                toughMudderChallengeImage.load(
                                    activityContext,
                                    logo
                                )
                                toughMudderTabData.title?.let {
                                    if (it.isNotEmpty()) {
                                        if (it != "") {
                                            toughMudderChallenge.text = it
                                        } else {
                                            imageOnlySetup(logo)
                                        }
                                    } else {
                                        imageOnlySetup(logo)
                                    }
                                } ?: kotlin.run {
                                    imageOnlySetup(logo)
                                }
                            } ?: kotlin.run {
                                toughMudderChallengeImageDefault.visible()
                                toughMudderChallengeImage.gone()
                                toughMudderChallenge.gone()
                            }
                        }
                    } else {
                        toughMudderChallengeCell.gone()
                    }
                }
                state.steppiChallengeListResponse.isDubaiFitnessAvailable?.let { isDubaiFitnessAvailable ->
                    if (isDubaiFitnessAvailable) {
                        dfcCell.visible()
                        state.steppiChallengeListResponse.dubaiFitnessTab?.let { dubaiFitnessTabData ->
                            dfcImage.load(
                                activityContext,
                                dubaiFitnessTabData.logo
                            )
                            dubaiFitnessTabData.title?.let {
                                if (it.isNotEmpty()) {
                                    if (it != "") {
                                        dfcChallenge.text = it
                                    }
                                }
                            }
                        }
                    } else {
                        dfcCell.gone()
                    }
                }
                setSteppiChallengeList(state.steppiChallengeListResponse)
            }
            is STChallengesState.JoinLeaveChallenge -> {
                requestDidFinish()
                STConstants.UPDATE_MY_CHALLENGE_LIST = true
                state.challengeDetailsResponse?.data?.let {
                    if (it.type == STConstants.DUBAI_FITNESS_CHALLENGE) {
                        dfcDialog?.let { dfcDialog_ ->
                            if (dfcDialog_.isShowing)
                                dfcDialog_.dismiss()
                        }
                    }
                }
                joinChallengeData?.let {
                    it.joined = true
                    it.participants = (it.participants!!.toInt() + 1).toString()
                    steppiChallengeListAdapter!!.notifyItemChanged(joinChallengeItemPosition!!)
                }
                //steppiChallengeListAdapter!!.removeItem(joinChallengeItemPosition!!)
            }
            else -> {
            }
        }
    }

    private fun imageOnlySetup(logo: String) {
        toughMudderChallengeImageDefault.visible()
        toughMudderChallengeImage.gone()
        toughMudderChallenge.gone()
        toughMudderChallengeImageDefault.load(
            activityContext,
            logo
        )
    }

    override fun onResume() {
        super.onResume()
        if (STConstants.UPDATE_STEPPI_CHALLENGE_LIST) {
            isRefreshList = true
            offset = 0
            isLastPage = false
            STConstants.UPDATE_STEPPI_CHALLENGE_LIST = false
            when {
                isAll!! -> invokeIntent(
                    STChallengesIntent.GetSteppiChallengesList(
                        STConstants.API_END_POINT_ALL,
                        offset
                    )
                )
                isDfc!! -> invokeIntent(
                    STChallengesIntent.GetSteppiChallengesList(
                        STConstants.API_END_POINT_DFC,
                        offset
                    )
                )
                isPc1!! -> invokeIntent(
                    STChallengesIntent.GetSteppiChallengesList(
                        STConstants.API_END_POINT_PC_1,
                        offset
                    )
                )
                isPc2!! -> invokeIntent(
                    STChallengesIntent.GetSteppiChallengesList(
                        STConstants.API_END_POINT_PC_2,
                        offset
                    )
                )
                else -> invokeIntent(
                    STChallengesIntent.GetSteppiChallengesList(
                        STConstants.API_END_POINT_PC_3,
                        offset
                    )
                )
            }
        }
    }

    private fun initSteppiChallengesList() {
        challengeSteppiListPullRefresh.setOnRefreshListener(this)
        challengeSteppiListPullRefresh.setColorSchemeResources(
            R.color.button_bg_enabled_color,
            R.color.button_bg_enabled_color,
            R.color.button_bg_enabled_color,
            R.color.button_bg_enabled_color
        )
        val layoutManager =
            LinearLayoutManager(activityContext, LinearLayoutManager.VERTICAL, false)

        list_steppi_challenges.layoutManager = layoutManager
        steppiChallengeListAdapter = STSteppiChallengesListAdapter(activityContext)
        list_steppi_challenges.adapter = steppiChallengeListAdapter
        steppiChallengeListAdapter?.setClickListener(object :
            STSteppiChallengesListAdapter.OnItemClickListener {
            override fun onItemClick(challengesData: STChallengesListData?) {
                val container = Intent(activityContext, STContainerActivity::class.java)
                container.putExtra(STConstants.FRAGMENT_NAME, "")
                container.putExtra(
                    STConstants.FRAGMENT_ID,
                    STConstants.FRAGMENT_ID_CHALLENGE_DETAILS
                )
                container.putExtra(STConstants.CHALLENGES_DATA, challengesData)
                container.putExtra(STConstants.CHALLENGES_TYPE, challengesData?.challengeType)
                container.putExtra(STConstants.CHALLENGES_ISPRIVATE, challengesData?.isPrivate)
                challengesData?.joinCode?.let {
                    container.putExtra(STConstants.PRIVATE_CHALLENGE_JOIN_CODE, it)
                }
                container.putExtra(STConstants.CHALLENGES_DATA_ID, challengesData?.id)
                startActivity(container)
            }

            override fun onJoinClick(challengesData: STChallengesListData?, position: Int) {
                joinChallengeData = challengesData
                joinChallengeItemPosition = position
                if (joinChallengeData?.status
                    == "Completed"
                ) joinAnotherChallengeDialog(joinChallengeData)
                else {
                    if (joinChallengeData?.type == STConstants.DUBAI_FITNESS_CHALLENGE) joinDFCChallengeDialog()
                    else {

                        if (joinChallengeData?.publicJoinCode!!) joinPublicWithCodeChallengeDialog()
                        else joinChallengeDialog()

                    }
                }
            }

            override fun onDeleteClick(challengesData: STChallengesListData?, position: Int) {
            }
        })
        addScrollListener(layoutManager)
    }

    /*



    todo: join public challenge (target,goal)  */

    private fun joinPublicWithCodeChallengeDialog() {
        publicJoinDialog = showJoinDFCChallengeDialog {
            clickToAgree()
            dialogOK {
                if (isPleaseAgree()) {
                    requestDidStart()
                    joinPublicChallenge(getChallengeJoinCode()!!)
//                    dismissDialog()
                }
            }
        }
        publicJoinDialog?.setSize(activityContext)
        publicJoinDialog?.show()
    }

    private fun joinPublicChallenge(challengeJoinCode: String) {
        //Public Chanllenge with code
        Log.d("TAG", "joinPublicChallenge: ")
        initRetrofit(challengeJoinCode)
    }

    private fun initRetrofit(challengeJoinCode: String) {
        if (STUtils.isInternetOn(activityContext)) {         //network On

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL.plus(STAPIConstants.API_VERSION))
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            CoroutineScope(Dispatchers.IO).launch {
                checkJoinCode(retrofit, challengeJoinCode)
            }

        } else showToast(resources.getString(R.string.no_internet))  //no network
    }


    private suspend fun checkJoinCode(retrofit: Retrofit, challengeJoinCode: String) {

        joinChallengeData?.id?.let { id ->
            STPreference.getAccessToken(activityContext)?.let {
                val stApiServicesClass = retrofit.create(STAPIServices::class.java)
                val call: Call<STChallengeDetailsResponse> =
                    stApiServicesClass.joinPublicChallenge(it, id, challengeJoinCode)

                withContext(Dispatchers.IO) {
                    joinPublicChallengeCodeApi(call)
                }
            }
        }
    }

    private fun joinPublicChallengeCodeApi(call: Call<STChallengeDetailsResponse>) {

        call.enqueue(object : Callback<STChallengeDetailsResponse> {

            override fun onResponse(
                call: Call<STChallengeDetailsResponse>,
                response: Response<STChallengeDetailsResponse>
            ) {

                if (response.isSuccessful) {
                    dismissPublicChallengeDialog()

//                        response.body()?.data.let {
//                            challengeDetails = it
//                        }
                    performUiUpdate(response)
                } else {
                    CoroutineScope(Dispatchers.Main).launch {
                        requestDidFinish()
                        dismissPublicChallengeDialog()
                        showToast("Invalid Code" + response.message())
                    }
                }
            }

            override fun onFailure(call: Call<STChallengeDetailsResponse>, t: Throwable) {
                CoroutineScope(Dispatchers.Main).launch {
                    requestDidFinish()
                    dismissPublicChallengeDialog()
                    showToast("Joining Failed" + t.message)
                }
            }
        })
    }


    private fun performUiUpdate(response: Response<STChallengeDetailsResponse>) {

        response.body()?.let {
            val challengeResponse: STChallengeDetailsResponse = it

            requestDidFinish()
            STConstants.UPDATE_MY_CHALLENGE_LIST = true
            challengeResponse?.data?.let {
                if (it.type == STConstants.DUBAI_FITNESS_CHALLENGE) {
                    dfcDialog?.let { dfcDialog_ ->
                        if (dfcDialog_.isShowing)
                            dfcDialog_.dismiss()
                    }
                }
            }
            joinChallengeData?.let {
                it.joined = true
                it.participants = (it.participants!!.toInt() + 1).toString()
                steppiChallengeListAdapter!!.notifyItemChanged(joinChallengeItemPosition!!)
            }
        }
    }

    private fun dismissPublicChallengeDialog() {
        publicJoinDialog?.let {
            if (it.isShowing)
                it.dismiss()
        }
    }


    //todo

    private fun addScrollListener(layoutManager: LinearLayoutManager) {
        list_steppi_challenges.addOnScrollListener(object : EndlessScrollListener(
            layoutManager, STConstants.VISIBLE_THRESHOLD
        ) {
            override fun loadMoreItems() {
                isLoading = true
                onViewModelReady()
            }

            override fun isLastPage() = isLastPage

            override fun isLoading() = isLoading

        })
    }

    private fun setSteppiChallengeList(steppiChallengeListResponse: STSteppiChallengeListResponse?) {
        challengeSteppiListPullRefresh?.isRefreshing = false
        steppiChallengeListResponse?.data?.let { challengeListData ->
            if (challengeListData.isNullOrEmpty()) {
                noData.visible()
            } else {
                noData.gone()
            }
            if (offset > 0) {
                steppiChallengeListAdapter!!.removeLoadingFooter()
                isLoading = false
            }

            steppiChallengeListAdapter?.setChallengeListData(
                isRefreshList,
                challengeListData as ArrayList<STChallengesListData>,
                STConstants.STEPPI_CHALLENGE_LIST
            )

            if (listTotal > steppiChallengeListAdapter!!.getListSize()) steppiChallengeListAdapter!!.addLoadingFooter()
            else isLastPage = true

            offset = steppiChallengeListAdapter!!.getListSize() - 1
        } ?: kotlin.run {
            noData.visible()
        }
        isRefreshList = false
    }

    private fun joinDFCChallengeDialog() {
        dfcDialog = showJoinDFCChallengeDialog {
            clickToAgree()
            dialogOK {
                if (isPleaseAgree()) {
                    joinLeaveChallenge(getChallengeJoinCode()!!)
                }
            }
        }
        dfcDialog?.setSize(activityContext)
        dfcDialog?.show()
    }

    private fun joinChallengeDialog() {
        val successDialog: Dialog = showJoinChallengeDialog {
            clickToAgree {
            }
            onTCClick()
            clickCheckboxLayout {}
            dialogOK {
                if (isPleaseAgree()) {
                    joinLeaveChallenge()
                    dismissDialog()
                }
            }
        }
        successDialog.setSize(activityContext)
        successDialog.show()
    }

    private fun joinAnotherChallengeDialog(challengesData: STChallengesListData?) {
        val successDialog: Dialog = showJoinAnotherChallengeDialog {
            dialogJoinChallenge {
                dismissDialog()
            }

            val challengeTitleString = SpannableStringBuilder()
            if (!challengesData?.joined!!) {
                challengeTitleString.append(resources.getString(R.string.reach_the_goal_not_joined_1))
            } else {
                challengeTitleString.append(resources.getString(R.string.reach_the_goal_joined_1))
            }
            challengeTitleString.color(
                STUtils.getColor(
                    activityContext,
                    R.color.button_bg_enabled_color
                )
            ) {
                append(
                    resources.getString(
                        R.string.reach_the_goal_not_joined_2,
                        STUtils.formatNumber(challengesData.targetSteps?.toInt())
                    )
                )
            }
                .append(resources.getString(R.string.reach_the_goal_not_joined_3))


            val challengeCompletedDescriptionString = SpannableStringBuilder()
                .bold { append(resources.getString(R.string.challenge_complete_description_1)) }
                //.append(resources.getString(R.string.challenge_complete_description_2))
                .color(
                    STUtils.getColor(
                        activityContext,
                        R.color.button_bg_enabled_color
                    )
                ) { append(STPreference.getName(activityContext)!! + "!") }
                .append(resources.getString(R.string.challenge_complete_description_3))
                .bold {
                    append(
                        resources.getString(
                            R.string.challenge_complete_description_4,
                            challengesData.challengeType!!
                        )
                    )
                }
                .append(resources.getString(R.string.challenge_complete_description_5))
            setChallengeStatus(challengesData.joined!!)
            setTitle(challengeTitleString)
            setDescription(challengeCompletedDescriptionString)
        }
        successDialog.setSize(activityContext)
        successDialog.show()
    }

    private fun joinLeaveChallenge(challengeJoinCode: String? = "") {
        joinChallengeData?.let {
            when (it.type) {
                STConstants.TOUGH_MUDDER_CHALLENGE -> {
                    it.id?.let { id ->
                        invokeIntent(
                            STChallengesIntent.JoinToughMudderChallenge(
                                if (it.joined!!) STConstants.CHALLENGE_OPERATION_LEAVE
                                else STConstants.CHALLENGE_OPERATION_JOIN, id
                            )
                        )
                    }
                }
                STConstants.DUBAI_FITNESS_CHALLENGE -> {
                    it.id?.let { id ->
                        if (challengeJoinCode == "") {
                            showToast(getString(R.string.dfc_challenge_code_validation))
                            return
                        }
                        invokeIntent(
                            STChallengesIntent.JoinLeaveDFCChallenge(
                                if (it.joined!!) STConstants.CHALLENGE_OPERATION_LEAVE
                                else STConstants.CHALLENGE_OPERATION_JOIN, id, challengeJoinCode!!
                            )
                        )
                    }
                }
                else -> {
                    it.id?.let { id ->
                        invokeIntent(
                            STChallengesIntent.JoinLeaveChallenge(
                                if (it.joined!!) STConstants.CHALLENGE_OPERATION_LEAVE
                                else STConstants.CHALLENGE_OPERATION_JOIN,
                                id
                            )
                        )
                    }
                }
            }
        }
    }

    @OnClick(R.id.allChallengeCell)
    fun onAllChallengeClick() {
        isDfc = false
        isPc1 = false
        isPc2 = false
        isPc3 = false
        isToughMudder = false
        allChallengeCell.isSelected = true
        dfcCell.isSelected = false
        pc1Cell.isSelected = false
        pc2Cell.isSelected = false
        pc3Cell.isSelected = false
        toughMudderChallengeCell.isSelected = false
        if (!isAll!!) {
            isAll = true
            clearAdapter()
            shimmer_view_container.startShimmerAnimation()
            shimmer_view_container.visible()
            invokeIntent(
                STChallengesIntent.GetSteppiChallengesList(
                    STConstants.API_END_POINT_ALL,
                    offset
                )
            )
        }
    }

    @OnClick(R.id.dfcCell)
    fun onDFCCellClick() {
        isAll = false
        isPc1 = false
        isPc2 = false
        isPc3 = false
        isToughMudder = false
        allChallengeCell.isSelected = false
        dfcCell.isSelected = true
        pc1Cell.isSelected = false
        pc2Cell.isSelected = false
        pc3Cell.isSelected = false
        toughMudderChallengeCell.isSelected = false
        if (!isDfc!!) {
            isDfc = true
            clearAdapter()
            shimmer_view_container.startShimmerAnimation()
            shimmer_view_container.visible()
            invokeIntent(
                STChallengesIntent.GetSteppiChallengesList(
                    STConstants.API_END_POINT_DFC,
                    offset
                )
            )
        }
    }

    @OnClick(R.id.toughMudderChallengeCell)
    fun toughMudderChallengeClick() {
        isAll = false
        isDfc = false
        isPc1 = false
        isPc2 = false
        isPc3 = false
        toughMudderChallengeCell.isSelected = true
        allChallengeCell.isSelected = false
        dfcCell.isSelected = false
        pc1Cell.isSelected = false
        pc2Cell.isSelected = false
        pc3Cell.isSelected = false
        if (!isToughMudder!!) {
            isToughMudder = true
            clearAdapter()
            shimmer_view_container.startShimmerAnimation()
            shimmer_view_container.visible()
            invokeIntent(STChallengesIntent.ToughMudderChallengeList)
        }
    }

    @OnClick(R.id.pc1Cell)
    fun onPc1Click() {
        isAll = false
        isDfc = false
        isPc2 = false
        isPc3 = false
        toughMudderChallengeCell.isSelected = false
        dfcCell.isSelected = false
        isToughMudder = false
        pc1Cell.isSelected = true
        pc2Cell.isSelected = false
        pc3Cell.isSelected = false
        allChallengeCell.isSelected = false
        if (!isPc1!!) {
            isPc1 = true
            clearAdapter()
            shimmer_view_container.startShimmerAnimation()
            shimmer_view_container.visible()
            invokeIntent(
                STChallengesIntent.GetSteppiChallengesList(
                    STConstants.API_END_POINT_PC_1,
                    offset
                )
            )
        }
    }

    @OnClick(R.id.pc2Cell)
    fun onPc2Click() {
        isAll = false
        isDfc = false
        isPc1 = false
        isPc3 = false
        isToughMudder = false
        pc2Cell.isSelected = true
        pc1Cell.isSelected = false
        allChallengeCell.isSelected = false
        pc3Cell.isSelected = false
        toughMudderChallengeCell.isSelected = false
        dfcCell.isSelected = false
        if (!isPc2!!) {
            isPc2 = true
            clearAdapter()
            shimmer_view_container.startShimmerAnimation()
            shimmer_view_container.visible()
            invokeIntent(
                STChallengesIntent.GetSteppiChallengesList(
                    STConstants.API_END_POINT_PC_2,
                    offset
                )
            )
        }
    }

    @OnClick(R.id.pc3Cell)
    fun onPc3Click() {
        isAll = false
        isDfc = false
        isPc2 = false
        isPc1 = false
        isToughMudder = false
        pc3Cell.isSelected = true
        dfcCell.isSelected = false
        pc1Cell.isSelected = false
        allChallengeCell.isSelected = false
        pc2Cell.isSelected = false
        toughMudderChallengeCell.isSelected = false
        if (!isPc3!!) {
            isPc3 = true
            clearAdapter()
            shimmer_view_container.startShimmerAnimation()
            shimmer_view_container.visible()
            invokeIntent(
                STChallengesIntent.GetSteppiChallengesList(
                    STConstants.API_END_POINT_PC_3,
                    offset
                )
            )
        }
    }

    private fun clearAdapter() {
        steppiChallengeListAdapter?.clear()
        isLoading = false
        isLastPage = false
        listTotal = 0
        offset = 0
    }
}
