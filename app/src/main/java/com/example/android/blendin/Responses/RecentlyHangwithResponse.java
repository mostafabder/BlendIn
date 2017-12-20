package com.example.android.blendin.Responses;

import com.example.android.blendin.Models.RecentlyHangwithModel;

import java.util.List;

/**
 * Created by Luffy on 12/20/2017.
 */

public class RecentlyHangwithResponse {
    String status;
    List<RecentlyHangwithModel> members;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<RecentlyHangwithModel> getMembers() {
        return members;
    }

    public void setMembers(List<RecentlyHangwithModel> members) {
        this.members = members;
    }
}
