<template>
  <div class="app-container">
    <div style="line-height: 40px; font-weight: 700; font-size: 22px; color: #a85a11; margin-left: 10px;"> 课程列表</div>
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="blogQuery.title" placeholder="博客标题"/>
      </el-form-item>

      <el-form-item>
        <el-select v-model="blogQuery.status" clearable placeholder="发布状态">
          <el-option value="Normal" label="已发布"/>
          <el-option value="Draft" label="未发布"/>
        </el-select>
      </el-form-item>

      <el-button type="primary" plain="true" icon="el-icon-search" @click="getListBlog()">查询</el-button>
      <el-button type="default" plain="true" @click="resetData()">清空</el-button>
    </el-form>

    <!-- 表格 -->
    <el-table
      :data="list"
      border
      fit
      highlight-current-row>

      <el-table-column
        label="序号"
        width="100"
        align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column label="博客标题">
        <template slot-scope="scope">
          <a :href="'http://localhost:3000/blog/'+scope.row.id">{{ scope.row.title }}</a>
        </template>
      </el-table-column>

      <el-table-column label="作者名字" width="100">
        <template slot-scope="scope">
          <a href="">{{ scope.row.authorName }}</a>
        </template>
      </el-table-column>

      <el-table-column label="分类" width="100" align="center">
        <template slot-scope="scope">
          <a href="">{{ scope.row.categoryName }}</a>
        </template>
      </el-table-column>

      <el-table-column label="标签" width="200" align="center">
        <template slot-scope="scope">
          <a href="">{{ scope.row.blogTags }}</a>
        </template>
      </el-table-column>

      <el-table-column prop="status" label="课程状态" width="130" align="center" >
        <template slot-scope="scope">
          <el-tag :type="scope.row.status === 'Draft' ? 'warning' : 'success'">{{ scope.row.status === 'Draft' ? '未发布' : '已发布' }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column label="浏览量" width="100" align="center">
        <template slot-scope="scope">
          <a href="">{{ scope.row.views }}</a>
        </template>
      </el-table-column>

      <el-table-column prop="gmtCreate" label="添加时间" width="170" />

      <el-table-column label="操作" align="center" style="height: 100%;">
        <template slot-scope="scope">
          <router-link :to="'/edit/'+scope.row.id">
            <el-button type="primary" plain="true" size="mini" icon="el-icon-edit">编辑博客信息</el-button>
          </router-link>
          <el-button type="danger" plain="true" size="mini" icon="el-icon-delete" @click="removeBlogById(scope.row.id)">删除博客</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination :current-page="page" :page-size="limit" :total="total" style="padding: 30px 0; text-align: center;" layout="total, prev, pager, next, jumper" @current-change="getListBlog" />
  </div>
</template>

<script>
  import cookie from "js-cookie";
  import blogApi from '@/api/blog'
    export default {
      data() {
        return {
          list: null,
          page: 1,
          limit: 5,
          total: 0,
          blogQuery: {},


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

        this.getUserInfo();
        this.getListBlog()

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
        // 查询所有课程
        getListBlog(page = 1) {
          this.page = page
          blogApi.getAuthorBlogList(this.loginInfo.id,this.page, this.limit, this.blogQuery)
            .then(response => { // 请求成功
              // response接口返回的数据
              this.list = response.data.data.rows
              this.total = response.data.data.total
            })
        },
        // 清空搜索框
        resetData() {
          this.blogQuery = {}
          this.getListBlog()
        },
        removeBlogById(blogId) {
          this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            blogApi.deleteBlog(blogId)
              .then(response => {
                // 提示
                this.$message({
                  type: 'success',
                  message: '删除课程成功! 😀'
                })
                this.getListBlog()
              })
          })
        }
      }
    }
</script>

<style scoped>

</style>
