<template>
    <el-form ref="form" :model="form" label-width="0"
             label-position="right">
        <div class="tool-doc">
            <h3>生成UUID</h3>
        </div>
        <el-form-item>
            <el-input v-model="form.uuid1" style="width: 350px;"></el-input>
        </el-form-item>
        <el-form-item>
            <el-input v-model="form.uuid2" style="width: 350px;"></el-input>
        </el-form-item>
        <el-form-item>
            <el-input v-model="form.uuid3" style="width: 350px;"></el-input>
        </el-form-item>
        <el-form-item>
            <el-input v-model="form.uuid4" style="width: 350px;"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="generate">生成</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
import {v4} from 'uuid';

export default {
    name: "UuidHelper",
    data() {
        return {
            form: {
                uuid1: '',
                uuid2: '',
                uuid3: ''
            }
        }
    },
    methods: {
        generate() {
            let newUuid = v4();
            this.form.uuid1 = newUuid.toLowerCase();
            this.form.uuid2 = newUuid.toLowerCase().replace(/[-]/g, '');
            this.form.uuid3 = newUuid.toUpperCase();
            this.form.uuid4 = newUuid.toUpperCase().replace(/[-]/g, '');
        }
    }
}
</script>

<style scoped>

</style>