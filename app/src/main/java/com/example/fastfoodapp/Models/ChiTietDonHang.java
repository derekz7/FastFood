package com.example.fastfoodapp.Models;

public class ChiTietDonHang {
    private int ID;
    private int IDDonHang;
    private String IDSP;
    private int soLuong;
    private int donGia;

    public ChiTietDonHang() {
    }

    public ChiTietDonHang(String IDSP, int soLuong, int donGia) {
        this.IDSP = IDSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDDonHang() {
        return IDDonHang;
    }

    public void setIDDonHang(int IDDonHang) {
        this.IDDonHang = IDDonHang;
    }

    public String getIDSP() {
        return IDSP;
    }

    public void setIDSP(String IDSP) {
        this.IDSP = IDSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }
}
