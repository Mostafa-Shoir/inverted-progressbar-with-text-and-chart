package com.taskfordigis.views.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar
import com.taskfordigis.R
import com.taskfordigis.models.ColorHintModel
import com.taskfordigis.models.DataModel
import com.taskfordigis.models.LegendModel
import kotlin.collections.ArrayList

class DataAdapter(var legendModel: LegendModel) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    var dataModels = ArrayList<DataModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_recycler_data,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataModels.size
    }

    fun setData(newData: ArrayList<DataModel>, position: Int) {
        dataModels = newData
        notifyItemChanged(position)
    }

    override fun onBindViewHolder(holder: DataAdapter.ViewHolder, position: Int) {

        holder.tvPrgressTitleRSRP.text = dataModels.get(position).rSRP.toString()
        holder.tvPrgressTitleRSRQ.text = dataModels.get(position).rSRQ.toString()
        holder.tvPrgressTitleSINR.text = dataModels.get(position).sINR.toString()

        setLegendColorForRSRP(position, holder)
        setLegendColorForRSRQ(position, holder)
        setLegendColorForSINR(position, holder)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var progressRSRP = itemView.findViewById<RoundCornerProgressBar>(R.id.progressRSRP)
        var progressRSRQ = itemView.findViewById<RoundCornerProgressBar>(R.id.progressRSRQ)
        var progressSINR = itemView.findViewById<RoundCornerProgressBar>(R.id.progressSINR)
        var tvPrgressTitleRSRP = itemView.findViewById<TextView>(R.id.tvPrgressTitleRSRP)
        var tvPrgressTitleRSRQ = itemView.findViewById<TextView>(R.id.tvPrgressTitleRSRQ)
        var tvPrgressTitleSINR = itemView.findViewById<TextView>(R.id.tvPrgressTitleSINR)
    }

    fun setLegendColorForRSRP(position: Int, holder: DataAdapter.ViewHolder){
        // get percentage and set it into progress
        holder.progressRSRP.setProgress(rangePercentage(dataModels.get(position).rSRP!!.toInt(), legendModel.RSRP))

        if (dataModels.get(position).rSRP!!.toInt() <= -110) {
            holder.progressRSRP.progressColor = Color.parseColor("#000A00")
        } else if (dataModels.get(position).rSRP!!.toInt() >= -60) {
            holder.progressRSRP.progressColor = Color.parseColor("#0007FF")
        } else {
            if (dataModels.get(position).rSRP!!.toInt() >= -110 && dataModels.get(position).rSRP!!.toInt() <= -100) {
                holder.progressRSRP.progressColor = Color.parseColor("#E51304")
            } else if (dataModels.get(position).rSRP!!.toInt() >= -100 && dataModels.get(position).rSRP!!.toInt() <= -90) {
                holder.progressRSRP.progressColor = Color.parseColor("#FAFD0C")
            } else if (dataModels.get(position).rSRP!!.toInt() >= -90 && dataModels.get(position).rSRP!!.toInt() <= -80) {
                holder.progressRSRP.progressColor = Color.parseColor("#02FA0E")
            } else if (dataModels.get(position).rSRP!!.toInt() >= -80 && dataModels.get(position).rSRP!!.toInt() <= -70) {
                holder.progressRSRP.progressColor = Color.parseColor("#0B440D")
            } else if (dataModels.get(position).rSRP!!.toInt() >= -70 && dataModels.get(position).rSRP!!.toInt() <= -60) {
                holder.progressRSRP.progressColor = Color.parseColor("#0EFFF8")
            }
        }


    }

    fun setLegendColorForRSRQ(position: Int, holder: DataAdapter.ViewHolder) {
        // get percentage and set it into progress
        holder.progressRSRQ.setProgress(rangePercentage(dataModels.get(position).rSRQ!!.toInt(), legendModel.RSRQ))

        if (dataModels.get(position).rSRQ!!.toInt() <= -19.5) {
            holder.progressRSRQ.progressColor = Color.parseColor("#000A00")
        } else if (dataModels.get(position).rSRQ!!.toInt() >= -3) {
            holder.progressRSRQ.progressColor = Color.parseColor("#3f7806")
        } else {
            if (dataModels.get(position).rSRQ!!.toInt() >= -19.5 && dataModels.get(position).rSRQ!!.toInt() <= -14) {
                holder.progressRSRQ.progressColor = Color.parseColor("#ff0000")
            } else if (dataModels.get(position).rSRQ!!.toInt() >= -14 && dataModels.get(position).rSRQ!!.toInt() <= -9) {
                holder.progressRSRQ.progressColor = Color.parseColor("#ffee00")
            } else if (dataModels.get(position).rSRQ!!.toInt() >= -9 && dataModels.get(position).rSRQ!!.toInt() <= -3) {
                holder.progressRSRQ.progressColor = Color.parseColor("#80ff00")
            }
        }
    }


    fun setLegendColorForSINR(position: Int, holder: DataAdapter.ViewHolder) {
        // get percentage and set it into progress
        holder.progressSINR.setProgress(rangePercentage(dataModels.get(position).sINR!!.toInt(), legendModel.SINR))

        if (dataModels.get(position).sINR!!.toInt() <= 0) {
            holder.progressSINR.progressColor = Color.parseColor("#000A00")
        } else if (dataModels.get(position).sINR!!.toInt() >= 30) {
            holder.progressSINR.progressColor = Color.parseColor("#0000F0")
        } else {
            if (dataModels.get(position).sINR!!.toInt() >= 0 && dataModels.get(position).sINR!!.toInt() <= 5) {
                holder.progressSINR.progressColor = Color.parseColor("#F90500")
            } else if (dataModels.get(position).sINR!!.toInt() >= 5 && dataModels.get(position).sINR!!.toInt() <= 10) {
                holder.progressSINR.progressColor = Color.parseColor("#FD7632")
            } else if (dataModels.get(position).sINR!!.toInt() >= 10 && dataModels.get(position).sINR!!.toInt() <= 15) {
                holder.progressSINR.progressColor = Color.parseColor("#FBFD00")
            } else if (dataModels.get(position).sINR!!.toInt() >= 15 && dataModels.get(position).sINR!!.toInt() <= 20) {
                holder.progressSINR.progressColor = Color.parseColor("#00FF06")
            } else if (dataModels.get(position).sINR!!.toInt() >= 20 && dataModels.get(position).sINR!!.toInt() <= 25) {
                holder.progressSINR.progressColor = Color.parseColor("#027500")
            } else if (dataModels.get(position).sINR!!.toInt() >= 25 && dataModels.get(position).sINR!!.toInt() <= 30) {
                holder.progressSINR.progressColor = Color.parseColor("#0EFFF8")
            }
        }
    }

    fun rangePercentage(inputValue: Int, legendColorModels : ArrayList<ColorHintModel>) : Int {
        var maxValue = 0.0
        var minValue = 0.0

        for (i in legendColorModels.indices) {
            if (legendColorModels[i].From.contentEquals("Min") && inputValue <= legendColorModels[i].To.toDouble()){
                return 100
            }else if (legendColorModels[i].To.contentEquals("Max") && inputValue >= legendColorModels[i].From.toDouble()){
                return 100
            }else if (!legendColorModels[i].From.contentEquals("Min") && !legendColorModels[i].To.contentEquals("Max")
                && (inputValue >= legendColorModels[i].From.toDouble()
                        && inputValue <= legendColorModels[i].To.toDouble())){
                minValue = legendColorModels[i].From.toDouble()
                maxValue = legendColorModels[i].To.toDouble()
                var percentage = ((inputValue - minValue) * 100) / (maxValue - minValue)
                return percentage.toInt()
            }
        }
        return 0
    }


}