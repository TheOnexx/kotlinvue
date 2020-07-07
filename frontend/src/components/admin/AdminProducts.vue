<template>
    <div>
        Products!

        <table class="centered">
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Category</th>
                <th>Price</th>
            </tr>
            <tr v-for="product in products" v-bind:key="product.id">
                <td>
                    {{product.name}}
                </td>
                <td>
                    {{product.description}}
                </td>
                <td>
                    {{product.category}}
                </td>
                <td>
                    {{product.price}}
                </td>
            </tr>
        </table>
    </div>
</template>

<script>
    import {AXIOS} from "../../http-common";

    export default {
        name: "AdminProducts",

        data: () => ({
            products: [],
            errorMsg: ''
        }),

        created() {
            AXIOS.get('/products/all')
                .then(response => {
                    this.products = response.data
                },
                e => {
                    this.errorMsg = e.response.data.message
                })
                .catch(e => {
                    this.errorMsg = e.response.data.message
                })
        }
    }
</script>

<style scoped>

</style>