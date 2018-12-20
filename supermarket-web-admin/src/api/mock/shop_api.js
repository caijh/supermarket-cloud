import shopList from './data/shopes'
import mockUtil from './mock_util'

export default {
  list (reqBody) {
    return new Promise((resolve, reject) => {
      setTimeout(() => {
        resolve(mockUtil.success(mockUtil.buildPageResp(shopList)))
      })
    })
  },
  get (id) {
    const listFilterById = shopList.filter(shop => shop.id === id)
    if (listFilterById.length > 0) {
      return Promise.resolve(mockUtil.success(listFilterById[0]))
    } else {
      return Promise.reject(new Error('not found'))
    }
  }
}
