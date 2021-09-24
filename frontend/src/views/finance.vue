<template>
  <div class="finance">
    <!-- 상단바도 컴포넌트로 두고 전환해야 하나? -->
    <FinanceInfo />
    <!-- <v-if></v-if> -->
    <!-- if로 컴포넌트 전환?  -->
    <TransactionHistory />
    <Transaction />
    <Footer />
    <!-- footer 어따 달지? -->
  </div>
</template>

<script>
import FinanceInfo from "@/components/finance/financeinfo.vue";
import TransactionHistory from "@/components/finance/transactionHistory.vue";
import Transaction from "@/components/finance/transaction.vue";
import Footer from "@/components/footer/footer.vue";
export default {
  components: {
    FinanceInfo,
    TransactionHistory,
    Transaction,
    Footer,
  },
}
</script>
<style scoped>
.finance {
  height: 100%;
}
</style>