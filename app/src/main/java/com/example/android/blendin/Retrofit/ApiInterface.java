package com.example.android.blendin.Retrofit;

import com.example.android.blendin.Responses.LoginResponse;
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
    Call<LoginResponse> loginFb(@Field("fbid") String fbid, @Field("type") String type);

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
            @Field("address") String address,
            @Field("gender") String gender,
            @Field("lat") String lat,
            @Field("lng") String lng);


}
