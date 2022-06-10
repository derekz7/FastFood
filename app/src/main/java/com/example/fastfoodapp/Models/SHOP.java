package com.example.fastfoodapp.Models;

public class SHOP {
    private String IDShop;
    private String Username;
    private String tenShop;
    private String DiaChi;
    private String ImgShop;

    public SHOP() {
    }

    public SHOP(String IDShop, String username, String tenShop, String diaChi, String imgShop) {
        this.IDShop = IDShop;
        Username = username;
        this.tenShop = tenShop;
        DiaChi = diaChi;
        ImgShop = imgShop;
    }

    public String getIDShop() {
        return IDShop;
    }

    public void setIDShop(String IDShop) {
        this.IDShop = IDShop;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getTenShop() {
        return tenShop;
    }

    public void setTenShop(String tenShop) {
        this.tenShop = tenShop;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getImgShop() {
        return ImgShop;
    }

    public void setImgShop(String imgShop) {
        ImgShop = imgShop;
    }
}
