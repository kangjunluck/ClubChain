<template>
    <div class="main">
        <!-- 뒤로가기 아이콘 -->
		<div align="left" class="cancel">
			<i class="far fa-times-circle fa-2x" @click="goback"></i>
		</div>
        <!-- 동호회 이미지 align-v="center"-->
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
                            id="password"
                            name="password"
                            v-model="inputPassword"
                            ref="password"
                            placeholder="비밀번호"
                            class="form-control mb-2"
                    />
                </b-col>
            </b-row>
            <b-row class="justify-content-center align-content mt-1">
                <b-col cols="2"></b-col>
                <b-col cols="4">
                    <b-button block class="cancel-btn-style" @click="goBack">취소</b-button>
                </b-col>
                
                <b-col cols="4">
                    <b-button block class="enter-btn-style" @click="enterClub">가입</b-button>
                </b-col>
                <b-col cols="2"></b-col>
                
            </b-row>
        </b-container>
    </div>
</template>

<script>
import http from "@/util/http-common";

export default {
  data() {
    return {
        name : "",
        image : "",
        password : "",
        inputPassword: ""
    };
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
        var url = "/api/";
        url += this.$store.state.selectedClub;
        url += "/";
        console.log(url);
      http
        .post(url, {
            clubid : this.$store.state.selectedClub,
        },
        {password: {password : this.inputPassword}},
        { withCredentials: true }
        ).then( () => {
            console.log("가입 성공");
            this.$router.push("/club/list");
        }).catch((error) => {
            console.log(error);
            alert("가입 실패");
        });
    },
    goBack() {
        this.$router.push({ name : "Main"});
    },
  },
  created() {
        console.log(this.$store.state.selectedClub);
        console.log("aaa");
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
.cancel {
	position: relative;
	left: 15px;
	top: 15px;
}

.cancel-btn-style {
    background-color: #999999;
    color: #ffffff;
    width: 100%;
    border: 0;
    outline: 0;
}

.enter-btn-style {
    background-color:#1ec0ff;
    color: #ffffff;
    width: 100%;
    border: 0;
    outline: 0;
}

.margin-style {
    margin-top: 100px;
}

.name {
    font-size: 150%;
    font-weight: bold;
}
</style>