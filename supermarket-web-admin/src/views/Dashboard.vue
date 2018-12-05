<template>
  <div>
    <span>test</span>
    <button @click="test"></button>
  </div>
</template>

<script>
  export default {
    name: 'dashboard',
    methods: {
      test () {
        this.$notify.open({
          type: 'info',
          title: 'hello',
          content: '这是一个通知',
          icon: 'fas smile',
          placement: 'top-center'
        })
      }
    }
  }
</script>

<style scoped>

</style>
