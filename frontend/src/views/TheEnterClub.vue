<template>
    <div class="main">
        <!-- 뒤로가기 아이콘 -->
		<div align="left" class="cancel">
			<i class="far fa-times-circle fa-2x" @click="goback"></i>
		</div>
        <!-- 동호회 이미지 -->
        <b-container align-v="center" class="margin-style">
            <b-row class="justify-content-center" >
                <b-col cols="8">
                    <b-card
                        :img-src="image"
                        img-alt=logo
                        tag="article"
                        img-height=300
                        img-width=400
                        class="mb-2 center"
                    >
                        <b-card-text class="name">{{name}}</b-card-text>
                    </b-card>
                </b-col>
            </b-row>
            <b-row class="justify-content-center mt-1" v-if="password">
                <b-col cols="8">
                    <!-- 비밀번호 입력 -->
                    <input
                            type="password"
                            id="inputPassword"
                            name="inputPassword"
                            v-model="inputPassword"
                            ref="inputPassword"
                            placeholder="비밀번호"
                            class="form-control mb-2"
                    />
                </b-col>
            </b-row>
            <b-row class="justify-content-center align-content mt-1">
                <b-col cols="8">
                    <b-button block class="enter-btn-style" @click="enterClub">가입</b-button>
                </b-col>
            </b-row>
        </b-container>
        <CompleteModal v-if="completeModal" :message="message" @close="moveTo"/>
        <FailModal v-if="failModal" :message="message" @close="failModal=false"/>
    </div>
</template>

<script>
import http from "@/util/http-common";
import CompleteModal from "../components/modal/complete.vue"
import FailModal from "../components/modal/fail.vue"

export default {
  data() {
    return {
        name : "",
        image : "",
        password : "",
        inputPassword: "",

        completeModal : false,
        failModal : false,
        message : "",
    };
  },
  components :{
    CompleteModal,
    FailModal,
  },
  methods: {
    goback () {
        this.$router.push("/club/list");
    },
    setSession: function () {
    document.cookie
    const token = localStorage.getItem('jwt')
    const config = {
        Authorization: `JWT ${token}`
    }
    return config
    },
    enterClub() {
        if(this.inputPassword != this.password) {
            this.message = "비밀번호를 다시 입력해주세요"
            this.failModal = true;
            this.inputPassword = "";
            return 
        }

        var url = "/api/";
        url += this.$store.state.selectedClub;
        url += "/";
        console.log(url);
      http
        .post(url, {
            clubid : this.$store.state.selectedClub,
        },
        { withCredentials: true }
        ).then( () => {
            console.log("가입 성공");
            this.message = "회원가입 완료";
            this.completeModal = true;
            
        }).catch((error) => {
            console.log(error);
            this.message = "가입실패"
            this.failModal = true;
        });
    },
    moveTo () {
      this.completeModal = false;
      this.$router.push("/club/list");
    },
  },
  created() {
        var url = "/api/club/{clubid}?clubid="
        url += this.$store.state.selectedClub;
        http
        .get(url,{
            withCredentials : true
        })
        .then((res) => {
            console.log(res.data);
            this.name = res.data.name;
            this.image = "/resources/" + res.data.profile_thumbnail;
            this.password = res.data.password;
        }).catch((error) => {
            console.log(error);
            alert("가져오기 실패");
        })
        
    },
};
</script>

<style scoped>
.main {
    overflow-x: hidden;
    overflow-y: hidden;
}

.cancel {
    position: relative;
	left: 15px;
	top: 15px;
}

.enter-btn-style {
    background-color:#1ec0ff;
    color: #ffffff;
    width: 100%;
    border: 0;
    outline: 0;
    border-radius: 3rem;
}

.margin-style {
    margin-top: 70px;
}

.name {
    font-size: 150%;
    font-weight: bold;
}
</style>