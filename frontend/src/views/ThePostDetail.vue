<template>
    <div class="main">
      <div class="toparea">
        <div class="d-flex justify-content-between h-100 align-items-center">
          <i class="fas fa-arrow-left" style="color: white;" @click="goback"></i>
        </div>
      </div>

        <div class="post">
          <div class="nickname mt-1">
            <img :src="getThumbnail(postinfo.user.userthumbnail)" alt="프로필" class="round">
            <div class="profile">
              <div class="d-flex flex-column justify-content-center">
                <div class="usernickname">{{postinfo.user.usernickname}}</div>
                <div class="created">{{postinfo.created}}</div>
              </div>
            </div>
          </div>

          <div class="title">
            {{postinfo.title}}
          </div>
          <!--<img :src="getPhotoThumbnail(post.photo_thumbnail)" alt="이미지" class="postimage" v-if="post.photo_thumbnail">-->
          <!--<img src="@/assets/gold.png" alt="a" class="postimage"> 로컬 테스트용-->
          <div class="content">
            {{postinfo.content}}
          </div>
        </div>
        <div class="space2"></div>


      <div class="buttonarea" v-if="postinfo.user.id == this.$store.state.credentials.userId">
        <div class="button" @click="modify">수정</div>
        <div class="button" @click="del">삭제</div>
      </div>
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
    goback() {
      this.$router.push("/club/ClubMain");
    },
    modify() {
      this.$router.push("/club/post/modify")
    },
    del() {
      var url = "/api/";
      url += this.$store.state.selectedClub;
      url += "/board/";
      url += this.$store.state.postId;
      http.delete(url, {
        withCredentials : true
      }).then((res) => {
        console.log(res.data);
        this.$router.push('/club/ClubMain');
      }).catch((error) => {
        console.log(error);
          alert("글 삭제 실패");
      })
    },
    getThumbnail(url) {
      return "/resources/" + url;
    },
  },
  created() {
    var url = "/api/";
    url += this.$store.state.selectedClub;
    url += "/board/"
    url += this.$store.state.postId;
    //url = "/api/6/board/1";
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


/*
.title {
  height: 3rem;
  text-align: left;
  width: 90%;
  margin-left: 5%;
  margin-top : 3rem;
  border-bottom: 1px solid #999999;
  margin-bottom: 2rem;
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
  text-align: left;
  width: 90%;
  margin-left: 5%;
}



.usernickname {
  display:block;
}

.created {
  color: #999999;
  font-size:0.5rem;
  display:block;
}

*/


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

.buttonarea {
  text-align: center;
}

.button {
  width: 5rem;
  background-color: #1ec0ff;
  border-radius: 0.5rem;
  height: 2rem;
  line-height: 2rem;
  color: white;
  display:inline-block;
  text-align: center;
}

</style>