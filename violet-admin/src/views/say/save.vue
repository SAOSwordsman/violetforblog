<template>
  <div class="app-container">
    <el-form label-width="120px">
      <br>
      <br>
      <el-form-item label="公告标题">
        <el-input v-model="sayQuery.title"/>
      </el-form-item>

      <el-form-item label="公告内容">
        <tinymce :height="300" v-model="sayQuery.content"/>
      </el-form-item>

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存</el-button>
      </el-form-item>
      <br>
      <br>
      <img src="http://img.violet-nian.top/imgs/2020/07/be537f42d81d4320.png" height="600px" width="900px">

    </el-form>
  </div>
</template>
<script>
import sayApi from '@/api/blog/say'
// 引入Tinymce富文本编辑器
import Tinymce from '@/components/Tinymce'

export default {
  // 声名组件
  components: { Tinymce },
  data() {
    return {
      saveBtnDisabled: false, // 保存按钮是否禁用

      sayQuery: {
        id: '',
        title: '',
        content: ''
      }
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
        this.sayQuery.id = id
        this.getInfo(id)
      } else { // 路径里面没有id值
        // 清空表单
        this.sayQuery = {}
      }
    },
    // 通过id获取讲师
    getInfo(id) {
      sayApi.getSayById(id)
        .then(response => {
          this.sayQuery = response.data.lvSay
        })
    },
    saveOrUpdate() {
      this.saveBtnDisabled = true
      // 判断是修改还是添加
      // 根据blogCategory中是否有id
      if (!this.sayQuery.id) {
        this.saveSay()
      } else {
        this.updateSayInfo()
      }
    },
    updateSayInfo() {
      sayApi.updateSay(this.sayQuery)
        .then(response => {
          this.$message({
            type: 'success',
            message: '修改成功!'
          })
          // 回到列表页面 路由跳转
          this.$router.push({ path: '/say/table' })
        })
    },
    // 保存
    saveSay() {
      sayApi.addSay(this.sayQuery)
        .then(response => { // 添加成功
          // 提示信息
          this.$message({
            type: 'success',
            message: '添加成功!'
          })
          // 回到列表页面 路由跳转
          this.$router.push({ path: '/say/table' })
        })
    }
  }
}
</script>
<style scoped>
  .tinymce-container {
    line-height: 29px;
  }
</style>
