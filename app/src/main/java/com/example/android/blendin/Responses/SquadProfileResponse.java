package com.example.android.blendin.Responses;

import android.widget.LinearLayout;

import com.example.android.blendin.Models.SquadChatMessageModel;
import com.example.android.blendin.Models.SquadProfileModel;

import java.util.List;

/**
 * Created by LeGenÐ on 12/18/2017.
 */

public class SquadProfileResponse {

    private String status;

    private SquadProfileModel squad;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SquadProfileModel getSquad() {
        return squad;
    }

    public void setSquad(SquadProfileModel squad) {
        this.squad = squad;
    }

}
