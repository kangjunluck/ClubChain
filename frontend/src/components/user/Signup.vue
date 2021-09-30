<template>
    <div>
        <div class="signup_header">
            회원가입
        </div><br>
        <div class="signup_form">
            <input v-on:change="fileSelect()" ref="image" type="file" name="photo" id="photo" />
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
import Web3 from "web3";
export default {
  data() {
    return {
      image: "",
      password: "",
      userEmail: "",
      useraccount: "",
      usernickname: "",
      addr:"",

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
        this.saveFile(privateKey)
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
        {
          headers: {
            'Content-Type' : 'multipart/form-data'
          }
        }         
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
      console.log(this.$refs);
      this.image = this.$refs.image.files[0];
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
    }
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