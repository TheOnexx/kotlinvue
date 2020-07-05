import Vue from 'vue'
import Router from 'vue-router'
import MainPage from "./components/MainPage.vue"
import UserPage from "./components/UserPage.vue"
import CategoriesPage from "./components/CategoriesPage.vue"
import LoginPage from "./components/LoginPage.vue"
import RegisterPage from "./components/RegisterPage.vue"
import AdminPage from "./components/AdminPage.vue"
import AdminProducts from "./components/admin/AdminProducts.vue"
import AdminUsers from "./components/admin/AdminUsers.vue"

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
        },
        {
            path: '/categories',
            name: 'CategoriesPage',
            component: CategoriesPage
        },
        {
            path: '/login',
            name: 'LoginPage',
            component: LoginPage
        },
        {
            path: '/register',
            name: 'RegisterPage',
            component: RegisterPage
        },
        {
            path: '/admin',
            name: 'AdminPage',
            component: AdminPage,
            children: [
                {
                    path: 'products',
                    component: AdminProducts
                },
                {
                    path: 'users',
                    component: AdminUsers
                }]
        }
    ]
})