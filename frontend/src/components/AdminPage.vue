<template>
    <div class="adm_p">
        Admin page
        <AdminNavBar/>
        <router-view class ="adm_p__content"/>
    </div>
</template>

<script>

    import AdminNavBar from "./admin/AdminNavBar";

    export default {
        name: "AdminPage",
        components: {AdminNavBar},
        comments: {
            AdminNavBar
        }


    }
</script>

<style scoped>

    .adm_p {
        display: grid;
        grid-template-columns: auto 1fr;
        grid-template-rows: auto;
        grid-template-areas: "header header" "nav content" "nav helper";
    }

    .adm_p__content {
        grid-area: content;
    }

</style>