package com.steppi.steppifitness.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.view.View
import android.webkit.URLUtil
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.challenges.data.STChallengesListData
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.adapter_steppi_challenge_item.view.*
import kotlinx.android.synthetic.main.adapter_steppi_challenge_item.view.challengeDescription
import kotlinx.android.synthetic.main.adapter_steppi_challenge_item.view.challengeName
import kotlinx.android.synthetic.main.adapter_steppi_challenge_item.view.challengeThemeType
import kotlinx.android.synthetic.main.adapter_steppi_challenge_item.view.date
import kotlinx.android.synthetic.main.adapter_steppi_challenge_item.view.deleteChallenge
import kotlinx.android.synthetic.main.adapter_steppi_challenge_item.view.endDate
import kotlinx.android.synthetic.main.adapter_steppi_challenge_item.view.endDateLayout
import kotlinx.android.synthetic.main.adapter_steppi_challenge_item.view.itemImage
import kotlinx.android.synthetic.main.adapter_steppi_challenge_item.view.joinNow
import kotlinx.android.synthetic.main.adapter_steppi_challenge_item.view.participant
import kotlinx.android.synthetic.main.adapter_steppi_challenge_item.view.participantLayout
import kotlinx.android.synthetic.main.adapter_steppi_challenge_item.view.status
import kotlinx.android.synthetic.main.fragment_my_challenge_details.view.*
import vimeoextractor.OnVimeoExtractionListener
import vimeoextractor.VimeoExtractor
import vimeoextractor.VimeoVideo
import java.io.IOException
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*

