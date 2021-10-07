<template>
  <div class="main">
    <!-- 로그인 여부에 따라 컴포넌트 하나 추가로 쓸 예정 -->
    <MainLogin />
  </div>
</template>

<script>
// @ is an alias to /src
import MainLogin from "@/components/main/MainLogin.vue";
import http from "@/util/http-common";

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
  method: {
    checkLogin() {
      http
        .get("api/users/islogin", { withCredentials: true })
        .then((res) => {
          this.credentials.userEmail = res.data.userEmail;
          this.credentials.useraccount = res.data.useraccount;
          this.credentials.usernickname = res.data.usernickname;
          this.credentials.userthumbnail = res.data.userthumbnail;
          this.credentials.userId = res.data.userId;
          this.$store.dispatch("logininfo", this.credentials);
          this.$store.dispatch("isLogin");
          console.log("로그인 데이터");
          this.$router.push("club/list"); 
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  created () {
    this.checkLogin();
  }
};
</script>
