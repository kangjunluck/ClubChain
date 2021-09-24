<template>
  <div class="container">
    <div class="historyTitle">최근거래</div>
    <div>최근 거래 테이블</div>
  </div>
</template>

<script>
export default {
  name: "TransactionHistory",
}
</script>

<style scoped>
.container {
  background-color: teal;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.historyTitle {
  text-align: left;
}
</style>