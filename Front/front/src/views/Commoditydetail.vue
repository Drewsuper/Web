<template>
	<el-container>
		<el-header id="header">
			<Header></Header>
		</el-header>
		<el-main id="context">
			<div class="product-detail">
				<div class="product-image">
					<img :src="data.urlpath" alt="商品图片" />
				</div>
				<div class="product-info">
					<h1 class="product-name">{{data.goodname}}</h1>
					<p class="product-price">￥{{data.prices}}</p>
					<p class="product-desc">{{data.description}}</p>
					<button class="product-buy" @click="showDetail(1)">立即购买</button>
				</div>
			</div>
		</el-main>
		<el-footer style="background-color: antiquewhite">
			<el-menu class="el-menu-demo" mode="horizontal" style="background-color: antiquewhite">
				<el-menu-item index="0" @click="this.$router.push('/index/intro')" style="">作者介绍</el-menu-item>
				<p style="padding-left: 72.3%;">&copy;2023 Technology Company</p>
			</el-menu>
		</el-footer>
	</el-container>
	<el-dialog v-model="dialogVisisble" title="购物详情" style="width: 30%;" draggable>
		<el-form :model="formdata">
		      <el-form-item label="购买数量" :label-width="formLabelWidth">
		        <el-input v-model="formdata.number" autocomplete="off" />
		      </el-form-item>
		      <el-form-item label="收货地址" :label-width="formLabelWidth">
		        <el-select v-model="formdata.address" placeholder="Please select a zone">
		          <el-option label="Zone No.1" value="shanghai" />
		          <el-option label="Zone No.2" value="beijing" />
		        </el-select>
		      </el-form-item>
		    </el-form>
	    <template #footer>
	          <span class="dialog-footer">
	            <el-button @click="showDetail(2)">取消</el-button>
	            <el-button type="primary" @click="buyGood()">确定</el-button>
	          </span>
	        </template>
	</el-dialog>
</template>

<script>
	import {mapState} from "vuex";
	import axios from "axios";
	import Header from '../components/Header.vue';
	import {ElMessage} from "element-plus";
	
	export default {
		computed: mapState(["visible"]),
		components: {Header},
		mounted() {
			let formdata = new FormData();
			formdata.append("id", this.mes);
			axios.post("api/commodity/getAc", formdata).then((res) => {
				this.data = res.data;
			})
		},
		data() {
			return {
				mes: decodeURI(this.$route.query.id),
				data: "",
				dialogVisisble:false,
				formdata:{
					number:0,
					address:"",
				},
			}
		},
		methods: {
			buyGood(){
				if(sessionStorage.getItem("isLogin") === "true"){
					let form = new FormData();
					form.append("userid",sessionStorage.getItem("Userid"));
					form.append("goodid",this.data.id);
					form.append("isfinish",0);
					form.append("ispay",1);
					form.append("incar",1);
					form.append("numner",this.formdata.number);
					axios.post("api/car/insert",form).then((res)=>{
						ElMessage({
							showClose:true,
							message:res.data.mes,
							type:"warning",
						})
					})
				}
				else{
					ElMessage.error("您未登录，请先登录")
				}
				this.showDetail(2);
				this.clearDialogData();
			},
			showDetail(select){
				if (select ==  1){
					this.dialogVisisble = true;
				}
				else{
					this.dialogVisisble = false;
				}
			},
			clearDialogData(){
				this.formdata.number = 0;
				this.formdata.address = "";
			}
		}
	}
</script>

<style>
	.product-detail {
		display: flex;
		flex-direction: column;
		align-items: center;
		margin: 20px;
	}
	.product-image {
		width: 300px;
		height: 300px;
	}
	.product-image img {
		width: 100%;
		height: 100%;
	}
	.product-info {
		width: 300px;
		padding: 10px;
	}
	.product-name {
		font-size: 24px;
		font-weight: bold;
	}
	.product-price {
		color: red;
		font-size: 20px;
	}
	.product-desc {
		margin-top: 10px;
		text-align: center;
	}
	.product-buy {
		width: 100%;
		height: 40px;
		background-color: green;
		color: white;
		border: none;
		border-radius: 5px;
		margin-top: 20px;
	}
	.dialog-footer button:first-child {
	  margin-right: 10px;
	}
</style>