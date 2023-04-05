import request from '@/utils/request'

// 查询赛鸽管理列表
export function listPigeonmgr(query) {
  return request({
    url: '/member/pigeonmgr/list',
    method: 'get',
    params: query
  })
}

// 查询赛鸽管理详细
export function getPigeonmgr(id) {
  return request({
    url: '/member/pigeonmgr/' + id,
    method: 'get'
  })
}

// 新增赛鸽管理
export function addPigeonmgr(data) {
  return request({
    url: '/member/pigeonmgr',
    method: 'post',
    data: data
  })
}

// 修改赛鸽管理
export function updatePigeonmgr(data) {
  return request({
    url: '/member/pigeonmgr',
    method: 'put',
    data: data
  })
}

// 删除赛鸽管理
export function delPigeonmgr(id) {
  return request({
    url: '/member/pigeonmgr/' + id,
    method: 'delete'
  })
}
