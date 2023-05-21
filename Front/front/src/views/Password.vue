<template>
	<div id="page">
		<el-header id="headerCommon">
			<Header></Header>
		</el-header>
		<el-main style="overflow: auto;height: 700px;">
			<div class="container">
				<el-form ref="form" :model="form" :rules="rules" label-width="100px">
					<el-form-item label="原密码" prop="oldPassword">
						<el-input v-model="form.oldPassword" type="password" show-password></el-input>
					</el-form-item>
					<el-form-item label="新密码" prop="newPassword">
						<el-input v-model="form.newPassword" type="password" show-password></el-input>
					</el-form-item>
					<el-form-item label="确认密码" prop="confirmPassword">
						<el-input v-model="form.confirmPassword" type="password" show-password></el-input>
					</el-form-item>
					<el-form-item>
						<el-button type="primary" @click="submitForm()">提交</el-button>
						<el-button @click="resetForm('form')" style="margin-left: 30%;">重置</el-button>
					</el-form-item>
				</el-form>
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
import axios from "axios";
import { ElMessage } from "element-plus";
import Header from "../components/Header.vue";
export default {
	components:{Header},
	data() {
		return {
			form: {
				oldPassword: "",
				newPassword: "",
				confirmPassword: "",
			},
			rules: {
				oldPassword: [{
						required: true,
						message: "请输入原密码",
						trigger: "blur"
					},
					{
						min: 6,
						max: 20,
						message: "长度在 6 到 20 个字符",
						trigger: "blur"
					},
				],
				newPassword: [{
						required: true,
						message: "请输入新密码",
						trigger: "blur"
					},
					{
						min: 6,
						max: 20,
						message: "长度在 6 到 20 个字符",
						rigger: "blur"
					},
				],
				confirmPassword: [{
						required: true,
						message: "请输入确认密码",
						trigger: "blur"
					},
					{
						validator: (rule, value, callback) => {
							if (value !== this.form.newPassword) {
								callback(new Error("两次输入密码不一致!"));
							} else {
								callback();
							}
						},
						trigger: "blur",
					},
					],
				},
			};
		},
	methods: {
		submitForm(formName) {
			var formdata = new FormData();
			formdata.append("id",sessionStorage.getItem("Userid"));
			formdata.append("pwd",this.form.newPassword);
			axios.post("api/users/pwdup",formdata).then((res)=>{
				if(res.data.code == 1){
					ElMessage({
						showClose:true,
						message:res.data.mes,
						type:"success"
					})
				}else{
					ElMessage.error(res.data.mes)
				}
			})
		},
		resetForm(formName) {
			this.$refs[formName].resetFields();
		},
	},
	};
</script>

<style>
	.container {
		width:500px;
		margin: 0 auto;
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