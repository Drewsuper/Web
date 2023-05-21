<template>
  <div id="personInformation">
    <el-card class="card">
      <template #header>
        <div>
          <span>数据看板</span>
        </div>
      </template>
      <div id="chartFour" style="width: 100%;height: 300px"></div>
    </el-card>
  </div>
  <br>
  <div id="board">
    <el-card class="board">
      <template #header>
        <div>
          <span>用户数量</span>
          <el-button style="float: right" text>详细</el-button>
        </div>
      </template>
      <div id="chartOne"></div>
    </el-card>
    <el-card class="board bandOne">
      <template #header>
        <div>
          <span>商品信息</span>
          <el-button style="float: right" text>详细</el-button>
        </div>
      </template>
     <div id="chartTwo"></div>
    </el-card>
    <el-card class="board bandOne">
      <template #header>
        <div>
          <span>订单数量</span>
          <el-button style="float: right" text>详细</el-button>
        </div>
      </template>
      <div id="chartThere"></div>
    </el-card>
  </div>
</template>

<script>
const echarts = require('echarts');
export default {
  mounted() {
    this.showOne()
    this.showTwo()
    this.showThere()
    this.showFour()
  },
  data(){
    return{

    }
  },
  methods: {
    showOne() {
      let dataX = ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
      let dataY = [5, 10, 12, 45, 60, 79, 80, 90, 100, 154, 156, 160]
      echarts.init(document.getElementById('chartOne'), null, {
        width: 350,
        height: 300
      }).setOption({
        legend: {},
        tooltip: {},
        xAxis: {
          type: 'category',
          data: dataX
        },
        yAxis: {},
        series: [{
          type: 'line',
          color:'red',
          data: dataY
        }]
      })
    },
    showTwo() {
      let dataX = ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
      let dataY = [5, 10, 12, 45, 60, 79, 80, 90, 100, 154, 156, 160]
      echarts.init(document.getElementById('chartTwo'), null, {
        width: 350,
        height: 300,
      }).setOption({
        legend: {},
        tooltip: {},
        xAxis: {
          type: 'category',
          data: dataX
        },
        yAxis: {},
        series: [{
          type: 'line',
          color:'red',
          data: dataY
        }]
      })
    },
    showThere() {
      let dataX = ['31日', '1日', '2日', '3日', '4日', '5日', '6日', '7日', '8日', '9日', '10日', '11日']
      let dataYt = [1, 5, 7, 20, 40, 45, 60, 70, 90, 120, 135, 156]
      let dataYo = [5, 10, 12, 45, 60, 79, 80, 90, 100, 154, 156, 160]
      let dataYe = []
      dataYo.forEach((item,index) => {
        dataYe.push(item-dataYt[index])
      })
      echarts.init(document.getElementById('chartThere'), null, {
        width: 350,
        height: 300,
      }).setOption({
        legend: {},
        tooltip: {},
        xAxis: {
          type: 'category',
          data: dataX
        },
        yAxis: {},
        series:[
          {
            name:'已发数量',
            type:'bar',
            color:'red',
            data: dataYt
          },
          {
            name:'订单数量',
            type: 'bar',
            color:'green',
            data: dataYo
          },
          {
            name:'未发数量',
            type: 'bar',
            color:'yellow',
            data: dataYe
          },
        ]
      })
    },
    showFour(){
      let dataX = ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
      let dataY = [5, 10, 12, 45, 60, 79, 80, 90, 100, 154, 156, 160]
      echarts.init(document.getElementById('chartFour')).setOption({
        legend: {},
        tooltip: {},
        xAxis: {
          type: 'category',
          data: dataX
        },
        yAxis: {},
        series: [{
          type: 'line',
          color:'red',
          data: dataY
        }]
      })
    }
  }
}
</script>

<style scoped>

</style>
