<template>
  <div class="finanaceInfo">
    <div>회비관리</div>
    <div class="clubname">동아리 이름</div>
    <b-container class="infomation">
      <b-row>
        <b-col cols="1" align-self="center">◀</b-col>
        <b-col cols="10" class="ethCard">
          <div>356계좌번호04SDF64SFX</div>
          <div style="font-size: 3rem">잔고 ETH</div>
          <div>
            <span class="button1" @click="transactionHistoryButton">거래내역</span
            ><span class="button2" @click="transferButton">이체</span>
          </div>
        </b-col> 
        <b-col cols="1" align-self="center">▶</b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
export default {
  name: "FinanceInfo",
  data: function () {
    return {
      componenetStateValue: "",
    }
  },
  props: {
    // eslint-disable-next-line vue/no-dupe-keys
    componenetState: {
      type: String,
    }
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