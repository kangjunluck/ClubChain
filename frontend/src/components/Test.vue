<template>
  <div>
    <div>test 입니다</div>
    <!-- <button @click="myaccount"></button>
    <div>{{ this.$store.state.isLogin }}</div>
    <div>{{ this.$store.state.credentials.userEmail }}</div>
    <div>{{ this.$store.state.credentials.useraccount }}</div>
    <div>{{ this.$store.state.credentials.usernickname }}</div>
    <div>{{ this.$store.state.credentials.userthumbnail }}</div>
    <div>{{ this.$store.state.credentials.userId }}</div> -->
    
    <div class="small-12 medium-2 large-2 columns">
      <div class="circle">
        <!-- User Profile Image -->
        <img
          class="profile-pic"
          src="http://cdn.cutestpaw.com/wp-content/uploads/2012/07/l-Wittle-puppy-yawning.jpg"
        />

        <!-- Default Image -->
        <!-- <i class="fa fa-user fa-5x"></i> -->
      </div>

      <input class="file-upload" type="file" accept="image/*" />
    </div>
    <div>이미지</div>
    <img src="/var/lib/jenkins/workspace/PJT/Backend/init/src/main/resources/image/user/sss_뜨개질.jpg" alt="">
    <img src="/main/resources/image/user/sss_뜨개질.jpg" alt="">
    <img src="/image/user/sss_뜨개질.jpg" alt="">
  </div>
</template>

<script>
import $ from "jquery";
$(document).ready(function () {
  var readURL = function (input) {
    // console.log("input", input.files);
    if (input.files && input.files[0]) {
      var reader = new FileReader();

      reader.onload = function (e) {
        $(".profile-pic").attr("src", e.target.result);
      };

      reader.readAsDataURL(input.files[0]);
    }
  };

  $(".file-upload").on("change", function () {
    // console.log(this, "this");
    readURL(this);
  });

  // $(".upload-button").on("click", function () {
  //   $(".file-upload").click();
  // });
});
export default {
  // methods: {
  //   myaccount() {
  //     http
  //       .get("api/account/" + userid)
  //   }
  // }
};
</script>

<style scoped>
body {
  background-color: #efefef;
}

.profile-pic {
  max-width: 200px;
  max-height: 200px;
  display: block;
}

.file-upload {
  /* display: none; */
}
.circle {
  border-radius: 1000px !important;
  overflow: hidden;
  width: 128px;
  height: 128px;
  border: 8px solid rgba(255, 255, 255, 0.7);
  position: absolute;
  top: 72px;
}
img {
  max-width: 100%;
  height: auto;
}
.upload-button {
  font-size: 1.2em;
}

.upload-button:hover {
  transition: all 0.3s cubic-bezier(0.175, 0.885, 0.32, 1.275);
  color: #999;
}
</style>