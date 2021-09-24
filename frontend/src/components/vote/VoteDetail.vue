<template>
  <div class="vote">
    <div>투표하는창 조회(get)</div>
    <!-- 나중에 아이콘 이미지로 바꾸기 -->
    <div class="arrow">⬅</div>
    <div class="voteContent">투표 내용</div>
    <div class="endDate">종료 날짜</div>
    <div class="voteOwn"><span>이미지</span> <span>작성자 이름</span></div>
    <!-- for문 수정 -->
    <div>투표 박스 1</div>
    <div>투표 박스 2</div>
    <div>투표 박스 3</div>
    <div class="voteParticipants">n명 참여</div>

    <div>
      <!-- post로 보냄 -->
      <button class="voteButton">투표하기</button>
      <button class="voteButton">투표 종료</button>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
export default {
  name: "Vote",
  methods: {
    voteList() {
      http.get("api/{clubid}/vote");
    },
  },
};
</script>

<style>
.vote {
  /* display: flex; */
  /* display: grid; */
  /* flex-direction: column; */
}
.arrow {
  /* align-self: flex-start; */
}
.voteContent {
  /* align-self: flex-start; */
}
.endDate {
  /* align-self: flex-start; */
}
.voteOwn {
  /* align-self: flex-start; */
}
.voteParticipants {
  /* align-self: flex-start; */
}
.voteButton {
  background-color: #1ec0ff;
  margin: 0 1rem 0 1rem;
  width: 30%;
  height: 2.3rem;
  border-radius: 0.5rem;
  font-size: 1rem;
  font-weight: 500;
  color: #fff;
  border: 1px solid transparent;
  height: 2.5rem;
  align-self: stretch;
}
</style>