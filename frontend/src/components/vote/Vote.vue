<template>
  <div class='vote'>
    <!-- 나중에 아이콘 이미지로 바꾸기 -->
    <div class='arrow'>⬅</div>
    <div class="voteContent" >투표 내용</div>
    <div class="endDate">종료 날짜 어떻게 받아오지</div>
    <div class="vote_own"><span>이미지</span> <span>작성자 이름</span></div>
    <div>투표 박스 1</div>
    <div>투표 박스 2</div>
    <div>투표 박스 3</div>
    <div>n명 참여</div>
    
    <div><button>투표하기</button> <button>투표 종료</button></div>

  </div>
</template>

<script>
export default {
  name: 'Vote',

}
</script>

<style>
.vote {
  display: flex;
  flex-direction: column;
}
.arrow {
  align-self: flex-start;
}
.voteContent{
  align-self: flex-start;
}
.endDate{
  align-self: flex-start;
}
</style>