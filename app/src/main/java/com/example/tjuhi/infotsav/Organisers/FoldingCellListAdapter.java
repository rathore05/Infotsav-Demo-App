package com.example.tjuhi.infotsav.Organisers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tjuhi.infotsav.R;
import com.ramotion.foldingcell.FoldingCell;

import java.util.HashSet;
import java.util.List;

/**
 * Created by tjuhi on 6/10/2017.
 */

public class FoldingCellListAdapter extends ArrayAdapter<Item> {
    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private View.OnClickListener defaultRequestBtnClickListener;


    public FoldingCellListAdapter(Context context, List<Item> objects) {
        super(context, 0, objects);
    }
    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get item for selected view
        Item item = getItem(position);
        // if cell is exists - reuse it, if not - create the new one from resource
        FoldingCell cell = (FoldingCell) convertView;
        ViewHolder viewHolder;
        if (cell == null) {
            viewHolder = new ViewHolder();
            LayoutInflater vi = LayoutInflater.from(getContext());
            cell = (FoldingCell) vi.inflate(R.layout.cell, parent, false);
           // viewHolder.contentRequestBtn = (TextView) cell.findViewById(R.id.content_request_btn);
            viewHolder.name=(TextView)cell.findViewById(R.id.name);
            viewHolder.designation=(TextView)cell.findViewById(R.id.desig);
            viewHolder.mName=(TextView)cell.findViewById(R.id.mname);
            viewHolder.mDesig=(TextView)cell.findViewById(R.id.mdesig);
            viewHolder.mail=(TextView)cell.findViewById(R.id.mail);
            viewHolder.btn2=(TextView) cell.findViewById(R.id.button2);

        }
        else {
            // for existing cell set valid valid state(without animation)
            if (unfoldedIndexes.contains(position)) {
                cell.unfold(true);
            } else {
                cell.fold(true);
            }
            viewHolder = (ViewHolder) cell.getTag();
        }
        viewHolder.name.setText(item.getmName());
        viewHolder.mName.setText(item.getmName());
        viewHolder.designation.setText(item.getmDesignation());
        viewHolder.mDesig.setText(item.getmDesignation());
        viewHolder.mail.setText(item.getmMail());
       if (item.getRequestBtnClickListener() != null) {

           viewHolder.btn2.setOnClickListener(item.getRequestBtnClickListener());
        } else {
            // (optionally) add "default" handler if no handler found in item

           viewHolder.btn2.setOnClickListener(defaultRequestBtnClickListener);
        }
        return cell;
    }


    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }

    public View.OnClickListener getDefaultRequestBtnClickListener() {
        return defaultRequestBtnClickListener;
    }

    public void setDefaultRequestBtnClickListener(View.OnClickListener defaultRequestBtnClickListener) {
        this.defaultRequestBtnClickListener = defaultRequestBtnClickListener;
    }
    private static class ViewHolder {
        TextView name,mName,mDesig,mail;
        TextView designation;
        TextView btn2;
    }
}
