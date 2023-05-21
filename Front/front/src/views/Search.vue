<template>
  <div class="common-layout">
    <el-container>
      <el-header id="headerCom">
        <Header></Header>
      </el-header>
      <el-main id="context">
		<div class="searchIn">
			<el-input placeholder="搜索框" v-model="mes" @keydown.enter="goSearch()">
				<template #suffix>
					<img :src="require('../assets/Search.png')" style="width: 1em;height: 1em;" alt="未找到" @click="goSearch()"/>
				</template>
			</el-input>  
		</div>
		<CommodityList style="width: 80%;margin-left: 10%;margin-top: 5%"></CommodityList>
		 <el-backtop :bottom="100">
		    <div class="backtop">
		      TOP
		    </div>
		  </el-backtop>
	  </el-main>
    </el-container>
  </div>
</template>

<script>
import axios from "axios";
import { carouselContextKey } from "element-plus";
import {mapState} from "vuex";
import Header from '../components/Header.vue';
import CommodityList from '../components/CommodityList.vue';

export default {
  computed: mapState(["visible","AvatarUrl"]),
  components:{CommodityList,Header},
  mounted() {
    this.mes = this.$route.query.ws;
	var form = new FormData();
	form.append("name",this.mes);
	axios.post("/api/commodity/search",form).then((res)=>{
		this.good_list = res.data;
		console.log(res);
	})
  },
  data(){
    return{
      path:this.AvatarUrl,
      mes:decodeURI(this.$route.query.ws),
    }
  },
  methods:{
	goSearch(){
		this.$router.push({ptah:"/index/so",query:{
			ws:encodeURI(this.mes)
		}})
		var form = new FormData();
		form.append("name",this.mes);
		axios.post("/api/commodity/search",form).then((res)=>{
			this.good_list = res.data;
			console.log(res);
		})
	},
	Todetail(data){
		this.$router.push({path:'/index/detail',query:{
			id:encodeURI(data.id)
		}})
		this.$store.setTitle(data.goodname)
	},
  }
}
</script>


<style>
.searchIn {
	width: 50%;
	height: 10%;
	display: inline-block;
}
.searchIn .el-input{
	width: 70%;
}
</style>
