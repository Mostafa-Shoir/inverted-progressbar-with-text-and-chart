package com.taskfordigis.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.taskfordigis.models.DataModel
import com.taskfordigis.webServices.Repository
import com.taskfordigis.webServices.Response

class DataViewModel: ViewModel() {
    fun fitchData(): LiveData<DataModel> {
        return Repository.getData()
    }
}