<template>
    <el-form ref="form" :model="form" label-width="0">
        <el-form-item>
            <el-input
                    type="textarea"
                    :autosize="{ minRows: 15, maxRows: 1000}"
                    placeholder="请输入JSON字符串！"
                    v-model="form.jsonStr">
            </el-input>
        </el-form-item>
        <el-form-item class="demo-form-inline" :inline="true">
            <el-button type="primary" @click="formatToJson">转JSON</el-button>
            <el-button type="primary" @click="formatToString">转String</el-button>
        </el-form-item>
    </el-form>
</template>

<script>

export default {
    name: "JsonFormat",
    data() {
        return {
            form: {
                jsonStr: '',
            }
        }
    },
    methods: {
        formatToJson() {
            try {
                this.form.jsonStr = JSON.stringify(JSON.parse(this.form.jsonStr), null, '\t');
            } catch (e) {
                this.$message.error('JSON格式化异常，请检查！');
                console.log(e);
            }
        },
        formatToString(){
            try {
                this.form.jsonStr = JSON.stringify(JSON.parse(this.form.jsonStr), null, null);
            } catch (e) {
                this.$message.error('JSON格式化异常，请检查！');
                console.log(e);
            }
        }
    }
}
</script>

<style scoped>

</style>