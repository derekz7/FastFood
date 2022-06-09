package com.example.fastfoodapp.Utils;

import com.example.fastfoodapp.Models.DanhMuc;
import com.example.fastfoodapp.Models.NguoiDung;
import com.example.fastfoodapp.Models.SanPham;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Common {
    public static String baseUrl = "https://fastfoodapi.conveyor.cloud/";
    public static List<SanPham> sanPhamList;
    public static List<DanhMuc> danhMucList;
    public static SanPham sanPham;
    public static NguoiDung userLog;
    public static String SDTuser;
    public static String formatMoney(int money) {
        Locale locale = new Locale("vn", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(money);
    }
}
