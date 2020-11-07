package com.example.tes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Regist extends AppCompatActivity {

    String REGISTURL = "http://192.168.1.2/tes/regist.php";

    TextView username, password, repass;
    EditText et_username, et_password, et_rpassword;

    String usernames, passwords;

    ProgressDialog progress;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        username        = findViewById(R.id.tv_username);
        password        = findViewById(R.id.tv_pass);
        repass           = findViewById(R.id.tv_rpass);

        et_username     = findViewById(R.id.et_username);
        et_password     = findViewById(R.id.et_password);
        et_rpassword    = findViewById(R.id.et_rpass);

        progress = new ProgressDialog(Regist.this);

    }

    public void regist(View view) {
        cekedittext();
    }

    private void cekedittext() {
        //cek edit text jika kosong muncul toast
        if(TextUtils.isEmpty(et_username.getText().toString().trim())){
            Toast.makeText(this, "fill username!", Toast.LENGTH_SHORT).show();
        } else if(TextUtils.isEmpty(et_password.getText().toString())){
            Toast.makeText(this, "fill password!", Toast.LENGTH_SHORT).show();
        } else if(TextUtils.isEmpty(et_rpassword.getText().toString().trim())){
            Toast.makeText(this, "fill repeat password!", Toast.LENGTH_SHORT).show();
        } else if (!et_password.equals(et_rpassword)){
            Toast.makeText(this, "password not match!", Toast.LENGTH_SHORT).show();
        }else{
            senddata();
        }
    }

    private void senddata() {
        //jika edit text sudah terisi semua
        //mengirimkan data

        // menampilkan progres dialog ketika mengirimkan data
        progress.setMessage("Please Wait, We are Inserting Your Data on Server");
        progress.show();

        //mengambil data dari edittext
        usernames = et_username.getText().toString();
        passwords = et_password.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTURL, response -> {
            //menyembunyikan progress dialog setelah semua selesai
            progress.dismiss();

            // menampilkan respon dari server
            Toast.makeText(Regist.this, response, Toast.LENGTH_LONG).show();

            if (response.equals("Data Successfully Added!")){
                Intent intent = new Intent(Regist.this, Login.class);
                Toast.makeText(Regist.this, response, Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        }, error -> {
            //menyembunyikan progress dialog setelah semua selesai
            progress.dismiss();

            // menampilkan pesan error
            Toast.makeText(Regist.this, error.toString(), Toast.LENGTH_LONG).show();
            Log.d("onFailure", (error.getMessage()));
        }) {
            @Override
            protected Map<String, String> getParams() {

                // membuat Map String Params.
                Map<String, String> params = new HashMap<>();

                // menambahkan value
                params.put("username", usernames);
                params.put("password", passwords);
                return params;
            }
        };
        // Creating RequestQueue.
        requestQueue = Volley.newRequestQueue(Regist.this);

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);

    }
}
