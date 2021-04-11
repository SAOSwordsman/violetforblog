import request from '@/utils/request'

export default {

  // 1分页查询所有友链
  getAllFriendList(current, limit, friendQuery) {
    return request({
      url: `/adminservice/friend/pageFriend/${current}/${limit}`,
      method: 'post',
      data: friendQuery
    })
  },

  // 2根据友链id删除友链
  removeFriendId(friendId) {
    return request({
      url: `/adminservice/friend/removeById/${friendId}`,
      method: 'delete'
    })
  },

  // 3通过id查询分类
  getFriendById(friendId) {
    return request({
      url: `/adminservice/friend/getFriendById/${friendId}`,
      method: 'get'
    })
  },
  // 4修改友链
  updateFriend(friendlink) {
    return request({
      url: `/adminservice/friend/updateFriend`,
      method: 'post',
      data: friendlink
    })
  },
  // 5添加友链
  addFriend(friendlink) {
    return request({
      url: `/adminservice/friend/addFriend`,
      method: 'post',
      data: friendlink
    })
  }

}
