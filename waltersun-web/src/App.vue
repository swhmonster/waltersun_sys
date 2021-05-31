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
        <FileOperation></FileOperation>
      </el-main>
      <el-footer>Copyright © 2020-2021</el-footer>
    </el-container>
  </div>
</template>

<script>
import FileOperation from './components/FileOperation.vue'

export default {
  name: 'App',
  components: {
    FileOperation
  },
  data() {
    return {
      activeIndex: '1'
    }
  },
  methods: {
    handleSelect()
    {

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
</style>
