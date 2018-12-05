<template>
  <aside class="menu">
    <ul class="menu-list">
      <li><a :href="'#/app/dashboard'">Dashboard</a></li>
      <li v-for="menu in resources" :key="menu.id">
        <a :href="menu.url ? '#/app' + menu.url: 'javascript: void;'">{{menu.displayName}}</a>
        <app-nav-bar-menu :menus="menu.subs"></app-nav-bar-menu>
      </li>
    </ul>
  </aside>
</template>

<script>
  import AppNavBarMenu from '@/components/AppNavbarMenu'
  import store from '@/store'

  export default {
    name: 'app-nav-bar',
    components: {AppNavBarMenu},
    data () {
      return {
        resources: []
      }
    },
    methods: {},
    beforeMount () {
      store.dispatch('getResources', {userId: store.getters.getUserToken.userId})
        .then(resp => {
          this.resources = resp.data
        })
    }
  }
</script>

<style scoped>

</style>
