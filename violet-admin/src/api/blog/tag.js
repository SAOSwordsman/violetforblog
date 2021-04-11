import request from '@/utils/request'

export default {

  // 1查询所有分类列表
  getAllTagList(current, limit, tagQuery) {
    return request({
      url: `/adminservice/tag/pageTag/${current}/${limit}`,
      method: 'post',
      data: tagQuery
    })
  },

  // 2根据博客id逻辑删除或恢复博客
  deleteTagId(tagId) {
    return request({
      url: `/adminservice/tag/deleteById/${tagId}`,
      method: 'delete'
    })
  },

  // 3删除分类
  removeTagId(tagId) {
    return request({
      url: `/adminservice/tag/removeById/${tagId}`,
      method: 'delete'
    })
  },
  // 4通过id查询分类
  getTagById(tagId) {
    return request({
      url: `/adminservice/tag/getTagById/${tagId}`,
      method: 'get'
    })
  },
  // 4修改分类
  updateTag(blogTag) {
    return request({
      url: `/adminservice/tag/updateTag`,
      method: 'post',
      data: blogTag
    })
  },
  // 5添加分类
  addTag(blogTag) {
    return request({
      url: `/adminservice/tag/addTag`,
      method: 'post',
      data: blogTag
    })
  }

}
