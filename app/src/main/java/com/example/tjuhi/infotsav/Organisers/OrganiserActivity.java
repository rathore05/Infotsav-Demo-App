package com.example.tjuhi.infotsav.Organisers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tjuhi.infotsav.R;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;

public class OrganiserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ListView theListView = (ListView) findViewById(R.id.mainListView);

        final ArrayList<Item> items = Item.getTestingList();


        items.get(0).setRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String s="9461155105";
                startCall(s);

            }
        });
        items.get(1).setRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String s="77422680011";
                startCall(s);
            }
        });

        // create custom adapter that holds elements and their state (we need hold a id's of unfolded elements for reusable elements)
        final FoldingCellListAdapter adapter = new FoldingCellListAdapter(this, items);
        theListView.setAdapter(adapter);


        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                ((FoldingCell) view).toggle(false);
                 adapter.registerToggle(pos);
            }
        });
    }
    public void startCall(String s){
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + s));
        startActivity(intent);
    }
}
