<template>
    <div class="main">
        <div class="header">
            <img alt="Vue logo" src="@/assets/logo.png" class="logo" />
            <img alt="profile" src="@/assets/logo.png" class="profile"/>
        </div>
    </div>
</template>

<script>
//import http from "@/util/http-common";

export default {
  data() {
    return {
      
    };
  },
  methods: {

  },
};
</script>

<style scoped>
.mainLogin {
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.header img{
    width: 10%;
    height: 10%;
}
.profile{
    float:right;
    margin-right: 5%;
    margin-top: 5%;
}
.logo{
    float:left;
    margin-left: 5%;
    margin-top: 5%;
}
</style>