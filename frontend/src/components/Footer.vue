<script setup lang="ts">

</script>

<template>
  <div class="footer">
    <el-space direction="vertical">
      <el-text>
        该网站的前端和后端代码都开源至
        <el-link
          type="primary"
          :underline="true"
          href="https://github.com/gaogao-qwq/spring_course_management_system"
        >
          GitHub 
        </el-link> 
        并自豪地使用 
        <el-link
          type="primary"
          :underline="true"
          href="https://www.gnu.org/licenses/gpl-3.0.en.html"
        >
          GPL-3.0 协议
        </el-link>
        和
        <el-link
          type="primary"
          :underline="true"
          href="https://www.gnu.org/licenses/agpl-3.0.en.html"
        >
          AGPL-3.0 协议
        </el-link>
      </el-text>
      <el-text>
        Made with ❤️ by 
        <el-link
          type="primary"
          :underline="true"
          href="https://github.com/gaogao-qwq"
        >
          gaogao_qwq
        </el-link>
      </el-text>
    </el-space>
  </div>
</template>

<style scoped>
.footer {
  position: relative;
  text-align: center;
}
</style>