<template>
  <div class="in-wrap">
    <!-- 公共头引入 -->
    <header id="header">
      <section class="container">
        <h1 id="logo">
          <a href="#" title="谷粒学院">
            <img src="~/assets/img/logo.png" width="100%" alt="谷粒学院" />
          </a>
        </h1>
        <div class="h-r-nsl">
          <ul class="nav">
            <router-link to="/" tag="li" active-class="current" exact>
              <a>首页</a>
            </router-link>
            <router-link to="/category" tag="li" active-class="current">
              <a>分类</a>
            </router-link>
            <router-link to="/tag" tag="li" active-class="current">
              <a>标签</a>
            </router-link>
            <router-link to="/say" tag="li" active-class="current">
              <a>公告</a>
            </router-link>
            <router-link to="/about" tag="li" active-class="current">
              <a>关于</a>
            </router-link>
            <router-link to="/friend" tag="li" active-class="current">
              <a>友链</a>
            </router-link>
          </ul>
          <!-- / nav -->
          <ul class="h-r-login">
            <!-- id不存在则说明没有用户，显示登录注册 -->
            <li v-if="!loginInfo.id" id="no-login">
              <a href="/login" title="登录">
                <em class="icon18 login-icon">&nbsp;</em>
                <span class="vam ml5">登录</span>
              </a>
              |
              <a href="/register" title="注册">
                <span class="vam ml5">注册</span>
              </a>
            </li>

            <li v-if="loginInfo.id" id="is-login-two" class="h-r-user">
              <el-dropdown>
                <span class="el-dropdown-link">
                  <img :src="loginInfo.avatar" width="30" height="30" class="vam picImg" alt />
                  <span id="userName" class="vam disIb">{{ loginInfo.nickname }}</span>
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item>
                    <a id="headerMsgCountId" href="/myBlog" title="消息">
                      <span  class="vam disIb">我的博客</span>
                    </a>
                  </el-dropdown-item>
                  <el-dropdown-item>
                    <a href="/edit" title="消息">
                      <span  class="vam disIb">写博客</span>
                    </a>
                  </el-dropdown-item>
                  <el-dropdown-item>
                    <a :href="'/user/'+loginInfo.id" title="消息">
                      <span  class="vam disIb">个人信息</span>
                    </a>
                  </el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>


              <a href="javascript:void(0);" title="退出" @click="logout()" class="ml5">退出</a>
            </li>
            <!-- /未登录显示第1 li；登录后显示第2，3 li -->
          </ul>
          <aside class="h-r-search">
            <form action="#" method="post">
              <label class="h-r-s-box">
                <input type="text" placeholder="输入你想搜索的文章" name="queryCourse.courseName" value />
                <button type="submit" class="s-btn">
                  <em class="icon18">&nbsp;</em>
                </button>
              </label>
            </form>
          </aside>
        </div>
        <aside class="mw-nav-btn">
          <div class="mw-nav-icon"></div>
        </aside>
        <div class="clear"></div>
      </section>
    </header>
    <!-- /公共头引入 -->

    <nuxt />

    <!-- 公共底引入 -->


    <footer class="foot">
      <section class="container">
        <div id="layout-footer">
          <div class="footer-main">
            <div class="footer-item">
              <div style="font-size:17px;font-weight: bold;">资源</div>
              <div><a target="_blank" class="out-link" href="http://violet-nian.top/">我的博客</a></div>
            </div>
          </div>
          <div class="copyright">Copyright © 2020 by <a target="_blank" class="out-link" href="http://violet-nian.top/">violet-nian.top</a> . All rights reserved. | <a target="_blank" class="out-link" href="http://www.beian.miit.gov.cn">渝ICP备17015355号-1</a></div>
        </div>
      </section>
    </footer>
    <!-- /公共底引入 -->
  </div>
</template>

<script>
  import '~/assets/css/reset.css'
  import '~/assets/css/theme.css'
  import '~/assets/css/global.css'
  import '~/assets/css/web.css'
  import '~/assets/css/base.css'
  import '~/assets/css/activity_tab.css'
  import '~/assets/css/bottom_rec.css'
  import '~/assets/css/nice_select.css'
  import '~/assets/css/order.css'
  import '~/assets/css/swiper-3.3.1.min.css'

  import cookie from "js-cookie";

  export default {

    data() {
      return {
        //token
        token: "",
        //用户信息
        loginInfo: {
          id: "",
          age: "",
          avatar: "",
          mobile: "",
          nickname: "",
          sex: ""
        }
      }
    },
    created() {
      //获取token
      this.getUserInfo();
    },
    methods: {
      //获取用户信息
      getUserInfo() {
        //获取的是Json字符串，需要转成Json对象，从后端获取的Json都是字符串形式的
        var userStr = cookie.get("violet_ucenter");
        //若有用户信息，则取出用户信息
        if (userStr) {
          //获取用户信息
          this.loginInfo = JSON.parse(userStr);
        }
      },
      //退出
      logout() {
        //清空cookie的值，还是使用set方法，把值替换成""即可
        cookie.set("violet_ucenter", "", { domain: "localhost" });
        cookie.set("violet_token", "", { domain: "localhost" });
        //跳转首页
        window.location.href = "/";
      }
    }

  }
</script>
<style scoped lang="less">

  #layout-footer{
    padding: 2%;
    border-top: 1px solid #95ecf7;
    font-size: 13px;
    color: #9c954c;
    a.out-link:hover{
      color: #ff6d6d;
    }
    .footer-main{
      max-width: 800px;
      margin: 0 auto 20px auto;
      display: flex;
      justify-content: space-around;
      align-items: flex-start;
      text-align: center;
      .footer-item{
        flex: 1;
        & > div:not(:last-child){
          margin-bottom: 10px;
        }
        i{
          margin-right: 10px;
        }
      }
    }
    .copyright{
      text-align: center;
    }
  }
  /*****/
  @media (max-width: 800px){
    #layout-footer{
      .footer-main .footer-item:nth-child(3){
        flex: 2;
      }
    }
  }
  @media (max-width: 600px){
    #layout-footer{
      .footer-main {
        display: block;
        .footer-item{
          display: flex;
          justify-content: space-around;
          align-items: center;
          flex-wrap: wrap;
          & > div{
            margin-bottom: 10px;
          }
        }
      }
    }
  }

</style>
