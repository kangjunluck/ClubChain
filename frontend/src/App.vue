<template>
  <div id="app">
    <!-- <div id="nav">
      <router-link to="/">test</router-link> |
      <router-link to="/about">About</router-link> |
      <router-link :to="{ name: 'Main' }">Main</router-link>
    </div> -->

    <router-view class="router_view" />
  </div>
</template>

<script>
export default {
  name: "App",
  data: function () {
    return {
      isLogin: false,
    }
  },
  methods: {},
  created: function () {
    // const token = localStorage.getItem('jwt')
    // if (token) {
    //   this.isLogin = true
    // }
  },
}
</script>



<style>
@font-face{
  font-family: "SpoqaHanSansNeo";
  src: url(./font/SpoqaHanSansNeo-Bold.ttf) format('truetype');
  font-weight: 600;
}
@font-face{
  font-family: "SpoqaHanSansNeo";
  src: url(./font/SpoqaHanSansNeo-Light.ttf) format('truetype');
  font-weight: 300;
}
@font-face{
  font-family: "SpoqaHanSansNeo";
  src: url(./font/SpoqaHanSansNeo-Medium.ttf) format('truetype');
  font-weight: 500;
}
@font-face{
  font-family: "SpoqaHanSansNeo";
  src: url(./font/SpoqaHanSansNeo-Regular.ttf) format('truetype');
  font-weight: 400;
}
@font-face{
  font-family: "SpoqaHanSansNeo";
  src: url(./font/SpoqaHanSansNeo-Thin.ttf) format('truetype') ;
  font-weight: 100;
}
#app {
  font-family: SpoqaHanSansNeo, Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  height: 100%;
}
#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
.router_view {
  height: 100%;
}
</style>
