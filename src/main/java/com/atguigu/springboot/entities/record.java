package com.atguigu.springboot.entities;

public class record {
    private String kind;
    private String num;
    private String time;
    private  String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public record() {
    }

    public record(String kind, String num, String time, String name) {
        this.kind = kind;
        this.num = num;
        this.time = time;
        this.name=name;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    @Override
    public String toString() {
        return "record{" +
                "kind=" + kind +
                ", Num='" + num + '\'' +
                ", Name='" + name + '\'' +
                ", time=" + time +
                '}';
    }
}
