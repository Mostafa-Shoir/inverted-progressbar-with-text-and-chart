package com.taskfordigis.viewModels;

import com.taskfordigis.models.DataModel;

import java.util.ArrayList;

public class ParentResponseModel {

    private String message;
    private String status;
    private DataModel dataModels;

    public DataModel getDataModels() {
        return dataModels;
    }

    public void setDataModels(DataModel dataModels) {
        this.dataModels = dataModels;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
