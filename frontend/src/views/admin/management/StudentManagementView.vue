<script lang="ts" setup>
import { FetchClass, FetchStudentByPage, GetStudentCount, UpdateStudentInfo } from '@/internal/apis';
import { genders } from '@/internal/constants';
import type { Response, Student } from '@/internal/types';
import { ElMessage, type FormInstance, type FormRules, type TableColumnCtx } from 'element-plus';
import { isEmpty, isNull, isUndefined } from 'lodash';
import { reactive, ref, type Ref } from 'vue';

const handlePageSwitch = async (page: number) => {
  let resp = await FetchStudentByPage(page, tableSize.value)
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
  studentCount.value = await GetStudentCount()
  if (studentCount.value === NaN) ElMessage.error("获取学生数失败")
}

const studentCount = ref(0);
const currentPage = ref(1);
const tableSize = ref(10);
const tableData: Ref<Student[]> = ref(<Student[]>[]);
const editDialogEnable = ref(false);
const ruleFormRef = ref<FormInstance>();
const form: Ref<Student> = ref(<Student>{});
const origin: Ref<Student> = ref(<Student>{});
const cancelConfirm = ref(false)
const submitConfirm = ref(false)

const genderFormatter = (row: Student, column: TableColumnCtx<Student>, cellValue: number) => {
  return cellValue === 0 ? "男" : "女"
};

const dateFormatter = (row: Student, column: TableColumnCtx<Student>, cellValue: Date) => {
  return cellValue.toString().slice(0, 10)
};

const handleEdit = async (index: number, row: Student) => {
  origin.value = row
  form.value = JSON.parse(JSON.stringify(row))
  const classes = await FetchClass()
  editDialogEnable.value = true;
}

const rules = reactive<FormRules>({
  codeName: [{
    validator: (rule: any, value: any, callback: any) => {
      if (isNull(value) || isEmpty(value)) {
        callback(new Error("学号不得为空"))
      }
      callback()
    },
    trigger: "blur"
  }],
  name: [{
    validator: (rule: any, value: any, callback:any) => {
      if (isNull(value) || isEmpty(value)) {
        callback(new Error("姓名不得为空"))
      }
      callback()
    },
    trigger: "blur"
  }],
  gender: [{
    validator: (rule: any, value: any, callback:any) => {
      if (isNull(value) || isEmpty(value)) {
        callback(new Error("性别不得为空"))
      }
      callback()
    },
    trigger: "blur"
  }],
  birthDate: [{
    validator: (rule: any, value: any, callback:any) => {
      if (isNull(value) || isEmpty(value)) {
        callback(new Error("出生日期不得为空"))
      }
      callback()
    },
    trigger: "blur"
  }],
  admissionDate: [{
    validator: (rule: any, value: any, callback:any) => {
      if (isNull(value) || isEmpty(value)) {
        callback(new Error("入学日期不得为空"))
      }
      callback()
    },
    trigger: "blur"
  }],
  fkClass: [{
    validator: (rule: any, value: any, callback:any) => {
      if (isNull(value)) {
        callback(new Error("班级不得为空"))
      }
      callback()
    },
    trigger: "blur"
  }]
})

const onSubmit = async (formEl: FormInstance | undefined) => {
  if (isUndefined(formEl) || isNull(formEl)) return
  console.log(form.value)
  const resp = await UpdateStudentInfo(form.value)
  if (isNull(resp)) return
  if (!resp.success) {
    ElMessage(resp.message)
  }
  editDialogEnable.value = false
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

  <el-dialog
    v-model="editDialogEnable"
    :draggable="true"
    :show-close="false"
    width="60%"
    :close-on-click-modal="false"
    class="editDialog"
    align-center
    center
    style="border-radius: 8px;"
  >
    <template #header="{titleId, titleClass}">
      <el-space direction="horizontal" class="dialog-header">
        <h4 :id="titleId" :class="titleClass">
          编辑学生信息
        </h4>
        <el-button
          type="danger"
          @click="cancelConfirm = submitConfirm = editDialogEnable = false"
        >
          <el-icon class="el-icon--left"><CircleCloseFilled /></el-icon>
          关闭
        </el-button>
      </el-space>
    </template>
    <el-divider />
    <el-form
      :model="form"
      :rules="rules"
      ref="ruleFormRef"
      label-position="right"
      label-width="80px"
      status-icon
    >
      <el-form-item prop="codeName" label="学号">
        <el-input v-model="form.codeName" max-length="20" />
      </el-form-item>
      <el-form-item prop="name" label="姓名">
        <el-input v-model="form.name" max-length="20" min-length="5"/>
      </el-form-item>
      <el-form-item prop="gender" label="性别">
        <el-select v-model="form.gender">
          <el-option
            v-for="item in genders"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item prop="birthDate" label="出生日期">
        <el-date-picker 
          v-model="form.birthDate"
          type="date"
          placeholder="选择出生日期"
        />
      </el-form-item>
      <el-form-item prop="admissionDate" label="入学日期">
        <el-date-picker
          v-model="form.admissionDate"
          type="date"
          placeholder="选择入学日期"
        />
      </el-form-item>
      <el-form-item prop="fkClass" label="班级">
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-popover
          placement="top"
          :visible="cancelConfirm"
          width="200"
        >
          <p>有未提交的变更，确定放弃变更？</p>
          <el-button @click="cancelConfirm = false">
            取消
          </el-button>
          <el-button 
            type="warning"
            @click="cancelConfirm = editDialogEnable = false"
          >
            放弃
          </el-button>
          <template #reference>
            <el-button
              @click="cancelConfirm = editDialogEnable = 
                JSON.stringify(form) === JSON.stringify(origin) 
                  ? false 
                  : true"
            >
              取消
            </el-button>
          </template>
        </el-popover>
        <el-popover
          placement="top"
          :visible="submitConfirm"
          width="200"
          style="align-items: center; align-content: center;"
        >
          <p>提交的内容没有更改</p>
          <el-button @click="submitConfirm = false">确定</el-button>
          <template #reference>
            <el-button
              type="primary"
              @click="JSON.stringify(form) === JSON.stringify(origin)
                ? submitConfirm = true
                : onSubmit(ruleFormRef)"
            >
              提交
            </el-button>
          </template>
        </el-popover>
        
      </span>
    </template>
  </el-dialog>
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
  
  .dialog-header {
    display: flex;
    justify-content: space-between;
    margin-top: -18px;
    margin-bottom: -60px;
  }

  .dialog-header button:first-child {
    margin-right: -20px;
  }

  .dialog-footer button:first-child {
    margin-right: 10px;
  }
</style>