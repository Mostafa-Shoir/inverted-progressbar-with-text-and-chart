package com.taskfordigis.webServices

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.taskfordigis.models.DataModel
import com.taskfordigis.viewModels.ParentResponseModel
import io.reactivex.schedulers.Schedulers

object Repository {
    fun getData(): LiveData<DataModel> {
        val data = MutableLiveData<DataModel>()
        NetworkModule.provideRetrofitInterface().create(ApiServices::class.java)
            .getData()
            .subscribeOn(Schedulers.io())
            .repeat(1)
            .subscribe({ result ->
                data.postValue(result)
            }, { error ->
                error.printStackTrace()
                Log.d("repository", "--- error")
//                data.postValue(error.message)
            })
        return data
    }

}