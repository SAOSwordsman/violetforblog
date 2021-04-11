<template>
  <div class="container">
    <el-container direction="vertical" class="detail-container">
      <el-row type="flex" justify="center" >
        <el-col>
          <div class="detail-blog-box">
            <h2>{{blog.title}}</h2>
            <el-row class="detail-inscribe" type="flex" align="bottom" justify="start">
              <el-col :span="12" class="el-col-category" >
                <ul>分类：
                  <li >{{ blog.categoryName }}</li>
                </ul>
              </el-col>
              <el-col :span="10">作者：{{blog.authorName}}</el-col>
            </el-row>

            <article class="detail-blog-article" v-html="blog.content"></article>
          </div>
        </el-col>
      </el-row>

    </el-container>

    <br><br><br>

<!--    评论功能-->
    <div>
      <div v-clickoutside="hideReplyBtn" @click="inputFocus" class="my-reply">
        <el-avatar class="header-img" :size="40" :src="myHeader"></el-avatar>
        <div class="reply-info" >
          <div
            tabindex="0"
            contenteditable="true"
            id="replyInput"
            spellcheck="false"
            placeholder="输入评论..."
            class="reply-input"
            @focus="showReplyBtn"
            @input="onDivInput($event)"
          >
          </div>
        </div>
        <div class="reply-btn-box" v-show="btnShow">
          <el-button class="reply-btn" size="medium" @click="sendComment" type="primary">发表评论</el-button>
        </div>
      </div>
      <div v-for="(item,i) in comments" :key="i" class="author-title reply-father">
        <el-avatar class="header-img" :size="40" :src="item.headImg"></el-avatar>
        <div class="author-info">
          <span class="author-name">{{item.name}}</span>
          <span class="author-time">{{item.time}}</span>
        </div>
        <div class="icon-btn">
          <span @click="showReplyInput(i,item.name,item.id)"><i class="iconfont el-icon-s-comment"></i>回复</span>
        </div>
        <div class="talk-box">
          <p>
            <span class="reply">{{item.comment}}</span>
          </p>
        </div>
        <div class="reply-box">
          <div v-for="(reply,j) in item.reply" :key="j" class="author-title">
            <el-avatar class="header-img" :size="40" :src="reply.fromHeadImg"></el-avatar>
            <div class="author-info">
              <span class="author-name">{{reply.from}}</span>
              <span class="author-time">{{reply.time}}</span>
            </div>
            <div class="icon-btn">
              <span @click="showReplyInput(i,reply.from,reply.id)"><i class="iconfont el-icon-s-comment"></i>回复</span>

            </div>
            <div class="talk-box">
              <p>
                <span>回复 {{reply.to}}:</span>
                <span class="reply">{{reply.comment}}</span>
              </p>
            </div>
            <div class="reply-box">

            </div>
          </div>
        </div>
        <div  v-show="_inputShow(i)" class="my-reply my-comment-reply">
          <el-avatar class="header-img" :size="40" :src="myHeader"></el-avatar>
          <div class="reply-info" >
            <div tabindex="0" contenteditable="true" spellcheck="false" placeholder="输入评论..."   @input="onDivInput($event)"  class="reply-input reply-comment-input"></div>
          </div>
          <div class=" reply-btn-box">
            <el-button class="reply-btn" size="medium" @click="sendCommentReply(i)" type="primary">发表评论</el-button>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
  import blogApi from '@/api/blog'
  import commentApi from '@/api/comment'
  import cookie from "js-cookie";

  //这是评论方法
  const clickoutside = {
    // 初始化指令
    bind(el, binding, vnode) {
      function documentHandler(e) {
        // 这里判断点击的元素是否是本身，是本身，则返回
        if (el.contains(e.target)) {
          return false;
        }
        // 判断指令中是否绑定了函数
        if (binding.expression) {
          // 如果绑定了函数 则调用那个函数，此处binding.value就是handleClose方法
          binding.value(e);
        }
      }
      // 给当前元素绑定个私有变量，方便在unbind中可以解除事件监听
      el.vueClickOutside = documentHandler;
      document.addEventListener('click', documentHandler);
    },
    update() {},
    unbind(el, binding) {
      // 解除事件监听
      document.removeEventListener('click', el.vueClickOutside);
      delete el.vueClickOutside;
    },
  };

    export default {
      data() {
        return {
          //用户信息
          loginInfo: {
            id: "",
            avatar: "",
            nickname: "",
          },
          id: '',
          blog: {},

          btnShow: false,
          index:'0',
          replyComment:'',
          myName: '',
          myHeader: '',
          myId: '',
          to:'',
          toId:-1,
          comments: []
        }
      },
      directives: {clickoutside},

      created() {
        if(this.$route.params&&this.$route.params.id){
          this.id = this.$route.params.id
          this.getBlog(this.id)
        }
        this.updateViewsById()
        this.getUserInfo()
        this.getComment()
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
          //赋值给评论信息
          this.myId = this.loginInfo.id
          this.myName = this.loginInfo.nickname
          this.myHeader= this.loginInfo.avatar
        },
        // 修改浏览量
        updateViewsById(){
          blogApi.updateViews(this.id)
        },
        getBlog(id){
          blogApi.getBlogById(id).then(response =>{
            this.blog=response.data.data.blog
          })
        },
        getComment(){
          commentApi.getCommentList(this.id).then(response =>{
            this.comments = response.data.data.comments
          })
        },



        //以下为评论功能
        inputFocus(){
          var replyInput = document.getElementById('replyInput');
          replyInput.style.padding= "8px 8px"
          replyInput.style.border ="2px solid blue"
          replyInput.focus()
        },
        showReplyBtn(){
          this.btnShow = true
        },
        hideReplyBtn(){
          this.btnShow = false
          replyInput.style.padding= "10px"
          replyInput.style.border ="none"
        },
        showReplyInput(i,name,id){
          this.comments[this.index].inputShow = false
          this.index =i
          this.comments[i].inputShow = true
          this.to = name
          this.toId = id
        },
        _inputShow(i){
          return this.comments[i].inputShow
        },
        sendComment(){
          var userStr = cookie.get("violet_ucenter");
          //若有没有用户信息，则跳转到登录用户
          if (!userStr) {
            window.location.href = '/login'
          }else{
            if(!this.replyComment){
              this.$message({
                showClose: true,
                type:'warning',
                message:'评论不能为空'
              })
            }else{
              let a ={}
              let input =  document.getElementById('replyInput')
              let timeNow = new Date().getTime();
              let time= this.dateStr(timeNow);
              a.id = this.id
              a.myId = this.myId
              a.name= this.myName
              a.comment =this.replyComment
              a.headImg = this.myHeader

              commentApi.addComment(a)
              this.comments.push(a)

              this.replyComment = ''
              input.innerHTML = '';

            }
          }

        },
        sendCommentReply(i){
          var userStr = cookie.get("violet_ucenter");
          //若有没有用户信息，则跳转到登录用户
          if (!userStr) {
            window.location.href = '/login'
          }else{
            if(!this.replyComment){
              this.$message({
                showClose: true,
                type:'warning',
                message:'评论不能为空'
              })
            }else{
              let a ={}
              let b ={}
              let timeNow = new Date().getTime();
              let time= this.dateStr(timeNow);
              b.id = this.id
              b.myId = this.myId
              b.name= this.myName
              b.comment =this.replyComment
              b.headImg = this.myHeader
              b.com = this.comments[i].comment

              a.from= this.myName
              a.to = this.to
              a.fromHeadImg = this.myHeader
              a.comment =this.replyComment
              a.time = time

              commentApi.addComment(b)
              this.comments[i].reply.push(a)

              this.replyComment = ''
              document.getElementsByClassName("reply-comment-input")[i].innerHTML = ""
            }
          }

        },
        onDivInput: function(e) {
          this.replyComment = e.target.innerHTML;
        },
        dateStr(date){
          //获取js 时间戳
          var time=new Date().getTime();
          //去掉 js 时间戳后三位，与php 时间戳保持一致
          time=parseInt((time-date)/1000);
          //存储转换值
          var s;
          if(time<60*10){//十分钟内
            return '刚刚';
          }else if((time<60*60)&&(time>=60*10)){
            //超过十分钟少于1小时
            s = Math.floor(time/60);
            return  s+"分钟前";
          }else if((time<60*60*24)&&(time>=60*60)){
            //超过1小时少于24小时
            s = Math.floor(time/60/60);
            return  s+"小时前";
          }else if((time<60*60*24*30)&&(time>=60*60*24)){
            //超过1天少于30天内
            s = Math.floor(time/60/60/24);
            return s+"天前";
          }else{
            //超过30天ddd
            var date= new Date(parseInt(date));
            return date.getFullYear()+"/"+(date.getMonth()+1)+"/"+date.getDate();
          }
        }
      }
    }
