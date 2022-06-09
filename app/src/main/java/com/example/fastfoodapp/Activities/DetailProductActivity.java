package com.example.fastfoodapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.fastfoodapp.API.ApiService;
import com.example.fastfoodapp.Models.Banner;
import com.example.fastfoodapp.Models.HinhAnh;
import com.example.fastfoodapp.R;
import com.example.fastfoodapp.Utils.Common;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProductActivity extends AppCompatActivity {

    private ImageButton igb_back;
    private ImageSlider imageSlider;
    private List<SlideModel> slideModels;
    private TextView tvDanhgia, tvTenSP,tvGiaSP, tvMota;
    private Button btnThemvaoGio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        init();
        loadLayout();
        onClick();
    }

    private void onClick() {
        igb_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void loadLayout() {
        loadImage();
        tvTenSP.setText(Common.sanPham.getTenSP());
        tvDanhgia.setText(Common.sanPham.getDanhGia()+".0");
        tvGiaSP.setText(Common.formatMoney(Common.sanPham.getGia()));
        tvMota.setText(Common.sanPham.getMoTaSP());
    }

    private void loadImage() {
        ApiService.api.getHinhAnhs(Common.sanPham.getIDSP()).enqueue(new Callback<List<HinhAnh>>() {
            @Override
            public void onResponse(Call<List<HinhAnh>> call, Response<List<HinhAnh>> response) {
                if (response.body() != null) {
                    slideModels.clear();
                    for (HinhAnh hinhAnh : response.body()
                    ) {
                        slideModels.add(new SlideModel(hinhAnh.getImgUrl(), ScaleTypes.CENTER_INSIDE));
                    }
                    imageSlider.setImageList(slideModels);
                }
            }

            @Override
            public void onFailure(Call<List<HinhAnh>> call, Throwable t) {

            }
        });
    }

    private void init(){
        igb_back = findViewById(R.id.igb_back);
        imageSlider = findViewById(R.id.slider_imgProduct);
        slideModels = new ArrayList<>();
        tvDanhgia = findViewById(R.id.tv_DanhGia);
        tvTenSP = findViewById(R.id.tv_tenSP);
        tvMota = findViewById(R.id.tv_moTaSP);
        tvGiaSP = findViewById(R.id.tvGiaSP);
        btnThemvaoGio = findViewById(R.id.btnThemVaoGio);
    }
}