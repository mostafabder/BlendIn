package com.example.android.blendin.Responses;

import com.example.android.blendin.Models.SquadRequestModel;

import java.util.List;

/**
 * Created by LeGenÐ on 12/19/2017.
 */

public class SquadRequestResponse {
    private String status;
    private List<SquadRequestModel> squad_requests;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SquadRequestModel> getSquad_requests() {
        return squad_requests;
    }

    public void setSquad_requests(List<SquadRequestModel> squad_requests) {
        this.squad_requests = squad_requests;
    }
}
