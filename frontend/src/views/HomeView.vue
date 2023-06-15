<script setup lang="ts">
import { ref, reactive, inject } from 'vue';
import { isEmpty, isNull, isUndefined } from 'lodash';
import { LoginApi } from '@/internal/apis';
import type { VueCookies } from 'vue-cookies';
import { ElDialog, type FormInstance, type FormRules } from 'element-plus';
import type { Response } from '@/internal/types';
import { useRouter } from 'vue-router';

const router = useRouter()
const $cookies = inject<VueCookies>('$cookies');
const dialogVisible = ref(false)
const dialogMessage = ref("")

if ($cookies?.isKey("token")) {
  router.push("/admin/home")
}

const ruleFormRef = ref<FormInstance>()
const form = reactive({
  "username": "",
  "password": ""
})

const rules = reactive<FormRules>({
  username: [{
    validator: (rule: any, value: any, callback: any) => {
      if (isNull(value) || isEmpty(value)) {
        callback(new Error("账号不得为空"))
      }
      callback()
    },
    trigger: "blur"
  }],
  password: [{
    validator: (rule: any, value: any, callback: any) => {
      if (isNull(value) || isEmpty(value)) {
        callback(new Error("密码不得为空"))
      }
      callback()
    },
    trigger: "blur"
  }],
})

const onLogin = async (formEl: FormInstance | undefined) => {
  if (isUndefined(formEl) || isNull(formEl)) return

  formEl?.validate(async (valid) => {
    if (!valid) {
      return;
    }

    let resp = await LoginApi
      .post('/auth/login', {
        'username': form.username,
        'password': form.password
    })
      .then((r) => {
        if ((r.data as Response).success) {
          $cookies?.set("token", r.data.data.token, r.data.data.validityInMs as number/1000)
          $cookies?.set("username", r.data.data.username, r.data.data.validityInMs as number/1000)
        }
        return r.data as Response
    })
      .catch((reason) => {
        dialogMessage.value = "连接服务器失败"
        dialogVisible.value = true
        return;
    })
    if (!isNull(resp) && (resp as Response).success === false) {
      dialogMessage.value = (resp as Response).message
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
      </el-form>
      <el-button
        type="primary"
        @click=onLogin(ruleFormRef)
      >
        登陆
      </el-button>
    </el-card>
  </div>

  <el-dialog
    class="dialog"
    v-model="dialogVisible"
    title="注意"
    width="30%"
    center
    style="border-radius: 8px;"
  >
    <span>{{ dialogMessage }}</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button
          type="primary"
          @click="dialogVisible = false"
        >
          确定
        </el-button>
      </span>
    </template>
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
    margin-top: 200px;
    width: 400px;
    height: 310px;
    padding: 30px;
    border-radius: 16px;
    text-align: center;
    backdrop-filter: blur(10px)
  }

  .dialog-footer {
    margin-bottom: 10px;
  }
</style>
