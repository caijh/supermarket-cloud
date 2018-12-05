import mockUtil from './mock_util'
import roles from './data/roles'
import roleResources from './data/role_resources'
import roleUsers from './data/role_users'

export default {
  listRole (reqBody) {
    return Promise.resolve(mockUtil.success(mockUtil.buildPageResp(roles)))
  },
  getRole (roleId) {
    const rolesFilterById = roles.filter(role => role.id === roleId)
    if (rolesFilterById.length > 0) {
      return Promise.resolve(mockUtil.success(rolesFilterById[0]))
    } else {
      return Promise.reject(new Error('role not found'))
    }
  },
  updateRole (roleUpdateReqBody) {
    return Promise.resolve(mockUtil.success('修改成功'))
  },
  delRole (roleId) {
    return Promise.resolve(mockUtil.success('删除成功'))
  },
  preSetRoleResources (roleId) {
    return Promise.resolve(mockUtil.success(roleResources))
  },
  setRoleResources (roleId, resourceIds) {
    return Promise.resolve(mockUtil.success('设置成功'))
  },
  getRoleUsers (reqBody) {
    return Promise.resolve(mockUtil.success(mockUtil.buildPageResp(roleUsers)))
  }
}
