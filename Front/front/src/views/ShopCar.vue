<template>
  <el-container>
    <el-header id="header">
      <Header></Header>
    </el-header>
    <el-main id="context">
		<el-card>
			<el-table
				:data="data"
				stripe
				:key="Math.random()"
				>
				<el-table-column type="selection" width="55" />
				<el-table-column prop="date" label="时间" width="120" sortable align="center"></el-table-column>
				<el-table-column label="商品图" width="220" align="center">
					<template #default="scope">
						<img :src="scope.row.commodity.urlpath" alt="失败" title="商品图" style="width: 50%;height: 50%;"/>
					</template>
				</el-table-column>
				<el-table-column label="名称" width="120" align="center">
					<template #default="scope">
						{{scope.row.commodity.goodname}}
					</template>
				</el-table-column>
				<el-table-column label="价格" width="120" align="center" sortable>
					<template #default="scope">
						{{scope.row.commodity.prices}}
					</template>
				</el-table-column>
				<el-table-column label="描述" width="200" align="center">
					<template #default="scope">
						{{scope.row.commodity.description}}
					</template>
				</el-table-column>
				<el-table-column label="操作" align="center" fixed="right">
					<el-table-column label="数量" align="center">
						<template #default="scope">
							{{scope.row.number}}
						</template>
					</el-table-column>
					<el-table-column label="状态" align="center">
						<template #default="scope">
							<div>
								<p v-if="scope.row.incar">在购物车中</p>
							</div>
							<div>
								<p v-if="scope.row.isfinish">已完成</p>
								<p  v-else>未完成</p>
							</div>
							<div>
								<p v-if="scope.row.ispay">已支付</p>
								<p v-else>未支付</p>
							</div>
						</template>
						
					</el-table-column>
				</el-table-column>
			</el-table>
		</el-card>
		<el-divider />
		<p id="show"><span>为您推荐:</span></p>
		<CommodityList></CommodityList>
	</el-main>
    <el-footer style="background-color: antiquewhite">
		<el-menu class="el-menu-demo" mode="horizontal" style="background-color: antiquewhite">
			<el-menu-item index="0" @click="this.$router.push('/index/intro')" style="">作者介绍</el-menu-item>
			<p style="padding: 0 33%">Copyright&copy;某某科技有限公司</p>
		</el-menu>
    </el-footer>
  </el-container>
</template>

<script>
import axios from "axios";
import {mapState} from "vuex";
import CommodityList from '../components/CommodityList.vue';
import Header from "../components/Header.vue";
export default {
  computed:{
    ...mapState(['visible','AvatarUrl'])
  },
  components:{CommodityList,Header},
  mounted() {
    let form = new FormData();
    form.append("userid",sessionStorage.getItem("Userid"))
    axios.post("/api/car/getac",
        form).then((res)=>{
          this.data=res.data;
		  console.log(res.data)
    });
  },
  data(){
    return{
      AvatarPath:require("../assets/avatar.jpg"),
      search:"",
      data:[],
    }
  },
  methods:{
    Getdata(){

    },
	showChage(data){
		console.log(data)
	}
  }
}
</script>

<style>
#context{
  text-align: center;
  margin-top: 65px;
}
#show{
  text-align: left;
  display: block;
  font-size: large;
  color: red;
}
</style>
