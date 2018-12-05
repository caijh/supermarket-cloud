import formatDate from './filter/format_date'

const filters = {
  formatDate: formatDate
}

function install (Vue, options) {
  if (install.installed) {
    return
  }

  Object.keys(filters).forEach(key => Vue.filter(key, filters[key]))
}

export default {
  install,
  version: '1.0.0'
}
