<script setup lang="ts">
import { computed, ref } from 'vue'
import router from '@/router'

const circleAvatarUrl = 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'

const activeIndex = computed(() => {
  return router.currentRoute.value.path
})

const handleSelect = (key: string, keyPath: string[]) => {
  router.push(key)
}

</script>

<template>
  <div class="toolbar">
    <span>username</span>
    <el-dropdown>
      <el-icon style="margin-left: 8px; margin-top: 1px">
        <setting/>
      </el-icon>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item>View</el-dropdown-item>
          <el-dropdown-item>Add</el-dropdown-item>
          <el-dropdown-item>Delete</el-dropdown-item>
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