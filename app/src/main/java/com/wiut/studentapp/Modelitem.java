package com.wiut.studentapp;

public class Modelitem {
    String data,coin1,coin2;

    public Modelitem(String data, String coin1, String coin2) {
        this.data = data;
        this.coin1 = coin1;
        this.coin2 = coin2;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCoin1() {
        return coin1;
    }

    public void setCoin1(String coin1) {
        this.coin1 = coin1;
    }

    public String getCoin2() {
        return coin2;
    }

    public void setCoin2(String coin2) {
        this.coin2 = coin2;
    }
}
