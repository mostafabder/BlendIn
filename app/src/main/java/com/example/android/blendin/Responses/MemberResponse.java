package com.example.android.blendin.Responses;

import com.example.android.blendin.Models.HangoutProfileMemberModel;
import com.example.android.blendin.Models.User;

import java.util.List;

/**
 * Created by Luffy on 12/19/2017.
 */

public class MemberResponse {
    String status;
    List<HangoutProfileMemberModel> members;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<HangoutProfileMemberModel> getMembers() {
        return members;
    }

    public void setMembers(List<HangoutProfileMemberModel> members) {
        this.members = members;
    }
}
