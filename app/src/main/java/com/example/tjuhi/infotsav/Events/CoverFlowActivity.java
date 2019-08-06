package com.example.tjuhi.infotsav.Events;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.TextSwitcher;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.tjuhi.infotsav.R;

import java.util.ArrayList;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class CoverFlowActivity extends ActionBarActivity {

    private FeatureCoverFlow mCoverFlow;
    private CoverFlowAdapter mAdapter;
    private ArrayList<Utils> mData = new ArrayList<>(0);
    private TextSwitcher mTitle;
    public static String[] s;
    public static int[] l;
    public static String t;
    //Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover_flow);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mData.add(new Utils(R.drawable.image_1, R.string.title1));
        mData.add(new Utils(R.drawable.image_2, R.string.title2));
        mData.add(new Utils(R.drawable.image_3, R.string.title3));
        mData.add(new Utils(R.drawable.image_4, R.string.title4));
        mData.add(new Utils(R.drawable.image_5, R.string.title5));
        mData.add(new Utils(R.drawable.image_6, R.string.title6));




        mTitle = (TextSwitcher) findViewById(R.id.title);

        mTitle.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                LayoutInflater inflater = LayoutInflater.from(CoverFlowActivity.this);
                return inflater.inflate(R.layout.item_title, null);
            }
        });

        Animation in = AnimationUtils.loadAnimation(this, R.anim.slide_in_top);
        Animation out = AnimationUtils.loadAnimation(this, R.anim.slide_out_bottom);

        mTitle.setAnimation(in);
        mTitle.setAnimation(out);

        mAdapter = new CoverFlowAdapter(this);
        mAdapter.setData(mData);

        mCoverFlow = (FeatureCoverFlow) findViewById(R.id.coverflow);
        mCoverFlow.setAdapter(mAdapter);

        mCoverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CoverFlowActivity.this, getResources().getString(mData.get(position).titleResId),
                        Toast.LENGTH_SHORT).show();
                switch (getResources().getString(mData.get(position).titleResId)) {
                    case "Technical Events":
                        t="Technical Events";
                        s = new String[]{"WWWizard", "APP Studio", "Code Rush", "Escape", "Bug Spot", "Scrud", "Jobs"};
                        l = new int[]{R.drawable.two, R.drawable.one, R.drawable.three, R.drawable.four,
                                R.drawable.five, R.drawable.six, R.drawable.seven};
                        break;
                    case "Managerial Events":
                        t="Managerial Events";
                        s = new String[]{"Sameeksha", "Innovation Through Waste", "BrandSome", "Pinnacle", "Tomorrow Cities", "Aadhar"};
                        l = new int[]{R.drawable.two, R.drawable.one, R.drawable.three, R.drawable.four,
                                R.drawable.five, R.drawable.six};
                        break;
                    case "Simulation Events":
                        t="Simulation Events";
                        s = new String[]{"Job Bureau", "FOREX", "Stock Sutra", "Trova Trace"};
                        l = new int[]{R.drawable.two, R.drawable.one, R.drawable.three, R.drawable.four};
                        break;
                    case "Quiz":
                        t="Quiz";
                        s = new String[]{"Programming Quiz", "Enigma"};
                        l = new int[]{R.drawable.two, R.drawable.one};
                        break;
                    case "RoboFiesta":
                        t="RoboFiesta";
                        s = new String[]{"COURSE CHASER", "BLAZING WHEELS", "ROBO SOCCER", "ROBO WAR"};
                        l = new int[]{R.drawable.two, R.drawable.one, R.drawable.three, R.drawable.four};
                        break;
                    case "Gamics":
                        t="Gamics";
                        s = new String[]{"Gamiacs"};
                        break;
                    default:
                        break;
                }
                startActivity(new Intent(CoverFlowActivity.this, StaggeredActivity.class));
                CoverFlowActivity.this.overridePendingTransition(R.anim.bottom_in,R.anim.top_out);
            }
        });

        mCoverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                mTitle.setText(getResources().getString(mData.get(position).titleResId));
            }

            @Override
            public void onScrolling() {

            }
        });
    }
}
