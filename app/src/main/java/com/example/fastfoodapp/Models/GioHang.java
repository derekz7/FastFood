package com.example.fastfoodapp.Models;

public class GioHang {
    private int IDGH;
    private String ID;
    private String IDSP;
    private int SoLuong;


    public GioHang() {
    }

    public GioHang(int IDGH, String ID, String IDSP, int soLuong) {
        this.IDGH = IDGH;
        this.ID = ID;
        this.IDSP = IDSP;
        SoLuong = soLuong;
    }

    public int getIDGH() {
        return IDGH;
    }

    public void setIDGH(int IDGH) {
        this.IDGH = IDGH;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getIDSP() {
        return IDSP;
    }

    public void setIDSP(String IDSP) {
        this.IDSP = IDSP;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }
}
