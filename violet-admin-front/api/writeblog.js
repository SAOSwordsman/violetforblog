import request from '@/utils/request'

export default {

  // 1查询所有分类
  getCategorys(){
    return request({
      url: `/adminservice/front/writeblog/getAllCategory`,
      method: 'get'
    })
  },
  // 2查询所有标签
  getTags(){
    return request({
      url: `/adminservice/front/writeblog/getAllTag`,
      method: 'get'
    })
  },

  //3 添加博客
  addBlog(blogInfo){
    return request({
      url: `/adminservice/front/writeblog/addBlog`,
      method: 'post',
      data: blogInfo
    })
  },

  //4 通过id查询博客
  getBlogById(id){
    return request({
      url: `/adminservice/front/writeblog/getBlogById/${id}`,
      method: 'get'
    })
  },

  //5 修改博客
  updateBlog(blogInfo){
    return request({
      url: `/adminservice/front/writeblog/updateBlog`,
      method: 'post',
      data: blogInfo
    })
  }
}
