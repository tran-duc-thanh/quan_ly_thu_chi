package com.example.quan_ly_thu_chi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quan_ly_thu_chi.R;
import com.example.quan_ly_thu_chi.data.model.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private List<Menu> items;
    private MenuItemListener listener;

    public MenuAdapter() {
        items = new ArrayList<>();
    }

    public void setItems(List<Menu> items) {
        this.items = items;
    }

    public void setListener(MenuItemListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        return new MenuViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        Menu item = items.get(position);
        if (item == null) return;
        holder.imageButton.setImageResource(item.getIcon());
        holder.textView.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public Menu getItem (int position) {
        return items.get(position);
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageButton imageButton;
        private TextView textView;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            imageButton = itemView.findViewById(R.id.menuItemImgBtn);
            textView = itemView.findViewById(R.id.menuItemTv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null) listener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface MenuItemListener {
        void onItemClick (View view, int position);
    }

}
