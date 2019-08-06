package com.example.tjuhi.infotsav.Sponsors;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.tjuhi.infotsav.R;

public class CardsDataAdapter extends ArrayAdapter<String> {

    public CardsDataAdapter(Context context) {
        super(context, R.layout.card_content);
    }

    @Override
    public View getView(int position, final View contentView, ViewGroup parent){
        ImageView i=(ImageView)(contentView.findViewById(R.id.img));
        //TextView v = (TextView)(contentView.findViewById(R.id.content));
        i.setImageResource(Integer.parseInt(getItem(position)));
       // v.setText(getItem(position));
        return contentView;
    }

}

