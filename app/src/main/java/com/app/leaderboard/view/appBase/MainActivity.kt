package com.app.leaderboard.view.appBase

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.app.leaderboard.R
import com.app.leaderboard.constants.IS_NETWORK_AVAILABLE
import com.app.leaderboard.constants.NETWORK
import com.app.leaderboard.databinding.ActivityMainDrawerBinding
import com.app.leaderboard.model.CompanyListResponse
import com.app.leaderboard.view.fragments.HomeFragment
import com.app.leaderboard.view.fragments.SortByFragment
import com.app.leaderboard.view.fragments.ViewDetailsFragment
import kotlinx.android.synthetic.main.toolbar.view.*

class MainActivity : BaseActivity() {
    private var mContext: Context? = null
    private var binding: ActivityMainDrawerBinding? = null
    private var toolBarTitle: TextView? = null
    var toolBarBtn: TextView? = null
    private var isDoubleBackPress = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_drawer)
        mContext = this
        initAllFunctions()

    }

    private fun initAllFunctions() {
        setSupportActionBar(binding!!.toolbarLayout.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        toolBarTitle = binding!!.toolbarLayout.toolBarTitle
        toolBarBtn = binding!!.toolbarLayout.sortBtn
        backStackHandel()
        initHomeFragment()
    }

    private fun backStackHandel() {
        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount == 0) {
                binding!!.toolbarLayout.visibility = View.GONE
            } else {
                supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1)
                    .name?.let {
                        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back)
                        setToolBarTitle(it)
                    }
            }
        }

    }


    private fun setToolBarTitle(title: String) {
        toolBarTitle!!.text = title
    }

    private fun launchFragment(fragment: Fragment, fragmentTitle: String) {
        hideSoftKeyboard()
        if (isNetworkAvailable() || fragmentTitle == getString(R.string.no_network)) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.content_frame, fragment, fragmentTitle)
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
                .addToBackStack(fragmentTitle)
                .commitAllowingStateLoss()
        } else {
            showToast(getString(R.string.internet_connection_failed))
        }
    }

     fun initHomeFragment() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            popAllFragmentFromBackStack()
        } else {
            val fragment = HomeFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
                .commitAllowingStateLoss()
        }
    }

    fun launchSortByFragment(){

        launchFragment(SortByFragment(),resources.getString(R.string.app_name))
    }


    fun showViewSheetDialogFragment(data: CompanyListResponse.App) {
        var bottomSheetFragment = ViewDetailsFragment()
        var bundle = Bundle()
        bundle.putSerializable("data_to_view", data)
        bottomSheetFragment.arguments = bundle
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }


    /* --------------- fragment handlers --------------------------- */
    open fun popAllFragmentFromBackStack() {
        val fm = supportFragmentManager
        for (i in 0 until fm.backStackEntryCount) {
            fm.popBackStack()
        }
    }

    override fun onEventReceived(eventName: String?, bundle: Bundle?) {
        super.onEventReceived(eventName, bundle)
        if (eventName == NETWORK) {
            if (bundle!!.getBoolean(IS_NETWORK_AVAILABLE)) {
                if (toolBarTitle!!.text.toString() == resources.getString(R.string.no_network)) {
                    popAllFragmentFromBackStack()
                }
            } else {
                val fragmentCount = supportFragmentManager.backStackEntryCount
                if (fragmentCount == 0) {
                    launchFragment(NoNetworkFragment(), getString(R.string.no_network))
                } else {
                    if (!supportFragmentManager.getBackStackEntryAt(fragmentCount - 1).name.equals(
                            getString(R.string.no_network),
                            true
                        )
                    ) {
                        launchFragment(NoNetworkFragment(), getString(R.string.no_network))
                    }
                }

            }
        }
    }



    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            if (isDoubleBackPress) {
                super.onBackPressed()
                return
            }
            this.isDoubleBackPress = true
            showToast(getString(R.string.app_close_message))
            Handler().postDelayed({
                isDoubleBackPress = false
            }, 2000)
        } else {
            if (toolBarTitle!!.text != getString(R.string.no_network)) {
                super.onBackPressed()
            }
            return
        }
    }



}
