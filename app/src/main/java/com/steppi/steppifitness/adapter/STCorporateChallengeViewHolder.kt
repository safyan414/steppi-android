package com.steppi.steppifitness.adapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.steppi.steppifitness.R
import com.steppi.steppifitness.R2.id.playButton
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.challenges.data.STChallengesListData
import com.steppi.steppifitness.utils.STUtils
import com.steppi.steppifitness.utils.gone
import com.steppi.steppifitness.utils.load
import com.steppi.steppifitness.utils.visible
import kotlinx.android.synthetic.main.adapter_my_challenge_item.view.*
import kotlinx.android.synthetic.main.adapter_my_challenge_item.view.challengeDescription
import kotlinx.android.synthetic.main.adapter_my_challenge_item.view.challengeName
import kotlinx.android.synthetic.main.adapter_my_challenge_item.view.date
import kotlinx.android.synthetic.main.adapter_my_challenge_item.view.deleteChallenge
import kotlinx.android.synthetic.main.adapter_my_challenge_item.view.endDateLayout
import kotlinx.android.synthetic.main.adapter_my_challenge_item.view.gift
import kotlinx.android.synthetic.main.adapter_my_challenge_item.view.giftCell
import kotlinx.android.synthetic.main.adapter_my_challenge_item.view.itemImage
import kotlinx.android.synthetic.main.adapter_my_challenge_item.view.joinNow
import kotlinx.android.synthetic.main.adapter_my_challenge_item.view.logo
import kotlinx.android.synthetic.main.adapter_my_challenge_item.view.startDate
import kotlinx.android.synthetic.main.adapter_my_challenge_item.view.startDateLayout
import kotlinx.android.synthetic.main.adapter_my_challenge_item.view.status
import kotlinx.android.synthetic.main.adapter_steppi_challenge_item.view.*
import kotlinx.android.synthetic.main.fragment_my_challenge_details.view.*
import vimeoextractor.OnVimeoExtractionListener
import vimeoextractor.VimeoExtractor
import vimeoextractor.VimeoVideo
import java.io.IOException

