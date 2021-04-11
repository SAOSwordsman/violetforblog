import request from '@/utils/request'

export default {

  // 1查询所有博客列表
  getAllBlogList(current, limit) {
    return request({
      url: `/adminservice/front/blog/pageList/${current}/${limit}`,
      method: 'get'
    })
  }

}
