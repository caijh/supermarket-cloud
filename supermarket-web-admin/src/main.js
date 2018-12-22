// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'

Vue.config.productionTip = false

function findRouterList (resources, path) {
  let routeList
  for (let i = 0; i < resources.length; i++) {
    routeList = []
    routeList.push(resources[i])
    if (resources[i].url === path) {
      break
    }
    if (!resources[i].subs) {
      routeList = []
      continue
    }
    let _routerList = findRouterList(resources[i].subs, path)
    if (_routerList.length > 0) {
      routeList.push(_routerList)
    }
  }
  return routeList
}

router.beforeEach((to, from, next) => {
  let resources = store.getters.getResources
  to.meta.routeList = findRouterList(resources, to.path)
  next()
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: {App},
  template: '<App/>'
})
