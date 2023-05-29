<script setup lang="ts">
import { ref, reactive } from 'vue';
import { useUserStore } from '@/stores/user';
import { isNull, isString } from 'lodash';

const dialogVisible = ref(false)
const dialogMessage = ref("")

const form = reactive({
  "username": "",
  "password": ""
})

const onLogin = () => {
  let msg = useUserStore().login(form.username, form.password)
  if (!isNull(msg)) {
    dialogVisible.value = true
    dialogMessage.value = msg as string
  }
}
</script>

<template>

  <el-card class="login-form">
    <h2>登录</h2>
    <el-divider/>
    <el-form 
      label-position="top"
    >
      <el-form-item label="账号">
        <el-input v-model="form.username" />
      </el-form-item>
      <el-form-item label="密码">
        <el-input type="password" v-model="form.password" />
      </el-form-item>
      <el-row justify="center">
        <el-button
          type="primary"
          @click=onLogin
        >
          登陆
        </el-button>
      </el-row>
    </el-form>
  </el-card>

  <el-dialog
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
  .login-form {
    position: relative;
    border-radius: 16px;
    margin-top: 30%;
    margin-left: 40%;
    margin-right: 10%;
    margin-bottom: 30%;
  }
  .login-form h2 {
    text-align: center;
  }
</style>
