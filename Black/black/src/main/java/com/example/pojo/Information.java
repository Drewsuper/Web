package com.example.pojo;
/**
 *
 * @author 李翰
 * @since 2023-5-1
 */
public class Information {
    public int id;
    public int userid;
    public String name;
    public String getname;
    public String phone;
    public String email;
    public String addressone;
    public String addresstwo;
    public String avatar;

    public Information(){}
    public Information(int id,
                       int userid,
                       String name,
                       String getname,
                       String phone,
                       String email,
                       String addressone,
                       String addresstwo,
                       String avatar){
        this.id=id;
        this.userid=userid;
        this.name=name;
        this.getname=getname;
        this.phone=phone;
        this.email=email;
        this.addressone=addressone;
        this.addresstwo=addresstwo;
        this.avatar = avatar;
    }
    public Information(int userid,
                       String name,
                       String getname,
                       String phone,
                       String email,
                       String addressone,
                       String addresstwo,
                       String avatar){
        this.userid = userid;
        this.name=name;
        this.getname=getname;
        this.phone=phone;
        this.email=email;
        this.addressone=addressone;
        this.addresstwo=addresstwo;
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "Information{" +
                "id=" + id +
                ", userid=" + userid +
                ", name='" + name + '\'' +
                ", getname='" + getname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", addressone='" + addressone + '\'' +
                ", addresstwo='" + addresstwo + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }

    public String getName() {return name;}

    public String getGetname() {return getname;}

    public String getAddressone() {return addressone;}

    public String getAddresstwo() {return addresstwo;}
}
