package com.example.android.blendin.Retrofit;

import com.example.android.blendin.Responses.ActivitiesResponse;
import com.example.android.blendin.Responses.CommentResponse;
import com.example.android.blendin.Responses.CommentsResponse;
import com.example.android.blendin.Responses.LoginResponse;
import com.example.android.blendin.Responses.ProfileResponse;
import com.example.android.blendin.Responses.LoveResponse;
import com.example.android.blendin.Responses.NewsfeedResponse;
import com.example.android.blendin.Responses.SearchPeople;
import com.example.android.blendin.Responses.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Luffy on 12/15/2017.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("secure/auth/login")
    Call<LoginResponse> loginRegular(@Field("email") String email, @Field("password") String password, @Field("type") String type);

    @FormUrlEncoded
    @POST("secure/auth/login")
    Call<LoginResponse> loginFb(@Field("fbid") String fbid, @Field("type") String type
            , @Field("first_name") String first_name,
                                @Field("last_name") String last_name,
                                @Field("email") String email,
                                @Field("gender") String gender);


    @FormUrlEncoded
    @POST("secure/auth/login")
    Call<LoginResponse> loginTw(@Field("tid") String tid, @Field("type") String type);

    @FormUrlEncoded
    @POST("/secure/auth/register")
    Call<SignUpResponse> signupRegular(
            @Field("email") String email,
            @Field("password") String password,
            @Field("type") String type,
            @Field("first_name") String first_name,
            @Field("last_name") String last_name,
            @Field("gender") String gender);


    @FormUrlEncoded
    @POST("secure/auth/profile/mine")
    Call<ProfileResponse> getProfile(
            @Field("token") String token,
            @Field("secret") String secret
    );
  
    @FormUrlEncoded
    @POST("/news-feed")
    Call<NewsfeedResponse> newsfeed(@Field("token") String token, @Field("secret") String secret);

    @FormUrlEncoded
    @POST("/news-feed/posts/love")
    Call<LoveResponse> love(@Field("token") String token, @Field("secret") String secret, @Field("post_id") String post_id);

    @FormUrlEncoded
    @POST("/news-feed/posts/comments")
    Call<CommentsResponse> comments(@Field("token") String token, @Field("secret") String secret, @Field("post_id") String post_id);

    @FormUrlEncoded
    @POST("news-feed/posts/comments/push")
    Call<CommentResponse> addComment(@Field("token") String token, @Field("secret") String secret, @Field("post_id") String post_id, @Field("message") String message);

    @FormUrlEncoded
    @POST("/activities")
    Call<ActivitiesResponse> getActivities(@Field("token") String token, @Field("secret") String secret);

    @FormUrlEncoded
    @POST("hangouts/search/people")
    Call<SearchPeople> searchPeople(@Field("token") String token, @Field("secret") String secret, @Field("activity_id") String activity_id, @Field("lat") String lat, @Field("lng") String lng);
}
