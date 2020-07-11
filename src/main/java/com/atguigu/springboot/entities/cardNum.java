package com.atguigu.springboot.entities;

public class cardNum {
    public cardNum() {
    }

    public cardNum(String kind, String num) {
        this.kind = kind;
        this.num = num;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    private String kind;
    private String num;
}
