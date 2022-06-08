package com.example.fastfoodapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastfoodapp.Models.DanhMuc;
import com.example.fastfoodapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private Context mcontext;
    private List<DanhMuc> listDanhMuc;
    private onItemCLickListener mListener;
    private int row_index = -1;


    public interface onItemCLickListener{
        void onItemClick(int pos, View view);
    }

    public void setOnItemClickListener(onItemCLickListener mListener) {
        this.mListener = mListener;
    }

    public CategoryAdapter(Context mcontext, List<DanhMuc> listDanhMuc) {
        this.mcontext = mcontext;
        this.listDanhMuc = listDanhMuc;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<DanhMuc> list){
        this.listDanhMuc = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danhmuc,parent,false);
        return new CategoryViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        DanhMuc danhMuc = listDanhMuc.get(position);
        int pos  = position;
        if (danhMuc == null) {
            return;
        }
        Picasso.get().load(danhMuc.getImgDM()).into(holder.img_DM);
        holder.tv_DM.setText(danhMuc.getTenDanhMuc());
        if (row_index == pos){
            holder.img_DM.setBackgroundResource(R.drawable.bg_item_red);
            holder.tv_DM.setTextColor(Color.parseColor("#9E0707"));
            holder.tv_DM.setTypeface(Typeface.DEFAULT_BOLD);
        }else {
            holder.img_DM.setBackgroundResource(R.drawable.bg_item);
            holder.tv_DM.setTextColor(Color.parseColor("#FF000000"));
            holder.tv_DM.setTypeface(Typeface.DEFAULT);
        }
    }

    @Override
    public int getItemCount() {
        if (listDanhMuc != null){
            return listDanhMuc.size();
        }
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        private ImageView img_DM;
        private TextView tv_DM;
        public CategoryViewHolder(@NonNull View itemView, onItemCLickListener listener) {
            super(itemView);
            img_DM = itemView.findViewById(R.id.img_danhMuc);
            tv_DM = itemView.findViewById(R.id.tv_tenDM);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        row_index = position;
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position,v);
                        }
                        notifyDataSetChanged();
                    }
                }
            });
        }
    }
}
