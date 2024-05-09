package com.steppi.steppifitness.adapter.country

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.steppi.steppifitness.R
import com.steppi.steppifitness.model.STCountryData
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.list_item_country.view.*

class STCountryAdapter(private val context: Context,
    private val countryList: ArrayList<STCountryData>,
    private val needShowDialCode: Boolean,
    private var selectedCountry: STCountryData?,
    val listItemOnClick: (Any) -> Unit
) : RecyclerView.Adapter<STCountryAdapter.CountryViewHolder>() {
    private var filteredList: ArrayList<STCountryData>? = null

    init {
        filteredList = ArrayList()
        filteredList?.addAll(countryList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_country, parent, false)

        return CountryViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return filteredList?.size ?: 0
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        filteredList?.let {
            val country = it[position]
            if (needShowDialCode) {
                STUtils.setValueToView(
                    holder.itemView.countryName,
                    "(".plus(country.phoneCode).plus(")").plus(" ").plus(country.name)
                )
                holder.itemView.countryFlag.load(context, country.flag)
            } else {
                STUtils.setValueToView(holder.itemView.countryName, country.name)
                holder.itemView.countryFlag.load(context, country.flag)
            }
            selectedCountry?.let { countryData ->
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

    fun setCountryList(countryList: ArrayList<STCountryData>) {
        this.filteredList?.clear()
        this.filteredList?.addAll(countryList)
        notifyDataSetChanged()
    }

    fun getCountryList(): List<STCountryData>? {
        return countryList
    }

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
