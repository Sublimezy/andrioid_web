import request from '@/utils/request'

// 查询驾考题目列表
export function listBook(query) {
  return request({
    url: '/app/book/list',
    method: 'get',
    params: query
  })
}

// 查询驾考题目详细
export function getBook(id) {
  return request({
    url: '/app/book/' + id,
    method: 'get'
  })
}

// 新增驾考题目
export function addBook(data) {
  return request({
    url: '/app/book',
    method: 'post',
    data: data
  })
}

// 修改驾考题目
export function updateBook(data) {
  return request({
    url: '/app/book',
    method: 'put',
    data: data
  })
}

// 删除驾考题目
export function delBook(id) {
  return request({
    url: '/app/book/' + id,
    method: 'delete'
  })
}



export function listDrivingBook(query) {
  return request({
    url: '/queryDriving',
    method: 'get',
    params: query
  })
}
