import request from '@/utils/request'

export default {

  // 1博客详情
  getSum() {
    return request({
      url: `/adminservice/blog/sum`,
      method: 'get'
    })
  },

  // 2查询所有博客列表
  getAllBlogList(current, limit, blogQuery) {
    return request({
      url: `/adminservice/blog/pageBlog/${current}/${limit}`,
      method: 'post',
      data: blogQuery
    })
  },

  // 3根据博客id删除博客
  deleteBlog(blogId) {
    return request({
      url: `/adminservice/blog/${blogId}`,
      method: 'delete'
    })
  }

}
