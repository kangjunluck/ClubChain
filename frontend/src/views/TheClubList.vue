<template>
    <div class="main">
        <b-container class="mt-3">
          <!-- 상단 로고와 프로필 이미지 -->
          <b-row class="mb-3">
            <b-col cols="2" class="text-style">
              <img alt="Vue logo" src="@/assets/CC_logo_symbol.svg" />
            </b-col>
            <b-col class="text-style">ssafy님</b-col>
            <b-col cols="2" align-self="end" class="padding-style">
              <div class="round-box">
                <img alt="profile" src="https://picsum.photos/1024/400/?image=41" class="round"/>
              </div>
            </b-col>
          </b-row>

          <!-- 검색창 -->
          <b-row class="justify-content-center">
            <b-col cols="10" class="mt-1 padding-right-style">
              <b-form-input placeholder="동호회 이름을 입력해주세요"></b-form-input>
            </b-col>
            <b-col class="padding-style">
              <img alt="searchbtn" src="@/assets/search.png" class="searchbtn"/>
            </b-col>
          </b-row>

          <!-- 가입한 동호회 리스트 제목 -->
          <b-row class="mt-3">
            <b-col class="font-style">
              나의 동호회
            </b-col>
          </b-row>

          <!-- 가입한 동호회 리스트 -->
          <div class="clubarea mt-2" >
            <div v-for="club in clublist" v-bind:key="club" class="club">
              <img src="https://picsum.photos/250/250/?image=54" class="img-style" alt="클럽썸네일" @click="enterClub(club.clubid)">
              <!--club 안에 썸네일 주소를 통해 이미지 불러와야함-->
              <div @click="enterClub(club.id)" class="club-name-style">
              {{club.name}}
              </div>
            </div>
          </div>
          <div class="notexist" v-if="!clubexist">
              가입한 동호회가 없습니다.
          </div>

          <!-- 전체 동호회 리스트 제목 -->
          <b-row class="mt-3">
            <b-col class="font-style">
              전체 동호회
            </b-col>
          </b-row>

          <!-- 전체 동호회 리스트 -->
          <div class="clubarea mt-2" >
            <div v-for="club in totalclublist" v-bind:key="club" class="club">
              <img src="https://picsum.photos/250/250/?image=54" class="img-style" alt="클럽썸네일" @click="enterClub(club.clubid)">
              <!--club 안에 썸네일 주소를 통해 이미지 불러와야함-->
              <div @click="enterClub(club.id)" class="club-name-style">
              {{club.name}}
              </div>
            </div>
          </div>
          <div class="notexist" v-if="!totalclubexist">
              동호회가 존재하지 않습니다.
          </div>

        </b-container>
    </div>
</template>

<script>
import http from "@/util/http-common";
import {mapGetters} from 'vuex'; 

export default {
  data() {
    return {
      clublist: null,
      totalclublist : null,
      clubexist: false,
      totalclubexist : false,
    };
  },
  methods: {
    enterClub(clubid) {
      this.$store.dispatch("selectedClub", clubid);
      console.log(this.$store.state.selectedClub);
      this.$router.push({name : "EnterClub"});
    },
  },
  computed: {
    ...mapGetters(['logininfo'])
  },
  created() {
    http.
      get("/api/club/", {
        withCredentials : true
      })
      .then((res) => {
        console.log(res.data);
        this.totalclublist = res.data;
        if(this.totalclublist.length > 0) this.totalclubexist = true;
      }).catch((error) => {
        console.log(error);
        alert("가져오기 실패");
      });

  http.
      get("/api/club/myclub", {
        withCredentials : true
      })
      .then((res) => {
        console.log(res.data);
        this.clublist = res.data;
        if(this.clublist.length > 0) this.clubexist = true;
      }).catch((error) => {
        console.log(error);
        alert("가져오기 실패");
      });    
  },
};
</script>

<style scoped>
.round-box {
  width: 50px;
  height: 50px;
  border-radius: 70%;
  overflow: hidden;
}

.round {
  width: 100%;
  height: 100%;
  object-fit:cover;
}

.text-style {
  text-align: right;
  margin-top: 15px;
}

.padding-style {
  padding-left:0;
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
  width: 100%;
}

.club-name-style{
  font-weight: bold;
  font-size: 100%;
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
  float:left;
  margin-left: 7%;
  margin-bottom: 7px;
}
</style>