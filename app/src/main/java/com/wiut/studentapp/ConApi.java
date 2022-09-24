package com.wiut.studentapp;

public class ConApi {

    private String Ccy;
    private String CcyNm_EN;
    private  String CcyNm_RU;
    private String CcyNm_UZ;
    private String CcyNm_UZC;
    private String Code;
    private String Date;
    private String Diff;
    private String Nominal;
    private String Rate;
    private String id;

    public ConApi(String ccy, String ccyNm_EN, String ccyNm_RU, String ccyNm_UZ, String ccyNm_UZC, String code, String date, String diff, String nominal, String rate, String id) {
        Ccy = ccy;
        CcyNm_EN = ccyNm_EN;
        CcyNm_RU = ccyNm_RU;
        CcyNm_UZ = ccyNm_UZ;
        CcyNm_UZC = ccyNm_UZC;
        Code = code;
        Date = date;
        Diff = diff;
        Nominal = nominal;
        Rate = rate;
        this.id = id;
    }

    public String getCcy() {
        return Ccy;
    }

    public void setCcy(String ccy) {
        Ccy = ccy;
    }

    public String getCcyNm_EN() {
        return CcyNm_EN;
    }

    public void setCcyNm_EN(String ccyNm_EN) {
        CcyNm_EN = ccyNm_EN;
    }

    public String getCcyNm_RU() {
        return CcyNm_RU;
    }

    public void setCcyNm_RU(String ccyNm_RU) {
        CcyNm_RU = ccyNm_RU;
    }

    public String getCcyNm_UZ() {
        return CcyNm_UZ;
    }

    public void setCcyNm_UZ(String ccyNm_UZ) {
        CcyNm_UZ = ccyNm_UZ;
    }

    public String getCcyNm_UZC() {
        return CcyNm_UZC;
    }

    public void setCcyNm_UZC(String ccyNm_UZC) {
        CcyNm_UZC = ccyNm_UZC;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDiff() {
        return Diff;
    }

    public void setDiff(String diff) {
        Diff = diff;
    }

    public String getNominal() {
        return Nominal;
    }

    public void setNominal(String nominal) {
        Nominal = nominal;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
