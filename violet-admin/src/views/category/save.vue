<template>
  <div class="app-container">
    <el-form label-width="120px">
      <br>
      <br>
      <el-form-item label="分类名称">
        <el-input v-model="blogCategory.category"/>
      </el-form-item>

      <el-form-item label="分类权限">
        <el-select v-model="blogCategory.isDelete" clearable placeholder="请选择">
          <el-option :value="0" label="启用"/>
          <el-option :value="1" label="不启用"/>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存</el-button>
      </el-form-item>
      <br>
      <br>
      <img src="http://img.violet-nian.top/imgs/2020/07/060b0a40dd345bdb.png" height="600px" width="900px">

    </el-form>
  </div>
</template>

<script>
import categoryApi from '@/api/blog/category'

export default {

  data() {
    return {
      blogCategory: {
        id: '',
        category: '',
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
        this.blogCategory.id = id
        this.getInfo(id)
      } else { // 路径里面没有id值
        // 清空表单
        this.blogCategory = {}
      }
    },
    // 通过id获取讲师
    getInfo(id) {
      categoryApi.getCategoryById(id)
        .then(response => {
          this.blogCategory = response.data.blogCategory
        })
    },
    saveOrUpdate() {
      this.saveBtnDisabled = true
      // 判断是修改还是添加
      // 根据blogCategory中是否有id
      if (!this.blogCategory.id) {
        this.saveCategory()
      } else {
        this.updateCategoryInfo()
      }
    },
    updateCategoryInfo() {
      categoryApi.updateCategory(this.blogCategory)
        .then(response => {
          this.$message({
            type: 'success',
            message: '修改成功!'
          })
          // 回到列表页面 路由跳转
          this.$router.push({ path: '/category/table' })
        })
    },
    // 保存
    saveCategory() {
      categoryApi.addCategory(this.blogCategory)
        .then(response => { // 添加成功
          // 提示信息
          this.$message({
            type: 'success',
            message: '添加成功!'
          })
          // 回到列表页面 路由跳转
          this.$router.push({ path: '/category/table' })
        })
    }
  }
}
</script>

<style scoped>

</style>