class STCorporateChallengeViewHolder(val adapter: STSteppiChallengesListAdapter, itemView: View, val activityContext: Context) :
    RecyclerView.ViewHolder(itemView) {

    fun getVideo(url: String) {
        VimeoExtractor.getInstance().fetchVideoWithURL(
            url,
            null,
            object : OnVimeoExtractionListener {
                override fun onSuccess(video: VimeoVideo) {

                }

                override fun onFailure(throwable: Throwable?) {
                    Log.e("error player", throwable.toString())
                }
            })
    }

    fun bind(challengesData: STChallengesListData) {
        challengesData.let { challengeData ->

            //itemView.challengeTypeValueTextView.text="bilawa shsadsru"
            itemView.challengeName.text = challengeData.name
            itemView.playButton.gone()
            challengeData.urlVideo?.let {

                if (it.isNotEmpty()) {
                    getVideo(it)
                    itemView.playButton.visible()
                }
            }

            challengeData.description?.let {
                if (it == "") {
                    itemView.challengeDescription.gone()
                } else {
                    itemView.challengeDescription.text = it
                }
            } ?: kotlin.run {
                itemView.challengeDescription.gone()
            }
            challengeData.startDate?.let { startDate ->
                itemView.startDate.text = STUtils.getDate(
                    startDate,
                    "yyyy-MM-dd", "dd-MMM-yyyy"
                )
            } ?: kotlin.run {
                itemView.startDateLayout.gone()
            }
            challengeData.endDate?.let { endDate ->
                itemView.date.text = STUtils.getDate(
                    endDate,
                    "yyyy-MM-dd", "dd-MMM-yyyy"
                )
            } ?: kotlin.run {
                itemView.endDateLayout.gone()
            }

            challengeData.corporateChallengeType?.let  { challengeType ->
                itemView.challengeTypeValueTextView.text = "Steps"
                if (challengeType == "step") {
                    itemView.challengeTypeValueTextView.text = "Steps"
                } else if (challengeType == "active_minutes") {
                    itemView.challengeTypeValueTextView.text = "Active Minutes"
                } else if (challengeType == "distance_covered") {
                    itemView.challengeTypeValueTextView.text = "Distance Covered"
                }  else if (challengeType == "calories_burned") {
                    itemView.challengeTypeValueTextView.text = "Calories Burned"
                }
            }

            challengeData.teams?.let { teamsCount ->
                itemView.participantsLayout.visible()
                when (teamsCount) {
                    "0" -> {
                        itemView.teams.text = teamsCount
                    }
                    "1" -> {
                        itemView.teams.text =
                            "$teamsCount ${adapter.activityContext.getString(R.string.teams_value_sub_one)}"
                    }
                    else -> {
                        itemView.teams.text =
                            "$teamsCount ${adapter.activityContext.getString(R.string.teams_value_sub)}"
                    }
                }
            } ?: kotlin.run {
                itemView.participantsLayout.gone()
            }

            challengeData.voucherDescription?.let {
                if (it == "") {
                    itemView.giftCell.gone()
                } else {
                    itemView.giftCell.visible()
                    itemView.gift.text = it
                }
            } ?: kotlin.run {
                itemView.giftCell.gone()
            }


//            if (challengeData.isCompleted == null || !challengeData.isCompleted!!) {
//                itemView.endDateLayout.visible()
//                itemView.dailyAverageLayout.gone()
//                itemView.totalScoreLayout.gone()
//                itemView.teamRankLayout.gone()
//            } else {
//                val myTeam = challengeData.participantTeams?.first {
//                    it.myTeam
//                }
//                myTeam?.totalScore?.let {
//                    itemView.totalScoreLayout.visible()
//                    itemView.totalScore.text = it
//                } ?: kotlin.run {
//                    itemView.totalScoreLayout.gone()
//                }
//                myTeam?.dailyAverage?.let {
//                    itemView.dailyAverageLayout.visible()
//                    itemView.dailyAverage.text = it
//                } ?: kotlin.run {
//                    itemView.dailyAverageLayout.gone()
//                }
//                myTeam?.rank?.let {
//                    itemView.teamRankLayout.visible()
//                    itemView.teamRank.text = it.toString()
//                } ?: kotlin.run {
//                    itemView.teamRankLayout.gone()
//                }
//            }
//            challengeData.voucherDescription?.let { voucherDescriptionText ->
//                if (voucherDescriptionText == "") {
//                    itemView.voucherDescriptionLayout.gone()
//                } else {
//                    itemView.voucherDescriptionLayout.visible()
//                    itemView.voucherDescription.text = voucherDescriptionText
//                }
//            } ?: kotlin.run {
//                itemView.voucherDescriptionLayout.gone()
//            }
            if (!challengesData.images.isNullOrEmpty()) {
                itemView.itemImage.load(
                    adapter.activityContext,
                    challengesData.images!![0]
                )
            } else {
                itemView.itemImage.setImageDrawable(
                    ContextCompat.getDrawable(
                        adapter.activityContext,
                        R.color.theme_color
                    )
                )
            }
            STUtils.setImageSize(
                adapter.activityContext,
                itemView.itemImage,
                STUtils.convertDpToPixel(
                    adapter.activityContext,
                    25F
                ).toInt()/*32*//*STUtils.convertPixelsToDp(activityContext, 25F).toInt()*/
            )
//            itemView.stepsLayout.gone()
            challengeData.corporation?.let { corporationData ->
                itemView.logo.load(adapter.activityContext, corporationData.logo)
//                itemView.challengeType.text = corporationData.name
            }
            challengeData.status?.let {
                if (adapter.challengeListType == STConstants.MY_CHALLENGE_LIST && it.isNotEmpty()) {
                    itemView.status.visible()
                    when { // Upcoming Ongoing Completed
                        it.equals(STConstants.CHALLENGE_STATUS_UPCOMING, true) -> {
                            itemView.deleteChallenge.gone()
                            itemView.status.text =
                                adapter.activityContext.getString(R.string.upcoming)
                            itemView.status.setTextColor(
                                STUtils.getColor(
                                    adapter.activityContext,
                                    R.color.orange_color
                                )
                            )
//                            if(upComing == true) {
//                                itemView.corporateLayout.visible()
//                            } else {
//                                itemView.corporateLayout.gone()
//                            }
                        }
                        it.equals(STConstants.CHALLENGE_STATUS_ONGOING, true) -> {
                            itemView.deleteChallenge.gone()
                            itemView.status.text =
                                adapter.activityContext.getString(R.string.ongoing)
                            itemView.status.setTextColor(
                                STUtils.getColor(
                                    adapter.activityContext,
                                    R.color.button_bg_enabled_color
                                )
                            )
                        }
                        it.equals(STConstants.CHALLENGE_STATUS_COMPLETED, true) -> {
                            itemView.deleteChallenge.visible()
                            itemView.status.gone()

//                            itemView.status.text =
//                                adapter.activityContext.getString(R.string.done)
//                            itemView.status.setTextColor(
//                                STUtils.getColor(
//                                    adapter.activityContext,
//                                    R.color.red_color
//                                )
//                            )
                        }
                    }
                } else {
                    itemView.status.gone()
                }
            } ?: kotlin.run {
                itemView.status.gone()
            }
            itemView.deleteChallenge.setOnClickListener {
                if (STUtils.singleClick()) {
                    adapter.onClickItem?.onDeleteClick(challengeData, adapterPosition)
                }
            }
            itemView.joinNow.setOnClickListener {
                if (STUtils.singleClick()) {
                    adapter.onClickItem?.onItemClick(adapter.getItem(adapterPosition) as STChallengesListData)
                }
            }
            itemView.setOnClickListener {
                if (STUtils.singleClick()) {
                    if (challengeData.targetSteps == "0" && challengeData.challengeType.equals(
                            STConstants.STEPPI_CHALLENGE,
                            ignoreCase = true
                        )
                    ) {
//                    Toast.makeText(
//                        adapter.activityContext,
//                        adapter.activityContext.getString(R.string.invalid_goal),
//                        Toast.LENGTH_SHORT
//                    ).show()
                    } else {
                        adapter.onClickItem?.onItemClick(adapter.getItem(adapterPosition) as STChallengesListData)
                    }
                }
            }
        }
    }
}
