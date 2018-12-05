import users from './data/users'
import userLoginResp from './data/user_login'
import resources from './data/resources'
import mockUtil from './mock_util'

export default {
  login (username, password) {
    return new Promise((resolve, reject) => {
      const usersFilterByAccount = users.filter(user => user.account === username)
      if (usersFilterByAccount.length > 0) {
        let user = usersFilterByAccount[0]
        if (username === user.account && password === user.password) {
          setTimeout(() => {
            resolve(mockUtil.success(userLoginResp))
          }, 1000)
        } else {
          setTimeout(() => {
            reject(new Error('username or password error'))
          }, 1000)
        }
      } else {
        setTimeout(() => {
          reject(new Error('username not found'))
        }, 1000)
      }
    })
  },
  logout (tokenType, token) {
    return new Promise(resolve => {
      setTimeout(() => {
        resolve()
      }, 1000)
    })
  },
  getUserDetail (userId) {
    return new Promise((resolve, reject) => {
      const usersFilterById = users.filter(user => user.id === userId)
      if (usersFilterById.length > 0) {
        setTimeout(() => {
          resolve(mockUtil.success(usersFilterById[0]))
        }, 1000)
      } else {
        reject(new Error('user not found'))
      }
    })
  },
  getResources (userId) {
    return new Promise((resolve, reject) => {
      setTimeout(() => {
        resolve(mockUtil.success(resources))
      })
    })
  },
  listUser (reqBody) {
    return new Promise((resolve, reject) => {
      setTimeout(() => {
        resolve(mockUtil.success(mockUtil.buildPageResp(users)))
      })
    })
  }
}
