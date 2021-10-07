<template>
  <div class="table-content">
    <div class="
        item1
        ">
      <div class="image_area">
        
        <img class="image" :src="'/resources/'+photo.photo_address" alt="상세 이미지">
        <!-- <img class="image" src="@/assets/cats.jpg" alt="이미지"> -->
        
      </div>

    </div>
    <div class="row">
      <div class="detail_info d-flex justify-content-left">
        <img class=" circle" :src="'/resources/'+photo.userthumbnail" alt="이미지">
        <div class="image_info_div">
          <p class="m-0 h5 p-tag" style="text-align:left;">{{photo.user_name}}</p>
          <p class="m-0 h6 p-tag" style="text-align:left;">{{afterDayFormat}}</p>
        </div>
      </div>
    </div>

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
.table-content{
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  /* background-color:cornflowerblue */
}
.item1{
  flex: 1;
  
}
.circle {
  
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
  
  margin: 0 0 0 1rem;
}
.container{
  width:100%;
  min-height:100%;
}
.image_area{
  
  width:100%;
  height:100vw;
  overflow:hidden;
  margin: 0 0 0 0;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.image{
  width: 200px;
  height: 200px; 
  width: 100%;
  height: 130vw;
  object-fit: contain;
  /* border: 1px solid #000; */
}
.image_info_div{
  padding: 0 0 0 1rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.detail_info{
  margin: 0 0 0 0;
  padding: 0 0 2vh 0 ;
}
.row {
  margin:0 0 0 0;
  padding:0 0 0 0;
}
.p-tag {
  margin:0 0 0 0;
  padding:0 0 0 0;
}
</style>