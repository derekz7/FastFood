package com.example.fastfoodapp.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fastfoodapp.DialogBacktoLogin;
import com.example.fastfoodapp.R;
import com.example.fastfoodapp.Utils.Common;
import com.squareup.picasso.Picasso;

public class UserFragment extends Fragment {
    private DialogBacktoLogin dialogBacktoLogin;
    private TextView tv_Hoten, tv_Nhan;
    private ImageView img_Ava;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        if (Common.userLog != null){
            tv_Nhan.setVisibility(View.VISIBLE);
            loadLayout();
        }else {
            tv_Nhan.setVisibility(View.INVISIBLE);
            tv_Hoten.setText("Bạn chưa đăng nhập!");
            dialogBacktoLogin.show();
        }
    }

    private void loadLayout() {
        tv_Nhan.setVisibility(View.VISIBLE);
        tv_Hoten.setText(Common.userLog.getHoTen());
        if (Common.userLog.getImgND() != null){
            Picasso.get().load(Common.userLog.getImgND()).into(img_Ava);
        }

    }

    private void init(View view) {
        tv_Nhan = view.findViewById(R.id.tv_nhan);
        tv_Hoten = view.findViewById(R.id.tv_hoTen);
        img_Ava = view.findViewById(R.id.img_avatarUser);
        dialogBacktoLogin = new DialogBacktoLogin(getContext());
    }
}