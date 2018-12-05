import client from 'api-client'

const roleModule = {
  state: {},
  getters: {},
  mutations: {},
  actions: {
    listRole: ({commit}, reqBody) => {
      return client.roleApi.listRole(reqBody)
    },
    getRole: ({commit}, {roleId}) => {
      return client.roleApi.getRole(roleId)
    },
    updateRole: ({commit}, {id, name, updatedBy}) => {
      return client.roleApi.updateRole({id, name, updatedBy})
    },
    delRole: ({commit}, {roleId}) => {
      return client.roleApi.delRole(roleId)
    },
    preSetRoleResources: ({commit}, {roleId}) => {
      return client.roleApi.preSetRoleResources(roleId)
    },
    setRoleResources: ({commit}, {roleId, resources}) => {
      return client.roleApi.setRoleResources(roleId, resources)
    },
    getRoleUsers: ({commit}, reqBody) => {
      return client.roleApi.getRoleUsers(reqBody)
    }
  }
}
export default roleModule
