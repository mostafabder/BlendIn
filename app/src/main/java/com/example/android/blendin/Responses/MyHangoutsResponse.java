package com.example.android.blendin.Responses;

import com.example.android.blendin.Models.MyHangoutsModel;

import java.util.List;

/**
 * Created by Luffy on 12/20/2017.
 */

public class MyHangoutsResponse {

    String status;
    List<MyHangoutsModel> hangouts;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MyHangoutsModel> getHangouts() {
        return hangouts;
    }

    public void setHangouts(List<MyHangoutsModel> hangouts) {
        this.hangouts = hangouts;
    }

}
