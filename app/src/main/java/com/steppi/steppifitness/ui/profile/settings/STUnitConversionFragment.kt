package com.steppi.steppifitness.ui.profile.settings

import androidx.recyclerview.widget.LinearLayoutManager
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.unit_list.STUnitListAdapter
import com.steppi.steppifitness.ui.base.STBaseFragment
import com.steppi.steppifitness.utils.STPreference
import com.steppi.steppifitness.utils.STUtils
import com.steppi.steppifitness.utils.STVerticalSpaceItemDecoration
import kotlinx.android.synthetic.main.fragment_unit_conversion.*

class STUnitConversionFragment : STBaseFragment() {
    private var unitsListAdapter: STUnitListAdapter? = null
    private var unitSelected: String? = null
    private var unitList: ArrayList<String>? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_unit_conversion
    }

    override fun initViews() {
        unitsList.layoutManager = LinearLayoutManager(activityContext)
        unitsList.addItemDecoration(
            STVerticalSpaceItemDecoration(
                STUtils.getDimen(
                    activityContext,
                    R.dimen.margin_normal
                )
            )
        )
        unitList = ArrayList()
        unitList?.add(getString(R.string.kilometer))
        unitList?.add(getString(R.string.miles))
        STPreference.getUnitSelected(activityContext)?.let {
            unitSelected = it
        }
        unitsListAdapter = STUnitListAdapter(
            unitList, unitSelected,
            listItemOnClick = { unitName ->
                STPreference.saveUnitSelected(activityContext, unitName)
                unitSelected = unitName
                unitsListAdapter?.setUnitSelected(unitSelected!!)
            }
        )
        unitsList.adapter = unitsListAdapter
    }
}
