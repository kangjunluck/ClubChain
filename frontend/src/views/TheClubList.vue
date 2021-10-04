<template>
    <div class="main">
        <div class="profilearea">
          <img alt="Vue logo" src="@/assets/CC_logo_symbol.svg" class="logo" />
          <img alt="profile" src="@/assets/profile.png" class="profile"/>
        </div>
        <div class="searcharea">
            <input 
                type="text"
                id="search"
                name="search"
                v-model="search"
                ref="search"
                placeholder="검색어를 입력해주세요"
                class="form-control mt-5 mb-2"
            />
            <img alt="searchbtn" src="@/assets/search.png" class="searchbtn"/>
        </div>
        <div class="textarea">
          <div class="clubtext">동호회 리스트</div>
          <img alt="plusbtn" src="@/assets/plusbtn.png" class="plusbtn" />
        </div>
        <div class="clubarea" >
          <div v-for="club in clublist" v-bind:key="club" class="club">
            <img src="@/assets/CC_logo_symbol.svg" alt="클럽썸네일" @click="enterClub(club.clubid)">
            <!--club 안에 썸네일 주소를 통해 이미지 불러와야함-->
            <div @click="enterClub(club.id)">
            {{club.name}}
            </div>
          </div>
        </div>
        <div class="notexist" v-if="!clubexist">
            동호회가 존재하지 않습니다.
        </div>
    </div>
</template>

<script>
import http from "@/util/http-common";

export default {
  data() {
    return {
      clublist : null,
      clubexist : false,
    };
  },
  methods: {
    enterClub(clubid) {
      this.$store.dispatch("selectedClub", clubid);
      console.log(this.$store.state.selectedClub);
      this.$router.push({name : "EnterClub"});
    },
  },
  created() {
    http.
      get("/api/club/", {
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
.mainLogin {
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.profilearea{
  height: 10%;
}

.logo, .profile{
    width: 10%;
    height: 50%;
}
.profile{
    float:right;
    margin-right: 5%;
    margin-top: 5%;
}
.logo{
    float:left;
    margin-left: 5%;
    margin-top: 5%;
}
#search{
  width: 90%;
  display:inline-block;
}
.searcharea{
  
}
.searchbtn{
  width: 10%;
}

.textarea{
  height: 5%;
  width: 100%;
  display: flex;
}
.clubtext{
  font-size: 1.5rem;
}
.plusbtn{
  width: 5%;
  height: 50%;
}

.club {
  width: 40%;
  height: 20%;
  float:left;
  margin-left: 5%;
}
</style>