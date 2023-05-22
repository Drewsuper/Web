package com.example.pojo;

public class Commodity {
    public int id;
    public String goodname;
    public double prices;
    public String description;
    public String urlpath;
    public int isup;
    public String time;
    public int classid;
    public CommodityClass commodityClass;

    public Commodity(){}
    public Commodity(int id, String goodname, double prices, String description, int isup, String urlpath, String time, int classid){
        this.id=id;
        this.description = description;
        this.goodname = goodname;
        this.urlpath = urlpath;
        this.prices = prices;
        this.isup = isup;
        this.time=time;
        this.classid= classid;
    }
    public Commodity(String goodname,
                     String description,
                     String urlpath,
                     double prices,
                     int isup,
                     int classid){
        this.description=description;
        this.goodname = goodname;
        this.urlpath = urlpath;
        this.prices = prices;
        this.isup = isup;
        this.classid = classid;
    }
    public Commodity(int id,
                     String goodname,
                     String description,
                     double prices,
                     int isup,
                     int classid){
        this.id = id;
        this.description=description;
        this.goodname = goodname;
        this.prices = prices;
        this.isup = isup;
        this.classid = classid;
    }

    @Override
    public String toString() {
        return "Commmodity{" +
                "id=" + id +
                ", goodname='" + goodname + '\'' +
                ", prices=" + prices +
                ", description='" + description + '\'' +
                ", urlpath='" + urlpath + '\'' +
                ", isup=" + isup +
                ", time='" + time + '\'' +
                ", classid=" + classid +
                ", commodityClass=" + commodityClass +
                '}';
    }
}
