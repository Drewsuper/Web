package com.example.controller;

import com.example.dao.CheckClass;
import com.example.pojo.CommodityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/class")
public class GetClass {
    @Autowired
    private CheckClass getC;

    @PostMapping("/get")
    @ResponseBody
    public List<CommodityClass> getAllClass(){return getC.getAll();}
    @PostMapping("/getall")
    @ResponseBody
    public List<CommodityClass> getall(){return getC.getall();}
    @PostMapping("/getac")
    @ResponseBody
    public CommodityClass getAcclass(@RequestParam Map<String,Object> cOne){
        int id = Integer.valueOf(cOne.get("id").toString());
        return getC.getAc(id);
    }
    @PostMapping("/insert")
    @ResponseBody
    public Map<String,Object> insertClass(@RequestParam Map<String,Object> cOne){
        Map<String,Object> map = new HashMap<>();
        if(cOne.get("calssname").toString().length() == 0){
            map.put("code",-1);
            map.put("mes","添加失败！");
        }else{
            CommodityClass cTwo = new CommodityClass(cOne.get("classname").toString());
            int result  = getC.insertC(cTwo);
            if(result > 0){
                map.put("code",result);
                map.put("mes","添加成功");
            }else {
                map.put("code", -2);
                map.put("mes", "添加失败！");
            }
        }
        return map;
    }
    @PostMapping("/delete")
    @ResponseBody
    public Map<String,Object> deleteC(@RequestParam Map<String,Object> cOne){
        Map<String,Object> map = new HashMap<>();
        if(cOne.get("id").toString().length() == 0 ){
            map.put("code",-1);
            map.put("mes","删除失败");
        }else{
            int id =Integer.valueOf(cOne.get("id").toString());
            int result = getC.deleteC(id);
            if(result > 0){
                map.put("code",1);
                map.put("mes","删除成功");
            }else{
                map.put("code",-2);
                map.put("mes","删除失败");
            }
        }
        return map;
    }

}
