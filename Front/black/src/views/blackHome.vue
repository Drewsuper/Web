<template>
  <el-container>
    <el-header id="headerAdmin" height="60px">
      <el-menu
          class="el-menu-demo"
          mode="horizontal"
          :ellipsis="false"
          default-active="1"
          style="height: 100%;">
        <el-menu-item index="0">
          <img src="../../public/logoPage.png" alt="logo" style="height: 55px"/>
        </el-menu-item>
        <el-menu-item index="1" @click="this.$router.push('/black')">管理主页</el-menu-item>
        <p id="title">商城购物后台</p>
        <div style="flex-grow: 1"/>
        <div v-if="!visible" style="margin: 5px 10px"><!---->
          <el-menu-item @click="goLogin()" index="3" class="right">登录</el-menu-item>
        </div>
        <el-sub-menu index="2" v-if="visible">
          <template #title>
            <el-avatar style="margin: 5px 10px"><img :src="AvatarPath" alt="avatar"/></el-avatar>
          </template>
          <el-menu-item @click="Loginout">退出</el-menu-item>
          <el-menu-item @click="alert('123')">修改密码</el-menu-item>
        </el-sub-menu>
      </el-menu>
    </el-header>
    <el-main>
      <el-container id="blackContainerOne"
                    v-loading="loadingAll"
                    element-loading-background="darkgrey"
                    element-loading-text="加载中..."><!---->
        <el-container id="black_context">
          <el-aside width="200px" class="containerOne">
            <el-menu
                class="el-menu-vertical-demo"
                style="height: 100%"
                background-color="#545c64"
                text-color="aliceblue"
                default-active="0">
			  <el-menu-item index="0" @click="showPage(1)">
                <template #title>
                  <el-icon><img class="icon" src="../assets/person.png" alt="个人"/></el-icon>
                  <span>个人信息</span>
                </template>
              </el-menu-item>
			  <el-menu-item index="5" @click="showPage(9)">
					<template #title>
						<el-icon><img class="icon" src="../assets/dashboard.png" alt="dashboard"/></el-icon>
						<span>数据看板</span>
					</template>
			  </el-menu-item>
              <el-sub-menu index="1">
                <template #title>
                  <el-icon><img class="icon" src="../assets/goods.png" alt="商品"/></el-icon>
                  <span>商品信息</span>
                </template>
                <el-menu-item index="1-1" @click="showPage(2)">
                  <template #title>
                    <el-icon><img class="icon" src="../assets/good_edit.png" alt="分类"/></el-icon>
                    <span>商品编辑</span>
                  </template>
                </el-menu-item>
                <el-menu-item index="1-2" @click="showPage(3)">
                  <template #title>
                    <el-icon><img class="icon" src="../assets/good_class.png" alt="分类"/></el-icon>
                    <span>分类管理</span>
                  </template>
                </el-menu-item>
              </el-sub-menu>
              <el-sub-menu index="2">
                <template #title>
                  <el-icon><img class="icon" src="../assets/order.png" alt="订单"/></el-icon>
                  <span>订单管理</span>
                </template>
                <el-menu-item @click="showPage(4)">
                  <template #title>
                    <el-icon><img class="icon" src="../assets/good_edit.png" alt="订单"/></el-icon>
                    <span>订单管理</span>
                  </template>
                </el-menu-item>
              </el-sub-menu>
              <el-sub-menu index="3">
                <template #title>
                  <el-icon><img class="icon" src="../assets/system.png" alt="系统"/></el-icon>
                  <span>系统管理</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="3-1" @click="showPage(5)">
                    <template #title>
                      <el-icon><img class="icon" src="../assets/user_edit.png" alt="系统"/></el-icon>
                      <span>用户管理</span>
                    </template>
                  </el-menu-item>
                </el-menu-item-group>
              </el-sub-menu>
              <el-menu-item index="4" @click="showPage(8)">
                <template #title>
                  <el-icon><img class="icon" src="../assets/data.png" alt="数据展示"/></el-icon>
                  <span>数据统计</span>
                </template>
              </el-menu-item>
            </el-menu>
          </el-aside>
          <el-main v-loading="loadingModule"
                   element-loading-background="darkgray"
                   element-loading-text="加载中..."
                   style="margin-top: 0.2%"><!---->
            <div>
              <DashBoard v-if="this.visiblePage.visibleDashboard"/>
              <Person v-if="this.visiblePage.visiblePerson"/>
              <GoodsEdit v-if="this.visiblePage.visibleGoodsOne"/>
              <GoodClass v-if="this.visiblePage.visibleGoodsTwo"/>
              <Orders v-if="this.visiblePage.visibleOrdersOne"/>
              <UserEdit v-if="this.visiblePage.visibleUserOne"/>
              <RoleEdit v-if="this.visiblePage.visibleUserTwo"/>
              <PermissionEdit v-if="this.visiblePage.visibleUserThere"/>
              <DataShow v-if="this.visiblePage.visibleData"/>
            </div>
          </el-main>
        </el-container>
        <el-footer style="background-color: antiquewhite;" height="50px">
          <el-menu class="el-menu-demo" mode="horizontal" style="background-color: antiquewhite;height: 50px">
            <el-menu-item index="0" @click="this.$router.push('/black/intro')" style="">作者介绍</el-menu-item>
            <p style="padding: 0 33%">Copyright&copy;某某科技有限公司</p>
          </el-menu>
        </el-footer>
      </el-container>
    </el-main>
  </el-container>
</template>

