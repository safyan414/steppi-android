package com.steppi.steppifitness.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.steppi.steppifitness.R
import com.steppi.steppifitness.network.response.category.STMerchantStoresListData
import com.steppi.steppifitness.utils.STUtils
import com.steppi.steppifitness.utils.invisible
import com.steppi.steppifitness.utils.visible
import kotlinx.android.synthetic.main.list_item_store_location.view.*

class STStoreLocationAdapter(
    private val context: Context,
    private val countryList: ArrayList<STMerchantStoresListData>,
    private var selectedMerchantStore: STMerchantStoresListData?,
    val listItemOnClick: (Any) -> Unit
) : RecyclerView.Adapter<STStoreLocationAdapter.StoreLocationViewHolder>() {
    private var filteredList: ArrayList<STMerchantStoresListData>? = null

    init {
        filteredList = ArrayList()
        filteredList?.addAll(countryList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreLocationViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_store_location, parent, false)
        return StoreLocationViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return filteredList?.size ?: 0
    }

    override fun onBindViewHolder(holder: StoreLocationViewHolder, position: Int) {
        filteredList?.let {
            val country = it[position]
            STUtils.setValueToView(holder.itemView.locationName, country.name)
            selectedMerchantStore?.let { countryData ->
                if (country.id.equals(countryData.id, true)) {
                    holder.itemView.selectedImage.visible()
                } else {
                    holder.itemView.selectedImage.invisible()
                }
            } ?: run {
                holder.itemView.selectedImage.invisible()
            }
            holder.itemView.setOnClickListener {
                listItemOnClick(country)
            }
        }
    }

    fun setLocationList(countryList: ArrayList<STMerchantStoresListData>) {
        this.filteredList?.clear()
        this.filteredList?.addAll(countryList)
        notifyDataSetChanged()
    }

    fun getLocationList(): ArrayList<STMerchantStoresListData> {
        return countryList
    }

    class StoreLocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
