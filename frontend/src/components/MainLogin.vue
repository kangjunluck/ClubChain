<template>
  <div class="MainLogin">
    <!-- 나중에 삭제 -->
    <div>main-login 화면입니다.</div>
    <!-- 로고와 서비스이름 파일 들어갈 예정 -->
    <img alt="Vue logo" src="@/assets/logo.png" />
    <!-- input 태그를 이용해 로그인 폼 만들기 -->
    <div>
      <input
        type="text"
        id="useremail"
        v-model="credentials.useremail"
        placeholder="이메일"
      />
    </div>
    <div>
      <input
        type="password"
        v-model="credentials.password"
        placeholder="비밀번호"
      />
    </div>
    <!-- 클릭할 때 함수 실행 -->
    <button @click="login(credentials)">로그인</button>
  </div>
</template>

<script>
import { mapActions } from "vuex";
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
  },
};
</script>

<style>
</style>