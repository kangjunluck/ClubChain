<template>
  <div id="footer">
    <div class="d-flex justify-content-around h-100 align-items-center">
      <p class="fas fa-home fa-lg" @click="moveToHome"></p>
      <p class="fas fa-wallet fa-lg walletIcon" :class="{select: onwallet}" @click="moveToFinance"></p>
      <p class="fas fa-edit fa-lg" :class="{select: onedit}" @click="moveToWrite"></p>
      <p class="fas fa-images fa-lg" :class="{select: onimage}" @click="moveToImage"></p>
      <p class="fas fa-user fa-lg myPageIcon" :class="{select: onuser}" @click="moveToMyPage"></p>
    </div>
  </div>
</template>

<script>
import $ from "jquery";
export default {
	name: "Footer",
  props: ['footernum'],
  data() {
    return {
      onwallet : false,
      onedit : false,
      onimage : false,
      onuser : false,
    };
  },
  methods: {
    moveToHome() {
      this.$router.push("/club/ClubMain")
      console.log('올라가!')
      $( 'html, body' ).animate( { scrollTop : 0 }, 400 ); 
    },
    moveToFinance() {
      console.log('계좌로이동')
      console.log(this.$route.path)
      this.makeinit()
      this.onwallet = true;
      console.log(this.onwallet+"11111111111")
      this.$router.push("/club/finance")
    },
    moveToWrite() {
      this.$router.push("/club/post/write");
    },
    moveToImage() {
      this.$router.push("/club/album");
    },
    moveToMyPage() {
      this.$router.push("/club/mypage")
    },
    makeinit() {
      console.log(this.$props.footernum)
      switch (this.$props.footernum) {
        case 1:
          this.onwallet = true
          break
        case 2:
          this.onedit = true
          break
        case 3:
          this.onimage = true
          break
        case 4:
          this.onuser = true
          break
      }
    },
  },
  created () {
    this.makeinit()
  }
}
</script>

<style scoped>
.select {
  color: #0080FF;
}
.fas {
  font-size: 1.7rem;
  margin: 0;
  /* color: rgba(0, 0, 0, 0.7) */
}
#footer {
  border-top: 1px solid;
  position: fixed;
  bottom: 0;
  height: 3.5rem;
  width: 100%;
  background-color: rgb(255, 255, 255);
}
</style>