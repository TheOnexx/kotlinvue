<template>
    <div>
        Products!
    </div>
</template>

<script>
    import {AXIOS} from "../../http-common";

    export default {
        name: "AdminProducts",

        data: () => ({
            products: []
        }),

        created() {
            AXIOS.get('/')
        }
    }
</script>

<style scoped>

</style>