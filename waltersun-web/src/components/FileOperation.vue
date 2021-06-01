<template>
  <el-table :data="linkList" v-loading="loading">
    <el-table-column prop="fileName" label="下载列表"/>
    <el-table-column prop="fileName" label="" width="110">
      <template slot-scope="scope">
        <el-button type="primary" size="mini" @click="download(scope.row.fileAddress, scope.row.fileName)">download
        </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  name: "FileOperation",
  data() {
    return {
      linkList: [],
      loading: true
    }
  },
  mounted() {
    this.$axios.get('/api/file/fileList', {params: {id: 'xxx'}}).then(res => {
      this.linkList = res.data.data
      this.loading = false;
    }).catch(err => {
      this.$message.error('页面加载异常');
      console.log(err);
    }).finally(() => {
      this.loading = false;
    })
  },
  methods: {
    download(path, fileName) {
      window.open('/api/file/fileInfos?path=' + encodeURI(path) + '&fileName=' + encodeURI(fileName), '_blank')
    }
  }
}
</script>

<style scoped>

</style>
