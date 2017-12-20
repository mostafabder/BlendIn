package com.example.android.blendin.Responses;

import com.example.android.blendin.Models.CommentModel;
import com.example.android.blendin.Models.HangoutModel;
import com.example.android.blendin.Models.HangoutProfileModel;
import com.example.android.blendin.Models.NewsFeedModel;
import com.example.android.blendin.Models.User;

import java.util.List;

/**
 * Created by Luffy on 12/19/2017.
 */

public class HangoutProfileResponse {
    String status;
    HangoutProfileModel hangout;
    List<CommentModel> chat;
    List<NewsFeedModel> posts;
    List<HangoutModel> members;

    public List<HangoutModel> getMembers() {
        return members;
    }

    public void setMembers(List<HangoutModel> members) {
        this.members = members;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HangoutProfileModel getHangout() {
        return hangout;
    }

    public void setHangout(HangoutProfileModel hangout) {
        this.hangout = hangout;
    }

    public List<CommentModel> getChat() {
        return chat;
    }

    public void setChat(List<CommentModel> chat) {
        this.chat = chat;
    }

    public List<NewsFeedModel> getPosts() {
        return posts;
    }

    public void setPosts(List<NewsFeedModel> posts) {
        this.posts = posts;
    }
}