</script>

<style lang="stylus" scoped>
  .detail-container{
    width: 850px;
    background: #fff;
    -webkit-border-radius: 8px;
    -moz-border-radius: 8px;
    border-radius: 8px;
  }
  .detail-blog-box{
    line-height: 30px;
    padding: 20px;
    border-radius: 8px;
  }
  .detail-blog-box h2{
    text-align: center;
    margin-bottom: 10px;
  }
  .detail-blog-article{
    color: #696969;
    text-indent: 2em;
  }
  .detail-inscribe{
    font-size: 13px;
    color: #8c8c8c;
  }
  .blog-edit{
    text-align: right;
  }
  .blog-edit a{
    width: 50px;
    height: 30px;
    line-height: 30px;
    display:inline-block;
    margin-left: 10px;
    text-align: center;
    cursor: pointer;
  }
  .blog-edit a:first-child{
    color: blue;
  }
  .blog-edit a:last-child{
    color: red;
  }
  .el-col-category ul li{
    list-style: none;
    padding: 0;
    margin-right: 10px;
    display: inline-block;
  }




  .my-reply
    padding 10px
    background-color #fafbfc
    .header-img
      display inline-block
      vertical-align top
    .reply-info
      display inline-block
      margin-left 5px
      width 90%
      @media screen and (max-width:1200px) {
        width 80%
      }
      .reply-input
        min-height 20px
        line-height 22px
        padding 10px 10px
        color #ccc
        background-color #fff
        border-radius 5px
        &:empty:before
          content attr(placeholder)
        &:focus:before
          content none
        &:focus
          padding 8px 8px
          border 2px solid blue
          box-shadow none
          outline none
    .reply-btn-box
      height 25px
      margin 10px 0
      .reply-btn
        position relative
        float right
        margin-right 15px
  .my-comment-reply
    margin-left 50px
    .reply-input
      width flex
  .author-title:not(:last-child)
    border-bottom: 1px solid rgba(178,186,194,.3)
  .author-title
    padding 10px
    .header-img
      display inline-block
      vertical-align top
    .author-info
      display inline-block
      margin-left 5px
      width 60%
      height 40px
      line-height 20px
      >span
        display block
        cursor pointer
        overflow hidden
        white-space nowrap
        text-overflow ellipsis
      .author-name
        color #000
        font-size 18px
        font-weight bold
      .author-time
        font-size 14px
    .icon-btn
      width 30%
      padding 0 !important
      float right
      @media screen and (max-width : 1200px){
        width 20%
        padding 7px
      }
      >span
        cursor pointer
      .iconfont
        margin 0 5px
    .talk-box
      margin 0 50px
      >p
        margin 0
      .reply
        font-size 16px
        color #000
    .reply-box
      margin 10px 0 0 50px
      background-color #efefef

</style>

