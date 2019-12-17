package com.example.loginrest.service;

import com.example.loginrest.entities.Login;
import com.example.loginrest.entities.Token;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
    //@GET("users")
    //Call<List<Users>> getUsuarios();

    //@POST("posts")
    //Call<Post> createPost(@Body Post post);

    @POST("login")
    Call<Token> register(@Body Login login);
}
