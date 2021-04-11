<template>
  <div class="app-container">
    <div style="line-height: 40px; font-weight: 700; font-size: 22px; color: #a85a11; margin-left: 10px;"> 课程列表</div>
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="memberQuery.mobile" placeholder="手机号"/>
      </el-form-item>
      <el-form-item>
        <el-input v-model="memberQuery.nickname" placeholder="昵称"/>
      </el-form-item>

      <el-button type="primary" plain="true" icon="el-icon-search" @click="getListUser()">查询</el-button>
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

      <el-table-column label="手机号" width="200">
        <template slot-scope="scope">
          <a href="">{{ scope.row.mobile }}</a>
        </template>
      </el-table-column>
      <el-table-column label="昵称" width="100">
        <template slot-scope="scope">
          <a href="">{{ scope.row.nickname }}</a>
        </template>
      </el-table-column>
      <el-table-column label="QQ" width="100">
        <template slot-scope="scope">
          <a href="">{{ scope.row.qq }}</a>
        </template>
      </el-table-column>
      <el-table-column label="性别" width="50">
        <template slot-scope="scope">
          <a href="">{{ scope.row.sex }}</a>
        </template>
      </el-table-column>
      <el-table-column label="年龄" width="50">
        <template slot-scope="scope">
          <a href="">{{ scope.row.age }}</a>
        </template>
      </el-table-column>

      <el-table-column label="是否启用" width="100">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.isDeleted" :active-value="0" :inactive-value="1" @change="deleteUserById(scope.row.id)"/>
        </template>
      </el-table-column>

      <el-table-column prop="gmtCreate" label="添加时间" width="200" />

      <el-table-column label="操作" align="center" style="height: 100%;">
        <template slot-scope="scope">
          <el-button type="danger" plain="true" size="mini" icon="el-icon-delete" @click="removeUserById(scope.row.id)">删除用户</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination :current-page="page" :page-size="limit" :total="total" style="padding: 30px 0; text-align: center;" layout="total, prev, pager, next, jumper" @current-change="getListUser" />
  </div>
</template>

<script>
import userApi from '@/api/user'

export default {
  data() {
    return {
      list: null,
      page: 1,
      limit: 5,
      total: 0,
      memberQuery: {}
    }
  },
  created() { // 页面渲染之前执行
    this.getListUser()
  },
  methods: {

    // 查询所有课程
    getListUser(page = 1) {
      this.page = page
      userApi.getAllUserList(this.page, this.limit, this.memberQuery)
        .then(response => { // 请求成功
          // response接口返回的数据
          this.list = response.data.rows
          this.total = response.data.total
        })
    },
    // 清空搜索框
    resetData() {
      this.memberQuery = {}
      this.getListUser()
    },
    deleteUserById(id) {
      userApi.deleteUserId(id)
    },
    // 删除分类
    removeUserById(id) {
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        userApi.removeUserId(id)
          .then(response => {
            // 提示
            this.$message({
              type: 'success',
              message: '删除课程成功! 😀'
            })
            this.getListUser()
          })
      })
    }
  }
}
</script>

<style scoped>

</style>
