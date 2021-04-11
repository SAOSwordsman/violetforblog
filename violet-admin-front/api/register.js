import request from '@/utils/request'

export default {

  // 根据手机号码发送短信
  sendCode(mobile) {
    return request({
      url: `/violetmsm/msm/send/${mobile}`,
      method: 'get'
    })
  },

  // 注册
  registerMember(formItem) {
    return request({
      url: `/violetcenter/user/register`,
      method: 'post',
      data: formItem
    })
  }
}
