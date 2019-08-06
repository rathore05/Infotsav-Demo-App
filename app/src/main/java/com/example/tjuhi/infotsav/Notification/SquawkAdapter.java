/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.tjuhi.infotsav.Notification;

import android.database.Cursor;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tjuhi.infotsav.Notification.provider.SquawkContract;
import com.example.tjuhi.infotsav.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Converts cursor data for squawk messages into visible list items in a RecyclerView
 */
public class SquawkAdapter extends RecyclerView.Adapter<SquawkAdapter.SquawkViewHolder> {


    private Cursor mData;
    private static SimpleDateFormat sDateFormat = new SimpleDateFormat("dd MMM");


    private static final long MINUTE_MILLIS = 1000 * 60;
    private static final long HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final long DAY_MILLIS = 24 * HOUR_MILLIS;


    @Override
    public SquawkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_squawk_list, parent, false);

        SquawkViewHolder vh = new SquawkViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(SquawkViewHolder holder, int position) {
        mData.moveToPosition(position);
String index=mData.getString(NotifMain.COL_NUM_COLUMN_ID);
        String message = mData.getString(NotifMain.COL_NUM_MESSAGE);
        String author = mData.getString(NotifMain.COL_NUM_AUTHOR);
        String authorKey = mData.getString(NotifMain.COL_NUM_AUTHOR_KEY);
        int a=Integer.parseInt(index);
        // Get the date for displaying
        long dateMillis = mData.getLong(NotifMain.COL_NUM_DATE);
        String date = "";
        long now = System.currentTimeMillis();
holder.itemView.setTag(a);
        // Change how the date is displayed depending on whether it was written in the last minute,
        // the hour, etc.
        if (now - dateMillis < (DAY_MILLIS)) {
            if (now - dateMillis < (HOUR_MILLIS)) {
                long minutes = Math.round((now - dateMillis) / MINUTE_MILLIS);
                date = String.valueOf(minutes) + "m";
            } else {
                long minutes = Math.round((now - dateMillis) / HOUR_MILLIS);
                date = String.valueOf(minutes) + "h";
            }
        } else {
            Date dateDate = new Date(dateMillis);
            date = sDateFormat.format(dateDate);
        }

        // Add a dot to the date string
        date = "\u2022 " + date;

        holder.messageTextView.setText(message);
        holder.authorTextView.setText(author);
        holder.dateTextView.setText(date);

        // Choose the correct, and in this case, locally stored asset for the instructor. If there
        // were more users, you'd probably download this as part of the message.
        switch (authorKey) {
            case SquawkContract.TECH_KEY:
                holder.authorImageView.setImageResource(R.drawable.asser);
                break;
            case SquawkContract.MANAGERIAL_KEY:
                holder.authorImageView.setImageResource(R.drawable.cezanne);
                break;
            case SquawkContract.QUIZ_KEY:
                holder.authorImageView.setImageResource(R.drawable.jlin);
                break;
            case SquawkContract.ROBOTICS_KEY:
                holder.authorImageView.setImageResource(R.drawable.lyla);
                break;
            case SquawkContract.GAMIACS_KEY:
                holder.authorImageView.setImageResource(R.drawable.nikita);
                break;
            default:
                holder.authorImageView.setImageResource(R.mipmap.ic_launcher);
        }
    }

    @Override
    public int getItemCount() {
        if (null == mData) return 0;
        return mData.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        mData = newCursor;
        notifyDataSetChanged();
    }

    public class SquawkViewHolder extends RecyclerView.ViewHolder {
        final TextView authorTextView;
        final TextView messageTextView;
        final TextView dateTextView;
        final ImageView authorImageView;

        public SquawkViewHolder(View layoutView) {
            super(layoutView);
            authorTextView = (TextView) layoutView.findViewById(R.id.author_text_view);
            messageTextView = (TextView) layoutView.findViewById(R.id.message_text_view);
            dateTextView = (TextView) layoutView.findViewById(R.id.date_text_view);
            authorImageView = (ImageView) layoutView.findViewById(
                    R.id.author_image_view);
        }
    }
}
