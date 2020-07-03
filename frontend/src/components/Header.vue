<template>
    <div>
        <nav class="#039be5 light-blue darken-1">
            <div class="nav-wrapper">
                <router-link to="/" class="brand-logo">Dev Shop</router-link>
                <ul id="nav-mobile" class="right hide-on-med-and-down">
                    <router-link
                            tag="li"
                            to="/categories"
                            active-class="active"
                    >
                        <a href="#">Categories</a>
                    </router-link>
                    <router-link
                            v-if="!this.isLogin"
                            tag="li"
                            to="/login"
                            active-class="active"
                    >
                        <a href="#">Sign in</a>
                    </router-link>
                    <router-link
                            v-else
                            tag="li"
                            to="/categories"
                            active-class="active"
                    >
                        <a href="#">Personal Page</a>
                    </router-link>
                    <router-link
                            v-if="!this.isLogin"
                            tag="li"
                            to="/register"
                            active-class="active"
                    >
                        <a href="#">Sign up</a>
                    </router-link>
                    <li v-if="this.isLogin" v-on:click="logout" class="active">Logout!</li>

                </ul>
            </div>
        </nav>
    </div>
</template>

<script>
    import {mapState} from 'vuex'
    export default {
        name: "Header",

        computed: {
            ...mapState({
                isLogin: 'isLogin'
            })
        },

        methods: {
            logout() {
                console.log("logout start");
                this.$store.dispatch('logout_action');
                this.$router.push("/")
            }
        }

    }
</script>

<style scoped>

</style>