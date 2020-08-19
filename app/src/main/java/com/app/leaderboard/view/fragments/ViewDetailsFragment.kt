package com.app.leaderboard.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.leaderboard.R
import com.app.leaderboard.databinding.ViewDetailsFragmentBinding
import com.app.leaderboard.model.CompanyListResponse
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.android.material.bottomsheet.BottomSheetDialogFragment



class ViewDetailsFragment : BottomSheetDialogFragment(){
    private var viewDetailsFragmentBinding: ViewDetailsFragmentBinding? = null
    var dataList: CompanyListResponse.App? = null



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDetailsFragmentBinding =
            ViewDetailsFragmentBinding.inflate(LayoutInflater.from(context), container, false)
        return viewDetailsFragmentBinding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if(arguments!=null) {
            dataList = arguments!!.getSerializable("data_to_view") as CompanyListResponse.App
        }
        viewDetailsFragmentBinding!!.companynameText.text = dataList!!.name
        viewDetailsFragmentBinding!!.totalSalesText.text = "GBP "+dataList!!.data.total_sale.total.toString()

        /*entries.add(Entry(20f, dataList!!.data.total_sale.month_wise.jan.toFloat()))
        entries.add(Entry(40f, dataList!!.data.total_sale.month_wise.feb.toFloat()))
        entries.add(Entry(60f, dataList!!.data.total_sale.month_wise.mar.toFloat()))
        entries.add(Entry(80f, dataList!!.data.total_sale.month_wise.apr.toFloat()))
        entries.add(Entry(100f, dataList!!.data.total_sale.month_wise.may.toFloat()))
        entries.add(Entry(120f, dataList!!.data.total_sale.month_wise.jun.toFloat()))
        displayData()*/

        viewDetailsFragmentBinding!!.moneyClick.setOnClickListener {
            val entries = ArrayList<Entry>()
            Log.d("sales",dataList!!.data.total_sale.month_wise.toString())

            entries.add(Entry(20f, dataList!!.data.total_sale.month_wise.jan.toFloat()))
            entries.add(Entry(40f, dataList!!.data.total_sale.month_wise.feb.toFloat()))
            entries.add(Entry(60f, dataList!!.data.total_sale.month_wise.mar.toFloat()))
            entries.add(Entry(80f, dataList!!.data.total_sale.month_wise.apr.toFloat()))
            entries.add(Entry(100f, dataList!!.data.total_sale.month_wise.may.toFloat()))
            entries.add(Entry(120f, dataList!!.data.total_sale.month_wise.jun.toFloat()))
            displayData(entries)
        }

        viewDetailsFragmentBinding!!.shopClick.setOnClickListener {
            val entries = ArrayList<Entry>()
            Log.d("shops",dataList!!.data.add_to_cart.month_wise.toString())
            Log.d("shops",dataList!!.data.add_to_cart.month_wise.jan.toString())
            entries.add(Entry(20f, dataList!!.data.add_to_cart.month_wise.jan.toFloat()))
            entries.add(Entry(40f, dataList!!.data.add_to_cart.month_wise.feb.toFloat()))
            entries.add(Entry(60f, dataList!!.data.add_to_cart.month_wise.mar.toFloat()))
            entries.add(Entry(80f, dataList!!.data.add_to_cart.month_wise.apr.toFloat()))
            entries.add(Entry(100f, dataList!!.data.add_to_cart.month_wise.may.toFloat()))
            entries.add(Entry(120f, dataList!!.data.add_to_cart.month_wise.jun.toFloat()))
            displayData(entries)
        }

        viewDetailsFragmentBinding!!.sessionClick.setOnClickListener {
            val entries = ArrayList<Entry>()
            entries.add(Entry(20f, dataList!!.data.sessions.month_wise.jan.toFloat()))
            entries.add(Entry(40f, dataList!!.data.sessions.month_wise.feb.toFloat()))
            entries.add(Entry(60f, dataList!!.data.sessions.month_wise.mar.toFloat()))
            entries.add(Entry(80f, dataList!!.data.sessions.month_wise.apr.toFloat()))
            entries.add(Entry(100f, dataList!!.data.sessions.month_wise.may.toFloat()))
            entries.add(Entry(120f, dataList!!.data.sessions.month_wise.jun.toFloat()))
            displayData(entries)
        }

        viewDetailsFragmentBinding!!.downloadClick.setOnClickListener {
            val entries = ArrayList<Entry>()
            entries.add(Entry(20f, dataList!!.data.downloads.month_wise.jan.toFloat()))
            entries.add(Entry(40f, dataList!!.data.downloads.month_wise.feb.toFloat()))
            entries.add(Entry(60f, dataList!!.data.downloads.month_wise.mar.toFloat()))
            entries.add(Entry(80f, dataList!!.data.downloads.month_wise.apr.toFloat()))
            entries.add(Entry(100f, dataList!!.data.downloads.month_wise.may.toFloat()))
            entries.add(Entry(120f, dataList!!.data.downloads.month_wise.jun.toFloat()))
            displayData(entries)
        }

    }

    fun displayData(entries : ArrayList<Entry>){
        val vl = LineDataSet(entries, "Month Wise")
        vl.setDrawValues(false)
        vl.setDrawFilled(true)
        vl.lineWidth = 3f
        vl.fillColor = R.color.dark_gray
        vl.fillAlpha = R.color.black
        viewDetailsFragmentBinding!!.lineChart.xAxis.labelRotationAngle = 0f
        viewDetailsFragmentBinding!!.lineChart.data = LineData(vl)
        viewDetailsFragmentBinding!!.lineChart.setTouchEnabled(true)
        viewDetailsFragmentBinding!!.lineChart.setPinchZoom(true)
        viewDetailsFragmentBinding!!.lineChart.description.text = "Month"
        viewDetailsFragmentBinding!!.lineChart.setNoDataText("No forex yet!")
        viewDetailsFragmentBinding!!.lineChart.animateX(1000, Easing.EaseInExpo)
    }





}