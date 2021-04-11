<template>

  <el-row>
    <el-col :span="6">
      <el-card :body-style="{ padding: '0px' }">
        <img src="http://img.violet-nian.top/imgs/2020/07/7f840e4b31453224.png" class="image">
        <div style="padding: 14px;">
          <span>文章总数</span>
          <div class="bottom clearfix">
            <time class="time">{{ sum.blogs }}</time>
            <a href="http://localhost:9528/#/blog/table"><el-button type="text" class="button">More info-></el-button></a>
          </div>
        </div>
      </el-card>
    </el-col>
    <el-col :span="6">
      <el-card :body-style="{ padding: '0px' }">
        <img src="http://img.violet-nian.top/imgs/2020/07/305e0b80472a2a4e.jpg" class="image">
        <div style="padding: 14px;">
          <span>分类总数</span>
          <div class="bottom clearfix">
            <time class="time">{{ sum.blogs }}</time>
            <a href="http://localhost:9528/#/category/table"><el-button type="text" class="button">More info-></el-button></a>
          </div>
        </div>
      </el-card>
    </el-col>
    <el-col :span="7">
      <el-card :body-style="{ padding: '0px' }">
        <img src="http://img.violet-nian.top/imgs/2020/07/f6ea81bde4119801.jpg" class="image">
        <div style="padding: 14px;">
          <span>标签总数</span>
          <div class="bottom clearfix">
            <time class="time">{{ sum.tags }}</time>
            <a href="http://localhost:9528/#/tag/table"><el-button type="text" class="button">More info-></el-button></a>
          </div>
        </div>
      </el-card>
    </el-col>
    <el-col :span="6">
      <el-card :body-style="{ padding: '0px' }">
        <img src="http://img.violet-nian.top/imgs/2020/07/a6b8d34a0f8f39dc.jpg" class="image">
        <div style="padding: 14px;">
          <span>评论总数</span>
          <div class="bottom clearfix">
            <time class="time">{{ sum.comments }}</time>
            <a href="http://localhost:9528/#/comment/table"><el-button type="text" class="button">More info-></el-button></a>
          </div>
        </div>
      </el-card>
    </el-col>
    <el-col :span="6">
      <el-card :body-style="{ padding: '0px' }">
        <img src="http://img.violet-nian.top/imgs/2020/07/88ba6318b016f214.jpeg" class="image">
        <div style="padding: 14px;">
          <span>友链总数</span>
          <div class="bottom clearfix">
            <time class="time">{{ sum.friendLinks }}</time>
            <a href="http://localhost:9528/#/friend/table"><el-button type="text" class="button">More info-></el-button></a>
          </div>
        </div>
      </el-card>
    </el-col>
  </el-row>

<!--  <el-row :gutter="20">-->
<!--    <el-col :span="6" :offset="3">-->
<!--      <div class="grid-content bg-purple">-->
<!--        <div class="ha1">-->
<!--          <h1>{{ sum.blogs }}</h1>-->
<!--          <h5>-->
<!--            文章总数-->
<!--          </h5>-->
<!--          <el-button type="primary" >主要按钮</el-button>-->
<!--        </div>-->
<!--      </div>-->
<!--    </el-col>-->
<!--    <el-col :span="6" :offset="6">-->
<!--      <div class="grid-content bg-purple">-->
<!--        <el-button type="success">成功按钮</el-button>-->
<!--      </div>-->
<!--    </el-col>-->
<!--  </el-row>-->

</template>

<script>
import blogApi from '@/api/blog/blog'

export default {
  data() {
    return {
      sum: {},
      currentDate: new Date()
    }
  },
  created() {
    this.getSumBlog()
  },
  methods: {
    getSumBlog() {
      blogApi.getSum().then(response => {
        this.sum = response.data.sum
      })
    }
  }
}
</script>

<style scoped>
  .time {
    font-size: 20px;
    color: #999;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }
</style>
