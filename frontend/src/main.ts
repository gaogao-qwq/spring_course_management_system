import { createApp } from 'vue'

import 'element-plus/dist/index.css'

import * as ElementPlusIconsVue from '@element-plus/icons-vue'

import router from './router'
import App from './App.vue'
import ElementPlus from 'element-plus'

import zhCn from 'element-plus/dist/locale/zh-cn.mjs'

const app = createApp(App)

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}

app.use(router)
app.use(ElementPlus, {
  locale: zhCn
})

app.mount('#app')
