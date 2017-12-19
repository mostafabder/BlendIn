package com.example.android.blendin.Responses;

import com.example.android.blendin.Models.NewsFeedModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Luffy on 12/15/2017.
 */

public class NewsfeedResponse {


    @SerializedName("status")
    @Expose
    private String flag;

    private List<NewsFeedModel> posts;


    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<NewsFeedModel> getPosts() {
        return posts;
    }

    public void setPosts(List<NewsFeedModel> newsFeedModelList) {
        this.posts = newsFeedModelList;
    }
}
