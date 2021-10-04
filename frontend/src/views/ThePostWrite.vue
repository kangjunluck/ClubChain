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
    };
  },
  methods: {
    write() {
        const formData = new FormData;
        formData.append('title', this.title);
        formData.append('content', this.content);
        formData.append('section', "section");
        formData.append('clubid', this.$store.state.selectedClub);
        formData.append('userid', this.$store.state.credentials.userId);
        var url = "/api/";
        url += this.$store.state.selectedClub;
        url += /board/;
        http.post(url, formData
        ).then( (res) => {
            console.log(res.data);
        }).catch((error) => {
            alert("회원가입 실패");
            console.dir(error);
        })
    }
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
</style>