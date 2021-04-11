import request from '@/utils/request'
export default {

  // 登录
  submitLoginUser(loginVo) {
    return request({
      url: `/violetcenter/user/login`,
      method: 'post',
      data: loginVo
    })
  },
  // 根据token获取用户信息
  getLoginUserInfo() {
    return request({
      url: `/violetcenter/user/getMemberInfo`,
      method: 'get'
    // headers: {'token': cookie.get('guli_token')}
    })
  }
}
