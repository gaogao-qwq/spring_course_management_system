<script setup lang="ts">
import { auto } from '@popperjs/core';

</script>

<template>
  <div class="footer">
    <el-space direction="vertical">
      <el-text>
        Release under the 
        <el-link href="https://www.gnu.org/licenses/gpl-3.0.en.html">GPL-3.0 license</el-link>
      </el-text>
      <el-text>
        Made with ❤️ by 
        <el-link href="https://github.com/gaogao-qwq">gaogao_qwq</el-link>
      </el-text>
    </el-space>
  </div>
</template>

<style scoped>
.footer {
  position: relative;
  height: auto;
  box-sizing: border-box;
  text-align: center;
}
</style>