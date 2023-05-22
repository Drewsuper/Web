package com.example.dao;

import com.example.pojo.Commodity;
import org.apache.ibatis.annotations.*;

import java.util.List;
/**
 *
 * @author 李翰
 * @since 2023-5-1
 */
@Mapper
public interface CheckCommodity {
    @Select("select * from commodity where isup=1")
    @Results({
            @Result(property="commodityClass",column = "classid",one=@One(select = "com.example.dao.CheckClass.Link_to_Commodity"))
    })
    public List<Commodity> getCommodity();
    @Insert("insert into commodity(goodname,prices,description,isup,urlpath,time,classid) value(#{goodname},#{prices},#{description},#{isup},#{urlpath},Now(),#{classid})")
    public int insertCommmdity(Commodity commmodity);
    @Delete("delete from commodity where id=#{id}")
    public int  deleteCommdity(int id);
    @Update("update commodity set isup=#{isup} where id=#{id}")
    public int changeIsup(int isup,int id);
    @Select("select * from commodity where classid=#{id}")
    public List<Commodity> Link_to_Class(int id);
    @Select("select * from commodity where id=#{id}")
    @Results({
            @Result(property="commodityClass",column = "classid",one=@One(select = "com.example.dao.CheckClass.Link_to_Commodity"))
    })
    public Commodity getCommodityAc(int id);
    @Select("select * from commodity where goodname like concat('%',#{name},'%')")
    public List<Commodity> getlike(String name);
    @Select("select * from commodity")
    @Results({
            @Result(property="commodityClass",column = "classid",one=@One(select = "com.example.dao.CheckClass.Link_to_Commodity"))
    })
    public List<Commodity> getAll();
    @Select("select * from commodity where id=#{id}")
    public Commodity Link_to_Car(int id);
    @Update("update commodity set goodname=#{goodname},prices=#{prices},description=#{description},isup=#{isup},classid=#{classid} where id=#{id}")
    public int updateAll(Commodity commodity);
}
