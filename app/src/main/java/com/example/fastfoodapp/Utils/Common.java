package com.example.fastfoodapp.Utils;

import android.content.Context;
import android.widget.Toast;

import com.example.fastfoodapp.Models.DanhMuc;
import com.example.fastfoodapp.Models.GioHang;
import com.example.fastfoodapp.Models.NguoiDung;
import com.example.fastfoodapp.Models.SHOP;
import com.example.fastfoodapp.Models.SanPham;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Common {
    public static String baseUrl = "https://fastfoodapi.conveyor.cloud/";
    public static List<SanPham> sanPhamList;
    public static List<DanhMuc> danhMucList;
    public static List<GioHang> gioHangList;
    public static SanPham sanPham;
    public static NguoiDung userLog;
    public static String username;
    public static SHOP shop;
    public static String formatMoney(int money) {
        Locale locale = new Locale("vn", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(money);
    }
    public static void themGioHang(GioHang gioHang, Context context) {
        if (gioHangList == null) {
            gioHangList = new ArrayList<>();
            gioHangList.add(gioHang);
            Toast.makeText(context, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
        } else {
            int kt = ktTonTai(gioHang.getIdSP());
            if (kt >= 0) {
                GioHang gioHang1 = gioHangList.get(kt);
                if (gioHang1.getSoLuong() <= sanPham.getSoLuong()) {
                    gioHang1.setSoLuong(gioHang1.getSoLuong() + 1);
                    gioHangList.set(kt, gioHang1);
                    Toast.makeText(context, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Số lượng tối đa", Toast.LENGTH_SHORT).show();
                }
            } else {
                gioHangList.add(gioHang);
                Toast.makeText(context, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public static int ktTonTai(String maSP) {
        GioHang model = null;
        for (GioHang gioHang : gioHangList
        ) {
            if (gioHang.getIdSP().equals(maSP)) model = gioHang;
        }
        return gioHangList.indexOf(model);
    }
}
