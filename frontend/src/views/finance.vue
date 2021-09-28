<template>
  <div class="finance">
    <!-- 상단바도 컴포넌트로 두고 전환해야 하나? -->
    <div>회비 관리 상단바</div>
    <MyFiananceInfoLight v-if="componenetStateEmit==='transactionHistory' ||componenetStateEmit === 'transfer' "/>
    <FinanceInfo @componenetState="componenetState" v-if="componenetStateEmit===''"/>
    <!-- <div v-if="componenetStateEmit===''">여기에 최근 거래 몇개 보여줘야 함</div> -->
    <TransactionHistory v-if= "componenetStateEmit==='transactionHistory'" />
    <Transaction v-if= "componenetStateEmit==='transfer'" />
    <Footer />
    <!-- footer 어따 달지? -->
  </div>
</template>

<script>
import FinanceInfo from "@/components/finance/financeinfo.vue";
import TransactionHistory from "@/components/finance/transactionHistory.vue";
import Transaction from "@/components/finance/transaction.vue";
import Footer from "@/components/footer/footer.vue";
import MyFiananceInfoLight from "@/components/finance/myFiananceInfoLight.vue";
// import http from "@/util/http-common";
export default {
  name: "Finance",
  data: function () {
    return {
      componenetStateEmit: "",
    }
  },
  methods: {
    // eslint-disable-next-line vue/no-dupe-keys
    componenetState(componenetState) {
      this.componenetStateEmit = componenetState;
    }
  },

  components: {
    FinanceInfo,
    TransactionHistory,
    Transaction,
    Footer,
    MyFiananceInfoLight
  },
}
</script>
<style scoped>
.finance {
  height: 100%;
}
</style>