<template>
  <div class="finanaceInfo">
    <b-container class="info_container">
      <b-row class="info_row">
        <b-col class="info-nav-box">
            지갑 관리
        </b-col>
      </b-row>
    </b-container>
    <div class="clubname" style="font-size: 1.2rem; font-weight:500; margin:2vw 0 2vw 0;" >{{clubname}}</div>
    <b-container style="" class="">
      <b-row>
        <b-col cols="1" align-self="center"></b-col>
        <b-col cols="10" class="ethCard">
          <div style="font-size: 0.8rem; width:100%; overflow:hidden;">{{ myAccountNumber}} </div>
          <div>
            <div style="font-size: 2rem">잔고: {{balance}} <i class="fas fa-coins"></i>&nbsp;</div>
          </div>
          
          <div class="d-flex justify-content-between" style="padding:0 10vw 0 10vw">
            <span class="button1" @click="transactionHistoryButton">거래내역</span>
            <span class="button1" @click="transferButton">이체</span>
            <span class="button2" @click="tokenEnchargeButton">충전</span>
          </div>
        </b-col> 
        <b-col cols="1" align-self="center"></b-col>
      </b-row>
    <div style="height:20px;"></div>
    <Transaction v-if= "componenetStateValue==='transfer'" v-bind:clubAddr="clubAddr" />
    <TransactionHistory v-else-if= "componenetStateValue==='transactionHistory'" v-bind:balance="balance" v-bind:clubAddr="clubAddr"  v-bind:hst="myhistory" v-bind:cst="clubhistory" />
    <Encharge v-else-if="componenetStateValue==='tokenEncharge'" v-bind:nickname="myNickName" v-bind:abi="contractAbi" v-bind:contractAddr="contractAddr" 
    v-bind:myAddr="myAccountNumber" />
    <div v-else>
      <b-row>
        <b-col style="font-size:1.2rem; text-align: left; font-weight: bolder; 
            margin-left: 12px; ">최근거래</b-col>
      </b-row>
    <div style="height:20px;"></div>
      <div v-for="(item,index) in myhistory.slice(0,5)" v-bind:key="index">
        <b-row>
          <b-col style="font-size:1rem;">{{item.message}}</b-col>
          <b-col style="font-size:1rem;" v-if="item.fromAddr == myAccountNumber">- {{item.value}} CC</b-col>
          <b-col style="font-size:1rem;" v-else>+ {{item.value}} CC</b-col>
        </b-row>
        <b-row>
          <b-col style="font-size:0.8rem; color:#999999">{{item.date.substring(0,24)}}</b-col>
          <b-col style="font-size:0.8rem; color:#999999">{{balances[index]}} CC</b-col>
        </b-row>
        <hr style=" color:#333333; margin: 0.3em;">
      </div>
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
import Encharge from './encharge.vue';
export default {
  name: "FinanceInfo",
  data: function () {
    return {
      componenetStateValue: "",
      myAccountNumber: "",
      myNickName:"",
      contractAbi:"",
      contractAddr:"",
      balance:"",
      balances:[],
      history:[],
      myhistory:[],
      clubhistory:[],
      clubname:"",
      clubAddr:"",
    }
  },
  components: {
    Transaction,
    TransactionHistory,
    Encharge,
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
        this.myNickName = res.data.usernickname;
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

      let url = "api/club/{clubid}?clubid=" + this.$store.state.selectedClub;

      http.get(url)
      .then(res =>{
        this.clubname = res.data.name;
        this.clubAddr = res.data.clubaccount;
      })

      contract.methods.loadRecode().call()
      .then(data =>{
        this.history = data;
        let temp = [];
        let temp2 = [];
        for(var i in data)
        {
          if(data[i].fromAddr == this.myAccountNumber || data[i].toAddr == this.myAccountNumber)
          {
            temp.push(data[i]);
            // this.myhistory.push(data[i]);
          }
          if(data[i].fromAddr == this.clubAddr || data[i].toAddr == this.clubAddr)
          {
            temp2.push(data[i]);
            // this.myhistory.push(data[i]);
          }
          
        }
        this.myhistory = temp.reverse();
        this.clubhistory = temp2.reverse();
        // console.log(this.myhistory)
        // console.log('동호회 거래내역',this.clubhistory);

        this.balances.push(this.balance)
        for (let i = 0; i < this.myhistory.length; i++){
          if(i==0)
          {
            if(this.myhistory[i].fromAddr == this.myAddr)
            {
              this.balances.push(this.myhistory[i].value*1 + this.balance*1)
            }
            else
              this.balances.push(this.balance*1 - this.myhistory[i].value*1)
          }
          else
          {
            if(this.myhistory[i].fromAddr == this.myAddr)
            {
              this.balances.push(this.balances[i]*1 + this.myhistory[i].value*1)
            }
            else
              this.balances.push(this.balances[i]*1 - this.myhistory[i].value*1)
          }
        }
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
    tokenEnchargeButton()
    {
      if(this.componenetStateValue != "tokenEncharge")
      {
        this.componenetStateValue = "tokenEncharge";
        console.log(this.componenetStateValue);
        // this.$emit("componenetState", this.componenetStateValue);
      }
      else
      {
        this.componenetStateValue = "";
      }
    }
  }
}
</script>
<style scoped>
/* 왜 scoped 하면 적용 안되지? */
/* 로컬스토리지 날리면 적용됨 */
.info_container{
  margin: 0 0 0 0;
  padding: 0 0 0 0;
  min-width: 100%
}
.info_row {
  margin: 0 0 0 0;
  padding: 0 0 0 0;
  font-size: 1.7rem;
}
.info-nav-box{
  height: 3rem;
  background-color: #0080FF;
  color : #fff;
  display: flex;
  flex-direction: column;
  justify-content: center;
  text-align: left;
  margin: 0 0 0 0;
  padding: 0 0 0 5%;
}
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
  margin: 0 0 0 0;
}
.button2 {
  margin: 0 0 0 0;
}
</style>