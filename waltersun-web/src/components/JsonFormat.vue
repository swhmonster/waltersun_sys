<template>
    <el-main>
        <div class="tool-doc">
            <h3>JSON格式化</h3>
        </div>
        <el-button type="text" @click="handleTabsEdit(null,'add')"> + New Tab </el-button>
        <el-tabs v-model="editableTabsValue" type="card" editable @edit="handleTabsEdit">
            <el-tab-pane
                    :key="item.name"
                    v-for="(item) in editableTabs"
                    :label="item.title"
                    :name="item.name"
            >
                <el-form ref="form" :model="item.form" label-width="0">
                    <el-container>
                        <el-main style="width: 50%">
                            <el-form-item class="demo-form-inline" :inline="true">
                                <el-input
                                        type="textarea"
                                        :autosize="{ minRows: 15, maxRows: 1000}"
                                        placeholder="请输入JSON字符串！"
                                        v-model="item.form.jsonStr"
                                        maxlength="65535">
                                </el-input>
                            </el-form-item>
                        </el-main>
                        <el-main style="width: 50%; overflow: hidden">
                            <json-viewer :value="item.jsonData"
                                         :expand-depth=5
                                         copyable
                                         expanded
                                         sort
                                         show-array-index
                                         style="height: 100%"></json-viewer>
                            <div style="margin-bottom: 22px"></div>
                        </el-main>
                    </el-container>
                    <el-form-item class="demo-form-inline" :inline="true" style="text-align: center;">
                        <el-button type="primary" @click="formatToJson(item)">转JSON</el-button>
                        <el-button type="primary" @click="formatToString(item)">转String</el-button>
                    </el-form-item>
                </el-form>
            </el-tab-pane>
        </el-tabs>
    </el-main>
</template>

<script>

import 'vue-json-viewer/style.css'

export default {
    name: "JsonFormat",
    data() {
        return {
            jsonData: {},
            form: {
                jsonStr: ''
            },
            editableTabsValue: '1',
            editableTabs: [{
                title: 'New Tab 1',
                name: '1',
                jsonData: {},
                form: {
                    jsonStr: ''
                }
            }, {
                title: 'New Tab 2',
                name: '2',
                jsonData: {},
                form: {
                    jsonStr: ''
                }
            }],
            tabIndex: 2
        }
    },
    methods: {
        formatToJson(item) {
            try {
                let postStr = item.form.jsonStr;
                let jsonRes = JSON.parse(item.form.jsonStr);
                item.jsonData = jsonRes;
                item.form.jsonStr = JSON.stringify(jsonRes, null, '\t');
                this.$notify({
                    title: 'success',
                    message: 'JSON格式化成功！',
                    type: 'success',
                    jsonData: {},
                    form: {
                        jsonStr: ''
                    }
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
        formatToString(item) {
            try {
                item.form.jsonStr = JSON.stringify(JSON.parse(item.form.jsonStr), null, null);
                item.jsonData = item.form.jsonStr;
                this.$notify({
                    title: 'success',
                    message: 'JSON格式化成功！',
                    type: 'success'
                });
                let postStr = item.form.jsonStr;
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
        handleTabsEdit(targetName, action) {
            if (action === 'add') {
                let newTabName = ++this.tabIndex + '';
                this.editableTabs.push({
                    title: 'New Tab ' + newTabName,
                    name: newTabName,
                    content: 'New Tab content',
                    jsonData: {},
                    form: {
                        jsonStr: ''
                    }
                });
                this.editableTabsValue = newTabName;
            }
            if (action === 'remove') {
                let tabs = this.editableTabs;
                let activeName = this.editableTabsValue;
                if (activeName === targetName) {
                    tabs.forEach((tab, index) => {
                        if (tab.name === targetName) {
                            let nextTab = tabs[index + 1] || tabs[index - 1];
                            if (nextTab) {
                                activeName = nextTab.name;
                            }
                        }
                    });
                }

                this.editableTabsValue = activeName;
                this.editableTabs = tabs.filter(tab => tab.name !== targetName);
            }
        }
    }
}
</script>

<style scoped>
.tool-doc {
    text-align: center;
}
</style>