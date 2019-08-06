package com.example.tjuhi.infotsav.Workshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.tjuhi.infotsav.R;
import com.wajahatkarim3.easyflipview.EasyFlipView;

public class Workshop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshop);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ImageView imgFront = (ImageView) findViewById(R.id.FrontCard);
        ImageView imgBack = (ImageView) findViewById(R.id.BackCard);
        ImageView imgFront1 = (ImageView) findViewById(R.id.imgFrontCard);
        ImageView imgBack1 = (ImageView) findViewById(R.id.imgBackCard);

        Glide.with(this).load(R.drawable.iotabout).into(imgFront1);
        Glide.with(this).load(R.drawable.iot).into(imgBack1);
        Glide.with(this).load(R.drawable.hack).into(imgFront);
        Glide.with(this).load(R.drawable.hackabt).into(imgBack);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final EasyFlipView easyFlipView = (EasyFlipView) findViewById(R.id.easyFlipView);
        final EasyFlipView easyFlipView2 = (EasyFlipView) findViewById(R.id.easyFlipView2);
        easyFlipView.setFlipDuration(1000);
        easyFlipView2.setFlipDuration(1000);
        easyFlipView2.setFlipEnabled(true);
        easyFlipView.setFlipEnabled(true);

        imgFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                easyFlipView2.flipTheView();
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                easyFlipView2.flipTheView();
            }
        });
        imgFront1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                easyFlipView.flipTheView();

            }
        });

        imgBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                easyFlipView.flipTheView();
            }
        });
    }
}
