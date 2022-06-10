package com.example.fastfoodapp.API;

import com.example.fastfoodapp.Models.Banner;
import com.example.fastfoodapp.Models.ChiTietDonHang;
import com.example.fastfoodapp.Models.DanhMuc;
import com.example.fastfoodapp.Models.DonHang;
import com.example.fastfoodapp.Models.HinhAnh;
import com.example.fastfoodapp.Models.NguoiDung;
import com.example.fastfoodapp.Models.SHOP;
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
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
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


    //tài khoản

    @GET("  api/NguoiDungs/checkUsername")
    Call<NguoiDung> getNDbyUsername(@Query("username") String username);

    @GET("api/NguoiDungs/checksdt")
    Call<NguoiDung> getNDbySDT(@Query("sdt") String sdt);

    @GET("api/NguoiDungs/checkUsernameExist")
    Call<Boolean> checkUsernameExist(@Query("username") String username);

    @GET("api/NguoiDungs/checkSDTexist")
    Call<Boolean> checkSDTExist(@Query("sdt") String sdt);

    @POST("api/NguoiDungs/PostNguoiDung")
    Call<Integer> dangKy(@Body NguoiDung nguoiDung);

    @GET("api/NguoiDungs/DangNhap")
    Call<Boolean> dangNhap(@Query("username") String username, @Query("password") String password);

    //hình ảnh sản phẩm
    @GET("api/HinhAnhs/getImageSP")
    Call<List<HinhAnh>> getHinhAnhs(@Query("idSP") String idSP);

    @GET("api/Banners")
    Call<List<Banner>> getBanner();

    @GET("api/DanhMucs")
    Call<List<DanhMuc>> getDanhMuc();

    @GET("api/SanPhams")
    Call<List<SanPham>> getSanPham();

    @GET("api/SanPhams/getSPbyDM")
    Call<List<SanPham>> getSPbyDM(@Query("idDM") String idDM);

    @GET("api/SanPhams/getShop")
    Call<SHOP> getShop(@Query("idshop") String idshop);

    @POST("api/DonHangs/PostDonHang")
    Call<Integer> addDonHang(@Body DonHang donHang);

    @GET("api/ChiTietDonHangs/getCTdonHang")
    Call<List<ChiTietDonHang>> getCTDonHang(@Query("idDH") int idDH);

    @POST("api/ChiTietDonHangs/PostCTDonHang")
    Call<Integer> addCTDonHang(@Body ChiTietDonHang ctDOnHang);
}
