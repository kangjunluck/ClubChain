<template>
  <div>
    <b-container>
      <b-row>
        <b-col offset="1" cols="1" style="min-height:3rem, vertical-align:middle" @click="goBack">
          <i class="fas fa-arrow-left"></i>
        </b-col>
      </b-row>
    </b-container>
		
  </div>
</template>

<script>
export default {
	name: 'Navigator',
  props: ['componenetStateEmit'],
  
  methods: {
    goBack () {
      // this.$router.go(-1)
      if (this.componenetStateEmit === 'transfer' || this.componenetStateEmit === 'transactionHistory' ){
        const componenetState = ''
        this.$emit("test", componenetState);
      }       
    }
  }
}
</script>

<style>

</style>