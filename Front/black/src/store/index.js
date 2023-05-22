import { createStore } from 'vuex'

export default createStore({
  state: {
    isAdmin:sessionStorage.getItem("isAdmin"),
    isLogin:sessionStorage.getItem("isLogin"),
    visible:(sessionStorage.getItem("isAdmin")==="true"&&sessionStorage.getItem("isLogin")==="true")
  },
  getters: {
  },
  mutations: {
    setLogin(state,flag){
      state.isLogin=flag;
      window.sessionStorage.setItem("isLogin",flag)
      state.visible=(sessionStorage.getItem("isAdmin")==="true"&&sessionStorage.getItem("isLogin")==="true")
    },
    seAdmin(state,flag){
      state.isAdmin=flag;
      window.sessionStorage.setItem("isAdmin",flag)
      state.visible=(sessionStorage.getItem("isAdmin")==="true"&&sessionStorage.getItem("isLogin")==="true")
    },
    setLoginout(state){
      sessionStorage.removeItem("isLogin");
      sessionStorage.removeItem("isAdmin");
      state.visible=(sessionStorage.getItem("isAdmin")==="true"&&sessionStorage.getItem("isLogin")==="true")
    },
    setVisible(state){
      state.visible=(sessionStorage.getItem("isAdmin")==="true"&&sessionStorage.getItem("isLogin")==="true")
    }
  },
  actions: {
  },
  modules: {
  }
})
