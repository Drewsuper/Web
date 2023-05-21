package com.example.pojo;

import org.springframework.beans.factory.annotation.Autowired;

public class CarInformation {
    public int id;
    public int userid;
    public  int goodid;
    public int isfinish;
    public int incar;
    public int ispay;
    public String date;
    public int number;
    public  Commodity commodity;
    public CarInformation(int id,
                          String date,
                          int userid,
                          int goodid,
                          int isfiish,
                          int incar,
                          int ispay,
                          int number,
                          Commodity commodity){
        this.id=id;
        this.date=date;
        this.userid=userid;
        this.goodid = goodid;
        this.isfinish=isfiish;
        this.ispay=ispay;
        this.incar=incar;
        this.number=number;
        this.commodity = commodity;
    }
    public CarInformation(int userid,
                          int goodid,
                          int isfiish,
                          int incar,
                          int ispay,
                          int number){
        this.goodid = goodid;
        this.userid=userid;
        this.isfinish=isfiish;
        this.ispay=ispay;
        this.incar=incar;
        this.number = number;
    }
    public CarInformation(){}

    @Override
    public String toString() {
        return "CarInformation{" +
                "id=" + id +
                ", userid=" + userid +
                ", goodid=" + goodid +
                ", isfinish=" + isfinish +
                ", incar=" + incar +
                ", ispay=" + ispay +
                ", date='" + date + '\'' +
                ", number=" + number +
                ", commodity=" + commodity +
                '}';
    }

    public String toStringNew(){
        return "CarInformation{" +
                " userid=" + userid +
                ", date='" + date+'\''+
                ", number=" +number +
                ", commodity=" + commodity +
                '}';
   }
}
