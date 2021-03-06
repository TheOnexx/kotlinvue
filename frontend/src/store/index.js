import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);
Vue.config.devtools = true;


const state = {
    username: localStorage.getItem('username') || '',
    role: localStorage.getItem('role') || '',
    isLogin: localStorage.getItem("isLogin") || ''
};

const getters = {
    isLogin: state => {
        return state.isLogin === 'true'
    },
    isAdmin: state => {
        return state.role === 'ROLE_ADMIN';
    },
    isDefaultUser: state => {
        return state.role === 'ROLE_USER';
    },

};

const mutations = {
    login: (state, user) => {
        localStorage.setItem('username', user.username);
        localStorage.setItem('role', user.role);
        localStorage.setItem('isLogin', 'true');

        state.username = user.username;
        state.role = user.role;
        state.isLogin = user.isLogin
    },
    logout: (state) => {
        localStorage.setItem('username', '');
        localStorage.setItem('role', '');
        localStorage.setItem('isLogin', '');

        state.username = '';
        state.role = '';
        state.isLogin = '';
    }
};

const actions = {
    login_action: (context, user) => {
        context.commit('login', user)
    },
    logout_action: (context) => {
        context.commit('logout')
    }
};

export const store = new Vuex.Store ({
    state,
    getters,
    mutations,
    actions
});
