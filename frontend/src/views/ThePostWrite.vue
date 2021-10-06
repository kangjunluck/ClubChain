<template>
    <div class="main">
        <div class="header">
            <div class="cancel">
                취소
            </div>
            <div class="done" @click="write()">
                완료
            </div>
        </div>
        <input 
            type="text"
            name="title"
            v-model="title"
            ref="title"
            placeholder="제목을 입력해주세요"
            class="title"
        />
        <input 
            type="text"
            name="content"
            v-model="content"
            ref="content"
            placeholder="내용을 입력해주세요"
            class="content"
            />
            <img :src="selecturl" alt="url" class="col-6 px-0" style="width:3rem; height:3rem;">
          <label class="photo-button" for="photo">업로드</label>
          <input v-on:change="fileSelect()" ref="image" type="file" name="photo" id="photo" class="col-6" style="display:none;" />
    </div>
</template>

<script>
import http from "@/util/http-common";

export default {
  data() {
    return {
      title: "",
      content: "",
      section: "",
      image: "",
      selecturl: require("@/assets/profile.png"),
    };
  },
  methods: {
    write() {
        const formData = new FormData;
        formData.append('title', this.title);
        formData.append('content', this.content);
        formData.append('section', "분류");
        formData.append('clubid', this.$store.state.selectedClub);
        formData.append('userid', this.$store.state.credentials.userId);
        formData.append('board_thumbnail', this.image);
        
        console.log('title : ' + this.title);
        console.log('content :' + this.content);
        console.log('clubid : ' + this.$store.state.selectedClub);
        console.log('userid : ' + this.$store.state.credentials.userId);
        console.log('board_thumbnail : ' + this.image);

        var url = "/api/";
        url += this.$store.state.selectedClub;
        url += /board/;
        http.post(url, formData, {
            withCredentials : true
        }
        ).then( (res) => {
            console.log(res.data);
            alert("글 작성 완료");
            this.$router.push("/club/ClubMain");
        }).catch((error) => {
            alert("글쓰기 실패");
            console.dir(error);
        })
    },
    fileSelect() {
      this.image = this.$refs.image.files[0];
      this.selecturl = URL.createObjectURL(this.image);
      console.log(this.image);
    },
  },
};
</script>

<style scoped>
.header {
    height: 3rem;
    background-color: sandybrown;
}
.cancel {
    font-size:1rem;
    float:left;
}
.done {
    font-size:1rem;
    float:right;
}
.title {
    font-size:1rem;
    width: 100%;
    height: 3rem;
}
.content {
    font-size:1rem;
    width: 100%;
    height: 10rem;
}
.photo-button{
  padding: 3px 15px;
  background-color:#1ec0ff;
  border-radius: 2px;
  color: white;
  cursor: pointer;
}
</style>