<template>
  <div class="finanaceInfo">
    <div class="clubname">동아리 이름</div>
    <b-container class="infomation">
      <b-row>
        <b-col cols="1" align-self="center">◀</b-col>
        <b-col cols="10" class="ethCard">
          <div>계좌번호 : {{ myAccountNumber}} </div>
          <div></div>
          <div style="font-size: 3rem">잔고: {{balance}} Token</div>
          
          <div>
            <span class="button1" @click="transactionHistoryButton">거래내역</span>
            <span class="button1" @click="transferButton">이체</span>

            <span class="button2" @click="encharge">충전</span>
          </div>
        </b-col> 
        <b-col cols="1" align-self="center">▶</b-col>
      </b-row>
    </b-container>
  </div>

<!-- 개인 계좌 불러오기 (api 요청) -->
<!-- 키 불러오기 -->
</template>



<script>
import http from "@/util/http-common";
import Web3 from "web3";
export default {
  name: "FinanceInfo",
  data: function () {
    return {
      componenetStateValue: "",
      myAccountNumber: "",
      contractAbi:"",
      contractAddr:"",
      balance:"",
    }
  },
  props: {
    // eslint-disable-next-line vue/no-dupe-keys
    componenetState: {
      type: String,
    }
  },
  created: function () {
    // var email = this.$store.state.credentials.userEmail.split('@');
    // let emailAddr = email[0]+"%40"+email[1];
    let email = encodeURI(this.$store.state.credentials.userEmail)
    const url = "api/users/" + email;
    console.log(url)
    http
      .get(url)
      .then((res) => {
        console.log(res);
        this.myAccountNumber = res.data.useraccount;
        //잔액 조회
        var web3 = new Web3(new Web3.providers.HttpProvider("https://ropsten.infura.io/v3/d2f03576222c4c2fbc5eeb6850f9abf3"));
        let contractAddr = '0x856638064bdecb3cbb3329dc8e3c083f0726218d';
        let abi = [
        {
          "constant": true,
          "inputs": [],
          "name": "name",
          "outputs": [
            {
              "name": "",
              "type": "string"
            }
          ],
          "payable": false,
          "stateMutability": "view",
          "type": "function"
        },
        {
          "constant": false,
          "inputs": [
            {
              "name": "spender",
              "type": "address"
            },
            {
              "name": "tokens",
              "type": "uint256"
            }
          ],
          "name": "approve",
          "outputs": [
            {
              "name": "success",
              "type": "bool"
            }
          ],
          "payable": false,
          "stateMutability": "nonpayable",
          "type": "function"
        },
        {
          "constant": true,
          "inputs": [],
          "name": "totalSupply",
          "outputs": [
            {
              "name": "",
              "type": "uint256"
            }
          ],
          "payable": false,
          "stateMutability": "view",
          "type": "function"
        },
        {
          "constant": false,
          "inputs": [
            {
              "name": "from",
              "type": "address"
            },
            {
              "name": "to",
              "type": "address"
            },
            {
              "name": "tokens",
              "type": "uint256"
            }
          ],
          "name": "transferFrom",
          "outputs": [
            {
              "name": "success",
              "type": "bool"
            }
          ],
          "payable": false,
          "stateMutability": "nonpayable",
          "type": "function"
        },
        {
          "constant": true,
          "inputs": [],
          "name": "decimals",
          "outputs": [
            {
              "name": "",
              "type": "uint8"
            }
          ],
          "payable": false,
          "stateMutability": "view",
          "type": "function"
        },
        {
          "constant": true,
          "inputs": [],
          "name": "_totalSupply",
          "outputs": [
            {
              "name": "",
              "type": "uint256"
            }
          ],
          "payable": false,
          "stateMutability": "view",
          "type": "function"
        },
        {
          "constant": true,
          "inputs": [
            {
              "name": "tokenOwner",
              "type": "address"
            }
          ],
          "name": "balanceOf",
          "outputs": [
            {
              "name": "balance",
              "type": "uint256"
            }
          ],
          "payable": false,
          "stateMutability": "view",
          "type": "function"
        },
        {
          "constant": true,
          "inputs": [],
          "name": "symbol",
          "outputs": [
            {
              "name": "",
              "type": "string"
            }
          ],
          "payable": false,
          "stateMutability": "view",
          "type": "function"
        },
        {
          "constant": true,
          "inputs": [
            {
              "name": "a",
              "type": "uint256"
            },
            {
              "name": "b",
              "type": "uint256"
            }
          ],
          "name": "safeSub",
          "outputs": [
            {
              "name": "c",
              "type": "uint256"
            }
          ],
          "payable": false,
          "stateMutability": "view",
          "type": "function"
        },
        {
          "constant": false,
          "inputs": [
            {
              "name": "to",
              "type": "address"
            },
            {
              "name": "tokens",
              "type": "uint256"
            }
          ],
          "name": "transfer",
          "outputs": [
            {
              "name": "success",
              "type": "bool"
            }
          ],
          "payable": false,
          "stateMutability": "nonpayable",
          "type": "function"
        },
        {
          "constant": true,
          "inputs": [
            {
              "name": "a",
              "type": "uint256"
            },
            {
              "name": "b",
              "type": "uint256"
            }
          ],
          "name": "safeDiv",
          "outputs": [
            {
              "name": "c",
              "type": "uint256"
            }
          ],
          "payable": false,
          "stateMutability": "view",
          "type": "function"
        },
        {
          "constant": false,
          "inputs": [
            {
              "name": "spender",
              "type": "address"
            },
            {
              "name": "tokens",
              "type": "uint256"
            },
            {
              "name": "data",
              "type": "bytes"
            }
          ],
          "name": "approveAndCall",
          "outputs": [
            {
              "name": "success",
              "type": "bool"
            }
          ],
          "payable": false,
          "stateMutability": "nonpayable",
          "type": "function"
        },
        {
          "constant": true,
          "inputs": [
            {
              "name": "a",
              "type": "uint256"
            },
            {
              "name": "b",
              "type": "uint256"
            }
          ],
          "name": "safeMul",
          "outputs": [
            {
              "name": "c",
              "type": "uint256"
            }
          ],
          "payable": false,
          "stateMutability": "view",
          "type": "function"
        },
        {
          "constant": true,
          "inputs": [
            {
              "name": "tokenOwner",
              "type": "address"
            },
            {
              "name": "spender",
              "type": "address"
            }
          ],
          "name": "allowance",
          "outputs": [
            {
              "name": "remaining",
              "type": "uint256"
            }
          ],
          "payable": false,
          "stateMutability": "view",
          "type": "function"
        },
        {
          "constant": true,
          "inputs": [
            {
              "name": "a",
              "type": "uint256"
            },
            {
              "name": "b",
              "type": "uint256"
            }
          ],
          "name": "safeAdd",
          "outputs": [
            {
              "name": "c",
              "type": "uint256"
            }
          ],
          "payable": false,
          "stateMutability": "view",
          "type": "function"
        },
        {
          "inputs": [],
          "payable": false,
          "stateMutability": "nonpayable",
          "type": "constructor"
        },
        {
          "payable": true,
          "stateMutability": "payable",
          "type": "fallback"
        },
        {
          "anonymous": false,
          "inputs": [
            {
              "indexed": true,
              "name": "from",
              "type": "address"
            },
            {
              "indexed": true,
              "name": "to",
              "type": "address"
            },
            {
              "indexed": false,
              "name": "tokens",
              "type": "uint256"
            }
          ],
          "name": "Transfer",
          "type": "event"
        },
        {
          "anonymous": false,
          "inputs": [
            {
              "indexed": true,
              "name": "tokenOwner",
              "type": "address"
            },
            {
              "indexed": true,
              "name": "spender",
              "type": "address"
            },
            {
              "indexed": false,
              "name": "tokens",
              "type": "uint256"
            }
          ],
          "name": "Approval",
          "type": "event"
        }
      ]
      var contract = new web3.eth.Contract(abi,contractAddr); 
        contract.methods.balanceOf(this.myAccountNumber).call() // this.myAccountNumber
        .then(data => {
          console.log(data);
          this.contractAbi = abi;
          this.contractAddr = contractAddr;
          this.balance = data;
        })
      })
      .catch((error) => {
        console.log(error)
        this.myAccountNumber = error
      });
  },
  methods: {
    transactionHistoryButton() {
      this.componenetStateValue = "transactionHistory"
      console.log(this.componenetStateValue)
      this.$emit("componenetState", this.componenetStateValue);
    },
    transferButton() {
      this.componenetStateValue = "transfer";
      console.log(this.componenetStateValue);
      this.$emit("componenetState", this.componenetStateValue);
    },
    encharge()
    {
      const Tx = require('ethereumjs-tx').Transaction;
      var web3 = new Web3(new Web3.providers.HttpProvider("https://ropsten.infura.io/v3/d2f03576222c4c2fbc5eeb6850f9abf3"));
      var contract = new web3.eth.Contract(this.contractAbi,this.contractAddr,{from: '0x97415060E1Ff0d2c51BF6d92B959be7D6316a983'}); //보내는사람 주소
      let privKey_= "27ddaa90db29f7740736e57703c437595a6f62707aa53d90773cb3fb4c91282d"; // 보내는사람의 개인키
      let privKey= new Buffer.from(privKey_, "hex");
      
      console.log("privateKey = ",privKey);
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
/* .infomation >>> .ethCard { */
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