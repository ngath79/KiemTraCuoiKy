package com.example.venom;

public class Sach {
    private String img, masach,ten,tacgia,namsx;

    public Sach() {
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMasach() {
        return masach;
    }

    public void setMasach(String masach) {
        this.masach = masach;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getNamsx() {
        return namsx;
    }

    public void setNamsx(String namsx) {
        this.namsx = namsx;
    }

    public Sach(String img, String masach, String ten, String tacgia, String namsx) {
        this.img = img;
        this.masach = masach;
        this.ten = ten;
        this.tacgia = tacgia;
        this.namsx = namsx;
    }
}
