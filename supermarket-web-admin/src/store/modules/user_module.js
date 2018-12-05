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
    users: []
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
    },
    listUser: ({commit}, reqBody) => {
      return client.userApi.listUser(reqBody)
    }
  }
}
export default userModule
