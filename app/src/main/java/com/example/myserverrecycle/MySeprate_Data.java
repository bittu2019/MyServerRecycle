package com.example.myserverrecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MySeprate_Data extends AppCompatActivity {

    private TextView tvMyusrid;
    private TextView tvMyid;
    private TextView tvMytitle;
    private TextView tvMybody;
    private ImageView imgImageview;
    String key3,key4;
    String key1,key2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_seprate_data);
        tvMyusrid = (TextView) findViewById(R.id.tv_myusrid);
        tvMyid = (TextView) findViewById(R.id.tv_myid);
        tvMytitle = (TextView) findViewById(R.id.tv_mytitle);
        tvMybody = (TextView) findViewById(R.id.tv_mybody);
        imgImageview = (ImageView) findViewById(R.id.img_imageview);

        Glide.with(getApplicationContext()).load("http://goo.gl/gEgYUd").into(imgImageview);
        key1=getIntent().getStringExtra("userid");
        key2=getIntent().getStringExtra("id");
        key3=getIntent().getStringExtra("title");
        key4=getIntent().getStringExtra("body");

        tvMyusrid.setText(key1);
        tvMyid.setText(key2);
        tvMytitle.setText(key3);
        tvMybody.setText(key4);
    }
}