package com.example.pojo;

import java.util.List;
/**
 *
 * @author 李翰
 * @since 2023-5-1
 */
public class CommodityClass {
    private int id;
    private String classname;
    public List<Commodity> commodities;
    public String date;
    public CommodityClass(){}
    public CommodityClass(int id,String classname,String date){
        this.id = id;
        this.classname = classname;
        this.date = date;
    }
    public CommodityClass(String classname){
        this.classname = classname;
    }

    @Override
    public String toString() {
        return "CommodityClass{" +
                "id=" + id +
                ", classname='" + classname + '\'' +
                ", commodities=" + commodities +
                ", date='" + date + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getClassname() {
        return classname;
    }

}
