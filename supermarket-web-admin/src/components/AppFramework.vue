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
        <nav aria-label="breadcrumbs" class="breadcrumb">
          <ul>
            <li :class="{'is-active': index === routerList.length -1}" :key="resource.id"
                v-for="(resource, index) in routerList">
              <a href="#">
                <span class="icon is-small" v-if="resource.icon">
                  <i :class="resource.icon" aria-hidden="true"></i>
                </span>
                <span>{{resource.displayName}}</span>
              </a>
            </li>
          </ul>
        </nav>
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
    },
    computed: {
      routerList () {
        return this.$router.meta.routerList
      }
    }
  }
</script>

<style scoped>

</style>
