<template>
  <div class="container">
    <!-- <div>앨범 메인페이지 {{ componenetStateEmit }}</div> -->
    <div class="photo_count">전체 사진 {{ photoCount }}개</div>
    <div>앨범 영역</div>
    <b-row v-if="photoCount !== 0" class="photos_area">
      <b-col
        v-for="photo in photoList"
        v-bind:key="photo"
        class="photo_list"
        cols="4"
        style="overflow: hidden"
      >
        <img
          class="EqHeightDiv"
          :src="'/resources/' + photo.photo_address"
          alt="앨범 이미지"
          @click="photoClick(photo)"
        />
        <!-- <b-col @click="goClub(club.clubid)" class="club-name-style">{{club.name}}</b-col> -->
      </b-col>
    </b-row>
    <div>앨범영역 끝</div>

    <!-- <div v-if="photoCount !== 0" class="photos_area">
      <div v-for="photo in photoList" v-bind:key="photo" class="photo_list">
        <img :src="'/resources/' + photo.photo_address" alt="앨범 이미지" />
      </div>
    </div> -->

    <!-- <img class="image_test" src="@/assets/cats.jpg" alt="앨범 이미지"  @click="photoClick()">
      <img class="image_test" src="@/assets/cats.jpg" alt="앨범 이미지"  @click="photoClick()">
      <img class="image_test" src="@/assets/cats.jpg" alt="앨범 이미지"  @click="photoClick()">
     -->

    <div class="image_area EqHeightDiv" @click="photoClick()">
      <img class="image" src="@/assets/cats.jpg" alt="앨범 이미지" />
    </div>
    <div class="image_area EqHeightDiv" @click="photoClick()">
      <img class="image" src="@/assets/cats.jpg" alt="앨범 이미지" />
    </div>

    <div class="image_area EqHeightDiv" @click="photoClick()">
      <img class="image" src="@/assets/cats.jpg" alt="앨범 이미지" />
    </div>
    <div v-if="photoCount === 0" class="photos_area">
      업로드 된 사진이 없습니다.
      <br />
      첫 사진을 업로드 해주세요.
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
import $ from "jquery";
export default {
  props: ["componenetStateEmit"],
  data() {
    return {
      photoCount: 0,
      clubId: this.$store.state.selectedClub,
      photoList: null,
      photo: 1,
    };
  },
  methods: {
    getAlbum() {
      console.log("동호회 사진 불러오기");
      console.log("this.clubId");
      console.log(this.clubId);
      http
        .get("api/" + this.clubId + "/board/photo")
        .then((res) => {
          console.log(res);
          // console.log(res.data.total, "전체사진");
          // console.log("리스트", res.data.list);
          // console.log(res.data);
          // console.log(res.data.total);
          // console.log(res.data.list);
          this.photoCount = res.data.total;
          this.photoList = res.data.list;
          console.log(this.photoCount);
          console.log(this.photoList);
          console.log(this.photoList[0]);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    uploadButton() {
      console.log("사진 업로드 버튼");
      this.$emit("stateChange");
    },
    photoClick(photo) {
      console.log("특정 사진 클릭");
      console.log("자식", this.photo);
      console.log("자식2", photo);
      this.$emit("stateChange");
      this.$emit("photoInfo", photo);
    },
    divSizeChange() {
      $(document).ready(function () {
        $(".container").each(function () {
          // var highestBox = 0;

          $(".EqHeightDiv", this).each(function () {
            $(this).height($(this).width());
            // if ($(this).height() > highestBox) highestBox = $(this).height();
          });

          // $(".EqHeightDiv", this).height(highestBox);
        });
      });
    },
  },
  created() {
    this.getAlbum();
  },
  mounted() {
    this.divSizeChange();
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
  font-weight: bold;
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
.image_area {
  background-color: aquamarine;
  width: 30%;
  height: 25%;
  overflow: hidden;
  /* margin:0 auto; */
  margin: 0 1% 0 1%;
}
.image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.photo_count {
  text-align: left;
  padding: 0 0 0 4.5%;
  font-size: 1.3rem;
  font-weight: bold;
}
</style>