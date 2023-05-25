package com.example.quan_ly_thu_chi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quan_ly_thu_chi.R;
import com.example.quan_ly_thu_chi.data.SQLiteHelper;
import com.example.quan_ly_thu_chi.data.model.Menu;
import com.example.quan_ly_thu_chi.data.model.ThuChi;
import com.example.quan_ly_thu_chi.utils.DialogUtils;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private static final String NOTIFY = "Thông báo";
    private static final String MESSAGE_NOTIFY_DELETE = "Xác nhận xóa item";

    private Context context;
    private List<ThuChi> items;
    private ListItemListener listener;
    private SQLiteHelper db;

    public ListAdapter(Context context) {
        this.context = context;
        items = new ArrayList<>();
        db = new SQLiteHelper(context);
    }

    public void setItems(List<ThuChi> items) {
        this.items = items;
    }

    public void setListener(ListItemListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        ThuChi item = items.get(position);
        Menu menu = db.getOneMenu(item.getMenuId());
        if (item == null || menu == null) return;
        holder.icon.setImageResource(menu.getIcon());
        holder.icon.getResources().getDrawable(menu.getIcon()).setTint(holder.icon.getResources().getColor(menu.getColor()));
        holder.menuName.setText(menu.getName());
        holder.menuName.setTextColor(holder.menuName.getResources().getColor(menu.getColor()));
        holder.date.setText(item.getDate());
        holder.money.setText(String.valueOf(item.getMoney()));
        holder.btnDelete.setOnClickListener(view -> removeItem(position));
    }

    public void removeItem (int position) {
        ThuChi item = items.get(position);
        AlertDialog.Builder builder = DialogUtils.createAlertDialog(context, NOTIFY,
                String.format("%s: %s", MESSAGE_NOTIFY_DELETE, item.getDate()), R.drawable.icon_notify);
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            db.deleteThuChi(items.remove(position).getId());
            notifyDataSetChanged();
        });
        builder.setNegativeButton("No", (dialogInterface, i) -> {});
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public ThuChi getItem (int position) {
        return items.get(position);
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView menuName, date, money;
        private ImageView icon;
        private ImageButton btnDelete;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            menuName = itemView.findViewById(R.id.menuName);
            date = itemView.findViewById(R.id.date);
            money = itemView.findViewById(R.id.money);
            icon = itemView.findViewById(R.id.icon);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null) listener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface ListItemListener {
        void onItemClick (View view, int position);
    }
}
