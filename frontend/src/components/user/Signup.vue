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
                    <input v-on:change="fileSelect()" ref="image" type="file" name="photo" id="photo" />
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
                type="text"
                id="useraccount"
                name="useraccount"
                v-model="useraccount"
                ref="useraccount"
                placeholder="계정명"
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
      password: "",
      userEmail: "",
      useraccount: "",
      usernickname: "",
    };
  },
  methods: {
    insertUser() {
      const formData = new FormData;
      formData.append('image', this.image);
      formData.append('password', this.password);
      formData.append('userEmail', this.userEmail);
      formData.append('useraccount', this.useraccount);
      formData.append('usernickname', this.usernickname);
      http
        .post("/api/users/regist", formData, {
          headers: {
            'Content-Type' : 'multipart/form-data'
          }
        }         
        )
        .then( res => {
          console.log(res.data);
          let msg = "회원가입 완료";
          alert(msg);
          this.$router.push("/");
        })
        .catch((error) => {
          alert("회원가입 실패");
          console.dir(error);
        });
    },
    home() {
      this.$router.push("/");
    },
    fileSelect() {
      console.log(this.$refs);
      this.image = this.$refs.image.files[0];
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