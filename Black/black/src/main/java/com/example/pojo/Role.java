package com.example.pojo;

import java.util.List;

public class Role {
    public int id;
    public String name;
    public List<User> users;

    public Role(){}

    public Role(int id,
                String classname,
                List<User> users){
        this.id=id;
        this.name=classname;
        this.users = users;
    }

    public Role(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
