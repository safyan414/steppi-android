package com.steppi.steppifitness.adapter

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.steppi.steppifitness.R
import com.steppi.steppifitness.network.response.category.STCategory
import com.steppi.steppifitness.utils.STUtils
import com.steppi.steppifitness.utils.load
import kotlinx.android.synthetic.main.list_item_event.view.*

class STItemEventListAdapter(
    private val activityContext: Context,
    private val isMenu: Boolean = false
) : RecyclerView.Adapter<STItemEventListAdapter.EventViewHolder>() {
    private var menuList: ArrayList<STCategory>? = null
    init {
//        setMenuList()
    }
    private var selectedReward: STCategory? = null
    private var inActiveColor = R.color.edit_text_bg_stroke_color
    private var itemClick: ((STCategory) -> Unit)? = null
    private var selectedPosition: Int? = 0
    private var onClickItem: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(selectedReward: STCategory?, selectedPosition: Int?)
    }

    fun setClickListener(listener: OnItemClickListener) {
        this.onClickItem = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_event, parent, false)

        return EventViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return menuList?.size ?: 0
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bindView(position)
    }

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(position: Int) {
            val menuModel = menuList!![position]
            itemView.image.load(itemView.context, menuModel.logo)
            STUtils.setValueToView(itemView.title, menuModel.name)
            val background = itemView.background as GradientDrawable
            background.setColor(
                STUtils.getColor(menuModel.colorCode) ?: STUtils.getColor(
                    activityContext,
                    R.color.button_bg_enabled_color
                )
            )
            setIfMenu(menuModel)
            itemView.setOnClickListener {
                onClickItem?.onItemClick(menuModel, position)
//                itemClick?.invoke(menuModel)
                selectedReward = menuModel
                notifyDataSetChanged()
            }
        }

        private fun setIfMenu(menuModel: STCategory) {
            if (isMenu) {
                val background = itemView.background as GradientDrawable
                if (selectedReward?.id == menuModel.id) {
                    background.setColor(
                        STUtils.getColor(menuModel.colorCode) ?: STUtils.getColor(
                            activityContext,
                            R.color.button_bg_enabled_color
                        )
                    )
                } else {
                    background.setColor(STUtils.getColor(activityContext, inActiveColor))
                }
            }
        }
    }
//    fun setOnItemClickListener(itemClick: (menuModel: STCategory) -> Unit) {
//        this.itemClick = itemClick
//    }

//    private fun setMenuList() {
//        menuList = ArrayList()
//
//        menuList?.add(STRewardMenuModel().apply {
//            id = 0
//            image = R.drawable.home_reward_featured
//            title = activityContext.getString(R.string.featured)
//            colorCode = R.color.button_bg_enabled_color
//        })
//
//        menuList?.add(STRewardMenuModel().apply {
//            id = 1
//            image = R.drawable.home_reward_food
//            title = activityContext.getString(R.string.food)
//            colorCode = R.color.orange_color
//        })
//
//        menuList?.add(STRewardMenuModel().apply {
//            id = 2
//            image = R.drawable.home_reward_activity
//            title = activityContext.getString(R.string.activities)
//            colorCode = R.color.light_blue
//        })
//
//        menuList?.add(STRewardMenuModel().apply {
//            id = 3
//            image = R.drawable.home_reward_services
//            title = activityContext.getString(R.string.services)
//            colorCode = R.color.violet_color
//        })
//
//        menuList?.add(STRewardMenuModel().apply {
//            id = 4
//            image = R.drawable.home_reward_beauty
//            title = activityContext.getString(R.string.beauty)
//            colorCode = R.color.red_color
//        })
//
//        menuList?.add(STRewardMenuModel().apply {
//            id = 5
//            image = R.drawable.home_reward_wellness
//            title = activityContext.getString(R.string.wellness)
//            colorCode = R.color.button_lang_bg_enabled_color
//        })
//    }

    fun getRewards(): ArrayList<STCategory>? {
        return menuList
    }

    fun setCategory(menuList: ArrayList<STCategory>?, selectedReward: STCategory?) {
        this.menuList = menuList
        if (null == selectedReward && !menuList.isNullOrEmpty()) {
            this.selectedReward = menuList[0]
        }
        this.selectedReward = selectedReward
        notifyDataSetChanged()
    }
}
