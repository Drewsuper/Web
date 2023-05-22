<template>
  <div>
    <el-card class="card">
      <template #header>
        <div>
          <span>数据显示</span>
          <el-button style="float: right" text>操作按钮</el-button>
        </div>
      </template>
      <div id="charts" style="width: auto;height: 600px"></div>
    </el-card>
  </div>
</template>

<script>
const charts = require('echarts')
export default {
  mounted() {
    this.initEcharts()
  },
  data(){
    return{

    }
  },
  methods:{
    initEcharts(){
      charts.init(document.getElementById("charts")).setOption({
        legend: {},
        tooltip: {},
        dataset: {
          source: [
            ['product', '用户数', '商品数', '订单数'],
            ['Matcha Latte', 43.3, 85.8, 93.7],
            ['Milk Tea', 53.1, 73.4, 55.1],
            ['Cheese Cocoa', 66.4, 65.2, 82.5],
            ['Walnut Brownie', 72.4, 53.9, 39.1]
          ]
        },
        xAxis: { type: 'category' },
        yAxis: {},
        series: [{ type: 'line' }, { type: 'line' }, { type: 'line' }]

      })
    }
  }
}
</script>

<style scoped>

</style>
