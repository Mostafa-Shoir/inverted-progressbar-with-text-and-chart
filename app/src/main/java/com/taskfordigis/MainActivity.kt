package com.taskfordigis

import JsonParser
import Utils
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.taskfordigis.models.DataModel
import com.taskfordigis.models.LegendModel
import com.taskfordigis.viewModels.DataViewModel
import com.taskfordigis.views.ChartActivity
import com.taskfordigis.views.adapter.DataAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    var legendModel: LegendModel? = null
    private val viewModel: DataViewModel by lazy {
        ViewModelProviders.of(this).get(DataViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jsonFileString = Utils().getJsonFromAssets(this, "Legend.json")
        Log.i("data", jsonFileString!!)
        legendModel = JsonParser().getFileJsonParse(jsonFileString)
        initiateRecycleData()

        fab.setOnClickListener {
            val intent = Intent(this, ChartActivity::class.java)
            intent.putExtra("dataModels", dataModels)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }


    }

    lateinit var dataAdapter: DataAdapter
    var dataModels = ArrayList<DataModel>()

    fun initiateRecycleData() {
        dataAdapter = DataAdapter(legendModel!!)
        var layoutManager = LinearLayoutManager(this)
        recyclerViewData.layoutManager = layoutManager
        recyclerViewData.setHasFixedSize(false)
        recyclerViewData.isNestedScrollingEnabled = false
        recyclerViewData.adapter = dataAdapter

        getData()
    }

    private fun getData() {
        viewModel.fitchData()
            .observe(this, Observer {
                if (it != null) {
                    val c = Calendar.getInstance()
                    val minute = c.get(Calendar.MINUTE)
                    val second = c.get(Calendar.SECOND)

                    var timeValue = second * 60 + minute * 60
                    var timeLabel = "$minute:$second"

                    val dataModel: DataModel = DataModel()
                    dataModel.rSRP = it.rSRP
                    dataModel.rSRQ = it.rSRQ
                    dataModel.sINR = it.sINR
                    dataModel.timeValue = timeValue
                    dataModel.timeLabel = timeLabel

                    dataModels.add(dataModel)
                    dataAdapter.setData(dataModels, dataModels.size - 1)
//                    recyclerViewData.smoothScrollToPosition(dataModels.size - 1)
                    counterForReCallRequest()
                }
            })
    }

    var countDownTimer: CountDownTimer? = null

    fun counterForReCallRequest() {
        countDownTimer = object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }
            override fun onFinish() {
                getData()
            }
        }.start()
    }

}