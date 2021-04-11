<template>
  <div class="app-container">
    <div style="line-height: 40px; font-weight: 700; font-size: 22px; color: #a85a11; margin-left: 10px;"> 分类列表</div>
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="categoryQuery.category" placeholder="分类名称"/>
      </el-form-item>

      <el-button type="primary" plain="true" icon="el-icon-search" @click="getListCategory()">查询</el-button>
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

      <el-table-column label="分类名称" width="200">
        <template slot-scope="scope">
          <a href="">{{ scope.row.category }}</a>
        </template>
      </el-table-column>

      <el-table-column label="是否启用" width="100">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.isDelete" :active-value="0" :inactive-value="1" @change="deleteCategoryById(scope.row.id)"/>
        </template>
      </el-table-column>

      <el-table-column prop="gmtCreate" label="添加时间" width="200" />

      <el-table-column label="操作" align="center" style="height: 100%;">
        <template slot-scope="scope">
          <router-link :to="'/category/edit/'+scope.row.id">
            <el-button type="primary" plain="true" size="mini" icon="el-icon-edit">编辑分类信息</el-button>
          </router-link>
          <el-button type="danger" plain="true" size="mini" icon="el-icon-delete" @click="removeCategoryById(scope.row.id)">删除分类</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination :current-page="page" :page-size="limit" :total="total" style="padding: 30px 0; text-align: center;" layout="total, prev, pager, next, jumper" @current-change="getListCategory" />
  </div>
</template>

<script>
import categoryApi from '@/api/blog/category'

export default {
  data() {
    return {
      list: null,
      page: 1,
      limit: 5,
      total: 0,
      categoryQuery: {}
    }
  },
  created() { // 页面渲染之前执行
    this.getListCategory()
  },
  methods: {

    // 查询所有课程
    getListCategory(page = 1) {
      this.page = page
      categoryApi.getAllCategoryList(this.page, this.limit, this.categoryQuery)
        .then(response => { // 请求成功
          // response接口返回的数据
          this.list = response.data.rows
          this.total = response.data.total
        })
    },
    // 清空搜索框
    resetData() {
      this.categoryQuery = {}
      this.getListCategory()
    },
    deleteCategoryById(categoryId) {
      categoryApi.deleteCategoryId(categoryId)
    },
    // 删除分类
    removeCategoryById(categoryId) {
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        categoryApi.removeCategoryId(categoryId)
          .then(response => {
            // 提示
            this.$message({
              type: 'success',
              message: '删除课程成功! 😀'
            })
            this.getListCategory()
          })
      })
    }
  }
}
</script>

<style scoped>

</style>
