import request from '@/utils/request'

export function login(userName, password) {
  return request({
    url: '/adminservice/user/login',
    method: 'post',
    data: {
      userName,
      password
    }
  })
}

export function getInfo(token) {
  return request({
    url: `/adminservice/user/info/${token}`,
    method: 'get',
    // params: { token }
  })
}

export function logout() {
  return request({
    url: '/adminservice/user/logout',
    method: 'post'
  })
}
