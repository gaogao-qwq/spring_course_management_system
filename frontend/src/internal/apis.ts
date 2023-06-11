import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:8080'
axios.defaults.withCredentials = true

export const LoginApi = axios.create({
  url: '/auth/login',
})

export const UserInfoApi = axios.create({
  url: '/user/me',
})

export const UpdateUserInfoApi = axios.create({
  url: '/user/update',
})

export const GetUserCountApi = axios.create({
  url: '/user/count'
})

export const FetchUserApi = axios.create({
  url: '/user/all'
})

export const FetchUserByPageApi = axios.create({
  url: '/user/'
})

export const FetchStudentApi = axios.create({
  url: '/student/all'
})

export const FetchStudentByPageApi = axios.create({
  url: '/student/'
})

export const GetStudentCountApi = axios.create({
  url: '/student/count'
})

export const FetchClassApi = axios.create({
  url: '/class/all'
})

export const FetchClassByPageApi = axios.create({
  url: '/class/'
})

export const GetClassCountApi = axios.create({
  url: '/class/count'
})

export const FetchTeacherApi = axios.create({
  url: '/teacher/all'
})

export const FetchTeacherByPageApi = axios.create({
  url: '/teacher/'
})

export const GetTeacherCountApi = axios.create({
  url: '/teacher/count'
})

export const FetchMajorApi = axios.create({
  url: '/major/all'
})

export const FetchMajorByPageApi = axios.create({
  url: '/major/'
})

export const GetMajorCountApi = axios.create({
  url: '/major/count'
})

export const FetchCourseApi = axios.create({
  url: '/course/all'
})

export const FetchCourseByPageApi = axios.create({
  url: '/course/'
})

export const GetCourseCountApi = axios.create({
  url: '/course/count'
})


