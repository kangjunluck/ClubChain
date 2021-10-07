<template>
    <div class="main">
        <div class="toparea">
            <div class="cancel" @click="goBack()">
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
        <textarea 
            name="content"
            v-model="content"
            ref="content"
            placeholder="내용을 입력해주세요"
            class="content"
            />
        <div class="photoarea">
        <img :src="selecturl" alt="url" class="col-6 px-0 photo" style="width:3rem; height:3rem;">
        <label class="photo-button" for="photo">사진 업로드</label>
        <input v-on:change="fileSelect()" ref="image" type="file" name="photo" id="photo" class="col-6" style="display:none;" />
        </div>
        <Footer :footernum="footernum" />
    </div>
</template>

<script>
import http from "@/util/http-common";
import Footer from '../components/footer/footer.vue';

export default {
  data() {
    return {
      title: "",
      content: "",
      section: "",
      image: "",
      selecturl: require("@/assets/picture.png"),

      footernum : 2,
    };
  },
  components: {
    Footer,
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
    goBack() {
        this.$router.push("/club/ClubMain");
    }
  },
};
</script>

<style scoped>
.toparea {
    height: 3rem;
}
.cancel {
    font-size:1rem;
    box-sizing: border-box;
    float:left;
    width: 3rem;
}
.done {
    font-size:1rem;
    box-sizing: border-box;
    float:right;
    width: 3rem;
    padding-top: 0.9rem;
    padding-right: 1rem;
}
.title {
    font-size:1rem;
    width: 100%;
    height: 3rem;
    border: 1px solid white;
    border-bottom: 1px solid #999999;
    padding-left: 0.5rem;
}
.content {
    font-size:1rem;
    width: 100%;
    height: 10rem;
    height: 60%;
    border: 1px solid white;
    padding-top: 0.5rem;
    padding-left: 0.5rem;
}
input:focus{outline:none;}
textarea:focus{outline: none;}
.photo-button{
  padding: 3px 15px;
  background-color:#1ec0ff;
  border-radius: 2px;
  color: white;
  cursor: pointer;
}

.photoarea {
    text-align: left;
    margin-left: 2rem;
}
</style>