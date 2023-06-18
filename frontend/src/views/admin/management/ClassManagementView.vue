<script lang="ts" setup>
import { FetchClassByPage, GetClassCount } from '@/internal/apis';
import type { Class } from '@/internal/types';
import { isNull } from 'lodash';
import { ElMessage } from 'element-plus';
import { ref, type Ref } from 'vue';

const handlePageSwitch = async (page: number) => {
  let resp = await FetchClassByPage(page, tableSize.value)
  if (isNull(resp)) {
    ElMessage.error("连接服务器失败")
    return
  }
  if (!resp.success) {
    ElMessage.error("获取班级数据失败")
  }
  currentPage.value = page;
  tableData.value = resp.data;
  classCount.value = await GetClassCount()
  if (classCount.value === NaN) ElMessage.error("获取班级数失败")
}

const classCount = ref(0)
const currentPage = ref(1)
const tableSize = ref(10)
const tableData: Ref<Class[]> = ref([])

const handleEdit = (index: number, row: Class) => {

}

await handlePageSwitch(currentPage.value)
</script>

<template>
  <el-card class="container-card">
    <el-table :data="tableData" stripe max-height="630px">
      <el-table-column fixed="left" prop="codeName" label="班级号" min-width="120" />
      <el-table-column prop="name" label="班级名" min-width="250" />
      <el-table-column prop="fkMajor.name" label="专业名" min-width="150" />
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
        :total="classCount"
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