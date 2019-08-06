package com.example.tjuhi.infotsav.Login;


import com.example.tjuhi.infotsav.Login.models.ServerRequest;
import com.example.tjuhi.infotsav.Login.models.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestInterface {

    @POST("infoapi/")
    Call<ServerResponse> operation(@Body ServerRequest request);

}
