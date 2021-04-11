<template>
  <div class="main">
    <div class="title">
      <a class="active" href="/login">登录</a>
      <span>·</span>
      <a href="/register">注册</a>
    </div>
    <div class="sign-up-container">
      <el-form ref="userForm" :model="user">
        <el-form-item :rules="[{required: true, message: '请输入手机号码', trigger: 'blur' },{validator:checkPhone, trigger: 'blur'}]" class="input-prepend restyle" prop="mobile" >
          <div >
            <el-input v-model="user.mobile" type="text" placeholder="手机号" />
            <i class="iconfont icon-phone" />
          </div>
        </el-form-item>
        <el-form-item :rules="[{ required:true, message: '请输入密码', trigger: 'blur' }]" class="input-prepend" prop="password" >
          <div>
            <el-input v-model="user.password" type="password" placeholder="密码" />
            <i class="iconfont icon-password"/>
          </div>
        </el-form-item>
        <div class="btn">
          <input type="button" class="sign-in-button" value="登录" @click="submitLogin">
        </div>
      </el-form>

    </div>
  </div>
</template>
<script>
import '~/assets/css/sign.css'
import '~/assets/css/iconfont.css'
import cookie from 'js-cookie'
import loginApi from '@/api/login'

export default {
  layout: 'sign',
  data() {
    return {
      user: {
        mobile: '',
        password: ''
      },
      loginInfo: {}
    }
  },
  methods: {
    // 登录的方法
    submitLogin() {
      // 第一步，调用接口进行登录，返回token字符串
      loginApi.submitLoginUser(this.user).then(response => {
        // 第二步，获取token字符串放到cookie里面
        // 第一个参数cookie名称，第二个参数值，第三个参数作用范围
        cookie.set('violet_token', response.data.data.token, { domain: 'localhost' })
        // 第四步 调用接口，根据token获取用户信息
        loginApi.getLoginUserInfo().then(response => {
          this.loginInfo = response.data.data.userInfoMember
          cookie.set('violet_ucenter', this.loginInfo, { domain: 'localhost' })
        })
        // 跳转按钮
        window.location.href = '/'
      })
    },
    checkPhone(rule, value, callback) {
      // debugger
      if (!(/^1[34578]\d{9}$/.test(value))) {
        return callback(new Error('手机号码格式不正确'))
      }
      return callback()
    }
  }
}
</script>
<style>
  .el-form-item__error{
    z-index: 9999999;
  }
</style>
