<template>
    <div class="main">
      <div class="toparea">
        <div class="d-flex justify-content-between h-100 align-items-center">
          <i class="fas fa-arrow-left" style="color: white;" @click="goback"></i>
        </div>
      </div>
      <div class="clubname d-flex flex-column justify-content-center" :style="{ backgroundImage : `url(${club.photo_thumbnail})`}">
        <!-- <img src="@/assets/profile.png" style="width:100%; height:1.5rem;" alt=""> -->
        <div>{{club.name}}</div>
      </div>
      <div class="clubmember d-flex flex-column justify-content-center">
        <div style="text-align:right;">공개 그룹 - {{club.join_num}}명</div>
      </div>
      <div class="space"></div>
      <div class="post" v-for="post in postlist" v-bind:key="post" @click="detail(post.id)">
        <div class="nickname mt-1">
          <img :src="getThumbnail(post.userthumbnail)" alt="프로필" class="round">
          <div class="profile">
            <div class="d-flex flex-column justify-content-center">
              <div class="usernickname">{{post.usernickname}}</div>
              <div class="created">{{post.created}}</div>
            </div>
          </div>
        </div>

        <div class="title">
          {{post.title}}
        </div>
        <img :src="getPhotoThumbnail(post.photo_thumbnail)" alt="이미지" class="postimage" v-if="post.photo_thumbnail">
        <!--<img src="@/assets/gold.png" alt="a" class="postimage"> 로컬 테스트용-->
        <div class="content">
          {{post.content}}
        </div>
        <div class="space"></div>
      </div>
      <div class="exist text-center" v-if="postlist.length == 0">등록된 게시물이 없습니다</div>
      <Footer :footernum="footernum" />
    </div>
</template>

<script>
import http from "@/util/http-common";
import Footer from '../components/footer/footer.vue';
import $ from "jquery";
export default {
  data() {
    return {
      club : "",
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
      //url = "/api/8/board/";   //로컬 테스트용
      http.
        get(url, {
          withCredentials : true
        }).then((res) => {
          console.log(res.data);
          console.log("게시글 찍히나 확인");
          console.log(res.data[0]);
          this.postlist = res.data.reverse();
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
    },
    goTop(){
      $( 'html, body' ).animate( { scrollTop : 0 }, 400 ); 
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
  background-color: #0080FF;
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
  font-size: 1.5rem;
  width:100%;
  height: 5rem;
  text-align: center;
  background-size: 100%;
}

.clubmember{
  margin-left: 2rem;
  margin-right: 2rem;
  height: 2rem;
}
.post {
  margin-left: 2rem;
  margin-right: 2rem;
}
.notice{
  height: 3rem;
  border-bottom : 1px solid #999999;
}
.schedule{
  height: 3rem;
}


.title {
  margin-top: 1rem;
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
  border-top : 1px solid #999999;
  height: 1rem;
  background-color: #DDE0E3;
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
  margin-left: 1rem;
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
