package com.skuy.deathnote;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.skuy.deathnote.preferences.Session;

import androidx.appcompat.app.AppCompatActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    EditText edtUsername, edtPassword;
    TextView login;
    Button btnLogin;

    String username = "admin";
    String password = "admin";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("onCreate", "mulai");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (Session.init(LoginActivity.this)
                .getBoolean("login") == true ) {
                    finish();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
        }

        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        login = findViewById(R.id.tv_login);

        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
//        btnLogin.setOnLongClickListener(this);
        Log.e("onCreate", "selesai");

    }

    @Override
    public void onClick(View view) {
        Log.e("onClick", "mulai");
        if (username.equals(edtUsername.getText().toString()) && password.equals(edtPassword.getText().toString()) ) {
            Intent intent = new Intent(this, MainActivity.class);

            Session.init(LoginActivity.this)
                    .set("username", username.toString())
                    .set("login", true);

            finish();
            startActivity(intent);
            Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Login Gagal", Toast.LENGTH_SHORT).show();
        }
        Log.e("onClick", "selesai");
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v,
//                                    ContextMenu.ContextMenuInfo menuInfo) {
//        Log.v("onCreateContextMenu", "Mulai");
//        super.onCreateContextMenu(menu, v, menuInfo);
//        Log.e("onCreateContextMenu", "Selesai");
//    }
//
//    @Override
//    protected void onStart() {
//        Log.e("onStart", "mulai");
//        super.onStart();
//        Log.e("onStart", "selesai");
//    }
//
//    @Override
//    protected void onRestart() {
//        Log.e("onRestart", "mulai");
//        super.onRestart();
//        Log.e("onRestart", "selesai");
//    }
//
//    @Override
//    protected void onResume() {
//        Log.e("onResume", "mulai");
//        super.onResume();
//        Log.e("onResume", "selesai");
//    }
//
//    @Override
//    protected void onPause() {
//        Log.e("onPause", "mulai");
//        super.onPause();
//        Log.e("onPause", "selesai");
//    }
//
//    @Override
//    protected void onStop() {
//        Log.e("onStop", "mulai");
//        super.onStop();
//        Log.e("onStop", "selesai");
//    }
//
//    @Override
//    protected void onDestroy() {
//        Log.e("onDestroy", "mulai");
//        super.onDestroy();
//        Log.e("onDestroy", "selesai");
//    }

//    @Override
//    public boolean onLongClick(View view) {
////        Log.e("onLongClick", "mulai");
//        Toast.makeText(this, "Login sukses", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//        return false;
//    }
}
