<template>
  <div class="finanaceInfo">
    <div style="background-color: #0080FF; height:30px; color:#ffffff; font-size:1rem;">회비관리</div>
    <div style="height:40px;"></div>
    <div class="clubname" style="font-size: 1rem" >{{clubname}}</div>
    <b-container style="" class="infomation">
      <b-row>
        <b-col cols="1" align-self="center">◀</b-col>
        <b-col cols="10" class="ethCard">
          <div style="font-size: 0.8rem;">{{ myAccountNumber}} </div>
          <div></div>
          <div style="font-size: 3rem">잔고: {{balance}} <img src="@/assets/gold.png" width="40" height="40" /> </div>
          
          <div>
            <span class="button1" @click="transactionHistoryButton">거래내역</span>
            <span class="button1" @click="transferButton">이체</span>

            <span class="button2" @click="encharge">충전</span>
          </div>
        </b-col> 
        <b-col cols="1" align-self="center">▶</b-col>
      </b-row>
     <div style="height:20px;"></div>
    <Transaction v-if= "componenetStateValue==='transfer'"/>
    <TransactionHistory v-else-if= "componenetStateValue==='transactionHistory'" v-bind:hst="myhistory" v-bind:cst="clubhistory"
    v-bind:balance="balance" />
    <div v-else v-for="(item,index) in myhistory" v-bind:key="index">
      {{item.message}}
    </div>
    </b-container>
  </div>

<!-- 개인 계좌 불러오기 (api 요청) -->
<!-- 키 불러오기 -->
</template>



<script>
import http from "@/util/http-common";
import Web3 from "web3";
import Transaction from './transaction.vue';
import TransactionHistory from './transactionHistory.vue';
export default {
  name: "FinanceInfo",
  data: function () {
    return {
      componenetStateValue: "",
      myAccountNumber: "",
      contractAbi:"",
      contractAddr:"",
      balance:"",
      history:[],
      myhistory:[],
      clubhistory:[],
      clubname:"",
    }
  },
  components: {
    Transaction,
    TransactionHistory
  },
  props: {
    componenetState: {
      type: String,
    }
  },
  created: function () {
    // var email = this.$store.state.credentials.userEmail.split('@');
    // let emailAddr = email[0]+"%40"+email[1];
    let email = encodeURI(this.$store.state.credentials.userEmail)
    const url = "api/users/" + email;
    http
      .get(url)
      .then((res) => {
        this.myAccountNumber = res.data.useraccount;
        //잔액 조회
        var web3 = new Web3(new Web3.providers.HttpProvider("https://ropsten.infura.io/v3/d2f03576222c4c2fbc5eeb6850f9abf3"));
        let contractAddr = '0xbC9e278a4F4ceAb626324Cf9cF1D7ea46F469dB3';
        let abi = [
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
                  ]
      var contract = new web3.eth.Contract(abi,contractAddr); 
        contract.methods.balanceOf(this.myAccountNumber).call() // this.myAccountNumber
        .then(data => {
          this.contractAbi = abi;
          this.contractAddr = contractAddr;
          this.balance = data;
        })

      contract.methods.loadRecode().call()
      .then(data =>{
        this.history = data;
        let temp = [];
        for(var i in data)
        {
          if(data[i].fromAddr == this.myAccountNumber || data[i].toAddr == this.myAccountNumber)
          {
            temp.push(data[i]);
            // this.myhistory.push(data[i]);
          }
        }
        this.myhistory = temp.reverse();
      })

      })
      .catch((error) => {
        console.log(error)
        this.myAccountNumber = error
      });
  },
  methods: {
    transactionHistoryButton() {

      if(this.componenetStateValue !="transactionHistory")
      {
        this.componenetStateValue = "transactionHistory"
        // this.$emit("componenetState", this.componenetStateValue);
      }
      else{
        this.componenetStateValue = "";
      }
    },
    transferButton() {
      if(this.componenetStateValue != "transfer")
      {
        this.componenetStateValue = "transfer";
        // this.$emit("componenetState", this.componenetStateValue);
      }
      else
      {
        this.componenetStateValue = "";
      }
      
    },
    encharge()
    {
      const Tx = require('ethereumjs-tx').Transaction;
      var web3 = new Web3(new Web3.providers.HttpProvider("https://ropsten.infura.io/v3/d2f03576222c4c2fbc5eeb6850f9abf3"));
      var contract = new web3.eth.Contract(this.contractAbi,this.contractAddr,{from: '0x97415060E1Ff0d2c51BF6d92B959be7D6316a983'}); //보내는사람 주소
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
          'data': contract.methods.transfer(this.myAccountNumber,100).encodeABI() //받는 주소, 토큰 갯수
        }
        let transaction = new Tx(txObject,{'chain':'ropsten'});
        transaction.sign(privKey);
        web3.eth.sendSignedTransaction('0x'+transaction.serialize().toString('hex'))
        .on('transactionHash',console.log)
      })
    }
  }
}
</script>
<style scoped>
/* 왜 scoped 하면 적용 안되지? */
/* 로컬스토리지 날리면 적용됨 */
.clubname {
  text-align: left;
  padding: 0 0 0 9%;
  margin: 0 0 0.1rem 0;
  font-size: 1.5rem;
}
.ethCard {
  background-color: #1EC0FF;
  height: 10rem;
  border-radius: 0.5rem;
  color: #FFFFFF;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.button1 {
  margin: 0 2rem 0 0;
}
.button2 {
  margin: 0 0 0 2 rem;
}
</style>