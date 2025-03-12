package com.example.project.spinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.project.R;

import java.util.ArrayList;

public class CustomSpinnerAdapter extends ArrayAdapter<CustomSpinnerItem> {

    public CustomSpinnerAdapter(Context context, ArrayList<CustomSpinnerItem> list) {
        super(context, 0, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        // Inflate the view only if it's null to optimize performance
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_spinner_item, parent, false);
        }

        // Find TextView and set the item name
        TextView textViewName = convertView.findViewById(R.id.text_view);
        CustomSpinnerItem currentItem = getItem(position);

        if (currentItem != null) {
            textViewName.setText(currentItem.getName());
        }

        return convertView;
    }
}