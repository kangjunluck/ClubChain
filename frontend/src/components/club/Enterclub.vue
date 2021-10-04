<template>
    <div class="main">
        <!-- 뒤로가기 아이콘 -->
		<div align="left" class="cancel">
			<i class="far fa-times-circle fa-2x"></i>
		</div>
        <!-- 동호회 이미지 align-v="center"-->
        <b-container align-v="center" class="margin-style">
            <b-row class="justify-content-center" >
                <b-col cols="8">
                    <b-card
                        img-src="https://placekitten.com/500/350"
                        img-alt=logo
                        tag="article"
                        img-height=300
                        img-width=400
                        class="mb-2 center"
                    >
                        <b-card-text><h3>{{name}}</h3></b-card-text>
                    </b-card>
                </b-col>
            </b-row>
            <b-row class="justify-content-center mt-1">
                <b-col cols="8">
                    <!-- 비밀번호 입력 -->
                    <input
                            type="password"
                            id="password"
                            name="password"
                            v-model="password"
                            ref="password"
                            placeholder="비밀번호"
                            class="form-control mb-2"
                    />
                    <!--v-if로 비밀번호가 필요한 동호회에만 표시하게끔 수정해야함-->
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
    };
},
  methods: {
    setSession: function () {
    document.cookie
    const token = localStorage.getItem('jwt')
    const config = {
        Authorization: `JWT ${token}`
    }
    return config
    },
    enterClub() {
        //var url = "/api/";
        //url += "4" + "/";     //가입할 동호회id로 변경
        //console.log(this.$store.state.credentials.userEmail);
        //console.log(this.$store.state.credentials.usernickname);
        //this.$session.set('LoginUser', this.$store.state.credentials.userEmail);
        //console.log(this.$session.get('LoginUser'));
        //console.log(this.$session.get('userEmail') + "aaaa");
      http
        .post("/api/4/", {},
        { withCredentials: true }
        ).then( () => {
        });

    },
    goBack() {
        this.$router.push({ name : "Main"});
    },
  },
  created() {
        //clubid를 스토어에서 가져와서 경로에 붙여야함. 현재는 4로 고정
        console.log(this.$store.state.selectedClub);
        console.log("aaa");
        var url = "/api/club/{clubid}?clubid=4"
        http
        .get(url,{
            withCredentials : true
        })
        .then((res) => {
            console.log(res.data);
            this.name = res.data.name;
        }).catch((error) => {
            console.log(error);
            alert("가져오기 실패");
        })
        
    },
};
</script>

<style scoped>
.cancel-btn-style {
    background-color: #999999;
    color: #ffffff;
    width: 120px;
    border: 0;
    outline: 0;
}

.enter-btn-style {
    background-color:#1ec0ff;
    color: #ffffff;
    width: 120px;
    border: 0;
    outline: 0;
}

.margin-style {
    margin-top: 100px;
}
</style>