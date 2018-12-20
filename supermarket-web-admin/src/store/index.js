import Vue from 'vue'
import Vuex from 'vuex'
import userModule from './modules/user_module'
import roleModule from '@/store/modules/role_module'
import shopModule from '@/store/modules/shop_module'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    users: userModule,
    roles: roleModule,
    shop: shopModule
  }
})
