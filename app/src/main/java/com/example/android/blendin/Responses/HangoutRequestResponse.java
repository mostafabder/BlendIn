package com.example.android.blendin.Responses;

import com.example.android.blendin.Models.HangoutRequestModel;

import java.util.List;

/**
 * Created by LeGenÐ on 12/19/2017.
 */

public class HangoutRequestResponse {
    private String status;
    private List<HangoutRequestModel> hangouts_requests;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<HangoutRequestModel> getHangouts_requests() {
        return hangouts_requests;
    }

    public void setHangouts_requests(List<HangoutRequestModel> hangouts_requests) {
        this.hangouts_requests = hangouts_requests;
    }
}
