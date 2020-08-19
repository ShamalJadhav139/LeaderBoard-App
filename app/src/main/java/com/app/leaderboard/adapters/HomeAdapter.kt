package com.app.leaderboard.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.leaderboard.databinding.ItemHomeBinding
import com.app.leaderboard.model.CompanyListResponse
import com.app.leaderboard.view.appBase.MainActivity


class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    lateinit var itemHomeBinding: ItemHomeBinding
    var bookList: List<CompanyListResponse.App> = emptyList()

    private lateinit var mContext: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        itemHomeBinding =
            ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        mContext = parent.context
        return ViewHolder(itemHomeBinding)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemHomeBinding = holder.itemHomeBinding
        itemHomeBinding.companynameText.text = bookList[position].name
        itemHomeBinding.totalSalesText.text =
            "GBP " + bookList[position].data.total_sale.total.toString()
        itemHomeBinding.viewDetailsBtn.setOnClickListener {
            (mContext as MainActivity).showViewSheetDialogFragment(bookList[position])
        }

    }

    fun setData(bookList: List<CompanyListResponse.App>?) {
        this.bookList = bookList!!
        notifyDataSetChanged()
    }

    class ViewHolder(val itemHomeBinding: ItemHomeBinding) :
        RecyclerView.ViewHolder(itemHomeBinding.root)

}