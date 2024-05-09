package com.steppi.steppifitness.ui.team_detail

import android.content.Context
import android.os.TransactionTooLargeException
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.OnTextChanged
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.STMemberListAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.challenges.STChallengeTeamMemberResponse
import com.steppi.steppifitness.network.response.challenges.data.STTeamMember
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.team_detail.mvi.STTeamDetailController
import com.steppi.steppifitness.ui.team_detail.mvi.STTeamDetailIntent
import com.steppi.steppifitness.ui.team_detail.mvi.STTeamDetailState
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.fragment_memebers.*
import java.util.*
import kotlin.concurrent.schedule


class STMembersFragment :
    STBaseViewModelFragment<STTeamDetailIntent, STTeamDetailState, STTeamDetailController>(
            STTeamDetailController::class.java
    ) {
    private var myMemberListAdapter: STMemberListAdapter? = null
    private var isChallengeStarted: Boolean? = null
    private var teamMembers: ArrayList<STTeamMember>? = null
    private var searchTeamMembers: ArrayList<STTeamMember>? = arrayListOf()
    private var teamMembersData: ArrayList<STTeamMember>? = arrayListOf()
    private var isChallengeCompleted: Boolean? = null
    private var challengesId: String? = null
    var isLastPage = false
    var isLoading = true
    var offset = 0
    var teamId = ""
    var lastVisiblePostion = 0
    var isSearchedData = false
    var timer: Timer = Timer("SettingUp1", false)
    var timer2 = Timer("SettingUp2", false)

    override fun onViewModelReady() {

        challengesId?.let {
            invokeIntent(STTeamDetailIntent.GetChallengeTeamMembers(this.teamId, it, offset))
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_memebers
    }

    override fun processState(state: STTeamDetailState) {
        try{
//code that may throw exception
            when (state) {
                is STTeamDetailState.Loading -> requestDidStart()
                is STTeamDetailState.Error -> {
                    requestDidFinish()
                    showToast(state.errorData?.message)
                    manageError(state.errorData?.statusCode)
                }
                is STTeamDetailState.SearchMembersState -> {
                    myMemberListAdapter?.setMembersList(state.teamMembersList as ArrayList<STTeamMember>)
                }
                is STTeamDetailState.CheerChallengeUser -> {
                }

                is STTeamDetailState.GetChallengeTeamMember -> {
                    requestDidFinish()
                    isLoading = false

                    state.challengeDetailsResponse?.let {
                        isSearchedData = false
                        initMyChallengesList(it.data)
                    }
                }

                is STTeamDetailState.GetChallengeSearcedTeamMember -> {
                    requestDidFinish()
                    isLoading = false
                    isSearchedData = true
                    //showKeyboard(search_members)
                    initMyChallengesList(state.challengeDetailsResponse)
                }

            }
        }catch (e: TransactionTooLargeException){
//code that handles exception
            Log.e("excepti", "asdasd")
        }

    }

    override fun initViews() {
        getExtras()
//        initMyChallengesList()
    }

    private fun initMyChallengesList(challengeDetailsResponse: STChallengeTeamMemberResponse?) {
        list_members.layoutManager = LinearLayoutManager(activityContext)
        challengeDetailsResponse?.let {
                userImageManRunning.visible()
                userImage.gone()
                if (isSearchedData == true) {
                    it.data?.let { members ->

                        if (members.isEmpty() == true || members.size < 10 || isSearchedData == true) {
                            isLastPage = true
                        }

                        Log.i("data for array", teamMembersData.toString())
                        teamMembersData?.clear()
                        for (item in members) {
                            teamMembersData?.add(item)
                        }

                        myMemberListAdapter =
                                STMemberListAdapter(
                                        activityContext,
                                        (teamMembersData)!!,
                                        isChallengeStarted,
                                        isChallengeCompleted)
                        myMemberListAdapter?.isSearchedData = true
                        myMemberListAdapter?.notifyDataSetChanged()

                    }

                }
                else
                {
                    it.teamMembers.let { members ->
                        stickView.visibility = View.VISIBLE

                        isSearchedData = false
                        it.myStats?.let {
                            userName.text = it.user?.name
                            steps.text = it.totalSteps
                            position.text = it.rank.toString()
                            if (members?.isNotEmpty() == true) {
                                if (members.first().type == "step") {
                                    steps.text = it.totalSteps
                                    stepsLabelMyUser.text = "Steps"

                                } else if (members.first().type == "active_minutes") {
                                    steps.text = it.totalActiveMinutes
                                    stepsLabelMyUser.text = "Mins"

                                } else if (members.first().type == "distance_covered") {
                                    steps.text = it.totalDistance
                                    stepsLabelMyUser.text = "KM"

                                } else if (members.first().type == "calories_burned") {
                                    steps.text = it.totalCalories
                                    stepsLabelMyUser.text = "Cals"
                                }
                            }


                            when (it.rank) {
                                0 -> {
                                    rankingImage.setImageResource(R.drawable.win_label_one)
                                }
                                1 -> {
                                    rankingImage.setImageResource(R.drawable.win_label_two)
                                }
                                2 -> {
                                    rankingImage.setImageResource(R.drawable.win_label_three)
                                }
                                else -> {
                                    viewPositionNormal(it.rank)
                                }

                            }

                            it.user?.picture?.let {
                                userImageManRunning.gone()
                                userImage.visible()

                                userImage.load(activityContext, it) {
                                    userImageManRunning.visible()
                                    userImage.gone()
                                }
                            }
                        }

                        if (members?.isNotEmpty() == true) {

                            if (members.isEmpty() == true || members.size < 10 || isSearchedData == true) {
                                isLastPage = true
                            }

                            it.myStats?.user?.picture?.let {
                                userImageManRunning.gone()
                                userImage.visible()

                                userImage.load(activityContext, it) {
                                    userImageManRunning.visible()
                                    userImage.gone()
                                }
                            }

                            Log.i("data for array", teamMembersData.toString())
                            for (item in members) {
                                teamMembersData?.add(item)
                            }

                            myMemberListAdapter =
                                    STMemberListAdapter(
                                            activityContext,
                                            (teamMembersData)!!,
                                            isChallengeStarted,
                                            isChallengeCompleted)
                            myMemberListAdapter?.isSearchedData = isSearchedData

                        }

                        if (isSearchedData != true && offset != 0) {
                            list_members.scrollToPosition(lastVisiblePostion)
                        }

                    }
                }
        }
        list_members.adapter = myMemberListAdapter
        myMemberListAdapter?.setClickListener(object :
                STMemberListAdapter.OnItemClickListener {
            override fun onItemClick(userId: String?) {
            }

            override fun onCheerClick(teamMember: STTeamMember, position: Int) {
                invokeIntent(
                        STTeamDetailIntent.CheerChallengeUser(
                                challengesId!!,
                                teamMember.id!!
                        )
                )
            }
        })

        list_members.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = recyclerView.layoutManager?.childCount
                val totalItemCount = recyclerView.layoutManager?.itemCount

                val firstVisibleItemPosition = (recyclerView.layoutManager as? LinearLayoutManager)?.findFirstVisibleItemPosition()
                if (isSearchedData == false) {
                    lastVisiblePostion = (recyclerView.layoutManager as? LinearLayoutManager)?.findLastVisibleItemPosition()!!
                }
                if (!isLoading && !isLastPage) {
                    if (visibleItemCount!! + firstVisibleItemPosition!! >= totalItemCount!! && firstVisibleItemPosition >= 0) {
                        offset = offset + 1
                        isLoading = true
                        challengesId?.let {
                            invokeIntent(STTeamDetailIntent.GetChallengeTeamMembers(teamId, it, offset))
                        }
                    }
                }
            }
        })
    }

    private fun viewPositionNormal(p0: Int) {
        position.visible()
        rankingImage.gone()
        position.text = (p0).toString()
    }

    private fun getExtras() {
        try
        {

            teamId = arguments?.getSerializable("teamId") as String
          //  teamMembers =
           //     arguments?.getSerializable(STConstants.TEAM_MEMBER_LIST) as ArrayList<STTeamMember>
            isChallengeStarted = arguments?.getBoolean(STConstants.CHALLENGE_STARTED, false)
            isChallengeCompleted = arguments?.getBoolean(STConstants.CHALLENGE_COMPLETED, false)
            challengesId = arguments?.getString(STConstants.CHALLENGE_ID)
        }catch (e: TransactionTooLargeException)
        {
            Log.e("sdfsdf", "sdfsdf")
        }
    }

    fun showKeyboard(view: EditText) {
        val imm: InputMethodManager? = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
        imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    var tempString = ""
    @OnTextChanged(R.id.search_members)
    fun membersSearch(text: CharSequence) {
        teamMembersData?.clear()
        teamMembers?.clear()
        if (text.isEmpty()) {
            stickView.visibility = View.VISIBLE
            offset = 0
           // timer2.cancel()
            timer2.schedule(1000) {
                challengesId?.let {
                    teamMembersData?.clear()
                    isLastPage = false
                    isLoading = false
                    invokeIntent(STTeamDetailIntent.GetChallengeTeamMembers(teamId, it, 0))
                }
            }

         } else {
            isSearchedData = true
            stickView.visibility = View.GONE
            tempString=text.toString()
            timer.cancel()
            timer=Timer("SettingUp1", false)

            timer.schedule(object : TimerTask() {
                override fun run() {

                    Log.e("inner","post delay")
                    if (text.isNotEmpty()) {
                        teamMembersData?.clear()
                        challengesId?.let {
                            invokeIntent(STTeamDetailIntent.GetChallengeSearchedTeamMembers(teamId, it, text.toString()))
                        }
                    }
                }
            }, 500)

        }
    }
}
