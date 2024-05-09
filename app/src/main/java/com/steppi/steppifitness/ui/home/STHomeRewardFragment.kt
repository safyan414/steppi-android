package com.steppi.steppifitness.ui.home

import android.content.Intent
import butterknife.OnClick
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.home.STFeaturedData
import com.steppi.steppifitness.ui.base.STBaseFragment
import com.steppi.steppifitness.ui.common.STContainerActivity
import com.steppi.steppifitness.utils.STPreference
import com.steppi.steppifitness.utils.STUtils
import com.steppi.steppifitness.utils.load
import kotlinx.android.synthetic.main.fragment_home_reward.*
import kotlinx.android.synthetic.main.fragment_home_reward.title
import java.text.NumberFormat
import java.util.*

class STHomeRewardFragment : STBaseFragment() {
//    private val random = Random()

    override fun getLayoutId(): Int {
        return R.layout.fragment_home_reward
    }

    override fun initViews() {
        initData()
        setFeaturedData()
    }

    private var featuredData: STFeaturedData? = null
    private fun initData() {
        featuredData =
            arguments?.getSerializable(STConstants.FEATURED_ARG_POSITION) as? STFeaturedData
    }

    private fun setFeaturedData() {
        if (STPreference.getLanguageCode(activityContext) == STConstants.ARABIC_CODE) {
            title.rotationY = 180F
            homeRewardBanner.rotationY = 180F
            requiredStepsView.rotationY = 180F
            estimateSavingView.rotationY = 180F
        }
        featuredData?.let { featuredDataList ->
            estimateSaving.text = "${NumberFormat.getNumberInstance(Locale.US)
                .format(featuredDataList.estimateSaving!!.toInt())}${activityContext.getString(R.string.aed_)}"
            requiredSteps.text = NumberFormat.getNumberInstance(Locale.US)
                .format(featuredDataList.requiredSteps!!.toInt())
//            featuredDataList.description?.let { description ->
//                if (description == "") {
//                    title.text = featuredDataList.name
//                } else {
//                    title.text = description
//                }
//            } ?: kotlin.run {
            featuredDataList.merchant?.name?.let {
                title.text = it + " - " + featuredDataList.name
            } ?: kotlin.run {
                title.text = featuredDataList.name
            }
//            }
            featuredDataList.merchant?.images?.let {
                if (!it.isNullOrEmpty()) {
                    homeRewardBanner.load(activityContext, it[0]) // randomValue(0, (it.size) - 1)
                } else {
                    homeRewardBanner.setImageResource(R.drawable.place_holder_image)
                }
                STUtils.setImageSize(
                    activityContext,
                    homeRewardBanner,
                    STUtils.convertDpToPixel(activityContext, 12F).toInt()
                )
            }
        }
    }

    @OnClick(R.id.rewardListCell)
    fun rewardListCell() {
        featuredData?.let {
            val container = Intent(activityContext, STContainerActivity::class.java)
            container.putExtra(STConstants.FRAGMENT_NAME, it.merchant?.name)
            container.putExtra(
                STConstants.FRAGMENT_ID,
                STConstants.FRAGMENT_ID_REWARDS_DETAILS
            )
            it.merchant?.let { merchantData ->
                container.putExtra(STConstants.MERCHANT_LIST, merchantData)
            }
            startActivity(container)
        }
    }

//    private fun randomValue(from: Int, to: Int) = random.nextInt(to - from + 1) + from
}
