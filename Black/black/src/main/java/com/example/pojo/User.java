package com.example.pojo;
/**
 *
 * @author 李翰
 * @since 2023-5-1
 */
public class User {
    public int id;
    public int issign;
    public String username;
    public String password;
    public int isadmin;
    public int role;
    public int infor;
    public Information information;
    public Role roleInfor;
    public String date;

    public User(){}
    public User(int id,
                String username,
                String password,
                int isadmin,
                int role,
                int infor,
                Role roleInfor,
                Information information,
                String date,
                int issign){
        this.id=id;
        this.username=username;
        this.password=password;
        this.isadmin=isadmin;
        this.role= role;
        this.roleInfor = roleInfor;
        this.information = information;
        this.infor=infor;
        this.date = date;
        this.issign = issign;
    }
    public User(String username,
                String password,
                int isadmin,
                int infor){
        this.username=username;
        this.password=password;
        this.isadmin=isadmin;
        this.role=2;
        this.infor = infor;
        this.issign = 1;
    }
    public User(String username,
                String password,
                int isadmin){
        this.username=username;
        this.password=password;
        this.isadmin=isadmin;
        this.role=2;
        this.issign = 1;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", issign=" + issign +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isadmin=" + isadmin +
                ", role=" + role +
                ", infor=" + infor +
                ", information=" + information +
                ", roleInfor=" + roleInfor +
                ", date='" + date + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public int  getIsadmin(){return isadmin;}

}
