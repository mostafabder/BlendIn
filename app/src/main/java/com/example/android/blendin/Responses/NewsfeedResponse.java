package com.example.android.blendin.Responses;

import com.example.android.blendin.Models.NewsFeedModel;

import java.util.List;

/**
 * Created by Luffy on 12/15/2017.
 */

public class NewsfeedResponse {
    private String flag;
    private List<NewsFeedModel> newsFeedModelList;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<NewsFeedModel> getNewsFeedModelList() {
        return newsFeedModelList;
    }

    public void setNewsFeedModelList(List<NewsFeedModel> newsFeedModelList) {
        this.newsFeedModelList = newsFeedModelList;
    }
}
