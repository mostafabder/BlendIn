package com.example.android.blendin.Responses;

import com.example.android.blendin.Models.CommentModel;

import java.util.List;

/**
 * Created by Luffy on 12/18/2017.
 */

public class CommentsResponse {
    String status;
    List<CommentModel> comments;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CommentModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentModel> comments) {
        this.comments = comments;
    }
}
