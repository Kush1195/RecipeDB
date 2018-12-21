package com.example.admin.recipedb;

import java.io.Serializable;

public class Model implements Serializable
{
    private String menu;
    private int id;
    private String submenu;
    private String detail;
    private int fav;

    public Model(String menu, int id, String submenu, String detail, int fav) {
        this.menu = menu;
        this.id = id;
        this.submenu = submenu;
        this.detail = detail;
        this.fav = fav;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubmenu() {
        return submenu;
    }

    public void setSubmenu(String submenu) {
        this.submenu = submenu;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getFav() {
        return fav;
    }

    public void setFav(int fav) {
        this.fav = fav;
    }
}
