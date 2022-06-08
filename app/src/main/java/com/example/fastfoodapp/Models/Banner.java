package com.example.fastfoodapp.Models;

public class Banner {
    private String IDbanner;
    private String ImgBanner;

    public Banner() {
    }

    public Banner(String IDbanner, String imgBanner) {
        this.IDbanner = IDbanner;
        ImgBanner = imgBanner;
    }

    public String getIDbanner() {
        return IDbanner;
    }

    public void setIDbanner(String IDbanner) {
        this.IDbanner = IDbanner;
    }

    public String getImgBanner() {
        return ImgBanner;
    }

    public void setImgBanner(String imgBanner) {
        ImgBanner = imgBanner;
    }
}
