<template>
  <b-container class="container" style="background-color:red;">
    
    <b-row style="background-color:yellow;" >
      <b-col class="d-flex justify-content-between" offset="1" cols="10">
        <div>
          내 계좌 <input type="checkbox" id="checkbox1" v-model="my" @click="check1">
        </div>
        <div>
          동호회 계좌 <input type="checkbox" id="checkbox2" v-model="club" @click="check2">
        </div>
      </b-col>
    </b-row>
    <div style="height:3vw; background-color:black;"></div>
    <div v-if="my==true">
      <div v-for="(item,index) in hst" v-bind:key="index">
        <!-- <b-row>
          <b-col style="font-size:1rem; overflow: hidden; white-space: nowrap;">{{item.message}}</b-col>
          <b-col style="font-size:1rem;" v-if="item.fromAddr == myAddr">- {{item.value}} CC</b-col>
          <b-col style="font-size:1rem;" v-else>+ {{item.value}} CC</b-col>
        </b-row> -->
        <b-row>
          <b-col style="font-size:1rem;" cols="6"><div style="font-size:1rem; overflow: hidden; white-space: nowrap;">{{item.message}}</div></b-col>
          <b-col style="font-size:1rem;" v-if="item.fromAddr == myAddr">- {{item.value}} CC</b-col>
          <b-col style="font-size:1rem;" v-else>+ {{item.value}} CC</b-col>
        </b-row>
        <b-row>
          <b-col style="font-size:0.8rem; color:#999999">{{item.date.substring(0,24)}}</b-col>
          <b-col style="font-size:0.8rem; color:#999999">{{balances[index]}} CC</b-col>
        </b-row>
        <hr style=" color:#333333; margin: 0.3em;">
      </div>
    </div>
    <div v-else-if="club==true">
            <div v-for="(item,index) in cst" v-bind:key="index">
        <b-row>
          <b-col style="font-size:1rem;">{{item.message}}</b-col>
          <b-col style="font-size:1rem;" v-if="item.fromAddr == clubAddr">- {{item.value}} CC</b-col>
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
  
</template>

<script>
import http from "@/util/http-common";
export default {
  name: "TransactionHistory",
  props: ["hst","cst","balance","clubAddr"],
  data() {
    return {
      balances:[],
      myAddr:"",
      my:true,
      club:false,
    };
  },
  created(){
    let email = encodeURI(this.$store.state.credentials.userEmail)
    const url = "api/users/" + email;
    http
      .get(url)
      .then(res=>{
        this.myAddr = res.data.useraccount;

        this.balances.push(this.balance)
        for (let i = 0; i < this.hst.length; i++){
          if(i==0)
          {
            if(this.hst[i].fromAddr == this.myAddr)
            {
              this.balances.push(this.hst[i].value*1 + this.balance*1)
            }
            else
              this.balances.push(this.balance*1 - this.hst[i].value*1)
          }
          else
          {
            if(this.hst[i].fromAddr == this.myAddr)
            {
              this.balances.push(this.balances[i]*1 + this.hst[i].value*1)
            }
            else
              this.balances.push(this.balances[i]*1 - this.hst[i].value*1)
          }
        }
      })
  },
  methods:{
    check1(){
      this.club = false;
    },
    check2(){
      this.my = false;
      this.balances= [];
      let btemp = [];
      let temp = [];
      this.cst.forEach(item => {
        temp.push(item);
      });
      console.log(temp);

      btemp.push(temp[0].value);

      for (let i = 1; i < temp.length; i++) {
        if(temp[i].fromAddr == this.clubAddr)
        {
          btemp.push(btemp[i-1]*1 - temp[i].value*1);
        }
        else
        {
          btemp.push(btemp[i-1]*1 + temp[i].value*1);
        }
      }
      this.balances = btemp.reverse();
    }
  }
}
</script>

<style scoped>
.container {
  /* background-color: teal; */
  display: flex;
  flex-direction: column;
  /* justify-content: center; */
}
.historyTitle {
  text-align: left;
}
</style>