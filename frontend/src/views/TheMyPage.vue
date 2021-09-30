<template>
  <div>
    <b-container>
      <b-row>
        <b-col offset="1" cols="4" class="circle">
          <img src="" alt="" />
        </b-col>
        <b-col cols="6">
          <div class="profile_info">
            <span class="nickname">
              {{ this.$store.state.credentials.usernickname }}
            </span>
            <br />
            <span class="email">{{
              this.$store.state.credentials.userEmail
            }}</span>
          </div>
        </b-col>
      </b-row>
    </b-container>
    
    <button @click="profileUpdate">프로필 설정</button>
    <hr />
    <div @click="myClubInfo">가입한 동호회</div>
    <hr />
    <div  @click="myPost">내가 쓴 글</div>
    <hr />
    <div @click="userDelete">회원 탈퇴</div>
  </div>
</template>

<script>
import http from "@/util/http-common";
export default {
  name: "myInfoPage",
  methods: {
    profileUpdate() {
      console.log('프로필 업뎃')
      this.$router.push('myinfoupdate')
    },
    myClubInfo() {
      console.log('가입한 동호회')
  
      http
        .post("api/club/myclub",{ withCredentials: true })
        .then((res) => {
          console.log(res)
        })
        .catch((error)=>{
          console.log('에러')
          console.log(error)
        })
    },
    myPost() {
      console.log('내 포스팅')
    },
    userDelete() {
      console.log('탈퇴하기')
      http
        .post("api/users/{userID}")
        .then((res)=>{
          console.log('탈퇴 응답')
          console.log(res)
        })
        .catch((error)=>{
          console.log("에러!")
          console.log(error)
        })
    }
  }
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
</style>