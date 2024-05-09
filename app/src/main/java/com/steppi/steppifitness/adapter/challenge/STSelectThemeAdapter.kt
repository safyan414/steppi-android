package com.steppi.steppifitness.adapter.challenge

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.STLoadingViewHolder
import com.steppi.steppifitness.adapter.STPaginationBaseAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.list_item_theme.view.*

class STSelectThemeAdapter : STPaginationBaseAdapter() {
    private var onClickItem: OnItemClickListener? = null
    private var selectedPosition = 0

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setClickListener(listener: OnItemClickListener) {
        this.onClickItem = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        /*val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_leader_board_list, parent, false)
        return RewardsViewHolder(itemView)*/
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            STConstants.ITEM -> ThemeViewHolder(
                inflater.inflate(R.layout.list_item_theme, parent, false)
            )
            else -> STLoadingViewHolder(
                this,
                inflater.inflate(R.layout.list_item_progress, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            STConstants.ITEM -> {
                if (position == 0) {
                    holder.itemView.themeImage.setImageResource(R.drawable.default_theme_bg)
                } else {
                    holder.itemView.themeImage.setImageResource(R.drawable.space_race_theme_bg)
                }
                if (selectedPosition == position) {
                    holder.itemView.imageBg.isSelected = true
                    holder.itemView.checked.visible()
                } else {
                    holder.itemView.imageBg.isSelected = false
                    holder.itemView.checked.gone()
                }
                holder.itemView.setOnClickListener {
                    selectedPosition = holder.adapterPosition
                    notifyDataSetChanged()
                    onClickItem?.onItemClick(selectedPosition)
                }
            }
            STConstants.LOADING -> {
                (holder as STLoadingViewHolder).bindViews(retryPageLoad, errorMsg, null)
            }
        }
    }

    override fun getItemCount(): Int {
        return 2
    }

    class ThemeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
