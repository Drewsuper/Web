<template>
  <div>
    <el-card class="card">
      <template #header>
        <div>
          <span>用户管理</span>
          <el-button style="float: right" text @click="this.dialogVisible = !this.dialogVisible">添加用户</el-button>
        </div>
      </template>
      <el-table :data="tableData" style="width: 100%" height="600">
        <el-table-column sortable prop="date" label="注册日期" width="120" align="center"/>
        <el-table-column prop="username" label="用户名" width="120" align="center"/>
		<el-table-column label="密码" width="150" align="center">
			<template #default="scope">
				<el-input type="password" v-model="scope.row.password" readonly show-password></el-input>
			</template>
		</el-table-column>
        <el-table-column prop="state" label="用户余额" width="120" align="center" />
        <el-table-column prop="roleInfor.name" label="当前角色" width="220" align="center"/>
        <el-table-column prop="address" label="当前权限" width="200" align="center"/>
        <el-table-column fixed="right" label="操作" width="300" align="center">
          <el-table-column label="是否允许登录" width="100">
            <template #default="scope">
              <el-switch :active-value=1
						 :inactive-value=0
						 v-model="scope.row.issign"
                         inline-prompt
                         style="--el-switch-on-color: #13ce66;--el-switch-off-color: #ff4949"
                         active-text="允许"
                         inactive-text="不允许"
						 @click="upIssign(scope.row.id,scope.row.issign)"></el-switch>
            </template>
          </el-table-column>
          <el-table-column label="编辑" width="200">
            <template #default="scope">
              <el-button type="danger" @click="showFunction(scope.row,1)">删除</el-button>
              <el-button type="success" @click="showFunction(scope.row,2)">修改</el-button>
            </template>
          </el-table-column>
        </el-table-column>
      </el-table>
    </el-card>
	<el-dialog v-model="dialogVisible"
			class="diaolgClass">
		<template #header>
			添加用户
		</template>
		<template #footer>
			<el-button type="success" @click="dialogFunction(1)">提交</el-button>
			<el-button type="danger" @click="dialogFunction(2)">取消</el-button>
		</template>
	</el-dialog>
  </div>
</template>

<script>
import axios from "axios";
import {ElMessage} from "element-plus";
export default {
	async mounted() {
		await axios.post("api/users/user").then((res)=>{
			this.tableData=res.data
		})
	},
  data(){
    return{
	  dialogVisible:false,
      switchValue:0,
      tableData : []
    }
  },
  methods:{
    showFunction(data,select){
      if(select===1)
        console.log(data)
      else if(select===2)
        console.log(data.value)
      else if(select===3) alert(this.switchValue)
    },
	dialogFunction(select){
		if(select === 1){
			this.dialogVisible = !this.dialogVisible;
			
			//this.clearData();
		}else{
			this.dialogVisible = !this.dialogVisible;
			// this.clearData();
		}
		console.log(this.DialogVisible)
	},
	upIssign(id,issign){
		let formdata = new FormData();
		formdata.append("id",id);
		formdata.append("issign",issign);
		axios.post("api/users/upsn",formdata).then((res)=>{
			let result =  res.data.code;
			if(result > 0){
				ElMessage({
					showClose:true,
					message:res.data.mes,
					type:"success",
				})
			}else{
				ElMessage.error(res.data.mes)
			}
		})
	}
  }
}
</script>

<style>
	.diaolgClass .el-dialog__body{
		padding-top :0 ;
		padding-bottom: 5%;
	}
</style>
