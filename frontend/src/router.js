import Vue from 'vue'
import Router from 'vue-router'
import MainPage from "./components/MainPage.vue"
import UserPage from "./components/UserPage.vue"

Vue.use(Router);

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'MainPage',
            component: MainPage
        },
        {
            path: '/user',
            name: 'UserPage',
            component: UserPage
        }
    ]
})