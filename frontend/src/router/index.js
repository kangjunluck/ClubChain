import Vue from "vue";
import VueRouter from "vue-router";
import Main from "../views/Main.vue";
import Signup from "../components/user/Signup.vue";
import Enterclub from "../components/club/Enterclub.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Main",
    component: Main,
  },
  
  {
    path: "/signup",
    name: "Signup",
    component: Signup,
  },
  {
    path: "/club/enter",
    name: "Enterclub",
    component: Enterclub,
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