<script>
import {mapState} from "vuex";
import Person from "@/components/Person.vue";
import UserEdit from "@/components/UserEdit.vue";
import GoodsEdit from "@/components/GoodsEdit.vue";
import GoodClass from "@/components/GoodClass.vue";
import Orders from "@/components/Orders.vue";
import RoleEdit from "@/components/RoleEdit.vue";
import PermissionEdit from "@/components/PermissionEdit.vue";
import DataShow from "@/components/DataShow.vue";
import DashBoard from "@/components/DashBoard.vue";
export default {
   async mounted() {
    setTimeout(()=>{
      this.loadingAll=false
    },1000)
     this.loadingModule=true;
    await setTimeout(()=>{
      this.loadingModule=false;
    },2000)

  },
  components: {Person,UserEdit,GoodsEdit,GoodClass,Orders,RoleEdit,PermissionEdit,DataShow,DashBoard},
  computed:mapState(["visible"]),
  data(){
    return{
      AvatarPath:require("../../public/avatar.jpg"),
      visiblePage:{visiblePerson:true,visibleGoodsOne: false,visibleGoodsTwo: false,visibleOrdersOne:false,
        visibleUserOne:false,visibleUserTwo: false,visibleUserThere: false,visibleData:false,visibleDashboard:false},
      loadingAll:true,
      loadingModule:false,
    }
  },
  methods:{
    goLogin(){
      this.$router.push('/black/login')
    },
    Loginout(){
      sessionStorage.removeItem("Userid");
      sessionStorage.removeItem("Flag");
      window.sessionStorage.removeItem("isLogin")
      window.sessionStorage.removeItem("isAdmin")
      this.$store.commit("setLogin",false);
      this.$router.push('/black/login')
    },
    showPage(select){
      switch (select) {
        case 1:{
          this.visiblePage.visiblePerson=true;this.visiblePage.visibleGoodsOne=false;this.visiblePage.visibleData=false;
          this.visiblePage.visibleGoodsTwo=false;this.visiblePage.visibleOrdersOne=false;this.visiblePage.visibleDashboard=false;
          this.visiblePage.visibleUserOne=false;this.visiblePage.visibleUserThere=false;this.visiblePage.visibleUserTwo=false;
        }break;
        case 2:{
          this.visiblePage.visiblePerson=false;this.visiblePage.visibleGoodsOne=true;this.visiblePage.visibleData=false;
          this.visiblePage.visibleGoodsTwo=false;this.visiblePage.visibleOrdersOne=false;this.visiblePage.visibleDashboard=false;
          this.visiblePage.visibleUserOne=false;this.visiblePage.visibleUserThere=false;this.visiblePage.visibleUserTwo=false;
        }break;
        case 3:{
          this.visiblePage.visiblePerson=false;this.visiblePage.visibleGoodsOne=false;this.visiblePage.visibleData=false;
          this.visiblePage.visibleGoodsTwo=true;this.visiblePage.visibleOrdersOne=false;this.visiblePage.visibleDashboard=false;
          this.visiblePage.visibleUserOne=false;this.visiblePage.visibleUserThere=false;this.visiblePage.visibleUserTwo=false;
        }break;
        case 4:{
          this.visiblePage.visiblePerson=false;this.visiblePage.visibleGoodsOne=false;this.visiblePage.visibleData=false;
          this.visiblePage.visibleGoodsTwo=false;this.visiblePage.visibleOrdersOne=true;this.visiblePage.visibleDashboard=false;
          this.visiblePage.visibleUserOne=false;this.visiblePage.visibleUserThere=false;this.visiblePage.visibleUserTwo=false;
        }break;
        case 5:{
          this.visiblePage.visiblePerson=false;this.visiblePage.visibleGoodsOne=false;this.visiblePage.visibleData=false;
          this.visiblePage.visibleGoodsTwo=false;this.visiblePage.visibleOrdersOne=false;this.visiblePage.visibleDashboard=false;
          this.visiblePage.visibleUserOne=true;this.visiblePage.visibleUserThere=false;this.visiblePage.visibleUserTwo=false;
        }break;
        case 8:{
          this.visiblePage.visiblePerson=false;this.visiblePage.visibleGoodsOne=false;this.visiblePage.visibleData=true;
          this.visiblePage.visibleGoodsTwo=false;this.visiblePage.visibleOrdersOne=false;this.visiblePage.visibleDashboard=false;
          this.visiblePage.visibleUserOne=false;this.visiblePage.visibleUserThere=false;this.visiblePage.visibleUserTwo=false;
        }break;
        case 9:{
          this.visiblePage.visiblePerson=false;this.visiblePage.visibleGoodsOne=false;this.visiblePage.visibleData=false;
          this.visiblePage.visibleGoodsTwo=false;this.visiblePage.visibleOrdersOne=false;this.visiblePage.visibleDashboard=true;
          this.visiblePage.visibleUserOne=false;this.visiblePage.visibleUserThere=false;this.visiblePage.visibleUserTwo=false;
        }break;
        default:break;
      }
      this.showLoading()
    },
    showLoading(){
      this.loadingModule=true
      setTimeout(()=>{
        this.loadingModule=false
      },1500)
    }
  }
}
</script>

<style>
#title{
  padding: 0 32%;
  font-size: 20px;
}
#headerAdmin{
  position: absolute;
  padding: 0 0;
  z-index: 1;
  top:0;
  left: 0;
  bottom: 0;
  right: 0;
}
.right{
  float: right;
}
#black_context{
  margin-top:4%;
}
#blackContainerOne{
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;

}
.icon{
  width: 1em;
  height: 1em;
}
.containerOne{
  overflow: auto;
}
</style>
