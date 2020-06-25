<template>
    <div>
        <nav class="#039be5 light-blue darken-1">
            <div class="nav-wrapper">
                <router-link to="/" class="brand-logo">Dev Shop</router-link>
                <ul id="nav-mobile" class="right hide-on-med-and-down">
                    <router-link
                            tag="li"
                            to="/categories"
                            active-class="active"
                    >
                        <a href="#">Categories</a>
                    </router-link>
                    <router-link
                            tag="li"
                            to="/login"
                            active-class="active"
                    >
                        <a href="#">Sign in</a>
                    </router-link>
                    <router-link
                            tag="li"
                            to="/register"
                            active-class="active"
                    >
                        <a href="#">Sign up</a>
                    </router-link>
                </ul>
            </div>
        </nav>
    </div>
</template>

<script>
    export default {
        name: "Header"
    }
</script>

<style scoped>

</style>