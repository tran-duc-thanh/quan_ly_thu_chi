package com.example.quan_ly_thu_chi.data.model;

public class ThuChiDTO {
    private Double totalMoney;
    private int totalItem;

    public ThuChiDTO() {
    }

    public ThuChiDTO(Double totalMoney, int totalItem) {
        this.totalMoney = totalMoney;
        this.totalItem = totalItem;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }
}
