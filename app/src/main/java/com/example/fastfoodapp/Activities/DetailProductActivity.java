package com.example.fastfoodapp.Activities;

import static com.example.fastfoodapp.Utils.Common.gioHangList;
import static com.example.fastfoodapp.Utils.Common.sanPham;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.fastfoodapp.API.ApiService;
import com.example.fastfoodapp.Adapters.GioHangAdapter;
import com.example.fastfoodapp.DialogLoading;
import com.example.fastfoodapp.Models.Banner;
import com.example.fastfoodapp.Models.GioHang;
import com.example.fastfoodapp.Models.HinhAnh;
import com.example.fastfoodapp.Models.SHOP;
import com.example.fastfoodapp.R;
import com.example.fastfoodapp.Utils.Common;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProductActivity extends AppCompatActivity {

    private ImageButton igb_back;
    private ImageSlider imageSlider;
    private List<SlideModel> slideModels;
    private TextView tvDanhgia, tvTenSP, tvGiaSP, tvMota, tvTenShop, tv_XemShop;
    private Button btnThemvaoGio;
    private ImageView img_shopAvatar;

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
        btnThemvaoGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GioHang gioHang = new GioHang(sanPham.getIDSP(), sanPham.getTenSP(), sanPham.getImgSP(), sanPham.getGia(), 1);
                themGioHang(gioHang);
            }
        });

    }

    private void themGioHang(GioHang gioHang) {
        if (gioHangList == null) {
            gioHangList = new ArrayList<>();
            gioHangList.add(gioHang);
            dialog("Đã thêm vào giỏ hàng");
        } else {
            int kt = ktTonTai(gioHang.getIdSP());
            if (kt >= 0) {
                GioHang gioHang1 = gioHangList.get(kt);
                if (gioHang1.getSoLuong() <= sanPham.getSoLuong()) {
                    gioHang1.setSoLuong(gioHang1.getSoLuong() + 1);
                    gioHangList.set(kt,gioHang1);
                    dialog("Đã thêm vào giỏ hàng");
                }else {
                    dialog("Số lượng tối đa");
                }
            }else {
                gioHangList.add(gioHang);
                dialog("Đã thêm vào giỏ hàng");
            }
        }
    }

    private int ktTonTai(String maSP) {
        GioHang model = null;
        for (GioHang gioHang : gioHangList
        ) {
            if (gioHang.getIdSP().equals(maSP)) model = gioHang;
        }
        return gioHangList.indexOf(model);
    }


    private void loadLayout() {
        loadImage();
        tvTenSP.setText(Common.sanPham.getTenSP());
        tvDanhgia.setText(Common.sanPham.getDanhGia() + ".0");
        tvGiaSP.setText(Common.formatMoney(Common.sanPham.getGia()));
        tvMota.setText(Common.sanPham.getMoTaSP());
    }

    private void loadImage() {
        DialogLoading dialogLoading = new DialogLoading(DetailProductActivity.this);
        dialogLoading.show();
        ApiService.api.getShop(Common.sanPham.getIDShop()).enqueue(new Callback<SHOP>() {
            @Override
            public void onResponse(Call<SHOP> call, Response<SHOP> response) {
                if (response.isSuccessful()) {
                    dialogLoading.dismissDialog();
                    if (response.body() != null) {
                        Glide.with(getApplicationContext()).load(response.body().getImgShop()).into(img_shopAvatar);
                        tvTenShop.setText(response.body().getTenShop());
                    }
                }
            }

            @Override
            public void onFailure(Call<SHOP> call, Throwable t) {
                dialogLoading.dismissDialog();
            }
        });
        ApiService.api.getHinhAnhs(Common.sanPham.getIDSP()).enqueue(new Callback<List<HinhAnh>>() {
            @Override
            public void onResponse(Call<List<HinhAnh>> call, Response<List<HinhAnh>> response) {
                if (response.body() != null) {
                    slideModels.clear();
                    for (HinhAnh hinhAnh : response.body()
                    ) {
                        slideModels.add(new SlideModel(hinhAnh.getImgUrl(), ScaleTypes.CENTER_INSIDE));
                    }

                }
                slideModels.add(new SlideModel(sanPham.getImgSP(), ScaleTypes.CENTER_INSIDE));
                imageSlider.setImageList(slideModels);
            }

            @Override
            public void onFailure(Call<List<HinhAnh>> call, Throwable t) {

            }
        });

    }

    private void init() {
        igb_back = findViewById(R.id.igb_back);
        imageSlider = findViewById(R.id.slider_imgProduct);
        slideModels = new ArrayList<>();
        tvDanhgia = findViewById(R.id.tv_DanhGia);
        tvTenSP = findViewById(R.id.tv_tenSP);
        tvMota = findViewById(R.id.tv_moTaSP);
        tvGiaSP = findViewById(R.id.tvGiaSP);
        btnThemvaoGio = findViewById(R.id.btnThemVaoGio);
        img_shopAvatar = findViewById(R.id.img_shopAvatar);
        tvTenShop = findViewById(R.id.tv_tenShop);
        tv_XemShop = findViewById(R.id.tv_XemShop);
    }

    private void dialog(String noidung) {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailProductActivity.this);
        builder.setTitle(noidung);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
}