package com.example.tjuhi.infotsav.Events;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Transition;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tjuhi.infotsav.R;

public class ScrollingActivity extends AppCompatActivity {
    CollapsingToolbarLayout toolbarLayout;
    Toolbar toolbar;

    TextView date, time, venue, org1, org2;
    ImageView org1_phone, org2_phone;

    String phone_1, phone_2;

    public static String ID;
    ImageView imageView;
    public static String string;
    public static String EXTRA_DRAWABLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Transition enterTrans = new Explode();
        getWindow().setEnterTransition(enterTrans);
        date = (TextView) findViewById(R.id.date);
        time = (TextView) findViewById(R.id.time);
        venue = (TextView) findViewById(R.id.venue);
        org1 = (TextView) findViewById(R.id.org_1);
        org2 = (TextView) findViewById(R.id.org_2);

        org1_phone = (ImageView) findViewById(R.id.img_btn);
        org2_phone = (ImageView) findViewById(R.id.img_btn_2);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        imageView = (ImageView) findViewById(R.id.backdrop);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        Bundle bundle = getIntent().getExtras();
        // int drawable = getIntent().getExtras().getInt("city");
        string = bundle.getString("id");
        int drawable = Integer.parseInt(bundle.getString("city"));
        imageView.setImageResource(drawable);
        Toast.makeText(ScrollingActivity.this, string, Toast.LENGTH_SHORT).show();
        if (getSupportActionBar() != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(string);
        }

        org1_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call(phone_1);
            }
        });

        org2_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call(phone_2);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        switch (string) {
            case "Code Rush":
                time.setText(R.string.code_rush_tym);
                date.setText(R.string.code_rush_date);
                venue.setText(R.string.code_rush_venue);
                org1.setText(R.string.code_rush_org_1);
                org2.setText(R.string.code_rush_org_2);
                phone_1 = Integer.toString(R.string.code_rush_org_1_phone);
                phone_2 = Integer.toString(R.string.code_rush_org_2_phone);
                break;
            case "Scrud":
                time.setText(R.string.scrud_time);
                date.setText(R.string.scrud_date);
                venue.setText(R.string.scrud_venue);
                org1.setText(R.string.scrud_org_1);
                org2.setText(R.string.scrud_org_2);
                phone_1 = Integer.toString(R.string.scrud_org_1_phone);
                phone_2 = Integer.toString(R.string.scrud_org_2_phone);
                break;
            default:
                break;

        }
    }

    public void call(String s) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + s));
        startActivity(intent);
    }
}
