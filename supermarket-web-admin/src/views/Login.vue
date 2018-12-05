<template>
  <section>
    <div class="columns is-centered is-gapless">
      <div class="column is-half">
        <div class="has-text-centered">
          <img src="../assets/logo.png">
        </div>
        <form @submit.prevent="beforeSubmit">
          <div class="field">
            <div class="control has-icons-left">
              <input class="input" type="text" placeholder="User name" name="username" v-model="user.name"
                     v-validate="{required: true}">
              <span class="icon is-small is-left">
                <font-awesome-icon :icon="['fas', 'user']"/>
              </span>
            </div>
            <span class="help is-danger" v-show="errors.has('username')">{{ errors.first('username') }}</span>
          </div>
          <div class="field">
            <div class="control has-icons-left">
              <input class="input" type="password" placeholder="Password" name="password" v-model="user.password"
                     v-validate="{required: true}">
              <span class="icon is-small is-left">
                <font-awesome-icon :icon="['fas', 'lock']"/>
              </span>
            </div>
            <span class="help is-danger" v-show="errors.has('password')">{{ errors.first('password') }}</span>
          </div>
          <div class="field is-grouped">
            <div class="control">
              <button class="button is-primary" type="submit" :disabled="submitBtnDisabled">Login</button>
            </div>
          </div>
        </form>
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
  import MD5 from 'crypto-js/md5'
  import AppFooter from '@/components/AppFooter'
  import store from '@/store'

  export default {
    components: {AppFooter},
    name: 'login',
    data () {
      return {
        submitBtnDisabled: false,
        user: {
          name: '',
          password: ''
        }
      }
    },
    methods: {
      beforeSubmit () {
        this.$validator.validateAll().then((result) => {
          if (!result) {
            this.$notify.open({
              content: '表单验证不通过',
              icon: 'smile-o',
              placement: 'top-center'
            })
            return
          }
          this.submitBtnDisabled = true
          store.dispatch('login', {username: this.user.name, password: MD5(this.user.password).toString()})
            .then(() => {
              this.submitBtnDisabled = false
              this.$router.push({path: '/app/dashboard'})
            })
            .catch(err => {
              this.submitBtnDisabled = false
              this.$toast.open({
                duration: 5000,
                message: err.message,
                position: 'is-top',
                type: 'is-warning'
              })
            })
        })
      }
    }
  }
</script>

<style scoped>

</style>
