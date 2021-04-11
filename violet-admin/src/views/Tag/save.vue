<template>
  <div class="app-container">
    <el-form label-width="120px">
      <br>
      <br>
      <el-form-item label="标签名称">
        <el-input v-model="blogTag.tag"/>
      </el-form-item>

      <el-form-item label="标签权限">
        <el-select v-model="blogTag.isDelete" clearable placeholder="请选择">
          <el-option :value="0" label="启用"/>
          <el-option :value="1" label="不启用"/>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存</el-button>
      </el-form-item>
      <br>
      <br>
      <img src="http://img.violet-nian.top/imgs/2020/07/d37295ebc6297474.jpg" height="600px" width="900px">

    </el-form>
  </div>
</template>

<script>
import tagApi from '@/api/blog/tag'

export default {

  data() {
    return {
      blogTag: {
        id: '',
        tag: '',
        isDelete: 0
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
        this.blogTag.id = id
        this.getInfo(id)
      } else { // 路径里面没有id值
        // 清空表单
        this.blogTag = {}
      }
    },
    // 通过id获取讲师
    getInfo(id) {
      tagApi.getTagById(id)
        .then(response => {
          this.blogTag = response.data.blogTag
        })
    },
    saveOrUpdate() {
      this.saveBtnDisabled = true
      // 判断是修改还是添加
      // 根据blogCategory中是否有id
      if (!this.blogTag.id) {
        this.saveTag()
      } else {
        this.updateTagInfo()
      }
    },
    updateTagInfo() {
      tagApi.updateTag(this.blogTag)
        .then(response => {
          this.$message({
            type: 'success',
            message: '修改成功!'
          })
          // 回到列表页面 路由跳转
          this.$router.push({ path: '/tag/table' })
        })
    },
    // 保存
    saveTag() {
      tagApi.addTag(this.blogTag)
        .then(response => { // 添加成功
          // 提示信息
          this.$message({
            type: 'success',
            message: '添加成功!'
          })
          // 回到列表页面 路由跳转
          this.$router.push({ path: '/tag/table' })
        })
    }
  }
}
</script>

<style scoped>

</style>
