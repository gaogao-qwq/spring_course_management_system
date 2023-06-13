<script setup lang="ts">
import { computed, inject } from 'vue'
import type { VueCookies } from 'vue-cookies';
import { useRouter } from 'vue-router';

const router = useRouter()

const props = defineProps({
  role: String
})

const circleAvatarUrl = 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'
const $cookies = inject<VueCookies>('$cookies') as VueCookies;

const handleRouting = (path: string) => {
  router.push(path)
}

const handleLogout = () => {
  $cookies?.remove("token")
  $cookies?.remove("username")
  handleRouting("/")
}

const userInfo = computed(() =>{
  let date = new Date()
  if (date.getHours() < 12) {
    return `早上好，${$cookies.get('username')}`
  }
  if (date.getHours() < 18) {
    return `下午好，${$cookies.get('username')}`
  }
  return `晚上好，${$cookies.get('username')}`
})

</script>

<template>
  <div class="toolbar">
    <el-text size="large">教务管理系统</el-text>
    <div class="flex-grow" />
    <el-text>{{ userInfo }}</el-text>
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
  .flex-grow {
    flex-grow: 1;
  }

  .toolbar {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100%;
  }
</style>