<template>
    <div id="app">
        <h1>Shop Dev</h1>
        <Header/>
        <router-view/>
    </div>
</template>

<script>
    import Header from "./components/Header";
    export default {
        name: 'app',
        components: {Header}

    }
</script>

<style>
    @import "~materialize-css/dist/css/materialize.min.css";
</style>
