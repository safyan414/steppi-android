package com.steppi.steppifitness.adapter.unit_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.steppi.steppifitness.R
import com.steppi.steppifitness.utils.STUtils
import com.steppi.steppifitness.utils.invisible
import com.steppi.steppifitness.utils.visible
import kotlinx.android.synthetic.main.unit_list_item.view.*

class STUnitListAdapter(
    private val unitList: ArrayList<String>?,
    private var unitSelected: String?,
    val listItemOnClick: (String) -> Unit
) : RecyclerView.Adapter<STUnitListAdapter.UnitListViewViewHolder>() {
    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnitListViewViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.unit_list_item, parent, false)
        return UnitListViewViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return unitList?.size ?: 0
    }

    override fun onBindViewHolder(holder: UnitListViewViewHolder, position: Int) {
        unitList?.let {
            val unitName = it[position]
            STUtils.setValueToView(holder.itemView.unit, unitName)
            unitSelected?.let { unitSelected ->
                if (unitSelected == unitName) {
                    holder.itemView.selectUnit.visible()
                } else {
                    holder.itemView.selectUnit.invisible()
                }
            }
            holder.itemView.setOnClickListener {
                unitSelected = null
                selectedPosition = position
                listItemOnClick(unitName)
                notifyDataSetChanged()
            }
        }
    }

    fun setUnitSelected(unitSelected: String) {
        this.unitSelected = unitSelected
        notifyDataSetChanged()
    }

    class UnitListViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
