<template>
	<div>
		<div>
		create club
		</div>
		<div>
			<input class="form-control" type="file" id="formFile">
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
		id="clubaccount"
		v-model="clubinfos.clubaccount"
		placeholder="동호회명"
		class="clubaccount"
	></b-form-input>
		<b-form-input
		type="text"
		id="clubinfo"
		v-model="clubinfos.clubinfo"
		placeholder="설명"
		class="formClubinfo"
	></b-form-input>
		<select class="form-select" name='test' aria-label="Default select example" @change="updatetpye">
			<option selected>타입</option>
			<option value="1">공개 동호회</option>
			<option value="2">비공개 동호회</option>
	</select>
	<input v-if="clubtype === '타입' || clubtype === '공개 동호회' " class="form-control" type="text" placeholder="비밀번호" aria-label="Disabled input example" disabled>
	<b-form-input v-if=" clubtype === '비공개 동호회'"
		type="text"
		id="clubpassword"
		v-model="clubinfos.clubpassword"
		placeholder="비밀번호"
		class="formClubPassword"
	></b-form-input>
		<button  class="clubCreateButton" @click="createSubmit">
				동호회 생성
			</button>
	
  </div>

</template>

<script>
import $ from "jquery";
// import http from "@/util/http-common";

export default {
	name: 'ClubCreate',
	data: function () {
		return {
			clubtype: '타입',
			file1: null,
			clubinfos: {
				clubaccount: "",
				clubinfo: "",
				clubpassword: "",
			},
			// types: [{text:'타입', value: null}, '공개 동호회', '비공개 동호회'],
		}
	},
	methods: {
		updatetpye() {
			console.log('업데이트')
			this.clubtype= $("select[name=test] option:checked").text()
		},
		createSubmit() {
			console.log(this.clubinfos.clubaccount)
			console.log(this.clubinfos.clubinfo)
			console.log(this.clubinfos.clubpassword)
			// http
			// 	.post("api/club")
		}
	}
}
</script>

<style>


</style>