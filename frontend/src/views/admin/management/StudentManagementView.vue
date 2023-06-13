<script lang="ts" setup>
import { FetchStudentByPageApi, GetStudentCountApi } from '@/internal/apis';
import type { Student } from '@/internal/types';
import { isString } from '@vueuse/shared';
import { ElMessage, type TableColumnCtx } from 'element-plus';
import { ref, type Ref } from 'vue';

const handlePageSwitch = async (page: number) => {
  let resp = await FetchStudentByPageApi.get('/student/', {
    params: {
      page: page-1,
      size: tableSize.value
    }
  }).then((r) => {
    return r.data.data
  }).catch((reason) => {
    return reason as string
  })
  if (isString(resp)) {
    ElMessage.error("获取学生数据失败")
    return
  }
  currentPage.value = page;
  tableData.value = resp;
}

let studentCount = ref(await GetStudentCountApi
  .get('/student/count')
  .then((r) => {
  return r.data.data
}).catch(() => {
  ElMessage.error("获取学生数失败")
  return NaN
}));

const currentPage = ref(1)
const tableSize = ref(10)
const tableData: Ref<Student[]> = ref([])

const genderFormatter = (row: Student, column: TableColumnCtx<Student>, cellValue: number) => {
  return cellValue === 0 ? "男" : "女"
};

const dateFormatter = (row: Student, column: TableColumnCtx<Student>, cellValue: string) => {
  return cellValue.slice(0, 10)
};

const handleEdit = (index: number, row: Student) => {

}

await handlePageSwitch(currentPage.value)
</script>

<template>
  <el-card class="container-card">
    <el-table :data="tableData" stripe max-height="630px">
      <el-table-column fixed="left" prop="codeName" label="学号" min-width="120" />
      <el-table-column prop="name" label="姓名" min-width="150" />
      <el-table-column prop="gender" label="性别" min-width="55" :formatter="genderFormatter" />
      <el-table-column prop="admissionDate" label="入学日期" min-width="100" :formatter="dateFormatter" />
      <el-table-column prop="birthDate" label="出生日期" min-width="100" :formatter="dateFormatter" />
      <el-table-column prop="fkClass.name" label="班级" min-width="225" />
      <el-table-column prop="fkClass.fkMajor.name" label="专业" min-width="200" />
      <el-table-column fixed="right" prop="operation" label="操作" width="60">
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
        :total="studentCount"
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