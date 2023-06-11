<script setup lang="ts">
import { UserInfoApi } from '@/internal/apis';
import type { FormInstance, FormRules } from 'element-plus';
import { isEmpty, isNull, isString, isUndefined } from 'lodash';
import { reactive, ref } from 'vue';

let resp = await UserInfoApi
  .get('/user/me')
  .then((response) => {
    if(!response.data.success || isNull(response.data.data)) {
      return response.data.message
    }
    return response.data.data
})
  .catch(() => {
    return "服务连接失败"
});

if (isString(resp)) {
}

const username = resp.username
const ruleFormRef = ref<FormInstance>()
const form = reactive({
  username: resp.username,
  old_password: "",
  new_password: ""
})

const usernameValidator = (rule: any, value: any, callback: any) => {
  if (value != username) {
    callback(new Error("账号不可更改"))
  }
  callback()
}

const passwordValidator = (rule: any, value: any, callback: any) => {
  if (isEmpty(value) || isNull(value)) {
    callback(new Error("密码不得为空"))
  }
  if (form.new_password == form.old_password) {
    callback(new Error("新密码与旧密码一致"))
  }
  callback()
}

const rules = reactive<FormRules>({
  username: { validator: usernameValidator },
  old_password: { validator: passwordValidator },
  new_password: { validator: passwordValidator },
})

const onSubmit = async (formEl: FormInstance | undefined) => {
  if (isUndefined(formEl) || isNull(formEl)) return

  formEl?.validate(async (valid) => {
    if (!valid) {
      return;
    }
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