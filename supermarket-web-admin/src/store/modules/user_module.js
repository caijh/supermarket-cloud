import client from 'api-client'

const userModule = {
  state: {
    userToken: {
      access_token: '',
      token_type: '',
      refresh_token: '',
      expires_in: '',
      scope: '',
      userId: '',
      jti: ''
    },
    users: [],
    resources: []
  },
  getters: {
    getUserToken (state) {
      if (!state.userToken.userId) {
        let userToken = JSON.parse(localStorage.getItem('userToken'))
        if (userToken) {
          state.userToken = userToken
        }
      }
      return state.userToken
    },
    getResources (state) {
      if (state.resources.length > 0) {
        return state.resources
      }
      let resources = JSON.parse(localStorage.getItem('resources'))
      if (resources) {
        state.resources = resources
      }
      return state.resources
    }
  },
  mutations: {
    saveOrUpdateUserToken (state, userToken) {
      state.userToken = userToken
      localStorage.setItem('userToken', JSON.stringify(state.userToken))
    },
    removeUserToken (state) {
      state.userToken = {
        access_token: '',
        token_type: '',
        refresh_token: '',
        expires_in: '',
        scope: '',
        userId: '',
        jti: ''
      }
      localStorage.setItem('userToken', state.userToken)
    },
    setUsers (state, users) {
      state.users = users
    },
    saveResources (state, resources) {
      state.resources = resources
      localStorage.setItem('resources', JSON.stringify(state.resources))
    }
  },
  actions: {
    login: ({commit}, {username, password}) => {
      return client.userApi.login(username, password)
        .then(resp => {
          commit('saveOrUpdateUserToken', resp.data)
        })
    },
    logout: ({commit}, {tokenType, token}) => {
      return client.userApi.logout(tokenType, token)
        .then(() => {
          commit('removeUserToken')
        })
    },
    removeUserToken: ({commit}) => {
      commit('removeUserToken')
    },
    getUserDetail: ({commit}, {userId}) => {
      return client.userApi.getUserDetail(userId)
    },
    getResources: ({commit}, {userId}) => {
      return client.userApi.getResources(userId)
        .then(resp => {
          commit('saveResources', resp.data)
          return Promise.resolve(resp)
        })
    },
    listUser: ({commit}, reqBody) => {
      return client.userApi.listUser(reqBody)
    }
  }
}
export default userModule
