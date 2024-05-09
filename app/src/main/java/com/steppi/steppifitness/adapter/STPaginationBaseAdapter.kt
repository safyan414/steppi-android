package com.steppi.steppifitness.adapter

import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.user.RedeemedRewards

abstract class STPaginationBaseAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    protected var mDataSet: ArrayList<Any> = ArrayList()
    protected var isLoadingAdded = false
    protected var retryPageLoad = false
    protected var errorMsg: String? = null

    override fun getItemCount(): Int {
        return mDataSet.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == mDataSet.size - 1 && isLoadingAdded) STConstants.LOADING else STConstants.ITEM
    }


    /*override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return onCreateVH(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        onBindVH(holder, position)
    }

    abstract fun onBindVH(itemView: RecyclerView.ViewHolder, position: Int)
    abstract fun onCreateVH(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder*/

    /*
        Helpers - Pagination
   _________________________________________________________________________________________________
    */
    fun add(r: RedeemedRewards?) {
        mDataSet.add(r!!)
        notifyItemInserted(mDataSet.size - 1)
    }

    fun remove(r: Any?) {
        val position: Int = mDataSet.indexOf(r)
        if (position > -1) {
            mDataSet.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun clear() {
        isLoadingAdded = false
        while (itemCount > 0) {
            remove(getItem(0))
        }
    }

    fun removeItem(position: Int) {
        isLoadingAdded = false
        if (itemCount > 0) {
            remove(getItem(position))
        }
    }

    fun isEmpty(): Boolean {
        return itemCount == 0
    }

    fun addLoadingFooter() {
        isLoadingAdded = true
        add(RedeemedRewards())
    }

    fun removeLoadingFooter() {
        isLoadingAdded = false
        val position: Int = mDataSet.size - 1
        val result: Any? = getItem(position)
        if (result != null) {
            mDataSet.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun getItem(position: Int): Any? {
        return mDataSet[position]
    }

    fun getListSize(): Int {
        return mDataSet.size
    }

    fun getList(): Any? {
        return mDataSet
    }

    /**
     * Displays Pagination retry footer view along with appropriate errorMsg
     *
     * @param show
     * @param errorMsg to display if page load fails
     */
    fun showRetry(show: Boolean, @Nullable errorMsg: String?) {
        retryPageLoad = show
        notifyItemChanged(mDataSet.size - 1)
        if (errorMsg != null) this.errorMsg = errorMsg
    }
}
