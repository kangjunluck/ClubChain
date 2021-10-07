<template>
  <div class="mainLogin">
    <!-- 나중에 trim도 추가하기 -->
    <img alt="Vue logo" src="@/assets/CC.svg" class="logo" />
    <!-- 로그인 폼 만들기 -->
    <div class="loginForm">
      <b-form-input
        type="text"
        id="userEmail"
        v-model="credentials.userEmail"
        placeholder="이메일"
        class="formuseremail"
        autocomplete="new-password"
      />
      <b-form-input
        type="password"
        v-model="password"
        placeholder="비밀번호"
        class="formPassword"
        autocomplete="new-password"
      />
      <!-- :state="validationPassword" -->
      <div
        :state="validationPassword"
        v-if="validationPassword"
        class="empty"
      ></div>
      <b-form-invalid-feedback
        :state="validationPassword"
        class="validationPassword"
      >
        <!-- 비밀번호 길이는 4 이상이어야 합니다. -->
      </b-form-invalid-feedback>
    </div>
    <!-- 클릭할 때 함수 실행 -->
    <button @click="loginSubmit" @keyup.enter="loginSubmit" class="loginButton">
      로그인
    </button>
    <div class="links">
      <span class="lostPassword">비밀번호 찾기</span>
      <router-link to="/signup" class="signUp">회원가입</router-link>
      <!-- <span class="signup" @click="signupLink">회원가입</span> -->
    </div>
    <!-- <div class="font1">1</div>
    <div class="font2">2</div>
    <div class="font3">3</div>
    <div class="font4">4</div>
    <div class="font5">5</div> -->
    <FailModal v-if="failModal" :message="message" @close="failModal=false"/>
  </div>
</template>

<script>
// import { mapActions } from "vuex";
// import $ from "jquery";
import http from "@/util/http-common";
import FailModal from "../modal/fail.vue"

export default {
  name: "Login",
  data: function () {
    return {
      password: null,
      credentials: {
        userEmail: null,
        useraccount: null,
        usernickname: null,
        userthumbnail: null,
        userId: null,
      },

      failModal : false,
      message : "",
    }
  },
  components :{
    FailModal,
  },
  computed: {
    validationPassword() {
      if (this.password === null) {
        // 나중에 수정... 비어있을 때 옳음 표시 뜸
        return true;
      } else {
        return this.password.length > 4;
      }
    },
  },
  methods: {
    // checkLogin() {
    //   http
    //     .get("api/users/islogin", { withCredentials: true })
    //     .then((res) => {
    //       if (res.data.statusCode == 200){
    //         this.credentials.userEmail = res.data.userEmail;
    //         this.credentials.useraccount = res.data.useraccount;
    //         this.credentials.usernickname = res.data.usernickname;
    //         this.credentials.userthumbnail = res.data.userthumbnail;
    //         this.credentials.userId = res.data.userId;
    //         this.$store.dispatch("logininfo", this.credentials);
    //         this.$store.dispatch("isLogin");
    //         console.log("로그인 데이터");
    //         this.$router.push("club/list"); 
    //       } else {
    //         console.log("세션 없음")
    //       }
    //     })
    //     .catch((error) => {
    //       console.log(error);
    //     });
    // },
    pageLink() {
      // object
      this.$router.push({ path: "signup" });
    },
    loginSubmit() {
      const formData = new FormData();
      formData.append("userEmail", this.credentials.userEmail);
      formData.append("password", this.password);
      http
        .post("api/users/login", formData, { withCredentials: true })
        // withcredentials를 넣어야 쿠키에 담겨있는 세션 id가 전달됨
        .then((res) => {
          if (res.data.statusCode === 200) {
            this.credentials.userEmail = res.data.userEmail;
            this.credentials.useraccount = res.data.useraccount;
            this.credentials.usernickname = res.data.usernickname;
            this.credentials.userthumbnail = res.data.userthumbnail;
            this.credentials.userId = res.data.userId;
            this.$store.dispatch("logininfo", this.credentials);
            this.$store.dispatch("isLogin");
            console.log("로그인 데이터");
            this.$router.push("club/list");
          }
        })
        .catch((error) => {
          console.log(error);
          this.message = "잘못된 정보입니다."
          this.failModal = true;
        });
    },
  },
  created () {
    // this.checkLogin();
  },
  // jquery로 화면 높이 계산하려고 했음
  // mounted() {
  // var windowHeight = $(window).height();
  // console.log(windowHeight);
  // var topHeight = $("#top").height();
  // $("#content").css({ height: windowHeight - topHeight + "px" });
  // }
};
</script>

<style scoped>
.logo {
  width: 55%;
  margin: 0 auto;
}
.mainLogin {
  height: 100%;
  display: flex;
  flex-direction: column;
  /* background-color: aqua; */
  justify-content: center;
}
.empty {
  height: 1.5rem;
  /* background-color: blue; */
}
.loginForm {
  margin: 0 auto;
  width: 70%;
}
.formuseremail {
  margin: 1.5rem 0 1.5rem 0;
  height: 3rem;
}
.formPassword {
  margin: 1.5rem 0 0 0;
  height: 3rem;
}
/* .formUseremail:focus {
  outline: none;
  box-shadow: none;
  border-color: none;
  color: none;
  /* outline: none !important; */
/* border:1px solid red; */
/* box-shadow: 0 0 10px #719E; 
} */
.loginButton {
  background-color: #1ec0ff;
  margin: 0 auto 0;
  width: 70%;
  border-radius: 3rem;
  font-size: 1rem;
  font-weight: 500;
  color: #fff;
  border: 1px solid transparent;
  min-height: 2.5rem;
  -webkit-appearance: none;
}
.links {
  margin: 1rem 1rem 3rem 0;
  font-size: 0.8rem;
}
.lostPassword {
  color: #1ec0ff;
  margin: 0 1rem 0 0;
}
.signUp {
  color: #1ec0ff;
  margin: 0 0 0 1rem;
  text-decoration-line: none;
}
.validationPassword {
  text-align: left;
  font-size: 0.8rem;
  margin: 2px 0 0 4px;
  height: 22px;
}
.font1 {
  font-weight: 100;
}
.font2 {
  font-weight: 200;
}
.font3 {
  font-weight: 300;
}
.font4 {
  font-weight: 400;
}
.font5 {
  font-weight: 500;
}
</style>