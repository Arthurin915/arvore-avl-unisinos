import Vue from 'vue'
import App from './App.vue'
import router from './router'

import '@/scss/general.scss';
import 'bootstrap/dist/css/bootstrap.css'
import './configs/axiosConfig'
import 'v-calendar/lib/v-calendar.min.css';
import VDatePicker from 'v-calendar'

import { BFormFile, BFormInput, BButton, BFormSelect, BInputGroup, BInputGroupAppend } from 'bootstrap-vue'
Vue.component('b-form-file', BFormFile)
Vue.component('b-form-input', BFormInput)
Vue.component('b-button', BButton)
Vue.component('b-form-select', BFormSelect)
Vue.component('b-input-group', BInputGroup)
Vue.component('b-input-group-append', BInputGroupAppend)
Vue.use(VDatePicker)
Vue.use(require('vue-moment'))

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
