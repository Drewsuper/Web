package com.example.controller;

import com.example.dao.CheckCar;
import com.example.pojo.CarInformation;
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
@RequestMapping("/car")
public class GetCar {
    @Autowired
    private CheckCar getdao;

    @PostMapping("/get")
    @ResponseBody
    public List<CarInformation> getAll(){return getdao.getAll();}
    @PostMapping("/getac")
    @ResponseBody
    public List<CarInformation> getac(@RequestParam Map<String,Object> cOne){
        if(cOne.get("userid").toString()==null)
            return null;
        else{
            int userid = Integer.valueOf(cOne.get("userid").toString());
            return getdao.getAccurate(userid);
        }
    }
    @PostMapping("/insert")
    @ResponseBody
    public Map<String,Object> insert(@RequestParam Map<String,Object> cOne){
        Map<String,Object> map = new HashMap<>();
        int userid = Integer.valueOf(cOne.get("userid").toString());
        int goodid = Integer.valueOf(cOne.get("goodid").toString());
        int isfinish = Integer.valueOf(cOne.get("isfinish").toString());
        int ispay = Integer.valueOf(cOne.get("ispay").toString());
        int incar = Integer.valueOf(cOne.get("incar").toString());
        int number = Integer.valueOf(cOne.get("number").toString());
        CarInformation cTwo = new CarInformation(userid,
                goodid,
                isfinish,
                incar,
                ispay,
                number);
        int result = getdao.insert(cTwo);
        if(result > 0){
            map.put("mes", "新建成功");
            return map;
        }
        else{
            map.put("mes", "新建失败");
            return map;
        }
    }
    @PostMapping("/delete")
    @ResponseBody
    public Map<String,Object> deleteCar(@RequestParam Map<String,Object> cOne){
        Map<String,Object> map = new HashMap<>();
        int id = Integer.valueOf(cOne.get("id").toString());
        int result = getdao.deleteCar(id);
        if (result > 0){
            map.put("coed",1);
            map.put("mes","删除成功");
        }else{
            map.put("code",-1);
            map.put("mes","删除失败");
        }
        return map;
    }
}
