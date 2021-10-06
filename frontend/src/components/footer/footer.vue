<template>
  <div id="footer">
    <div class="d-flex justify-content-around h-100 align-items-center">
      <p class="fas fa-wallet fa-lg walletIcon" :class="{select: onwallet}" @click="moveToFinance"></p>
      <p class="fas fa-edit fa-lg" :class="{select: onedit}" @click="moveToWrite"></p>
      <p class="fas fa-images fa-lg" :class="{select: onimage}" @click="moveToImage"></p>
      <p class="fas fa-user fa-lg myPageIcon" :class="{select: onuser}" @click="moveToMyPage"></p>
    </div>
  </div>
</template>

<script>
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
    moveToFinance() {
      console.log('계좌로이동')
      console.log(this.$route.path)
      this.makeinit()
      this.onwallet = true;
      console.log(this.onwallet+"11111111111")
      if(this.$route.path!=='/club/finance') {
        this.$router.push("finance")
        }
    },
    moveToWrite() {
      this.$router.push("/club/post/write");
    },
    moveToImage() {
      console.log('사진첩으로 이동')
    },
    moveToMyPage() {
      this.$router.push("club/mypage")
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
  font-size: 2rem;
  margin: 0;
}
#footer {
  border-top: solid;
  position: fixed;
  bottom: 0;
  height: 4rem;
  width: 100%;
  background-color: rgb(255, 255, 255);
}
</style>