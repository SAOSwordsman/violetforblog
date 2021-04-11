import request from '@/utils/request'
export default {

  //1 查询分类列表
  getCategoryList() {
    return request({
      url: `/adminservice/front/category/getCategoryList`,
      method: 'get'
    })
  },

  //2 根据分类获取博客
  getBlogByCategory(page, limit, category) {
    return request({
      url: `/adminservice/front/category/getBlogByCategory/${page}/${limit}`,
      method: 'post',
      data: category
    })
  },

}
