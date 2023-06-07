<script setup lang="ts">
import router from '@/router';
import { computed } from '@vue/reactivity';
import TopMenu from '@/components/TopMenu.vue';
import Footer from '@/components/Footer.vue';

const activeIndex = computed(() => {
  return router.currentRoute.value.path
})

const handleSelect = (key: string, keyPath: string[]) => {
  router.push(key)
}
</script>

<template>
  <el-container style="height: 800px">
    <el-aside width="20%">
      <el-menu
        :default-active="activeIndex"
        class="el-top-menu"
        mode="vertical"
        :ellipsis="false"
        @select="handleSelect"
      >
        <el-menu-item index="/home">
          <el-icon><HomeFilled /></el-icon>
          <span>主页</span>
        </el-menu-item>
        <el-menu-item index="/student">
          <el-icon><HomeFilled /></el-icon>
          <span>学生管理</span>
        </el-menu-item>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="text-align: right; font-size: 12px">
        <TopMenu role="admin" />
      </el-header>
    
      <el-main>
        <Suspense>
          <RouterView/>
        </Suspense>
      </el-main>

      <el-footer>
        <Footer />
      </el-footer>
    </el-container>

  </el-container>
</template>

<style scoped>
  .el-header {
    position: relative;
    background-color: var(--el-color-primary-light-7);
    color: var(--el-text-color-primary);
  }
  .el-aside {
    color: var(--el-text-color-primary);
    background: var(--el-color-primary-light-8);
  }
  .el-menu {
    border-right: none;
  }
</style>