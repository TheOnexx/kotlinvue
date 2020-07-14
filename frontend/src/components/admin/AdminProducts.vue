<template>
    <div>
        Products!

        <AdminTable
            :headers="headers"
            :tableData="productsData"/>
    </div>
</template>

<script>
    import {AXIOS} from "../../http-common";
    import AdminTable from './table/AdminTable'

    export default {
        name: "AdminProducts",

        components: {
            AdminTable
        },

        computed: {
            productsData: function () {
                return this.products.map(p => {
                    return {
                        objectId: p.id,
                        name: p.name,
                        description: p.description,
                        category: p.categoryName,
                        price: p.price
                    }
                })
            }
        },

        data: () => ({
            products: [],
            errorMsg: '',
            headers: [
                'Name',
                'Description',
                'Category',
                'Price!'
            ]
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