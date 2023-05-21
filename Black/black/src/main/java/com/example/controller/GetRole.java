package com.example.controller;

import com.example.dao.CheckRole;
import com.example.pojo.*;
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
@RequestMapping("/role")
public class GetRole {
    @Autowired
    private CheckRole getA;

    @PostMapping("/get")
    @ResponseBody
    public List<Role> getAllRole(){return getA.GetAll();}
    @PostMapping("/getac")
    @ResponseBody
    public Role getAcRole(@RequestParam Map<String,Object> rOne){
        int id = Integer.valueOf(rOne.get("id").toString());
        return getA.GetAc(id);
    }
    @PostMapping("/insert")
    @ResponseBody
    public Map<String,Object> insertRole(@RequestParam Map<String,Object> rOne){
        Map<String,Object> map = new HashMap<>();
        if(rOne.get("classname").toString().length() == 0){
            map.put("code",-1);
            map.put("mes","添加失败！");
        }else{
            Role rTwo = new Role(rOne.get("classname").toString());
            int result  = getA.insertR(rTwo);
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
    public Map<String,Object> deleteR(@RequestParam Map<String,Object> rOne){
        Map<String,Object> map = new HashMap<>();
        if(rOne.get("id").toString().length() == 0 ){
            map.put("code",-1);
            map.put("mes","删除失败");
        }else{
            int id =Integer.valueOf(rOne.get("id").toString());
            int result = getA.deleteR(id);
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
