package com.example.fastfoodapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;

import com.example.fastfoodapp.Activities.LoginActivity;

public class DialogBacktoLogin {
    private Context context;
    private Dialog dialog;
    private Button btnBack;

    public DialogBacktoLogin(Context context) {
        this.context = context;
    }

    public void show(){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_backtologin);
        btnBack = dialog.findViewById(R.id.btn_back_Login);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, LoginActivity.class));
                dialog.dismiss();
                ((Activity)context).finish();
            }
        });
        dialog.setCancelable(false);
        if (dialog.getWindow() != null)
        {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }
        dialog.show();
    }
    public void dismissDialog(){
        dialog.dismiss();
    }
}
