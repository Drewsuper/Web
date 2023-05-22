<template>
  <div id="page">
    <el-header id="headerCommon">
      <el-menu
          class="el-menu-demo,h1"
          mode="horizontal"
          :ellipsis="false">
        <el-menu-item index="0">
          <img src="../../public/logoPage.png" alt="logo" style="height: 55px"/>
        </el-menu-item>
        <el-menu-item index="1" @click="this.$router.push('/black')">管理主页</el-menu-item>
        <div style="flex-grow: 1"/>
        <div v-if="!visible">
          <el-menu-item @click="goLogin()" index="3" class="right">登录</el-menu-item>
        </div>
        <el-sub-menu index="2" v-if="visible">
          <template #title>
            <el-avatar style="margin: 5px 10px"><img :src="AvatarPath" alt="avatar"/></el-avatar>
          </template>
          <el-menu-item >个人中心</el-menu-item>
          <el-menu-item @click="Loginout">退出</el-menu-item>
          <el-menu-item @click="alert('123')">修改密码</el-menu-item>
        </el-sub-menu>
      </el-menu>
    </el-header>
    <el-main style="overflow: auto">
      <div id="form">
        <el-card>
          <el-form :model="formData" label-width="120px">
            <span style="text-align: left">登录</span>
            <el-divider></el-divider>
            <el-form-item label="用户名:" >
              <el-input v-model="formData.username"
                        clearable></el-input>
            </el-form-item>
            <br>
            <el-form-item label="密&nbsp;&nbsp;&nbsp;码:">
              <el-input type="password"
                        v-model="formData.password"
                        show-password
                        clearable
                        @keydown.enter="Login"></el-input>
            </el-form-item>
            <el-divider></el-divider>
            <el-form-item style="margin-bottom:0 ">
              <el-button type="primary" @click="Login">登录</el-button>
              <router-link to="/index/word" style="padding-left: 30%"><el-link type="danger">忘记密码</el-link></router-link>
            </el-form-item>
          </el-form>
        </el-card>
      </div>
    </el-main>
    <el-footer id="footer">
      <el-menu class="el-menu-demo" mode="horizontal" style="background-color: antiquewhite">
        <el-menu-item index="0" @click="this.$router.push('/black/intro')" style="">作者介绍</el-menu-item>
        <p style="padding: 0 33%">Copyright&copy;某某科技有限公司</p>
      </el-menu>
    </el-footer>
  </div>
</template>

<script>
import {mapState} from "vuex";
import axios from "axios";
import {ElMessage} from "element-plus";

export default {
  computed:mapState(["visible"]),
  mounted() {

  },
  data(){
    return{
      formData:{
        username:'',
        password:''
      },
      AvatarPath:sessionStorage.getItem("AvatarUrl"),
      
    }
  },
  methods:{
    goLogin(){
      this.$router.push("/black/login")
    },
    Login(){
      var form=new FormData();
	  var password=window.btoa(this.formData.password)
      form.append('username',this.formData.username);
      form.append('pwd',password);
      axios.post('/api/users/login',form).then((res)=>{
        if(res.data.code >0) {
          sessionStorage.setItem("isLogin","true")
          window.sessionStorage.setItem("UserId",res.data.userid)
          window.sessionStorage.setItem("UserName",this.formData.username)
		  window.sessionStorage.setItem("AvatarUrl",res.data.url);
          let result=res.data.isadmin
          if(result ){
            sessionStorage.setItem("isAdmin","true")
            this.$store.commit("setVisible")
            setTimeout(()=>{
              this.$router.push('/black');
              this.formData.username=""
              this.formData.password=""
            },300)
            ElMessage({
				message:"登陆成功,即将跳转!",
				type:"success",
			})
          }
          else{
            sessionStorage.setItem("isAdmin","false")
            ElMessage({
				message:"非管理员用户!",
				type:"warning",
			})
            this.formData.username=""
            this.formData.password=""
          }
        }else{
            ElMessage.error("登陆失败,请重试!!")
        }
      })
    },
    Loginout(){
      localStorage.removeItem("Userid");
      localStorage.removeItem("Flag");
      window.sessionStorage.removeItem("isLogin")
      window.sessionStorage.removeItem("isAdmin")
      this.$store.commit("setLogin","false");
      this.$router.push('/black/login')
    }
  }
}
</script>

<style scoped>
#form{
  width: 500px;
  height: 350px;
  float: right;
  margin: 15% 5% 10% 80%;
  opacity: 100%;
}
#page{
  background: url("../../public/background.jpg") no-repeat;
  width: 100%;
  height: 100%;
  position: absolute;
  background-size: 100% 100%;
  top: 0;
  left: 0;
}
#forget{
  color: red;
  margin:0 25% 0 60%;
  font-size: 5px
}
#footer{
  background-color: antiquewhite;
  position: absolute;
  bottom: 0;
  width: 100%;
}
#headerCommon{
  position: fixed;
  width: 100%;
  padding: 0 2px;
  top: 0;
  left: 0;
  z-index: 1;
}
</style>
