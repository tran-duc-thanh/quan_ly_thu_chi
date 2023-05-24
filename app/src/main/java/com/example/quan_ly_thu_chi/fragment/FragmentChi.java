package com.example.quan_ly_thu_chi.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quan_ly_thu_chi.FragmentAddOrEditMenu;
import com.example.quan_ly_thu_chi.R;
import com.example.quan_ly_thu_chi.adapter.MenuAdapter;
import com.example.quan_ly_thu_chi.data.SQLiteHelper;
import com.example.quan_ly_thu_chi.data.model.Menu;
import com.example.quan_ly_thu_chi.utils.Constants;

import java.util.List;

public class FragmentChi extends Fragment implements MenuAdapter.MenuItemListener {

    private Menu menu = null;
    private Integer index = null;
    private EditText date, note, money;
    private Button btnSave;
    private ImageButton btnAdd;
    private RecyclerView recyclerView;
    private MenuAdapter adapter;
    private SQLiteHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chi, container, false);
        initView(view);
        btnSave.setOnClickListener(view1 -> {
            Toast.makeText(view.getContext(), "save khoản chi", Toast.LENGTH_SHORT).show();
        });
        btnAdd.setOnClickListener(view1 -> {
            Intent intent = new Intent(view.getContext(), FragmentAddOrEditMenu.class);
            intent.putExtra("status", 0);
            startActivity(intent);
        });
        return view;
    }

    private void initView (View view) {
        db = new SQLiteHelper(getContext());
        recyclerView = view.findViewById(R.id.recycleViewChi);
        GridLayoutManager manager=new GridLayoutManager(view.getContext(),4);
        recyclerView.setLayoutManager(manager);
        adapter = new MenuAdapter(getContext());
        adapter.setItems(getListMenu());
        recyclerView.setAdapter(adapter);
        adapter.setListener(this);
        date = view.findViewById(R.id.dateChi);
        note = view.findViewById(R.id.noteChi);
        money = view.findViewById(R.id.tienChi);
        btnSave = view.findViewById(R.id.btnSaveChi);
        btnAdd = view.findViewById(R.id.btnAddMenu);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onItemClick(View view, int position) {
        menu = adapter.getItem(position);
        if (index != null) {
            recyclerView.getChildAt(index).setBackgroundColor(ContextCompat.getColor(view.getContext() ,R.color.white));
        }
        recyclerView.getChildAt(position).setBackgroundColor(ContextCompat.getColor(view.getContext() ,R.color.darkturquoise));
        index = position;
        Toast.makeText(view.getContext(), menu.getName(), Toast.LENGTH_SHORT).show();
    }

    private List<Menu> getListMenu () {
        List<Menu> results = db.getAllMenuByStatus(Constants.STATUS.CHI);
        return results;
    }

}
