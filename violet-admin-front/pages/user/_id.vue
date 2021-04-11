<template>
  <div class="container">
    <div class="hhh">修改个人信息</div>
    <br>
    <el-form label-width="100px">
      <el-form-item label="用户昵称">
        <el-input v-model="userInfo.nickname"/>
      </el-form-item>

      <el-form-item label="QQ号">
        <el-input v-model="userInfo.qq"/>
      </el-form-item>

      <el-form-item label="性别">
        <el-radio v-model="userInfo.sex" label="0">男</el-radio>
        <el-radio v-model="userInfo.sex" label="1">女</el-radio>
      </el-form-item>

      <el-form-item label="年龄">
        <el-input v-model="userInfo.age"/>
      </el-form-item>

      <el-form-item label="博客封面">
        <el-upload
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          :action="BASE_API+'/violetoss/fileoss/upload'"
          class="avatar-uploader">
          <img class="picture" :src="userInfo.avatar">
        </el-upload>
      </el-form-item>

      <el-form-item label="个人介绍">
        <el-input v-model="userInfo.sign"/>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="update">修改信息</el-button>
      </el-form-item>
    </el-form>
  </div>

</template>

<script>
  import userApi from '@/api/user'
    export default {
      data() {
        return{
          userInfo: {
            id: '',
            nickname: '',
            qq: '',
            sex: '',
            age: '',
            avatar: '',
            sign: ''
          },
          BASE_API: 'http://localhost:8222',
        }
    },

    created() {
      if(this.$route.params&&this.$route.params.id){
        this.userInfo.id = this.$route.params.id
      }
      this.getUser()
    },
    methods: {
      //上传封面成功调用的方法
      handleAvatarSuccess(res,file){
        this.userInfo.avatar = res.data.url
      },
      //上传之前调用的方法
      beforeAvatarUpload(file){
        const isJPG = file.type === 'image/png'
        const isLt8M = file.size / 1024 / 1024 < 2
        if (!isJPG) {
          this.$message.error('上传头像图片只能是 PNG 格式!')
        }
        if (!isLt8M) {
          this.$message.error('上传头像图片大小不能超过 2MB!')
        }
        return isJPG && isLt8M
      },
      getUser(){
        userApi.getUserById(this.userInfo.id).then(response =>{
          this.userInfo = response.data.data.user
        })
      },
      update(){
        this.$confirm('此操作将修改个人信息, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          userApi.updateUser(this.userInfo)
            .then(response => {
              // 提示
              this.$message({
                type: 'success',
                message: '修改信息成功! 😀'
              })
              window.location.href = '/'
            })
        })
      }

    }
  }
</script>

<style lang="less" scoped>
  .container {
    width: 60%;
    margin: 0 auto;
    padding: 50px 0;
    .quill-editor {
      min-height: 200px;
      max-height: 400px;
      overflow-y: auto;
    }
  }
  .hhh{
    text-align: center;
    font-size: 30px;
  }
  .picture{
    width: 300px;
    height: 150px;
  }
  .label{
    font-size: 30px;
  }
</style>
