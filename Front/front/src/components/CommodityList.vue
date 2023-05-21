<template>
	<div class="GoodContext">
			<div v-for="item in dataList" :key="item" class="goodDiv">
				<div @click="Todetail(item)">
					<el-image lazy :src=item.urlpath alt="未找到" class="goods" />
					<span class="prices">¥{{item.prices}}</span>
					{{item.goodname}}
				</div>
			</div>
	</div>
</template>

<script>
	import axios from "axios";
	export default{
		mounted() {
			axios.post('api/commodity/get').then((res)=>{
				this.dataList = res.data;
			})
		},
		data(){
			return{
				dataList:[]
			}
		},
		methods:{
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
	.goods{
		width: 60%;
		height: 60%;
	}
	.goodDiv{
		display: inline-block;
		width: 20%;
		height: 10%;
		margin-left: 3%;
		margin-top: 0.5%;
		cursor: pointer;
	}
	.GoodContext{
		width: 100%;
		height: 100%;
		padding: 0;
	}
	.prices{
		color: red;
		display: block;
	}
</style>