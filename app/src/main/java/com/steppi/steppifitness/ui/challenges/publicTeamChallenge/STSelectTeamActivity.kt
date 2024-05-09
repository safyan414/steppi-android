package com.steppi.steppifitness.ui.challenges.publicTeamChallenge

import android.app.Activity
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.challenge.STSelectTeamAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.STRetrofitClient
import com.steppi.steppifitness.network.response.challenges.STChallengeTeamResponse
import com.steppi.steppifitness.network.response.challenges.data.STChallengeTeamData
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelActivity
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesController
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesIntent
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesState
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.activity_select_team.*
import kotlinx.android.synthetic.main.activity_select_team.no_data
import kotlinx.android.synthetic.main.header_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class STSelectTeamActivity :
    STBaseViewModelActivity<STChallengesIntent, STChallengesState, STChallengesController>(
        STChallengesController::class.java
    ) {
    private var isLastPage: Boolean = false
    private var isLoading: Boolean = false
    private var listTotal: Int = 0
    private var offset = 0
    private var adapter: STSelectTeamAdapter? = null
    private var challengeId: String? = null
    private var queryText: String = ""
    private var selectedTeamPos: Int = -1

    override fun getLayoutId(): Int {
        return R.layout.activity_select_team
    }

    override fun initViews() {
        STUtils.setValueToView(header_name, getString(R.string.select_team_title))
        getIntentData()

        teamList.setVerticalLayoutManager()
        adapter = STSelectTeamAdapter(selectedTeamPos)
        teamList.adapter = adapter
        adapter!!.setClickListener(object : STSelectTeamAdapter.OnItemClickListener {
            override fun onItemClick(team: STChallengeTeamData, position: Int) {
                val intent = Intent()
                intent.putExtra(STConstants.SELECTED_TEAM, team)
                intent.putExtra(STConstants.SELECTED_TEAM_POS, position)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }

        })

        teamSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                if (s.toString()
//                        .isNotEmpty() && s?.length!! >= 2/*charSequence?.length?.rem(3) == 0*/) {
                queryText = s.toString()
                resetPaginationParam()
                getTeams()

//                } else {
//                    adapter?.clear()
//                    queryText = ""
//                    if (searchRequest != null) {
//                        searchRequest!!.cancel()
//                    }
//                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
        addScrollListener(teamList.layoutManager as LinearLayoutManager)
    }

    private fun getIntentData() {
        challengeId = intent.getStringExtra(STConstants.CHALLENGES_DATA_ID)
        selectedTeamPos = intent.getIntExtra(STConstants.SELECTED_TEAM_POS, -1) as Int
    }

    private fun resetPaginationParam() {
        isLoading = false
        isLastPage = false
        offset = 0
        listTotal = 0
    }

    override fun processState(state: STChallengesState) {
    }

    private fun setTeamData(response: STChallengeTeamResponse?) {
        listTotal = response?.total ?: 0
        response?.data?.let {
            if (it.isEmpty()) {
                no_data?.visible()
                no_data?.text = getString(R.string.no_search_results)
            } else {
                no_data?.gone()
            }

            if (offset > 0) {
                adapter!!.removeLoadingFooter()
                isLoading = false
            }

            adapter?.setTeamData(it as ArrayList<STChallengeTeamData>)

            if (listTotal > adapter!!.getListSize()) {
                adapter!!.addLoadingFooter()
                offset = adapter!!.getListSize() - 1
                //offset += 1
            } else isLastPage = true

        } ?: kotlin.run {
            no_data?.visible()
            no_data?.text = getString(R.string.no_search_results)
        }

    }

    override fun onViewModelReady() {
        queryText = ""
        getTeams()
    }

    private fun addScrollListener(layoutManager: LinearLayoutManager) {
        teamList.addOnScrollListener(object : EndlessScrollListener(
            layoutManager, STConstants.VISIBLE_THRESHOLD
        ) {
            override fun loadMoreItems() {
                isLoading = true
                getTeams()
            }

            override fun isLastPage() = isLastPage

            override fun isLoading() = isLoading

        })
    }

    private var searchRequest: Call<STChallengeTeamResponse>? = null
    private fun getTeams() {
//        if (queryText == "") {
//            adapter?.clear()
//            if (searchRequest != null) {
//                searchRequest!!.cancel()
//            }
//        } else {
        if (!isLoading) {
            adapter?.clear()
        }
        if (searchRequest != null) {
            searchRequest!!.cancel()
        }
        searchRequest = STRetrofitClient.create(activityContext).getChallengeTeams(
            challengeId,
            offset.toString(), queryText
        )
        searchRequest!!.enqueue(object : Callback<STChallengeTeamResponse> {
            override fun onFailure(call: Call<STChallengeTeamResponse>?, t: Throwable?) {
            }

            override fun onResponse(
                call: Call<STChallengeTeamResponse>?,
                response: Response<STChallengeTeamResponse>?
            ) {
                response?.let {
                    val searchResponse = it.body()
                    searchResponse?.let {
                        setTeamData(searchResponse)
                    } ?: kotlin.run {
                        no_data?.visible()
                        no_data?.text = getString(R.string.no_search_results)
                    }
                } ?: kotlin.run {
                    no_data?.visible()
                    no_data?.text = getString(R.string.no_search_results)
                }
            }
        })
//        }
    }
}