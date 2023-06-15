<script setup lang="ts">
import { computed } from '@vue/reactivity';
import TopMenu from '@/components/TopMenu.vue';
import Footer from '@/components/Footer.vue';
import { useRouter } from 'vue-router';

  const router = useRouter()

const activeIndex = computed(() => {
  return router.currentRoute.value.path
})

const handleSelect = (key: string, keyPath: string[]) => {
  router.push(key)
}
</script>

<template>
  <el-container class="main-container">
    <el-aside width="20%">
      <el-scrollbar>
        <el-menu
          :default-active="activeIndex"
          class="el-top-menu"
          mode="vertical"
          :ellipsis="false"
          @select="handleSelect"
        >
          <el-menu-item index="/admin/home">
            <el-icon><HomeFilled /></el-icon>
            <span>主页</span>
          </el-menu-item>
          <el-menu-item index="/admin/student">
            <el-icon><Postcard /></el-icon>
            <span>学生管理</span>
          </el-menu-item>
          <el-menu-item index="/admin/class">
            <el-icon><DataBoard /></el-icon>
            <span>班级管理</span>
          </el-menu-item>
          <el-menu-item index="/admin/teacher">
            <el-icon><Avatar /></el-icon>
            <span>教师管理</span>
          </el-menu-item>
          <el-menu-item index="/admin/major">
            <el-icon><Management /></el-icon>
            <span>专业管理</span>
          </el-menu-item>
          <el-menu-item index="/admin/course">
            <el-icon><Reading /></el-icon>
            <span>课程管理</span>
          </el-menu-item>
          <el-menu-item index="/admin/user">
            <el-icon><UserFilled /></el-icon>
            <span>用户管理</span>
          </el-menu-item>
        </el-menu>
      </el-scrollbar>
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
  .main-container {
    height: 100vh;
  }

  .el-header {
    position: relative;
    background-color: var(--el-color-primary-light-7);
    color: var(--el-text-color-primary);
  }

  .el-aside {
    position: relative;
    color: var(--el-text-color-primary);
    background: var(--el-color-primary-light-5);
  }

  .el-main {
    padding: 10px;
    position: relative;
    width: 100%;
  }

  .el-footer {
    position: relative;
    height: 100px;
    padding: 10px;
    color: var(--el-text-color-primary);
    background: var(--el-color-primary-light-7);
  }
</style>