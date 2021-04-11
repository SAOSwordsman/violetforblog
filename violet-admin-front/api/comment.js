import request from '@/utils/request'
export default {

  // 获取评论列表
  getCommentList(blogId) {
    return request({
      url: `/adminservice/front/comment/getCommentList/${blogId}`,
      method: 'get'
    })
  },
  // 增加评论
  addComment(a) {
    return request({
      url: `/adminservice/front/comment/addComment`,
      method: 'post',
      data: a
    })
  },

}
