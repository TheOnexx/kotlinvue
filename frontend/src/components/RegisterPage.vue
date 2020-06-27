<template>
    <div class="container">
        <div class="row">
            <form @submit="register" class="col s12">
                <div class="row">
                    <div v-if="errorMsg" class="card-panel">
                        <span class="blue-text text-darken-2">{{errorMsg}}</span>
                    </div>
                    <div class="input-field col s6">
                        <input id="userName" type="text" class="validate" required>
                        <label for="userName">First Name</label>
                        <span class="helper-text" data-error="Username shouldn't be empty"></span>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input id="password" type="password" class="validate" required>
                        <label for="password">Password</label>
                        <span class="helper-text" data-error="Password shouldn't be empty"></span>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input id="email" type="email" class="validate" required>
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
            userName: '',
            password: '',
            email: '',
            errorMsg: ''
        }),

        methods: {
            register() {
                AXIOS.post('/auth/login.do', {userName: this.userName, password: this.password, email: this.email})
                    .then(response => {
                        console.log(response);
                        this.$router.push("/login")
                    }, error => {
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