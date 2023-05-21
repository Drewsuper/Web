import {createRouter, createWebHashHistory} from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Login from '../views/Login.vue'
import store from "@/store"
import Intro from "../views/Introduction.vue"
import car from "../views/ShopCar.vue"
import word from "../views/Password.vue"
import commoditydetail from "@/views/Commoditydetail.vue";
import personal from "../views/Personal.vue"
import enroll from "@/views/Enroll.vue";
import search from "../views/Search.vue"


const routes = [
  {
    path: '/index',
    name: 'home',
    component: HomeView,
    meta:{
      isLogin:false,
      isAdmin: false,
      title:"商城主页"
    },
    alias:'/',
  },

  {
    path: '/index/login',
    component: Login,
    meta: {
      isLogin: false,
      isAdmin: false,
      title: "登录"
    }
  },
  {
    path:'/index/intro',
    component: Intro,
    meta:{
      isLogin: false,
      isAdmin: false,
      title:"作者简介"
    }
  },
  {
    path: "/index/car",
    component: car,
    meta:{
      isLogin: true,
      isAdmin: false,
      title: "购物车"
    }
  },
  {
    path:'/index/pwd',
    component: word,
    meta:{
      isLogin: false,
      isAdmin: false,
      title: '修改密码'
    }
  },
  {
    path: "/index/detail",
    component: commoditydetail,
    meta:{
      isLogin: false,
      isAdmin: false,
      title:"详细"
    }
  },
  {
    path:"/index/personal",
    component: personal,
    meta:{
      isLogin: false,
      isAdmin: false,
      title: "个人信息"
    }
  },
  {
    path:"/index/signup",
    component: enroll,
    meta:{
      title: "注册",
      isAdmin: false,
      isLogin: false,
    }
  },
  {
    path:"/index/so",
    component: search,
    meta:{
      title: "搜索",
      isAdmin: false,
      isLogin: false,
    }
  },
  
]

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes
})

export default router


router.beforeEach((to,from,next)=>{
  let getFlag=sessionStorage.getItem("isLogin");
  let getid=sessionStorage.getItem("Userid");
  if(getFlag==="true"&&getid!==null){
    store.isLogin=true;
    next();
    if(to.path==='/index/login'){
      alert("您已登录，将跳转到主页")
      setTimeout(()=>{
        router.push("/index")
      },10)
    }
  }else{
    if(to.meta.isLogin){
      next({
        path:'/index/login'
      })
    }else{
      next()
    }
  }
})

