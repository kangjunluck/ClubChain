<template>
  <div>
		<!-- <div>
      <i class="fas fa-wallet fa-lg"></i>
      <i class="fas fa-comment-dollar fa-lg"></i>
      <i class="fas fa-image fa-lg"></i>
      <i class="far fa-images fa-lg"></i>
      <i class="fas fa-images fa-lg"></i>
      <i class="fas fa-user fa-lg"></i>
      <i class="far fa-user fa-lg "></i>
      <i class="fas fa-user-circle fa-lg"></i>
      <i class="fas fa-edit fa-lg"></i>
    </div>
    <div>
      <i class="fas fa-wallet fa-lg"></i>
      <i class="far fa-images fa-lg"></i>
      <i class="far fa-user fa-lg "></i>
      <i class="fas fa-edit fa-lg"></i>
    </div> -->
    <div>
      <i class="fas fa-wallet fa-lg walletIcon" @click="moveToFinance"></i>
      <i class="fas fa-edit fa-lg" @click="moveToWrite"></i>
      <i class="fas fa-images fa-lg" @click="moveToImage"></i>
      <i class="fas fa-user fa-lg myPageIcon" @click="moveToMyPage"></i>
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
      console.log('글쓰기로 이동')
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
.myPageIcon {
  font-size: 1.25rem;
}
</style>