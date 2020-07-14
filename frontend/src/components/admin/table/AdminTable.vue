<template>
    <div>
        <table class="centered">
            <tr>
                <th v-for="header in headers"
                :key="header.id">
                    {{header}}
                </th>
            </tr>
            <TableRow v-for="row in tableData"
                      :key="row.objectId"
                      :objectId="row.objectId"
                      :rowData="row"></TableRow>
        </table>
    </div>
</template>

<script>
    import TableRow from './TableRow'

    export default {
        name: "AdminTable",

        props: {
            headers: {
                type: Array,
                default: () => {
                    return []
                }
            },
            tableData: {
                type: Array,
                default: () => {
                    return []
                }
            }
        },

        components: {
            TableRow
        },

        data: () => ({})
    }
</script>

<style scoped>

</style>