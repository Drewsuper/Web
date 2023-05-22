<template>
  <div id="enrolbackground">
    <div class="enroll">
    <div class="steps">
      <el-steps :active="active" finish-status="success">
        <el-step title="Step 1" description="用户名、密码"/>
        <el-step title="Step 2" description="用户头像"/>
        <el-step title="Step 3" description="其他基本信息"/>
      </el-steps>
    </div>
    <p style="text-align: center" v-if="this.visibleCard.visibleThere">{{ mesEnroll }}</p>
    <div class="card">
      <el-card v-if="this.visibleCard.visibleOne">
        <template #header>
          <span class="cardHeader">填写用户名和密码:</span>
        </template>
        <el-form
            ref="fromRef"
            :model="formData"
            label-width="100px">
          <br>
          <el-form-item class="from-item" prop="userid" label="用户名"
                        :rules="[{required:true,validator:validateUserName,trigger:'blur'}]">
            <el-input v-model="formData.userid" placeholder="用户名" clearable autofocus></el-input>
          </el-form-item>
          <br>
          <el-form-item class="from-item" prop="password" label="密&nbsp;码"
                        :rules="{required:true,validator:validatePassword,trigger:'blur'}">
            <el-input tpye="password" v-model="formData.password" placeholder="密码" clearable show-password></el-input>
          </el-form-item>
		  <br>
		  <el-form-item class="from-item" prop="repassword" label="确认密码"
		                :rules="{required:true,validator:validatePassword,trigger:'blur'}">
		    <el-input tpye="password" v-model="formData.repassword" placeholder="确认密码" clearable show-password></el-input>
		  </el-form-item>
        </el-form>
        <el-form-item id="itemOne">
          <el-button type="primary" @click="changeStepNext">下一步</el-button>
        </el-form-item>

      </el-card>
      <el-card v-if="this.visibleCard.visibleTwo">
        <template #header>
          <span class="cardHeader">上传头像:</span>
        </template>
        <div>
          <label style="display: block">默认为：</label>
          <img :src="defaultAvatar" style="width: 50px;height: 50px;" alt="未找到"/>
        </div>
        <el-upload  action=""
                    :show-file-list="false"
                    :on-change="handleAvatarChange"
                    :auto-upload="false"
                    id="uploader"
                    :limit="1"
                    ref="uploader"
        >
          <img v-if="tempPng.imgUrl" :src="tempPng.imgUrl" style="width: 100px;height: 100px;" alt=""/>
          <el-icon id="avatar-uploader"><img src="../assets/upload.png" alt="" style="width: 1em;height: 1em"/></el-icon>
          {{this.mesError}}
        </el-upload>
        <el-button id="uploadButtonTwo" type="primary" @click="submitImage">提交</el-button>
        <div class="button-group">
          <el-button type="primary" @click="changeStepPre">上一步</el-button>
          <el-button id="buttonTwo" type="success" @click="changeStepNext">下一步</el-button>
        </div>
      </el-card>
      <el-card v-if="this.visibleCard.visibleThere">
        <template #header>
          <span class="cardHeader">填写默认收货地址:(选填)</span>
        </template><br>
        <el-form :model="fromData"
                 label-width="100px">
          <el-form-item class="from-item" prop="name" label="收货人姓名">
            <el-input v-model="formData.realname" clearable></el-input>
          </el-form-item><br>
          <el-form-item class="from-item" prop="addressone" label="收货地址">
            <el-input v-model="formData.adddressone" clearable></el-input>
          </el-form-item><br>
          <el-form-item class="from-item" prop="phone" label="联系电话">
            <el-input v-model="formData.phone" clearable></el-input>
          </el-form-item>
        </el-form>
        <div class="button-group">
          <el-button type="primary" @click="changeStepPre">上一步</el-button>
          <el-button id="buttonTwo" type="success" @click="changeStepNext">完成</el-button>
        </div>
      </el-card>
    </div>
  </div>
  </div>
</template>

