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
    <div class="toparea position-absolute">
      <div class="d-flex justify-content-between h-100 align-items-center">
        <i class="fas fa-arrow-left" @click="goback"></i>
      </div>
    </div>
    <div class="row signup_header">
      <div class="col-9"><span>프로필수정</span></div>
    </div>
    <div class="row signup_form">
      <div class="col-12">
        <div class="mb-2 d-flex justify-content-between align-items-center">
          <div class="circle">
            <img :src="beforeurl" alt="" class="profile-pic" ref="image" />
          </div>
          <label class="photo-button" for="photo">업로드</label>
          <input
            v-on:change="fileSelect()"
            ref="image"
            type="file"
            name="photo"
            id="photo"
            class="col-6"
            style="display: none"
          />
        </div>
      </div>
      <input
        type="text"
        id="usernickname"
        name="usernickname"
        v-model="usernickname"
        ref="usernickname"
        placeholder="닉네임"
        class="form-control col-12"
      />
      <input
        type="password"
        id="password"
        name="password"
        v-model="password"
        ref="password"
        placeholder="비밀번호"
        class="form-control"
      />
      <input
        type="password"
        id="passwordconfirm"
        name="passwordconfirm"
        v-model="passwordconfirm"
        ref="passwordconfirm"
        placeholder="비밀번호 확인"
        class="form-control"
      />
      <div
        class="btn photo-button"
        @keyup.enter="myInfoUpdate"
        @click="myInfoUpdate"
      >
        정보 수정하기
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
import $ from "jquery";

export default {
  name: "MyInfoUpdate",
  data() {
    return {
      userinfo: null,
      beforeurl: "@/assets/profile.png",
      usernickname: "",
      useremail: "",
      image: "",
      password: "",
      passwordconfirm: "",
      selecturl: "",
    };
  },
  created: function () {
    this.checkLogin();
    $(document).ready(function () {});
  },
  methods: {
    checkLogin() {
      http
        .get("api/users/islogin", { withCredentials: true })
        .then((res) => {
          console.log(res);
          this.userinfo = res.data;
          this.usernickname = this.userinfo.usernickname;
          this.useremail = this.userinfo.useremail;
          if (
            this.userinfo.userthumbnail !== null ||
            this.userinfo.userthumbnail !== ""
          ) {
            this.beforeurl = "resources/" + this.userinfo.userthumbnail;
          }
        })
        .catch((error) => {
          console.log(error);
          this.$router.push("/");
        });
    },
    myInfoUpdate() {
      console.log("업데이트");

      if (this.password === this.passwordconfirm) {
        var putUrl = "api/users/" + this.userinfo.userId;
        const formData = new FormData();
        formData.append("file", this.image);
        formData.append("password", this.password);
        formData.append("userEmail", this.useremail);
        formData.append("usernickname", this.usernickname);
        http
          .put(putUrl, formData, { withCredentials: true })
          .then((res) => {
            console.log(res);
            this.$store.state.credentials.userEmail = res.data.userEmail;
            this.$store.state.credentials.useraccount = res.data.useraccount;
            this.$store.state.credentials.usernickname = res.data.usernickname;
            this.$store.state.credentials.userthumbnail = res.data.userthumbnail;
            this.$store.state.credentials.userId = res.data.userId;
            this.$store.dispatch("logininfo", this.credentials);
            this.$store.dispatch("isLogin");
            this.$router.push("MyPage");
          })
          .catch((error) => {
            console.log(error);
          });
      } else {
        alert("비밀번호를 확인해주세요.");
      }
    },
    fileSelect() {
      this.image = this.$refs.image.files[0];
      this.selecturl = URL.createObjectURL(this.image);
      this.beforeurl = URL.createObjectURL(this.image);
    },
    goback() {
      this.$router.push("/mypage");
    }
  },
};
</script>


<style scoped>
.file-upload {
  display: none;
}
.upload-button {
  background-color: #1ec0ff;
  margin: 0 auto 0;
  padding: 0.3rem;
  border-radius: 0.5rem;
  font-size: 1rem;
  font-weight: 500;
  color: #fff;
  border: 1px solid transparent;
  -webkit-appearance: none;
}
.circle {
  /* box-sizing: border-box; */
  box-sizing: content-box;
  position: relative;
  border-radius: 1000px !important;
  overflow: hidden;
  width: 6rem;
  height: 6rem;
  /* border: 6px solid rgba(0, 0, 0, 0.1); */
  /* background-color: red; */
  display: flex;
  align-items: center;
  justify-content: center;
  display: -webkit-flex;
  -webkit-align-item: center;
  -webkit-justify-content: center;
}
.profile-pic {
  position: absolute;
  /* transform: translate(-50%); */
  width: 6rem;
  height: 6rem;
}

.signup_header {
  width: 60%;
  font-size: 1.5rem;
  text-align: left;
}

.signup_form {
  width: 60%;
}
.photo-button {
  padding: 3px 15px;
  background-color: #1ec0ff;
  border-radius: 2px;
  color: white;
  cursor: pointer;
}
.form-control {
  margin-bottom: 8px;
}
.toparea {
  top: 0;
  height: 3rem;
  width: 100%;
}
.fas {
  font-size: 1.5rem;
  margin: 1rem;
}
</style>