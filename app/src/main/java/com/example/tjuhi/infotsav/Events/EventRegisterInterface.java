package com.example.tjuhi.infotsav.Events;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by tjuhi on 6/18/2017.
 */

 public interface EventRegisterInterface {
    String s=ScrollingActivity.string;

    @FormUrlEncoded
    @POST("/event.php")
    public void eventRegister(
            @Field("unique_id") String unique_id,
            @Field("name") String name,
            @Field("email") String email,
            Callback<Response> callback
    );
}
