<template>
    <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <div class="tool-doc">
            <h3>加密/解密</h3>
        </div>
        <el-form-item label="加解密方式" style="text-align:left">
            <el-radio v-model="form.type" label="DES">DES</el-radio>
            <el-radio v-model="form.type" label="AES">AES</el-radio>
        </el-form-item>
        <el-form-item label="加解密密钥" prop="checkSecretKey" style="width: 600px">
            <el-input placeholder="请输入密钥" v-model="form.key" show-password clearable></el-input>
        </el-form-item>
        <el-form-item label="加解密内容">
            <el-input
                    type="textarea"
                    :autosize="{ minRows: 3, maxRows: 10}"
                    placeholder="请输入加解密内容"
                    v-model="form.inputStr">
            </el-input>
        </el-form-item>
        <el-form-item label="加解密结果">
            <el-input
                    type="textarea"
                    :autosize="{ minRows: 3, maxRows: 10}"
                    placeholder="加解密结果"
                    v-model="form.resStr">
            </el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="encryptSubmit('form')">加密</el-button>
            <el-button type="primary" @click="dncryptSubmit('form')">解密</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
export default {
    name: "CipherOperation",
    data() {
        var checkSecretKey = (rule, value, callback) => {
            if (this.form.key === '') {
                return callback(new Error('密钥不能为空'));
            }
            if (this.form.type === 'DES' && this.form.key.length !== 8) {
                return callback(new Error('DES加解密 密钥要求8位!'));
            }
            if (this.form.type === 'AES' && this.form.key.length !== 16) {
                return callback(new Error('AES加解密 密钥要求16位!'));
            }
        }
        return {
            rules: {
                checkSecretKey: [
                    {validator: checkSecretKey, trigger: 'blur'}
                ]
            },
            form: {
                type: 'DES',
                key: '',
                inputStr: '',
                resStr: ''
            }
        };
    },
    methods: {
        encryptSubmit(formName) {
            let validateFlag = true;
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    validateFlag = true;
                } else {
                    validateFlag = false;
                    console.log('error submit!!');
                    return false;
                }
            });

            if (validateFlag) {
                this.$axios.post('/api/cipher/encrypt', this.form).then(res => {
                    this.form.resStr = res.data;
                }).catch(err => {
                    this.$message.error('加密异常');
                    console.log(err);
                }).finally(() => {
                })
            }
        },
        dncryptSubmit(formName) {
            let validateFlag = true;
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    validateFlag = true;
                } else {
                    validateFlag = false;
                    console.log('error submit!!');
                    return false;
                }
            });

            if (validateFlag) {
                this.$axios.post('/api/cipher/dncrypt', this.form).then(res => {
                    this.form.resStr = res.data;
                }).catch(err => {
                    this.$message.error('解密异常');
                    console.log(err);
                }).finally(() => {
                })
            }
        }
    },
}
</script>

<style scoped>

</style>