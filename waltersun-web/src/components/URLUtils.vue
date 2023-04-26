<template>
    <el-form ref="form" :model="form" label-width="0"
             label-position="right" style="text-align: center;">
        <div class="tool-doc">
            <h3>URL Encode编码/Decode解码</h3>
        </div>
        <el-form-item>
            <el-input
                    type="textarea"
                    :autosize="{ minRows: 10, maxRows: 20}"
                    placeholder="请输入URL..."
                    v-model="form.url"
                    maxlength="65535">
            </el-input>
        </el-form-item>
        <el-form-item class="demo-form-inline" :inline="true">
            <el-button type="primary" @click="encode">Encode编码</el-button>
            <el-button type="primary" @click="decode">Decode解码</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
export default {
    name: "URLUtils",
    data() {
        return {
            form: {
                url: '',
                utf: ''
            }
        }
    },
    methods: {
        encode() {
            this.form.url = encodeURIComponent(this.form.url);
            this.$notify({
                title: 'success',
                message: 'URL编码成功',
                type: 'success'
            });
        },
        decode() {
            this.form.url = decodeURIComponent(this.form.url);
            this.$notify({
                title: 'success',
                message: 'URL解码成功',
                type: 'success'
            });
        }
    }
}
</script>

<style scoped>

</style>