import request from '@/utils/request'

export default {

  // 1查询所有分类列表
  getAllCategoryList(current, limit, categoryQuery) {
    return request({
      url: `/adminservice/category/pageCategory/${current}/${limit}`,
      method: 'post',
      data: categoryQuery
    })
  },

  // 2根据博客id逻辑删除或恢复博客
  deleteCategoryId(categoryId) {
    return request({
      url: `/adminservice/category/deleteById/${categoryId}`,
      method: 'delete'
    })
  },

  // 3删除分类
  removeCategoryId(categoryId) {
    return request({
      url: `/adminservice/category/removeById/${categoryId}`,
      method: 'delete'
    })
  },
  // 4通过id查询分类
  getCategoryById(categoryId) {
    return request({
      url: `/adminservice/category/getCategoryById/${categoryId}`,
      method: 'get'
    })
  },
  // 4修改分类
  updateCategory(blogCategory) {
    return request({
      url: `/adminservice/category/updateCategory`,
      method: 'post',
      data: blogCategory
    })
  },
  // 5添加分类
  addCategory(blogCategory) {
    return request({
      url: `/adminservice/category/addCategory`,
      method: 'post',
      data: blogCategory
    })
  }

}
