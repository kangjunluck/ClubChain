<template>
  <div>
    <b-container>
      <b-row>
        <b-col offset="1" cols="10" class="ethCard" style="padding-top:10px; padding-bottom:10px;">
          <b-row>
            <b-col>받는사람</b-col>
            <b-col><b-form-input v-model="toEmail"></b-form-input></b-col>
          </b-row>
          <b-row>
            <b-col style="font-size: 0.8rem">동호회 계좌</b-col>
            <b-col><input type="checkbox" id="checkbox" v-model="checked"></b-col>
          </b-row>
          
          <b-row>
            <b-col>송금액</b-col>
            <b-col><b-form-input type="number" v-model="value"></b-form-input></b-col>
          </b-row>
          <b-row>
            <b-col>송금 메세지</b-col>
            <b-col><b-form-input v-model="message"></b-form-input></b-col>
          </b-row>
          <b-row style="text-align">
            <!-- <b-col>PrivateKey 업로드</b-col> -->
            <input id="PK" type="file" accept="text/*" value="개인키 파일" @change="updatePK">
          </b-row>
        </b-col>
        <div>
          <div style="height:30px"></div>
        <div class="btn btn-primary" @click="sendTx">
            송금
          </div>
          </div>
      </b-row>
    </b-container>
    
  </div>
</template>

