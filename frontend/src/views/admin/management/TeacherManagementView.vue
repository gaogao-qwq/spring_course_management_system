<script lang="ts" setup>
import { FetchTeacherByPage, GetTeacherCount } from '@/internal/apis';
import type { Teacher } from '@/internal/types';
import { isNull } from 'lodash';
import { ElMessage, type TableColumnCtx } from 'element-plus';
import { ref, type Ref } from 'vue';

const handlePageSwitch = async (page: number) => {
  let resp = await FetchTeacherByPage(page, tableSize.value)
  if (isNull(resp)) {
    ElMessage.error("连接服务器失败")
    return
  }
  if (!resp.success) {
    ElMessage.error(resp.message)
    return
  }
  currentPage.value = page;
  tableData.value = resp.data;
  teacherCount.value = await GetTeacherCount()
  if (teacherCount.value === NaN) ElMessage.error("获取教师数失败")
}

const genderFormatter = (row: Teacher, column: TableColumnCtx<Teacher>, cellValue: number) => {
  return cellValue === 0 ? "男" : "女"
};

const dateFormatter = (row: Teacher, column: TableColumnCtx<Teacher>, cellValue: Date) => {
  return cellValue.toString().slice(0,10)
};

const teacherCount = ref(0)
const currentPage = ref(1)
const tableSize = ref(10)
const tableData: Ref<Teacher[]> = ref([])

const handleEdit = (index: number, row: Teacher) => {

}

await handlePageSwitch(currentPage.value)
</script>

<template>
  <el-card class="container-card">
    <el-table :data="tableData" stripe max-height="630px">
      <el-table-column fixed="left" prop="codeName" label="教师号" min-width="120" />
      <el-table-column prop="name" label="教师名" min-width="150" />
      <el-table-column prop="gender" label="性别" min-width="55" :formatter="genderFormatter" />
      <el-table-column prop="admissionDate" label="入职日期" min-width="100" :formatter="dateFormatter" />
      <el-table-column prop="birthDate" label="出生日期" min-width="100" :formatter="dateFormatter" />
      <el-table-column fixed="right" label="操作" width="60">
        <template #default="scope">
          <el-button
            type="primary"
            link
            size="small"
            @click="handleEdit(scope.$index, scope.row)"
          >
            编辑
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="pagination-block">
      <el-pagination
        background 
        :page-size="tableSize"
        layout="prev, pager, next, jumper"
        :current-page="currentPage" 
        :total="teacherCount"
        @current-change="handlePageSwitch"
      />
    </div>
  </el-card>
</template>

<style scoped>
  .el-table {
    width: 100%;
  }

  .container-card {
    margin: 10px;
    border-radius: 8px;
  }
  
  .pagination-block {
    margin-top: 10px;
  }
</style>