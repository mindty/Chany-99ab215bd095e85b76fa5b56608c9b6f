package com.example.tes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String UPDATEURL = "http://192.168.1.2/tes/updatestate.php";

    LinearLayout iflogin, ifnot;
    TextView time, username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iflogin = findViewById(R.id.iflogin);
        ifnot   = findViewById(R.id.ifnotlogin);

        time        = findViewById(R.id.time_login);
        username    = findViewById(R.id.hellouser);

    }

    public void logout(View view) {
        updateloginstate();

        Intent intent = new Intent(this, Login.class);
        startActivity(intent);

    }

    private void updateloginstate() {
        String login_state = "0";
        String usernames = username.toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPDATEURL, response -> {

        }, error -> {
            // menampilkan pesan error
            Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            Log.d("onFailure", (error.getMessage()));
        }) {
            @Override
            protected Map<String, String> getParams() {

                // membuat Map String Params.
                Map<String, String> params = new HashMap<>();

                // menambahkan value
                params.put("username", usernames);
                params.put("login_state", login_state);
                return params;
            }
        };
        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);

    }

    public void login(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}