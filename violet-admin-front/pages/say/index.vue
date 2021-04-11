<template>
  <div class="container">
    <br><br>
    <div class="block">
      <el-timeline v-for="say in list" :key="say.id">
        <el-timeline-item :timestamp="say.gmtCreate" placement="top">
          <a :href="'/say/'+say.id">
          <el-card>
            <h1>{{ say.title }}</h1>
            <p>{{ say.gmtCreate }}</p>
          </el-card>
          </a>
        </el-timeline-item>
      </el-timeline>
    </div>
  </div>
</template>

<script>
  import sayApi from '@/api/say'
    export default {
        data(){
          return{
            list: {}
          }
        },
        created() {
          this.getSay()
        },
        methods:{
          getSay(){
            sayApi.getSayList().then(response =>{
              this.list = response.data.data.list
            })
          }
        }
    }
</script>

<style scoped>

</style>
