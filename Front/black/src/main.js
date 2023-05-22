import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import element from "element-plus"
import "element-plus/dist/index.css"

createApp(App).use(store).use(element).use(router).mount('#app')
router.beforeEach((to,from,next)=>{
    if(to.meta.title){
        document.title=to.meta.title
    }
    next()
})
