<template>
  <div>
    <b-container>
      <b-row>
        <b-col offset="1" cols="10" class="ethCard">
          <div class="textAlignStart">받는사람</div>
          <b-form-input ></b-form-input>
          
          <div class="textAlignStart">이체금액</div>
          <b-form-input></b-form-input>
          
          <div class="textAlignEnd">수수료 얼마 </div>
        </b-col>
      </b-row>
      <b-row>
        <b-col offset="1" cols="10" class="paddingRemove">
          <button class="transactonButton">송금</button>
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
  border-radius: 0.5rem;
  color: #FFFFFF;
  display: flex;  
  flex-direction: column;
  justify-content: center;
}
.textAlignStart {
  align-self: start;
  margin: 1rem 0 0.3rem 0;
  font-size: 1rem;
}
.textAlignEnd {
  align-self: end;
  margin: 0.5rem 0.3rem 0.5rem 0;
  font-size: 0.8rem;
}
.transactonButton {
  background-color: #1ec0ff;
  margin: 2rem auto 0;
  height: 2.3rem;
  width: 100%;
  border-radius: 2.5rem;
  font-size: 1rem;
  font-weight: 500;
  color: #fff;
  border: 1px solid transparent;
}
.paddingRemove {
  padding: 0 0 0 0;
}
</style>