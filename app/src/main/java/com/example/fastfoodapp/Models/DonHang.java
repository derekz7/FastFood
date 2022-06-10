package com.example.fastfoodapp.Models;

public class DonHang {
    private int ID;
    private String Username;
    private String TinhTrang;
    private int Phiship;
    private int TongTien;

    public DonHang() {
    }

    public DonHang(String username, int tongTien) {
        Username = username;
        TinhTrang = "Đang chờ xác nhận";
        Phiship = 15000;
        TongTien = tongTien;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        TinhTrang = tinhTrang;
    }

    public int getPhiship() {
        return Phiship;
    }

    public void setPhiship(int phiship) {
        Phiship = phiship;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int tongTien) {
        TongTien = tongTien;
    }
}
