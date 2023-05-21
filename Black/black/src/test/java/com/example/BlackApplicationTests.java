package com.example;

import com.example.dao.*;
import com.example.pojo.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class BlackApplicationTests {

    @Autowired
    CheckUser getdao;

    @Test
    void contextLoads() {
        User user = getdao.getPwd("lh002").get(0);
        System.out.println(user.information.avatar);
    }

}
