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
    };
  },
  methods: {
    write() {
        const formData = new FormData;
        formData.append('title', this.title);
        formData.append('content', this.content);
        http.post("", formData
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
}
.content {
    font-size:1rem;
}
</style>