<template>
    <div class="container">
        <div class="row">
            <h1>Login form</h1>
            <form class="col s12" @submit.prevent="handleLogin">
                <div class="row">
                    <div class="input-field">
                        <input v-model="userName" id="userName" type="text" class="validate" required>
                        <label for="userName">Username</label>
                        <span class="helper-text" data-error="Username shouldn't be empty"></span>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field">
                        <input v-model="password" id="password" type="password" class="validate">
                        <label for="password">Password</label>
                    </div>
                </div>
                <button class="btn waves-effect waves-light" type="submit" name="action">Submit
                    <i class="material-icons right"></i>
                </button>
            </form>
        </div>
    </div>
</template>

<script>
    import {AXIOS} from "../http-common";

    export default {
        name: "LoginPage",

        data: () => ({
            userName: '',
            password: ''
        }),

        methods: {
              handleLogin() {
                const loginInfo = {
                    userName: this.userName,
                    password: this.password
                };
                console.log(loginInfo);
                let response = AXIOS.post('/auth/login.do', {'userName' : this.userName, 'password' : this.password});


                console.log(response);
            }
        }
    }
</script>

<style scoped>
 .container {
     width: 40%;
 }
</style>