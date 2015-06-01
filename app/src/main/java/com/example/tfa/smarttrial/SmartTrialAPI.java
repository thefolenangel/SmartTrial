package com.example.tfa.smarttrial;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by TFA on 28-May-15.
 */
public interface SmartTrialAPI {

    //@FormUrlEncoded
    @POST("/public/signup/{pathwayId}")
    void createUser(@Path("pathwayId") String pathwayId, @Body User user, Callback<String> cb);
}
