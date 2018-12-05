import http from '@/utils/http'

export default {
  login (username, password) {
    return http.post('/api/login', {username: username, password: password})
  },
  getUserDetail (userId) {
    return http.get('/api/admin/user/detail?id=' + userId)
  },
  logout (tokenType, token) {
    return http.get('/api/logout?token_type=' + tokenType + '&token=' + token)
  },
  getResources (userId) {
    return http.get('/api/admin/user/resources?id=' + userId)
  },
  listUser (reqBody) {
    return http.post('/api/admin/user/list', reqBody)
  }
}
