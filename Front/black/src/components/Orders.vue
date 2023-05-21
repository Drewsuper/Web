<template>
  <div>
    <el-card class="card">
      <template #header>
        <div>
          <span>订单管理</span>
          <el-button style="float: right" text>操作按钮</el-button>
        </div>
      </template>
      <el-table :data="tableData" style="width: 100%" height="600">
        <el-table-column sortable prop="date" label="日期" width="120" align="center"/>
        <el-table-column prop="name" label="下单用户" width="120" align="center"/>
        <el-table-column prop="state" label="数量" width="120" align="center" />
        <el-table-column prop="city" label="收货人" width="220" align="center"/>
        <el-table-column prop="address" label="收货地址" width="450" align="center"/>
        <el-table-column fixed="right" prop="value" label="操作" width="300" align="center">
          <el-table-column label="是否发货" width="100">
            <template #default="scope">
              <el-switch :active-value=1
              			 :inactive-value=0
              			 v-model="scope.row.value"
                         inline-prompt
                         style="--el-switch-on-color: #13ce66;--el-switch-off-color: #ff4949"
                         active-text="已发"
                         inactive-text="未发"></el-switch>
            </template>
          </el-table-column>
          <el-table-column label="编辑" width="200">
            <template #default="scope">
              <el-button type="primary" @click="showFunction(scope.row,1)">删除</el-button>
              <el-button type="success" @click="showFunction(scope.row,2)">修改</el-button>
            </template>
          </el-table-column>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
export default {
	mounted() {
		// axios.post("api/car/get").then((res)=>{
		// 	this.tableData  = res.data;
		// })
	},
  data(){
    return{
      switchValue:0,
      tableData : [
        {
          date: '2016-05-03',
          name: 'Tom',
          state: 'California',
          city: 'Los Angeles',
          address: 'No. 189, Grove St, Los Angeles',
          zip: 'CA 90036',
          value:1,
        },
        {
          date: '2016-05-02',
          name: 'Tom',
          state: 'California',
          city: 'Los Angeles',
          address: 'No. 189, Grove St, Los Angeles',
          zip: 'CA 90036',
          value:0,
        },
        {
          date: '2016-05-04',
          name: 'Tom',
          state: 'California',
          city: 'Los Angeles',
          address: 'No. 189, Grove St, Los Angeles',
          zip: 'CA 90036',
          value:1,
        },
        {
          date: '2016-05-01',
          name: 'Tom',
          state: 'California',
          city: 'Los Angeles',
          address: 'No. 189, Grove St, Los Angeles',
          zip: 'CA 90036',
          value:1,
        },
        {
          date: '2016-05-08',
          name: 'Tom',
          state: 'California',
          city: 'Los Angeles',
          address: 'No. 189, Grove St, Los Angeles',
          zip: 'CA 90036',
          value:0,
        },
        {
          date: '2016-05-06',
          name: 'Tom',
          state: 'California',
          city: 'Los Angeles',
          address: 'No. 189, Grove St, Los Angeles',
          zip: 'CA 90036',
          value:0,
        },
        {
          date: '2016-05-07',
          name: 'Tom',
          state: 'California',
          city: 'Los Angeles',
          address: 'No. 189, Grove St, Los Angeles',
          zip: 'CA 90036',
          value:1,
        }
      ]
    }
  },
  methods:{
    showFunction(data,select){
      if(select===1)
        console.log(data)
      else if(select===2)
        console.log(data.value)
      else if(select===3) alert(this.switchValue)
    }
  }
}
</script>

<style>

</style>
