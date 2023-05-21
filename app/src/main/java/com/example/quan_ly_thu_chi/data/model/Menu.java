package com.example.quan_ly_thu_chi.data.model;

public class Menu {
    private Integer menuId;
    private String name;
    private Integer icon;
    private Integer color;
    private Integer status;

    public Menu() {
    }

    public Menu(Integer menuId, String name, Integer icon, Integer color, Integer status) {
        this.menuId = menuId;
        this.name = name;
        this.icon = icon;
        this.color = color;
        this.status = status;
    }

    public Menu(String name, Integer icon, Integer color, Integer status) {
        this.name = name;
        this.icon = icon;
        this.color = color;
        this.status = status;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
