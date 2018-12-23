// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'

Vue.config.productionTip = false

function findRouteList (resources, path) {
  let routeList
  let found = false
  for (let i = 0; i < resources.length && !found; i++) {
    routeList = []
    routeList.push(resources[i])
    if (resources[i].url === path) {
      found = true
      break
    }
    if (!resources[i].subs) {
      continue
    }
    let _routeList = findRouteList(resources[i].subs, path)
    if (_routeList.length > 0) {
      found = true
      for (let j = 0; j < _routeList.length; j++) {
        routeList.push(_routeList[j])
      }
      break
    }
  }
  if (!found) {
    routeList = []
  }
  console.log(routeList)
  return routeList
}

router.beforeEach((to, from, next) => {
  let path = to.path
  let index = to.path.indexOf('/app')
  if (index > -1) {
    path = path.substring(index + 4)
  }
  let resources = store.getters.getResources
  to.meta.routeList = findRouteList(resources, path)
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
