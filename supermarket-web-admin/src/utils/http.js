import axios from 'axios'
import CONSTANTS from '@/utils/constants'
import store from '@/store'

axios.defaults.headers.common['clientId'] = CONSTANTS.CLIENT_ID
axios.defaults.headers.post['Content-Type'] = 'application/json'

const INSTANCE = axios.create({
  timeout: 5000 // 请求超时时间
})

INSTANCE.interceptors.request.use(config => {
  if (config.url !== '/api/login' && store.getters.getUserToken.access_token) {
    config.headers['Authorization'] = 'Bearer '.concat(store.getters.getUserToken.access_token)
  }
  return config
}, error => {
  console.log(error)
  return Promise.reject(error)
})

INSTANCE.interceptors.response.use(resp => {
  return resp
}, error => {
  console.log(error)
  return Promise.reject(error)
})

export default INSTANCE
