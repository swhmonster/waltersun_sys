import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import ElementUI from 'element-ui'
import locale from 'element-ui/lib/locale/lang/en'

Vue.config.productionTip = false
Vue.prototype.$axios = axios

Vue.use(ElementUI, { locale })

new Vue({
  render: h => h(App),
}).$mount('#app')
