<template>
  <div>
    <el-card class="card">
      <template #header>
        <div>
          <span>分类</span>
          <el-button style="float: right" text>新增分类</el-button>
        </div>
      </template>
	  <el-table :data="dataClass" style="width: 100%" height="600">
		  <el-table-column sortable prop="date" label="创建日期" width="120" align="center"/>
		  <el-table-column prop="classname" label="类名" width="120" align="center"/>
		  <el-table-column  label="商品数" width="120" align="center" >
			  <template #default="scope">
				  {{scope.row.commodities.length}}
			  </template>
		  </el-table-column>
		  <el-table-column fixed="right" prop="value" label="操作" width="300" align="center">
		    <template #default="scope">
		      <el-button type="danger" @click="showFunction(scope.row,1)">删除</el-button>
		      <el-button type="primary" @click="showFunction(scope.row,2)">修改</el-button>
		    </template>
		  </el-table-column>
		</el-table>
    </el-card>
  </div>
</template>

<script>
import axios from 'axios'
export default {
	async mounted() {
		await axios.post("api/class/get").then((res)=>{
			this.dataClass=res.data;
			console.log(res.data)
		})
	},
  data(){
    return{
		dataClass:[]
    }
  },
  methods:{
	showFunction(data,select){
	  if(select===1)
	    console.log(data)
	  else if(select===2)
	    console.log(data)
	},
  }
}
</script>

<style scoped>

</style>
