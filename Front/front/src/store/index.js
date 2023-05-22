import { createStore } from 'vuex'

export default createStore({
  state: {
    username:window.sessionStorage.getItem("Userid"),
    isLogin:window.sessionStorage.getItem("isLogin"),
    isAdmin:window.sessionStorage.getItem("isAdmin"),
    visible:(sessionStorage.getItem("isLogin")==="true"),
    title:sessionStorage.getItem("name"),
    AvatarUrl:sessionStorage.getItem("AvatarUrl"),
  },
  getters: {
    isLogin:state => state.isLogin,
  },
  mutations: {
    setLogin(state,flag){
      state.isLogin=flag
      window.sessionStorage.setItem('isLogin',flag)
      state.visible=(sessionStorage.getItem("isLogin")==="true")
    },
    setAdmin(state,flag){
      state.isAdmin=flag
      window.sessionStorage.setItem("isAdmin",flag)
      state.visible=(sessionStorage.getItem("isLogin")==="true")
    },
    loginout(state){
      sessionStorage.removeItem("Userid")
      sessionStorage.removeItem("isLogin")
      sessionStorage.removeItem("isAdmin")
      state.visible=(sessionStorage.getItem("isLogin")==="true")
    },
    setVisible(state){
      state.visible= (sessionStorage.getItem("isLogin")==="true")
    },
	setTitle(state,name){
		state.title = name
		window.sessionStorage.setItem("name",name)
	}
  },
  actions: {
    setUser({commit},flag){
      commit("setLogin",flag)
    },
  },
  modules: {
  }
})
