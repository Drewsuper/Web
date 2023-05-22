<template>
  <div v-loading="loading"
		element-loading-background="darkgrey"
		element-loading-text="加载中...">
    <el-card class="card">
      <template #header>
        <div>
          <span>商品编辑</span>
          <el-button style="float: right" type="primary" @click="showNew()">添加商品</el-button>
        </div>
      </template>
      <el-table :data="tableData" style="width: 100%" height="600">
        <el-table-column sortable prop="time" label="日期" width="120" align="center"/>
        <el-table-column prop="goodname" label="品名" width="120" align="center"/>
        <el-table-column prop="prices" label="价格" width="120" align="center" />
		<el-table-column label="商品图" width="200">
			<template #default="scope">
				<el-image lazy :src="scope.row.urlpath" :fit="fill" alt="未找到" style="width: 5em;height: 5em;"></el-image>
			</template>
		</el-table-column>
		<el-table-column label="所属类" width="100">
			<template #default="scope">
				{{scope.row.commodityClass.classname}}
			</template>
		</el-table-column>
        <el-table-column prop="description" label="描述" width="400" align="center"/>
        <el-table-column fixed="right" label="操作" width="300" align="center">
          <el-table-column label="是否上架" width="100">
            <template #default="scope">
              <el-switch :active-value=1
						 :inactive-value=0
						 v-model="scope.row.isup"
                         inline-prompt
                         style="--el-switch-on-color: #13ce66;--el-switch-off-color: #ff4949"
                         active-text="上架"
                         inactive-text="下架"
						 @change="upIsup(scope.row)"></el-switch>
            </template>
          </el-table-column>
          <el-table-column label="编辑" width="200">
            <template #default="scope">
              <el-button type="danger" @click="showFunction(scope.row,1)">删除</el-button>
              <el-button type="primary" @click="showFunction(scope.row,2)">修改</el-button>
            </template>
          </el-table-column>
        </el-table-column>
      </el-table>
    </el-card>
	<el-dialog
			v-model="DialogVisible"
			width="50%"
			class="dialogForm"
			>
		<template #header>
			<span>添加商品</span>
			<el-divider/>
		</template>
		<el-form :model="dialogData"
				label-width="12%" id="DialogForm">
			<el-form-item label="名称:">
				<el-input v-model='dialogData.name' placeholder="商品名称"></el-input>
			</el-form-item>
			<el-form-item label="价格:">
				<el-input-number v-model='dialogData.prices' placeholder="商品价格" />元
			</el-form-item>
			<el-form-item label="图片:">
				<el-upload  action="#"
                    :show-file-list="false"
                    :on-change="handleAvatarChange"
                    :auto-upload="false"
                    id="uploader"
                    :limit="5"
                    ref="uploader"
				>
          <img v-if="tempPng.imgUrl" :src="tempPng.imgUrl" style="width: 100px;height: 100px;" alt=""/>
          <el-icon id="avatar-uploader"><img src="../assets/upload.png" alt="" style="width: 1em;height: 1em"/></el-icon>
          {{this.mesError}}
				</el-upload>
				<el-button id="uploadButtonTwo" type="primary" @click="submitImage">提交</el-button>
			</el-form-item>
			<el-form-item label="是否上线:">
				<el-switch	:active-value=1
							:inactive-value=0
							v-model="dialogData.isup"
							inline-prompt
							style="--el-switch-on-color: #13ce66;--el-switch-off-color: #ff4949"
							active-text="上架"
							inactive-text="下架"></el-switch>
			</el-form-item>
			<el-form-item label="描述:">
				<el-input v-model='dialogData.description' type="textarea" placeholder="商品描述"></el-input>
			</el-form-item>
			<el-form-item label="类型:">
				<el-select v-model="dialogData.classid">
					<el-option
					v-for="item in classData"
					:key="item.id"
					:label="item.classname"
					:value="item.id"
					/>
				</el-select>
			</el-form-item>
		</el-form>
		<template #footer>
			<div>
				<el-button type='success' @click="dialogFunction(1)">提交</el-button>
				<el-button type='danger' @click="dialogFunction(2)">取消</el-button>
			</div>
		</template>
	</el-dialog>
  </div>
  <el-dialog
  			v-model="Visible"
  			width="50%"
  			class="dialogForm"
  			draggable>
  		<template #header>
  			<span>修改信息</span>
  			<el-divider/>
  		</template>
  		<el-form :model="dialogData"
  				label-width="12%" id="DialogForm">
  			<el-form-item label="名称:">
  				<el-input v-model='dialogData.goodname' placeholder="商品名称"></el-input>
  			</el-form-item>
  			<el-form-item label="价格:">
  				<el-input-number v-model='dialogData.prices' placeholder="商品价格" />元
  			</el-form-item>
  			<el-form-item label="是否上线:">
  				<el-switch	:active-value=1
  							:inactive-value=0
  							v-model="dialogData.isup"
  							inline-prompt
  							style="--el-switch-on-color: #13ce66;--el-switch-off-color: #ff4949"
  							active-text="上架"
  							inactive-text="下架"></el-switch>
  			</el-form-item>
  			<el-form-item label="描述:">
  				<el-input v-model='dialogData.description' type="textarea" placeholder="商品描述"></el-input>
  			</el-form-item>
			<el-form-item label="类型:">
				<el-select v-model="dialogData.classid">
					<el-option
					v-for="item in classData"
					:key="item.id"
					:label="item.classname"
					:value="item.id"
					/>
				</el-select>
			</el-form-item>
  		</el-form>
  		<template #footer>
  			<div>
  				<el-button type='success' @click="updateInfor(1)">提交</el-button>
  				<el-button type='danger' @click="updateInfor(2)">取消</el-button>
  			</div>
  		</template>
  	</el-dialog>
