<template>
  <div>
      토큰전송페이지
      <div class="btn btn-primary" @click="enchargeToken">
            토큰충전
      </div>
      <div class="btn btn-primary" @click="balanceToken">
            잔액확인
      </div>
  </div>
</template>
 
<script>
import Web3 from "web3";
// import Tx from 'ethereumjs-tx';
export default {
  data() {
    return {
      contractAddr:"0x856638064bdecb3cbb3329dc8e3c083f0726218d",
      abi:[
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
    };
  },
  methods:{
    balanceToken(){
      var web3 = new Web3(new Web3.providers.HttpProvider("https://ropsten.infura.io/v3/d2f03576222c4c2fbc5eeb6850f9abf3"));
      var contract = new web3.eth.Contract(this.abi,this.contractAddr); 

      contract.methods.balanceOf("0x97415060E1Ff0d2c51BF6d92B959be7D6316a983").call()
      .then(data => {
        console.log(data);
      })
    },
    async enchargeToken()
    {
      const Tx = require('ethereumjs-tx').Transaction;
      var web3 = new Web3(new Web3.providers.HttpProvider("https://ropsten.infura.io/v3/d2f03576222c4c2fbc5eeb6850f9abf3"));
      var contract = new web3.eth.Contract(this.abi,this.contractAddr,{from: '0x50dEEFBA64329a6377960325e6e9b9DC6ce6Bc81'}); //보내는사람 주소
      let privKey_= "cf0f0a64538a8a005532d195605d334f02d6a89fde64f068f5a2d239531986e7"; // 보내는사람의 개인키
      let privKey= new Buffer.from(privKey_, "hex");
      console.log("privateKey = ",privKey);
      web3.eth.getTransactionCount("0x50dEEFBA64329a6377960325e6e9b9DC6ce6Bc81",(err,txCount)=>{ //보내는 주소
        const txObject = {
          'from':'0x50dEEFBA64329a6377960325e6e9b9DC6ce6Bc81', //보내는 주소
          'nonce': web3.utils.toHex(txCount),
          'gasLimit': web3.utils.toHex(1000000),
          'gasPrice': web3.utils.toHex(web3.utils.toWei('10','gwei')),
          'to': this.contractAddr, //계약 주소
          'value': '0x0',
          'data': contract.methods.transfer('0x9317eE31aCEB52365e4c9ED6b17FC436756A8169',10).encodeABI() //받는 주소, 토큰 갯수
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

<style>

</style>