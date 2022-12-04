package com.example.venom.staff;

public class Staff {
    private String idNV, tenNV, chucVu, namSinh, urlAnh;

    public Staff(String idNV, String tenNV, String chucVu, String namSinh, String urlAnh) {
        this.idNV = idNV;
        this.tenNV = tenNV;
        this.chucVu = chucVu;
        this.namSinh = namSinh;
        this.urlAnh = urlAnh;
    }

    public String getIdNV() {
        return idNV;
    }

    public void setIdNV(String idNV) {
        this.idNV = idNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getUrlAnh() {
        return urlAnh;
    }

    public void setUrlAnh(String urlAnh) {
        this.urlAnh = urlAnh;
    }
}
