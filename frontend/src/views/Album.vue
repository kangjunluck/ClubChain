<template>
  <div class="box">
    
    <AlbumNavigator
        class="nav"
        v-on:stateChange="doStateChange"
        v-bind:componenetStateEmit="componenetStateEmit"
        v-bind:index="index"
        v-bind:photoCount="photoCount"
      />
    <div class="item">
    <AlbumMain
      
      v-if="componenetStateEmit === true"
      v-on:stateChange="doStateChange"
      v-on:photoInfo="checkPhotoInfo"
    />
    
    <AlbumDetail class="" v-if="componenetStateEmit === false" v-bind:index="index" v-bind:photo="photo" v-bind:photoCount="photoCount"/>
    </div>
    <Footer :footernum="footernum" v-if="componenetStateEmit === true" />

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
      photoCount: 0,

      footernum : 3,
    };
  },
  methods: {
    doStateChange() {
      this.componenetStateEmit = !this.componenetStateEmit;
    },
    checkPhotoInfo(photo, index, photoCount){
      this.photo = photo
      this.index = index
      this.photoCount = photoCount
      console.log('부모', this.photo, this.index)
    },
  },
};
</script>

<style scoped>
.box{
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  /* background-color: red; */
}
.item{
  flex: 1;
  /* background-color:blueviolet; */
}
</style>