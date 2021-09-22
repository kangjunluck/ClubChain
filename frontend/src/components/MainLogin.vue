<template>
  <div class="MainLogin">
    <!-- 로고와 서비스이름 파일 들어갈 예정 -->
    <img alt="Vue logo" src="@/assets/logo.png" class="logo item" />
    <!-- 로그인 폼 만들기 -->
    <div class="login_form item">
      <b-form-input
        type="text"
        id="useremail"
        v-model="credentials.useremail"
        placeholder="이메일"
        class="form_useremail"
      />
      <b-form-input
        type="password"
        v-model="credentials.password"
        placeholder="비밀번호"
        class="form_password"
      />
    </div>
    <!-- 클릭할 때 함수 실행 -->
    <button @click="login(credentials)" class="login_button item">로그인</button>
    <div class="links item">
      <span class="lost_password">비밀번호 찾기</span>
      <router-link :to="{ name: 'Signup' }" class="signup"
        >회원가입</router-link
      >
      <!-- <span class="signup" @click="signupLink">회원가입</span> -->
    </div>
  </div>
  
</template>

<script>
import { mapActions } from "vuex";
import $ from "jquery";
export default {
  name: "Login",
  data: function () {
    return {
      credentials: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    ...mapActions(["login"]),
    pageLink() {
      // object
      this.$router.push({ path: "signup" });
    },
  },
  // jquery로 화면 높이 계산하려고 했음
  mounted() {
    var windowHeight = $(window).height();
    console.log(windowHeight);
    // var topHeight = $("#top").height();
    // $("#content").css({ height: windowHeight - topHeight + "px" });
    },
};
</script>

<style scoped>
.logo {
  width: 50%;
  margin: 0 auto;
}
.MainLogin {
  height: 100%;
  display: flex;
  flex-direction: column;
  /* background-color: aqua; */
  justify-content: center;
}
.login_form {
  margin: 0 auto;
  width: 70%;
}
.form_useremail {
  margin: 1rem 0 1rem 0;
}
.form_password {
  margin: 1rem 0 1rem 0;
}
.login_button {
  background-color: #1ec0ff;
  margin: 0 auto;
  width: 70%;
  height: 2.3rem;
  border-radius: 3rem;
  font-size: 1rem;
  font-weight: 500;
  color: #fff;
  border: 1px solid transparent;
}
.links {
  margin: 1rem 1rem 3rem 0;
  font-size: 0.8rem;
}
.lost_password {
  color: #1ec0ff;
  margin: 0 1rem 0 0;
}
.signup {
  color: #1ec0ff;
  margin: 0 0 0 1rem;
  text-decoration-line: none;
}
</style>