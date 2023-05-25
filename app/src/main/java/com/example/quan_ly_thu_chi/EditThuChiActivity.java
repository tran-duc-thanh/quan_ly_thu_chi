package com.example.quan_ly_thu_chi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.quan_ly_thu_chi.adapter.MenuAdapter;
import com.example.quan_ly_thu_chi.data.SQLiteHelper;
import com.example.quan_ly_thu_chi.data.model.Menu;
import com.example.quan_ly_thu_chi.data.model.ThuChi;
import com.example.quan_ly_thu_chi.utils.Constants;
import com.example.quan_ly_thu_chi.utils.DataUtils;
import com.example.quan_ly_thu_chi.utils.DateUtils;

import java.util.List;

public class EditThuChiActivity extends AppCompatActivity implements MenuAdapter.MenuItemListener{

    private Menu menu = null;
    private Integer index = null;
    private EditText date, note, money;
    private ImageButton back, btnAdd;
    private RecyclerView recyclerView;
    private Button btnSave;
    private MenuAdapter adapter;
    private SQLiteHelper db;
    private Intent data;
    private ThuChi thuChi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_thu_chi);
        initView();
        setData();
        btnSave.setOnClickListener(view1 -> {if (validate()) save();});
        btnAdd.setOnClickListener(view1 -> {
            Intent intent = new Intent(getApplicationContext(), FragmentAddOrEditMenu.class);
            intent.putExtra("status", Constants.STATUS.CHI);
            startActivity(intent);
        });
        date.setOnClickListener(view1 -> createDatePickerDialog().show());
        back.setOnClickListener(view1 -> {
            Intent intent = new Intent(getApplicationContext(), ListActivity.class);
            intent.putExtra("status", thuChi.getStatus());
            intent.putExtra("sDate", data.getStringExtra("sDate"));
            intent.putExtra("eDate", data.getStringExtra("eDate"));
            startActivity(intent);
        });
    }

    private void initView () {
        data = getIntent();
        db = new SQLiteHelper(getApplicationContext());
        thuChi = db.getOneThuChi(data.getIntExtra("id", 0));
        recyclerView = findViewById(R.id.recycleViewChi);
        GridLayoutManager manager=new GridLayoutManager(getApplicationContext(),4);
        recyclerView.setLayoutManager(manager);
        adapter = new MenuAdapter(getApplicationContext());
        adapter.setItems(getListMenu());
        recyclerView.setAdapter(adapter);
        adapter.setListener(this);
        date = findViewById(R.id.dateChi);
        note = findViewById(R.id.noteChi);
        money = findViewById(R.id.tienChi);
        btnSave = findViewById(R.id.btnSaveChi);
        btnAdd = findViewById(R.id.btnAddMenu);
        back = findViewById(R.id.btnBack);
    }

    private void save () {
        int menuId;
        if (menu != null) menuId = menu.getMenuId();
        else menuId = thuChi.getMenuId();
        ThuChi result = new ThuChi(thuChi.getId() , menuId, note.getText().toString(), date.getText().toString(),
                thuChi.getStatus(), Double.parseDouble(money.getText().toString()));
        db.updateThuChi(result);
        Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
        intent.putExtra("status", thuChi.getStatus());
        intent.putExtra("sDate", data.getStringExtra("sDate"));
        intent.putExtra("eDate", data.getStringExtra("eDate"));
        startActivity(intent);
    }

    private void setData () {
        date.setText(thuChi.getDate());
        note.setText(thuChi.getNote());
        money.setText(String.valueOf(thuChi.getMoney()));
        Menu m = db.getOneMenu(thuChi.getMenuId());
        List<Menu> l = getListMenu();
        l.remove(m);
        adapter.setItems(l);
    }

    private List<Menu> getListMenu () {
        List<Menu> results = db.getAllMenuByStatus(thuChi.getStatus());
        return results;
    }

    private DatePickerDialog createDatePickerDialog() {
        return new DatePickerDialog(getApplicationContext(),
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
        return true;
    }

    @Override
    public void onItemClick(View view, int position) {
        menu = adapter.getItem(position);
        if (index != null) {
            recyclerView.getChildAt(index).setBackgroundColor(ContextCompat.getColor(view.getContext() ,R.color.white));
        }
        recyclerView.getChildAt(position).setBackgroundColor(ContextCompat.getColor(view.getContext() ,R.color.darkturquoise));
        index = position;
    }
}