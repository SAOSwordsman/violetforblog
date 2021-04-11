<template>
  <div>
    <div class="block">
      <el-carousel height="500px">
        <el-carousel-item v-for="banner in bannerList" :key="banner.id" class="swiper-slide">
          <a :href="banner.linkUrl" target="_blank" >
            <img :src="banner.imageUrl" :alt="banner.title">
          </a>
        </el-carousel-item>
      </el-carousel>
    </div>


    <!-- 博客列表 开始 -->
    <div>
      <section class="container">
        <header class="comm-title">
          <h2 class="tac">
            <span class="c-333">博客列表</span>
          </h2>
        </header>
        <div>
          <article class="comm-course-list">
            <ul id="bna" class="of">
              <li v-for="blog in list" :key="blog.id">
                <div class="cc-l-wrap">
                  <section class="course-img">
                    <img
                      :src="blog.authorAvatar"
                      :alt="blog.authorName"
                      class="img-responsive"
                    >
                    <div class="cc-mask">
                      <a :href="'/blog/'+blog.id" title="开始阅读" class="comm-btn c-btn-1">开始阅读</a>
                    </div>
                  </section>
                  <h3 class="hLh30 txtOf mt10">
                    <a :title="blog.title" :href="'/blog/'+blog.id" class="course-title fsize18 c-333">{{ blog.title }}</a>
                  </h3>
                  <section class="mt10 hLh20 of">
                      <span class="fr jgTag bg-green">
                        <i class="c-fff fsize12 f-fA">{{ blog.categoryName}}</i>
                        |
                        <i class="c-fff fsize12 f-fA">{{ blog.blogTags }}</i>
                      </span>
                    <span class="fl jgAttr c-ccc f-fA">
                        <i class="c-999 f-fA">{{ blog.views }}</i>
                        |
                        <i class="c-999 f-fA">{{ blog.gmtCreate }}</i>
                      </span>
                  </section>
                </div>
              </li>
            </ul>
            <div class="clear"/>
          </article>
        </div>
      </section>
    </div>

    <!-- 分页 -->
    <el-pagination :current-page="page" :page-size="limit" :total="total" style="padding: 30px 0; text-align: center;" layout="total, prev, pager, next, jumper" @current-change="getListBlog" />
  </div>


</template>
<script>
  import banner from '@/api/banner'
  import indexApi from '@/api/index'

  export default {
    data() {
      return {
        bannerList: {},
        list: {},
        page: 1,
        limit: 8,
        total: 0,
      }
    },
    created() {
      this.initDataBanner()
      this.getListBlog()
    },
    methods: {
      initDataBanner() {
        banner.getList().then(response => {
          this.bannerList = response.data.data.list
        })
      },
      // 查询所有课程
      getListBlog(page = 1) {
        this.page = page
        indexApi.getAllBlogList(this.page, this.limit)
          .then(response => { // 请求成功
            // response接口返回的数据
            this.list = response.data.data.rows
            this.total = response.data.data.total
          })
      }
    }
  }
</script>
