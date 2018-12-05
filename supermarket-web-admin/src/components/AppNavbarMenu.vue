<template>
  <ul v-if="menus != null && menus.length > 0">
    <li v-for="menu in menus" :key="menu.id" v-if="menu.type === 1">
      <div v-if="menu.type === 1">
        <a :href="menu.url ? '#/app' + menu.url: 'javascript: void;'">{{menu.displayName}}</a>
        <app-nav-bar-menu :menus="menu.subs"></app-nav-bar-menu>
      </div>
    </li>
  </ul>
</template>

<script>
  export default {
    name: 'app-nav-bar-menu',
    props: ['menus']
  }
</script>

<style scoped>

</style>
