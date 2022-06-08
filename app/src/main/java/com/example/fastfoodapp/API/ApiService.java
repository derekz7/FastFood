package com.example.fastfoodapp.API;

import com.example.fastfoodapp.Models.Banner;
import com.example.fastfoodapp.Models.DanhMuc;
import com.example.fastfoodapp.Models.SanPham;
import com.example.fastfoodapp.Utils.Common;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build();
    ApiService api = new Retrofit.Builder()
            .baseUrl(Common.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(ApiService.class);

    @GET("api/Banners")
    Call<List<Banner>> getBanner();

    @GET("api/DanhMucs")
    Call<List<DanhMuc>> getDanhMuc();

    @GET("api/SanPhams")
    Call<List<SanPham>> getSanPham();

    @GET("api/SanPhams/getSPbyDM")
    Call<List<SanPham>> getSPbyDM(@Query("idDM") String idDM);
}
