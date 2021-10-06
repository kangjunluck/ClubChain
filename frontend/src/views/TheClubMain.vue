<template>
    <div class="main">
      <div class="toparea">
        <div class="d-flex justify-content-between h-100 align-items-center">
          <i class="fas fa-arrow-left" @click="goback"></i>
          <i class="fas fa-plus" @click="write()" ></i>
        </div>
      </div>

      <div class="clubname">
        clubname
      </div>
      <div class="clubmember">
        공개 그룹 - {{club.join_num}}명
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
      <div class="space"></div>
      <div class="post" v-for="post in postlist" v-bind:key="post" @click="detail(post.id)">
        <div class="nickname">
            <img :src="getThumbnail(post.userthumbnail)" alt="프로필" class="round">
            <!--<img src="@/assets/gold.png" alt="a" class="round"> -->
          <div class="profile">
            <div class="usernickname">{{post.usernickname}}</div>
            <div class="created">{{post.created}}</div>
          </div>
        </div>

        <div class="title">
          {{post.title}}
        </div>
        <img :src="getPhotoThumbnail(post.photo_thumbnail)" alt="이미지" class="postimage">
        <!--<img src="@/assets/gold.png" alt="a" class="postimage"> 로컬 테스트용-->
        <div class="content">
          {{post.content}}
        </div>
        <div class="space"></div>
      </div>
      <div class="exist" v-if="postlist.length == 0">등록된 게시물이 없습니다</div>
      <Footer :footernum="footernum" />
    </div>
</template>

<script>
import http from "@/util/http-common";
import Footer from '../components/footer/footer.vue';

export default {
  data() {
    return {
      club : null,
      schedule : "",
      postlist : "",
      join_num : "",

      footernum : 0,
    };
  },
  components: {
    Footer,
  },
  methods: {
    write() {
      this.$router.push("/club/post/write");
    },
    detail(postid){
      this.$store.dispatch("postId", postid);
      this.$router.push('/club/post/detail');
    },
    getClubinfo(){
      var url = "/api/club/{clubid}?clubid=";
      url += this.$store.state.selectedClub;
      http.
        get(url, {
          withCredentials : true
        }).then((res) => {
          console.log(res.data);
          console.log("동호회 정보");
          console.log(res.data);
          this.club = res.data;
          console.log(this.club);
        }).catch((error) => {
          console.log(error);
          alert("동호회 가져오기 실패");
      })
    },
    getBoardinfo(){
      var url = "/api/";
      url += this.$store.state.selectedClub;
      url += "/board/";
      url = "/api/30/board/";   //로컬 테스트용
      http.
        get(url, {
          withCredentials : true
        }).then((res) => {
          console.log(res.data);
          console.log("게시글 찍히나 확인");
          console.log(res.data[0]);
          this.postlist = res.data;
          console.log(this.postlist);
        }).catch((error) => {
          console.log(error);
          alert("게시글 가져오기 실패");
      })
    },
    goback () {
      this.$router.push('/club/list');
    },
    getThumbnail(url) {
      return url;
    },
    getPhotoThumbnail(url) {
      return url;
    }
    
  },
  created() {
    this.getClubinfo();
    this.getBoardinfo();
  },
};
</script>

<style scoped>
.toparea {
  top: 0;
  height: 3rem;
  width: 100%;
}
.fas {
  font-size: 1.5rem;
  margin: 1rem;
}
.plusbtn {
  width:1rem;
  height: 1rem;
  float:right;
}

.main {
  width: 100%;
  text-align: left;
}
.clubname {
  font-weight: 600;
  font-size: 2rem;
  width:100%;
  height: 3rem;
  text-align: center;
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


.title {
  margin-top: 2rem;
  margin-left: 5%;
  font-weight: 600;
}

.content{
  height: 6rem;
  margin-left: 5%;
}
.comment{
  height: 3rem;
}
.space {
  height: 1rem;
  background-color: #999999;
}
.round-box {
  width: 3.5rem;
  height: 3.5rem;
  border-radius: 70%;
  overflow: hidden;
  display:inline-block;
}

.round {
  width: 3.5rem;
  height: 3.5rem;
  border-radius: 70%;
  object-fit:cover;
  display:inline-block;
}

.profile {
  display:inline-block;
  margin-left: 2rem;
  height: 100%;
}

.postimage {
  width: 100%;
}

.usernickname {
  display:block;
}

.created {
  color: #999999;
  font-size:0.5rem;
  display:block;
}

</style>