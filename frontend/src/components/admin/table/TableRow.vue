<template>
    <tr>
        <td v-for="(column, index) in rowDataArray"
            :key="index">{{column}}
        </td>
    </tr>
</template>

<script>
    export default {
        name: "TableRow",

        props: {
            rowData: {
                type: Object,
                default: () => {
                    return {}
                }
            },
            objectId: {
                type: String,
                default: () => {
                    return ''
                }
            }
        },
        computed: {
            rowDataArray: function () {
                let arr = Object.keys(this.rowData).map(i => this.rowData[i]);
                arr.shift();
                return arr;
            }
        }
    }
</script>

<style scoped>

</style>