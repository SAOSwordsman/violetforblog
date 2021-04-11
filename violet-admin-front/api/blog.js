import request from '@/utils/request'

export default {

  // 修改浏览量
  updateViews(id) {
    return request({
      url: `/adminservice/front/blog/updateViews/${id}`,
      method: 'post'
    })
  },

  // 1查询博客内容
  getBlogById(id) {
    return request({
      url: `/adminservice/front/blog/getBlogById/${id}`,
      method: 'get'
    })
  },
  // 2查询作者的所有博客
  getAuthorBlogList(id, current, limit, blogQuery) {
    return request({
      url: `/adminservice/front/blog/pageListById/${current}/${limit}/${id}`,
      method: 'post',
      data: blogQuery
    })
  },

  // 3根据博客id删除博客
  deleteBlog(blogId) {
    return request({
      url: `/adminservice/front/blog/${blogId}`,
      method: 'delete'
    })
  }

}
