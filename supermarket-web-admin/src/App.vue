<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
  import Vue from 'vue'
  import Buefy from 'buefy'
  import VeeValidate from 'vee-validate'
  import 'buefy/dist/buefy.css'
  import '@fortawesome/fontawesome-free/css/all.css'
  import VueMoment from '@/plugins/vue-moment'
  import {library} from '@fortawesome/fontawesome-svg-core'
  import {fas} from '@fortawesome/free-solid-svg-icons'
  import {FontAwesomeIcon} from '@fortawesome/vue-fontawesome'
  import VJstree from 'vue-jstree'

  library.add(fas)
  Vue.component('font-awesome-icon', FontAwesomeIcon)

  Vue.use(Buefy, {
    defaultIconPack: 'fas'
  })
  Vue.use(VeeValidate)
  Vue.use(VueMoment)
  Vue.use(VJstree)

  export default {
    name: 'App'
  }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
  }
</style>
