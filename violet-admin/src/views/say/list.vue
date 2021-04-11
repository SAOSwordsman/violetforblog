<template>
  <div class="app-container">
    <div style="line-height: 40px; font-weight: 700; font-size: 22px; color: #a85a11; margin-left: 10px;"> 公告列表</div>
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="sayQuery.title" placeholder="公告标题"/>
      </el-form-item>

      <el-form-item label="日期">
        <el-date-picker
          v-model="sayQuery.gmtCreate"
          type="date"
          placeholder="选择要统计的日期"
          value-format="yyyy-MM-dd" />
      </el-form-item>

      <el-button type="primary" plain="true" icon="el-icon-search" @click="getListSay()">查询</el-button>
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

      <el-table-column label="公告标题" width="200">
        <template slot-scope="scope">
          <a href="">{{ scope.row.title }}</a>
        </template>
      </el-table-column>

      <el-table-column label="公告内容" style="height: 100%;">
        <template slot-scope="scope">
          <a href="">{{ scope.row.content }}</a>
        </template>
      </el-table-column>

      <el-table-column prop="gmtCreate" label="添加时间" width="200" />

      <el-table-column label="操作" align="center" style="height: 100%;">
        <template slot-scope="scope">
          <router-link :to="'/say/edit/'+scope.row.id">
            <el-button type="primary" plain="true" size="mini" icon="el-icon-edit">编辑标签信息</el-button>
          </router-link>
          <el-button type="danger" plain="true" size="mini" icon="el-icon-delete" @click="removeSayById(scope.row.id)">删除标签</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination :current-page="page" :page-size="limit" :total="total" style="padding: 30px 0; text-align: center;" layout="total, prev, pager, next, jumper" @current-change="getListSay" />
  </div>
</template>

<script>
import sayApi from '@/api/blog/say'

export default {
  data() {
    return {
      list: null,
      page: 1,
      limit: 5,
      total: 0,
      sayQuery: {}
    }
  },
  created() { // 页面渲染之前执行
    this.getListSay()
  },
  methods: {

    // 查询所有课程
    getListSay(page = 1) {
      this.page = page
      sayApi.getAllSayList(this.page, this.limit, this.sayQuery)
        .then(response => { // 请求成功
          // response接口返回的数据
          this.list = response.data.rows
          this.total = response.data.total
        })
    },
    // 清空搜索框
    resetData() {
      this.sayQuery = {}
      this.getListSay()
    },
    // 删除分类
    removeSayById(id) {
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        sayApi.removeSayId(id)
          .then(response => {
            // 提示
            this.$message({
              type: 'success',
              message: '删除课程成功! 😀'
            })
            this.getListSay()
          })
      })
    }
  }
}
</script>

<style scoped>

</style>
