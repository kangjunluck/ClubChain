<template>
    <div class="main">
        <div class="club">
            <div class="clubimage">
                <img alt="Vue logo" src="@/assets/CC_logo_symbol.svg"/>
            </div>
            <div class="clubname">
                {{name}}
            </div>
        </div>
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
        <div class="buttonarea">
            <div class="cancel btn btn-secondary" @click="goBack">
                취소
            </div>
            <div class="enter btn btn-primary" @click="enterClub">
                가입
            </div>
        </div>
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

.club {
    border: 1px solid black;
    width: 80%;
    margin-left: 10%;
}
.clubimage {
    border: 1px solid black;
}

.club img {
    width: 80%;
}

.clubname {
    font-size: 200%;
    border: 1px solid black;
}

.buttonarea {
    margin-top: 10%;
}

.cancel {
    margin-right: 50px;
}

#password {
    width: 80%;
    margin-left: 10%;
    margin-top: 10%;
}
</style>