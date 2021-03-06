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
import ClubUpdate from "../views/TheClubUpdate.vue"
import ClubMypage from "../views/TheClubMyPage.vue";
import MyPage from "../views/TheMyPage.vue"
import MyinfoUpdate from "../views/TheMyInfoUpdate.vue"
import Photos from "../views/Photos.vue"
import ClubMain from "../views/TheClubMain.vue"
import PostWrite from "../views/ThePostWrite.vue"
import PostDetail from "../views/ThePostDetail.vue"
import PostSearch from "../views/ThePostSearch.vue"
import Album from "../views/Album.vue"
import ClubMyPage from "../views/TheClubMyPage.vue"
import PostModify from "../views/ThePostModify.vue"

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
    path: "/club/mypage",
    name: "ClubMyPage",
    component: ClubMyPage,
  },
  {
    path: "/create",
    name: "ClubCreate",
    component: ClubCreate,
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
    path: "/club/ClubMain",
    name: "ClubMain",
    component: ClubMain,
  },
  {
    path: "/club/post/write",
    name: "PostWrite",
    component: PostWrite,
  },
  {
    path: "/club/post/detail",
    name: "PostDetail",
    component: PostDetail,
  },
  {
    path: "/club/post/Search",
    name: "PostSearch",
    component: PostSearch,
  },
  {
    path: "/club/album",
    name: "Album",
    component: Album,
  },
  {
    path:"/club/update",
    name:"ClubUpdate",
    component:ClubUpdate,
  },
  {
    path:"/club/mypage",
    name:"ClubMypage",
    component:ClubMypage,
  },
  {
    path:"/club/post/modify",
    name:"PostModify",
    component:PostModify,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
