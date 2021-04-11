import request from '@/utils/request'
export default {

  //1 根据id获取用户
  getUserById(id){
    return request({
      url: `/violetcenter/user/getUserById/${id}`,
      method: 'get'
    })
  },

  //2 修改用户
  updateUser(userInfo){
    return request({
      url: `/violetcenter/user/updateUser`,
      method: 'post',
      data: userInfo
    })
  }



}
