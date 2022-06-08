package com.example.fastfoodapp.Models;

public class DanhMuc {
    private  String IDDanhMuc;
    private String TenDanhMuc;
    private String ImgDM;
    private String moTa;

    public DanhMuc() {
    }

    public DanhMuc(String IDDanhMuc, String tenDanhMuc, String imgDM, String moTa) {
        this.IDDanhMuc = IDDanhMuc;
        TenDanhMuc = tenDanhMuc;
        ImgDM = imgDM;
        this.moTa = moTa;
    }

    public String getIDDanhMuc() {
        return IDDanhMuc;
    }

    public void setIDDanhMuc(String IDDanhMuc) {
        this.IDDanhMuc = IDDanhMuc;
    }

    public String getTenDanhMuc() {
        return TenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        TenDanhMuc = tenDanhMuc;
    }

    public String getImgDM() {
        return ImgDM;
    }

    public void setImgDM(String imgDM) {
        ImgDM = imgDM;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
