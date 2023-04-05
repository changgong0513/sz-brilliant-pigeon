import request from '@/utils/request'

// 查询会员信息列表
export function listSzbpuser(query) {
  return request({
    url: '/member/szbpuser/list',
    method: 'get',
    params: query
  })
}

// 查询会员信息详细
export function getSzbpuser(id) {
  return request({
    url: '/member/szbpuser/' + id,
    method: 'get'
  })
}

// 新增会员信息
export function addSzbpuser(data) {
  return request({
    url: '/member/szbpuser',
    method: 'post',
    data: data
  })
}

// 修改会员信息
export function updateSzbpuser(data) {
  return request({
    url: '/member/szbpuser',
    method: 'put',
    data: data
  })
}

// 删除会员信息
export function delSzbpuser(id) {
  return request({
    url: '/member/szbpuser/' + id,
    method: 'delete'
  })
}
