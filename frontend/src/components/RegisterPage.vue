<template>
    <div class="container">
        <div class="row">
            <form @submit.prevent="register" class="col s12">
                <div v-if="errorMsg" class="card-panel">
                    <span class="blue-text text-darken-2">{{errorMsg}}</span>
                </div>
                <div class="row">
                    <div class="input-field col s6">
                        <input v-model="username" id="userName" type="text" class="validate" required>
                        <label for="userName">Username</label>
                        <span class="helper-text" data-error="Username shouldn't be empty"></span>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input v-model="password" id="password" type="password" class="validate" required>
                        <label for="password">Password</label>
                        <span class="helper-text" data-error="Password shouldn't be empty"></span>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input v-model="email" id="email" type="email" class="validate" required>
                        <label for="email">Email</label>
                        <span class="helper-text" data-error="Email shouldn't be empty"></span>
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
        name: "RegisterPage",

        data: () => ({
            username: '',
            password: '',
            email: '',
            errorMsg: ''
        }),

        methods: {
            register() {
                AXIOS.post('/auth/register.do', {username: this.username, password: this.password, email: this.email})
                    .then(response => {
                        console.log(response);
                        this.$router.push("/login")
                    }, error => {
                        console.log("error response " + error.response.data.message);
                        this.showError(error.response.data.message)
                    })
                    .catch(
                        e => {
                            console.log("ERROR!!! " + e);
                            this.showError('Server error. Please, report this error website owners');
                        }
                    )
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