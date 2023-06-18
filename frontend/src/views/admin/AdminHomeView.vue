<script lang="ts" setup>
import { GetClassCount, GetCourseCount, GetMajorCount, GetStudentCount, GetTeacherCount, GetUserCount } from '@/internal/apis';
import { ElMessage } from 'element-plus';
import { computed } from 'vue';

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
    return `早上好，现在是 ${year}年${month}月${day}日，早上 ${hour.length < 2 ? '0'+hour : hour}:${min.length < 2 ? '0'+min : min}，${weekdays[weekday]}。`
  }
  if (date.getHours() < 18) {
    return `下午好，现在是 ${year}年${month}月${day}日，下午 ${hour.length < 2 ? '0'+hour : hour}:${min.length < 2 ? '0'+min : min}，${weekdays[weekday]}。`
  }
  return `晚上好，现在是 ${year}年${month}月${day}日，晚上 ${hour.length < 2 ? '0'+hour : hour}:${min.length < 2 ? '0'+min : min}，${weekdays[weekday]}。`
})

let studentCount = await GetStudentCount()
if (studentCount === NaN) ElMessage.error("获取学生数失败")

let classCount = await GetClassCount()
if (classCount === NaN) ElMessage.error("获取班级数失败")

let teacherCount = await GetTeacherCount()
if (teacherCount === NaN) ElMessage.error("获取教师数失败")

let courseCount = await GetCourseCount()
if (courseCount === NaN) ElMessage.error("获取课程数失败")

let majorCount = await GetMajorCount()
if (majorCount === NaN) ElMessage.error("获取专业数失败")

let userCount = await GetUserCount()
if (userCount === NaN) ElMessage.error("获取用户数失败")

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

        <router-link to="/admin/class">
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
