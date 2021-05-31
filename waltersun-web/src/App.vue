<template>
  <div id="app">
    <el-container>
      <el-header>
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect"
                 background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
          <el-menu-item index="1">Downloads 下载</el-menu-item>
        </el-menu>
      </el-header>
      <el-main>
        <el-carousel indicator-position="outside" type="card">
          <el-carousel-item v-for="item in arr" :key="item">
            <h3>{{ item }}</h3>
          </el-carousel-item>
        </el-carousel>
        <FileOperation></FileOperation>
      </el-main>
      <el-footer>Copyright © 2020-2021</el-footer>
    </el-container>
  </div>
</template>

<script>
import FileOperation from './components/FileOperation.vue'
import 'element-ui/lib/theme-chalk/index.css'

export default {
  name: 'App',
  components: {
    FileOperation
  },
  data() {
    return {
      activeIndex: '1',
      arr:['Welcome','to','My','New','world']
    }
  },
  methods: {
    handleSelect() {

    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.el-carousel__item h3 {
  color: #C0C4CC;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}
</style>
