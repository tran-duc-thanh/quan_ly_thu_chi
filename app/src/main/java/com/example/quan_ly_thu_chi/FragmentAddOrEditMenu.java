package com.example.quan_ly_thu_chi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.quan_ly_thu_chi.adapter.SpinnerAdapterColor;
import com.example.quan_ly_thu_chi.adapter.SpinnerAdapterIcon;
import com.example.quan_ly_thu_chi.data.SQLiteHelper;
import com.example.quan_ly_thu_chi.data.model.Menu;
import com.example.quan_ly_thu_chi.utils.Constants;

public class FragmentAddOrEditMenu extends AppCompatActivity {

    private int[] arrIconsChi = {R.drawable.icon_bus, R.drawable.icon_cart, R.drawable.icon_restaurant,
            R.drawable.icon_water, R.drawable.icon_book, R.drawable.icon_money, R.drawable.icon_bitcoin,
            R.drawable.icon_giftcard, R.drawable.icon_wallet, R.drawable.icon_language};

    private int[] arrIconsThu = {R.drawable.icon_bus_1, R.drawable.icon_cart_1, R.drawable.icon_restaurant_1,
            R.drawable.icon_water_1, R.drawable.icon_book_1, R.drawable.icon_money_1, R.drawable.icon_bitcoin_1,
            R.drawable.icon_giftcard_1, R.drawable.icon_wallet_1, R.drawable.icon_language_1};

    private EditText menuName;
    private Spinner icons, colors;
    private ImageButton back;
    private Button save;
    private SpinnerAdapterIcon adapterIcon;
    private SpinnerAdapterColor adapterColor;
    private SQLiteHelper db;
    private Intent data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        data = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_or_edit_menu);
        db = new SQLiteHelper(getApplicationContext());
        menuName = findViewById(R.id.menuName);
        icons = findViewById(R.id.spinnerIcon);
        colors = findViewById(R.id.spinnerColor);
        back = findViewById(R.id.btnBack);
        save = findViewById(R.id.btnSaveMenu);
        if (data.getIntExtra("status", -1) == Constants.STATUS.CHI) {
            adapterIcon = new SpinnerAdapterIcon(this, arrIconsChi);
        } else if (data.getIntExtra("status", -1) == Constants.STATUS.THU){
            adapterIcon = new SpinnerAdapterIcon(this, arrIconsThu);
        }
        adapterColor = new SpinnerAdapterColor(this);
        icons.setAdapter(adapterIcon);
        colors.setAdapter(adapterColor);
        back.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        save.setOnClickListener(view -> {
            Menu menu = new Menu();
            menu.setColor(Integer.parseInt(colors.getSelectedItem().toString()));
            menu.setIcon(Integer.parseInt(icons.getSelectedItem().toString()));
            menu.setName(menuName.getText().toString());
            menu.setStatus(data.getIntExtra("status", -1));
            db.addMenu(menu);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        db.getAllMenuByStatus(Constants.STATUS.CHI).forEach(rs -> getResources().getDrawable(rs.getIcon()).setTint(getResources().getColor(rs.getColor())));
        db.getAllMenuByStatus(Constants.STATUS.THU).forEach(rs -> getResources().getDrawable(rs.getIcon()).setTint(getResources().getColor(rs.getColor())));
        super.onResume();
    }

}