package com.example.wsmeverylisten.common.wsmjson;

public class Province {

    private String id;

    private String name;

    private String jianCheng;

    private String shengHui;

    public Province() {
    }

    public Province(String id, String name, String jianCheng, String shengHui) {
        this.id = id;
        this.name = name;
        this.jianCheng = jianCheng;
        this.shengHui = shengHui;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJianCheng() {
        return jianCheng;
    }

    public void setJianCheng(String jianCheng) {
        this.jianCheng = jianCheng;
    }

    public String getShengHui() {
        return shengHui;
    }

    public void setShengHui(String shengHui) {
        this.shengHui = shengHui;
    }
}
