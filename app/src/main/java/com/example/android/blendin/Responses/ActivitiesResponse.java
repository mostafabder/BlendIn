package com.example.android.blendin.Responses;

import android.app.Activity;

import com.example.android.blendin.Models.anActivity;

import java.util.List;

/**
 * Created by Luffy on 12/18/2017.
 */

public class ActivitiesResponse {
    String status;
    List<anActivity> activities;

    public List<anActivity> getActivities() {
        return activities;
    }

    public void setActivities(List<anActivity> activities) {
        this.activities = activities;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
