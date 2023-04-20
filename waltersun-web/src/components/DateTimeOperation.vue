<template>
    <div>
        <el-form ref="form1" :model="form1" :rules="rules1" label-width="100px" class="demo-form-inline" inline="true">
            <el-form-item label="时间戳" prop="checkEmpty1">
                <el-input v-model="form1.timestamp" placeholder="1681982671506" lab></el-input>
            </el-form-item>
            <el-form-item>
                <el-select v-model="form1.unit" placeholder="毫秒(ms)">
                    <el-option v-for="item in options1" :key="item.value" :label="item.label" :value="item.value"/>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button @click="onSubmit1('form1')">转换-></el-button>
            </el-form-item>
            <el-form-item label="北京时间">
                <el-input v-model="form1.datetime" placeholder=""></el-input>
            </el-form-item>
        </el-form>
        <el-form ref="form2" :model="form2" :rules="rules2" label-width="100px" class="demo-form-inline" inline="true">
            <el-form-item label="时间:北京时间" prop="checkEmpty2">
                <el-input v-model="form2.datetime" placeholder="1970-1-1 00:00:00"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button @click="onSubmit2('form2')">转换-></el-button>
            </el-form-item>
            <el-form-item>
                <el-input v-model="form2.timestamp" placeholder=""></el-input>
            </el-form-item>
            <el-form-item>
                <el-select v-model="form2.unit" placeholder="毫秒(ms)">
                    <el-option v-for="item in options2" :key="item.value" :label="item.label" :value="item.value"/>
                </el-select>
            </el-form-item>
        </el-form>
        <div class="tool-doc" style="text-align:justify">
            <h3>时间戳</h3>
            <p>Unix 时间戳是从1970年1月1日（UTC/GMT的午夜）开始所经过的秒数，不考虑闰秒。</p>
            <h3>北京时间</h3>
            <h4>夏令时</h4>
            <p>
                1986年至1991年，中华人民共和国在全国范围实行了六年夏令时，每年从4月中旬的第一个星期日2时整(北京时间)到9月中旬第一个星期日的凌晨2时整(北京夏令时)。除1986年因是实行夏令时的第一年，从5月4日开始到9月14日结束外，其它年份均按规定的时段施行。夏令时实施期间，将时间向后调快一小时。1992年4月5日后不再实行。</p>
            <h4>JDK 不同版本的夏令时问题</h4>
            <p>夏令时的起止，是政令对日历描述的人为干预。每年均可能发生变化，JDK 如何感知这个规律并在系统上加以体现的？穷举所有变化，并配置在
                JDK 中。详见：<a target="_blank" href="https://www.oracle.com/java/technologies/tzdata-versions.html">Timezone
                    Data Versions in the JRE Software</a></p>
            <p>不同版本下 Asia/Shanghai 时区夏令时起始时间不同，早期维护者认为中国标准时间的夏令时切换发生在0时，而后来又经证明发生在2时，新版本
                JDK 及时修正了这个问题。</p>
        </div>
    </div>
</template>

<script>
export default {
    name: "DateTimeOperation",
    data() {
        var checkEmpty1 = (rule, value, callback) => {
            if (this.form1.timestamp === '') {
                return callback(new Error('时间戳必填！'));
            }
            if (this.form1.unit === 'MILLISECONDS' && this.form1.timestamp.length < 13) {
                return callback(new Error('毫秒级别 时间戳 应是13位！'));
            }
            if (this.form1.unit === 'SECONDS' && this.form1.timestamp.length > 10) {
                return callback(new Error('秒级别 时间戳 应是10位！'));
            }
        }
        var checkEmpty2 = (rule, value, callback) => {
            if (this.form2.datetime === '') {
                return callback(new Error('时间必填！'));
            }
        }
        return {
            rules1: {
                checkEmpty1: [
                    {validator: checkEmpty1, trigger: 'blur'}
                ]
            },
            rules2: {
                checkEmpty2: [
                    {validator: checkEmpty2, trigger: 'blur'}
                ]
            },
            form1: {
                timestamp: '',
                datetime: '',
                unit: 'MILLISECONDS'
            },
            form2: {
                timestamp: '',
                datetime: '',
                unit: 'MILLISECONDS'
            },
            options1: [
                {
                    value: 'MILLISECONDS',
                    label: '毫秒(ms)'
                },
                {
                    value: 'SECONDS',
                    label: '秒(s)'
                }
            ],
            options2: [
                {
                    value: 'MILLISECONDS',
                    label: '毫秒(ms)'
                },
                {
                    value: 'SECONDS',
                    label: '秒(s)'
                }
            ]
        };
    },
    methods: {
        onSubmit1(formName) {
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
                this.$axios.get('/api/datetime/datetime?unit=' + this.form1.unit + '&timestamp=' + this.form1.timestamp, {}).then(res => {
                    this.form1.datetime = res.data;
                }).catch(err => {
                    this.$message.error('转换异常，请检查入参！');
                    console.log(err);
                }).finally(() => {
                })
            }
        },
        onSubmit2(formName) {
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
                this.$axios.get('/api/datetime/timestamp?unit=' + this.form2.unit + '&dateTime=' + this.form2.datetime, {}).then(res => {
                    this.form2.timestamp = res.data;
                }).catch(err => {
                    this.$message.error('转换异常，请检查入参！');
                    console.log(err);
                }).finally(() => {
                })
            }
        }
    }
}
</script>

<style scoped>

</style>