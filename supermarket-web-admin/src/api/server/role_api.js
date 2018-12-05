import http from '@/utils/http'

export default {
  listRole (reqBody) {
    return http.post('/api/admin/role/list', reqBody)
  },
  getRole (roleId) {
    return http.get('/api/admin/role/detail?id=' + roleId)
  },
  updateRole (roleUpdateReqBody) {
    return http.post('/api/admin/role/update', roleUpdateReqBody)
  },
  delRole (roleId) {
    return http.get('/api/admin/role/delete?id=' + roleId)
  },
  preSetRoleResources (roleId) {
    return http.get('/api/admin/role/set/resources?action=pre&id=' + roleId)
  },
  setRoleResources (roleId, resourceIds) {
    return http.post('/api/admin/role/set/resources?id=' + roleId, resourceIds)
  },
  getRoleUsers (reqBody) {
    return http.post('/api/admin/role/user/list', reqBody)
  }
}
