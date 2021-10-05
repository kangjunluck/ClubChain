<template>
  <div>
    <AlbumNavigator
      v-on:stateChange="doStateChange"
      v-bind:componenetStateEmit="componenetStateEmit"
    />
    <AlbumMain
      v-if="componenetStateEmit === true"
      v-on:stateChange="doStateChange"
    />
    <AlbumDetail v-if="componenetStateEmit === false" />
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
    };
  },
  methods: {
    doStateChange() {
      this.componenetStateEmit = !this.componenetStateEmit;
    },
  },
};
</script>

<style>
</style>