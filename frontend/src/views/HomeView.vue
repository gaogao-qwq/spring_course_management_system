<script setup lang="ts">
import { ref, reactive, inject } from 'vue';
import { isEmpty, isNull, isUndefined } from 'lodash';
import router from '@/router';
import { LoginApi } from '@/internal/apis';
import type { VueCookies } from 'vue-cookies';
import type { FormInstance, FormRules } from 'element-plus';


const $cookies = inject<VueCookies>('$cookies');
const dialogVisible = ref(false)
const dialogMessage = ref("")

const ruleFormRef = ref<FormInstance>()
const form = reactive({
  "username": "",
  "password": ""
})

const usernameValidator = (rule: any, value: any, callback: any) => {
  if (isNull(value) || isEmpty(value)) {
    callback(new Error("账号不得为空"))
  }
  callback()
}

const passwordValidator = (rule: any, value: any, callback: any) => {
  if (isNull(value) || isEmpty(value)) {
    console.log(value)
    callback(new Error("密码不得为空"))
  }
  callback()
}

const rules = reactive<FormRules>({
  username: [{ validator: usernameValidator, trigger: "blur" }],
  password: [{ validator: passwordValidator, trigger: "blur" }],
})

const onLogin = async (formEl: FormInstance | undefined) => {
  if (isUndefined(formEl) || isNull(formEl)) return

  formEl?.validate(async (valid) => {
    if (!valid) {
      return;
    }

    let msg = await LoginApi
      .post('/auth/login', {
        'username': form.username,
        'password': form.password
    })
      .then((r) => {
        if (!r.data.success || isNull(r.data.data)) {
          return r.data.message;
        }
        $cookies?.set("token", r.data.data.token, r.data.data.validityInMs as number/1000)
        $cookies?.set("username", r.data.data.username, r.data.data.validityInMs as number/1000)
        console.log($cookies)
        return ""
    })
      .catch(() => {
        return "服务连接失败"
    })
    if (!isEmpty(msg)) {
      dialogMessage.value = msg
      dialogVisible.value = true
      return
    }
    router.push("/admin/home")
  })
}
</script>

<template>

  <div class="container">
    <el-card class="login-form">
      <h2>登录</h2>
      <el-divider/>
      <el-form
        label-position="top"
        ref="ruleFormRef"
        status-icon
        :model="form"
        :rules="rules"
      >
        <el-form-item label="账号" prop="username">
          <el-input
            v-model="form.username"
            max-length="20"
            placeholder="输入账号"
          />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input
            type="password"
            v-model="form.password"
            max-length="20"
            placeholder="输入密码"
            show-password
            autocomplete="off"
          />
        </el-form-item>
        <el-row justify="center">
          <el-button
            type="primary"
            @click=onLogin(ruleFormRef)
          >
            登陆
          </el-button>
        </el-row>
      </el-form>
    </el-card>
  </div>

  <el-dialog
    class="dialog"
    v-model="dialogVisible"
    title="注意"
    width="30%"
    center
  >
    <span>{{ dialogMessage }}</span>
    <el-row justify="center">
      <el-button
        type="primary"
        @click="dialogVisible = false"
      >
        确定
      </el-button>
    </el-row>
</el-dialog>

</template>

<style scoped>
  .container {
    justify-content: center;
    display: flex;
    height: 100vh;
  }
  .login-form {
    position: relative;
    margin-top: 10%;
    width: 400px;
    height: 300px;
    padding: 30px;
    border-radius: 16px;
    text-align: center;
  }
  .dialog {
    border-radius: 8px;
  }
</style>
