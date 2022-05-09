package com.app.peyza.views

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.peyza.R
import com.app.peyza.networks.Status
import com.app.peyza.views.adapters.CasesListAdapter
import com.app.peyza.views.adapters.StatesListAdapter
import com.app.peyza.views.adapters.TestedListAdapter
import com.app.peyza.views.localDatabase.DBHelper
import com.app.peyza.views.models.CovidData
import com.app.peyza.views.modules.loginModule.LoginViewModel
import com.kaopiz.kprogresshud.KProgressHUD
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_nav_hold.*

@AndroidEntryPoint
class NavHoldActivity : AppCompatActivity(), ItemClicks {

    var caseAdapter: CasesListAdapter? = null
    var statesAdapter: StatesListAdapter? = null
    var testedAdapter: TestedListAdapter? = null

    private val viewModel: LoginViewModel by viewModels()

    fun progressBar(): KProgressHUD {
        return KProgressHUD.create(this)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setLabel(getString(R.string.loading_please_wait))
            .setCancellable(false)
            .setAnimationSpeed(2)
            .setDimAmount(0.5f)
    }


    var helperClass: DBHelper? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_hold)

        init()
        listeners()
        setupAdapter()
        addObservers()
    }

    fun init() {
        helperClass = DBHelper(this)
    }

    fun setupAdapter() {
        caseAdapter = CasesListAdapter(this, this)
        statesAdapter = StatesListAdapter(this, this)
        testedAdapter = TestedListAdapter(this, this)

        rvMain.adapter = caseAdapter

        if (helperClass?.getCasesData()?.isNullOrEmpty() == true)
            viewModel.covidDataApi()
        else bindDataFromLocal()
    }

    fun addObservers() {
        val progressBar = progressBar()
        viewModel.covidData.observe(this) {
            when (it.code) {
                Status.SUCCESS -> {
                    progressBar.dismiss()
                    addToLocal(it.data)
                }
                Status.ERROR -> {
                    progressBar.dismiss()
                }
                Status.LOADING -> {
                    progressBar.show()
                }
            }
        }
    }

    fun addToLocal(data: CovidData?) {
        data?.cases_time_series?.let { helperClass?.addCases(it) }
        data?.statewise?.let { helperClass?.addStates(it) }
        data?.tested?.let { helperClass?.addTested(it) }
        bindDataFromLocal()
    }

    fun bindDataFromLocal() {
        helperClass?.getCasesData()?.let { caseAdapter?.addList(it) }
        helperClass?.getStatesData()?.let { statesAdapter?.addList(it) }
        helperClass?.getTestedData()?.let { testedAdapter?.addList(it) }
    }

    fun listeners() {
        bottomView?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_cases -> {
                    rvMain.adapter = caseAdapter
                    true
                }
                R.id.navigation_states -> {
                    rvMain.adapter = statesAdapter
                    true
                }
                else -> {
                    rvMain.adapter = testedAdapter
                    true
                }
            }
        }
    }

    override fun onItemClicked(position: Int) {
        when (rvMain.adapter) {
            is StatesListAdapter -> {
                helperClass?.updateTable(
                    (rvMain.adapter as StatesListAdapter).listData.get(position).id,
                    (rvMain.adapter as StatesListAdapter).listData.get(position).isFav,
                    DBHelper.STATES_TABLE_NAME
                )
            }
            is TestedListAdapter -> {
                helperClass?.updateTable(
                    (rvMain.adapter as TestedListAdapter).listData.get(position).id,
                    (rvMain.adapter as TestedListAdapter).listData.get(position).isFav,
                    DBHelper.TESTED_TABLE_NAME
                )
            }
            else -> {
                helperClass?.updateTable(
                    (rvMain.adapter as CasesListAdapter).listData.get(position).id,
                    (rvMain.adapter as CasesListAdapter).listData.get(position).isFav,
                    DBHelper.CASES_TABLE_NAME
                )
            }
        }
    }
}