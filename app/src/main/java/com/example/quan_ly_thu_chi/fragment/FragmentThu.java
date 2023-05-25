package com.example.quan_ly_thu_chi.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
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
import com.example.quan_ly_thu_chi.data.model.ThuChi;
import com.example.quan_ly_thu_chi.utils.Constants;
import com.example.quan_ly_thu_chi.utils.DataUtils;
import com.example.quan_ly_thu_chi.utils.DateUtils;

import java.util.List;

public class FragmentThu extends Fragment implements MenuAdapter.MenuItemListener {

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
        View view = inflater.inflate(R.layout.fragment_thu, container, false);
        initView(view);
        btnSave.setOnClickListener(view1 -> {if (validate()) save();});
        btnAdd.setOnClickListener(view1 -> {
            Intent intent = new Intent(view.getContext(), FragmentAddOrEditMenu.class);
            intent.putExtra("status", Constants.STATUS.THU);
            startActivity(intent);
        });
        date.setOnClickListener(view1 -> createDatePickerDialog().show());
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

    private void save () {
        ThuChi data = new ThuChi(menu.getMenuId(), note.getText().toString(), date.getText().toString(),
                Constants.STATUS.THU, Double.parseDouble(money.getText().toString()));
        db.addThuChi(data);
        Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
        resetForm();
    }

    private void resetForm () {
        date.setText("");
        note.setText("");
        money.setText("");
        recyclerView.getChildAt(index).setBackgroundColor(ContextCompat.getColor(getContext() ,R.color.white));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private List<Menu> getListMenu () {
        List<Menu> results = db.getAllMenuByStatus(Constants.STATUS.THU);
        return results;
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

    private DatePickerDialog createDatePickerDialog() {
        return new DatePickerDialog(getContext(),
                (datePicker, year, month, day) -> date.setText(String.format("%02d/%02d/%04d", day, month, year)),
                DateUtils.getYear(), DateUtils.getMonth(), DateUtils.getDay());
    }

    private boolean validate () {
        if (DataUtils.isNullOrEmptyOrBlank(date.getText().toString())) {
            date.setError(getResources().getString(R.string.message_error_empty));
            return false;
        }
        if (DataUtils.isNullOrEmptyOrBlank(money.getText().toString())) {
            money.setError(getResources().getString(R.string.message_error_empty));
            return false;
        }
        if (DataUtils.isNullOrEmptyOrBlank(menu)) {
            Toast.makeText(getContext(), "Vui lòng chọn danh mục", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
