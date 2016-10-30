package com.palette;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Hari Prasad on 10/30/16.
 */

public interface Api {
    String BASE_URL = "https://jsonplaceholder.typicode.com";

    @GET("/photos")
    Call<List<ImagesModel>> images();
}
