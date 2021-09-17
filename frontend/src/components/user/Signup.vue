<template>
    <div>
        <div class="signup_header">
            회원가입
        </div><br>
        <div class="signup_form">
            <div class="my-4 d-flex">
                <div class="float1">
                    <img alt="프로필" :src="image" id="image" />
                </div>
                <div class="float2">
                    <div class="filename">파일명....png</div>
                    <div class="btn btn-secondary">이미지 선택</div>
                </div>
            </div>
            <input 
                type="text"
                id="usernickname"
                name="usernickname"
                v-model="usernickname"
                ref="usernickname"
                placeholder="닉네임"
                class="form-control mt-5 mb-2"
            />
            <input
                type="text"
                id="userEmail"
                name="userEmail"
                v-model="userEmail"
                ref="userEmail"
                placeholder="이메일"
                class="form-control mb-2"
            />
            <span id="request" class="btn btn-primary">
                인증 요청
            </span>
            <input
                type="text"
                id="code"
                name="code"
                v-model="code"
                ref="code"
                placeholder="인증코드"
                class="form-control mb-2"
            />
            <input
                type="password"
                id="password"
                name="password"
                v-model="password"
                ref="password"
                placeholder="비밀번호"
                class="form-control mb-2"
            />
            <input
                type="password"
                id="passwordconfirm"
                name="passwordconfirm"
                v-model="passwordconfirm"
                ref="passwordconfirm"
                placeholder="비밀번호 확인"
                class="form-control mb-2"
            />
        </div>

        <div class="btn btn-primary" @keyup.enter="insertUser" @click="insertUser">
            회원가입
        </div>
    </div>
</template>

<script>
import http from "@/util/http-common";

export default {
  data() {
    return {
      image: "",
      usernickname: "",
      userEmail: "",
      password: "",
    };
  },
  methods: {
    insertUser() {
      http
        .post("/regist", {
          password: this.password,
          userEmail: this.userEmail,
          usernickname: this.usernickname,
          userthumbnail: "",
        })
        .then(({ data }) => {
          console.log(data);
          let msg = "회원가입 실패!!";
          if (data === "success") {
            msg = "회원가입 완료";
            this.$router.push("/");
          }
          alert(msg);
        })
        .catch((error) => {
          alert("회원가입 실패");
          console.dir(error);
        });
    },
    home() {
      this.$router.push("/");
    },
  },
};
</script>



<style scoped>
.signup_header {
    font-size: 30px;
    float:left;
    margin-left: 20%;
}

.signup_form {
    width: 60%;
    margin-left: 20%;
}
.float1{
    width:35%;
}
.float2{
    width: 65%;
}

#image {
  width: 100px;
  height: 100%;
  border-radius: 100%;
}

#userEmail {
    width: 70%;
    display: inline-block;
}
#request {
    font-size: 10px;
}
</style>