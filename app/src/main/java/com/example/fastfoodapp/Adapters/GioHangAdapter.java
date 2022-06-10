package com.example.fastfoodapp.Adapters;

import static com.example.fastfoodapp.Utils.Common.gioHangList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fastfoodapp.Models.GioHang;
import com.example.fastfoodapp.R;
import com.example.fastfoodapp.Utils.Common;

import java.util.ArrayList;
import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.GioHangViewHolder> {

    private Context context;

    public GioHangAdapter(Context context) {
        this.context = context;
        if (gioHangList == null) {
            gioHangList = new ArrayList<>();
        }
    }

    @NonNull
    @Override
    public GioHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giohang,parent,false);
        return new GioHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangViewHolder holder, int position) {
        int pos = position;
        GioHang gioHang = gioHangList.get(position);
        Glide.with(context).load(gioHang.getImgUrl()).into(holder.img_Sp);
        holder.tv_Name.setText(gioHang.getTenSP());
        holder.tv_Gia.setText(Common.formatMoney(gioHang.getGia() * gioHang.getSoLuong()));
        holder.tv_soLuong.setText(gioHang.getSoLuong() +"");
        holder.igb_xoa.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                gioHangList.remove(pos);
                notifyDataSetChanged();
            }
        });
        holder.igb_tru.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                if (gioHang.getSoLuong() > 1){
                    gioHang.setSoLuong(gioHang.getSoLuong() - 1);
                    holder.tv_soLuong.setText(gioHang.getSoLuong()+"");
                    gioHangList.set(pos,gioHang);
                }else {
                    gioHangList.remove(pos);
                }
                notifyDataSetChanged();

            }
        });
        holder.igb_cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gioHang.setSoLuong(gioHang.getSoLuong() + 1);
                holder.tv_soLuong.setText(gioHang.getSoLuong() + "");
                gioHangList.set(pos,gioHang);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (gioHangList.size() == 0){
            return 0;
        }else return gioHangList.size();

    }

    public class GioHangViewHolder extends RecyclerView.ViewHolder{
        private ImageView img_Sp;
        private TextView tv_Name, tv_Gia, tv_soLuong;
        private ImageButton igb_xoa, igb_tru, igb_cong;

        public GioHangViewHolder(@NonNull View itemView) {
            super(itemView);
            img_Sp = itemView.findViewById(R.id.img_AnhSP);
            tv_Name = itemView.findViewById(R.id.tv_tenSp);
            tv_Gia = itemView.findViewById(R.id.tv_GiaSP);
            tv_soLuong = itemView.findViewById(R.id.tv_soLuong);
            igb_tru = itemView.findViewById(R.id.igb_tru);
            igb_cong = itemView.findViewById(R.id.igb_cong);
            igb_xoa = itemView.findViewById(R.id.igb_xoaSP);
        }
    }
}
