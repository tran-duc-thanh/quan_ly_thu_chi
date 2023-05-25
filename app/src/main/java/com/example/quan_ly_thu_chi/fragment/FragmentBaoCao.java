package com.example.quan_ly_thu_chi.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.quan_ly_thu_chi.FragmentAddOrEditMenu;
import com.example.quan_ly_thu_chi.ListActivity;
import com.example.quan_ly_thu_chi.R;
import com.example.quan_ly_thu_chi.data.SQLiteHelper;
import com.example.quan_ly_thu_chi.data.model.ThuChiDTO;
import com.example.quan_ly_thu_chi.utils.Constants;
import com.example.quan_ly_thu_chi.utils.DataUtils;
import com.example.quan_ly_thu_chi.utils.DateUtils;
import com.google.android.material.tabs.TabLayout;

public class FragmentBaoCao extends Fragment {

    private EditText sDate, eDate;
    private TextView moneyThu, moneyChi, countThu, countChi, tallMoney;
    private Button btnStart, btnDetailThu, btnDetailChi;
    private SQLiteHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bao_cao, container, false);
        initView(view);
        db = new SQLiteHelper(getContext());
        sDate.setOnClickListener(view1 -> createDatePickerDialog(sDate).show());
        eDate.setOnClickListener(view1 -> createDatePickerDialog(eDate).show());
        btnStart.setOnClickListener(view1 -> {if (validate()) setData();});
        btnDetailChi.setOnClickListener(view1 -> {
            if (!validate()) return;
            Intent intent = new Intent(view.getContext(), ListActivity.class);
            intent.putExtra("status", Constants.STATUS.CHI);
            intent.putExtra("sDate", sDate.getText().toString());
            intent.putExtra("eDate", eDate.getText().toString());
            startActivity(intent);
        });
        btnDetailThu.setOnClickListener(view1 -> {
            if (!validate()) return;
            Intent intent = new Intent(view.getContext(), ListActivity.class);
            intent.putExtra("status", Constants.STATUS.THU);
            intent.putExtra("sDate", sDate.getText().toString());
            intent.putExtra("eDate", eDate.getText().toString());
            startActivity(intent);
        });
        return view;
    }

    private void initView (View view) {
        sDate = view.findViewById(R.id.startDate);
        eDate = view.findViewById(R.id.endDate);
        moneyThu = view.findViewById(R.id.totalMoneyThu);
        moneyChi = view.findViewById(R.id.totalMoneyChi);
        countThu = view.findViewById(R.id.totalCountThu);
        countChi = view.findViewById(R.id.totalCountChi);
        tallMoney = view.findViewById(R.id.totalMoney);
        btnStart = view.findViewById(R.id.btnStart);
        btnDetailThu = view.findViewById(R.id.btnDetailThu);
        btnDetailChi = view.findViewById(R.id.btnDetailChi);
    }

    private void setData () {
        ThuChiDTO thu = db.getInfoThuChi(sDate.getText().toString(), eDate.getText().toString(),
                Constants.STATUS.THU);
        ThuChiDTO chi = db.getInfoThuChi(sDate.getText().toString(), eDate.getText().toString(),
                Constants.STATUS.CHI);
        moneyThu.setText(String.valueOf(thu.getTotalMoney()));
        moneyChi.setText(String.valueOf(chi.getTotalMoney()));
        countThu.setText(String.valueOf(thu.getTotalItem()));
        countChi.setText(String.valueOf(chi.getTotalItem()));
        tallMoney.setText(String.valueOf(thu.getTotalMoney() - chi.getTotalMoney()));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private DatePickerDialog createDatePickerDialog(EditText date) {
        return new DatePickerDialog(getContext(),
                (datePicker, year, month, day) -> date.setText(String.format("%02d/%02d/%04d", day, month, year)),
                DateUtils.getYear(), DateUtils.getMonth(), DateUtils.getDay());
    }

    private boolean validate () {
        if (DataUtils.isNullOrEmptyOrBlank(sDate.getText().toString())) {
            sDate.setError(getResources().getString(R.string.message_error_empty));
            return false;
        }
        if (DataUtils.isNullOrEmptyOrBlank(eDate.getText().toString())) {
            eDate.setError(getResources().getString(R.string.message_error_empty));
            return false;
        }
        return true;
    }
}
