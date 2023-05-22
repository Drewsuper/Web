<template>
  <div id="page">
    <el-header id="headerCommon">
      <Header></Header>
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
              <el-button type="success" @click="Login">登录</el-button>
              <router-link to="/index/word" style="padding-left: 30%"><el-link type="danger">忘记密码</el-link></router-link>
            </el-form-item>
          </el-form>
        </el-card>
      </div>
    </el-main>
    <el-footer id="footer">
      <el-menu class="el-menu-demo" mode="horizontal" style="background-color: antiquewhite">
        <el-menu-item index="0" @click="this.$router.push('/index/intro')" style="">作者介绍</el-menu-item>
        <p style="padding: 0 33%">Copyright&copy;某某科技有限公司</p>
      </el-menu>
    </el-footer>
  </div>
</template>

<script>
import {mapState} from "vuex";
import axios from "axios";
import {ElMessage} from "element-plus";
import Header from '../components/Header.vue';

export default {
  computed:mapState(["visible"]),
  components:{Header},
  data(){
    return{
      formData:{
        username:'',
        password:''
      },
    }
  },
  methods:{
    Login(){
      var form=new FormData();
      var password=window.btoa(this.formData.password)
      form.append('username',this.formData.username);
      form.append('pwd',password);
      axios.post('/api/users/login',form).then((res)=>{
        let result = res.data.code
        if(result >0) {
          let userid = res.data.userid
          window.sessionStorage.setItem("Userid",userid)
          window.sessionStorage.setItem("isLogin","true");
          sessionStorage.setItem("isAdmin","false")
          sessionStorage.setItem("AvatarUrl",res.data.url)
		  console.log(res.data)
          this.$store.commit("setVisible")
          ElMessage({
			  message:"登陆成功,即将跳转到主页!",
			  type:"success",
		  })
          setTimeout(()=>{
            this.$router.push('/index');
          },500)
        }else{
			
            ElMessage.error("登陆失败,请重试!!",)
        }
      })
    },
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
  height: 104%;
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
  width: 100%;
  padding: 0 0;
  top: 0;
  left: 0;
}

</style>
