package com.example.controller;
import com.example.dao.CheckCommodity;
import com.example.pojo.Commodity;

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
@RequestMapping("/commodity")
public class GetCommodity {
    @Autowired
    private CheckCommodity getdao;



    @PostMapping("/get")
    @ResponseBody
    public List<Commodity> getCommodity(){
        return getdao.getCommodity();
    }
    @PostMapping("/getall")
    @ResponseBody
    public List<Commodity> getAll(){
        return getdao.getAll();
    }

    @PostMapping("/insert")
    @ResponseBody
    public Map<String,Object> insert(@RequestParam Map<String,Object> commodity){
        Map<String,Object> map = new HashMap<>();
        String url = commodity.get("url").toString();
        String name = commodity.get("name").toString();
        double price = Double.valueOf(commodity.get("prices").toString());
        String description = commodity.get("description").toString();
        int isup = Integer.valueOf(commodity.get("isup").toString());
        int classid = Integer.valueOf(commodity.get("classic").toString());
        Commodity commodityOne = new Commodity(name,description,url,price,isup,classid);
        if(getdao.insertCommmdity(commodityOne) > 0){
            map.put("mes", "修改成功");
            return map;
        }
        else{
            map.put("mes", "修改失败");
            return map;
        }
    }
    @PostMapping("/upis")
    @ResponseBody
    public Map<String,Object> changeIsup(@RequestParam Map<String,Object> cThere){
        Map<String,Object> map = new HashMap<>();
        int isup = Integer.valueOf(cThere.get("isup").toString());
        int id = Integer.valueOf(cThere.get("id").toString());
        if(getdao.changeIsup(isup,id) > 0) {
            map.put("mes", "修改成功");
            return map;
        }
        else {
            map.put("mes", "修改失败");
            return map;
        }
    }
    @PostMapping("/deCOM")
    @ResponseBody
    public Map<String,Object> deCOM(@RequestParam Map<String,Object> cOne){
        Map<String,Object> map = new HashMap<>();
        int id=Integer.valueOf(cOne.get("id").toString());
        if(getdao.deleteCommdity(id) > 0){
            map.put("mes", "修改成功");
            return map;
        }
        else{
            map.put("mes", "修改失败");
            return map;
        }
    }
    @PostMapping("/getAc")
    @ResponseBody
    public Commodity getAcCommodity(@RequestParam Map<String,Object> cOne){
        if(cOne.get("id").toString().length() == 0){
            Commodity commodity =new Commodity();
            return commodity;
        }else{
            int id = Integer.valueOf(cOne.get("id").toString());
            return getdao.getCommodityAc(id);
        }
    }
    @PostMapping("/search")
    @ResponseBody
    public List<Commodity> getlike(@RequestParam Map<String,Object> cOne){
        String name = cOne.get("name").toString();
        return getdao.getlike(name);
    }
    @PostMapping("/upall")
    @ResponseBody
    public Map<String, Object> upAll(@RequestParam Map<String,Object> cOne){
        Map<String,Object> map = new HashMap<>();
        int id = Integer.valueOf(cOne.get("id").toString());
        String name = cOne.get("name").toString();
        double price = Double.valueOf(cOne.get("prices").toString());
        String description = cOne.get("description").toString();
        int isup = Integer.valueOf(cOne.get("isup").toString());
        int classid = Integer.valueOf(cOne.get("classid").toString());
        Commodity commodity = new Commodity(id,name,description,price,isup,classid);
        int result = getdao.updateAll(commodity);
        if(result >0) {
            map.put("code", 1);
            map.put("mes", "修改成功");
        }else{
            map.put("code",-1);
            map.put("mes","修改失败");
        }
        return map;
    }
}
