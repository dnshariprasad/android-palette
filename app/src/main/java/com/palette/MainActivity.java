package com.palette;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        api.images().enqueue(new Callback<List<ImagesModel>>() {
            @Override
            public void onResponse(Call<List<ImagesModel>> call, Response<List<ImagesModel>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.raw());
                    ImagesAdapter.build(MainActivity.this, (RecyclerView) findViewById(R.id.rv_images), response.body());
                } else {
                    Log.e(TAG, "onResponse: " + response.raw());
                }
            }

            @Override
            public void onFailure(Call<List<ImagesModel>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }
}
