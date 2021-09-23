import Vue from "vue";
import VueRouter from "vue-router";
import Main from "../views/Main.vue";
import Signup from "../components/user/Signup.vue";
import Enterclub from "../components/club/Enterclub.vue";
import Vote from "../components/vote/Vote.vue"

import Test from "../components/Test.vue"
import Clublist from "../components/club/Clublist.vue"

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
  },
  {
    path: "/vote",
    name: "Vote",
    component: Vote,
  },
  {
    path: "/test",
    name: "Test",
    component: Test,
  },
  {
    path: "/club/list",
    name: "Clublist",
    component: Clublist,

  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
