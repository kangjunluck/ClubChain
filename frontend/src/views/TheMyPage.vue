<template>
  <div
    class="
      container
      d-flex
      flex-column
      justify-content-center
      align-items-center
    "
  >
    <div class="row w-50">
      <div class="h2 p-0 mb-3" style="text-align: left">MyPage</div>
    </div>
    <div class="row w-50">
      <div class="d-flex justify-content-between align-items-center mb-3 p-0">
        <img
          :src="selecturl"
          alt=""
          class="px-0"
          style="width: 3rem; height: 3rem"
        />
        <div class="w-50">
          <p class="m-0 h5">{{ this.userinfo.userEmail }}</p>
          <p class="m-0 h5">{{ this.userinfo.usernickname }}</p>
        </div>
      </div>
      <button @click="profileUpdate" class="btn update-button w-50">
        설정
      </button>
    </div>
    <hr size="1px" width="60%" />
    <div class="row w-50">
      <div class="p-0 mb-1" style="text-align: left" @click="myClubInfo">
        가입한 동호회
      </div>
      <div v-for="club in myclubinfo" :key="club.pk">
        <p class="mb-1">{{ club.introduce }}</p>
      </div>
    </div>
    <hr />
    <div class="row w-50">
      <button @click="userDelete" class="btn delete-button w-50">탈퇴</button>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
export default {
  name: "myInfoPage",
  data: function () {
    return {
      userinfo: null,
      myclubinfo: null,

      selecturl: "@/asserts/profile.png",
    };
  },
  methods: {
    // 모든 vue에서 실행될 checklogin 함수 #######
    checkLogin() {
      http
        .get("api/users/islogin", { withCredentials: true })
        .then((res) => {
          this.userinfo = res.data;
          console.log(this.userinfo);
          this.myClubInfo();
        })
        .catch((error) => {
          console.log(error);
          this.$router.push("/");
        });
    },
    profileUpdate() {
      console.log("프로필 업뎃");
      this.$router.push("myinfoupdate");
    },
    myClubInfo() {
      console.log("가입한 동호회");
      http
        .get("api/club/myclub", { withCredentials: true })
        .then((res) => {
          console.log(res.data);
          this.myclubinfo = res.data;
        })
        .catch((error) => {
          console.log("에러");
          console.log(error);
        });
    },
    userDelete() {
      console.log("탈퇴하기");
      var userId = this.$store.state.credentials.userId;
      var formdata = new FormData();
      formdata.append("userId", userId);
      var url = "api/users/" + userId;
      http
        .delete(url, formdata, { withCredentials: true })
        .then((res) => {
          console.log("탈퇴 응답");
          console.log(res);
          if (res.status === 204) {
            alert("회원 탈퇴");
            this.$router.push("/");
          }
        })
        .catch((error) => {
          console.log("에러!");
          console.log(error);
        });
    },
  },
  created() {
    this.checkLogin();
  },
};
</script>

<style scoped>
.circle {
  border-radius: 1000px;
  overflow: hidden;
  max-width: 7rem;
  height: 7rem;
  border: 8px solid #efefef;
  /* position: absolute;
  top: 72px; */
}
.profile_info {
  text-align: left;
}
.nickname {
  font-size: 3rem;
  margin: 0 0 0 0;
  padding: 0 0 0 0;
}
.email {
  font-size: 1.5rem;
  color: red;
  margin: 0 0 0 0;
  padding: 0 0 0 0;
}
.update-button {
  margin-left: auto;
  padding: 3px 15px;
  background-color: #1ec0ff;
  border-radius: 2px;
  color: white;
  cursor: pointer;
}
.delete-button {
  margin-left: auto;
  padding: 3px 15px;
  background-color: #ffc5c5;
  border-radius: 2px;
  color: rgb(255, 10, 10);
  cursor: pointer;
}
</style>