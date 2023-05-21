<template>
  <div id="personInformation">
    <el-card class="card">
      <template #header>
        <div>
          <span>个人信息</span>
        </div>
      </template>
      <ul>
        <li v-for="item in 4" :key="item">{{item}}</li>
      </ul>
    </el-card>
  </div>
  <br>
  <div id="board">
    <el-card class="board">
      <template #header>
        <div>
          <span>个人信息</span>
          <el-button style="float: right" text>详细</el-button>
        </div>
      </template>
      <ul>
        <li v-for="item in 4" :key="item">{{item}}</li>
      </ul>
    </el-card>
    <el-card class="board bandOne">
      <template #header>
        <div>
          <span>个人信息</span>
          <el-button style="float: right" text>详细</el-button>
        </div>
      </template>
      <ul>
        <li v-for="item in 4" :key="item">{{item}}</li>
      </ul>
    </el-card>
    <el-card class="board bandOne">
      <template #header>
        <div>
          <span>个人信息</span>
          <el-button style="float: right" text>详细</el-button>
        </div>
      </template>
      <ul>
        <li v-for="item in 4" :key="item">{{item}}</li>
      </ul>
    </el-card>
  </div>
</template>

<script>
  export default {
    data(){
      return{

      }
    },
    methods:{
      functionOne(){
        alert("12345")
      }
    }
  }
</script>

<style>
.board{
  display: inline-block;
  width: 31%;
}
.bandOne{
  margin-left: 3.11%;
}
</style>
