<script setup lang="ts">
import { GetUserProfile, UpdateUserInfo } from '@/internal/apis';
import type { User } from '@/internal/types';
import router from '@/router/router';
import { ElMessage, type FormInstance, type FormRules } from 'element-plus';
import { isEmpty, isNull, isUndefined } from 'lodash';
import { inject, reactive, ref } from 'vue';
import type { VueCookies } from 'vue-cookies';

const $cookies = inject<VueCookies>('$cookies');
const ruleFormRef = ref<FormInstance>()
let user = <User>{}
const form = reactive({
  username: "",
  old_password: "",
  new_password: ""
})

let resp = await GetUserProfile()
if (isNull(resp)) {
  ElMessage.error("连接服务器错误")
} else if (!resp.success) {
  ElMessage.error(resp.message)
} else {
  user = resp.data as User
  form.username = user.username
}

const rules = reactive<FormRules>({
  username: [{
    validator: (rule: any, value: any, callback: any) => {
      if (value != user.username) {
        callback(new Error("账号不可更改"))
      }
      callback()
    },
    trigger: "blur"
  }],
  old_password: [{
    validator: (rule: any, value: any, callback: any) => {
      if (isEmpty(value) || isNull(value)) {
        callback(new Error("密码不得为空"))
      }
      if (form.new_password == form.old_password) {
        callback(new Error("新密码与旧密码一致"))
      }
      callback()
    },
    trigger: "blur"
  }],
  new_password: [{
    validator: (rule: any, value: any, callback: any) => {
      if (isEmpty(value) || isNull(value)) {
        callback(new Error("密码不得为空"))
      }
      if (form.new_password == form.old_password) {
        callback(new Error("新密码与旧密码一致"))
      }
      callback()
    },
    trigger: "blur"
  }],
})

const onSubmit = async (formEl: FormInstance | undefined) => {
  if (isUndefined(formEl) || isNull(formEl)) return

  formEl?.validate(async (valid) => {
    if (!valid) {
      return;
    }
    const resp = await UpdateUserInfo(form)
    if (isNull(resp)) {
      ElMessage.error("连接服务器失败")
      return;
    }
    if (!resp.success) {
      ElMessage.error(resp.message)
      return;
    }
    $cookies?.remove('token')
    $cookies?.remove('username')
    router.push('/')
    ElMessage.success("更改档案成功，请重新登陆。")
  });
}
</script>

<template>
  <el-card class="profile-card">
    <el-form 
      :model="form"
      :rules="rules"
      ref="ruleFormRef"
      status-icon
    >
      <el-form-item label="账号" prop="username">
        <el-input v-model="form.username" disabled />
      </el-form-item>
      <el-form-item label="旧密码" prop="old_password">
        <el-input 
          type="password"
          v-model="form.old_password"
          minlength="4"
          maxlength="20"
          placeholder="输入旧密码" 
          show-password
        />
      </el-form-item>
      <el-form-item label="新密码" prop="new_password">
        <el-input
          type="password"
          v-model="form.new_password"
          minlength="4"
          maxlength="20"
          placeholder="输入新密码"
          show-password
        />
      </el-form-item>
      <el-row justify="center">
        <el-button
          type="primary"
          @click=onSubmit(ruleFormRef)
        >
          提交
        </el-button>
      </el-row>
    </el-form>
  </el-card>
</template>

<style scoped>
.profile-card {
  border-radius: 8px;
  margin: 10px;
}
</style>