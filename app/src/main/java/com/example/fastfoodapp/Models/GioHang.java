package com.example.fastfoodapp.Models;

public class GioHang {
    private String idSP;
    private String tenSP;
    private String imgUrl;
    private int Gia;
    private int soLuong;


    public GioHang() {
    }

    public GioHang(String idSP, String tenSP, String imgUrl, int gia, int soLuong) {
        this.idSP = idSP;
        this.tenSP = tenSP;
        this.imgUrl = imgUrl;
        Gia = gia;
        this.soLuong = soLuong;
    }

    public String getIdSP() {
        return idSP;
    }

    public void setIdSP(String idSP) {
        this.idSP = idSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
