<template>
  <div>
    <!-- <div>네비게이터입니다! {{ componenetStateEmit }}</div> -->
    
    <b-container class="nav_container">
      <b-row class="nav_row" v-if="componenetStateEmit === true">
        <b-col class="nav_album">
            앨범
        </b-col>
      </b-row>
      <b-row v-if="componenetStateEmit === false" class="nav_row">
        <b-col
          class="nav_album_detail count_photo arrow"
          cols="2"
          @click="goBack"
        >
          <i class="fas fa-arrow-left"></i>
        </b-col>
        <b-col class="count_photo nav_album_detail" cols="8">
          {{index}} / {{photoCount}}
        </b-col>
        <b-col cols="2"></b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
export default {
  // name: "Navigator",
  props: ["componenetStateEmit", "index", "photoCount"],
  methods: {
    goBack() {
      this.$emit("stateChange");
    },
  },
};
</script>

<style scoped>
.nav_container{
  margin: 0 0 0 0;
  padding: 0 0 0 0;
  min-width: 100%
}
.nav_album{
  height: 3rem;
  background-color: #0080FF;
  color : #fff;
  display: flex;
  flex-direction: column;
  justify-content: center;
  text-align: left;
  margin: 0 0 0 0;
  padding: 0 0 0 5%;
}
.nav_row{
  margin: 0 0 0 0;
  padding: 0 0 0 0;
  font-size: 1.7rem;
}
.nav_album_detail{
  height: 3rem;
  margin: 0 0 0 0;
  /* flex-direction: column;
  justify-content: center; */
  /* background-color: #0080FF; */
}
.arrow{
  /* 이모티콘이라 중앙정렬 안맞아서 셀프 추가 */
  padding: 0 0 1vw 0;
}
.count_photo{
  display: flex;
  flex-direction: column;
  justify-content: center;
}

</style>