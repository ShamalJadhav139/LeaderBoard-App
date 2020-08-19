package com.app.leaderboard.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.leaderboard.adapters.HomeAdapter
import com.app.leaderboard.constants.ApiConstants
import com.app.leaderboard.databinding.HomeFragmentBinding
import com.app.leaderboard.model.CompanyListResponse
import com.app.leaderboard.networkContracter.MainActivityPresenter
import com.app.leaderboard.networkContracter.MainContractor
import com.app.leaderboard.view.appBase.MainActivity
import com.google.gson.Gson
import java.io.Serializable


class HomeFragment : Fragment(), MainContractor.View {
    private var homeFragmentBinding: HomeFragmentBinding? = null
    private var presenter: MainContractor.Presenter? = null
    private var homeAdapter: HomeAdapter? = null
    var dataList: List<CompanyListResponse.App>? = null
    var bottomSheetFragment = SortByFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeFragmentBinding =
            HomeFragmentBinding.inflate(LayoutInflater.from(context), container, false)
        presenter = MainActivityPresenter(this)
        return homeFragmentBinding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        callGetBookApi()

        homeFragmentBinding!!.companyList.layoutManager = LinearLayoutManager(context)
        homeAdapter = HomeAdapter()
        homeFragmentBinding!!.companyList.adapter = homeAdapter
        (context as MainActivity).toolBarBtn!!.setOnClickListener {
            showBottomSheetDialogFragment(dataList!!)
        }

        bottomSheetFragment.callback = {
            homeAdapter!!.setData(it)
        }

    }
    private fun showBottomSheetDialogFragment(data: List<CompanyListResponse.App>) {
        var bundle = Bundle()
        bundle.putSerializable("data_to_sort", data as Serializable)
        bottomSheetFragment.arguments = bundle
        bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)

    }
    fun callGetBookApi() {
        presenter!!.onClick(
            ApiConstants.getCompany,
            emptyArray(),
            requireContext(),
            true
        )
    }

    override fun setViewData(data: String, view: ApiConstants) {
        when (view) {
            ApiConstants.getCompany -> {
                val response = Gson().fromJson(data, CompanyListResponse::class.java)
                if (response != null) {
                    dataList = response.apps
                    homeAdapter!!.setData(response.apps)
                } else {
                    //showToast("Data not found")
                }
            }
        }
    }


}