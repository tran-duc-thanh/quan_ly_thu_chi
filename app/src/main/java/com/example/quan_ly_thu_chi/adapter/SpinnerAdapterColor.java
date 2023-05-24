package com.example.quan_ly_thu_chi.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.core.app.ActivityCompat;

import com.example.quan_ly_thu_chi.R;

public class SpinnerAdapterColor extends BaseAdapter {

    private Context context;
    public static int[] colors = {R.color.black, R.color.red , R.color.purple, R.color.fuchsia, R.color.green, R.color.orange, R.color.yellow, R.color.green, R.color.lime};

    public SpinnerAdapterColor(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return colors.length;
    }

    @Override
    public Object getItem(int i) {
        return colors[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewItem = LayoutInflater.from(context).inflate(R.layout.item_spinner_icon, viewGroup, false);
        ImageView img = viewItem.findViewById(R.id.spinnerItem);
        img.setBackgroundColor(context.getResources().getColor(colors[i]));
        return viewItem;
    }
}
