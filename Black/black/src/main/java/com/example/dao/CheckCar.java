package com.example.dao;

import com.example.pojo.CarInformation;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CheckCar {
    @Select("select * from carinformation")
    @Results({
            @Result(property="commodity",column = "goodid",one=@One(select = "com.example.dao.CheckCommodity.Link_to_Car"))
    })
    public List<CarInformation> getAll();
    @Select("select * from carinformation where userid=#{userid} and incar=1")
    @Results({
            @Result(property="commodity",column = "goodid",one=@One(select = "com.example.dao.CheckCommodity.Link_to_Car"))
    })
    public List<CarInformation> getAccurate(int userid);
    @Insert("insert into carinformation(userid,goodid,isfinish,incar,ispay,date) value(#{userid},#{goodid},#{isfinish},#{incar},#{ispay},curdate())")
    public int insert(CarInformation carInformation);
    @Delete("delete * from carinformation where id=#{id}")
    public int deleteCar(int id);

}
