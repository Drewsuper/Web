<template>
  <el-container >
    <el-header id="headerCom">
      <Header></Header>
    </el-header>
    <el-main id="context">
      	<el-card style="width: 40%;margin-top:5%;margin-left: 30%;margin-bottom: 10%;">
      		<div slot="header" class="clearfix">
      			<span>个人中心</span>
      		</div>
      		<div class="name-role">
      			<span class="sender">Admin - {{dataForm.nickName}}</span>
      		</div>
      		<div class="registe-info">
      			<span class="registe-info">
      				注册时间：
      				<li class="fa fa-clock-o"></li>
      				2020/4/10 9:40:33
      			</span>
      		</div>
      		<el-divider></el-divider>
      		<div class="personal-relation">
      			<div class="relation-item">手机号: <div style="float: right; padding-right:20px;">{{dataForm.phone}}</div>
      				</div>
      		</div>
      		<div class="personal-relation">
      			<div class="relation-item">所属企业: <div style="float: right; padding-right:20px;">杭州诚聚</div>
      			</div>
      		</div>
      		<div class="personal-relation">
      			<div class="relation-item">电子邮箱: <div style="float: right; padding-right:20px;">{{dataForm.homeUrl}}</div>
      			</div>
      		</div>
			<div slot="footer" class="dialog-footer">
				<el-button size="mini" type="primary">修改</el-button>
				<el-button size="mini" type="warning">关闭</el-button>
			</div>
      	</el-card>
    </el-main>
    <el-footer style="background-color: antiquewhite">
      <el-menu class="el-menu-demo" mode="horizontal" style="background-color: antiquewhite">
        <el-menu-item index="0" @click="this.$router.push('/index/intro')" style="">作者介绍</el-menu-item>
        <p style="padding: 0 33%">Copyright&copy;某某科技有限公司</p>
      </el-menu>
    </el-footer>
  </el-container>
</template>

<script>
import {mapState} from "vuex";
import Header from "../components/Header.vue";

export default {
  computed:mapState(["visible","AvatarUrl"]),
  components:{Header},
  mounted() {
  	
  },
  data(){
    return{
		dataForm: {
			nickName: '超级管理员',
			phone: '173567777777',
			homeUrl: 'http://www.baidu.com'
		}
    }
  },
  methods: {
   
  }
}
</script>

<style scoped>
.containerOne{
  height: 900px;
  overflow: auto;
}
#context{
  margin: 0 0;
  padding: 0 0;
}
.clearfix:before,.clearfix:after {
		display: table;
		content: "";
	}
	.clearfix:after {
		clear: both
	}

	.box-card {
		width: 100%;
	}

	.name-role {
		font-size: 16px;
		padding: 5px;
		text-align: center;
	}

	.sender {
		text-align: center;
	}

	.registe-info {
		text-align: center;
		padding-top: 10px;
	}

	.personal-relation {
		font-size: 16px;
		padding: 0px 5px 15px;
		margin-right: 1px;
		width: 100%
	}

	.relation-item {
		padding: 12px;
	}

	.dialog-footer {
		padding-top: 10px;
		padding-left: 10%;
	}
	.el-row {
		margin-bottom: 20px;
	}

	.el-col {
		border-radius: 4px;
	}
	.bg-purple-dark {
		background: #99a9bf;
	}
	.bg-purple {
		background: #d3dce6;
	}

	.bg-purple-light {
		background: #e5e9f2;
	}

	.grid-content {
		border-radius: 4px;
		min-height: 36px;
	}

	.row-bg {
		padding: 10px 0;
		background-color: #f9fafc;
	}

</style>
