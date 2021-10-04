<template>
  <div class="container">
    <div v-for="(item,index) in hst" v-bind:key="index">
      <b-row>
        <b-col style="font-size:1rem;">{{item.message}}</b-col>
        <b-col style="font-size:1rem;" v-if="item.fromAddr == myAddr">- {{item.value}} CC</b-col>
        <b-col style="font-size:1rem;" v-else>+ {{item.value}}</b-col>
      </b-row>
    </div>
  </div>
  
</template>

<script>
import http from "@/util/http-common";
export default {
  name: "TransactionHistory",
  props: ["hst","cst"],
  data() {
    return {
      balance:"",
      myAddr:"",
    };
  },
  created(){
    let email = encodeURI(this.$store.state.credentials.userEmail)
    const url = "api/users/" + email;
    console.log(url)
    http
      .get(url)
      .then(res=>{
        this.myAddr = res.data.useraccount;
      })
  }
}
</script>

<style scoped>
.container {
  /* background-color: teal; */
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.historyTitle {
  text-align: left;
}
</style>