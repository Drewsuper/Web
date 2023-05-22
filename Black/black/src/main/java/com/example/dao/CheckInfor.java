package com.example.dao;

import com.example.pojo.Information;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface CheckInfor {
    @Select("select * from information")
    public List<Information> getAll();
    @Insert("insert into information(name,getname,avatar,phone,email,addressone,addresstwo,userid) " +
            "value(#{name},#{getname},#{avatar},#{phone},#{email},#{addressone},#{addresstwo},#{userid})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    public int insert(Information information);
    @Update("update information set userid=#{userid} where id=#{id}")
    public int setUserId(int userid,int id);
    @Select("select * from information where id=#{id}")
    public Information Link_to_User(int id);
    @Update("update information set name=#{name},getname=#{getname},avatar=#{avatar},phone=#{phone},email=#{email},addressone=#{addressone}  where userid=#{userid}")
    public int updateAll(Information information);
    @Select("select * form information where userid=#{userid}")
    public Information getByUserId(int userid);
}
