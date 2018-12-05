<template>
  <section>
    <div class="columns is-gapless">
      <div class="column">
        <AppHeader></AppHeader>
      </div>
    </div>
    <div class="columns is-gapless">
      <div class="column is-2">
        <AppNavbar></AppNavbar>
      </div>
      <div class="column">
        <router-view></router-view>
      </div>
    </div>
    <div class="columns is-gapless">
      <div class="column">
        <AppFooter></AppFooter>
      </div>
    </div>
  </section>
</template>

<script>
  import AppHeader from '@/components/AppHeader'
  import AppNavbar from '@/components/AppNavbar'
  import AppFooter from '@/components/AppFooter'

  export default {
    name: 'app-framework',
    components: {AppHeader: AppHeader, AppNavbar: AppNavbar, AppFooter: AppFooter},
    beforeMount () {
    }
  }
</script>

<style scoped>

</style>
