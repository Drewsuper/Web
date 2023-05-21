package com.example.dao;

import com.example.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
/**
 *
 * @author 李翰
 * @since 2023-5-1
 */
@Mapper
public interface CheckUser {
    @Select("select * from user")
    @Results({
            @Result(property="roleInfor",column = "role",one=@One(select = "com.example.dao.CheckRole.Link_to_Role")),
            @Result(property="information",column = "infor",one=@One(select = "com.example.dao.CheckInfor.Link_to_User"))
    })
    public List<User> getUList();
    @Select("select * from user where id=#{id}")
    @Results({
            @Result(property="roleInfor",column = "role",one=@One(select = "com.example.dao.CheckRole.Link_to_Role")),
            @Result(property="information",column = "infor",one=@One(select = "com.example.dao.CheckInfor.Link_to_User"))
    })
    public User getAc(int id);
    @Select("select * from user where username like #{id}")
    @Results({
            @Result(property="information",column = "infor",one=@One(select = "com.example.dao.CheckInfor.Link_to_User"))
    })
    public List<User> getPwd(String id);
    @Insert("insert into user(username,password,isadmin,role,date) value(#{username},#{password},#{isadmin},#{role},now())")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    public int Insert(User user);
    @Delete("delete from user where id=#{id}")
    public int  deleteUser(int id);
    @Update("update user set password=#{password} where id=#{id}")
    public int upPWD(String password,int id);
    @Select("select * from user where username=#{id}")
    public List<User> getUser(String id);
    @Select("select * from user where role=${id}")
    public List<User> Link_to_Role(int id);
    @Update("update user set infor=#{inforid} where id=#{id}")
    public int setInforId(int inforid,int id);
    @Select("select username from user")
    public List<String> getName();
    @Update("update user set issign=#{issign} where id=#{id}")
    public int upsign(int issign,int id);
}
