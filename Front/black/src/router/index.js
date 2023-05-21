import {createRouter, createWebHashHistory} from 'vue-router'
import home from '../views/blackHome.vue'
import login from "../views/blackLogin.vue"
import introduction from "@/views/Introduction.vue";
const routes = [
  {
    path: '/black',
    component: home,
    meta:{
      title:"管理主页",
      isLogin: true,
      isAdmin: true
    },
  },
  {
    path: '/black/login',
    component: login,
    meta:{
      title:"登录",
      isLogin:false,
      isAdmin:false
    },
  },
  {
    path: '/black/intro',
    component: introduction,
    meta:{
      title: "简介",
      isLogin:false,
      isAdmin:false
    },
  }
]

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes,
})

export default router
router.beforeEach((to,from,next)=>{
  let getLogin=sessionStorage.getItem("isLogin");
  let getAdmin=sessionStorage.getItem("isAdmin");
  if(getLogin==="true"&&getAdmin==="true"){
    next()
    if(to.path==='/black/login'){
      alert("您已登录，将跳转到主页")
      setTimeout(()=>{
        router.push("/black")
      },10)
    }
  }else{
    if(to.meta.isLogin){
      next({
        path:'/black/login'
      })
    }else{
      next()
    }
  }
})
