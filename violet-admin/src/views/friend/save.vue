<template>
  <div class="app-container">
    <el-form label-width="120px">
      <br>
      <br>
      <el-form-item label="博客作者">
        <el-input v-model="friendQuery.blogger"/>
      </el-form-item>

      <el-form-item label="博客地址">
        <el-input v-model="friendQuery.url"/>
      </el-form-item>

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存</el-button>
      </el-form-item>
      <br>
      <br>
      <img src="http://img.violet-nian.top/imgs/2020/07/aef87ec4511ff252.jpg" height="600px" width="900px">

    </el-form>
  </div>
</template>

<script>
import friendApi from '@/api/blog/friend'

export default {

  data() {
    return {
      friendQuery: {
        id: '',
        blogger: '',
        url: ''
      },

      saveBtnDisabled: false
    }
  },

  // 监听  路由变化方式
  watch: {
    $route(to, from) {
      console.log('watch $route')
      this.init()
    }
  },
  created() {
    this.init()
  },

  methods: {
    init() {
      if (this.$route.params && this.$route.params.id) { // 路径里面有id值
        const id = this.$route.params.id
        this.friendQuery.id = id
        this.getInfo(id)
      } else { // 路径里面没有id值
        // 清空表单
        this.friendQuery = {}
      }
    },
    // 通过id获取讲师
    getInfo(id) {
      friendApi.getFriendById(id)
        .then(response => {
          this.friendQuery = response.data.friendlink
        })
    },
    saveOrUpdate() {
      this.saveBtnDisabled = true
      // 判断是修改还是添加
      // 根据blogCategory中是否有id
      if (!this.friendQuery.id) {
        this.saveFriend()
      } else {
        this.updateFriendInfo()
      }
    },
    updateFriendInfo() {
      friendApi.updateFriend(this.friendQuery)
        .then(response => {
          this.$message({
            type: 'success',
            message: '修改成功!'
          })
          // 回到列表页面 路由跳转
          this.$router.push({ path: '/friend/table' })
        })
    },
    // 保存
    saveFriend() {
      friendApi.addFriend(this.friendQuery)
        .then(response => { // 添加成功
          // 提示信息
          this.$message({
            type: 'success',
            message: '添加成功!'
          })
          // 回到列表页面 路由跳转
          this.$router.push({ path: '/friend/table' })
        })
    }
  }
}
</script>

<style scoped>

</style>
