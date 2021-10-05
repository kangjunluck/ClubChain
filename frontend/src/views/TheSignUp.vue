<template>
  <div class="container d-flex flex-column justify-content-center align-items-center">
    <div class="toparea position-absolute">
      <div class="d-flex justify-content-between h-100 align-items-center">
        <i class="fas fa-arrow-left" @click="goback"></i>
      </div>
    </div>
    <div class="row signup_header">
      <div class="col-9"><span>회원가입</span></div>
    </div>
    <div class="row signup_form">
      <div class="col-12">
        <div class="mb-2 d-flex justify-content-between align-items-center">
          <img :src="selecturl" alt="url" class="col-6 px-0" style="width:3rem; height:3rem;">
          <label class="photo-button" for="photo">업로드</label>
          <input v-on:change="fileSelect()" ref="image" type="file" name="photo" id="photo" class="col-6" style="display:none;" />
        </div>
      </div>
      <input 
          type="text"
          id="usernickname"
          name="usernickname"
          v-model="usernickname"
          ref="usernickname"
          placeholder="닉네임"
          class="form-control col-12"
      />
      <div class="col-12">
        <div class="row">
          <input
              type="text"
              id="userEmail"
              name="userEmail"
              v-model="userEmail"
              ref="userEmail"
              placeholder="이메일"
              class="form-control"
          />
          <button class="btn photo-button mb-2">
              인증 요청
          </button>
        </div>
      </div>
      <input
          type="text"
          id="code"
          name="code"
          v-model="code"
          ref="code"
          placeholder="인증코드"
          class="form-control"
      />
      <input
          type="password"
          id="password"
          name="password"
          v-model="password"
          ref="password"
          placeholder="비밀번호"
          class="form-control"
      />
      <input
          type="password"
          id="passwordconfirm"
          name="passwordconfirm"
          v-model="passwordconfirm"
          ref="passwordconfirm"
          placeholder="비밀번호 확인"
          class="form-control"
      />
      <div class="btn photo-button" @keyup.enter="insertUser" @click="insertUser">
          회원가입
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
import Web3 from "web3";
export default {
  data() {
    return {
      image: "",
      password: "",
      userEmail: "",
      useraccount: "",
      usernickname: "",
      addr: "",

      selecturl: require("@/assets/profile.png"),
    };
  },
  methods: {
    insertUser() {
      var web3 = new Web3(new Web3.providers.HttpProvider("https://ropsten.infura.io/v3/d2f03576222c4c2fbc5eeb6850f9abf3"));
      // 계정생성, 지갑생성
        let {address,privateKey} = web3.eth.accounts.create();

        let wallet = web3.eth.accounts.wallet.add(
          {
            privateKey: privateKey,
            address: address
          }
        )
        // 유저에게 privateKey 전달
        this.saveFile(privateKey.substring(2))
        console.log(wallet);

      const formData = new FormData;
      formData.append('image', this.image);
      formData.append('password', this.password);
      formData.append('userEmail', this.userEmail);
      formData.append('usernickname', this.usernickname);
      formData.append('useraccount', address);
      formData.append('userprivatekey', privateKey);

      this.useraccount = address;
      http
        .post("/api/users/regist", formData,
        // {
        //   headers: {
        //     'Content-Type' : 'multipart/form-data'
        //   }
        // }         
        ).then( res => {
          this.sendEth()
          console.log(res.data);
          alert(msg);
          let msg = "회원가입 완료";

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
      this.image = this.$refs.image.files[0];
      this.selecturl = URL.createObjectURL(this.image);
    },
    sendEth()
    {
      const Tx = require('ethereumjs-tx').Transaction;
      var web3 = new Web3(new Web3.providers.HttpProvider("https://ropsten.infura.io/v3/d2f03576222c4c2fbc5eeb6850f9abf3"));

      let privKey_= "27ddaa90db29f7740736e57703c437595a6f62707aa53d90773cb3fb4c91282d"; // 보내는사람의 개인키
      let privKey= new Buffer.from(privKey_, "hex");
      console.log("privateKey = ",privKey);

      web3.eth.getTransactionCount("0x97415060E1Ff0d2c51BF6d92B959be7D6316a983",(err,txCount)=>{ //보내는 주소
        const txObject = {
          'from':'0x97415060E1Ff0d2c51BF6d92B959be7D6316a983', //보내는 주소
          'nonce': web3.utils.toHex(txCount),
          'gasLimit': web3.utils.toHex(1000000),
          'gasPrice': web3.utils.toHex(web3.utils.toWei('10','gwei')),
          'to': this.useraccount,
          'value': '0x16345785d8a0000',
        }

        let transaction = new Tx(txObject,{'chain':'ropsten'});
        transaction.sign(privKey);
        web3.eth.sendSignedTransaction('0x'+transaction.serialize().toString('hex'))
        .on('transactionHash',console.log)
      });
    },
    saveFile(privateKey) {
      var blob = new Blob([privateKey], {type: 'text/plain'});

      const objURL = window.URL.createObjectURL(blob);
      console.log(objURL);
      if (window.__Xr_objURL_forCreatingFile__) {
        window.URL.revokeObjectURL(window.__Xr_objURL_forCreatingFile__);
      }
      window.__Xr_objURL_forCreatingFile__ = objURL;
      var a = document.createElement('a');
      a.download = "PrivateKey";
      a.href = objURL;
      console.log(a)
      a.click();
    },
    goback () {
      this.$router.push("/");
    },
  },
};
</script>



<style scoped>
.signup_header {
    width: 60%;
    font-size: 2em;
    text-align: left;
}

.signup_form {
    width: 60%;
}
.photo-button{
  padding: 3px 15px;
  background-color:#1ec0ff;
  border-radius: 2px;
  color: white;
  cursor: pointer;
}
.form-control{
  margin-bottom: 8px;
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