<template>
    <div class="main">
      <div class="toparea">
        <img src="@/assets/plusbtn.png" alt="돌아가기" class="backbtn" @click="back()">
      </div>
      <div class="title">
        {{postinfo.title}}
      </div>
      <div class="writer">
        <img src="@/assets/profile.png" class="profile">
        {{postinfo.user.usernickname}}
      </div>
      <div class="content">
        {{postinfo.content}}
      </div>
      <div class="comment" v-for="comment in commentlist" v-bind:key="comment">
        <img src="@/assets/profile.png" class="profile">
        <div>작성자</div>
        {{commentlist.content}}
      </div>
      <div class="exist" v-if="commentlist.length == 0">등록된 댓글이 없습니다</div>
    </div>
</template>

<script>
import http from "@/util/http-common";

export default {
  data() {
    return {
      postinfo: "",
      commentlist: "",
    };
  },
  methods: {
    
  },
  created() {
    var url = "/api/";
    url += this.$store.state.selectedClub;
    url += "/board/"
    url += this.$store.state.postId;
    url = "/api/6/board/1";
    http.
      get(url, {
        withCredentials : true
      })
      .then((res) => {
        console.log(res.data);
        this.postinfo = res.data;
      }).catch((error) => {
        console.log(error);
        alert("가져오기 실패");
      });

    url = "/api/";
    url += this.$store.state.selectedClub;
    url += "/";
    url += this.$store.state.postId;
    url += "/comment/";
    http.
      get(url, {
        withCredentials : true
      })
      .then((res) => {
        console.log(res.data);
        this.commentlist = res.data;
      }).catch((error) => {
        console.log(error);
        alert("가져오기 실패");
      });


  },
};
</script>

<style scoped>
.main {
  height: 100%;
  display: flex;
  flex-direction: column;
}
.toparea {
  height: 3rem;
  background-color: pink;
}
.backbtn {
  width: 2rem;
  height: 2rem;
  float:left;
}

.title {
  height: 3rem;
  text-align: left;
  width: 90%;
  margin-left: 5%;
  margin-top : 3rem;
}

.writer {
  text-align: left;
  width: 90%;
  margin-left: 5%;
}

.profile {
  width: 1.5rem;
  height: 1.5rem;
}

.content {
  height: 15rem;
  background-color: plum;
  text-align: left;
  width: 90%;
  margin-left: 5%;
}

.comment {
  width: 90%;
  height: 1.5rem;
  width: 90%;
  margin-left: 5%;
  text-align: left;
}



</style>