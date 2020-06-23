import Vue from 'vue'
import Router from 'vue-router'
import Message from "./components/Message.vue"
import Test from "./components/Test.vue"

Vue.use(Router);

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'Message',
            component: Message
        }
    ]
})