package com.example.fastfoodapp.Fragment;

import static com.example.fastfoodapp.Utils.Common.danhMucList;
import static com.example.fastfoodapp.Utils.Common.sanPhamList;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.fastfoodapp.API.ApiService;
import com.example.fastfoodapp.Activities.DetailProductActivity;
import com.example.fastfoodapp.Adapters.CategoryAdapter;
import com.example.fastfoodapp.Adapters.SanPhamAdapter;
import com.example.fastfoodapp.DialogLoading;
import com.example.fastfoodapp.Models.Banner;
import com.example.fastfoodapp.Models.DanhMuc;
import com.example.fastfoodapp.Models.NguoiDung;
import com.example.fastfoodapp.Models.SanPham;
import com.example.fastfoodapp.R;
import com.example.fastfoodapp.Utils.Common;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.android.material.shadow.ShadowRenderer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    private RecyclerView rec_DM, rec_SP;
    private SanPhamAdapter sanPhamAdapter;
    private CategoryAdapter categoryAdapter;
    private ImageSlider imageSlider;
    private List<SlideModel> slideModels;
    private TextView titleSP, tv_title;
    private DialogLoading dialogLoading;
    private ImageView img_Profile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        getUser();
        setLayout();
        loadData();
        onClick();


    }

    private void getUser() {
        if (Common.username != null){
            ApiService.api.getNDbyUsername(Common.username).enqueue(new Callback<NguoiDung>() {
                @Override
                public void onResponse(Call<NguoiDung> call, Response<NguoiDung> response) {
                    if (response.isSuccessful()){
                        if (response.body() != null){
                            Common.userLog = response.body();
                            if (response.body().getImgND().equals("")){
                                img_Profile.setImageResource(R.drawable.avataruser);
                            }else {
                                Glide.with(getContext()).load(response.body().getImgND()).into(img_Profile);
                            }

                        }
                    }
                }

                @Override
                public void onFailure(Call<NguoiDung> call, Throwable t) {

                }
            });
        }
    }


    private void onClick() {
        sanPhamAdapter.setOnItemClickListener(new SanPhamAdapter.onItemCLickListener() {
            @Override
            public void onItemClick(int pos, View view) {
                Common.sanPham = sanPhamList.get(pos);
                startActivity(new Intent(getContext(), DetailProductActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        categoryAdapter.setOnItemClickListener(new CategoryAdapter.onItemCLickListener() {
            @Override
            public void onItemClick(int pos, View view) {
                DanhMuc danhMuc = danhMucList.get(pos);
                getSanPham(danhMuc);
                titleSP.setText(danhMuc.getTenDanhMuc());
                imageSlider.setVisibility(View.GONE);
            }
        });

        tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });
    }

    private void getSanPham(DanhMuc danhMuc) {
        dialogLoading.show();
        ApiService.api.getSPbyDM(danhMuc.getIDDanhMuc()).enqueue(new Callback<List<SanPham>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                if (response.body() != null) {
                    sanPhamList.clear();
                    for (SanPham sanPham : response.body()
                    ) {
                        sanPhamList.add(sanPham);

                    }
                    sanPhamAdapter.notifyDataSetChanged();
                }
                dialogLoading.dismissDialog();
            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {
                Toast.makeText(getContext(), "" + t, Toast.LENGTH_SHORT).show();
                dialogLoading.dismissDialog();
            }
        });
    }

    private void setLayout() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        rec_DM.setLayoutManager(linearLayoutManager);
//        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
//        rec_SP.setLayoutManager(layoutManager);
        FlexboxLayoutManager manager = new FlexboxLayoutManager(getContext(), FlexDirection.ROW);
        manager.setJustifyContent(JustifyContent.CENTER);
        rec_SP.setLayoutManager(manager);
        rec_SP.setNestedScrollingEnabled(false);
    }

    private void loadData() {
        titleSP.setText("Sản phẩm");
        imageSlider.setVisibility(View.VISIBLE);
        dialogLoading.show();
        ApiService.api.getDanhMuc().enqueue(new Callback<List<DanhMuc>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<DanhMuc>> call, Response<List<DanhMuc>> response) {
                if (response.body() != null) {
                    danhMucList.clear();
                    for (DanhMuc danhMuc : response.body()
                    ) {
                        danhMucList.add(danhMuc);
                        categoryAdapter.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<DanhMuc>> call, Throwable t) {
            }
        });

        ApiService.api.getSanPham().enqueue(new Callback<List<SanPham>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                if (response.body() != null) {
                    sanPhamList.clear();
                    for (SanPham sanPham : response.body()
                    ) {
                        sanPhamList.add(sanPham);
                        sanPhamAdapter.notifyDataSetChanged();
                    }
                   dialogLoading.dismissDialog();
                }
            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {
                Toast.makeText(getContext(), "" + t, Toast.LENGTH_SHORT).show();
                dialogLoading.dismissDialog();
            }
        });

        ApiService.api.getBanner().enqueue(new Callback<List<Banner>>() {
            @Override
            public void onResponse(Call<List<Banner>> call, Response<List<Banner>> response) {
                if (response.body() != null) {
                    slideModels.clear();
                    for (Banner banner : response.body()
                    ) {
                        slideModels.add(new SlideModel(banner.getImgBanner(), ScaleTypes.CENTER_INSIDE));
                    }
                    imageSlider.setImageList(slideModels);
                }
            }

            @Override
            public void onFailure(Call<List<Banner>> call, Throwable t) {

            }
        });
    }

    private void init(View view) {
        imageSlider = view.findViewById(R.id.img_slideBanner);
        rec_DM = view.findViewById(R.id.rec_DM);
        rec_SP = view.findViewById(R.id.rec_SP);
        titleSP = view.findViewById(R.id.tv_titleSP);
        tv_title = view.findViewById(R.id.tv_title);
        img_Profile = view.findViewById(R.id.img_Profile);
        sanPhamList = new ArrayList<>();
        danhMucList = new ArrayList<>();
        slideModels = new ArrayList<>();
        dialogLoading = new DialogLoading(getContext());
        categoryAdapter = new CategoryAdapter(getContext(), danhMucList);
        rec_DM.setAdapter(categoryAdapter);
        sanPhamAdapter = new SanPhamAdapter(getContext(), sanPhamList);
        rec_SP.setAdapter(sanPhamAdapter);
    }
}