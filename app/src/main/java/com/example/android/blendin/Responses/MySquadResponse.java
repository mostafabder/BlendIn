package com.example.android.blendin.Responses;

import com.example.android.blendin.Models.MySquadModel;

import java.util.List;

/**
 * Created by Luffy on 12/15/2017.
 */

public class MySquadResponse {
    private String status;
    private List<MySquadModel> squads;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MySquadModel> getSquads() {
        return squads;
    }

    public void setSquads(List<MySquadModel> squads) {
        this.squads = squads;
    }
}
