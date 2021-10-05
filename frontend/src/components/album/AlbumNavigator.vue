<template>
  <div>
    <!-- <div>네비게이터입니다! {{ componenetStateEmit }}</div> -->
    <b-container>
      <b-row v-if="componenetStateEmit === true">
        <b-col>앨범</b-col>
      </b-row>
      <b-row v-if="componenetStateEmit === false">
        <b-col
          offset="1"
          cols="1"
          style="min-height:2rem, vertical-align:middle"
          @click="goBack"
        >
          <i class="fas fa-arrow-left"></i>
        </b-col>
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

<style>
</style>