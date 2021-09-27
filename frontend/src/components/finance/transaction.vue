<template>
  <div>
    <div>송금 컴포넌트</div>
    <b-container>
      <b-row>
        <b-col offset="1" cols="10" class="ethCard">
          <b-row>
            <b-col>받는사람</b-col>
            <b-col><b-form-input></b-form-input></b-col>
          </b-row>
          <b-row>
            <b-col>송금액</b-col>
            <b-col><b-form-input></b-form-input></b-col>
          </b-row>
          <b-row>
            <b-col>수수료 얼마</b-col>
          </b-row>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
export default {
  name: "Transaction",
}
</script>

<style scoped>
.ethCard {
  background-color: #1EC0FF;
  height: 10rem;
  border-radius: 0.5rem;
  color: #FFFFFF;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

</style>