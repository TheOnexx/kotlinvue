<template>
    <div class="adm_p__nav">
        <ul>
            <router-link
                tag="li"
                to="/admin/products"
                active-class="active"
            >
                <a href="#">Products</a>
            </router-link>
            <router-link
                    tag="li"
                    to="/admin/users"
                    active-class="active"
            >
                <a href="#">Users</a>
            </router-link>
        </ul>
    </div>
</template>

<script>
    export default {
        name: "AdminNavBar"
    }
</script>

<style scoped>
    .adm_p__nav {
        grid-area: nav;
        border: 1px dotted;
        margin-right: .75rem;
        padding: .3rem;
    }
</style>