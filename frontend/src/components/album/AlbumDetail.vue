<template>
  <div class="box">

    <div class="image_area">
      <!-- <img class="image" :src="'/resources/'+photo.photo_address" alt="상세 이미지"> -->
      <img class="image" src="@/assets/근데.png" alt="이미지">
    </div>
    <div>
      <div>야근 중</div>
      <div>2021-10-06 07:54:09</div>
      <!-- <div>{{photo.user_name}}</div>
      <div>{{photo.time}}</div> -->
    </div>

    <!-- <div>{{photo}}</div>
      <div>{{photo.photo_address}}</div>
      <div>{{photo.time}}</div>
      <div>{{photo.user_name}}</div>
      <div>{{index}}</div> -->
  </div>
</template>

<script>
export default {
  data(){
    return {
      afterDayFormat:"",
    }
  },
  props: ["photo", "index"],
  monunted: {
    theDay(photo) {
      console.log('파싱 전', photo.time)
      var parsingdata1 = photo.time.substring(0,10)
      var parsingdata2 = parsingdata1.replace('-', '년 ')
      var parsingdata3 = parsingdata2.replace('-', '월 ')
      this.afterDayFormat = parsingdata3 + "일"
      console.log(this.afterDayFormat)

      
    }
  },

};
</script>

<style scoped>
.box{
  width:100%;
  min-height:80%;
}
/* .image_area{
  background-color:aquamarine;
  width:100%;
  height:100vw;
  overflow:hidden;
  margin: 0 0 0 0;
} */
.image{
  /* width: 200px;
  height: 200px; */
  width: 100%;
  height: 150vw;
  object-fit: contain;
  border: 1px solid #000    
}
</style>