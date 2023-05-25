package com.example.quan_ly_thu_chi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.quan_ly_thu_chi.adapter.ListAdapter;
import com.example.quan_ly_thu_chi.data.SQLiteHelper;
import com.example.quan_ly_thu_chi.data.model.ThuChi;
import com.example.quan_ly_thu_chi.utils.Constants;

public class ListActivity extends AppCompatActivity implements ListAdapter.ListItemListener{

    private ImageButton btnBack;
    private RecyclerView recyclerView;
    private ListAdapter adapter;
    private SQLiteHelper db;
    private Intent data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = getIntent();
        db = new SQLiteHelper(this);
        setContentView(R.layout.activity_list);
        btnBack = findViewById(R.id.btnBack);
        recyclerView = findViewById(R.id.recycleViewChi);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        adapter = new ListAdapter(this);
        adapter.setListener(this);
        adapter.setItems(db.searchThuChi(data.getStringExtra("sDate"), data.getStringExtra("eDate"), data.getIntExtra("status", -1)));
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        btnBack.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("indexF", 1);
            startActivity(intent);
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        ThuChi item = adapter.getItem(position);
        Intent intent = new Intent(getApplicationContext(), EditThuChiActivity.class);
        intent.putExtra("id", item.getId());
        intent.putExtra("sDate", data.getStringExtra("sDate"));
        intent.putExtra("eDate", data.getStringExtra("eDate"));
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        db.getAllMenuByStatus(Constants.STATUS.CHI).forEach(rs -> getResources().getDrawable(rs.getIcon()).setTint(getResources().getColor(rs.getColor())));
        db.getAllMenuByStatus(Constants.STATUS.THU).forEach(rs -> getResources().getDrawable(rs.getIcon()).setTint(getResources().getColor(rs.getColor())));
        super.onResume();
        adapter.setItems(db.searchThuChi(data.getStringExtra("sDate"), data.getStringExtra("eDate"), data.getIntExtra("status", -1)));
    }
}