<template>
  <div id="page">
    <div class="common-layout">
      <el-container>
        <el-header id="headerCom">
          <el-menu
              class="el-menu-demo,h1"
              mode="horizontal"
              :ellipsis="false">
            <el-menu-item index="0">
              <img src="../assets/logo.png" alt="logo" style="height: 55px"/>
            </el-menu-item>
            <el-menu-item index="1" @click="this.$router.push('/index')">商城主页</el-menu-item>
            <div style="flex-grow: 1"/>
            <img src="../assets/cart.png" alt="cart" style="height: 30px;padding: 1% 0" @click="this.$router.push('/index/car')">
            <div v-if="!visible">
              <el-menu-item @click="goLogin(2)" index="4" class="right">注册</el-menu-item>
              <el-divider direction="vertical" style="height: auto" />
              <el-menu-item @click="goLogin(1)" index="3" class="right">登录</el-menu-item>
            </div>
            <el-sub-menu index="2" v-if="this.visible">
              <template #title>
                <el-avatar style="margin: 5px 10px"><img :src="AvatarPath" alt="avatar"/></el-avatar>
              </template>
              <el-menu-item >个人中心</el-menu-item>
              <el-menu-item @click="Loginout">退出</el-menu-item>
              <el-menu-item @click="alert('123')">修改密码</el-menu-item>
            </el-sub-menu>
          </el-menu>
        </el-header>
        <el-main>
          <el-card id="intro">
            <h2 style="text-align: center">个人简介</h2>
            <hr size="5" color="red" width="100%">
            <el-avatar style="width: 20%;height:100px;float: right">
              <img src="../../public/background.jpg" alt="未找到">
            </el-avatar>
            <p>姓名：<span>李帆</span></p>
            <p>性别：<span>男</span></p>
            <p>出生年月：<span>2003年7月</span></p>
            <hr>
            <P class="text">
              <strong>教育经历:</strong>
              <br>
              1990.9----1996.7湖口小学<br>
              1996.9----2005.7湖口小学<br>
              2005.9----至今 华软软件学院软件工程系
            </P>
            <hr>
            <p class="text">
              <strong>项目经历：</strong>
              <br>
              2005.12---2006.2，使用ASP.NET技术为班级设计和开发了一个版务管理系统<br>
              2006.6----2006.9，使用JSP技术A公司设计和开发了一个动态网站<br>
              2006.12---至今，参与B公司的一个ESP项目的开发
            </p>
            <hr>
            <p>
              <strong>熟悉的开发工具、数据库、技术及语言：</strong>
              <br>
              开发工具：DEV C++、WebStorm、IntelIIiJ IDEA、VSCode、PyCharm等。<br>
              数据库：MySQL。<br>
              技术及语言：JS、Java、C、python、SpringBoot、Vue。
            </p>
            <hr>
            <p>
              <strong>联系方式：</strong><br>
              TEL：17543038230<br>
              E-mail:LF20182003@outlook.com
            </p>
          </el-card>
        </el-main>
      </el-container>
    </div>
  </div>

</template>

<script>
import {mapState} from "vuex";
export default {
  computed:mapState(["visible"]),
  data(){
    return{
      AvatarPath:require("../assets/avatar.jpg")
    }
  },
  methods:{
    Loginout(){
      localStorage.removeItem("Userid");
      localStorage.removeItem("Flag");
      window.sessionStorage.removeItem("isLogin")
      window.sessionStorage.removeItem("isAdmin")
      this.$store.commit("setLogin",false);
      this.$store.commit("changeVisible")
      this.$router.push('/index')
    },
    goLogin(select){
      if(select === 1) this.$router.push('/index/login')
      else this.$router.push('/index/signup')
    },
  }
}
</script>

<style scoped>
#page{
	background: url("../../public/background.jpg") no-repeat;
	width: 100%;
	height: 104%;
	position: absolute;
	background-size: 100% 100%;
	top: 0;
	left: 0;
}
#intro{
  overflow: auto;
  position: absolute;
  width: 40%;
  left: 30%;
  top: 5%;
  background-color: #98a2ff;
}
#headerCom{
  width: 100%;
  padding: 0 0;
  top: 0;
  left: 0;
  z-index:1;
}
</style>
