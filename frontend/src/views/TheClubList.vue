<template>
  <div class="main">
    <b-container>
      <!-- 상단 로고와 프로필 이미지 -->
      <b-row class="mt-3 mb-2">
        <b-col cols="2" align-self="start">
          <img alt="Vue" class="logo" src="@/assets/CC_logo_symbol.svg" />
        </b-col>
        <b-col v-if="userinfo" class="text-style"
          >{{ userinfo.usernickname }}님</b-col
        >
        <b-col cols="2" align-self="end" class="padding-style">
          <div class="round-box" @click="profile">
            <img alt="profile" :src="selecturl" class="round" />
          </div>
        </b-col>
      </b-row>

      <!-- 검색창 -->
      <b-row class="justify-content-center">
        <b-col class=" box">
          <b-form-input
            placeholder="동호회 이름을 입력해주세요"
            v-model="word"
            v-on:keyup.enter="searchClub"
          ></b-form-input>
          <i class="fas fa-search icon-box"></i>
        </b-col>
      </b-row>

      <!-- 가입한 동호회 리스트 제목 -->
      <b-row class="mt-3 padding-style-text" align-h="start">
        <b-col
          class="font-style padding-right-style"
          cols="5"
          style="width: 130px"
        >
          나의 동호회
        </b-col>
        <b-col cols="1" class="padding-style" @click="createClub"
          ><i class="fas fa-plus-circle"></i
        ></b-col>
      </b-row>

      <!-- 가입한 동호회 리스트 -->
      <b-row class="padding-style-all">
        <b-col
          class="mb-1"
          v-for="club in clublist"
          v-bind:key="club"
          cols="6"
          style="overflow: hidden"
        >
          <img
            :src="'/resources/' + club.profile_thumbnail"
            class="img-style"
            alt="클럽썸네일"
            @click="goClub(club.clubid)"
          />
          <b-col @click="goClub(club.clubid)" class="club-name-style">{{
            club.name
          }}</b-col>
        </b-col>
      </b-row>
      <b-row class="notexist" v-if="!clubexist">
        <b-col>가입한 동호회가 없습니다.</b-col>
      </b-row>

      <!-- 전체 동호회 리스트 제목 -->
      <b-row class="mt-3 padding-style-text">
        <b-col class="font-style"> 전체 동호회 </b-col>
      </b-row>

      <!-- 전체 동호회 리스트 -->
      <b-row class="padding-style-all">
        <b-col
          class="mb-1"
          v-for="club in totalclublist"
          v-bind:key="club"
          cols="6"
          style="overflow: hidden"
        >
          <img
            :src="'/' + club.profile_thumbnail"
            class="img-style"
            alt="클럽썸네일"
            @click="enterClub(club.clubid)"
          />
          <b-col @click="enterClub(club.clubid)" class="club-name-style">{{
            club.name
          }}</b-col>
        </b-col>
      </b-row>
      <b-row class="notexist" v-if="!totalclubexist">
        <b-col>동호회가 존재하지 않습니다.</b-col>
      </b-row>
    </b-container>
  </div>
</template> 

<script>
import http from "@/util/http-common";

export default {
  data() {
    return {
      clublist: null,
      totalclublist: null,
      clubexist: false,
      totalclubexist: false,
      userinfo: null,
      selecturl: "@/assets/profile.png",
      word: "",
    };
  },
  methods: {
    createClub() {
      this.$router.push("/create");
    },
    goClub(clubid) {
      this.$store.dispatch("selectedClub", clubid);
      this.$router.push({ name: "ClubMain" });
    },
    enterClub(clubid) {
      this.$store.dispatch("selectedClub", clubid);
      console.log(this.$store.state.selectedClub);
      this.$router.push({ name: "EnterClub" });
    },
    // 모든 vue에서 실행될 checklogin 함수 #######
    checkLogin() {
      http
        .get("api/users/islogin", { withCredentials: true })
        .then((res) => {
          this.userinfo = res.data;
          this.selecturl = "/resources/" + res.data.userthumbnail;
        })
        .catch((error) => {
          console.log(error);
          this.$router.push("/");
        });
    },
    searchClub() {
      http
        .get("api/club/search/", {
          params: {
            word: this.word,
          },
        })
        .then((res) => {
          this.totalclublist = res.data;
          if (this.totalclublist.length > 0) this.totalclubexist = true;
        })
        .catch((error) => {
          console.log(error);
          this.$router.push("/club/list");
        });
    },
    profile() {
      this.$router.push("/mypage");
    },
  },
  created() {
    this.checkLogin();
    http
      .get("/api/club/", {
        withCredentials: true,
      })
      .then((res) => {
        console.log(res.data);
        this.totalclublist = res.data;
        if (this.totalclublist.length > 0) this.totalclubexist = true;
      })
      .catch((error) => {
        console.log(error);
        alert("가져오기 실패");
      });

    http
      .get("/api/club/myclub", {
        withCredentials: true,
      })
      .then((res) => {
        console.log(res.data);
        this.clublist = res.data;
        if (this.clublist.length > 0) this.clubexist = true;
      })
      .catch((error) => {
        console.log(error);
        alert("가져오기 실패");
      });
  },
};
</script>

<style scoped>
.logo {
  width: 50px;
  height: 50px;
}

.round-box {
  width: 50px;
  height: 50px;
  border-radius: 70%;
  overflow: hidden;
}

.round {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.text-style {
  text-align: right;
  margin-top: 16px;
}

.padding-style {
  padding-left: 0;
}

.padding-right-style {
  padding-right: 0;
}

.searchbtn {
  width: 90%;
}

.font-style {
  font-weight: bold;
  font-size: 150%;
  text-align: left;
}

.img-style {
  border-radius: 0.3rem;
  width: 150px;
  height: 150px;
}

.club-name-style {
  font-weight: bold;
  font-size: 105%;
  text-align: left;
}

.notexist {
  margin-top: 5px;
}

.list-style {
  float: left;
}

.club {
  width: 40%;
  height: 20%;
  float: left;
  margin-left: 7%;
  margin-bottom: 7px;
}

.padding-style-all {
  padding: 10px;
}

.padding-style-text {
  padding-right: 10px;
  padding-left: 10px;
}

.box {
  position: relative;
}

.icon-box {
  position: absolute;
  left: 87vw;
  top: 11px;
  color: #999999;
}
</style>