import request from '@/utils/request'
export default {

  //1 查询分类列表
  getTagList() {
    return request({
      url: `/adminservice/front/tag/getTagList`,
      method: 'get'
    })
  },

  //2 根据分类获取博客
  getBlogByTag(page, limit, tag) {
    return request({
      url: `/adminservice/front/tag/getBlogByTag/${page}/${limit}`,
      method: 'post',
      data: tag
    })
  },

}
