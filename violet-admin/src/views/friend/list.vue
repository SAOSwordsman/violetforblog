<template>
  <div class="app-container">
    <div style="line-height: 40px; font-weight: 700; font-size: 22px; color: #a85a11; margin-left: 10px;"> 友链列表</div>
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="friendQuery.blogger" placeholder="博客作者"/>
      </el-form-item>

      <el-button type="primary" plain="true" icon="el-icon-search" @click="getListFriend()">查询</el-button>
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

      <el-table-column label="博客作者" width="200">
        <template slot-scope="scope">
          <a href="">{{ scope.row.blogger }}</a>
        </template>
      </el-table-column>

      <el-table-column label="博客地址" width="200">
        <template slot-scope="scope">
          <a :href="scope.row.url">{{ scope.row.url }}</a>
        </template>
      </el-table-column>

      <el-table-column prop="gmtCreate" label="添加时间" width="200" />

      <el-table-column label="操作" align="center" style="height: 100%;">
        <template slot-scope="scope">
          <router-link :to="'/friend/edit/'+scope.row.id">
            <el-button type="primary" plain="true" size="mini" icon="el-icon-edit">编辑友链信息</el-button>
          </router-link>
          <el-button type="danger" plain="true" size="mini" icon="el-icon-delete" @click="removeFriendById(scope.row.id)">删除分类</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination :current-page="page" :page-size="limit" :total="total" style="padding: 30px 0; text-align: center;" layout="total, prev, pager, next, jumper" @current-change="getListFriend" />
  </div>
</template>

<script>
import friendApi from '@/api/blog/friend'

export default {
  data() {
    return {
      list: null,
      page: 1,
      limit: 5,
      total: 0,
      friendQuery: {}
    }
  },
  created() { // 页面渲染之前执行
    this.getListFriend()
  },
  methods: {

    // 查询所有友链
    getListFriend(page = 1) {
      this.page = page
      friendApi.getAllFriendList(this.page, this.limit, this.friendQuery)
        .then(response => { // 请求成功
          // response接口返回的数据
          this.list = response.data.rows
          this.total = response.data.total
        })
    },
    // 清空搜索框
    resetData() {
      this.friendQuery = {}
      this.getListFriend()
    },
    // 删除友链
    removeFriendById(friendId) {
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        friendApi.removeFriendId(friendId)
          .then(response => {
            // 提示
            this.$message({
              type: 'success',
              message: '删除课程成功! 😀'
            })
            this.getListFriend()
          })
      })
    }
  }
}
</script>

<style scoped>

</style>
