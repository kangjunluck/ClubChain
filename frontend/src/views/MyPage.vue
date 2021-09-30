<template>
  <div>
    <div>MyPage</div>
    <!-- <div>{{ this.$store.state.credentials.userEmail }}</div>
    <div>{{ this.$store.state.credentials.useraccount }}</div>
    <div>{{ this.$store.state.credentials.usernickname }}</div>
    <div>{{ this.$store.state.credentials.userthumbnail }}</div>
     -->
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

    <div></div>
    <button>프로필 설정</button>
    <hr />
    <div>가입한 동호회</div>
    <hr />
    <div>내가 쓴 글</div>
    <hr />
    <div>회원 탈퇴</div>
  </div>
</template>

<script>
export default {
  name: "MyPage",
  // data: {},
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