<template>
  <div>
		<div>
			프로필 수정
    </div>
		<b-container>
			<b-row>
				<b-col offset="1" cols="4" class="circle">
				<img src="" alt="" />
        </b-col>
				<b-col>
							<div>파일명</div>
							<button>이미지 선택</button>
				</b-col>
			</b-row>
		</b-container>
		<b-form-input placeholder="닉네임"></b-form-input>
		<b-form-input placeholder="이메일"></b-form-input>
		<button @click="myInfoUpdate">수정하기</button>
  </div>
</template>

<script>
// import http from "@/util/http-common";
export default {
  name: 'MyInfoUpdate',
	data: function(){
		return {
		usernickname: this.$store.state.credentials.usernickname
		}
	},
	methods: {
		myInfoUpdate() {
			console.log('업데이트')
			// http
			// 	.post("api/users/{userId}", formData, )
		}
	}
}
</script>


<style scoped>
.circle {
  border-radius: 1000px;
  overflow: hidden;
  max-width: 7rem;
  height: 7rem;
  border: 8px solid #efefef;
  /* position: absolute;
  top: 72px; */
}
</style>