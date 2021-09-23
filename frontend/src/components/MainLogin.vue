<template>
  <div class="mainLogin">
    <!-- 로고와 서비스이름 파일 들어갈 예정 -->
    <img alt="Vue logo" src="@/assets/logo.png" class="logo" />
    <!-- 로그인 폼 만들기 -->
    <div class="loginForm">
      <b-form-input
        type="text"
        id="userEmail"
        v-model="userEmail"
        placeholder="이메일"
        class="formuseremail"
      />
      <b-form-input
        type="password"
        v-model="password"
        placeholder="비밀번호"
        :state="validationPassword"
        class="formPassword"
      />
      <div
        :state="validationPassword"
        v-if="validationPassword"
        class="empty"
      ></div>
      <b-form-invalid-feedback
        :state="validationPassword"
        class="validationPassword"
      >
        비밀번호 길이는 4 이상이어야 합니다.
      </b-form-invalid-feedback>
    </div>
    <!-- 클릭할 때 함수 실행 -->
    <button @click="login" @keyup.enter="login" class="loginButton">
      로그인
    </button>
    <div class="links">
      <span class="lostPassword">비밀번호 찾기</span>
      <router-link :to="{ name: 'Signup' }" class="signUp"
        >회원가입</router-link
      >
      <!-- <span class="signup" @click="signupLink">회원가입</span> -->
    </div>
  </div>
</template>

<script>
// import { mapActions } from "vuex";
// import $ from "jquery";
import http from "@/util/http-common";

export default {
  name: "Login",
  data: function () {
    return {
      userEmail: "",
      password: "",
    };
  },
  computed: {
    validationPassword() {
      return this.password.length > 4;
    },
  },
  methods: {
    // ...mapActions(["login"]),
    pageLink() {
      // object
      this.$router.push({ path: "signup" });
    },
    login() {
      http
        .post("api/users/login", {
          userEmail: this.userEmail,
          password: this.password,
        })
        .then((res) => {
          console.log(res);
        })
        .catch((error) => {
          console.log(error);
          alert("로그인 실패");
        });
    },
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
  width: 100px;
  margin: 0 auto 3rem;
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
.formUseremail:focus {
  outline: none;
  box-shadow: none;
  border-color: none;
  color: none;
  /* outline: none !important; */
  /* border:1px solid red; */
  /* box-shadow: 0 0 10px #719E; */
}
.loginButton {
  background-color: #1ec0ff;
  margin: 0 auto 0;
  width: 70%;
  height: 2.3rem;
  border-radius: 3rem;
  font-size: 1rem;
  font-weight: 500;
  color: #fff;
  border: 1px solid transparent;
  height: 2.5rem;
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
</style>