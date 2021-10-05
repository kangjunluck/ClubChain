<template>
  <div>
    <AlbumNavigator
      v-on:stateChange="doStateChange"
      v-bind:componenetStateEmit="componenetStateEmit"
    />
    <AlbumMain
      v-if="componenetStateEmit === true"
      v-on:stateChange="doStateChange"
      v-on:photoInfo="checkPhotoInfo"
    />
    <AlbumDetail v-if="componenetStateEmit === false" v-bind:photoIndex="photoIndex"/>
    <Footer />
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
      photoIndex: ""
    };
  },
  methods: {
    doStateChange() {
      this.componenetStateEmit = !this.componenetStateEmit;
    },
    checkPhotoInfo(photoIndex){
      this.photoIndex = photoIndex
      console.log('부모', this.photoIndex)

    }
  },
};
</script>

<style>
</style>