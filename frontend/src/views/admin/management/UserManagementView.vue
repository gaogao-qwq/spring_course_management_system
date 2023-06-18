<script lang="ts" setup>
import { FetchUserByPage, GetUserCount } from '@/internal/apis';
import type { User } from '@/internal/types';
import { isNull } from 'lodash'
import { ElMessage, type TableColumnCtx } from 'element-plus';
import { ref, type Ref } from 'vue';

const handlePageSwitch = async (page: number) => {
  let resp = await FetchUserByPage(page, tableSize.value)
  if (isNull(resp)) {
    ElMessage.error("连接服务器错误")
    return
  }
  if (!resp.success) {
    ElMessage.error(resp.message)
    return
  }
  currentPage.value = page;
  tableData.value = resp.data;
  userCount.value = await GetUserCount()
  if (userCount.value === NaN) ElMessage.error("获取用户数失败")
}

const roleFormatter = (row: User, column: TableColumnCtx<User>, cellValue: string[]) => {
  let roles: string[] = [];
  cellValue.forEach(e => {
    switch (e) {
      case "ROLE_ADMIN":
        roles.push("管理员")
        break
      case "ROLE_STUDENT":
        roles.push("学生")
        break
      case "ROLE_TEACHER":
        roles.push("教师")
        break
      default:
        roles.push("未知")
        break
    }
  });
  return roles.join(', ')
};

const handleEdit = (index: number, row: User) => {

}
const userCount = ref(0)
const currentPage = ref(1)
const tableSize = ref(10)
const tableData: Ref<User[]> = ref([])

await handlePageSwitch(currentPage.value)
</script>

<template>
  <el-card class="container-card">
    <el-table :data="tableData" stripe max-height="630px">
      <el-table-column fixed="left" prop="username" label="用户名" min-width="120" />
      <el-table-column prop="roles" label="身份" min-width="100" :formatter="roleFormatter" />
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
        :total="userCount"
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