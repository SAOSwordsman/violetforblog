<template>

    <div id="aCoursesList" class="bg-fa of">
      <!-- /课程列表 开始 -->
      <section class="container">
        <header class="comm-title">
          <h2 class="fl tac">
            <span class="c-333">全部分类</span>
          </h2>
        </header>
        <section class="c-sort-box">
          <section class="c-s-dl">
            <dl>
              <dt>
                <span class="c-999 fsize14">分类类别</span>
              </dt>
              <dd class="c-s-dl-li">
                <ul class="clearfix">
                  <li v-for="(item, index) in categoryList" :key="index" :class="{active:oneIndex==index}">
                    <a :title="item.category" href="#" @click="searchOne(item.category,index)">{{ item.category }}</a>
                  </li>
                </ul>
              </dd>
            </dl>
            <div class="clear"/>
          </section>
        </section>
        <div class="mt40">
          <!-- /无数据提示 开始-->
          <section v-if="data.total == 0" class="no-data-wrap" >
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam">没有相关文章，博主正在努力创作中...</span>
          </section>
          <!-- /无数据提示 结束-->
          <article class="comm-course-list">
            <ul id="bna" class="of" >
              <li v-for="item in data.items" :key="item">
                <div class="cc-l-wrap">
                  <section class="course-img">
                    <img :src="item.authorAvatar" :alt="item.authorName" class="img-responsive">
                    <div class="cc-mask">
                      <a :href="'/blog/'+item.id" title="开始阅读" class="comm-btn c-btn-1">开始阅读</a>
                    </div>
                  </section>
                  <h3 class="hLh30 txtOf mt10">
                    <a :href="'/blog/'+item.id" :title="item.title" class="course-titlefsize18 c-333">{{ item.title }}</a>
                  </h3>
                  <section class="mt10 hLh20 of">
                    <span class="fr jgTag bg-green">
                        <i class="c-fff fsize12 f-fA">{{ item.categoryName}}</i>
                        |
                        <i class="c-fff fsize12 f-fA">{{ item.blogTags }}</i>
                      </span>
                    <span class="fl jgAttr c-ccc f-fA">
                        <i class="c-999 f-fA">{{ item.views }}</i>
                        |
                        <i class="c-999 f-fA">{{ item.gmtCreate }}</i>
                      </span>
                  </section>
                </div>
              </li>
            </ul>
            <div class="clear"/>
          </article>
        </div>
        <!-- 公共分页 开始 -->
        <div>
          <div class="paging">
            <!-- undisable这个class是否存在，取决于数据属性hasPrevious -->
            <a
              :class="{undisable: !data.hasPrevious}"
              href="#"
              title="首页"
              @click.prevent="gotoPage(1)">首页</a>
            <a
              :class="{undisable: !data.hasPrevious}"
              href="#"
              title="前一页"
              @click.prevent="gotoPage(data.current-1)">&lt;</a>
            <a
              v-for="page in data.pages"
              :key="page"
              :class="{current: data.current == page, undisable: data.current ==page}"
              :title="'第'+page+'页'"
              href="#"
              @click.prevent="gotoPage(page)">{{ page }}</a>
            <a
              :class="{undisable: !data.hasNext}"
              href="#"
              title="后一页"
              @click.prevent="gotoPage(data.current+1)">&gt;</a>
            <a
              :class="{undisable: !data.hasNext}"
              href="#"
              title="末页"
              @click.prevent="gotoPage(data.pages)">尾页</a>
            <div class="clear"/>
          </div>
        </div>
        <!-- 公共分页 结束 -->
      </section>
    </div>


</template>

<script>
  import categoryApi from '@/api/category'
    export default {
      data() {
        return {
          page: 1,
          data: {},
          categoryQuery: {
            category: ''
          },
          categoryList: [], // 一级分类列表

          oneIndex: -1,

        }
      },
      created() {
        this.init()
      },
      methods:{
        init(){
          categoryApi.getCategoryList().then(response =>{
            this.categoryList = response.data.data.categoryList
          })
        },
        // 分页切换的方法
        gotoPage(page) {
          categoryApi.getBlogByCategory(page, 8, this.categoryQuery).then(response => {
            this.data = response.data.data
          })
        },
        searchOne(category,index){
          this.oneIndex = index
          this.categoryQuery.category = category
          categoryApi.getBlogByCategory(1, 8, this.categoryQuery).then(response => {
            this.data = response.data.data
          })
        }

      }
    }

</script>

<style>
    .active {
      background: #bdbdbd;
    }
    .hide {
      display: none;
    }
    .show {
      display: block;
    }
  </style>

