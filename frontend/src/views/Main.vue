<template>
  <div class="main">
    <!-- 로그인 여부에 따라 컴포넌트 하나 추가로 쓸 예정 -->
    <MainLogin />
  </div>
</template>

<script>
// @ is an alias to /src
import MainLogin from "@/components/main/MainLogin.vue";

export default {
  name: "Main",
  components: {
    MainLogin,
  },
  data: function () {
    return {
      password: null,
      credentials: {
        userEmail: null,
        useraccount: null,
        usernickname: null,
        userthumbnail: null,
        userId: null,
      }
    }
  },
};
</script>
