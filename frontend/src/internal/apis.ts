import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:8080'
axios.defaults.withCredentials = true

export const LoginApi = axios.create({
  url: '/auth/login',
})

export const UserInfoApi = axios.create({
  url: '/user/',
})

export const UpdateUserInfoApi = axios.create({
  url: '/user/update',
})


