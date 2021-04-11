//封装axios
import axios from 'axios'
import {
  MessageBox,
  Message
} from 'element-ui'
import cookie from "js-cookie";
// 创建axios实例
const service = axios.create({
  baseURL: 'http://localhost:8222', // nginx的地址
  timeout: 20000 // 请求超时时间
})
//定义前端请求拦截器
//设置每次请求都进行拦截
service.interceptors.request.use(
  config => {
    //如果有cookie，则向请求头设置token
    if (cookie.get('violet_token')) {
      config.headers['token'] = cookie.get('violet_token');
    }
    return config
  },
  err => {
    return Promise.reject(err);
  })

// http response 响应拦截器
// service.interceptors.response.use(
//   response => {
//     //debugger
//     if (response.data.code == 28004) {
//       console.log("response.data.resultCode是28004")
//       // 返回 错误代码-1 清除ticket信息并跳转到登录页面
//       //debugger
//       window.location.href = "/login"
//       return
//     } else {
//       if (response.data.code !== 20000) {
//         //25000：订单支付中，不做任何提示
//         if (response.data.code != 25000) {
//           Message({
//             message: response.data.message || 'error',
//             type: 'error',
//             duration: 5 * 1000
//           })
//         }
//       } else {
//         return response;
//       }
//     }
//   },
//   error => {
//     return Promise.reject(error.response) // 返回接口返回的错误信息
//   });

export default service
