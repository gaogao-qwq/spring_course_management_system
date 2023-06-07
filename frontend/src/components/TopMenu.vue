<script setup lang="ts">
import { computed, inject } from 'vue'
import router from '@/router'
import type { VueCookies } from 'vue-cookies';

const props = defineProps({
  role: String
})

const circleAvatarUrl = 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'
const $cookies = inject<VueCookies>('$cookies');

const activeIndex = computed(() => {
  return router.currentRoute.value.path
})

const handleRouting = (path: string) => {
  router.push(path)
}

const handleLogout = () => {
  $cookies?.remove("token")
  $cookies?.remove("username")
  handleRouting("/")
}

</script>

<template>
  <div class="toolbar">
    <span>{{ $cookies.get('username') }}</span>
    <el-dropdown>
      <el-icon style="margin-left: 8px; margin-top: 1px">
        <setting/>
      </el-icon>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item @click="handleRouting('/'+props.role as string+'/profile')">编辑档案</el-dropdown-item>
        </el-dropdown-menu>
        <el-dropdown-menu>
          <el-dropdown-item @click="handleLogout()">登出</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </div>
</template>

<style scoped>
.toolbar {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  right: 20px;
}
</style>