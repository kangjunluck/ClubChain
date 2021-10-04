<template>
    <div class="main">
      <div class="toparea">
        상단바 영역
      </div>
      <img src="@/assets/profile.png" alt="" class="bgphoto">
      <div class="clubmember">
        공개 그룹 - {{5}}명
      </div>
      <div class="notice">
        중요 공지
      </div>
      <div class="schedule">
      가까운 일정(7일 이내)
        <div>
        {{schedule.title}}
        </div>
      </div>
      <div class="post" v-for="post in postlist" v-bind:key="post">
        <div class="nickname" @click="detail(post.id)">
          {{post.user.id}}
        </div>
        <div class="content">
          {{post.content}}
        </div>
      </div>
    </div>
</template>

<script>
import http from "@/util/http-common";

export default {
  data() {
    return {
      schedule : "",
      postlist : "",
      join_num : "",
    };
  },
  methods: {
    write(postid) {
      this.$store.dispatch("postId", postid);
      this.$router.push("club/finance");
    }
  },
  created() {
    var url = "/api/";
    url += this.$store.state.selectedClub;
    url += "/schedule/";
    http.
      get(url, {
        withCredentials : true
      }).then((res) => {
        console.log(res.data[0]);
        this.schedule = res.data[0];
      }).catch((error) => {
        console.log(error);
        alert("일정 가져오기 실패");
      })

    url = "/api/";
    url += this.$store.state.selectedClub;
    url += "/board/";
    http.
      get(url, {
        withCredentials : true
      }).then((res) => {
        console.log(res.data);
        this.post = res.data;
      }).catch((error) => {
        console.log(error);
        alert("게시글 가져오기 실패");
      })

  },
};
</script>

<style scoped>
.main {
  width: 100%;
  text-align: left;
}
.toparea {
  background-color: pink;
  height: 3rem;
  
}
.bgphoto {
  width:100%;
  height: 3rem;
}

.clubmember{
  height: 3rem;
  border-bottom : 1px solid black;
}
.notice{
  height: 3rem;
  border-bottom : 1px solid black;
}
.schedule{
  height: 3rem;
  border-bottom : 1px solid black;
}
.post{
  height: 12rem;
  background-color: sandybrown;
}
.nickname{
  height: 3rem;
}
.content{
  height: 6rem;
}
.comment{
  height: 3rem;
}
</style>