<template>
    <el-form ref="form" :model="form" label-width="0">
        <div class="tool-doc">
            <h3>JSON格式化</h3>
        </div>
        <el-form-item>
            <el-radio-group v-model="ratio1">
                <el-radio-button label="Code"></el-radio-button>
                <el-radio-button label="Tree"></el-radio-button>
            </el-radio-group>
        </el-form-item>
        <json-viewer :value="jsonData"
                     :expand-depth=5
                     copyable
                     boxed expanded sort v-show="ratio1==='Tree'"></json-viewer>
        <div style="margin-bottom: 22px"></div>
        <el-form-item class="demo-form-inline" :inline="true" v-show="ratio1==='Code'">
            <el-input
                    type="textarea"
                    :autosize="{ minRows: 15, maxRows: 1000}"
                    placeholder="请输入JSON字符串！"
                    v-model="form.jsonStr"
                    maxlength="65535">
            </el-input>
        </el-form-item>
        <el-form-item class="demo-form-inline" :inline="true" style="text-align: center;">
            <el-button type="primary" @click="formatToJson">转JSON</el-button>
            <el-button type="primary" @click="formatToString">转String</el-button>
        </el-form-item>
    </el-form>
</template>

<script>

import 'vue-json-viewer/style.css'

export default {
    name: "JsonFormat",
    data() {
        return {
            ratio1: 'Code',
            jsonData: {},
            form: {
                jsonStr: ''
            }
        }
    },
    methods: {
        formatToJson() {
            try {
                let postStr = this.form.jsonStr;
                let jsonRes = JSON.parse(this.form.jsonStr);
                this.jsonData = jsonRes;
                this.form.jsonStr = JSON.stringify(jsonRes, null, '\t');
                this.$notify({
                    title: 'success',
                    message: 'JSON格式化成功！',
                    type: 'success'
                });
                this.$axios.post('/api/utils/jsonFormat', postStr).then(res => {
                    console.log(res);
                }).catch(err => {
                    console.log(err);
                }).finally(() => {
                })
            } catch (e) {
                this.$notify({
                    title: '发生异常',
                    message: 'JSON格式化异常，请检查！',
                    type: 'warning'
                });
                console.log(e);
            }
        },
        formatToString() {
            try {
                this.form.jsonStr = JSON.stringify(JSON.parse(this.form.jsonStr), null, null);
                this.jsonData = this.form.jsonStr;
                this.$notify({
                    title: 'success',
                    message: 'JSON格式化成功！',
                    type: 'success'
                });
                let postStr = this.form.jsonStr;
                this.$axios.post('/api/utils/jsonFormat', postStr).then(res => {
                    console.log(res);
                }).catch(err => {
                    console.log(err);
                }).finally(() => {
                })
            } catch (e) {
                this.$notify({
                    title: '发生异常',
                    message: 'JSON格式化异常，请检查！',
                    type: 'warning'
                });
                console.log(e);
            }
        }
    }
}
</script>

<style scoped>

</style>