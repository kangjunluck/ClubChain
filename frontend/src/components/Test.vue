<template>
  <div>
    <div>test 입니다</div>
    <div>{{ this.$store.state.isLogin }}</div>
    <div>{{ this.$store.state.credentials.userEmail }}</div>
    <div>{{ this.$store.state.credentials.useraccount }}</div>
    <div>{{ this.$store.state.credentials.usernickname }}</div>
    <div>{{ this.$store.state.credentials.userthumbnail }}</div>

  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>