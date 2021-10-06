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
          class="nav_album_detail arrow"
          style="vertical-align:middle"
          cols="2"
          @click="goBack"
        >
          <i class="fas fa-arrow-left"></i>
        </b-col>
        <b-col class="nav_album_detail" cols="8">
          <div>{{index}} / {{photoCount}}</div>
        </b-col>
        <b-col cols="2"></b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
export default {
  // name: "Navigator",
  props: ["componenetStateEmit"],
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
  margin: 0 0 0 0;
  flex-direction: column;
  justify-content: center;
  /* background-color: #0080FF; */
}
.arrow{
  padding:0 0 0 5%;
  text-align: left;
}

</style>