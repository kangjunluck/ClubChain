<template>
    <div class="main">
        <div class="club">
            <div class="clubimage">
                <img alt="Vue logo" src="@/assets/logo.png"/>
            </div>
            <div class="clubname">
                2게 블록이야
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
      
    };
  },
  methods: {
    enterClub() {
        console.log("test");
      http
        .post("/", {
            user_id: "",
            position: "",
        })
        .then( () => {
            
        })
    },
    goBack() {
        this.$router.push({ name : "Main"});
    },
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