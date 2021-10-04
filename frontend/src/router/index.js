import Vue from "vue";
import VueRouter from "vue-router";
import Main from "../views/Main.vue";
import SignUp from "../views/TheSignUp.vue";
import EnterClub from "../views/TheEnterClub.vue";
import Vote from "../components/vote/Vote.vue";
import Test from "../components/Test.vue";
import ClubList from "../views/TheClubList.vue";
import sendToken from "../components/Token/sendToken.vue";
import Finance from "../views/finance.vue"
import ClubCreate from "../views/TheCreateClub.vue"
import MyClub from "../views/TheMyClub.vue"
import MyPage from "../views/TheMyPage.vue"
import MyinfoUpdate from "../views/TheMyInfoUpdate.vue"
import Photos from "../views/Photos.vue"
import ClubMain from "../views/TheClubMain.vue"

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Main",
    component: Main,
  },
  {
    path: "/signup",
    name: "SignUp",
    component: SignUp,
  },
  {
    path: "/club/enter",
    name: "EnterClub",
    component: EnterClub,
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
    name: "ClubList",
    component: ClubList,
  },
  {
    path: "/sendToken",
    name: "sendToken",
    component: sendToken,
  },
  {
    path: "/club/finance",
    name: "Finance",
    component: Finance,
  },
  {
    path: "/create",
    name: "ClubCreate",
    component: ClubCreate,
  },
  {
    path: "/club/myclub",
    name: "MyClub",
    component: MyClub,
  },
  {
    path: "/mypage",
    name: "MyPage",
    component: MyPage,
  },
  {
    path: "/myinfoupdate",
    name: "MyinfoUpdate",
    component: MyinfoUpdate,
  },
  {
    path: "/photos",
    name: "Photos",
    component: Photos,
  },
  {
    path: "/club/main",
    name: "ClubMain",
    component: ClubMain,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
