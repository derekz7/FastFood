package com.example.fastfoodapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fastfoodapp.API.ApiService;
import com.example.fastfoodapp.DialogLoading;
import com.example.fastfoodapp.Models.NguoiDung;
import com.example.fastfoodapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    private Button btn_backToLogin, btn_signUp;
    private EditText edt_Username, edt_NameDK, edt_SDT, edt_password1, edt_password2;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
        onCLick();
    }

    private void onCLick() {
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edt_Username.getText().toString();
                String name = edt_NameDK.getText().toString();
                String SDT = edt_SDT.getText().toString();
                String pass1 = edt_password1.getText().toString();
                String pass2 = edt_password2.getText().toString();
                if (username.length() == 0 || name.length() == 0 || SDT.length() == 0 || pass1.length() == 0 || pass2.length() == 0) {
                    Toast.makeText(SignUpActivity.this, "Các trường không được để trống!", Toast.LENGTH_SHORT).show();
                } else if (!pass2.equals(pass1)) {
                    edt_password2.setError("Mật khẩu không khớp");
                } else {
                    DialogLoading dialogLoading = new DialogLoading(SignUpActivity.this);
                    dialogLoading.show();

                    //check tai khoan ton tai
                    ApiService.api.checkUsernameExist(username).enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            if (response.isSuccessful()) {
                                dialogLoading.dismissDialog();
                                if (Boolean.TRUE.equals(response.body())) {
                                   edt_Username.setError("Tên tài khoản đã tồn tại");
                                }else {
                                    dialogLoading.show();

                                    //check sdt
                                    ApiService.api.checkSDTExist(SDT).enqueue(new Callback<Boolean>() {
                                        @Override
                                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                            if (response.isSuccessful())
                                                dialogLoading.dismissDialog();
                                            {
                                                if (Boolean.TRUE.equals(response.body())) {
                                                    edt_SDT.setError("Số điện thoại đã được sử dụng");
                                                } else {
                                                    dialogLoading.show();
                                                    NguoiDung nguoiDung = new NguoiDung(username, name, SDT, pass1);

                                                    //dang ky
                                                    ApiService.api.dangKy(nguoiDung).enqueue(new Callback<Integer>() {
                                                        @Override
                                                        public void onResponse(Call<Integer> call, Response<Integer> response) {
                                                            if (response.body() != null) {
                                                                dialogLoading.dismissDialog();
                                                                if (response.body() > 0) {
                                                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                                                    editor.putString("username", username);
                                                                    editor.putString("pass", pass1);
                                                                    editor.putBoolean("checked", true);
                                                                    editor.apply();
                                                                    startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                                                                    finish();
                                                                    Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_LONG).show();
                                                                } else {
                                                                    Toast.makeText(getApplicationContext(), "Không thành công", Toast.LENGTH_LONG).show();
                                                                }
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<Integer> call, Throwable t) {
                                                            dialogLoading.dismissDialog();
                                                            Toast.makeText(getApplicationContext(), "Đăng ký thất bại", Toast.LENGTH_LONG).show();
                                                        }
                                                    });
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


    private void init() {
        btn_backToLogin = findViewById(R.id.btn_backToLogin);
        btn_signUp = findViewById(R.id.btn_signUp);
        edt_Username = findViewById(R.id.edtUsernameDK);
        edt_NameDK = findViewById(R.id.edt_NameDK);
        edt_SDT = findViewById(R.id.edt_SDT);
        edt_password1 = findViewById(R.id.edt_password1);
        edt_password2 = findViewById(R.id.edt_password2);
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
    }
}