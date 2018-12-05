<template>
  <nav class="navbar is-transparent is-fixed-top">
    <div class="navbar-brand">
      <a class="navbar-item">
        <img src="../assets/logo.png">
      </a>
      <div class="navbar-burger burger" data-target="navbarExampleTransparentExample">
        <span></span>
        <span></span>
        <span></span>
      </div>
    </div>

    <div id="navbarExampleTransparentExample" class="navbar-menu">
      <div class="navbar-start">
        <a class="navbar-item" href="https://bulma.io/">
          Home
        </a>
        <div class="navbar-item has-dropdown is-hoverable">
          <a class="navbar-link" href="/documentation/overview/start/">
            Docs
          </a>
          <div class="navbar-dropdown is-boxed">
            <a class="navbar-item" href="/documentation/overview/start/">
              Overview
            </a>
          </div>
        </div>
      </div>

      <div class="navbar-end">
        <div class="navbar-item has-dropdown is-hoverable">
          <a class="navbar-link">
            <span class="icon has-text-info">
              <b-icon pack="fas" icon="info-circle" size="is-small"></b-icon>
            </span>
            {{nickname}}
          </a>
          <div class="navbar-dropdown is-right">
            <a class="navbar-item" @click="onLogout">退出
              <b-icon pack="fas" icon="sign-out-alt" size="is-small"></b-icon>
            </a>
          </div>
        </div>
      </div>

    </div>
  </nav>
</template>

<script>
  import store from '@/store'

  export default {
    name: 'app-header',
    data () {
      return {
        nickname: ''
      }
    },
    methods: {
      onLogout () {
        store.dispatch('logout', {
          tokenType: store.getters.getUserToken.token_type,
          token: store.getters.getUserToken.token
        })
          .then(() => {
            this.$router.push({path: '/login'})
          })
      }
    },
    beforeMount () {
      store.dispatch('getUserDetail', {userId: store.getters.getUserToken.userId})
        .then(resp => {
          this.nickname = resp.data.nickname
        })
    }
  }
</script>

<style scoped>

</style>
