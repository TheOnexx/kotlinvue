<script>

    import {AXIOS} from "../http-common";

    export default {
        name: "Message",

        data() { return {
                counter: 0,
                message: ''
            };
        },
        methods: {
            submit() {
                AXIOS.post('/message', {counter: this.counter, message: this.message})
            },
            write() {
                AXIOS.get('/message')
                    .then(response => {
                        this.$data.counter = response.data.id;
                        this.$data.message = response.data.message;
                    })
            }
        },
        mounted() {
            this.write();
        }

    }
</script>

<template>
    <div class="message">
        <form @submit="submit">
            <strong>Message:</strong>
            <input type="text" class="form-control" v-model="message">
            <button class="btn btn-success">Submit</button>
        </form>
        <strong>Output:</strong>
        <p>Counter: {{counter}}</p>
        <p>Message: {{message}}</p>

        <ul>
            <li class="nav-item">
                <router-link class="nav-link" to="/test">To test page</router-link>
            </li>
        </ul>
    </div>
</template>

<style scoped>

</style>