<template>
  <div id="footer">
    <div class="d-flex justify-content-around h-100 align-items-center">
      <p class="fas fa-wallet fa-lg walletIcon" @click="moveToFinance"></p>
      <p class="fas fa-edit fa-lg" @click="moveToWrite"></p>
      <p class="fas fa-images fa-lg" @click="moveToImage"></p>
      <p class="fas fa-user fa-lg myPageIcon" @click="moveToMyPage"></p>
    </div>
  </div>
</template>

<script>
export default {
	name: "Footer",
  methods: {
    moveToFinance() {
      console.log('계좌로이동')
      console.log(this.$route.path)
      if(this.$route.path!=='/club/finance') {this.$router.push("finance")}
    },
    moveToWrite() {
      this.$router.push("/club/post/write");
    },
    moveToImage() {
      console.log('사진첩으로 이동')
    },
    moveToMyPage() {
      console.log('마이페이지로 이동')
    },
  }
}
</script>

<style scoped>
.walletIcon {
}
.fas {
  font-size: 2rem;
  margin: 0;
}
#footer {
  position: fixed;
  bottom: 0;
  height: 4rem;
  width: 100%;
  background-color: rgb(219, 234, 253);
}
</style>