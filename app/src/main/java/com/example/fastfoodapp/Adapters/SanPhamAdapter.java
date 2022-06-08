package com.example.fastfoodapp.Adapters;

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

import com.example.fastfoodapp.Models.DanhMuc;
import com.example.fastfoodapp.Models.SanPham;
import com.example.fastfoodapp.R;
import com.example.fastfoodapp.Utils.Common;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.SanPhamViewHolder> {
    private Context mcontext;
    private List<SanPham> listSp;
    private SanPhamAdapter.onItemCLickListener mListener;


    public interface onItemCLickListener{
        void onItemClick(int pos, View view);
    }

    public SanPhamAdapter(Context mcontext, List<SanPham> listSp) {
        this.mcontext = mcontext;
        this.listSp = listSp;
    }

    public void setData(List<SanPham> list){
        this.listSp = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SanPhamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham,parent,false);
        return new SanPhamViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamViewHolder holder, int position) {
        SanPham sanPham = listSp.get(position);
        if (sanPham == null) {
            return;
        }
        Picasso.get().load(sanPham.getImgSP()).into(holder.img_SP);
        holder.tv_TenSP.setText(sanPham.getTenSP());
        holder.tv_Gia.setText(Common.formatMoney(sanPham.getGia()));
    }

    @Override
    public int getItemCount() {
        if (listSp != null){
            return listSp.size();
        }
        return 0;
    }



    public class SanPhamViewHolder extends RecyclerView.ViewHolder{
        private ImageView img_SP;
        private TextView tv_TenSP, tv_Gia;
        private ImageButton igb_add;
        public SanPhamViewHolder (@NonNull View itemView, SanPhamAdapter.onItemCLickListener listener) {
            super(itemView);
            img_SP = itemView.findViewById(R.id.img_SP);
            tv_TenSP = itemView.findViewById(R.id.tv_tenSP);
            tv_Gia = itemView.findViewById(R.id.tv_gia);
            igb_add  = itemView.findViewById(R.id.igb_add);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position,v);
                        }
                    }
                }
            });
            igb_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
