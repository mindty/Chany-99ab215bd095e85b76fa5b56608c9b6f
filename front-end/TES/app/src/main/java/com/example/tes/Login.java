package com.example.tes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    String LOGINURL = "http://192.168.1.2/tes/login.php";

    SharedPreferences sp;
    EditText et_username, et_password;

    String username, password;
    ProgressDialog progress;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_password = findViewById(R.id.et_password);
        et_username     = findViewById(R.id.et_username);

        sp = getSharedPreferences("login",MODE_PRIVATE);

        if(sp.getBoolean("logged",false)){
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        }

        progress = new ProgressDialog(Login.this);
    }

    public void login(View view) {
        //cek apakah edittext username dan password sudah terisi
        if (TextUtils.isEmpty(et_password.getText().toString()) || TextUtils.isEmpty(et_username.getText().toString())){
            Toast.makeText(this, "fill in all data!", Toast.LENGTH_SHORT).show();
        } else {

            // menampilkan progres dialog ketika mengirimkan data
            progress.setMessage("Please Wait . . .");
            progress.show();

            //mengambil data dari edittext
            username = et_username.getText().toString();
            password = et_password.getText().toString();

            String time = java.text.DateFormat.getDateTimeInstance().format(new Date());
            String loginstate = "1";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGINURL, response -> {
                //menyembunyikan progress dialog setelah semua selesai
                progress.dismiss();

                // menampilkan respon dari server
                Toast.makeText(Login.this, response, Toast.LENGTH_LONG).show();

                if (response.equals("Login Successfully")) {

                    Intent intent = new Intent(Login.this, MainActivity.class);
                    Bundle b = new Bundle();

                    b.putString("username", et_username.getText().toString());
                    intent.putExtras(b);
                    startActivity(intent);
                    sp.edit().putBoolean("logged",true).apply();

                }
            }, error -> {
                //menyembunyikan progress dialog setelah semua selesai
                progress.dismiss();

                // menampilkan pesan error
                Toast.makeText(Login.this, error.toString(), Toast.LENGTH_LONG).show();
                Log.d("onFailure", (error.getMessage()));
            }) {
                @Override
                protected Map<String, String> getParams() {

                    // membuat Map String Params.
                    Map<String, String> params = new HashMap<>();

                    // mengirim value
                    params.put("username", username);
                    params.put("password", password);
                    params.put("login_time", time);
                    params.put("login_state", loginstate);

                    return params;
                }
            };
            // Creating RequestQueue.
            requestQueue = Volley.newRequestQueue(Login.this);

            // Adding the StringRequest object into requestQueue.
            requestQueue.add(stringRequest);
        }
    }

    public void register(View view) {
        Intent intent = new Intent(this, Regist.class);
        startActivity(intent);
    }
}