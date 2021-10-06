<template>
  <div class="container d-flex flex-column justify-content-center">
    <div class="toparea position-absolute">
      <div class="d-flex justify-content-between h-100 align-items-center">
        <i class="fas fa-arrow-left" @click="goback"></i>
      </div>
    </div>
    <div class="row signup_header mb-3">회원가입</div>
    <div class="row signup_form">
      <div
        class="mb-2 d-flex justify-content-between align-items-end photo-set"
      >
        <img :src="selecturl" alt="url" class="col-6 px-0 image" />
        <label class="photo-button" for="photo">업로드</label>
        <input
          v-on:change="fileSelect()"
          ref="image"
          type="file"
          name="photo"
          id="photo"
          class="col-6"
          style="display: none"
        />
      </div>
      <input
        type="text"
        id="usernickname"
        name="usernickname"
        v-model="usernickname"
        ref="usernickname"
        placeholder="닉네임"
        autocomplete="off"
        class="form-control col-12"
      />

      <input
        type="text"
        id="userEmail"
        name="userEmail"
        v-model="userEmail"
        ref="userEmail"
        placeholder="이메일"
        class="form-control"
        autocomplete="off"
      />
      <button class="btn email-button mb-2">인증</button>

      <input
        type="text"
        id="code"
        name="code"
        v-model="code"
        ref="code"
        placeholder="인증코드"
        class="form-control"
        autocomplete="off"
      />
      <input
        type="password"
        id="password"
        name="password"
        v-model="password"
        ref="password"
        placeholder="비밀번호"
        class="form-control"
        autocomplete="new-password"
      />
      <input
        type="password"
        id="passwordconfirm"
        name="passwordconfirm"
        v-model="passwordconfirm"
        ref="passwordconfirm"
        placeholder="비밀번호 확인"
        class="form-control"
        autocomplete="new-password"
      />
      <div
        class="btn submit-button"
        @keyup.enter="insertUser"
        @click="insertUser"
      >
        회원가입
      </div>
    </div>
    <CompleteModal v-if="completeModal" :message="message" @close="moveTo"/>
    <FailModal v-if="failModal" :message="message" @close="failModal=false"/>
  </div>
</template>

<script>
import http from "@/util/http-common";
import CompleteModal from "../components/modal/complete.vue"
import FailModal from "../components/modal/fail.vue"
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
    // imageclick(){
    //   $
    // },
    insertUser() {
      var web3 = new Web3(
        new Web3.providers.HttpProvider(
          "https://ropsten.infura.io/v3/d2f03576222c4c2fbc5eeb6850f9abf3"
        )
      );
      // 계정생성, 지갑생성
      let { address, privateKey } = web3.eth.accounts.create();

      let wallet = web3.eth.accounts.wallet.add({
        privateKey: privateKey,
        address: address,
      });
      // 유저에게 privateKey 전달
      this.saveFile(privateKey.substring(2));
      console.log(wallet);

      const formData = new FormData();
      formData.append("image", this.image);
      formData.append("password", this.password);
      formData.append("userEmail", this.userEmail);
      formData.append("usernickname", this.usernickname);
      formData.append("useraccount", address);
      formData.append("userprivatekey", privateKey);

      this.useraccount = address;
      http
        .post(
          "/api/users/regist",
          formData
        )
        .then((res) => {
          this.sendEth();
          console.log(res.data);
          this.message = "회원가입 완료";
          this.completeModal = true;
          
        })
        .catch((error) => {
          this.message = error
          this.failModal = true;
        });
    },
    home() {
      this.$router.push("/");
    },
    fileSelect() {
      this.image = this.$refs.image.files[0];
      this.selecturl = URL.createObjectURL(this.image);
    },
    sendEth() {
      const Tx = require("ethereumjs-tx").Transaction;
      var web3 = new Web3(
        new Web3.providers.HttpProvider(
          "https://ropsten.infura.io/v3/d2f03576222c4c2fbc5eeb6850f9abf3"
        )
      );

      let privKey_ =
        "27ddaa90db29f7740736e57703c437595a6f62707aa53d90773cb3fb4c91282d"; // 보내는사람의 개인키
      let privKey = new Buffer.from(privKey_, "hex");
      console.log("privateKey = ", privKey);

      web3.eth.getTransactionCount(
        "0x97415060E1Ff0d2c51BF6d92B959be7D6316a983",
        (err, txCount) => {
          //보내는 주소
          const txObject = {
            from: "0x97415060E1Ff0d2c51BF6d92B959be7D6316a983", //보내는 주소
            nonce: web3.utils.toHex(txCount),
            gasLimit: web3.utils.toHex(1000000),
            gasPrice: web3.utils.toHex(web3.utils.toWei("10", "gwei")),
            to: this.useraccount,
            value: "0x16345785d8a0000",
          };

          let transaction = new Tx(txObject, { chain: "ropsten" });
          transaction.sign(privKey);
          web3.eth
            .sendSignedTransaction(
              "0x" + transaction.serialize().toString("hex")
            )
            .on("transactionHash", console.log);
        }
      );
    },
    saveFile(privateKey) {
      var blob = new Blob([privateKey], { type: "text/plain" });

      const objURL = window.URL.createObjectURL(blob);
      console.log(objURL);
      if (window.__Xr_objURL_forCreatingFile__) {
        window.URL.revokeObjectURL(window.__Xr_objURL_forCreatingFile__);
      }
      window.__Xr_objURL_forCreatingFile__ = objURL;
      var a = document.createElement("a");
      a.download = "PrivateKey";
      a.href = objURL;
      console.log(a);
      a.click();
    },
    goback() {
      this.$router.push("/");
    },
    moveTo () {
      this.completeModal = false;
      this.$router.push("/");
    },
  },
};
</script>



<style scoped>
.container {
  width: 100%;
  height: 100%;
}
.signup_header {
  width: 70%;
  font-size: 2em;
  font-weight: 500;
  text-align: left;
}
.photo-set {
  margin: 0 0 0 0;
  padding: 0 0 0 0;
}

.signup_form {
  width: 70%;
}
#userEmail {
  width: 70%;
}
.photo-button {
  width: 28%;
  min-height: 2rem;
  background-color: #1ec0ff;
  border-radius: 0.2rem;
  color: white;
  cursor: pointer;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.submit-button {
  width: 100%;
  background-color: #1ec0ff;
  border-radius: 3rem;
  color: white;
}
.email-button {
  width: 28%;
  margin: 0 0 0 2%;
  background-color: #1ec0ff;
  border-radius: 0.2rem;
  color: white;
}
.form-control {
  margin-bottom: 8px;
}
.float1 {
  width: 35%;
}
.float2 {
  width: 65%;
}

.image {
  width: 6rem;
  height: 6rem;
  border-radius: 100%;
}
.toparea {
  /* 네브바 3rem 통일 */
  top: 0;
  height: 3rem;
  width: 100%;
  /* background-color: red; */
}
.fas {
  font-size: 1.5rem;
  /* 나중에 패딩 통일 */
  padding: 0 0 0 0.8rem;
}
.row {
  margin: 0 auto;
  padding: 0 0 0 0;
  width: 70%;
}
</style>