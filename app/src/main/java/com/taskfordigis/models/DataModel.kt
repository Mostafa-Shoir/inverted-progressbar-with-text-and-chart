package com.taskfordigis.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class DataModel : Serializable{
    @SerializedName("RSRP")
    @Expose
    var rSRP: Int? = null

    @SerializedName("RSRQ")
    @Expose
    var rSRQ: Int? = null

    @SerializedName("SINR")
    @Expose
    var sINR: Int? = null

    var timeValue : Int = 0
    var timeLabel : String = ""

}