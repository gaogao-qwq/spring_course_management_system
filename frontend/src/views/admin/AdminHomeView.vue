<script lang="ts" setup>
import { GetClassCountApi, GetCourseCountApi, GetMajorCountApi, GetStudentCountApi, GetTeacherCountApi, GetUserCountApi } from '@/internal/apis';
import { ElMessage } from 'element-plus';
import { computed, ref } from 'vue';

const welcome = computed(() => {
  const weekdays = ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"]
  let date = new Date()
  let year = date.getFullYear().toString()
  let month = date.getMonth().toString()
  let day = date.getDate().toString()
  let hour = date.getHours().toString()
  let min = date.getMinutes().toString()
  let weekday = date.getDay()
  if (date.getHours() < 12) {
    return `早上好，现在是 ${year}年${month}月${day}日，晚上 ${hour.length < 2 ? '0'+hour : hour}:${min.length < 2 ? '0'+min : min}，${weekdays[weekday]}。`
  }
  if (date.getHours() < 18) {
    return `下午好，现在是 ${year}年${month}月${day}日，晚上 ${hour.length < 2 ? '0'+hour : hour}:${min.length < 2 ? '0'+min : min}，${weekdays[weekday]}。`
  }
  return `晚上好，现在是 ${year}年${month}月${day}日，晚上 ${hour.length < 2 ? '0'+hour : hour}:${min.length < 2 ? '0'+min : min}，${weekdays[weekday]}。`
})

let studentCount = ref(await GetStudentCountApi
  .get('/student/count')
  .then((r) => {
    return r.data.data
}).catch(() => {
  ElMessage.error("获取学生数失败")
  return NaN
}));

let classCount = ref(await GetClassCountApi
  .get('/class/count')
  .then((r) => {
  return r.data.data
}).catch(() => {
  ElMessage.error("获取班级数失败")
  return NaN
}));

let teacherCount = ref(await GetTeacherCountApi
  .get('/teacher/count')
  .then((r) => {
  return r.data.data
}).catch(() => {
  ElMessage.error("获取教师数失败")
  return NaN
}));

let courseCount = ref(await GetCourseCountApi
  .get('/course/count')
  .then((r) => {
  return r.data.data
}).catch(() => {
  ElMessage.error("获取课程数失败")
  return NaN
}));

let majorCount = ref(await GetMajorCountApi
  .get('/major/count')
  .then((r) => {
  return r.data.data
}).catch(() => {
  ElMessage.error("获取专业数失败")
  return NaN
}));

let userCount = ref(await GetUserCountApi
  .get('/user/count')
  .then((r) => {
  return r.data.data
}).catch(() => {
  ElMessage.error("获取用户数失败")
  return NaN
}));

</script>

<template>
  <div class="card-info-block">
    <el-row :gutter="30">
      <el-col :span="8">

        <router-link to="/admin/student">
          <el-card class="info-card" shadow="hover">
            <el-space direction="vertical">
              <el-space direction="horizontal">
                <el-icon color="var(--el-color-primary)"><Postcard /></el-icon>
                <el-text type="primary" size="large">学生</el-text>
              </el-space>
              <el-text>共有 {{ studentCount }}名学生</el-text>
            </el-space>
          </el-card>
        </router-link>

      </el-col>
      <el-col :span="8">

        <router-link to="/admin/student">
          <el-card class="info-card" shadow="hover">
            <el-space direction="vertical">
              <el-space direction="horizontal">
                <el-icon color="var(--el-color-primary)"><DataBoard /></el-icon>
                <el-text type="primary" size="large">班级</el-text>
              </el-space>
              <el-text>共有 {{ classCount }}个班级</el-text>
            </el-space>
          </el-card>
        </router-link>

      </el-col>
      <el-col :span="8">
        
        <router-link to="/admin/teacher">
          <el-card class="info-card" shadow="hover">
            <el-space direction="vertical">
              <el-space direction="horizontal">
                <el-icon color="var(--el-color-primary)"><Avatar /></el-icon>
                <el-text type="primary" size="large">教师</el-text>
              </el-space>
              <el-text>共有 {{ teacherCount }}名教师</el-text>
            </el-space>
          </el-card>
        </router-link>

      </el-col>
    </el-row>

    <el-row :gutter="30">
      <el-col :span="8">

        <router-link to="/admin/major">
          <el-card class="info-card" shadow="hover">
            <el-space direction="vertical">
              <el-space direction="horizontal">
                <el-icon color="var(--el-color-primary)"><Management /></el-icon>
                <el-text type="primary" size="large">专业</el-text>
              </el-space>
              <el-text>共有 {{ majorCount }}个专业</el-text>
            </el-space>
          </el-card>
        </router-link>

      </el-col>
      <el-col :span="8">

        <router-link to="/admin/course">
          <el-card class="info-card" shadow="hover">
            <el-space direction="vertical">
              <el-space direction="horizontal">
                <el-icon color="var(--el-color-primary)"><Reading /></el-icon>
                <el-text type="primary" size="large">课程</el-text>
              </el-space>
              <el-text>共有 {{ courseCount }}个课程</el-text>
            </el-space>
          </el-card>
        </router-link>

      </el-col>
      <el-col :span="8">

        <router-link to="/admin/user">
          <el-card class="info-card" shadow="hover">
            <el-space direction="vertical">
              <el-space direction="horizontal">
                <el-icon color="var(--el-color-primary)"><UserFilled /></el-icon>
                <el-text type="primary" size="large">用户</el-text>
              </el-space>
              <el-text>共有 {{ userCount }}个用户</el-text>
            </el-space>
          </el-card>
        </router-link>
        
      </el-col>
    </el-row>
    <el-row>
      <el-col>
        <el-card class="info-card" shadow="hover">
          {{ welcome }}
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<style scoped>
  .card-info-block {
    margin: 10px
  }

  .el-row {
    margin-bottom: 20px;
  }

  .info-card {
    border-radius: 8px;
    align-items: center;
    text-align: center;
  }
</style>
