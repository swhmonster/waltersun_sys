<template>
  <div id="app">
    <el-container>
      <el-header>
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect"
                 background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
          <el-menu-item index="1">Downloads 下载</el-menu-item>
          <el-menu-item index="2">item</el-menu-item>
        </el-menu>
      </el-header>
      <el-main>
        <el-carousel indicator-position="outside" :interval="5000">
          <el-carousel-item v-for="item in arr" :key="item">
            <h3>{{ item }}</h3>
          </el-carousel-item>
        </el-carousel>
        <FileOperation v-show="activeIndex === '1'"></FileOperation>
      </el-main>
      <el-footer>Copyright © 2020-2021
        <a target="_blank" href="http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=F2RgfyAuIyZXZmY5dHh6"
           style="text-decoration:none;">
          <img src="http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_02.png"/>
        </a>
      </el-footer>
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
      arr: [
        '善良的人都晚熟，并且是被劣人催熟的，后来虽然开窍了，但他仍然善良与赤诚，不断寻找同类，最后变成最孤独的一个人。 ——莫言',
        '那时我们有梦，关于文学，关于爱情，关于穿越世界的旅行。如今我们深夜饮酒，杯子碰到一起，都是梦破碎的声音。—— 北岛',
        '我觉得人的脆弱和坚强都超乎自己的想象。有时，我可能脆弱得一句话就泪流满面；有时，也发现自己咬着牙走了很长的路。——莫泊桑'
      ]
    }
  },
  mounted() {
    // 百度统计集成开始
    const _hmt = _hmt || [];
    (function () {
      var hm = document.createElement("script");
      hm.src = "https://hm.baidu.com/hm.js?565a7b05ee2b4d75932f0afb2c903ed8";
      var s = document.getElementsByTagName("script")[0];
      s.parentNode.insertBefore(hm, s);
    })();
    // 百度统计集成结束
  },
  methods: {
    handleSelect(key) {
      this.activeIndex = key;
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
  color: #eaebee;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #606266;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #606266;
}
</style>
