import './scss/main.scss'

import Notify from '@/plugins/vue-bulma/components/notify'

import Pagination from './components/pagination'

const components = {
  Pagination
}

function install (Vue, options) {
  if (install.installed) {
    return
  }

  Object.keys(components).forEach(key => Vue.component(key, components[key]))
  Vue.prototype.$notify = Notify
}

export default {
  install,
  version: '1.0.0'
}
