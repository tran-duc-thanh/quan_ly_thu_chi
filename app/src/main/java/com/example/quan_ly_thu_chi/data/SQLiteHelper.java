package com.example.quan_ly_thu_chi.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.quan_ly_thu_chi.data.model.Menu;
import com.example.quan_ly_thu_chi.data.model.ThuChi;
import com.example.quan_ly_thu_chi.data.model.ThuChiDTO;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "data.db";
    private static final int DB_VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE menu (menuId INTEGER PRIMARY KEY AUTOINCREMENT,");
        sql.append(" icon INTEGER, name TEXT, color INTEGER, status INTEGER);");
        StringBuilder sql1 = new StringBuilder();
        sql1.append("CREATE TABLE thu_chi (id INTEGER PRIMARY KEY AUTOINCREMENT,");
        sql1.append(" menuId INTEGER, note TEXT, money DOUBLE, status INTEGER, date TEXT)");
        sqLiteDatabase.execSQL(sql.toString());
        sqLiteDatabase.execSQL(sql1.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+"menu");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+"thu_chi");
        onCreate(sqLiteDatabase);
    }

    public List<Menu> getAllMenuByStatus (Integer status) {
        List<Menu> results = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor rs = database.query("menu", null, "status = ?", new String[]{String.valueOf(status)}, null, null, null);
        while (rs != null && rs.moveToNext()) {
            results.add(new Menu(rs.getInt(0), rs.getString(2), rs.getInt(1), rs.getInt(3), rs.getInt(4)));
        }
        return results;
    }

    public Menu getOneMenu (int id) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor rs = database.rawQuery("SELECT * FROM menu WHERE menuId = ?", new String[]{String.valueOf(id)});
        if (rs != null && rs.moveToNext()) {
            return new Menu(rs.getInt(0), rs.getString(2), rs.getInt(1), rs.getInt(3), rs.getInt(4));
        }
        return null;
    }

    public Long addMenu (Menu menu) {
        ContentValues values = new ContentValues();
        values.put("icon", menu.getIcon());
        values.put("name", menu.getName());
        values.put("color", menu.getColor());
        values.put("status", menu.getStatus());
        SQLiteDatabase database = getWritableDatabase();
        return database.insert("menu", null, values);
    }

    public int updateMenu (Menu menu) {
        ContentValues values = new ContentValues();
        values.put("icon", menu.getIcon());
        values.put("name", menu.getName());
        values.put("color", menu.getColor());
        values.put("status", menu.getStatus());
        SQLiteDatabase database = getWritableDatabase();
        String whereClause = "menuId = ?";
        String[] whereArgs = {String.valueOf(menu.getMenuId())};
        return database.update("menu", values, whereClause, whereArgs);
    }

    public List<ThuChi> getAllThuChiByStatus (Integer status) {
        List<ThuChi> results = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor rs = database.query("thu_chi", null, "status = ?", new String[]{String.valueOf(status)}, null, null, null);
        while (rs != null && rs.moveToNext()) {
            results.add(new ThuChi(rs.getInt(0), rs.getInt(1), rs.getString(2), rs.getString(5),
                    rs.getInt(4), rs.getDouble(3)));
        }
        return results;
    }

    public ThuChi getOneThuChi (int id) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor rs = database.rawQuery("SELECT * FROM thu_chi WHERE id = ?", new String[]{String.valueOf(id)});
        if (rs != null && rs.moveToNext()) {
            return new ThuChi(rs.getInt(0), rs.getInt(1), rs.getString(2), rs.getString(5),
                    rs.getInt(4), rs.getDouble(3));
        }
        return null;
    }

    public List<ThuChi> searchThuChi (String sDate, String eDate, int status) {
        List<ThuChi> results = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor rs = database.rawQuery("SELECT * FROM thu_chi WHERE status = ? AND date BETWEEN ? AND ?", new String[]{String.valueOf(status), sDate, eDate});
        while (rs != null && rs.moveToNext()) {
            results.add(new ThuChi(rs.getInt(0), rs.getInt(1), rs.getString(2), rs.getString(5),
                    rs.getInt(4), rs.getDouble(3)));
        }
        return results;
    }

    public ThuChiDTO getInfoThuChi (String sDate, String eDate, int status) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor rs = database.rawQuery("SELECT COUNT(id), SUM(money) FROM thu_chi WHERE status = ? AND date BETWEEN ? AND ?",
                new String[]{String.valueOf(status), sDate, eDate});
        if (rs != null && rs.moveToNext()) {
            return new ThuChiDTO(rs.getDouble(1), rs.getInt(0));
        }
        return null;
    }

    public Long addThuChi (ThuChi thuChi) {
        ContentValues values = new ContentValues();
        values.put("menuId", thuChi.getMenuId());
        values.put("note", thuChi.getNote());
        values.put("money", thuChi.getMoney());
        values.put("status", thuChi.getStatus());
        values.put("date", thuChi.getDate());
        SQLiteDatabase database = getWritableDatabase();
        return database.insert("thu_chi", "note", values);
    }

    public int updateThuChi (ThuChi thuChi) {
        ContentValues values = new ContentValues();
        values.put("menuId", thuChi.getMenuId());
        values.put("note", thuChi.getNote());
        values.put("money", thuChi.getMoney());
        values.put("status", thuChi.getStatus());
        values.put("date", thuChi.getDate());
        SQLiteDatabase database = getWritableDatabase();
        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(thuChi.getId())};
        return database.update("thu_chi", values, whereClause, whereArgs);
    }

    public int deleteThuChi (int id) {
        SQLiteDatabase database = getWritableDatabase();
        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(id)};
        return database.delete("thu_chi", whereClause, whereArgs);
    }

}
