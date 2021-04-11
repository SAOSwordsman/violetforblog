<template>
  <div class="container">
    <div class="hhh">发布博客</div>
    <br>
    <el-form label-width="120px">
      <el-form-item label="课程标题">
        <el-input v-model="blogInfo.title" placeholder="示例：机器学习项目课：从基础到搭建项目"/>
      </el-form-item>
      <!-- 所属分类 TODO -->
      <el-form-item label="博客分类">
        <el-select v-model="blogInfo.categoryName" placeholder="请选择">
          <el-option
            v-for="categorys in categoryList"
            :key="categorys.id"
            :label="categorys.category"
            :value="categorys.category">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="博客标签">
        <el-select v-model="blogInfo.blogTags" placeholder="请选择">
          <el-option
            v-for="tags in tagList"
            :key="tags.id"
            :label="tags.tag"
            :value="tags.tag">
          </el-option>
        </el-select>
      </el-form-item>



      <quill-editor
        ref="editor"
        v-model="blogInfo.content"
        @change="onEditorChange($event)"
        @blur="onEditorBlur($event)"
        @focus="onEditorFocus($event)"
        @ready="onEditorReady($event)"
        v-quill:myQuillEditor="editorOption"
      />



<!--      <div class="quill-editor"-->
<!--           :content="blogInfo.content"-->
<!--           @change="onEditorChange($event)"-->
<!--           @blur="onEditorBlur($event)"-->
<!--           @focus="onEditorFocus($event)"-->
<!--           @ready="onEditorReady($event)"-->
<!--           v-quill:myQuillEditor="editorOption">-->
<!--      </div>-->

      <br>
      <br><br>
      <br><br>
      <br><br>
      <br>

      <el-form-item label="博客封面">
        <el-upload
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          :action="BASE_API+'/violetoss/fileoss/upload'"
          class="avatar-uploader">
          <img class="picture" :src="blogInfo.authorAvatar">
        </el-upload>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="save">发布博客</el-button>
      </el-form-item>

    </el-form>
  </div>
</template>

<script>
  import writeblogApi from '@/api/writeblog'
  import cookie from "js-cookie";
  export default {
    data () {
      return {
        //用户信息
        loginInfo: {
          id: "",
          age: "",
          avatar: "",
          mobile: "",
          nickname: "",
          sex: ""
        },

        blogInfo: {
          title: '',
          content: '请把内容写在这里',
          authorId: '',
          authorName: '',
          authorAvatar: 'https://violet-imgs.oss-cn-beijing.aliyuncs.com/0.jpg',
          categoryName: '',
          blogTags: '',
          status: 'Normal'
        },
        categoryList: [],
        tagList: [],

        BASE_API: 'http://localhost:8222',

        editorOption: {
          // some quill options
          modules: {
            toolbar: [
              ['bold', 'italic', 'underline', 'strike'],
              [{ 'header': 1},{'header': 2}], // 标题
              [{ 'list': 'ordered'}, {'list': 'bullet'}], // 列表
              [{ script: "sub" }, { script: "super" }], // 上标/下标
              [{ indent: "-1" }, { indent: "+1" }], // 缩进
              [{ size: ["small", false, "large", "huge"] }], // 字体大小
              [{ color: [] }, { background: [] }], // 字体颜色、字体背景颜色
              [{ font: [] }], // 字体种类
              [{ align: [] }], // 对齐方式
              ['blockquote', 'code-block'],
              ['image','video']
            ]
          }
        }
      }
    },
    created() {
      this.getAllCategory()
      this.getAllTag()
      this.getUserInfo();
    },
    methods: {
      onEditorBlur(editor) {
        console.log('editor blur!', editor)
      },
      onEditorFocus(editor) {
        console.log('editor focus!', editor)
      },
      onEditorReady(editor) {
        console.log('editor ready!', editor)
      },
      onEditorChange({ editor, html, text }) {
        console.log('editor change!', editor, html, text)
        this.content = html
      },
      //上传封面成功调用的方法
      handleAvatarSuccess(res,file){
        this.blogInfo.authorAvatar = res.data.url
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
      getAllCategory(){
        writeblogApi.getCategorys().then(response =>{
          this.categoryList = response.data.data.categorys
        })
      },
      getAllTag(){
        writeblogApi.getTags().then(response =>{
          this.tagList = response.data.data.tags
        })
      },
      //获取用户信息
      getUserInfo() {
        //获取的是Json字符串，需要转成Json对象，从后端获取的Json都是字符串形式的
        var userStr = cookie.get("violet_ucenter");
        //若有用户信息，则取出用户信息
        if (userStr) {
          //获取用户信息
          this.loginInfo = JSON.parse(userStr);
          this.blogInfo.authorId = this.loginInfo.id;
          this.blogInfo.authorName = this.loginInfo.nickname;
        }
      },
      save(){
        this.$confirm('此操作将发布博客, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          writeblogApi.addBlog(this.blogInfo)
            .then(response => {
              // 提示
              this.$message({
                type: 'success',
                message: '添加博客成功! 😀'
              })
              window.location.href = '/myBlog'
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
