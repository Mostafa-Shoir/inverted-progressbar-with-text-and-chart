package com.taskfordigis.views

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.Legend.LegendForm
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.taskfordigis.R
import com.taskfordigis.models.DataModel
import kotlinx.android.synthetic.main.activity_chart.*
import java.util.*

class ChartActivity : AppCompatActivity() {

    var dataModels = ArrayList<DataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)
        setListener()

        dataModels = intent.getSerializableExtra("dataModels") as ArrayList<DataModel>
        if (dataModels.size > 0) {
            chartForRSRP()
            setDataRSRP()
            chartForRSRQ()
            setDataRSRQ()
            chartForSINR()
            setDataSINR()
        }
    }

    fun setListener(){
        ivBackActivityLineChart.setOnClickListener {
            onBackPressed()
        }
    }

    fun chartForRSRP(){
        chartRSRP.description.isEnabled = false
        chartRSRP.setTouchEnabled(true)
        chartRSRP.dragDecelerationFrictionCoef = 0.9f
        chartRSRP.isDragEnabled = true
        chartRSRP.setScaleEnabled(true)
        chartRSRP.setDrawGridBackground(false)
        chartRSRP.isHighlightPerDragEnabled = false
        chartRSRP.setPinchZoom(true)
        chartRSRP.setBackgroundColor(Color.LTGRAY)
        chartRSRP.animateX(1500)
        val l = chartRSRP.legend

        // modify the legend ...
        l.form = LegendForm.LINE
        l.textSize = 11f
        l.textColor = ColorTemplate.getHoloBlue()
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)

        // set time into xAxis and move it to bottom
        val xAxis = chartRSRP.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.axisMinimum = 0f
        xAxis.granularity = 1f
