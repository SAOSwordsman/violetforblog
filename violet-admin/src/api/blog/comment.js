import request from '@/utils/request'

export default {

  // 1查询所有博客列表
  getAllCommentList(current, limit, commentQuery) {
    return request({
      url: `/adminservice/comment/pageComment/${current}/${limit}`,
      method: 'post',
      data: commentQuery
    })
  },

  // 2根据博客id删除评论
  deleteComment(id) {
    return request({
      url: `/adminservice/comment/${id}`,
      method: 'delete'
    })
  }

}
