package com.example.quan_ly_thu_chi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.quan_ly_thu_chi.R;

public class SpinnerAdapterIcon extends BaseAdapter {

    private Context context;

    private int[] icons;

    public SpinnerAdapterIcon(Context context, int[] icons) {
        this.context = context;
        this.icons = icons;
    }

    @Override
    public int getCount() {
        return icons.length;
    }

    @Override
    public Object getItem(int i) {
        return icons[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewItem = LayoutInflater.from(context).inflate(R.layout.item_spinner_icon, viewGroup, false);
        ImageView img = viewItem.findViewById(R.id.spinnerItem);
        img.setImageResource(icons[i]);
        return viewItem;
    }
}
