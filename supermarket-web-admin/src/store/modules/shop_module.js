import client from 'api-client'

const shopModule = {
  state: {},
  getters: {},
  mutations: {},
  actions: {
    listShop: ({commit}, reqBody) => {
      return client.shopApi.list(reqBody)
    },
    getShop: ({commit}, id) => {
      return client.shopApi.get(id)
    }
  }
}
export default shopModule
