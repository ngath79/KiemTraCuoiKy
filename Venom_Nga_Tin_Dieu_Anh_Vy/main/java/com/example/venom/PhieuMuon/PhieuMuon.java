package com.example.venom.PhieuMuon;

import java.io.Serializable;

public class PhieuMuon  implements Serializable {

    private String id;
    private String masach;
    private String tensach;
    private String ngaymuon;
    private String ngaytra;


    @Override
    public String toString() {
        return "PhieuMuon{" +
                "id='" + id + '\'' +
                ", masach='" + masach + '\'' +
                ", tensach='" + tensach + '\'' +
                ", ngaymuon='" + ngaymuon + '\'' +
                ", ngaytra='" + ngaytra + '\'' +
                '}';
    }

    public PhieuMuon() {

    }

    public PhieuMuon( String masach, String tensach, String ngaymuon, String ngaytra) {
        this.masach = masach;
        this.tensach = tensach;
        this.ngaymuon = ngaymuon;
        this.ngaytra = ngaytra;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMasach() {
        return masach;
    }

    public void setMasach(String masach) {
        this.masach = masach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getNgaymuon() {
        return ngaymuon;
    }

    public void setNgaymuon(String ngaymuon) {
        this.ngaymuon = ngaymuon;
    }

    public String getNgaytra() {
        return ngaytra;
    }

    public void setNgaytra(String ngaytra) {
        this.ngaytra = ngaytra;
    }
}
