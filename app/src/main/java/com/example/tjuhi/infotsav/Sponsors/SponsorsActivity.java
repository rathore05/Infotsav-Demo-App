package com.example.tjuhi.infotsav.Sponsors;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.tjuhi.infotsav.R;
import com.wenchao.cardstack.CardStack;


public class SponsorsActivity extends AppCompatActivity {
    private CardStack mCardStack;
    private boolean mEnableLoop;
    private CardsDataAdapter mCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mCardStack = (CardStack) findViewById(R.id.container);

        mCardStack.setContentResource(R.layout.card_content);
        mCardAdapter = new CardsDataAdapter(getApplicationContext());
//        mCardStack.setStackMargin(20);
        for (int i = 0; i < 7; i++) {
            mCardAdapter.add(Integer.toString(R.drawable.ic_ecommerce));
            mCardAdapter.add(Integer.toString(R.drawable.ic_delivery));
            mCardAdapter.add(Integer.toString(R.drawable.ic_design));
            mCardAdapter.add(Integer.toString(R.drawable.ic_development));
            mCardAdapter.add(Integer.toString(R.drawable.ic_fintech));
            mCardAdapter.add(Integer.toString(R.drawable.ic_internet));
            mCardAdapter.add(Integer.toString(R.drawable.ic_development));
            mCardStack.setAdapter(mCardAdapter);
            continue;
        }


        // mCardStack.reset(true);


    }

    public void setEnableLoop(boolean enableLoop) {
        mEnableLoop = enableLoop;
    }
}
