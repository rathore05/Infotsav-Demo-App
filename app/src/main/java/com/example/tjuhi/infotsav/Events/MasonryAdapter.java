package com.example.tjuhi.infotsav.Events;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tjuhi.infotsav.R;

/**
 * Created by shivam on 12-06-2017.
 */
public class MasonryAdapter extends RecyclerView.Adapter<MasonryAdapter.MasonryView> {
public static Bundle bundle;
    private Context context;

    eventHandler mClickHandler;

    public interface eventHandler{
        void onclick(int position);
    }

    int[] imgList = CoverFlowActivity.l;

    String[] nameList = CoverFlowActivity.s;

    public MasonryAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MasonryView onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        MasonryView masonryView = new MasonryView(layoutView);
        return masonryView;
    }

    @Override
    public void onBindViewHolder(MasonryView holder, int position) {
       // holder.imageView.setImageResource(imgList[position]);
        Glide.with(context.getApplicationContext()).load(imgList[position]).into(holder.imageView);
        holder.textView.setText(nameList[position]);
    }

    @Override
    public int getItemCount() {
        return nameList.length;
    }

    class MasonryView extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;

        public MasonryView(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            imageView = (ImageView) itemView.findViewById(R.id.img);
            textView = (TextView) itemView.findViewById(R.id.img_name);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mClickHandler.onclick(position);
        }
    }

    public void setOnItemClickListener(final eventHandler mClickHandler){
    this.mClickHandler = mClickHandler;
    }
}
