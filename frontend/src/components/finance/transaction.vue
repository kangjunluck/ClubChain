<template>
  <div>
    <b-container>
      <b-row>
        <b-col offset="1" cols="10" class="ethCard">
          <b-row>
            <b-col>받는사람</b-col>
            <b-col><b-form-input v-model="toEmail"></b-form-input></b-col>
          </b-row>
          <b-row>
            <b-col>송금액</b-col>
            <b-col><b-form-input v-model="value"></b-form-input></b-col>
          </b-row>
          <b-row>
            <b-col>수수료</b-col>
          </b-row>
          <div class="btn btn-primary" @click="sendTx">
            송금
          </div>
        </b-col>
      </b-row>
    </b-container>
    
  </div>
</template>

<script>
import Web3 from "web3";
import http from "@/util/http-common";
export default {
  name: "Transaction",
  data() {
    return {  
      abi: [
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
      ],
      contractAddr:"0x856638064bdecb3cbb3329dc8e3c083f0726218d",
      myAddr:"",
      privateKey:"",
      toEmail:"",
      toAddr:"",
      value:"",
    };
  },

  methods:{
    async sendTx()
    {
      const res = await http.get("api/users/"+ encodeURI(this.$store.state.credentials.userEmail));
      const res2 = await http.get("api/users/"+this.toEmail);

      this.myAddr = res.data.useraccount;
      this.toAddr = res2.data.useraccount;

      this.sendToken();

    },

    async sendToken(){
      // await this.getAccount(encodeURI(this.$store.state.credentials.userEmail),this.toEmail)

      console.log("송신: ",this.myAddr)
      console.log("수신: ",this.toAddr)

      const Tx = require('ethereumjs-tx').Transaction;
      var web3 = new Web3(new Web3.providers.HttpProvider("https://ropsten.infura.io/v3/d2f03576222c4c2fbc5eeb6850f9abf3"));
      var contract = new web3.eth.Contract(this.abi,this.contractAddr,{from: this.myAddr}); //보내는사람 주소
      let privKey_= "3b0935331ba86137cd0b4c304af778aa267d736cf91b3031811514d60b34226c"; // 보내는사람의 개인키
      let privKey= new Buffer.from(privKey_, "hex");
      
      console.log("privateKey = ",privKey);
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
      });
    }
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