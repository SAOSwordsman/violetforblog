import request from '@/utils/request'

export default {

  // 1查询所有公告列表
  getAllSayList(current, limit, sayQuery) {
    return request({
      url: `/adminservice/lvSay/pageSay/${current}/${limit}`,
      method: 'post',
      data: sayQuery
    })
  },

  // 2删除公告
  removeSayId(id) {
    return request({
      url: `/adminservice/lvSay/removeById/${id}`,
      method: 'delete'
    })
  },
  // 3通过id查询公告
  getSayById(id) {
    return request({
      url: `/adminservice/lvSay/getSayById/${id}`,
      method: 'get'
    })
  },
  // 4修改分类
  updateSay(lvSay) {
    return request({
      url: `/adminservice/lvSay/updateSay`,
      method: 'post',
      data: lvSay
    })
  },
  // 5添加分类
  addSay(lvSay) {
    return request({
      url: `/adminservice/lvSay/addSay`,
      method: 'post',
      data: lvSay
    })
  }

}
