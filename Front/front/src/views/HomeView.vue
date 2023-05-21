<template>
  <el-container >
    <el-header id="header">
      <Header></Header>
    </el-header>
    <el-main id="context">
      <el-carousel :interval="2000" type="card" height="300px" >
        <el-carousel-item v-for="item in 4" :key="item">
          <el-image :src="require('../../public/background.jpg')"></el-image>
        </el-carousel-item>
      </el-carousel>
	  <CommodityList style="width: 90%;margin-left: 5%;"></CommodityList>
       <el-backtop :bottom="100">
          <div class="backtop">
            TOP
          </div>
        </el-backtop>
    </el-main>
    <el-footer style="background-color: antiquewhite">
      <el-menu class="el-menu-demo" mode="horizontal" style="background-color: antiquewhite">
        <el-menu-item index="0" @click="this.$router.push('/index/intro')" style="">作者介绍</el-menu-item>
		<p style="padding-left: 72.3%;">&copy;2023 Technology Company</p>
      </el-menu>
    </el-footer>
  </el-container>
</template>

<script>
import {mapState} from "vuex";
import CommodityList from '../components/CommodityList.vue';
import Header from "../components/Header.vue";
export default {
	components:{CommodityList,Header},
  computed:mapState(["visible"]),
  data(){
    return{
     
    }
  },
  methods:{
    
  }
}
</script>

<style>
#header{
  position: fixed;
  width: 100%;
  padding: 0 2px;
  opacity: 80%;
  top: 0;
  left: 0;
  z-index: 1;
}
.right{
  float: right;
}
#context{
  text-align: center;
  margin-top: 65px;
}
.backtop{
	height: 100%;
	width: 100%;
	background-color: var(--el-bg-color-overlay);
	box-shadow: var(--el-box-shadow-lighter);
	text-align: center;
	line-height: 40px;
	color: #1989fa;
}
</style>
