<script lang="ts" setup>
import { FetchClassByPageApi, GetClassCountApi } from '@/internal/apis';
import type { Class } from '@/internal/types';
import { isString } from '@vueuse/shared';
import { ElMessage } from 'element-plus';
import { ref, type Ref } from 'vue';

const handlePageSwitch = async (page: number) => {
  let resp = await FetchClassByPageApi.get('/class/', {
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
    ElMessage.error("获取班级数据失败")
    return
  }
  currentPage.value = page;
  tableData.value = resp;
}

let classCount = ref(await GetClassCountApi
  .get('/class/count')
  .then((r) => {
  return r.data.data
}).catch(() => {
  ElMessage.error("获取班级数失败")
  return NaN
}));

const handleEdit = (index: number, row: Class) => {

}

const currentPage = ref(1)
const tableSize = ref(10)
const tableData: Ref<Class[]> = ref([])

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