class STSteppiChallengeViewHolder(
        val adapter: STSteppiChallengesListAdapter,
        itemView: View,
        val activityContext: Context
) : RecyclerView.ViewHolder(itemView) {


    fun getVideo(url: String) {
        itemView.youtubeButton.visible()
    }

    fun bind(challengesData: STChallengesListData) {
        challengesData.let { challengeData ->
            itemView.challengeName.text = challengeData.name

            challengeData.logo?.let { logo ->

                if (logo.isNotEmpty()) {

                    itemView.challengeLogo.load(activityContext, logo)
                    itemView.youtubeButton.invisible()
                    challengeData.urlVideo?.let {

                        if (it.isNotEmpty() && URLUtil.isValidUrl(it)) {
                            getVideo(it)
                        }
                    }

                    Glide.with(activityContext).load(logo)
                            .listener(object : RequestListener<Drawable> {
                                override fun onLoadFailed(
                                        e: GlideException?,
                                        model: Any?,
                                        target: Target<Drawable>?,
                                        isFirstResource: Boolean
                                ): Boolean {
                                    itemView.challengeLogo.setImageResource(R.drawable.steppi_icon)
                                    return false
                                }

                                override fun onResourceReady(
                                        resource: Drawable?,
                                        model: Any?,
                                        target: Target<Drawable>?,
                                        dataSource: DataSource?,
                                        isFirstResource: Boolean
                                ): Boolean {
                                    itemView.challengeLogo.setImageDrawable(resource)
                                    return false
                                }
                            }).into(itemView.challengeLogo)
                } else {
                    itemView.challengeLogo.setImageResource(R.drawable.steppi_icon)

                }
            } ?: kotlin.run {
                itemView.challengeLogo.setImageResource(R.drawable.steppi_icon)
            }

            if (challengeData.isPrivate != null && challengeData.isPrivate!!) {
                itemView.challengeThemeType.text =
                        activityContext.getString(R.string.private_label)/*STConstants.PRIVATE*/
                itemView.challengeThemeType.setBackgroundResource(R.drawable.button_bg_enabled_small)
                if (STPreference.getLanguageCode(activityContext) == STConstants.ARABIC_CODE) {
                    itemView.challengeThemeType.setCompoundDrawablesWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.private_challenge_icon,
                            0
                    )
                } else {
                    itemView.challengeThemeType.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.private_challenge_icon,
                            0,
                            0,
                            0
                    )
                }
                itemView.labelParticipants.text =
                        activityContext.getString(R.string.participants)
                itemView.publicChallengeLayout.setBackgroundResource(R.drawable.edit_profile_bg)
            } else {
                if (STPreference.getLanguageCode(activityContext) == STConstants.ARABIC_CODE) {
                    itemView.challengeThemeType.setCompoundDrawablesWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.challenge_theme_type,
                            0
                    )
                } else {
                    itemView.challengeThemeType.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.challenge_theme_type,
                            0,
                            0,
                            0
                    )
                }
                challengeData.type?.let {
                    when (it) {
                        STConstants.STEPPI_CHALLENGE_1 -> {
                            itemView.challengeLogo.visible()
                            itemView.challengeThemeType.visible()
                            itemView.coverImageNormal.visible()
                            itemView.coverImageDFC.gone()
                            itemView.voucherDescriptionLayout.visible()
                            itemView.challengeThemeType.text =
                                    activityContext.getString(R.string.pc1)/*STConstants.PC1*/
                            itemView.labelParticipants.text =
                                    activityContext.getString(R.string.participants)
                            itemView.challengeThemeType.setBackgroundResource(R.drawable.button_bg_violet)
                            if (STPreference.getLanguageCode(activityContext) == STConstants.ARABIC_CODE) {
                                itemView.challengeThemeType.setCompoundDrawablesWithIntrinsicBounds(
                                        0,
                                        0,
                                        R.drawable.challenge_theme_type,
                                        0
                                )
                            } else {
                                itemView.challengeThemeType.setCompoundDrawablesWithIntrinsicBounds(
                                        R.drawable.challenge_theme_type,
                                        0,
                                        0,
                                        0
                                )
                            }
                            itemView.publicChallengeLayout.setBackgroundResource(R.drawable.background_violet_stroke)
                        }
                        STConstants.STEPPI_CHALLENGE_2 -> {
                            itemView.challengeLogo.visible()
                            itemView.challengeThemeType.visible()
                            itemView.coverImageNormal.visible()
                            itemView.coverImageDFC.gone()
                            itemView.voucherDescriptionLayout.visible()
                            itemView.labelParticipants.text =
                                    activityContext.getString(R.string.teams_label)
                            itemView.challengeThemeType.text =
                                    activityContext.getString(R.string.pc2)/*STConstants.PC2*/
                            itemView.challengeThemeType.setBackgroundResource(R.drawable.button_bg_light_blue_small)
                            if (STPreference.getLanguageCode(activityContext) == STConstants.ARABIC_CODE) {
                                itemView.challengeThemeType.setCompoundDrawablesWithIntrinsicBounds(
                                        0,
                                        0,
                                        R.drawable.private_challenge_icon,
                                        0
                                )
                            } else {
                                itemView.challengeThemeType.setCompoundDrawablesWithIntrinsicBounds(
                                        R.drawable.private_challenge_icon,
                                        0,
                                        0,
                                        0
                                )
                            }
                            itemView.publicChallengeLayout.setBackgroundResource(R.drawable.background_light_blue_stroke)
                        }
                        STConstants.STEPPI_CHALLENGE_3 -> {
                            itemView.challengeLogo.visible()
                            itemView.challengeThemeType.visible()
                            itemView.coverImageNormal.visible()
                            itemView.coverImageDFC.gone()
                            itemView.voucherDescriptionLayout.visible()
                            itemView.challengeThemeType.text =
                                    activityContext.getString(R.string.pc3)/*STConstants.PC3*/
                            itemView.challengeThemeType.setBackgroundResource(R.drawable.button_bg_orange_small)
                            if (STPreference.getLanguageCode(activityContext) == STConstants.ARABIC_CODE) {
                                itemView.challengeThemeType.setCompoundDrawablesWithIntrinsicBounds(
                                        0,
                                        0,
                                        R.drawable.pc_three_icon,
                                        0
                                )
                            } else {
                                itemView.challengeThemeType.setCompoundDrawablesWithIntrinsicBounds(
                                        R.drawable.pc_three_icon,
                                        0,
                                        0,
                                        0
                                )
                            }
                            itemView.labelParticipants.text =
                                    activityContext.getString(R.string.participants)
                            itemView.publicChallengeLayout.setBackgroundResource(R.drawable.background_orange_stroke)
                        }
                        STConstants.TOUGH_MUDDER_CHALLENGE -> {
                            itemView.participantLayout.gone()
                            itemView.challengeLogo.visible()
                            itemView.challengeThemeType.visible()
                            itemView.coverImageNormal.visible()
                            itemView.coverImageDFC.gone()
                            itemView.voucherDescriptionLayout.visible()
                            itemView.challengeThemeType.gone()
                            itemView.challengeThemeType.text = STConstants.THUGH_MUDDER
                            itemView.challengeThemeType.setTextColor(
                                    STUtils.getColor(
                                            activityContext,
                                            R.color.black
                                    )
                            )
                            itemView.challengeThemeType.setBackgroundResource(R.drawable.button_bg_tough_mudder_small)
                            itemView.challengeThemeType.setCompoundDrawablesWithIntrinsicBounds(
                                    0/*R.drawable.team_angel_wolf_small*/,
                                    0,
                                    0,
                                    0
                            )
                            itemView.labelParticipants.text =
                                    activityContext.getString(R.string.participants)
                            itemView.publicChallengeLayout.setBackgroundResource(R.drawable.background_tough_mudder_stroke)
                        }
                        STConstants.DUBAI_FITNESS_CHALLENGE -> {
                            itemView.challengeLogo.gone()
                            itemView.challengeThemeType.gone()
                            itemView.coverImageNormal.gone()
                            itemView.coverImageDFC.visible()
                            itemView.voucherDescriptionLayout.gone()
                            if (!challengesData.images.isNullOrEmpty()) {
                                itemView.itemImageDFC.load(
                                        adapter.activityContext,
                                        challengesData.images!![0]
                                )
                            } else {
                                itemView.itemImageDFC.setImageDrawable(
                                        ContextCompat.getDrawable(
                                                adapter.activityContext,
                                                R.drawable.place_holder_image
                                        )
                                )
                            }
                            STUtils.setImageSize(
                                    adapter.activityContext,
                                    itemView.itemImageDFC,
                                    STUtils.convertDpToPixel(
                                            adapter.activityContext,
                                            15F
                                    )
                                            .toInt()/*32*//*STUtils.convertPixelsToDp(activityContext, 25F).toInt()*/
                            )
                            itemView.labelParticipants.text =
                                    activityContext.getString(R.string.teams)
//                            itemView.challengeThemeType.setBackgroundResource(R.drawable.button_bg_violet)
//                            itemView.challengeThemeType.setCompoundDrawablesWithIntrinsicBounds(
//                                R.drawable.challenge_theme_type,
//                                0,
//                                0,
//                                0
//                            )
                            itemView.publicChallengeLayout.setBackgroundResource(R.drawable.background_light_blue_stroke)
                        }
                        else -> {
                            itemView.challengeLogo.visible()
                            itemView.challengeThemeType.visible()
                            itemView.coverImageNormal.visible()
                            itemView.coverImageDFC.gone()
                            itemView.voucherDescriptionLayout.visible()
                            itemView.challengeThemeType.text = it
                            itemView.labelParticipants.text =
                                    activityContext.getString(R.string.participants)
                        }
                    }
                } ?: kotlin.run { itemView.challengeThemeType.gone() }
            }
            challengeData.description?.let {
                if (it != "") {
                    itemView.challengeDescription.text = it
                } else {
                    itemView.challengeDescription.gone()
                    itemView.challengeDescriptionTitle.gone()
                }
            } ?: kotlin.run {
                itemView.challengeDescription.gone()
                itemView.challengeDescriptionTitle.gone()
            }
            challengeData.targetSteps?.let {
                when (challengeData.type) {
                    STConstants.STEPPI_CHALLENGE_3 -> {
                        itemView.steps.text =
                                NumberFormat.getNumberInstance(Locale.US)
                                        .format(
                                                BigDecimal(it.toDouble()).setScale(
                                                        0,
                                                        RoundingMode.HALF_EVEN
                                                )
                                        ) + " " + adapter.activityContext.getString(
                                        R.string.steps_per_day
                                )
                    }
                    STConstants.TOUGH_MUDDER_CHALLENGE -> {
                        if (it != "0")
                            itemView.steps.text =
                                    NumberFormat.getNumberInstance(Locale.US)
                                            .format(
                                                    BigDecimal(it.toDouble()).setScale(
                                                            3,
                                                            RoundingMode.HALF_EVEN
                                                    )
                                            ) + " " + adapter.activityContext.getString(
                                            R.string.steps_per_day
                                    )
                    }
                    else -> {
                        itemView.steps.text =
                                NumberFormat.getNumberInstance(Locale.US)
                                        .format(
                                                BigDecimal(it.toDouble()).setScale(
                                                        0,
                                                        RoundingMode.HALF_EVEN
                                                )
                                        ) + " " + adapter.activityContext.getString(
                                        R.string.steps
                                )
                    }
                }
                setTargetGoalLayoutVisibility(it, challengeData)
            }
            challengeData.targetDistance?.let { targetDistance ->
                if (challengeData.type == STConstants.TOUGH_MUDDER_CHALLENGE) {
                    if (targetDistance != "0") {
                        itemView.steps.text =
                                NumberFormat.getNumberInstance(Locale.US)
                                        .format(
                                                BigDecimal(targetDistance.toDouble() / 1000).setScale(
                                                        3,
                                                        RoundingMode.HALF_EVEN
                                                )
                                        ) + " " + adapter.activityContext.getString(R.string.km_per_day)
                    }
                }
            }
            challengeData.targetCalories?.let { targetCalorie ->
                if (challengeData.type == STConstants.TOUGH_MUDDER_CHALLENGE) {
                    if (targetCalorie != "0")
                        itemView.steps.text =
                                NumberFormat.getNumberInstance(Locale.US)
                                        .format(
                                                BigDecimal(targetCalorie.toDouble()).setScale(
                                                        3,
                                                        RoundingMode.HALF_EVEN
                                                )
                                        )/*STUtils.formatNumber(targetCalorie.toInt())*/ + " " + adapter.activityContext.getString(
                                        R.string.cal_per_day
                                )
                }
            }
            challengeData.targetActiveMinutes?.let { targetActiveMinutes ->
                if (challengeData.type == STConstants.TOUGH_MUDDER_CHALLENGE) {
                    if (targetActiveMinutes != "0")
                        itemView.steps.text =
                                NumberFormat.getNumberInstance(Locale.US)
                                        .format(targetActiveMinutes.toInt()) +
                                        " " + adapter.activityContext.getString(R.string.minutes_per_day)
                }
            }
            challengeData.startDate?.let {
                itemView.date.text = STUtils.getDate(
                        it,
                        "yyyy-MM-dd", "dd-MMM-yyyy"
                )
            }
            challengeData.endDate?.let { endDate ->
                if (challengeData.type == STConstants.TOUGH_MUDDER_CHALLENGE) {
                    itemView.endDateLayout.gone()
                } else {
                    itemView.endDateLayout.visible()
                    val currentDate = STUtils.getFormattedDate(
                            Calendar.getInstance(),
                            "yyyy-MM-dd"
                    )
                    /*when (STUtils.compareEndDate(endDate, currentDate)) {
                    STConstants.CHALLENGE_NOT_ENDED -> {
                        Log.d("CHALLENGE_NOT_ENDED", "CHALLENGE_NOT_ENDED")
                        adapter.isChallengeCompleted = false
                    }
                    STConstants.CHALLENGE_COMPLETED -> {
                        adapter.isChallengeCompleted = true
                    }
                }*/
                    itemView.endDate.text = STUtils.getDate(
                            challengeData.endDate,
                            "yyyy-MM-dd", "dd-MMM-yyyy"
                    )
                }
            } ?: kotlin.run {
                itemView.endDateLayout.gone()
            }
            challengeData.voucherDescription?.let { voucherDescriptionText ->
                val voucherDescription = itemView.findViewById<TextView>(R.id.voucherDescription)
                if (voucherDescriptionText.isEmpty()) {
                    itemView.voucherDescriptionLayout.gone()
                } else {
                    itemView.voucherDescriptionLayout.visible()
                    STUtils.setValueToView(voucherDescription, voucherDescriptionText)
//                    voucherDescription.post {
//                        val lineCount: Int = voucherDescription.lineCount
//                        // Use lineCount here
//                        if (lineCount > 1) {
//                            voucherDescription.gravity = Gravity.START
//                            voucherDescription.textAlignment =
//                                View.TEXT_ALIGNMENT_TEXT_START
//                        } else {
//                            voucherDescription.gravity = Gravity.END
//                            voucherDescription.textAlignment =
//                                View.TEXT_ALIGNMENT_TEXT_END
//                        }
//                    }
                }
            } ?: kotlin.run {
                itemView.voucherDescriptionLayout.gone()
            }
            challengeData.type?.let {
                if (it == STConstants.STEPPI_CHALLENGE_2 || it == STConstants.DUBAI_FITNESS_CHALLENGE) {
                    challengeData.teams?.let { teamsCount ->
                        itemView.participantLayout.visible()
                        when (teamsCount) {
                            "0" -> {
                                itemView.participant.text = teamsCount
                            }
                            "1" -> {
                                itemView.participant.text =
                                        "$teamsCount ${activityContext.getString(R.string.teams_value_sub_one)}"
                            }
                            else -> {
                                itemView.participant.text =
                                        "$teamsCount ${activityContext.getString(R.string.teams_value_sub)}"
                            }
                        }
                    } ?: kotlin.run {
                        itemView.participantLayout.gone()
                    }
                } else {
                    if (it == STConstants.TOUGH_MUDDER_CHALLENGE) {
                        itemView.participantLayout.gone()
                    } else {
                        challengeData.participants?.let { participants ->
                            itemView.participantLayout.visible()
                            itemView.participant.text = participants
                        } ?: kotlin.run {
                            itemView.participantLayout.gone()
                        }
                    }
                }
            }
            if (challengeData.joined!!) {
                itemView.joinNow.visible()
                itemView.joinNow.text =
                        activityContext.getString(R.string.challenge_details_button_label)
            } else {
                itemView.joinNow.visible()
            }
            challengeData.status?.let {
                //if (adapter.challengeListType == STConstants.MY_CHALLENGE_LIST && it.isNotEmpty()) {
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
                        if (!challengeData.joined!!) {
                            itemView.joinNow.text =
                                    adapter.activityContext.getString(R.string.join_challenge_cell)
                        }
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
                        if (!challengeData.joined!!) {
                            itemView.joinNow.text =
                                    adapter.activityContext.getString(R.string.join_challenge_cell)
                        }
                    }
                    it.equals(STConstants.CHALLENGE_STATUS_COMPLETED, true) -> {
                        if (challengeData.type == STConstants.TOUGH_MUDDER_CHALLENGE ||
                                challengeData.type == STConstants.DUBAI_FITNESS_CHALLENGE
                        ) {
                            itemView.deleteChallenge.gone()
                            itemView.status.visible()
                            itemView.status.text =
                                    adapter.activityContext.getString(R.string.done)
                            itemView.status.setTextColor(
                                    STUtils.getColor(
                                            adapter.activityContext,
                                            R.color.button_bg_enabled_color
                                    )
                            )
                            if (!challengeData.joined!!) {
                                itemView.joinNow.text =
                                        activityContext.getString(R.string.challenge_details_button_label)
                            }
                        } else {
                            itemView.deleteChallenge.visible()
                            itemView.status.gone()
                        }
                    }
                    it.equals(STConstants.CHALLENGE_STATUS_FAILED, true) -> {
                        itemView.deleteChallenge.gone()
                        itemView.status.text =
                                adapter.activityContext.getString(R.string.failed)
                        itemView.status.setTextColor(
                                STUtils.getColor(
                                        adapter.activityContext,
                                        R.color.red_color
                                )
                        )
                        if (!challengeData.joined!!) {
//                            itemView.joinNow.gone()
//                            itemView.joinNow.text =
//                                adapter.activityContext.getString(R.string.join_challenge_cell)
                            itemView.joinNow.text =
                                    activityContext.getString(R.string.challenge_details_button_label)
                        }
                    }
                }
                /*} else {
                itemView.status.gone()
            }*/
            } ?: kotlin.run {
                itemView.status.gone()
            }
            /*if (!challengeData.joined!! && (challengeData.challengeStatus!!.equals("Upcoming", true)
                        || challengeData.challengeStatus!!.equals("Ongoing", true))
            ) { //!joined && (challengeStatus === 'Upcoming' || challengeStatus === 'Ongoing')
                itemView.joinNow.visible()
            } else {
                itemView.joinNow.gone()
            }*/

            if (!challengesData.images.isNullOrEmpty()) {
                itemView.itemImage.load(
                        adapter.activityContext,
                        challengesData.images!![0]
                )
            } else {
                itemView.itemImage.setImageDrawable(
                        ContextCompat.getDrawable(
                                adapter.activityContext,
                                R.drawable.place_holder_image
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
            itemView.setOnClickListener {
                if (STUtils.singleClick()) {
                    adapter.onClickItem?.let {
                        it.onItemClick(adapter.getItem(adapterPosition) as STChallengesListData)
                    }
                }
            }
            itemView.deleteChallenge.setOnClickListener {
                if (STUtils.singleClick()) {
                    adapter.onClickItem?.let {
                        it.onDeleteClick(challengeData, adapterPosition)
                    }
                }
            }
            itemView.joinNow.setOnClickListener {
                if (STUtils.singleClick()) {
                    if (challengeData.type == STConstants.STEPPI_CHALLENGE_2) {
                        adapter.onClickItem?.onItemClick(adapter.getItem(adapterPosition) as STChallengesListData)
                    } else {
                        if (challengeData.joined!!) {
                            adapter.onClickItem?.onItemClick(adapter.getItem(adapterPosition) as STChallengesListData)
                        } else {
                            challengeData.status?.let {
                                if (it.equals(STConstants.CHALLENGE_STATUS_COMPLETED, true)) {
                                    if (challengeData.type == STConstants.TOUGH_MUDDER_CHALLENGE ||
                                            challengeData.type == STConstants.DUBAI_FITNESS_CHALLENGE
                                    ) {
                                        setDFCToughMudderButtonClick(challengeData)
                                    } else {
                                        joinButtonClick()
                                    }
                                } else {
                                    if (it.equals(STConstants.CHALLENGE_STATUS_FAILED, true)) {
                                        if (challengeData.type == STConstants.TOUGH_MUDDER_CHALLENGE
                                        ) {
                                            setDFCToughMudderButtonClick(challengeData)
                                        } else {
                                            joinButtonClick()
                                        }
                                    } else {
                                        joinButtonClick()
                                    }
                                }
                            } ?: kotlin.run {
                                joinButtonClick()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setDFCToughMudderButtonClick(challengeData: STChallengesListData) {
        if (!challengeData.joined!!) {
            adapter.onClickItem?.onItemClick(
                    adapter.getItem(
                            adapterPosition
                    ) as STChallengesListData
            )
        } else {
            joinButtonClick()
        }
    }

    private fun joinButtonClick() {
        adapter.onClickItem?.onJoinClick(
                adapter.getItem(adapterPosition) as STChallengesListData,
                adapterPosition
        )
    }

    private fun setTargetGoalLayoutVisibility(
            target: String,
            challengeData: STChallengesListData
    ) {
        if (target == "0" && challengeData.challengeType.equals(
                        STConstants.STEPPI_CHALLENGE,
                        ignoreCase = true
                ) && challengeData.type != STConstants.TOUGH_MUDDER_CHALLENGE
        ) {
            itemView.targetGoalLayout.gone()
        } else {
            if (challengeData.type != STConstants.DUBAI_FITNESS_CHALLENGE) {
                itemView.targetGoalLayout.visible()
            } else {
                itemView.targetGoalLayout.gone()
            }
        }
    }
}
