package com.example.fastfoodapp.Activities;

import static com.example.fastfoodapp.Utils.Common.gioHangList;
import static com.example.fastfoodapp.Utils.Common.userLog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fastfoodapp.API.ApiService;
import com.example.fastfoodapp.Adapters.GioHangAdapter;
import com.example.fastfoodapp.DialogLoading;
import com.example.fastfoodapp.Models.ChiTietDonHang;
import com.example.fastfoodapp.Models.DonHang;
import com.example.fastfoodapp.Models.GioHang;
import com.example.fastfoodapp.R;
import com.example.fastfoodapp.Utils.Common;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatHangActivity extends AppCompatActivity {
    private EditText edt_Ten, edtSDT, edtDiaChi;
    private RecyclerView rec_DSSP;
    private TextView tvTongTien;
    private Button btnDatHang;
    private GioHangAdapter gioHangAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_hang);
        init();
        setLayout();
        onClick();
    }

    private void onClick() {
        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtDiaChi.getText().toString().equals("") || edtSDT.getText().toString().equals("")){
                    Toast.makeText(DatHangActivity.this, "SDT và Địa chỉ không để trống", Toast.LENGTH_SHORT).show();
                }else {
                    themDonHang();
                }
            }
        });
    }

    private void themDonHang() {
        DonHang donHang = new DonHang(Common.username,tongTien(gioHangList));
        DialogLoading dialogLoading = new DialogLoading(DatHangActivity.this);
        dialogLoading.show();
        ApiService.api.addDonHang(donHang).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                    if (response.body() > 0) {
                        for (GioHang gioHang : Common.gioHangList
                        ) {
                            ChiTietDonHang chiTietDonHang = new ChiTietDonHang(gioHang.getIdSP(),gioHang.getSoLuong(),gioHang.getGia());
                            ApiService.api.addCTDonHang(chiTietDonHang).enqueue(new Callback<Integer>() {
                                @Override
                                public void onResponse(Call<Integer> call, Response<Integer> response) {
                                    if (response.isSuccessful()) {
                                        dialogLoading.dismissDialog();
                                        Toast.makeText(DatHangActivity.this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                }

                                @Override
                                public void onFailure(Call<Integer> call, Throwable t) {
                                    dialogLoading.dismissDialog();
                                    Toast.makeText(DatHangActivity.this, "Khong thanh cong", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        Common.gioHangList.clear();
                        startActivity(new Intent(DatHangActivity.this,HomeActivity.class));
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                dialogLoading.dismissDialog();
                Toast.makeText(DatHangActivity.this, "That Bai", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setLayout() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DatHangActivity.this, RecyclerView.VERTICAL, false);
        rec_DSSP.setLayoutManager(linearLayoutManager);
        rec_DSSP.setAdapter(gioHangAdapter);
        tvTongTien.setText(Common.formatMoney(tongTien(gioHangList))+"");
        edt_Ten.setText(userLog.getHoTen());
        edtSDT.setText(userLog.getSDT());
        edtDiaChi.setText(userLog.getDiaChi());

    }
    private int tongTien(List<GioHang> gioHangList){
        int tong = 0;
        for (GioHang gioHang : gioHangList){
            tong += gioHang.getGia()*gioHang.getSoLuong();
        }
        return tong;
    }

    private void init(){
        edt_Ten = findViewById(R.id.edt_HoTen);
        edtSDT = findViewById(R.id.edt_Sdt);
        edtDiaChi = findViewById(R.id.edt_DiaChi);
        rec_DSSP = findViewById(R.id.rec_dsSP);
        tvTongTien = findViewById(R.id.tv_TongTien);
        btnDatHang = findViewById(R.id.btnDatHang);
        gioHangAdapter = new GioHangAdapter(DatHangActivity.this);
    }
}