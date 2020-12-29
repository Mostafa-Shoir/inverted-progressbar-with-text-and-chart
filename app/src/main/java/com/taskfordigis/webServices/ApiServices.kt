package com.taskfordigis.webServices

import com.taskfordigis.models.DataModel
import com.taskfordigis.viewModels.ParentResponseModel
import io.reactivex.Observable
import retrofit2.http.*

interface ApiServices {

    @Headers("Content-Type:application/json")
    @GET("random")
    fun getData(): Observable<DataModel>

}