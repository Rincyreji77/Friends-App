package com.example.friendsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4;
    AppCompatButton b1;
    String getName,getFriendname,getNickname,getDescribe;
    String apiUrl="https://dummyapifriends.herokuapp.com/adddata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=(EditText)findViewById(R.id.yrnm);
        ed2=(EditText)findViewById(R.id.yrfrndnm);
        ed3=(EditText)findViewById(R.id.yrfrndnick);
        ed4=(EditText)findViewById(R.id.describe);
        b1=(AppCompatButton)findViewById(R.id.submit);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getName=ed1.getText().toString();
                getFriendname=ed2.getText().toString();
                getNickname=ed3.getText().toString();
                getDescribe=ed4.getText().toString();
                StringRequest sr=new StringRequest(Request.Method.POST, apiUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                        ed1.setText("");
                        ed2.setText("");
                        ed3.setText("");
                        ed4.setText("");

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> hm=new HashMap<>();
                        hm.put("name",getName);
                        hm.put("friendName",getFriendname);
                        hm.put("friendNickName",getNickname);
                        hm.put("DescribeYourFriend",getDescribe);
                        return hm;
                    }
                };
                RequestQueue rq= Volley.newRequestQueue(getApplicationContext());
                rq.add(sr);

            }
        });
    }
}