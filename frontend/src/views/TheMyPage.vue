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
    <div></div>
    <div class="row" style="padding-left: 33px; padding-right: 33px;">
      <div class="d-flex justify-content-between align-items-center mb-4 p-0">
        <img
          :src="selecturl"
          alt=""
          class="px-0 circle"
          style="width: 5rem; height: 5rem"
        />
        <div class="w-50">
          <p class="m-0 h5" style="text-align:left;">{{ this.userinfo.usernickname }}</p>
          <p class="m-0 h6" style="text-align:left;">{{ this.userinfo.userEmail }}</p>
        </div>
      </div>
      <button @click="profileUpdate" class="btn update-button">
        프로필 설정
      </button>
    </div>
    <hr size="1px" width="60%" />
    <div style="width:85%;">
      <div @click="showmyclub" style="line-height: 1.9; height:30px; width:100%; text-align:left; border-top:1px solid; border-bottom:1px solid">
        가입한 동호회
      </div>
      <div v-if="showclub" style="overflow: auto; height: 100px;">
        <div v-if="myclubinfo.length==0">
          가입한 동호회가 없습니다.
        </div>
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
      <div @click="goSend" style="line-height: 1.9; height:30px; width:100%; text-align:left;border-bottom:1px solid">
        지갑 관리
      </div>
      <div @click="logout" style="line-height: 1.9; height:30px; color:red; width:100%; text-align:left; border-bottom:1px black solid">
        로그 아웃
      </div>
      <div @click="userDelete" style="line-height: 1.9; height:30px; color:red; width:100%; text-align:left; border-bottom:1px black solid">
        회원 탈퇴
      </div>
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
      userinfo: "",
      myclubinfo: "",
      showclub : false,
      showboard: false,
      selecturl: "@/assets/profile.png",
      boards:[],
    };
  },
  methods: {
    // 모든 vue에서 실행될 checklogin 함수 ######
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
    logout () {
      var url = "api/users/logout";
      http
        .delete(url, { withCredentials: true })
        .then((res) => {
          console.log("로그아웃");
          console.log(res);
          this.$router.push("/");
        })
        .catch((error) => {
          console.log("에러!");
          console.log(error);
        });
    },
    goback() {
      this.$router.push("club/list");
    },
    goSend()
    {
      this.$router.push("club/finance");
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
      let url = "/api/users/board";
        http.get(url)
        .then((res)=>{
            // console.log(res);
            res.data.forEach(item => {
                if(item.user.userEmail == this.$store.state.credentials.userEmail)
                {
                    this.boards.push(item);
                }
            });
        })
    },
  },
  created() {
    this.checkLogin();
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