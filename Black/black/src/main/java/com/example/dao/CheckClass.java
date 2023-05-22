package com.example.dao;

import com.example.pojo.Commodity;
import com.example.pojo.CommodityClass;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CheckClass {
    @Select("select * from class")
    @Results({
            @Result(property = "commodities",column = "id",many=@Many(select = "com.example.dao.CheckCommodity.Link_to_Class"))
    })
    public List<CommodityClass> getAll();
    @Select("select * from class where id=#{id}")
    @Results({
            @Result(property = "commodities",column = "id",many=@Many(select = "com.example.dao.CheckCommodity.Link_to_Class"))
    })
    public CommodityClass getAc(int id);
    @Insert("insert into class(classname) value(#{classname})")
    public int insertC(CommodityClass commodityClass);
    @Delete("delete * from class where id=#{id}")
    public int deleteC(int id);
    @Select("select * from class where id=#{id}")
    public CommodityClass Link_to_Commodity(int id);
    @Select("select * from class")
    public List<CommodityClass> getall();
}
