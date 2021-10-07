<template>
  <div>
    <b-container style="padding-left: 0px; padding-right: 0px;">
      <b-row>
        <b-col offset="1" cols="10" class="ethCard" style="padding-top:10px; padding-bottom:10px;">
          <b-row>
            <b-col class="d-flex justify-content-between align-items-center" >
              <div style="width:35%;" >충전금액</div>
            <!-- </b-col>
            <b-col> -->
              <b-form-input  style="width:65%" v-model="value"></b-form-input></b-col>
          </b-row>
        </b-col>
        <div>
          <div style="height:30px"></div>
        <div class="btn btn-primary" @click="sendTx">
            충전
          </div>
          </div>
      </b-row>
    </b-container>
    
  </div>
</template>

<script>
import Web3 from "web3";
// import http from "@/util/http-common";
export default {
  name: "enchargeToken",
  props: ["abi","contractAddr","myAddr","nickname"],

  data(){
      return{
          value:"",
      }
  },
  methods:{
    async sendTx()
    {
      await this.encharge();

      this.saveHistory();

    },
    encharge()
    {
      const Tx = require('ethereumjs-tx').Transaction;
      var web3 = new Web3(new Web3.providers.HttpProvider("https://ropsten.infura.io/v3/d2f03576222c4c2fbc5eeb6850f9abf3"));
      var contract = new web3.eth.Contract(this.abi,this.contractAddr,{from: '0x97415060E1Ff0d2c51BF6d92B959be7D6316a983'}); //보내는사람 주소
      let privKey_= "27ddaa90db29f7740736e57703c437595a6f62707aa53d90773cb3fb4c91282d"; // 보내는사람의 개인키
      let privKey= new Buffer.from(privKey_, "hex");

      web3.eth.getTransactionCount('0x97415060E1Ff0d2c51BF6d92B959be7D6316a983',(err,txCount)=>{ //보내는 주소
        const txObject = {
          'from':'0x97415060E1Ff0d2c51BF6d92B959be7D6316a983', //보내는 주소
          'nonce': web3.utils.toHex(txCount),
          'gasLimit': web3.utils.toHex(1000000),
          'gasPrice': web3.utils.toHex(web3.utils.toWei('10','gwei')),
          'to': this.contractAddr, //계약 주소
          'value': '0x0',
          'data': contract.methods.transfer(this.myAddr,this.value).encodeABI() //받는 주소, 토큰 갯수
        }
        let transaction = new Tx(txObject,{'chain':'ropsten'});
        transaction.sign(privKey);
        web3.eth.sendSignedTransaction('0x'+transaction.serialize().toString('hex'))
        .on('transactionHash',console.log)
        .then((res)=>{
          alert("충전이 완료되었습니다.")
          console.log(res);
          location.reload();
        })
      })
    },
    async saveHistory()
    {
      var web3 = new Web3(new Web3.providers.HttpProvider("https://ropsten.infura.io/v3/d2f03576222c4c2fbc5eeb6850f9abf3"));
      var contract = new web3.eth.Contract(this.abi,this.contractAddr,{from: '0x97415060E1Ff0d2c51BF6d92B959be7D6316a983'}); 
      let today = new Date();
    	var year = today.getFullYear();
		  var month = ('0' + (today.getMonth() + 1)).slice(-2);
		  var day = ('0' + today.getDate()).slice(-2);
		  var hours = ('0' + today.getHours()).slice(-2); 
		  var minutes = ('0' + today.getMinutes()).slice(-2);
		  var dateString = year + '/' + month  + '/' + day + ' ' + hours+":"+minutes;
     //거래내역 저장하기

     let from = '0x97415060E1Ff0d2c51BF6d92B959be7D6316a983';
     let to = this.myAddr;
     let val = this.value *1;
     let msg = this.nickname+' 토큰충전';

      const Tx = require('ethereumjs-tx').Transaction;
      let privKey= new Buffer.from("27ddaa90db29f7740736e57703c437595a6f62707aa53d90773cb3fb4c91282d", "hex");

      web3.eth.getTransactionCount("0x97415060E1Ff0d2c51BF6d92B959be7D6316a983",(err,txCount)=>{ //보내는 주소
        const txObject = {
          'from':'0x97415060E1Ff0d2c51BF6d92B959be7D6316a983', //보내는 주소
          'nonce': web3.utils.toHex(txCount+1),
          'gasLimit': web3.utils.toHex(1000000),
          'gasPrice': web3.utils.toHex(web3.utils.toWei('10','gwei')),
          'to': this.contractAddr, //계약 주소
          'value': '0x0',
          'data': contract.methods.saveRecode(from,to,val,dateString,msg).encodeABI() //받는 주소, 토큰 갯수
        }
        let transaction = new Tx(txObject,{'chain':'ropsten'});
        transaction.sign(privKey);
        web3.eth.sendSignedTransaction('0x'+transaction.serialize().toString('hex'))
        .on('transactionHash',console.log)
      });
    },
  }
}
</script>
<style scoped>
.ethCard {
  background-color: #1EC0FF;
  border-radius: 0.5rem;
  color: #FFFFFF;
  display: flex;  
  flex-direction: column;
  justify-content: center;
  
}
.textAlignStart {
  align-self: start;
  margin: 1rem 0 0.3rem 0;
  font-size: 1rem;
}
.textAlignEnd {
  align-self: end;
  margin: 0.5rem 0.3rem 0.5rem 0;
  font-size: 0.8rem;
}
.transactonButton {
  background-color: #1ec0ff;
  margin: 2rem auto 0;
  height: 2.3rem;
  width: 100%;
  border-radius: 2.5rem;
  font-size: 1rem;
  font-weight: 500;
  color: #fff;
  border: 1px solid transparent;
}
.paddingRemove {
  padding: 0 0 0 0;
}
</style>