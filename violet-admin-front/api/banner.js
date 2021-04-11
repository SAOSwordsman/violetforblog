import request from '@/utils/request'

export default {
  getList() {
    return request({
      url: `/violetcms/bannerFront/getAllBanner`,
      method: 'get'
    })
  }

}
