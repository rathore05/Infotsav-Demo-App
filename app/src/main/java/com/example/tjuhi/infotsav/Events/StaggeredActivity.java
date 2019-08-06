package com.example.tjuhi.infotsav.Events;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Transition;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.example.tjuhi.infotsav.R;


public class StaggeredActivity extends AppCompatActivity {
    public static String[] nameList;
    //  Toolbar mToolbar;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered);
        Transition exitTrans = new Explode();
        getWindow().setExitTransition(exitTrans);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(CoverFlowActivity.t);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.masonry_grid);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        final MasonryAdapter adapter = new MasonryAdapter(this);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new MasonryAdapter.eventHandler() {
            @Override
            public void onclick(int position) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(StaggeredActivity.this);
                Intent intent = new Intent(StaggeredActivity.this, ScrollingActivity.class);

                Bundle extras = new Bundle();

                extras.putString("id", adapter.nameList[position]);
                extras.putString("city", Integer.toString(adapter.imgList[position]));
                intent.putExtras(extras);
                startActivity(intent,options.toBundle());
            }
        });


        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        mRecyclerView.addItemDecoration(decoration);

    }



}
