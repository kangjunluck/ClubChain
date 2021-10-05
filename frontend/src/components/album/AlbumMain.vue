<template>
  <div>
    <div>앨범 메인페이지 {{ componenetStateEmit }}</div>
    <div class="upload_button" @click="uploadButton()">
      <i class="far fa-plus-square"></i> 사진 올리기
    </div>
    <div>전체 사진 {{ photoCount }}개</div>
    <div v-if="photoCount !== 0" class="photos_area">
      <div
        v-for="photo in photoList"
        v-bind:key="photo"
        class="photo_list"
      ></div>
    </div>
    <div v-if="photoCount === 0" class="photos_area">
      업로드 된 사진이 없습니다.
      <br />
      첫 사진을 업로드 해주세요.
    </div>
  </div>
</template>

<script>
// import http from "@/util/http-common";
export default {
  props: ["componenetStateEmit"],
  data() {
    return {
      photoCount: 0,
      clubId: this.$store.state.selectedClub,
      photoList: null,
    };
  },
  methods: {
    getAlbum() {
      console.log("동호회 사진 불러오기");
      http
        .get("api/" + this.clubId + "board/photo")
        .then((res) => {
          console.log(res);
          console.log(res.total, "전체사진");
          console.log("리스트", res.list);
          this.photoCount = res.total;
          this.photoList = res.list;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    uploadButton() {
      console.log("사진 업로드 버튼");
      this.$emit("stateChange");
    },
  },
  created() {
    this.getAlbum();
  },
};
</script>

<style scoped>
.upload_button {
  background-color: #1ec0ff;
  margin: 0 auto 0;
  width: 70%;
  border-radius: 0.5rem;
  font-size: 1.2rem;
  font-weight: 500;
  color: #fff;
  border: 1px solid transparent;
  min-height: 2.5rem;
  -webkit-appearance: none;
}
.photos_area {
  background-color: red;
}
.photo_list {
  background-color: yellow;
}
</style>