<script>
import Web3 from "web3";
import http from "@/util/http-common";
export default {
  name: "Transaction",
    props: ["clubhis","clubAddr"],
  data() {
    return {  
      abi: [
            {
              "inputs": [],
              "stateMutability": "nonpayable",
              "type": "constructor"
            },
            {
              "anonymous": false,
              "inputs": [
                {
                  "indexed": true,
                  "internalType": "address",
                  "name": "owner",
                  "type": "address"
                },
                {
                  "indexed": true,
                  "internalType": "address",
                  "name": "spender",
                  "type": "address"
                },
                {
                  "indexed": false,
                  "internalType": "uint256",
                  "name": "value",
                  "type": "uint256"
                }
              ],
              "name": "Approval",
              "type": "event"
            },
            {
              "anonymous": false,
              "inputs": [
                {
                  "indexed": true,
                  "internalType": "address",
                  "name": "from",
                  "type": "address"
                },
                {
                  "indexed": true,
                  "internalType": "address",
                  "name": "to",
                  "type": "address"
                },
                {
                  "indexed": false,
                  "internalType": "uint256",
                  "name": "value",
                  "type": "uint256"
                }
              ],
              "name": "Transfer",
              "type": "event"
            },
            {
              "inputs": [],
              "name": "INITIAL_SUPPLY",
              "outputs": [
                {
                  "internalType": "uint256",
                  "name": "",
                  "type": "uint256"
                }
              ],
              "stateMutability": "view",
              "type": "function"
            },
            {
              "inputs": [
                {
                  "internalType": "uint256",
                  "name": "",
                  "type": "uint256"
                }
              ],
              "name": "Recodes",
              "outputs": [
                {
                  "internalType": "address",
                  "name": "fromAddr",
                  "type": "address"
                },
                {
                  "internalType": "address",
                  "name": "toAddr",
                  "type": "address"
                },
                {
                  "internalType": "uint256",
                  "name": "value",
                  "type": "uint256"
                },
                {
                  "internalType": "string",
                  "name": "date",
                  "type": "string"
                },
                {
                  "internalType": "string",
                  "name": "message",
                  "type": "string"
                }
              ],
              "stateMutability": "view",
              "type": "function"
            },
            {
              "inputs": [
                {
                  "internalType": "address",
                  "name": "owner",
                  "type": "address"
                },
                {
                  "internalType": "address",
                  "name": "spender",
                  "type": "address"
                }
              ],
              "name": "allowance",
              "outputs": [
                {
                  "internalType": "uint256",
                  "name": "",
                  "type": "uint256"
                }
              ],
              "stateMutability": "view",
              "type": "function"
            },
            {
              "inputs": [
                {
                  "internalType": "address",
                  "name": "spender",
                  "type": "address"
                },
                {
                  "internalType": "uint256",
                  "name": "amount",
                  "type": "uint256"
                }
              ],
              "name": "approve",
              "outputs": [
                {
                  "internalType": "bool",
                  "name": "",
                  "type": "bool"
                }
              ],
              "stateMutability": "nonpayable",
              "type": "function"
            },
            {
              "inputs": [
                {
                  "internalType": "address",
                  "name": "account",
                  "type": "address"
                }
              ],
              "name": "balanceOf",
              "outputs": [
                {
                  "internalType": "uint256",
                  "name": "",
                  "type": "uint256"
                }
              ],
              "stateMutability": "view",
              "type": "function"
            },
            {
              "inputs": [],
              "name": "decimals",
              "outputs": [
                {
                  "internalType": "uint8",
                  "name": "",
                  "type": "uint8"
                }
              ],
              "stateMutability": "view",
              "type": "function"
            },
            {
              "inputs": [
                {
                  "internalType": "address",
                  "name": "spender",
                  "type": "address"
                },
                {
                  "internalType": "uint256",
                  "name": "subtractedValue",
                  "type": "uint256"
                }
              ],
              "name": "decreaseAllowance",
              "outputs": [
                {
                  "internalType": "bool",
                  "name": "",
                  "type": "bool"
                }
              ],
              "stateMutability": "nonpayable",
              "type": "function"
            },
            {
              "inputs": [
                {
                  "internalType": "address",
                  "name": "spender",
                  "type": "address"
                },
                {
                  "internalType": "uint256",
                  "name": "addedValue",
                  "type": "uint256"
                }
              ],
              "name": "increaseAllowance",
              "outputs": [
                {
                  "internalType": "bool",
                  "name": "",
                  "type": "bool"
                }
              ],
              "stateMutability": "nonpayable",
              "type": "function"
            },
            {
              "inputs": [],
              "name": "loadRecode",
              "outputs": [
                {
                  "components": [
                    {
                      "internalType": "address",
                      "name": "fromAddr",
                      "type": "address"
                    },
                    {
                      "internalType": "address",
                      "name": "toAddr",
                      "type": "address"
                    },
                    {
                      "internalType": "uint256",
                      "name": "value",
                      "type": "uint256"
                    },
                    {
                      "internalType": "string",
                      "name": "date",
                      "type": "string"
                    },
                    {
                      "internalType": "string",
                      "name": "message",
                      "type": "string"
                    }
                  ],
                  "internalType": "struct MyToken.Recode[]",
                  "name": "",
                  "type": "tuple[]"
                }
              ],
              "stateMutability": "nonpayable",
              "type": "function"
            },
            {
              "inputs": [],
              "name": "name",
              "outputs": [
                {
                  "internalType": "string",
                  "name": "",
                  "type": "string"
                }
              ],
              "stateMutability": "view",
              "type": "function"
            },
            {
              "inputs": [
                {
                  "internalType": "address",
                  "name": "_fromAddr",
                  "type": "address"
                },
                {
                  "internalType": "address",
                  "name": "_toAddr",
                  "type": "address"
                },
                {
                  "internalType": "uint256",
                  "name": "_value",
                  "type": "uint256"
                },
                {
                  "internalType": "string",
                  "name": "_date",
                  "type": "string"
                },
                {
                  "internalType": "string",
                  "name": "_message",
                  "type": "string"
                }
              ],
              "name": "saveRecode",
              "outputs": [],
              "stateMutability": "nonpayable",
              "type": "function"
            },
            {
              "inputs": [],
              "name": "symbol",
              "outputs": [
                {
                  "internalType": "string",
                  "name": "",
                  "type": "string"
                }
              ],
              "stateMutability": "view",
              "type": "function"
            },
            {
              "inputs": [],
              "name": "totalSupply",
              "outputs": [
                {
                  "internalType": "uint256",
                  "name": "",
                  "type": "uint256"
                }
              ],
              "stateMutability": "view",
              "type": "function"
            },
            {
              "inputs": [
                {
                  "internalType": "address",
                  "name": "recipient",
                  "type": "address"
                },
                {
                  "internalType": "uint256",
                  "name": "amount",
                  "type": "uint256"
                }
              ],
              "name": "transfer",
              "outputs": [
                {
                  "internalType": "bool",
                  "name": "",
                  "type": "bool"
                }
              ],
              "stateMutability": "nonpayable",
              "type": "function"
            },
            {
              "inputs": [
                {
                  "internalType": "address",
                  "name": "sender",
                  "type": "address"
                },
                {
                  "internalType": "address",
                  "name": "recipient",
                  "type": "address"
                },
                {
                  "internalType": "uint256",
                  "name": "amount",
                  "type": "uint256"
                }
              ],
              "name": "transferFrom",
              "outputs": [
                {
                  "internalType": "bool",
                  "name": "",
                  "type": "bool"
                }
              ],
              "stateMutability": "nonpayable",
              "type": "function"
            }
          ],
      contractAddr:"0xbC9e278a4F4ceAb626324Cf9cF1D7ea46F469dB3",
      myAddr:"",
      privateKey:"",
      toEmail:"",
      toAddr:"",
      value:"",
      message:"",
    };
  },
  computed:{
    checked:
    {
      set: function(e)
      {
        if(e === true)
        {
          this.toEmail = "동호회 주소";
        }
        else
        {
          this.toEmail ="";
        }
      }
    }
  },

  methods:{
    async sendTx()
    {
      const res = await http.get("api/users/"+ encodeURI(this.$store.state.credentials.userEmail));
      if(this.toEmail != "동호회 주소")
      {
        const res2 = await http.get("api/users/"+this.toEmail);
        this.toAddr = res2.data.useraccount;
      }
      else
      {
        this.toAddr = this.clubAddr;
      }
      
      this.myAddr = res.data.useraccount;
      
      this.saveHistory();
      
      this.sendToken();

    },
    updatePK(event){
      var fileList = event.target.files;
      var file = fileList[0];
      var reader = new FileReader();
      reader.onload = function(){
        this.privateKey = reader.result;
        console.log('privateKey ',this.privateKey);
      }.bind(this)
      reader.readAsText(file);
    },
    async sendToken(){
      console.log("송신: ",this.myAddr)
      console.log("수신: ",this.toAddr)
      console.log(this.privateKey);
      if (this.privateKey == ""){
        alert("인증키를 입력해주세요")
      }
      const Tx = require('ethereumjs-tx').Transaction;
      var web3 = new Web3(new Web3.providers.HttpProvider("https://ropsten.infura.io/v3/d2f03576222c4c2fbc5eeb6850f9abf3"));
      var contract = new web3.eth.Contract(this.abi,this.contractAddr,{from: this.myAddr}); //보내는사람 주소
      let privKey= new Buffer.from(this.privateKey, "hex");
      
      console.log("privateKey = ",privKey);
      console.log("myAddr ",this.myAddr);
      

      web3.eth.getTransactionCount(this.myAddr,(err,txCount)=>{ //보내는 주소
        const txObject = {
          'from':this.myAddr, //보내는 주소
          'nonce': web3.utils.toHex(txCount),
          'gasLimit': web3.utils.toHex(1000000),
          'gasPrice': web3.utils.toHex(web3.utils.toWei('10','gwei')),
          'to': this.contractAddr, //계약 주소
          'value': '0x0',
          'data': contract.methods.transfer(this.toAddr,this.value).encodeABI() //받는 주소, 토큰 갯수
        }
        let transaction = new Tx(txObject,{'chain':'ropsten'});
        transaction.sign(privKey);
        web3.eth.sendSignedTransaction('0x'+transaction.serialize().toString('hex'))
        .on('transactionHash',console.log)
      })
    },
    async saveHistory()
    {
      console.log(this.myAddr);
      console.log(typeof(this.myAddr));
      console.log('value type ',typeof(this.value));

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

     let from = this.myAddr;
     let to = this.toAddr;
     let val = this.value *1;
     let msg = this.message;

      const Tx = require('ethereumjs-tx').Transaction;
      let privKey= new Buffer.from("27ddaa90db29f7740736e57703c437595a6f62707aa53d90773cb3fb4c91282d", "hex");

      web3.eth.getTransactionCount("0x97415060E1Ff0d2c51BF6d92B959be7D6316a983",(err,txCount)=>{ //보내는 주소
        const txObject = {
          'from':'0x97415060E1Ff0d2c51BF6d92B959be7D6316a983', //보내는 주소
          'nonce': web3.utils.toHex(txCount),
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