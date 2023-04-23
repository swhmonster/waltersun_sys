<template>
    <div>
        <div class="tool-doc">
            <h3>当前时间 和 时间戳</h3>
        </div>
        <el-form ref="form" :model="form" label-width="80px" class="demo-form-inline" :inline="true"
                 label-position="right">
            <el-form-item label="时间">
                <el-input v-model="form.dt" style="width: 170px"></el-input>
            </el-form-item>
            <el-form-item label="时间戳(s)">
                <el-input v-model="form.ts" style="width: 170px"></el-input>
            </el-form-item>
            <el-form-item label="时间戳(ms)">
                <el-input v-model="form.tms" style="width: 170px"></el-input>
            </el-form-item>
        </el-form>
        <el-form ref="form1" :model="form1" :rules="rules1" label-width="80px" class="demo-form-inline" :inline="true"
                 label-position="left">
            <div class="tool-doc">
                <h3>时间戳 -> 日期时间</h3>
            </div>
            <el-form-item prop="checkEmpty1">
                <el-input v-model="form1.timestamp" placeholder="1681982671506" clearable style="width: 230px"></el-input>
            </el-form-item>
            <el-form-item>
                <el-select v-model="form1.unit" placeholder="毫秒(ms)">
                    <el-option v-for="item in options1" :key="item.value" :label="item.label" :value="item.value"/>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" plain @click="onSubmit1('form1')">转换-></el-button>
            </el-form-item>
            <el-form-item lable="时区选择">
                <el-select v-model="form1.zone" placeholder="时区:北京时间">
                    <el-option v-for="item in zoneOption1" :key="item.value" :label="item.label" :value="item.value"/>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-input v-model="form1.datetime" placeholder="转换结果" clearable style="width: 230px"></el-input>
            </el-form-item>
        </el-form>
        <el-form ref="form2" :model="form2" :rules="rules2" label-width="80px" class="demo-form-inline" :inline="true"
                 label-position="left">
            <div class="tool-doc">
                <h3>日期时间 -> 时间戳</h3>
            </div>
            <el-form-item prop="checkEmpty2">
                <el-input v-model="form2.datetime" placeholder="1970-1-1 00:00:00" clearable style="width: 230px"></el-input>
            </el-form-item>
            <el-form-item lable="时区选择">
                <el-select v-model="form2.zone" placeholder="时区:北京时间">
                    <el-option v-for="item in zoneOption2" :key="item.value" :label="item.label" :value="item.value"/>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" plain @click="onSubmit2('form2')">转换-></el-button>
            </el-form-item>
            <el-form-item>
                <el-select v-model="form2.unit" placeholder="毫秒(ms)">
                    <el-option v-for="item in options2" :key="item.value" :label="item.label" :value="item.value"/>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-input v-model="form2.timestamp" placeholder="转换结果" clearable style="width: 230px"></el-input>
            </el-form-item>
        </el-form>
        <el-form ref="form3" :model="form3" :rules="rules3" label-width="70px" class="demo-form-inline" :inline="true"
                 label-position="left">
            <div class="tool-doc">
                <h3>日期格式化</h3>
            </div>
            <el-form-item prop="checkEmpty3">
                <el-input v-model="form3.datetime" placeholder="请输入日期" style="width: 250px" clearable></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" plain @click="onSubmit3('form3')">格式化-></el-button>
            </el-form-item>
            <el-form-item label="格式选择">
                <el-select v-model="form3.pattern" placeholder="" style="width: 250px">
                    <el-option v-for="item in partternOption" :key="item.value" :label="item.label"
                               :value="item.value"/>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-input v-model="form3.result" placeholder="格式化结果" style="width: 250px" clearable></el-input>
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
        var checkEmpty3 = (rule, value, callback) => {
            if (this.form3.datetime === '') {
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
            rules3: {
                checkEmpty3: [
                    {validator: checkEmpty3, trigger: 'blur'}
                ]
            },
            form: {
                dt: '',
                ts: '',
                tms: ''
            },
            form1: {
                timestamp: '',
                datetime: '',
                unit: 'MILLISECONDS',
                zone: 'Asia/Shanghai'
            },
            form2: {
                timestamp: '',
                datetime: '',
                unit: 'MILLISECONDS',
                zone: 'Asia/Shanghai'
            },
            form3: {
                datetime: '',
                pattern: 'yyyy-MM-dd HH:mm:ss',
                result: ''
            },
            partternOption: [
                {value: 'yyyy-MM-dd HH:mm:ss:SSS', label: '1970-01-01 00:00:00:000'},
                {value: 'yyyy-MM-dd HH:mm:ss', label: '1970-01-01 00:00:00'},
                {value: 'yyyy-MM-dd HH:mm', label: '1970-01-01 00:00'},
                {value: 'yyyy-MM-dd', label: '1970-01-01'},
                {value: 'yyyy-MM', label: '1970-01'},
                {value: 'yyyy', label: '1970'},
                {value: 'yyyy/MM/dd HH:mm:ss:SSS', label: '1970/01/01 00:00:00:000'},
                {value: 'yyyy/MM/dd HH:mm:ss', label: '1970/01/01 00:00:00'},
                {value: 'yyyy/MM/dd HH:mm', label: '1970/01/01 00:00'},
                {value: 'yyyy/MM/dd', label: '1970/01/01'},
                {value: 'yyyy/MM', label: '1970/01'},
                {value: 'yyyyMMdd HH:mm:ss:SSS', label: '19700101 00:00:00:000'},
                {value: 'yyyyMMdd HH:mm:ss', label: '19700101 00:00:00'},
                {value: 'yyyyMMdd HH:mm', label: '19700101 00:00'},
                {value: 'yyyyMMddHHmmssSSS', label: '19700101000000000'},
                {value: 'yyyyMMddHHmmss', label: '19700101000000'},
                {value: 'yyyyMMddHHmm', label: '197001010000'},
                {value: 'yyyyMMdd', label: '19700101'},
                {value: 'yyyyMM', label: '197001'},
                {value: 'yyyy-MM-dd\'T\'HH:mm:ss.SSSZ', label: '1970-01-01T00:00:00.000+0800'},
                {value: 'yyyy-MM-dd\'T\'HH:mm:ss.SSSXXX', label: '1970-01-01T00:00:00.000+08:00'},
                {value: 'yyyy.MM.dd G \'at\' HH:mm:ss z', label: '1970.01.01 公元 at 00:00:00 CST'},
                {value: 'EEE, d MMM yyyy HH:mm:ss Z', label: '周四, 1 1月 1970 00:00:00 +0800'},
                {value: 'EEE, MMM d, \'\'yy', label: '周四, 1月 1, \'70'},
                {value: 'h:mm a', label: '12:00 上午'},
                {value: 'hh \'o\'\'clock\' a, zzzz', label: '12 o\'clock 上午, 中国标准时间'},
                {value: 'K:mm a, z', label: '0:00 上午, CST'},
                {value: 'yyyyy.MMMMM.dd GGG hh:mm aaa', label: '01970.一月.01 公元 12:00 上午'},
                {value: 'yyMMddHHmmssZ', label: '700101000000+0800'},
                {value: 'YYYY-\'W\'ww-u', label: '1970-W01-4'}
            ],
            zoneOption1: [
                {value: 'Asia/Shanghai', label: 'Asia/Shanghai'},
                {value: 'America/Los_Angeles', label: 'America/Los_Angeles'},
                {value: 'Australia/Darwin', label: 'Australia/Darwin'},
                {value: 'Australia/Sydney', label: 'Australia/Sydney'},
                {value: 'America/Argentina/Buenos_Aires', label: 'America/Argentina/Buenos_Aires'},
                {value: 'Africa/Cairo', label: 'Africa/Cairo'},
                {value: 'America/Anchorage', label: 'America/Anchorage'},
                {value: 'America/Sao_Paulo', label: 'America/Sao_Paulo'},
                {value: 'Asia/Dhaka', label: 'Asia/Dhaka'},
                {value: 'Africa/Harare', label: 'Africa/Harare'},
                {value: 'America/St_Johns', label: 'America/St_Johns'},
                {value: 'America/Chicago', label: 'America/Chicago'},
                {value: 'Africa/Addis_Ababa', label: 'Africa/Addis_Ababa'},
                {value: 'Europe/Paris', label: 'Europe/Paris'},
                {value: 'America/Indiana/Indianapolis', label: 'America/Indiana/Indianapolis'},
                {value: 'Asia/Kolkata', label: 'Asia/Kolkata'},
                {value: 'Asia/Tokyo', label: 'Asia/Tokyo'},
                {value: 'Pacific/Apia', label: 'Pacific/Apia'},
                {value: 'Asia/Yerevan', label: 'Asia/Yerevan'},
                {value: 'Pacific/Auckland', label: 'Pacific/Auckland'},
                {value: 'Asia/Karachi', label: 'Asia/Karachi'},
                {value: 'America/Phoenix', label: 'America/Phoenix'},
                {value: 'America/Puerto_Rico', label: 'America/Puerto_Rico'},
                {value: 'Pacific/Guadalcanal', label: 'Pacific/Guadalcanal'},
                {value: 'Asia/Ho_Chi_Minh', label: 'Asia/Ho_Chi_Minh'}
            ],
            zoneOption2: [
                {value: 'Asia/Shanghai', label: 'Asia/Shanghai'},
                {value: 'America/Los_Angeles', label: 'America/Los_Angeles'},
                {value: 'Australia/Darwin', label: 'Australia/Darwin'},
                {value: 'Australia/Sydney', label: 'Australia/Sydney'},
                {value: 'America/Argentina/Buenos_Aires', label: 'America/Argentina/Buenos_Aires'},
                {value: 'Africa/Cairo', label: 'Africa/Cairo'},
                {value: 'America/Anchorage', label: 'America/Anchorage'},
                {value: 'America/Sao_Paulo', label: 'America/Sao_Paulo'},
                {value: 'Asia/Dhaka', label: 'Asia/Dhaka'},
                {value: 'Africa/Harare', label: 'Africa/Harare'},
                {value: 'America/St_Johns', label: 'America/St_Johns'},
                {value: 'America/Chicago', label: 'America/Chicago'},
                {value: 'Africa/Addis_Ababa', label: 'Africa/Addis_Ababa'},
                {value: 'Europe/Paris', label: 'Europe/Paris'},
                {value: 'America/Indiana/Indianapolis', label: 'America/Indiana/Indianapolis'},
                {value: 'Asia/Kolkata', label: 'Asia/Kolkata'},
                {value: 'Asia/Tokyo', label: 'Asia/Tokyo'},
                {value: 'Pacific/Apia', label: 'Pacific/Apia'},
                {value: 'Asia/Yerevan', label: 'Asia/Yerevan'},
                {value: 'Pacific/Auckland', label: 'Pacific/Auckland'},
                {value: 'Asia/Karachi', label: 'Asia/Karachi'},
                {value: 'America/Phoenix', label: 'America/Phoenix'},
                {value: 'America/Puerto_Rico', label: 'America/Puerto_Rico'},
                {value: 'Pacific/Guadalcanal', label: 'Pacific/Guadalcanal'},
                {value: 'Asia/Ho_Chi_Minh', label: 'Asia/Ho_Chi_Minh'}
            ],
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
    mounted() {
        this.currentTime();
    },
    beforeDestroy() {
        if (this.formatDate) {
            clearInterval(this.formatDate);
        }
    },
    methods: {
        currentTime() {
            setInterval(this.formatDate, 1000)
        },
        formatDate() {
            let time = new Date()
            let year = time.getFullYear()
            const month = (time.getMonth() + 1).toString().padStart(2, '0')
            const date = (time.getDate()).toString().padStart(2, '0')
            const hours = (time.getHours()).toString().padStart(2, '0')
            const minute = (time.getMinutes()).toString().padStart(2, '0')
            const second = (time.getSeconds()).toString().padStart(2, '0')
            this.form.dt = year + '-' + month + '-' + date + ' ' + hours + ':' + minute + ':' + second;
            this.form.ts = parseInt(new Date().getTime() / 1000) + ''
            this.form.tms = parseInt(new Date().getTime()) + ''
        },
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
                this.$axios.get('/api/utils/datetime?unit=' + this.form1.unit + '&timestamp=' + this.form1.timestamp + '&zone=' + this.form1.zone, {}).then(res => {
                    this.form1.datetime = res.data;
                    this.$notify({
                        title: 'success',
                        message: '转换成功！',
                        type: 'success'
                    });
                }).catch(err => {
                    this.$notify({
                        title: '转换异常',
                        message: '请检查输入和目标结果',
                        type: 'warning'
                    });
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
                this.$axios.get('/api/utils/timestamp?unit=' + this.form2.unit + '&dateTime=' + this.form2.datetime + '&zone=' + this.form2.zone, {}).then(res => {
                    this.form2.timestamp = res.data;
                    this.$notify({
                        title: 'success',
                        message: '转换成功！',
                        type: 'success'
                    });
                }).catch(err => {
                    this.$notify({
                        title: '转换异常',
                        message: '请检查输入和目标结果',
                        type: 'warning'
                    });
                    console.log(err);
                }).finally(() => {
                })
            }
        },
        onSubmit3(formName) {
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
                this.$axios.get('/api/utils/format?dateTime=' + this.form3.datetime + '&parttern=' + this.form3.pattern, {}).then(res => {
                    this.form3.result = res.data;
                    this.$notify({
                        title: 'success',
                        message: '格式化成功！',
                        type: 'success'
                    });
                }).catch(err => {
                    this.$notify({
                        title: '格式化异常',
                        message: '请检查输入和目标结果，部分格式的转化存在精度问题，会导致失败！',
                        type: 'warning'
                    });
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