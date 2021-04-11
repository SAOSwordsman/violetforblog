import request from '@/utils/request'

export default {

  // 1查询所有分类列表
  getAllUserList(current, limit, memberQuery) {
    return request({
      url: `/violetcenter/member/pageUser/${current}/${limit}`,
      method: 'post',
      data: memberQuery
    })
  },

  // 2根据博客id逻辑删除或恢复博客
  deleteUserId(id) {
    return request({
      url: `/violetcenter/member/deleteById/${id}`,
      method: 'delete'
    })
  },

  // 3删除分类
  removeUserId(id) {
    return request({
      url: `/violetcenter/member/removeById/${id}`,
      method: 'delete'
    })
  }
}
