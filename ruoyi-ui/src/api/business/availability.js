import request from '@/utils/request'

// 查询咨询师单日咨询时段安排列表
export function listAvailability(query) {
  return request({
    url: '/business/availability/list',
    method: 'get',
    params: query
  })
}

// 查询咨询师单日咨询时段安排详细
export function getAvailability(taskId) {
  return request({
    url: '/business/availability/' + taskId,
    method: 'get'
  })
}

// 新增咨询师单日咨询时段安排
export function addAvailability(data) {
  return request({
    url: '/business/availability',
    method: 'post',
    data: data
  })
}

// 修改咨询师单日咨询时段安排
export function updateAvailability(data) {
  return request({
    url: '/business/availability',
    method: 'put',
    data: data
  })
}

// 删除咨询师单日咨询时段安排
export function delAvailability(taskId) {
  return request({
    url: '/business/availability/' + taskId,
    method: 'delete'
  })
}