//        xAxis.axisMinimum = dataModels[0].timeValue.toFloat()
        xAxis.valueFormatter = object : IndexAxisValueFormatter() {
            override fun getFormattedValue(value: Float): String {
//                for (i in dataModels.indices) {
//                    if (value == dataModels[i].timeValue.toFloat()) {
//                        return dataModels[i].timeLabel
//                    }
//                }
//                return ""
                return dataModels[value.toInt() % dataModels.size].timeLabel
            }
        }

        // get max and min value  from list
        val maxObj = dataModels.maxWithOrNull(Comparator { a, b -> a.rSRP!!.compareTo(b.rSRP!!) })
        val minObj = dataModels.minWithOrNull(Comparator {a, b -> a.rSRP!!.compareTo(b.rSRP!!)})
        val maxValue = maxObj!!.rSRP!!.toFloat()
        val minValue = minObj!!.rSRP!!.toFloat()

        val leftAxis = chartRSRP.axisLeft
        leftAxis.textColor = ColorTemplate.getHoloBlue()
        leftAxis.axisMaximum = maxValue
        leftAxis.axisMinimum = minValue
        leftAxis.setDrawGridLines(true)
        leftAxis.isGranularityEnabled = true

        val rightAxis = chartRSRP.axisRight
        rightAxis.textColor = Color.TRANSPARENT
        rightAxis.textSize = 0f
        rightAxis.setDrawLabels(false)
        rightAxis.labelCount = 0
        rightAxis.axisMaximum = 900f
        rightAxis.axisMinimum = -200f
        rightAxis.setDrawGridLines(false)
        rightAxis.setDrawZeroLine(false)
        rightAxis.isGranularityEnabled = false
    }

    fun setDataRSRP(){
        val values1 = ArrayList<Entry>()

        for (i in dataModels.indices) {
            values1.add(Entry(i.toFloat(), dataModels[i].rSRP!!.toFloat()))
        }

        val set1: LineDataSet
        // create a dataset and give it a type
        set1 = LineDataSet(values1, "RSRP P")
        set1.axisDependency = AxisDependency.LEFT
        set1.color = ColorTemplate.getHoloBlue()
        set1.setCircleColor(ColorTemplate.getHoloBlue())
        set1.lineWidth = 2f
        set1.circleRadius = 3f
        set1.fillAlpha = 65
        set1.fillColor = ColorTemplate.getHoloBlue()
        set1.highLightColor = Color.rgb(244, 117, 117)
        set1.setDrawCircleHole(false)
        set1.values = values1

        // create a data object with the data sets
        val data = LineData(set1)
        data.setValueTextColor(Color.WHITE)
        data.setValueTextSize(0f)

        // set data
        chartRSRP.data = data
    }

    fun chartForRSRQ(){
        chartRSRQ.description.isEnabled = false
        chartRSRQ.setTouchEnabled(true)
        chartRSRQ.dragDecelerationFrictionCoef = 0.9f
        chartRSRQ.isDragEnabled = true
        chartRSRQ.setScaleEnabled(true)
        chartRSRQ.setDrawGridBackground(false)
        chartRSRQ.isHighlightPerDragEnabled = false
        chartRSRQ.setPinchZoom(true)
        chartRSRQ.setBackgroundColor(Color.LTGRAY)
        chartRSRQ.animateX(1500)

        val l = chartRSRQ.legend
        l.form = LegendForm.LINE
        l.textSize = 11f
        l.textColor = R.color.red
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)

        // set time into xAxis
        val xAxis = chartRSRQ.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.axisMinimum = 0f
        xAxis.granularity = 1f
        xAxis.valueFormatter = object : IndexAxisValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return dataModels[value.toInt() % dataModels.size].timeLabel
            }
        }

        // get max and min value  from list
        val maxObj = dataModels.maxWithOrNull { a, b -> a.rSRQ!!.compareTo(b.rSRQ!!) }
        val minObj = dataModels.minWithOrNull { a, b -> a.rSRQ!!.compareTo(b.rSRQ!!) }
        val maxValue = maxObj!!.rSRQ!!.toFloat()
        val minValue = minObj!!.rSRQ!!.toFloat()

        val leftAxis = chartRSRQ.axisLeft
        leftAxis.textColor = R.color.red
        leftAxis.axisMaximum = maxValue
        leftAxis.axisMinimum = minValue
        leftAxis.setDrawGridLines(true)
        leftAxis.isGranularityEnabled = true

        val rightAxis = chartRSRQ.axisRight
        rightAxis.textColor = Color.TRANSPARENT
        rightAxis.textSize = 0f
        rightAxis.setDrawLabels(false)
        rightAxis.labelCount = 0
        rightAxis.axisMaximum = 900f
        rightAxis.axisMinimum = -200f
        rightAxis.setDrawGridLines(false)
        rightAxis.setDrawZeroLine(false)
        rightAxis.isGranularityEnabled = false
    }

    fun setDataRSRQ(){
        val values1 = ArrayList<Entry>()

        for (i in dataModels.indices) {
            values1.add(Entry(i.toFloat(), dataModels[i].rSRQ!!.toFloat()))
        }

        val set1: LineDataSet
        // create a dataset and give it a type
        set1 = LineDataSet(values1, "RSRQ P")
        set1.axisDependency = AxisDependency.LEFT
        set1.color = R.color.red
        set1.setCircleColor(R.color.red)
        set1.lineWidth = 2f
        set1.circleRadius = 3f
        set1.fillAlpha = 65
        set1.fillColor = R.color.red
        set1.highLightColor = Color.rgb(244, 117, 117)
        set1.setDrawCircleHole(false)
        set1.values = values1

        // create a data object with the data sets
        val data = LineData(set1)
        data.setValueTextColor(Color.WHITE)
        data.setValueTextSize(0f)

        // set data
        chartRSRQ.data = data
    }

    fun chartForSINR(){
        chartSINR.description.isEnabled = false
        chartSINR.setTouchEnabled(true)
        chartSINR.dragDecelerationFrictionCoef = 0.9f
        chartSINR.isDragEnabled = true
        chartSINR.setScaleEnabled(true)
        chartSINR.setDrawGridBackground(false)
        chartSINR.isHighlightPerDragEnabled = false
        chartSINR.setPinchZoom(true)
        chartSINR.setBackgroundColor(Color.LTGRAY)
        chartSINR.animateX(1500)

        val l = chartSINR.legend
        l.form = LegendForm.LINE
        l.textSize = 11f
        l.textColor = R.color.dark_green
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)

        // set show time into xAxis
        val xAxis = chartSINR.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.axisMinimum = 0f
        xAxis.granularity = 1f
        xAxis.valueFormatter = object : IndexAxisValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return dataModels[value.toInt() % dataModels.size].timeLabel
            }
        }

        // get max and minvalue  from list
        val maxObj = dataModels.maxWithOrNull { a, b -> a.sINR!!.compareTo(b.sINR!!) }
        val minObj = dataModels.minWithOrNull { a, b -> a.sINR!!.compareTo(b.sINR!!) }
        val maxValue = maxObj!!.sINR!!.toFloat()
        val minValue = minObj!!.sINR!!.toFloat()

        val leftAxis = chartSINR.axisLeft
        leftAxis.textColor = R.color.dark_green
        leftAxis.axisMaximum = maxValue
        leftAxis.axisMinimum = minValue
        leftAxis.setDrawGridLines(true)
        leftAxis.isGranularityEnabled = false

        val rightAxis = chartSINR.axisRight
        rightAxis.textColor = Color.TRANSPARENT
        rightAxis.textSize = 0f
        rightAxis.setDrawLabels(false)
        rightAxis.labelCount = 0
        rightAxis.axisMaximum = 900f
        rightAxis.axisMinimum = -200f
        rightAxis.setDrawGridLines(false)
        rightAxis.setDrawZeroLine(false)
        rightAxis.isGranularityEnabled = false
    }

    fun setDataSINR(){
        val values1 = ArrayList<Entry>()

        for (i in dataModels.indices) {
            values1.add(Entry(i.toFloat(), dataModels[i].sINR!!.toFloat()))
        }

        val set1: LineDataSet
        // create a data set and give it a type
        set1 = LineDataSet(values1, "SINR P")
        set1.axisDependency = AxisDependency.LEFT
        set1.color = R.color.dark_green
        set1.setCircleColor(R.color.dark_green)
        set1.lineWidth = 2f
        set1.circleRadius = 3f
        set1.fillAlpha = 65
        set1.fillColor = R.color.dark_green
        set1.highLightColor = Color.rgb(244, 117, 117)
        set1.setDrawCircleHole(false)
        set1.values = values1

        // create a data object with the data sets
        val data = LineData(set1)
        data.setValueTextColor(Color.WHITE)
        data.setValueTextSize(0f)

        // set data
        chartSINR.data = data
    }


}