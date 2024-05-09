package com.steppi.steppifitness.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.steppi.steppifitness.adapter.redeemed.STRedeemedDigitalAdapter
import com.steppi.steppifitness.utils.PaginationAdapterCallback
import kotlinx.android.synthetic.main.list_item_progress.view.*

class STLoadingViewHolder(private var myAdapter: Any, itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindViews(
        retryPageLoad: Boolean,
        errorMsg: String?,
        mCallback: PaginationAdapterCallback? = null
    ) {
        if (retryPageLoad) {
            itemView.loadmore_errorlayout?.visibility = View.VISIBLE
            itemView.loadmore_progress?.visibility = View.GONE
            itemView.loadmore_errortxt?.text = errorMsg ?: "Something went wrong!!!"
        } else {
            itemView.loadmore_errorlayout?.visibility = View.GONE
            itemView.loadmore_progress?.visibility = View.VISIBLE
        }
        itemView.loadmore_errorlayout?.setOnClickListener {
            mCallback?.let {
                when (myAdapter) {
                    is STRedeemedDigitalAdapter -> (myAdapter as STRedeemedDigitalAdapter)
                        .showRetry(false, null)
                }
                mCallback.retryPageLoad()
            }

        }
    }
}
