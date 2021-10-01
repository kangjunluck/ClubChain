<template>
	<div>
		<div>
		create club
		</div>
		<div>
			<input v-on:change="fileSelect" class="form-control" ref="profile_thumbnail" type="file" name="profile_thumbnail" id="profile_thumbnail">
		</div>
		<!-- <b-form-file
		v-model="file1"
		:state="Boolean(file1)"
		placeholder="Choose a file or drop it here..."
		drop-placeholder="Drop file here..."
    ></b-form-file> -->
    <!-- <div class="mt-3">Selected file: {{ file1 ? file1.name : '' }}</div> -->
		<b-form-input
		type="text"
		id="name"
		v-model="clubinfos.name"
		placeholder="동호회명"
		class="name"
	></b-form-input>
		<b-form-input
		type="text"
		id="introduce"
		v-model="clubinfos.introduce"
		placeholder="설명"
		class="introduce"
	></b-form-input>
		<select class="form-select" name='test' aria-label="Default select example" @change="updatetpye">
			<option selected>타입</option>
			<option value="1">공개 동호회</option>
			<option value="2">비공개 동호회</option>
	</select>
	<input v-if="clubtype === '타입' || clubtype === '공개 동호회' " class="form-control" type="text" placeholder="비밀번호" aria-label="Disabled input example" disabled>
	<b-form-input v-if=" clubtype === '비공개 동호회'"
		type="text"
		id="password"
		v-model="clubinfos.password"
		placeholder="비밀번호"
		class="password"
	></b-form-input>
		<button  class="clubCreateButton" @click="createSubmit">
				동호회 생성
			</button>
	</div>
</template>

<script>
import $ from "jquery";
import http from "@/util/http-common";
// import axios from "axios";

export default {
	name: 'ClubCreate',
	data: function () {
		return {
			clubtype: '타입',
			file1: null,
			clubinfos: {
				profile_thumbnail: "",
				clubaccount: "",
				introduce: "",
				name: "",
				password: "",
			},
			// types: [{text:'타입', value: null}, '공개 동호회', '비공개 동호회'],
		}
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
			this.clubinfos.profile_thumbnail = this.$refs.profile_thumbnail.files[0];
		},
		createSubmit() {
			// var photoFile = document.getElementById("profile_thumbnail");
			const formData = new FormData;
			// formData.append('profile_thumbnail', photoFile.files[0])
			// console.log('포토파일', photoFile.files[0])
			formData.append('profile_thumbnail', this.clubinfos.profile_thumbnail)
			formData.append('clubaccount', this.clubinfos.clubaccount)
			formData.append('introduce', this.clubinfos.introduce)
			formData.append('name', this.clubinfos.name)
			formData.append('password', this.clubinfos.password)
			console.log(formData)	
		
			// FormData의 값 확인 
			for (var pair of formData.entries()) { console.log(pair[0]+ ', ' + pair[1]); }



			http
				.post("api/club/", formData, { withCredentials: true })
				.then( (res) => {
					console.log('성공')
					console.log(res)})
				.catch((error) => {		
						console.log('실패')
						console.log(error)
					})


		}
	}

}
</script>

<style>


</style>