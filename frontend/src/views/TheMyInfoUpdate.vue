<template>
  <div>
    <div>프로필 수정</div>

    <b-container>
      <b-row>
        <b-col offset="1">
          <div class="circle">
            <img :src="selecturl" alt="" class="profile-pic"  ref="image"/>
          </div>
        </b-col>
        <b-col cols="4">
          <label for="photo" class="upload-button">파일 선택</label>
          <input
            type="file"
            accept="image/*"
            id="photo"
            class="file-upload"
            ref="image"
            v-on:change="fileSelect()"
          />
        </b-col>
      </b-row>
    </b-container>
    <b-form-input
      placeholder="닉네임"
      v-model="usernickname"
      id="usernickname"
      class="usernickname"
      onclick="this.value=''; return true"
    ></b-form-input>
    <b-form-input
      placeholder="이메일"
      v-model="useremail"
      id="useremail"
      class="useremail"
      onclick="this.value=''; return true"
    ></b-form-input>
    <b-form-input
      type="password"
      placeholder="비밀번호"
      v-model="password"
      id="password"
      class="password"
      autocomplete="new-password" 
    ></b-form-input>
    <b-form-input
      type="password"
      placeholder="비밀번호 확인"
      v-model="passwordconfirm"
      id="passwordconfirm"
      class="passwordconfirm"
      autocomplete="new-password" 
      
    ></b-form-input>
    <button @click="myInfoUpdate">수정하기</button>
  </div>
</template>

<script>
import http from "@/util/http-common";
import $ from "jquery";

export default {
  name: "MyInfoUpdate",
  data() {
    return {
      startuseremail: this.$store.state.credentials.userEmail,
      usernickname: this.$store.state.credentials.usernickname,
      useremail: this.$store.state.credentials.userEmail,
      // usernickname: "",
      // useremail: "",
      image: "",
      password:"",
      passwordconfirm:"",
      selecturl: require("@/assets/profile.png"),
    };
  },
  created: function () {
    $(document).ready(function () {});
  },
  methods: {
    myInfoUpdate() {
      console.log("업데이트");

      if (this.password === this.passwordconfirm) {
        var userId = this.$store.state.credentials.userId;
        var putUrl = "api/users/" + userId;
        const formData = new FormData();
        formData.append("file", this.image);
        formData.append("password", this.password);
        formData.append("userEmail", this.useremail);
        formData.append("usernickname", this.usernickname);
        formData.append("userId", userId);
        formData.append(
          "useraccount",
          this.$store.state.credentials.useraccount
        );
        http
          .put(putUrl, formData,  { withCredentials: true })
          .then((res) => {
            console.log(res);
          })
          .catch((error) => {
            console.log(error);
          });
      } else {
        alert("비밀번호를 확인해주세요.")
      }
    },
    fileSelect() {
      this.image = this.$refs.image.files[0];
      if (this.image == undefined) {
        this.selecturl = require("@/assets/profile.png");
      }
      this.selecturl = URL.createObjectURL(this.image);
    },
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
  border: 6px solid rgba(0, 0, 0, 0.1);
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
</style>