import request from '@/utils/request'

export default {

  // 1查询所有友链列表
  getAllFriendList(current, limit) {
    return request({
      url: `/adminservice/front/friend/pageFriend/${current}/${limit}`,
      method: 'post'
    })
  }

}
