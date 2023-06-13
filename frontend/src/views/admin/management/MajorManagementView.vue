<script lang="ts" setup>
import { FetchMajorByPageApi, GetMajorCountApi } from '@/internal/apis';
import type { Major } from '@/internal/types';
import { isString } from '@vueuse/shared';
import { ElMessage } from 'element-plus';
import { ref, type Ref } from 'vue';

const handlePageSwitch = async (page: number) => {
  let resp = await FetchMajorByPageApi.get('/major/', {
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
    ElMessage.error("获取专业数据失败")
    return
  }
  currentPage.value = page;
  tableData.value = resp;
}

let majorCount = ref(await GetMajorCountApi
  .get('/major/count')
  .then((r) => {
  return r.data.data
}).catch(() => {
  ElMessage.error("获取专业数失败")
  return NaN
}));

const handleEdit = (index: number, row: Major) => {

}

const currentPage = ref(1)
const tableSize = ref(10)
const tableData: Ref<Major[]> = ref([])

await handlePageSwitch(currentPage.value)
</script>

<template>
  <el-card class="container-card">
    <el-table :data="tableData" stripe max-height="630px">
      <el-table-column fixed="left" prop="codeName" label="专业号" min-width="120" />
      <el-table-column prop="name" label="专业名" min-width="200"/>
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
        :total="majorCount"
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