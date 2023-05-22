package com.example.controller;

import com.example.dao.CheckInfor;
import com.example.pojo.Information;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李翰
 * @since 2023-5-21
 *
 */
@Controller
@RequestMapping("/infor")
public class GetInfor {

    @Autowired
    private CheckInfor getdao;


    @PostMapping("/getall")
    public List<Information> get_all(){
        return getdao.getAll();
    }

    @PostMapping("/getac")
    public Information get_ac(@RequestParam Map<String,Object> iOne){
        int id = Integer.valueOf(iOne.get("id").toString());
        return getdao.Link_to_User(id);
    }


    @PostMapping("/getacbyid")
    public Information get_ac_by_user(@RequestParam Map<String,Object>iOne){
        int userid = Integer.valueOf(iOne.get("useid").toString());
        return getdao.getByUserId(userid);
    }

    @PostMapping("/update")
    public Map<String,Object> update(@RequestParam Map<String,Object>iOne){
        Map<String,Object> map = new HashMap<>();
        int userid = Integer.valueOf(iOne.get("useid").toString());
        String adressone = iOne.get("adress").toString();
        String phone = iOne.get("phone").toString();
        String getname = iOne.get("getname").toString();
        String avatar = iOne.get("avatar").toString();
        String email = "";
        String name = "";
        if (iOne.get("email") != null){
            email = iOne.get("email").toString();
        }
        Information information = new Information(userid,name,getname,phone,email,adressone,adressone,avatar);
        int result = getdao.updateAll(information);
        if (result > 0){
            map.put("code",1);
            map.put("mes","修改成功");
        }else{
            map.put("code",-1);
            map.put("mes","修改失败");
        }
        return map;
    }
}
