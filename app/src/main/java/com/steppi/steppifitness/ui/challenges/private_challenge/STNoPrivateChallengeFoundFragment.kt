package com.steppi.steppifitness.ui.challenges.private_challenge

import android.content.Intent
import butterknife.OnClick
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.ui.base.STBaseFragment
import com.steppi.steppifitness.ui.common.STContainerActivity
import com.steppi.steppifitness.utils.STUtils

class STNoPrivateChallengeFoundFragment : STBaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_no_private_challenge
    }

    override fun initViews() {
    }

    @OnClick(R.id.joinChallenge)
    fun joinChallenge() {
        if (STUtils.singleClick()) {
            val container = Intent(activityContext, STContainerActivity::class.java)
            container.putExtra(
                STConstants.FRAGMENT_NAME,
                getString(R.string.create_private_challenge)
            )
            container.putExtra(
                STConstants.FRAGMENT_ID,
                STConstants.FRAGMENT_ID_CREATE_PRIVATE_CHALLENGE
            )
            startActivity(container)
            activityContext.finish()
        }
    }
}
