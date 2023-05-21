package com.example.dao;

import com.example.pojo.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Mapper
public interface CheckRole {
    @Select("select * from roleclass")
    @Results({
            @Result(property = "users",column = "id",many=@Many(select = "com.example.dao.CheckUser.Link_to_Role"))
    })
    public List<Role> GetAll();
    @Select("select * from roleclass where id=#{id}")
    @Results({
            @Result(property = "users",column = "id",many=@Many(select = "com.example.dao.CheckUser.Link_to_Role"))
    })
    public Role GetAc(int id);
    @Insert("insert into roleclass (name) value(#{name})")
    public int insertR(Role role);
    @Delete("delete * from where id=#{id}")
    public int deleteR(int id);
    @Select("select * from roleclass where id=#{id}")
    public Role Link_to_Role(int id);
}
