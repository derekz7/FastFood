package com.example.fastfoodapp.Models;

public class NguoiDung {
    private String ID;
    private String Email;
    private String HoTen;
    private String SDT;
    private String matKhau;
    private String DiaChi;
    private String ImgND;
    private String IDShop;

    public NguoiDung() {
    }

    public NguoiDung(String ID, String email, String hoTen, String SDT, String matKhau, String diaChi, String imgND) {
        this.ID = ID;
        Email = email;
        HoTen = hoTen;
        this.SDT = SDT;
        this.matKhau = matKhau;
        DiaChi = diaChi;
        ImgND = imgND;
        this.IDShop = null;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getImgND() {
        return ImgND;
    }

    public void setImgND(String imgND) {
        ImgND = imgND;
    }

    public String getIDShop() {
        return IDShop;
    }

    public void setIDShop(String IDShop) {
        this.IDShop = IDShop;
    }
}
