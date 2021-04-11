<template>

  <div class="container">
    <article class="comm-course-list">
      <ul id="bna" class="of">
        <li v-for="friend in list" :key="friend.id">
          <a class="btn btn1" :href="friend.url">{{ friend.blogger }}</a>
        </li>
      </ul>
    </article>

    <!-- 分页 -->
    <el-pagination :current-page="page" :page-size="limit" :total="total" style="padding: 30px 0; text-align: center;" layout="total, prev, pager, next, jumper" @current-change="getListFriend" />
  </div>


</template>

<script>
  import friendApi from '@/api/friend'
    export default {
      data() {
        return {
          list: {},
          page: 1,
          limit: 8,
          total: 0,

        }
      },
      created() {
        this.getListFriend()
      },
      methods:{
        getListFriend(page = 1){
          this.page = page
          friendApi.getAllFriendList(this.page, this.limit)
            .then(response => { // 请求成功
              // response接口返回的数据
              this.list = response.data.data.rows
              this.total = response.data.data.total
            })
        },

      }
    }
</script>

<style scoped>
  body{
    margin: 0;
    padding: 0;
    background: #f1f1f1;
  }

  .container{
    /*position: absolute;*/
    left: 50%;
    top: 50%;
    /*transform: translate(-50%,-50%);*/
    text-align: center;
  }

  .btn{
    /*float: inherit;*/
    font-size: 30px;
    /* 边框 */
    border: none;
    /* 外边距 */
    margin: 20px;
    /* 内边距 */
    padding: 24px;
    width: 220px;
    /* 字体 */
    /* font-family: "montserrat",sans-serif; */
    /* 定义字体只能大写 */
    text-transform: uppercase;
    /* 定义圆角 */
    border-radius: 6px;
    /* 鼠标覆盖按钮时候的样子 */
    cursor: pointer;
    /* 定义背景图片的大小，此处为100图标就无法变化 */
    background-size: 200%;
    /* 时间过度 */
    transition: 0.4s;
  }

  .btn1{
    /* 指定颜色变化的方向，起始颜色，中间颜色，终止颜色  */
    background-image: linear-gradient(to left,#FFC312,#EE5A24,#FFC312);

  }
  .btn2{
    background-image: linear-gradient(to left,#C4E538,#009432,#C4E538);

  }
  .btn3{
    background-image: linear-gradient(to left,#12CBC4,#0652DD,#12CBC4);

  }
  .btn4{
    background-image: linear-gradient(to left,#FDA7DF,#9980FA,#FDA7DF);

  }

  .btn:hover{
    background-position: right;
  }

</style>
