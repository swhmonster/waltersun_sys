<template>
    <div id="app">
        <el-container>
            <el-header>
                <el-container>
                    <el-main style="padding-top: 0;width: 93%">
                        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal"
                                 @select="handleSelect">
                            <el-menu-item index="1">JSON格式化</el-menu-item>
                            <el-menu-item index="2">时间戳工具</el-menu-item>
                            <el-menu-item index="3">加解密工具</el-menu-item>
                            <el-menu-item index="4">CDN工具</el-menu-item>
                            <el-menu-item index="5">UUID工具</el-menu-item>
                            <el-menu-item index="6">URL工具</el-menu-item>
                            <el-menu-item index="7">其他工具下载</el-menu-item>
                        </el-menu>
                    </el-main>
                    <el-main style="width:20px" v-if="false">
                        <el-avatar size="medium" :src="circleUrl"  style="float:right" @click.native="login"></el-avatar>
                    </el-main>
                </el-container>
            </el-header>
            <el-main style="">
                <el-carousel indicator-position="outside" :interval="8000" height="90px" style="color: #2c3e50">
                    <el-carousel-item v-for="item in arr" :key="item"
                                      style="color: #2c3e50; font-size: 18px; font-family: 'Helvetica Neue',cursive;text-align: center;background-color: azure;box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)">
                        <h3>{{ item }}</h3>
                    </el-carousel-item>
                </el-carousel>
                <JsonFormat v-show="activeIndex === '1'"></JsonFormat>
                <DateTimeOperation v-show="activeIndex === '2'"></DateTimeOperation>
                <CipherOperation v-show="activeIndex === '3'"></CipherOperation>
                <CDNdetect v-show="activeIndex === '4'"></CDNdetect>
                <UuidHelper v-show="activeIndex === '5'"></UuidHelper>
                <URLUtils v-show="activeIndex === '6'"></URLUtils>
                <FileOperation v-show="activeIndex === '7'"></FileOperation>
            </el-main>
            <el-footer style="text-align: center;">
                <el-link href="https://beian.miit.gov.cn/" target="_blank">苏ICP备2023008337号-1</el-link>
                <img src="./assets/beian.png" alt="beian icon"/>
                <el-link href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=32010602010775"
                         target="_blank">苏公网安备32010602010775号
                </el-link>
                ©2020-present, waltersun.cn
                <!--去除给我写信-->
                <!--<a target="_blank" href="http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=F2RgfyAuIyZXZmY5dHh6"
                       style="text-decoration:none;">
                        <img src="http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_02.png" alt="email me"/>
                    </a>-->
            </el-footer>
        </el-container>
    </div>
</template>

<script>
import 'element-ui/lib/theme-chalk/index.css'
import FileOperation from './components/FileOperation.vue'
import CipherOperation from "@/components/CipherOperation.vue";
import DateTimeOperation from "@/components/DateTimeOperation.vue";
import JsonFormat from "@/components/JsonFormat.vue";
import CDNdetect from "@/components/CDNdetect.vue";
import UuidHelper from "@/components/UuidHelper.vue";
import URLUtils from "@/components/URLUtils.vue";

export default {
    name: 'App',
    components: {
        URLUtils,
        UuidHelper,
        CDNdetect,
        JsonFormat,
        DateTimeOperation,
        CipherOperation,
        FileOperation
    },
    data() {
        return {
            activeIndex: '1',
            arr: [
                '善良的人都晚熟，并且是被劣人催熟的，后来虽然开窍了，但他仍然善良与赤诚，不断寻找同类，最后变成最孤独的一个人。 ——莫言',
                '那时我们有梦，关于文学，关于爱情，关于穿越世界的旅行。如今我们深夜饮酒，杯子碰到一起，都是梦破碎的声音。—— 北岛',
                '我觉得人的脆弱和坚强都超乎自己的想象。有时，我可能脆弱得一句话就泪流满面；有时，也发现自己咬着牙走了很长的路。——莫泊桑'
            ],
            circleUrl:'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'
        }
    },
    mounted() {
        // 百度统计集成开始
        const _hmt = _hmt || [];
        (function () {
            var hm = document.createElement("script");
            hm.src = "https://hm.baidu.com/hm.js?37ea8aa769f87ef76eeaa7d7844cb451";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
        // 百度统计集成结束
    },
    methods: {
        handleSelect(key) {
            this.activeIndex = key;
        },
        login(){
            /*this.$axios.get('/api/login', {}).then(res => {
                console.log(res);
            }).catch(err => {
                console.log(err);
            }).finally(() => {
            })*/
            window.open('/api/login')
        }
    }
}
</script>

<style>
#app {
    /*font-family: Avenir, Helvetica, Arial, sans-serif;*/
    /*-webkit-font-smoothing: antialiased;*/
    /*-moz-osx-font-smoothing: grayscale;*/
    /*color: #2c3e50;*/
    /*margin-top: 60px;*/
}

/*.el-carousel__item h3 {
    color: #eaebee;
    font-size: 18px;
    opacity: 0.75;
    line-height: 100px;
    margin: 0;
}

.el-carousel__item:nth-child(2n) {
    background-color: #606266;
}

.el-carousel__item:nth-child(2n+1) {
    background-color: #606266;
}*/
</style>
