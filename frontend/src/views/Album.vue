<template>
  <div class="box">
    <AlbumNavigator
      v-on:stateChange="doStateChange"
      v-bind:componenetStateEmit="componenetStateEmit"
      v-bind:photo="photo"
      v-bind:index="index"
    />
    <AlbumMain
      v-if="componenetStateEmit === true"
      v-on:stateChange="doStateChange"
      v-on:photoInfo="checkPhotoInfo"
    />
    <AlbumDetail v-if="componenetStateEmit === false" v-bind:photoIndex="photoIndex"/>
    <Footer v-if="componenetStateEmit === true" />
  </div>
</template>

<script>
import AlbumMain from "@/components/album/AlbumMain.vue";
import AlbumDetail from "@/components/album/AlbumDetail.vue";
import AlbumNavigator from "@/components/album/AlbumNavigator.vue";
import Footer from "@/components/footer/footer.vue";

export default {
  name: "Album",
  components: {
    Footer,
    AlbumMain,
    AlbumDetail,
    AlbumNavigator,
  },
  data() {
    return {
      componenetStateEmit: true,
      index: "",
      photo: "",
    };
  },
  methods: {
    doStateChange() {
      this.componenetStateEmit = !this.componenetStateEmit;
    },
    checkPhotoInfo(photo, index){
      this.photo = photo
      this.index = index
      console.log('부모', this.photoIndex, this.index)

    },


  },
};
</script>

<style scoped>
.box{
  width: 100%;
  height: 100%;
}
</style>