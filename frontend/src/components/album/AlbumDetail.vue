<template>
  <div class="container
      d-flex
      flex-column
      justify-content-center
      align-items-center">

    <div class="image_area">
      <img class="image" :src="'/resources/'+photo.photo_address" alt="상세 이미지">
      <!-- <img class="image" src="@/assets/cats.jpg" alt="이미지"> -->
    </div>
    <div class="row w-50">
      <div class="d-flex justify-content-between align-items-center mb-3 p-0">
        <img class="circle px-0" :src="'/resources/'+photo.userthumbnail" alt="">
        <div>
          <p class="m-0 h5" style="text-align:left;">>{{photo.user_name}}</p>
          <p class="m-0 h6" style="text-align:left;">{{afterDayFormat}}</p>
        </div>
      </div>
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
      // photo: photo,
    }
  },
  props: ["photo", "index"],
  methods: {
    parsingDay(photo){
      console.log('파싱 전', photo.time)
      var parsingdata1 = photo.time.substring(0,10)
      var parsingdata2 = parsingdata1.replace('-', '년 ')
      var parsingdata3 = parsingdata2.replace('-', '월 ')
      this.afterDayFormat = parsingdata3 + "일"
      console.log(this.afterDayFormat)
    },
    parsingDay0(photo){
      console.log('파싱 전', photo.time)
      var parsingdata1 = photo.time.substring(0,10)
      var parsingdata2 = parsingdata1.replace('-', '년 ')
      var parsingdata3 = parsingdata2.replace('-', '월 ')
      this.afterDayFormat = parsingdata3 + "일"
      console.log(this.afterDayFormat)
    },
  },
  created() {
    console.log('created 시작')
    this.parsingDay0(this.photo)
    console.log('created 끝')
  }

};
</script>

<style scoped>
.circle {
  /* box-sizing: border-box; */
  box-sizing: content-box;
  position: relative;
  border-radius: 1000px !important;
  overflow: hidden;
  width: 20vw;
  height: 20vw;
  display: flex;
  align-items: center;
  justify-content: center;
  display: -webkit-flex;
  -webkit-align-item: center;
  -webkit-justify-content: center;
}
.container{
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