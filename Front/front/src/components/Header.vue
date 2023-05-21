<template>
	<el-menu
	    class="el-menu-demo,h1"
	    mode="horizontal"
	    :ellipsis="false"
		style="height: 100%">
	  <el-menu-item index="0">
	    <img src="../assets/logo.png" alt="logo" style="height: 55px"/>
	  </el-menu-item>
	  <el-menu-item index="1" @click="this.$router.push('/index')">商城主页</el-menu-item>
	  <div style="flex-grow: 1"/>
	  <div id="input">
	    <el-input v-model="search" placeholder="搜索" @keydown.enter="GetData()" clearable>
				<template #suffix>
					<img :src="require('../assets/Search.png')" style="width: 1em;height: 1em;" alt="未找到"/>
				</template>
			  </el-input>
	  </div>
	  <img src="../assets/cart.png" alt="cart" style="height: 30px;padding: 1.2% 0" @click="this.$router.push('/index/car')">
	  <div v-if="!visible" style="margin: 5px 10px">
	    <el-menu-item @click="goLogin(2)" index="4" class="right">注册</el-menu-item>
	    <el-divider direction="vertical" style="height: auto" />
	    <el-menu-item @click="goLogin(1)" index="3" class="right">登录</el-menu-item>
	  </div>
	  <el-sub-menu index="3" v-if="visible">
	    <template #title>
	      <el-avatar style="margin: 5px 10px"><img :src="AvatarPath" alt="avatar"/></el-avatar>
	    </template>
	    <el-menu-item @click="this.$router.push('/index/personal')">个人中心</el-menu-item>
	    <el-menu-item @click="LoginOut()" index="3-1">退出</el-menu-item>
	    <el-menu-item @click="LoginOut()" index="3-2">修改密码</el-menu-item>
	  </el-sub-menu>
	</el-menu>
</template>

<script>
	import {mapState} from "vuex";
	export default {
	  computed:mapState(["visible"]),
	  data(){
	    return{
	      AvatarPath:window.sessionStorage.getItem("AvatarUrl"),
	      search:"",
	    }
	  },
	  methods:{
	    goLogin(select){
	      if(select === 1) this.$router.push('/index/login')
	      else this.$router.push('/index/signup')
	    },
	    LoginOut(){
	      sessionStorage.removeItem("Userid");
	      sessionStorage.removeItem("Flag");
	      window.sessionStorage.removeItem("isLogin")
	      window.sessionStorage.removeItem("isAdmin")
	      this.$store.commit("setLogin",false);
	      this.$router.push('/index');
	    },
	    GetData(){
	      let query = {search:this.search}
	      this.$router.push({path:'/index/so',query:{
			  ws:encodeURI(this.search)
		  }})
	    },
	  }
	}
</script>

<style>
	#input{
	  width: 20%;
	  padding: 1.2% 1% 1.2% 0;
	}
</style>