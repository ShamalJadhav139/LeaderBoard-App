package com.app.leaderboard.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.leaderboard.databinding.SortByFragmentBinding
import com.app.leaderboard.model.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*
import kotlin.collections.ArrayList


class SortByFragment : BottomSheetDialogFragment() {
    private var sortByFragment: SortByFragmentBinding? = null
    var dataList: ArrayList<CompanyListResponse.App>? = null
    var callback: ((ArrayList<CompanyListResponse.App>) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sortByFragment =
            SortByFragmentBinding.inflate(LayoutInflater.from(context), container, false)
        return sortByFragment!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (arguments != null) {
            dataList =
                arguments!!.getSerializable("data_to_sort") as ArrayList<CompanyListResponse.App>

        }

        sortByFragment!!.totalSalesText.setOnClickListener {
            val totalsalescomparator = TotalSalesComparator()
            Collections.sort(dataList, totalsalescomparator)
            callback!!.invoke(dataList!!)
            dismiss()
        }

        sortByFragment!!.addToCartText.setOnClickListener {
            val addtocartcomparator = AddToCartComparator()
            Collections.sort(dataList, addtocartcomparator)
            callback!!.invoke(dataList!!)
            dismiss()
        }

        sortByFragment!!.downloadsText.setOnClickListener {
            val downloadcomparator = DownloadComparator()
            Collections.sort(dataList, downloadcomparator)
            callback!!.invoke(dataList!!)
            dismiss()
        }

        sortByFragment!!.userSessionText.setOnClickListener {
            val sessionscomparator = SessionsComparator()
            Collections.sort(dataList, sessionscomparator)
            callback!!.invoke(dataList!!)
            dismiss()
        }

    }


}