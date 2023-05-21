package com.example.quan_ly_thu_chi.data.model;

public class ThuChi {
    private Integer id;
    private Integer menuId;
    private String note;
    private String date;
    private Integer status;
    private Double money;

    public ThuChi() {
    }

    public ThuChi(Integer id, Integer menuId, String note, String date, Integer status, Double money) {
        this.id = id;
        this.menuId = menuId;
        this.note = note;
        this.date = date;
        this.status = status;
        this.money = money;
    }

    public ThuChi(Integer menuId, String note, String date, Integer status, Double money) {
        this.menuId = menuId;
        this.note = note;
        this.date = date;
        this.status = status;
        this.money = money;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
