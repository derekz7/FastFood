package com.example.fastfoodapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fastfoodapp.API.ApiService;
import com.example.fastfoodapp.CheckEmail;
import com.example.fastfoodapp.DialogLoading;
import com.example.fastfoodapp.Models.NguoiDung;
import com.example.fastfoodapp.R;
import com.example.fastfoodapp.Utils.Common;

import java.text.ParsePosition;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText edtEmail, edtPass;
    private CheckBox cbRemember;
    private Button btn_SignIn;
    private TextView tv_SignUp;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        onClick();
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        edtEmail.setText(sharedPreferences.getString("sdt", ""));
        edtPass.setText(sharedPreferences.getString("pass", ""));
        cbRemember.setChecked(sharedPreferences.getBoolean("checked", false));
    }

    private void onClick() {
        tv_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });
        btn_SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sdt = edtEmail.getText().toString();
                String pass= edtPass.getText().toString();
                DialogLoading dialogLoading = new DialogLoading(LoginActivity.this);
                if (sdt.length() ==0){
                    edtEmail.setError("SDT không được để trống!");
                }else if (pass.length() == 0){
                    edtPass.setError("Mật khẩu không được để trống!");
                }else {
                    dialogLoading.show();
                    ApiService.api.dangNhap(sdt,pass).enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            if(response.isSuccessful()){
                                dialogLoading.dismissDialog();
                                if (Boolean.TRUE.equals(response.body())){
                                    getUserLog(sdt);
                                    if (cbRemember.isChecked()){
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("sdt", sdt);
                                        editor.putString("pass", pass);
                                        editor.putBoolean("checked", true);
                                        editor.apply();
                                    }else {
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.remove("sdt");
                                        editor.remove(pass);
                                        editor.remove("checked");
                                        editor.apply();
                                    }
                                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                                    finish();
                                }else {
                                    Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            dialogLoading.dismissDialog();
                        }
                    });
                }
            }
        });
    }

    private void getUserLog(String sdt){
        ApiService.api.checkSDT(sdt).enqueue(new Callback<NguoiDung>() {
            @Override
            public void onResponse(Call<NguoiDung> call, Response<NguoiDung> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        Common.userLog = response.body();
                    }
                }
            }

            @Override
            public void onFailure(Call<NguoiDung> call, Throwable t) {

            }
        });
    }

    private void init(){
        edtEmail = findViewById(R.id.edt_email);
        edtPass = findViewById(R.id.edt_password);
        cbRemember = findViewById(R.id.cb_remember);
        btn_SignIn = findViewById(R.id.btn_signIn);
        tv_SignUp = findViewById(R.id.tv_signUp);
    }
}