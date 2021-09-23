import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
// aixos import -> 전역 사용x
import axios from 'axios'

Vue.prototype.$Axios = axios;
Vue.config.productionTip = false;

import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
// import 'expose-loader?$!expose-loader?jQuery!jquery'

Vue.use(BootstrapVue)

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
