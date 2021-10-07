<template>
    <div class="main A">
        <div class="toparea d-flex flex-row justify-content-between align-items-center B">
            <div class="cancle" @click="goBack()">취소</div>
            <div class="done" @click="write()">완료</div>
        </div>
        <div class="a C">
            <input 
            type="text"
            name="title"
            v-model="title"
            ref="title"
            placeholder="제목을 입력해주세요"
            class="title b"
            />
            <textarea 
                name="content"
                v-model="content"
                ref="content"
                placeholder="내용을 입력해주세요"
                class="content c"
                />
            <!-- <i v-if="selecturl == false" class="far fa-image d" v-on:click="photoClick()"></i> -->
            <!-- <div v-if="selecturl !== false" class="photoarea"> -->
            <div class="photoarea d-flex justify-content-end" style="padding: 0 4vw 4vw 0">
                <img :src="selecturl" alt="url" class="photo" style="width:20vw; height:20vw;" v-on:click="photoClick()">
                <!-- <label class="photo-button" for="photo">사진 업로드</label> -->
            
            </div>
            <input v-on:change="fileSelect()" ref="image" type="file" name="photo" id="photo" class="col-6 photo-input" style="display:none;" />
        
            <div class="e"></div>
            <Footer :footernum="footernum" />
            
        </div>
        

    </div>
</template>

<script>
import http from "@/util/http-common";
import Footer from '../components/footer/footer.vue';
import $ from "jquery";
export default {
    data() {
        return {
            title: "",
            content: "",
            section: "",
            image: "",
            selecturl: require('@/assets/picture.png'),

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
        photoClick(){
            $(".photo-input").click()
            console.log('클릭됨?')
            
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
    mounted() {
        console.log(this.selecturl)
    }
};
</script>

<style scoped>
.far {
    font-size: 20vw;
    color: #8D8D8D
}
.toparea {
    height: 3rem;
}
.cancle {
    font-size:1rem;
    margin: 0 0 0 0;
    padding: 0 0 0 3vw;
}
.done {
    font-size:1rem;
    margin: 0 0 0 0;
    padding: 0 3vw 0 0;
}
.title {
    font-size:1rem;
    width: 100%;
    height: 3rem;
    border: 1px solid white;
    border-bottom: 1px solid #999999;
    /* padding-left: 0.5rem; */
}
input[type=text] {
    padding: 0 0 0 3vw;
}
input[type=textarea] {
    padding-left: 3vw;
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
    /* height: ; */
}
.a {
    display: flex;
    flex-direction: column;
}
.b {

}
.c {
    flex:1
}
.d {
}
.e {
    height: 3.5rem;
}
.A {
    display: flex;
    flex-direction: column;
    height: 100%;
}
.B {

}
.C {
    flex:1;
}
</style>