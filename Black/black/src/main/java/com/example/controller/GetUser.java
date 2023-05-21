package com.example.controller;

import com.example.dao.*;
import com.example.pojo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 *
 * @author 李翰
 * @since 2023-5-1
 */
@Controller
@RequestMapping("/users")
public class GetUser {

    @Autowired
    private CheckUser getdao;
    @Autowired
    private CheckInfor getinfor;

    @PostMapping ("/user")
    @ResponseBody
    public List<User> getUser(){
        return getdao.getUList();
    }
    @PostMapping("/insert")
    @ResponseBody
    public Map<String,Object> insertUser(@RequestParam Map<String,Object> uOne){
        Map<String,Object> map =new HashMap<>();
        if(getdao.getUser(uOne.get("username").toString()).size() > 0){
            map.put("mes","注册失败");
            map.put("code",-1);
            return map;
        }else{
            String adressone = uOne.get("adress").toString();
            String phone = uOne.get("phone").toString();
            String getname = uOne.get("getname").toString();
            String avatar = uOne.get("avatar").toString();
            int isadmin = Integer.valueOf(uOne.get("isadmin").toString());
            User user=new User(
                    uOne.get("username").toString(),
                    uOne.get("password").toString(),
                    isadmin);
            int result = getdao.Insert(user);
            Information infor = new Information(user.id,"",getname,phone,"",adressone,"",avatar);
            getinfor.insert(infor);
            int retwo = getdao.setInforId(infor.id, user.id);
            if(result > 0){
                map.put("mes","注册成功");
                map.put("code",1);
                return map;
            }else{
                map.put("mes","注册失败");
                map.put("code",-2);
                return map;
            }
        }
    }
    @PostMapping("/login")
    @ResponseBody
    public Map<String,Object> login(@RequestParam Map<String,Object> uTwo){
        String username=uTwo.get("username").toString();
        String password=uTwo.get("pwd").toString();
        Base64.Decoder decoder= Base64.getDecoder();
        password= new String(decoder.decode(password));
        Map<String,Object> map=new HashMap<>();
        int result = getdao.getPwd(username).size();
        if( result== 0){
            map.put("code",-1);
        }else{
            if (getdao.getPwd(username).size() <0){
                map.put("code",-1);
            }else{
                User user=getdao.getPwd(username).get(0);
                if (user.getPassword().equals(password)){
                    map.put("code",1);
                    map.put("userid",user.id);
                    map.put("isadmin",user.getIsadmin());
                    map.put("url",user.information.avatar);
                }else{
                    map.put("code",-1);
                }
            }
        }
        return map;
    }
    @PostMapping("/pwdup")
    @ResponseBody
    public Map<String,Object> pwdup(@RequestParam Map<String,Object> uThere){
        Map<String,Object> map = new HashMap<>();
        int id = Integer.valueOf(uThere.get("id").toString());
        String pwd = uThere.get("pwd").toString();
        if(getdao.upPWD(pwd,id) > 0){
           map.put("code",1);
           map.put("mes","修改成功");
        }
        else{
            map.put("code",-1);
            map.put("mes","修改失败");
        }
        return map;
    }
    @PostMapping("/deACC")
    @ResponseBody
    public Map<String,Object> deACC(@RequestParam Map<String,Object> uOne){
        Map<String,Object> map = new HashMap<>();
        int id = Integer.valueOf(uOne.get("id").toString());
        String username = uOne.get("name").toString();
        if(getdao.deleteUser(id) > 0){
            map.put("mes", "删除成功");
            return map;
        }
        else {
            map.put("mes", "删除失败");
            return map;
        }
    }
    @PostMapping("/username")
    @ResponseBody
    public List<String> userName(){
        return getdao.getName();
    }
    @PostMapping("/userac")
    @ResponseBody
    public User getAc(@RequestParam Map<String,Object> uOne){
        int id =Integer.valueOf(uOne.get("id").toString());
        return getdao.getAc(id);
    }
    @PostMapping("/upsn")
    @ResponseBody
    public Map<String,Object> upsign(@RequestParam Map<String,Object> uOne){
        int issign=Integer.valueOf(uOne.get("issign").toString());
        int id =Integer.valueOf(uOne.get("id").toString());
        Map<String,Object> map = new HashMap<>();
        int result = getdao.upsign(issign,id);
        if(result > 0){
            map.put("code",1);
            map.put("mes","修改成功");
            return map;
        }else{
            map.put("code",-1);
            map.put("mes","修改失败");
            return map;
        }
    }

}
