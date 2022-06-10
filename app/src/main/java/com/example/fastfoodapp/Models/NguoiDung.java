package com.example.fastfoodapp.Models;

public class NguoiDung {
    private String Username;
    private String HoTen;
    private String SDT;
    private String matKhau;
    private String DiaChi;
    private String ImgND;

    public NguoiDung() {
    }

    public NguoiDung( String username, String hoTen, String SDT, String matKhau) {
        this.Username = username;
        this.HoTen = hoTen;
        this.SDT = SDT;
        this.matKhau = matKhau;
        this.DiaChi = "";
        this.ImgND = "";
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
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

}
