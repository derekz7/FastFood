package com.example.fastfoodapp.Fragment;

import static com.example.fastfoodapp.Utils.Common.gioHangList;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fastfoodapp.Activities.DatHangActivity;
import com.example.fastfoodapp.Adapters.GioHangAdapter;
import com.example.fastfoodapp.Models.GioHang;
import com.example.fastfoodapp.R;
import com.example.fastfoodapp.Utils.Common;

import java.util.List;


public class GioHangFragment extends Fragment {
    private TextView tv_title, tv_TongTien;
    private Button btn_DatHang;
    private RecyclerView rec_GioHang;
    private GioHangAdapter gioHangAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_gio_hang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        setLayout();
        if (gioHangList == null || gioHangList.size() ==0){
            tv_title.setText("Không có sản phẩm trong giỏ");
        }else {
            tv_title.setText("Bạn có "+gioHangList.size()+" sản phẩm trong giỏ");

        }

        btn_DatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gioHangList != null){
                    startActivity(new Intent(getContext(), DatHangActivity.class));
                }
            }
        });
    }

    private void setLayout() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rec_GioHang.setLayoutManager(linearLayoutManager);
        rec_GioHang.setAdapter(gioHangAdapter);

    }



    private void init(View view){
        tv_title = view.findViewById(R.id.textView3);
        tv_TongTien = view.findViewById(R.id.tv_TongTien);
        rec_GioHang = view.findViewById(R.id.rec_GioHang);
        btn_DatHang = view.findViewById(R.id.btn_DatHang);
        gioHangAdapter = new GioHangAdapter(getContext());
    }
}