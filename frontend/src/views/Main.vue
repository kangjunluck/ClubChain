<template>
  <div class="main">
    <MainLogin />
  </div>
</template>

<script>
// @ is an alias to /src
import MainLogin from "@/components/MainLogin.vue";

export default {
  name: "Main",
  components: {
    MainLogin,
  },
};
</script>
