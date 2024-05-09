package com.steppi.steppifitness.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.challenges.data.STChallengesListData
import kotlin.collections.ArrayList

class STSteppiChallengesListAdapter(val activityContext: Context) : STPaginationBaseAdapter() {
    var onClickItem: OnItemClickListener? = null
    private val STEPPI_CHALLENGE = 2
    private val CORPORATE_CHALLENGE = 3
    var challengeListType: String? = null
    val videoview: VideoView? = null


    interface OnItemClickListener {
        fun onItemClick(challengesData: STChallengesListData?)
        fun onJoinClick(challengesData: STChallengesListData?, position: Int)
        fun onDeleteClick(challengesData: STChallengesListData?, position: Int)
    }

    fun setClickListener(listener: OnItemClickListener) {
        this.onClickItem = listener
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == mDataSet.size - 1 && isLoadingAdded) STConstants.LOADING
        else {
            val challenge = (mDataSet[position] as STChallengesListData)
            if (challenge.challengeType.equals(STConstants.STEPPI_CHALLENGE, ignoreCase = true)) {
                STEPPI_CHALLENGE
            } else {
                CORPORATE_CHALLENGE
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {//RewardsTypeListViewHolder
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            STEPPI_CHALLENGE -> STSteppiChallengeViewHolder(
                this,
                inflater.inflate(R.layout.adapter_steppi_challenge_item, parent, false),
                activityContext
            )
            CORPORATE_CHALLENGE -> STCorporateChallengeViewHolder(
                this,
                inflater.inflate(R.layout.adapter_my_challenge_item, parent, false),
                activityContext
            )
            else -> STLoadingViewHolder(
                this,
                inflater.inflate(R.layout.list_item_progress, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            STEPPI_CHALLENGE -> {
                val challengesData = getItem(position) as STChallengesListData
                (holder as STSteppiChallengeViewHolder).bind(challengesData)
            }
            CORPORATE_CHALLENGE -> {
                val challengesData = getItem(position) as STChallengesListData
                (holder as STCorporateChallengeViewHolder).bind(challengesData)
            }
            STConstants.LOADING -> {
                (holder as STLoadingViewHolder).bindViews(retryPageLoad, errorMsg, null)
            }
        }
    }

    fun setChallengeListData(
        isRefresh: Boolean,
        challengesListData: ArrayList<STChallengesListData>,
        challengeType: String
    ) {
        //this.challengesListData = challengesListData
        this.challengeListType = challengeType
        if (isRefresh) {
            mDataSet = ArrayList()
        }
        mDataSet.addAll(challengesListData)
        this.notifyDataSetChanged()
    }
}
