<template>
  <div
    class="
      container
      d-flex
      flex-column
      justify-content-center
      align-items-center
    "
  >
    <div class="toparea position-absolute">
      <!-- <div class="d-flex justify-content-between h-100 align-items-center">
        <i class="fas fa-arrow-left" @click="goback"></i>
      </div> -->
    </div>
    <!-- <div class="row" style=""> -->
      <div class=" d-flex justify-content-between align-items-center mb-3 p-0" style="width:85%">
        <img
          :src="selecturl"
          alt=""
          class="circle px-0"
          style="width: 5rem; height: 5rem"
        />
        <div class="">
          <p class="m-0 h5" style="text-align:left;">{{ this.userinfo.usernickname }}</p>
          <p class="m-0 h6" style="text-align:left;">{{ this.userinfo.userEmail }}</p>
        </div>
      </div>
      <!-- <button @click="profileUpdate" class="btn update-button">
        프로필 설정
      </button> -->
    <!-- </div> -->
    <hr size="1px" width="60%" />
        <div style="width:85%;">
      <div @click="showmyclub" style="line-height: 1.9; height:30px; width:100%; text-align:left; border-top:1px solid; border-bottom:1px solid">
        가입한 동호회
      </div>
      <div v-if="showclub" style="overflow: auto; height: 100px;">
        <div v-for="club in myclubinfo" :key="club.pk" style="height:30px;">
          <p style="text-align: left;">
            <i class="fas fa-chevron-right" style="font-size: 1rem;"></i>{{ club.name }}
          </p>
        </div>
      </div>
      <div @click="showBoard" style="line-height: 1.9; height:30px; width:100%; text-align:left;border-bottom:1px solid">
        내가 쓴 글
      </div>
      <div v-if="showboard" style="overflow: auto; height: 100px;">
        <div v-if="boards.length==0">
          게시글이 없습니다.
        </div>
        <div v-for="board in boards" :key="board.id" style="height:30px;">
          <p style="text-align: left;">
            <i class="fas fa-chevron-right" style="font-size: 1rem;"></i>{{ board.title }}
          </p>
        </div>
      </div>

      <div @click="goSetting" style="line-height: 1.9; height:30px; width:100%; text-align:left;border-bottom:1px solid">
        동호회 설정
      </div>
      <div v-if="checkuser" @click="userDelete" style="line-height: 1.9; height:30px; color:red; width:100%; text-align:left; border-bottom:1px black solid">
        동호회 탈퇴
      </div>
      <div v-else @click="clubDismantle" style="line-height: 1.9; height:30px; color:red; width:100%; text-align:left; border-bottom:1px black solid">
        동호회 해체
      </div>
      </div>
    <hr />
    <Footer :footernum="footernum" />
  </div>
</template>

<script>
import http from "@/util/http-common";
import Footer from '../components/footer/footer.vue';

export default {
  name: "ClubMypage",
  components: {
    Footer,
  },
  data: function () {
    return {
      userinfo: "",
      myclubinfo: "",
      showclub : false,
      showboard: false,
      checkuser : true,
      selecturl: "@/assets/profile.png",
      boards:[],

      footernum : 4,
    };
  },
  methods: {
    // 모든 vue에서 실행될 checklogin 함수 #######
    checkLogin() {
      http
        .get("api/users/islogin", { withCredentials: true })
        .then((res) => {
          this.userinfo = res.data;
          this.selecturl = "/resources/" + res.data.userthumbnail;
          console.log(this.selecturl)
          this.myClubInfo();
        })
        .catch((error) => {
          console.log(error);
          this.$router.push("/");
        });
    },
    profileUpdate() {
      console.log("프로필 업뎃");
      this.$router.push("/myinfoupdate");
    },
    myClubInfo() {
      console.log("가입한 동호회");
      http
        .get("api/club/myclub", { withCredentials: true })
        .then((res) => {
          console.log(res.data);
          this.myclubinfo = res.data;
        })
        .catch((error) => {
          console.log("에러");
          console.log(error);
        });
    },
    userDelete() {
      console.log("탈퇴하기");
      var url = "api/"
    url += this.$store.state.selectedClub+"/";
      http.delete(url)
      .then(()=>{
          alert("동호회에 탈퇴하셨습니다.")
          this.$router.push("/club/list");
      })
      .catch((error)=>{
          console.log(error)
      })
    },
    goback() {
      this.$router.push("ClubMain");
    },
    showmyclub()
    {
      if(this.showclub === false)
      {
        this.showclub = true;
      }
      else
        this.showclub = false;
    },
    showBoard()
    {
        if(!this.showboard)
        {
            this.showboard = true;
            this.myWrite();
        }
        else
        {
            this.showboard = false;
            this.boards = [];
        }
    },
    myWrite()
    {
        let url = "/api/"+ this.$store.state.selectedClub+"/board/";
        http.get(url)
        .then((res)=>{
            console.log(res);
            res.data.forEach(item => {
                if(item.userEmail == this.$store.state.credentials.userEmail)
                {
                    this.boards.push(item);
                }
            });
        })
    },
    goSetting()
    {
      let url = "/api/club/{clubid}?clubid=" + this.$store.state.selectedClub;
      http.get(url)
      .then((res)=>{
        if(res.data.owner_name == this.userinfo.usernickname)
        {
          // console.log('동호회 설정창으로 이동');
          this.$router.push("/club/update");
        }
        else{
          alert('동호회장만 설정할 수 있습니다.');
        }
      })

    },
    checkUser()
    {
      let url = "/api/club/{clubid}?clubid=" + this.$store.state.selectedClub;
      http.get(url)
      .then((res)=>{
        if(res.data.owner_name == this.userinfo.usernickname)
        {
          this.checkuser = false;
        }
        else{
          this.checkuser = true;
        }
      })
    },
    clubDismantle()
    {
      console.log('동호회 해체');
    },
  },
  created() {
    this.checkLogin();
    this.checkUser();
  },
}
</script>

<style scoped>
.circle {
  border-radius: 1000px;
  overflow: hidden;
  max-width: 7rem;
  height: 7rem;
  /* border: 8px solid #efefef; */
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
.update-button {
  margin-left: auto;
  padding: 3px 15px;
  background-color: #1ec0ff;
  border-radius: 2px;
  color: white;
  cursor: pointer;
}
.delete-button {
  margin-left: auto;
  padding: 3px 15px;
  background-color: #ffc5c5;
  border-radius: 2px;
  color: rgb(255, 10, 10);
  cursor: pointer;
}
.toparea {
  top: 0;
  height: 3rem;
  width: 100%;
}
.fas {
  font-size: 1.5rem;
  margin: 1rem;
}
</style>