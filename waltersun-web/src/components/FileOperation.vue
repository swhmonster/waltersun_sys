<template>
  <div>
    <div v-for="(link,index) in linkList" :key="index">
      <a @click="download(link.fileAddress,link.fileName)" v-text="link.fileName" target="_blank" style="cursor:pointer"/>
    </div>
  </div>
</template>

<script>
export default {
  name: "FileOperation",
  data() {
    return {
      linkList: []
    }
  },
  mounted() {
    this.$axios.get('/api/file/fileList', {params: {id: 'xxx'}}).then(res => {
      this.linkList = res.data.data
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
