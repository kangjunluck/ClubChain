<template>
  <div>
    <div>투표 메인</div>
    <VoteList />
    <VoteCreate />
    <VoteDetail />
  </div>
</template>

<script>
import VoteList from "@/components/vote/VoteList.vue";
import VoteCreate from "@/components/vote/VoteCreate.vue";
import VoteDetail from "@/components/vote/VoteDetail.vue";
import http from "@/util/http-common";
export default {
  name: "Vote",
  components: {
    VoteList,
    VoteCreate,
    VoteDetail,
  },
  methods: {
    voteList() {
      http.get("api/{clubid}/vote");
    },
  },
};
</script>

<style>
</style>