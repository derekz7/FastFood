package com.example.fastfoodapp.Models;

public class HinhAnh {
    private int idAnh;
    private String imgUrl;
    private String IDSP;

    public HinhAnh() {
    }

    public HinhAnh(int idAnh, String imgUrl, String IDSP) {
        this.idAnh = idAnh;
        this.imgUrl = imgUrl;
        this.IDSP = IDSP;
    }

    public int getIdAnh() {
        return idAnh;
    }

    public void setIdAnh(int idAnh) {
        this.idAnh = idAnh;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getIDSP() {
        return IDSP;
    }

    public void setIDSP(String IDSP) {
        this.IDSP = IDSP;
    }
}