<script>
import {isvalidatePassword, isvalidateUsername} from "@/validate";
import axios from "axios";
import { ElMessage } from "element-plus";
export default {
  mounted() {
    console.log(this.active)
  },
  data(){
    return{
      active : 0,
      visibleCard:{visibleOne : true, visibleTwo : false, visibleThere : false},
      mesEnroll:"即将完成",
      formData:{
        realname:"",
        userid:"",
        password:"",
		repassword:'',
        adddressone:"",
        phone:"",
        imgUrl:"",
      },
      mesError:'',
      tempPng:{
        name:"",
        imgUrl:'',
        data:''
      },
	  defaultAvatar:'http://36.133.127.248:8880/StaticData/avatar.jpg',
	}
  },
  methods:{
    changeStepPre(){
      if(this.active-- <= 1){
        this.active = 0;
      }
      this.changeCard()
    },
    changeStepNext(){
      if(this.formData.userid.length < 0&&this.formData.password.length < 0&& this.formData.repassword.length < 0){
        alert("有必要数据为空！！")
      }else{
		  if(!isvalidateUsername(this.formData.userid) || !isvalidatePassword(this.formData.password)||!isvalidatePassword(this.formData.repassword)){
		  	alert("密码或用户名不符合规则！！")
		  }else{
		  	if(this.formData.password != this.formData.repassword){
		  		alert("两次输入的密码不同")
		  	}else{
		  		if(this.active ++ ===3){
		  			this.active=3;
		  		}
		  		if(this.active===3){
		  			this.sendData()
		  		}
		  		this.changeCard()
		  	}
		  }
	  }
    },
    changeCard(){
        switch (this.active){
          case 0 :{
            this.visibleCard.visibleOne = true;
            this.visibleCard.visibleTwo = false;
            this.visibleCard.visibleThere = false
          }break;
          case 1 :{
            this.visibleCard.visibleOne = false;
            this.visibleCard.visibleTwo = true;
            this.visibleCard.visibleThere = false
          }break;
          case 2:{
            this.visibleCard.visibleOne = false;
            this.visibleCard.visibleTwo = false;
            this.visibleCard.visibleThere = true;
          }break;
          default:{
            this.visibleCard.visibleOne = false;
            this.visibleCard.visibleTwo = false;
            this.visibleCard.visibleThere = true;
          }break
        }
    },
    validateUserName :(rule,value,callback)=>{
      if(value===""){
        callback(new Error("请输入用户名"));
      }else if(!isvalidateUsername(value)){
        callback(new Error("用户名以字母开头 包含特殊字符 至少为4位，最多为18位"));
      }
    },
    validatePassword :(rule,value,callback)=>{
      if(value===""){
        callback(new Error("请输入密码"));
      }else if(!isvalidatePassword(value)){
        callback(new Error("密码以字母开头 包含特殊字符 至少为8位，最多为18位"));
      }
    },
    handleAvatarChange(file){
      const isJPG = file.raw.type === 'image/jpeg'
      const isPNG = file.raw.type === 'image/png'
      const isLt2M = file.raw.size /1024 /1024 < 0.5
      if(!isPNG && !isJPG){
          this.mesError="上传的图片只能是 JPN/PNG 格式！"
      }else if(!isLt2M){
        this.mesError="上传图片的大小不能超过 200 kb!"
      }else if (isLt2M &&(isJPG || isPNG)){
        this.tempPng.imgUrl = URL.createObjectURL(file.raw);
        this.tempPng.data = file.raw
        this.tempPng.name = file.name
      }
    },
    submitImage(){
      let config={
        timeout:3000,
        headers:{
          "Content-Type":'multipart / from-data',
        }
      };
      let fromdata = new FormData()
      fromdata.append("fileUpload",this.tempPng.data)
      axios.post("api/upimage",fromdata,config).then((res)=>{
       let result = res.data.code
        if(result){
          this.mesError = "上传成功！"
          this.formData.imgUrl = res.data.url
        }else this.mesError = "上传失败，请重试！!"
      })
    },
	sendData(){
		var form = new FormData();
		form.append("username",this.formData.userid);
		form.append("password",this.formData.password);
		form.append("adress",this.formData.adddressone);
		form.append("phone",this.formData.phone);
		form.append("avatar",this.formData.imgUrl);
		form.append("getname",this.formData.realname);
		form.append("isadmin",0);
		axios.post("api/users/insert",form).then((res)=>{
			if(res.data.code == 1){
				ElMessage({
					showClose:true,
					message:res.data.mes,
					type:"success",
				})
			}else{
				ElMessage.error(res.data.mes)
			}
		})
		setTimeout(()=>{
			this.$router.push("/index/login")
		},2000)
	}
  }
}
</script>

<style>
  .enroll{
    margin-top: 9%;
    margin-left: 25%;
    width: 50%;
  }
  .steps{
    width: 100%;
    height: 10%;
	margin-left: 5%;
  }
  .card{
	margin-left: 7%;
    margin-top: 5%;
    width: 90%;
    height: 30%;
    opacity: 75%;
  }
  .from-item{
    width: 50%;
    padding-left: 25% ;
  }
  #itemOne{
    padding: 10% 45% 5% 45%;
  }
  #uploader{
    width: 20%;
  }
  #avatar-uploader{
    margin-left: 1%;
    width: 100px;
    height: 100px;
    border-style:dashed;
    border-color: deepskyblue;
    border-width: thin;
  }
  .button-group{
    margin:10% 25% 0 25%;
  }
  #uploadButtonTwo{
    margin-top: 5%;
    margin-left: 20%;
  }
  #buttonTwo{
    margin-left: 50%;
  }
  .cardHeader{
    color: red;
  }
  #enrolbackground{
    background: url("../../public/enrolBackground.jpg") no-repeat;
    width: 100%;
    height: 100%;
    position: absolute;
    background-size: 100% 100%;
    top: 0;
    left: 0;
    bottom: 0;
    right: 0;
  }
</style>
