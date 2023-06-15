import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:8080'
axios.defaults.withCredentials = true

export const LoginApi = axios.create({
  url: '/auth/login',
  timeout: 5000
})

export const UserInfoApi = axios.create({
  url: '/user/me',
  timeout: 5000
})

export const UpdateUserInfoApi = axios.create({
  url: '/user/update',
  timeout: 5000
})

export const GetUserCountApi = axios.create({
  url: '/user/count',
  timeout: 5000
})

export const FetchUserApi = axios.create({
  url: '/user/all',
  timeout: 5000
})

export const FetchUserByPageApi = axios.create({
  url: '/user/',
  timeout: 5000
})

export const FetchStudentApi = axios.create({
  url: '/student/all',
  timeout: 5000
})

export const FetchStudentByPageApi = axios.create({
  url: '/student/',
  timeout: 5000
})

export const GetStudentCountApi = axios.create({
  url: '/student/count',
  timeout: 5000
})

export const UpdateStudentApi = axios.create({
  url: '/student/update',
  timeout: 5000
})

export const FetchClassApi = axios.create({
  url: '/class/all',
  timeout: 5000
})

export const FetchClassByPageApi = axios.create({
  url: '/class/',
  timeout: 5000
})

export const GetClassCountApi = axios.create({
  url: '/class/count',
  timeout: 5000
})

export const FetchTeacherApi = axios.create({
  url: '/teacher/all',
  timeout: 5000
})

export const FetchTeacherByPageApi = axios.create({
  url: '/teacher/',
  timeout: 5000
})

export const GetTeacherCountApi = axios.create({
  url: '/teacher/count',
  timeout: 5000
})

export const FetchMajorApi = axios.create({
  url: '/major/all',
  timeout: 5000
})

export const FetchMajorByPageApi = axios.create({
  url: '/major/',
  timeout: 5000
})

export const GetMajorCountApi = axios.create({
  url: '/major/count',
  timeout: 5000
})

export const FetchCourseApi = axios.create({
  url: '/course/all',
  timeout: 5000
})

export const FetchCourseByPageApi = axios.create({
  url: '/course/',
  timeout: 5000
})

export const GetCourseCountApi = axios.create({
  url: '/course/count',
  timeout: 5000
})


