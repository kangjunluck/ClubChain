<template>
	<div>
		<!-- 뒤로가기 아이콘 -->
		<div align="left" class="cancel">
			<i class="far fa-times-circle fa-2x" @click="goback"></i>
		</div>
		<div class="clubCreate form">
			<h2 class="signup_header">동호회 생성</h2>
			<!-- 동호회 썸네일 입력 -->
			<div>
				<!-- <div class="mb-2 d-flex justify-content-between align-items-center"> -->
				<!-- <img :src="selecturl" alt="url" class="photo-size" style="width:50px; height:50px; float: left;">
				<label class="photo-button" for="photo">업로드</label>
				<input v-on:change="fileSelect()" ref="image" type="file" name="photo" id="photo" class="col-6" style="display:none;" /> -->
				<!-- </div> -->
				<input v-on:change="fileSelect()" class="form-control" ref="image" type="file" name="photo" id="photo">
			</div>
			<!-- 동호회명 입력 -->
			<b-form-input
				type="text"
				id="name"
				v-model="clubinfos.name"
				placeholder="동호회명"
				class="inputMargin name"
			></b-form-input>
			<!-- 동호회 설명 입력 -->
			<b-form-input
				type="text"
				id="introduce"
				v-model="clubinfos.introduce"
				placeholder="설명"
				class="inputMargin introduce"
			></b-form-input>
			<!-- 동호회 타입 설정 -->
			<select class="inputMargin form-select" name='test' aria-label="Default select example" @change="updatetpye">
				<option selected>타입</option>
				<option value="1">공개 동호회</option>
				<option value="2">비공개 동호회</option>
			</select>
			<!-- 동호회 비밀번호 설정 -->
			<input v-if="clubtype === '타입' || clubtype === '공개 동호회' " class="inputMargin form-control" type="text" placeholder="비밀번호" aria-label="Disabled input example" disabled>
			<b-form-input v-if=" clubtype === '비공개 동호회'"
				type="text"
				id="password"
				v-model="clubinfos.password"
				placeholder="비밀번호"
				class="inputMargin password"
			></b-form-input>
			
			<button  class="inputMargin button button-color" @click="createSubmit">
				동호회 생성
			</button>
		</div>
		<CompleteModal v-if="completeModal" :message="message" @close="moveTo"/>
        <FailModal v-if="failModal" :message="message" @close="failModal=false"/>
		<!-- <b-form-file
		v-model="file1"
		:state="Boolean(file1)"
		placeholder="Choose a file or drop it here..."
		drop-placeholder="Drop file here..."
    ></b-form-file> -->
    <!-- <div class="mt-3">Selected file: {{ file1 ? file1.name : '' }}</div> -->
		
	</div>
</template>

<script>
import $ from "jquery";
import http from "@/util/http-common";
// import axios from "axios";
import Web3 from "web3";
import CompleteModal from "../components/modal/complete.vue"
import FailModal from "../components/modal/fail.vue"

export default {
	name: 'ClubCreate',
	data: function () {
		return {
			clubtype: '타입',
			file1: null,
			clubinfos: {
				club_thumbnail: "",
				clubaccount: "",
				introduce: "",
				name: "",
				password: "",
			},

			completeModal : false,
        	failModal : false,
        	message : "",
			// selecturl: require("../assets/profile.png"),
		}
	},
	components :{
		CompleteModal,
		FailModal,
  	},
	methods: {
		updatetpye() {
			console.log('업데이트')
			this.clubtype= $("select[name=test] option:checked").text()
		},
		// fileSelect(file) {
			// console.log(file);
			// this.clubinfos.profile_thumbnail = file
		// },
		fileSelect() {
			console.log(this.$refs);
			this.clubinfos.club_thumbnail = this.$refs.image.files[0];
      // if (this.clubinfos.club_thumbnail == undefined){
      //   this.selecturl = require("../assets/profile.png");
      // }
      // this.selecturl = URL.createObjectURL(this.clubinfos.club_thumbnail);
		},
		createSubmit() {
			var web3 = new Web3(new Web3.providers.HttpProvider("https://ropsten.infura.io/v3/d2f03576222c4c2fbc5eeb6850f9abf3"));
		// 계정생성, 지갑생성
			let {address,privateKey} = web3.eth.accounts.create();
			let wallet = web3.eth.accounts.wallet.add(
			{
				privateKey: privateKey,
				address: address
			})
			this.saveFile(privateKey.substring(2))
			console.log(wallet);

			// var photoFile = document.getElementById("profile_thumbnail");
			const formData = new FormData;
			// formData.append('profile_thumbnail', photoFile.files[0])
			// console.log('포토파일', photoFile.files[0])
			formData.append('club_thumbnail', this.clubinfos.club_thumbnail)
			formData.append('clubaccount', address)
			formData.append('introduce', this.clubinfos.introduce)
			formData.append('name', this.clubinfos.name)
			formData.append('password', this.clubinfos.password)

			console.log(formData)	
		
			// FormData의 값 확인 
			for (var pair of formData.entries()) { console.log(pair[0]+ ', ' + pair[1]); }



			http
				.post("api/club/", formData, { withCredentials: true })
				.then( (res) => {
					console.log(res)
					this.message = "동호회 생성 완료";
            		this.completeModal = true;
				})
				.catch((error) => {		
					console.log(error);
					this.message = error;
					this.failModal = true;
				})
		},
		saveFile(privateKey) {
			var blob = new Blob([privateKey], {type: 'text/plain'});

			const objURL = window.URL.createObjectURL(blob);
			console.log(objURL);
			if (window.__Xr_objURL_forCreatingFile__) {
				window.URL.revokeObjectURL(window.__Xr_objURL_forCreatingFile__);
			}
			window.__Xr_objURL_forCreatingFile__ = objURL;
			var a = document.createElement('a');
			a.download = "PrivateKey";
			a.href = objURL;
			console.log(a)
			a.click();
		},
		goback () {
			this.$router.push("/club/list");
		},
		moveTo () {
      		this.completeModal = false;
      		this.$router.push("/club/list");
    },
	}

}
</script>

<style>
.cancel {
	position: relative;
	left: 15px;
	top: 15px;
}

.clubCreate {
	height: 100%;
  display: flex;
  flex-direction: column;
  /* background-color: aqua; */
  justify-content: center;
}

.form {
	margin: 0 auto;
  width: 70%;
}

.inputMargin {
	margin-top: 1rem;
}

.button-color {
	padding: 3px 15px;
  background-color:#1ec0ff;
  border-radius: 3rem;
  color: white;
  cursor: pointer;
}

.signup_header {
    width: 100%;
    font-size: 2em;
    text-align: left;
}

.button {
	border: 0;
	outline: 0;
	height: 6%;
}
</style>