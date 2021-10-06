<template>
  <div>
    <div>test 입니다</div>
    <!-- <button @click="myaccount"></button>
    <div>{{ this.$store.state.isLogin }}</div>
    <div>{{ this.$store.state.credentials.userEmail }}</div>
    <div>{{ this.$store.state.credentials.useraccount }}</div>
    <div>{{ this.$store.state.credentials.usernickname }}</div>
    <div>{{ this.$store.state.credentials.userthumbnail }}</div>
    <div>{{ this.$store.state.credentials.userId }}</div> -->

    <!-- <div>11</div>
    <img src="/image/user/default.png" alt="" />
    <div>12</div>
    <img src="/user/default.png" alt="" />
    <div>13</div>
    <img src="/image/user/testing@testing.com_FAgd1lMVcAQNVoc.jpg" alt="" />
    <div>14</div>
    <img
      src="/resources/user/testing@testing.com_FAgd1lMVcAQNVoc.jpg"
      alt=""
    />"
    <div style="background-color: red">1</div>
    <div>club 테스트</div><img src="/resources/image/club/default.png" alt="클럽썸네일" class="img-style">
    <div>club 테스트2</div><img src="resources/image/club/default.png" alt="클럽썸네일" class="img-style"> -->
    <b-row>
      <b-col class="EqHeightDiv" v-for="photo in photos" v-bind:key="photo" cols="4"
      >
      <div>{{photo}}</div>
        <img class="testimage" :src="url" alt="이미지">
      </b-col>
    </b-row>

  </div>
</template>

<script>
import $ from "jquery";

export default {
  data(){
    return{
      photos: [1, 2, 3, 4, 5],
      // photos: [1, 2, 3, 4]
      url: require('@/assets/profile.png')
    }
  },
  mounted(){
    var a = $(".EqHeightDiv").width()
    $(".EqHeightDiv").each(function(){
    console.log('높이', a)
    $(this).height('1000px')
    
    $(".testimage").height('200px')
  })}
};
</script>

<style scoped>
body {
  background-color: #efefef;
}

.profile-pic {
  max-width: 200px;
  max-height: 200px;
  display: block;
}

.file-upload {
  /* display: none; */
}
.EqHeightDiv{
  background-color: red;
}
.circle {
  border-radius: 1000px !important;
  overflow: hidden;
  width: 128px;
  height: 128px;
  border: 8px solid rgba(255, 255, 255, 0.7);
  position: absolute;
  top: 72px;
}
img {
  max-width: 100%;
  height: auto;
}
.upload-button {
  font-size: 1.2em;
}

.upload-button:hover {
  transition: all 0.3s cubic-bezier(0.175, 0.885, 0.32, 1.275);
  color: #999;
}
</style>