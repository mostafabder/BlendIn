package com.example.android.blendin.Retrofit;

import com.example.android.blendin.Responses.LoginResponse;
import com.example.android.blendin.Responses.ProfileResponse;
import com.example.android.blendin.Responses.LoveResponse;
import com.example.android.blendin.Responses.NewsfeedResponse;
import com.example.android.blendin.Responses.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

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
    
}
