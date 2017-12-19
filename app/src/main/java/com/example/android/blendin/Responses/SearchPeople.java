package com.example.android.blendin.Responses;

import com.example.android.blendin.Models.User;

import java.util.List;

/**
 * Created by Luffy on 12/18/2017.
 */

public class SearchPeople {
    String status;
    List<User> users;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
