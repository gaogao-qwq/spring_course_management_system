import { createApp } from "vue";
import { createPinia } from "pinia";
import VueCookies from "vue-cookies"
import ElementPlus from 'element-plus'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'

import * as ElementPlusIconsVue from '@element-plus/icons-vue'

import App from "./App.vue";
import router from "./router/router";

import './assets/main.css'
import 'element-plus/dist/index.css'

const app = createApp(App);
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component);
}

app.use(ElementPlus, { locale: zhCn })
   .use(createPinia())
   .use(VueCookies, {expires: '1h', path: '/'})
   .use(router)

app.mount("#app");