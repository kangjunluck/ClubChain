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
      <div class="d-flex justify-content-between h-100 align-items-center">
        <i class="fas fa-arrow-left" @click="goback"></i>
      </div>
    </div>
    <div class="row w-50">
      <div class="d-flex justify-content-between align-items-center mb-3 p-0">
        <img
          :src="selecturl"
          alt=""
          class="px-0"
          style="width: 5rem; height: 5rem"
        />
        <div class="w-50">
          <p class="m-0 h5">{{ this.userinfo.userEmail }}</p>
          <p class="m-0 h5">{{ this.userinfo.usernickname }}</p>
        </div>
      </div>
      <button @click="profileUpdate" class="btn update-button">
        프로필 설정
      </button>
    </div>
    <hr size="1px" width="60%" />
      <div @click="showmyclub" style="height:30px; width:100%; text-align:left; border-top:1px solid; border-bottom:1px solid">
        가입한 동호회
      </div>
      <div v-if="showclub">
        <div v-for="club in myclubinfo" :key="club.pk">
          <p class="mb-1">{{ club.introduce }}</p>
        </div>
      </div>
      <div @click="createInvitation" style="height:30px; width:100%; text-align:left;border-bottom:1px solid">
        초대장 생성
      </div>
      <div @click="goSetting" style="height:30px; width:100%; text-align:left;border-bottom:1px solid">
        동호회 설정
      </div>
      <div v-if="checkuser" @click="userDelete" style="height:30px; color:red; width:100%; text-align:left; border-bottom:1px black solid">
        동호회 탈퇴
      </div>
      <div v-else @click="clubDismantle" style="height:30px; color:red; width:100%; text-align:left; border-bottom:1px black solid">
        동호회 해체
      </div>
    <hr />
  </div>
</template>

<script>
import http from "@/util/http-common";
export default {
  name: "myInfoPage",
  data: function () {
    return {
      userinfo: null,
      myclubinfo: null,
      showclub : false,
      checkuser : true,
      selecturl: "@/assets/profile.png",
    };
  },
  methods: {
    // 모든 vue에서 실행될 checklogin 함수 #######
    checkLogin() {
      http
        .get("api/users/islogin", { withCredentials: true })
        .then((res) => {
          this.userinfo = res.data;
          this.selecturl = "resources/" + res.data.userthumbnail;
          this.myClubInfo();
        })
        .catch((error) => {
          console.log(error);
          this.$router.push("/");
        });
    },
    profileUpdate() {
      console.log("프로필 업뎃");
      this.$router.push("myinfoupdate");
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
      var userId = this.$store.state.credentials.userId;
      var formdata = new FormData();
      formdata.append("userId", userId);
      var url = "api/users/" + userId;
      http
        .delete(url, formdata, { withCredentials: true })
        .then((res) => {
          console.log("탈퇴 응답");
          console.log(res);
          if (res.status === 204) {
            alert("회원 탈퇴");
            this.$router.push("/");
          }
        })
        .catch((error) => {
          console.log("에러!");
          console.log(error);
        });
    },
    goback() {
      this.$router.push("club/list");
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
    createInvitation()
    {

    },
    goSetting()
    {
      let url = "/api/club/{clubid}?clubid=" + this.$store.state.selectedClub;
      http.get(url)
      .then((res)=>{
        if(res.data.owner_name == this.userinfo.usernickname)
        {
          console.log('동호회 설정창으로 이동');
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
  border: 8px solid #efefef;
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