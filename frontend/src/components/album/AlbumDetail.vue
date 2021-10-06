<template>
  <div class="box">
    <!-- <div>앨범 상세 페이지</div> -->
    <!-- <div>{{photoIndex}}</div> -->
    <div class="image_area">
      <img :src="'/resources/'+photo.photo_address" alt="상세 이미지">
      
      <!-- <img class="image" src="@/assets/cats.jpg" alt="이미지"> -->
    </div>
    <div>하단</div>
    <div>{{photo}}</div>
      <div>{{photo.photo_address}}</div>
      <div>{{photo.time}}</div>
      <div>{{photo.user_name}}</div>
      <div>{{index}}</div>
  </div>
</template>

<script>
export default {
  props: ["photo", "index"],
};
</script>

<style scoped>
.box{
  width:100%;
  min-height:50%;
}
.image_area{
  background-color:aquamarine;
  width:100%;
  height:100vw;
  overflow:hidden;
  /* margin:0 auto; */
  margin: 0 0 0 0;
}
.image{
  width:100%;

  object-fit:contain;
}
</style>