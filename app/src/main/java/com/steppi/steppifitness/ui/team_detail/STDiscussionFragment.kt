package com.steppi.steppifitness.ui.team_detail

import android.os.TransactionTooLargeException
import android.util.Log
import android.view.Gravity
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.OnClick
import butterknife.OnTextChanged
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.STDiscussionListAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.request.corporate.STAddEditTeamDiscussionMessageRequest
import com.steppi.steppifitness.network.response.challenges.STTeamDiscussionMessageListResponse
import com.steppi.steppifitness.network.response.challenges.data.STTeamDiscussionMessageListData
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.team_detail.mvi.STTeamDetailController
import com.steppi.steppifitness.ui.team_detail.mvi.STTeamDetailIntent
import com.steppi.steppifitness.ui.team_detail.mvi.STTeamDetailState
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.fragment_discussions.*

class STDiscussionFragment :
    STBaseViewModelFragment<STTeamDetailIntent, STTeamDetailState, STTeamDetailController>(
        STTeamDetailController::class.java
    ) {
    private var myDiscussionListAdapter: STDiscussionListAdapter? = null
    private var discussionMessageList: ArrayList<STTeamDiscussionMessageListData>? = null
    private var challengesId: String? = null
    private var teamId: String? = null
    private var isLoading = false
    private var isLastPage = false
    private var TOTAL = 0
    private var offset = 0
    private var isRefreshList = true
    private var editDiscussionData: STTeamDiscussionMessageListData? = null
    private var editIndex: Int = -1

    override fun getLayoutId(): Int {
        return R.layout.fragment_discussions
    }

    override fun initViews() {
        getExtras()
        initMyChallengesList()
    }

    override fun processState(state: STTeamDetailState) {
        try {
            when (state) {
                is STTeamDetailState.Loading -> {
                    requestDidStart()
                }
                is STTeamDetailState.Error -> {
                    requestDidFinish()
                    showToast(state.errorData?.message)
                    manageError(state.errorData?.statusCode)
                }
                is STTeamDetailState.GetTeamDiscussionMessages -> {
                    requestDidFinish()
                    TOTAL = state.teamDiscussionMessagesResponse?.total!!
                    setMyChallengeList(state.teamDiscussionMessagesResponse)
                }
                is STTeamDetailState.AddTeamDiscussionMessage -> {
                    requestDidFinish()
                    noData.gone()
                    state.addTeamDiscussionMessageResponse?.let {
                        comment_box.setText("")
                        TOTAL++
                        discussionMessageList?.add(0, it.data!!)
                        myDiscussionListAdapter?.notifyDataSetChanged()
                    }
                }
                is STTeamDetailState.EditTeamDiscussionMessage -> {
                    requestDidFinish()
                    state.editTeamDiscussionMessageResponse?.let {
                        comment_box.setText("")
                        TOTAL++
                        discussionMessageList?.set(editIndex, it.data!!)
                        myDiscussionListAdapter?.notifyItemChanged(editIndex)
                        editIndex = -1
                        STUtils.closeKeyboard(activityContext)
                    }
                }
            }
        }catch (e: TransactionTooLargeException)
        {
            Log.e("esdfdfs","sdfsdf");
        }

    }

    override fun onViewModelReady() {
        challengesId?.let { cId ->
            teamId?.let { tId ->
                invokeIntent(STTeamDetailIntent.GetTeamDiscussionMessages(cId, tId, 0))
            }
        }
    }

    private fun getExtras() {

        try {

            teamId = arguments?.getString(STConstants.TEAM_ID)
            challengesId = arguments?.getString(STConstants.CHALLENGE_ID)
        }
        catch (e:TransactionTooLargeException)
        {
            Log.e("sdfsdf","sdfsdf");
        }

    }

    private fun initMyChallengesList() {
        val layoutManager =
            LinearLayoutManager(activityContext, LinearLayoutManager.VERTICAL, false)
        list_discussion.layoutManager = layoutManager
        list_discussion.addItemDecoration(
            STVerticalSpaceItemDecoration(
                STUtils.getDimen(
                    activityContext,
                    R.dimen.margin_normal_xx
                )
            )
        )
        myDiscussionListAdapter = STDiscussionListAdapter(activityContext)
        list_discussion.adapter = myDiscussionListAdapter
        myDiscussionListAdapter?.setClickListener(object :
            STDiscussionListAdapter.OnItemClickListener {
            override fun onItemClick() {
            }

            override fun onEditMessage(
                position: Int,
                discussionData: STTeamDiscussionMessageListData
            ) {
                comment_box.setText(discussionData.message)
                comment_box.requestFocus()
                comment_box.text?.length?.let { comment_box.setSelection(it) }
                STUtils.showSoftKeyboard(activityContext)
                editDiscussionData = discussionData
                editIndex = position
            }
        })
        addScrollListener(layoutManager)
    }


    private fun addScrollListener(layoutManager: LinearLayoutManager) {
        list_discussion.addOnScrollListener(object : EndlessScrollListener(
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

    private fun setMyChallengeList(teamDiscussionMessagesResponse: STTeamDiscussionMessageListResponse?) {
        teamDiscussionMessagesResponse?.data?.let { discussionListData ->
            if (discussionListData.isNullOrEmpty()) {
                noData.visible()
            } else {
                noData.gone()
            }
            if (offset > 0) {
                myDiscussionListAdapter!!.removeLoadingFooter()
                isLoading = false
            }
            myDiscussionListAdapter?.setDiscussionListData(
                isRefreshList,
                discussionListData as ArrayList<STTeamDiscussionMessageListData>
            )
            if (TOTAL > myDiscussionListAdapter!!.getListSize()) myDiscussionListAdapter!!.addLoadingFooter()
            else isLastPage = true
            offset = myDiscussionListAdapter!!.getListSize() - 1
            discussionMessageList =
                myDiscussionListAdapter!!.getList() as ArrayList<STTeamDiscussionMessageListData>?
        } ?: kotlin.run {
            noData.visible()
        }
        isRefreshList = false
    }

    @OnTextChanged(value = [R.id.comment_box], callback = OnTextChanged.Callback.TEXT_CHANGED)
    fun onTextChanged(text: CharSequence) {
        //Toast.makeText(this, "Before text changed: " + text, LENGTH_SHORT).show();
        validateAllData()
    }

    private fun validateAllData() {
        if (STUtils.getValueFromView(comment_box).isNullOrEmpty()) {
            comment_button.background =
                STUtils.getDrawable(activityContext, R.drawable.button_bg_normal)
            comment_button.setTextColor(STUtils.getColor(activityContext, R.color.black))
            comment_box.gravity = Gravity.CENTER
        } else {
            comment_button.background =
                STUtils.getDrawable(activityContext, R.drawable.button_bg_enabled)
            comment_button.setTextColor(STUtils.getColor(activityContext, R.color.theme_color))
            comment_box.gravity = Gravity.LEFT or Gravity.TOP
        }
    }

    @OnClick(R.id.cancel)
    fun cancel() {
        editIndex = -1
        comment_box.setText("")
    }

    @OnClick(R.id.comment_button)
    fun doComment() {
        if (!STUtils.getValueFromView(comment_box).isNullOrEmpty()) {
            challengesId?.let { cId ->
                teamId?.let { tId ->
                    val addDiscussionMessage = STAddEditTeamDiscussionMessageRequest()
                    addDiscussionMessage.message = STUtils.getValueFromView(comment_box) ?: ""
                    if (editIndex < 0) {
                        invokeIntent(
                            STTeamDetailIntent.AddTeamDiscussionMessage(
                                cId, tId, addDiscussionMessage
                            )
                        )
                    } else {
                        discussionMessageList?.get(editIndex)?.id?.let { dId ->
                            invokeIntent(
                                STTeamDetailIntent.EditTeamDiscussionMessage(
                                    cId, tId, dId, addDiscussionMessage
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}
