package com.example.venom;

public class Member {
    private String matv, ten,namsinh,surl;

    public Member() {
    }

    public Member(String matv, String ten, String namsinh, String surl) {
        this.matv = matv;
        this.ten = ten;
        this.namsinh = namsinh;
        this.surl = surl;
    }

    public String getMatv() {
        return matv;
    }

    public void setMatv(String matv) {
        this.matv = matv;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(String namsinh) {
        this.namsinh = namsinh;
    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }
}
