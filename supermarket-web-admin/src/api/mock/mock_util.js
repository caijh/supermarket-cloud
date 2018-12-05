export default {
  success: (data) => {
    return {data: data}
  },
  buildPageResp: data => {
    let totalElements = data.length
    let totalPage = totalElements / 10
    if (data % 10 > 0) {
      totalPage += 1
    }
    return {
      'content': data,
      'pageable': {
        'sort': {
          'sorted': false,
          'unsorted': true
        },
        'offset': 0,
        'pageNumber': 0,
        'pageSize': 10,
        'paged': true,
        'unpaged': false
      },
      'totalElements': totalElements,
      'last': true,
      'totalPages': totalPage,
      'size': 10,
      'number': 0,
      'sort': {
        'sorted': false,
        'unsorted': true
      },
      'numberOfElements': 1,
      'first': true
    }
  }
}
