<template>
    <div class="container">
        <div class="row">
            <h1>Login form</h1>
            <form class="col s12" @submit.prevent="handleLogin">
                    <div v-if="errorMsg" class="card-panel">
                        <span class="blue-text text-darken-2">{{errorMsg}}</span>
                    </div>
                <div class="row">
                    <div class="input-field">
                        <input v-model="username" id="userName" type="text" class="validate" required>
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
            username: '',
            password: '',
            errorMsg: '',
        }),

        methods: {
              handleLogin() {

                AXIOS.post('/auth/login.do', {'username' : this.username, 'password' : this.password})
                    .then(response => {
                        console.log("response: " + response);
                        this.$store.dispatch('login_action', {
                            'username': this.username,
                            'role': response.data.role,
                            'isLogin': 'true'
                        });
                        console.log("username: " + this.username);
                        console.log("role: " + response.data.role);
                        this.$router.push("/")
                    }, error => {
                        this.showError(error.response.data.message)
                    })
                    .catch(e => {
                        console.log("ERROR!!! " + e);
                        this.showError('Server error. Please, report this error website owners');
                    });


            },

            showError(errorMessage) {
                this.errorMsg = errorMessage
            }
        }
    }
</script>

<style scoped>
 .container {
     width: 40%;
 }
</style>