</template>

<script>
import axios from 'axios';
import { ElMessage } from 'element-plus';
export default {
	mounted() {
		axios.post("api/commodity/getall").then((res)=>{
			this.tableData=res.data
			console.log(res.data)
		})
		axios.post("api/class/getall").then((res)=>{
			this.classData = res.data
		})
	},
  data(){
    return{
		loading:false,
		DialogVisible:false,
		Visible:false,
		switchValue:0,
		tableData : [],
		dialogData:{
			name:"",
			prices:"",
			class:"",
			description:"",
			url:"",
			isup:1,
			classid:""
		},
		tempPng:{
			name:"",
			imgUrl:'',
			data:''
		},
		classData:[],
    }
  },
  methods:{
	Showloading(){
		this.loading =true
		setTimeout(()=>{
			this.loading = false
		},1000)
	},
    showFunction(data,select){
      if(select===1){
		  var form = new FormData();
		  form.append("id",data.id);
		  axios.post("/api/commodity/deCOM",form).then((res)=>{
			  ElMessage({
				  showClose:true,
				  message:res.data.mes,
			  })
		  })
	  }
      else{
		  this.dialogData = data;
		  this.Visible = true;
	  }
    },
	showNew(){
		this.DialogVisible = true;
	},
	dialogFunction(select){
		if(select === 1){
			let formData = new FormData();
			formData.append("name",this.dialogData.name);
			formData.append("url",this.dialogData.url);
			formData.append("prices",this.dialogData.prices);
			formData.append("description",this.dialogData.description);
			formData.append("classid",this.dialogData.classid);
			if(this.dialogData.isup){
				formData.append("isup",1);
			}else{
				formData.append("isup",0);
			}
			axios.post("api/commodity/insert",formData).then((res)=>{
				if(res.data.code){
					console.log(res.data.mes)
				}else{
					console.log(res.data)
				}
				setTimeout(()=>{
					this.DialogVisible = false;
				},100)
			})
			this.clearData();
			this.Showloading()
		}else{
			this.DialogVisible = !this.DialogVisible;
			this.clearData();
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
      let fromData = new FormData()
    fromData.append("fileUpload",this.tempPng.data)
    axios.post("api/upimage",fromData,config).then((res)=>{
      let result = res.data.code
      if(result){
        console.log(res.data.url)
        this.mesError = "上传成功！"
        this.dialogData.url = res.data.url
      }else this.mesError = "上传失败，请重试！!"
    })
	},
	clearData(){
		this.dialogData.description="";
		this.dialogData.isup=true;
		this.dialogData.name = "";
		this.dialogData.prices = "";
		this.dialogData.url = "";
		this.tempPng.data = "";
		this.tempPng.name = "";
		this.tempPng.imgUrl = "";
	},
	upIsup(data){
		var form=new FormData();
		form.append("isup",data.isup);
		form.append("id",data.id);
		console.log(data.isup);
		axios.post("api/commodity/upis",form).then((res)=>{
			ElMessage({
				showClose:true,
				message:res.data.mes,
			})
		})
	},
	updateInfor(select){
		if(select ==1){
			this.Visible = false;
			let form = new FormData();
			form.append("name",this.dialogData.name);
			form.append("url",this.dialogData.url);
			form.append("prices",this.dialogData.prices);
			form.append("description",this.dialogData.description);
			form.append("classid",this.dialogData.classid);
			if(this.dialogData.isup){
				form.append("isup",1);
			}else{
				form.append("isup",0);
			}
			axios.post("/api/commodity/upall",form).then((res)=>{
				var result = res.data.code
				if (result){
					ElMessage({
						showClose:true,
						message:res.data.mes,
						type:"success",
					})
				}else{
					ElMessage.error(res.data.mes)
				}
			})
			this.upData();
		}else{
			this.Visible = false;
		}
	},
	upData(){
		this.loading = true;
		setTimeout(()=>{
			axios.post("/api/commodity/getall").then((res)=>{
				this.tableData = res.data;
			})
			this.loading = false;
		},100)
	}
  }
}
</script>

<style>
	.dialogForm .el-dialog__body{
		padding-top: 0;
		padding-bottom: 5%;
	}
	/deep/.dialogForm.el-dialog--center {
    border-radius: 8px;
	}

</style>
