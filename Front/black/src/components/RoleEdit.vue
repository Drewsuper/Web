<template>
  <div>
    <el-card class="card">
      <template #header>
        <div>
          <span>角色编辑</span>
        </div>
      </template>
      <el-table :data="tableData" style="width: 100%" height="600">
        <el-table-column sortable prop="date" label="日期" width="120" align="center"/>
        <el-table-column prop="name" label="Name" width="120" align="center"/>
        <el-table-column prop="state" label="State" width="120" align="center" />
        <el-table-column prop="city" label="City" width="220" align="center"/>
        <el-table-column prop="address" label="Address" width="450" align="center"/>
        <el-table-column prop="zip" label="Zip" width="120" align="center"/>
        <el-table-column fixed="right" prop="value" label="操作" width="300" align="center">
          <el-table-column label="是否上架" width="100">
            <template #default="scope">
              <el-switch v-model="scope.row.value"
                         inline-prompt
                         style="--el-switch-on-color: #13ce66;--el-switch-off-color: #ff4949"
                         active-text="上架"
                         inactive-text="下架"></el-switch>
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
          value:true,
        },
        {
          date: '2016-05-02',
          name: 'Tom',
          state: 'California',
          city: 'Los Angeles',
          address: 'No. 189, Grove St, Los Angeles',
          zip: 'CA 90036',
          value:true,
        },
        {
          date: '2016-05-04',
          name: 'Tom',
          state: 'California',
          city: 'Los Angeles',
          address: 'No. 189, Grove St, Los Angeles',
          zip: 'CA 90036',
          value:false,
        },
        {
          date: '2016-05-01',
          name: 'Tom',
          state: 'California',
          city: 'Los Angeles',
          address: 'No. 189, Grove St, Los Angeles',
          zip: 'CA 90036',
          value:true,
        },
        {
          date: '2016-05-08',
          name: 'Tom',
          state: 'California',
          city: 'Los Angeles',
          address: 'No. 189, Grove St, Los Angeles',
          zip: 'CA 90036',
          value:false,
        },
        {
          date: '2016-05-06',
          name: 'Tom',
          state: 'California',
          city: 'Los Angeles',
          address: 'No. 189, Grove St, Los Angeles',
          zip: 'CA 90036',
          value:true,
        },
        {
          date: '2016-05-07',
          name: 'Tom',
          state: 'California',
          city: 'Los Angeles',
          address: 'No. 189, Grove St, Los Angeles',
          zip: 'CA 90036',
          value:true,
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
