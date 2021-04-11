<template>
  <div class="app-container">
    <div style="line-height: 40px; font-weight: 700; font-size: 22px; color: #a85a11; margin-left: 10px;"> 评论列表</div>
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="commentQuery.blogTitle" placeholder="博客标题"/>
      </el-form-item>

      <el-form-item>
        <el-input v-model="commentQuery.userName" placeholder="评论作者"/>
      </el-form-item>

      <el-button type="primary" plain="true" icon="el-icon-search" @click="getListComment()">查询</el-button>
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
          <a href="">{{ scope.row.blogTitle }}</a>
        </template>
      </el-table-column>

      <el-table-column label="评论作者" width="100">
        <template slot-scope="scope">
          <a href="">{{ scope.row.userName }}</a>
        </template>
      </el-table-column>

      <el-table-column label="评论内容" style="height: 100%;">
        <template slot-scope="scope">
          <a href="">{{ scope.row.content }}</a>
        </template>
      </el-table-column>

      <el-table-column prop="gmtCreate" label="添加时间" width="170" />

      <el-table-column label="操作" align="center" style="height: 100%;">
        <template slot-scope="scope">
          <el-button type="danger" plain="true" size="mini" icon="el-icon-delete" @click="removeCommentById(scope.row.id)">删除博客</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination :current-page="page" :page-size="limit" :total="total" style="padding: 30px 0; text-align: center;" layout="total, prev, pager, next, jumper" @current-change="getListComment" />
  </div>
</template>

<script>
import commentApi from '@/api/blog/comment'

export default {
  data() {
    return {
      list: null,
      page: 1,
      limit: 5,
      total: 0,
      commentQuery: {}
    }
  },
  created() { // 页面渲染之前执行
    this.getListComment()
  },
  methods: {

    // 查询所有课程
    getListComment(page = 1) {
      this.page = page
      commentApi.getAllCommentList(this.page, this.limit, this.commentQuery)
        .then(response => { // 请求成功
          // response接口返回的数据
          this.list = response.data.rows
          this.total = response.data.total
        })
    },
    // 清空搜索框
    resetData() {
      this.commentQuery = {}
      this.getListComment()
    },
    removeCommentById(id) {
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        commentApi.deleteComment(id)
          .then(response => {
            // 提示
            this.$message({
              type: 'success',
              message: '删除课程成功! 😀'
            })
            this.getListComment()
          })
      })
    }
  }
}
</script>

<style scoped>

</style>
