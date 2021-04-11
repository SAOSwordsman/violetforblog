import request from '@/utils/request'
export default {

  //1 查询公告列表
  getSayList() {
    return request({
      url: `/adminservice/front/say/getFrontSay`,
      method: 'get'
    })
  },
  //2 通过id获取公告
  getFrontSayById(id) {
    return request({
      url: `/adminservice/front/say/getFrontSayById/${id}`,
      method: 'get'
    })
  },



}
