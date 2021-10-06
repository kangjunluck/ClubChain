<template>
  <div class="container">
    <!-- <div>앨범 메인페이지 {{ componenetStateEmit }}</div> -->
    <div class="photo_count">전체 사진 {{ photoCount }}개</div>
    <b-row v-if="photoCount !== 0" class="photos_area" >
      <b-col
        v-for="(photo, index) in photoList"
        v-bind:key="index"
        class="EqHeightDiv photo_list"
        cols="4"
        style="overflow: hidden"
      >
        <img
          class="image_resize"
          :src="'/resources/' + photo.photo_address"
          alt="앨범 이미지"
          @click="photoClick(photo, index)"
        />
      </b-col>
    </b-row>

    <div  class="image_area EqHeightDiv" @click="photoClick()">
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
    <!-- <button @click="test()">클릭</button> -->

  </div>
  
</template>

<script>
$(window).resize(function(){
  $('.EqHeightDiv').each(function() {
  console.log('너비', a)
  var a = $('.EqHeightDiv').width()
  $('.EqHeightDiv').height(a)
  })
}).resize();
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
      rowHeight: 0,
    };
  },
  methods: {
    getAlbum() {
      console.log("동호회 사진 불러오기");
      console.log('클럽 아이디', this.clubId);
      http
        .get("api/" + this.clubId + "/board/photo")
        .then((res) => {
          console.log('응답', res);
          this.photoCount = res.data.total;
          this.photoList = res.data.list;        
            // console.log('Works!')          
            // var a = $(".EqHeightDiv").width()
            // $(".EqHeightDiv").each(function(){
            // $(this).height(a)
            // console.log('높이', a)
          // })
          // console.log('end')
          
        })
        .catch((error) => {
          console.log('에러', error);
        });
    },
    uploadButton() {
      console.log("사진 업로드 버튼");
      this.$emit("stateChange");
    },
    photoClick(photo, index) {
      console.log("특정 사진 클릭");
      // console.log("자식", this.photo);
      console.log("자식2", photo, index);
      this.$emit("stateChange");
      this.$emit("photoInfo", photo, index);
    },
    test() {
      $(".EqHeightDiv").each(function(){
        var b = $(this).width()
        console.log('높이', b)
        $(this).height(b)
    })
    }
  },
  created() {
    this.getAlbum();
    // var viewportWidth = $(window).width();
    // this.rowHeight = viewportWidth/3
    // console.log(viewportWidth)
    // console.log(this.rowHeight)
  },
  // mounted() {          
  //     var a = $(this).width()
  //     $(".EqHeightDiv").each(function(){
  //     $(this).height(a)
  //     console.log('높이', a)
  //     console.log('end')
  //     })
  // },
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
  padding:2vw;
}
.photo_list {
  background-color: yellow;
  padding: 2px 2px 2px 2px;
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
.image_resize{
  width: 100%;
  min-height: 30vw;
  max-height: 33vw;
}
</style>