package com.example.fastfoodapp.Models;

public class SanPham {
    private String IDSP;
    private String TenSP;
    private String IDDanhMuc;
    private String IDShop;
    private int DanhGia;
    private int Gia;
    private int soLuong;
    private String ImgSP;
    private String moTaSP;

    public SanPham() {
    }

    public SanPham(String IDSP, String tenSP, String IDDanhMuc, String IDShop, int danhGia, int gia, int soLuong, String imgSP, String moTaSP) {
        this.IDSP = IDSP;
        TenSP = tenSP;
        this.IDDanhMuc = IDDanhMuc;
        this.IDShop = IDShop;
        DanhGia = danhGia;
        Gia = gia;
        this.soLuong = soLuong;
        ImgSP = imgSP;
        this.moTaSP = moTaSP;
    }

    public String getIDSP() {
        return IDSP;
    }

    public void setIDSP(String IDSP) {
        this.IDSP = IDSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public String getIDDanhMuc() {
        return IDDanhMuc;
    }

    public void setIDDanhMuc(String IDDanhMuc) {
        this.IDDanhMuc = IDDanhMuc;
    }

    public String getIDShop() {
        return IDShop;
    }

    public void setIDShop(String IDShop) {
        this.IDShop = IDShop;
    }

    public int getDanhGia() {
        return DanhGia;
    }

    public void setDanhGia(int danhGia) {
        DanhGia = danhGia;
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

    public String getImgSP() {
        return ImgSP;
    }

    public void setImgSP(String imgSP) {
        ImgSP = imgSP;
    }

    public String getMoTaSP() {
        return moTaSP;
    }

    public void setMoTaSP(String moTaSP) {
        this.moTaSP = moTaSP;
    }
}
