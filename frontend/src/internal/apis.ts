import axios from 'axios'
import type { Class, Response } from '@/internal/types'
import type { VueCookies } from 'vue-cookies'

axios.defaults.baseURL = '/api'
axios.defaults.withCredentials = true
axios.defaults.timeout = 5000

const cookies: VueCookies = $cookies

export const Login = async function Login(username: string, password: string) {
  return await axios.post('/auth/login', {
      'username': username,
      'password': password
    },
    {
      withCredentials: false,
    })
      .then((r) => {
        if ((r.data as Response).success) {
          cookies.set("token", r.data.data.token, r.data.data.validityInMs as number/1000)
          cookies.set("username", r.data.data.username, r.data.data.validityInMs as number/1000)
        }
        return r.data as Response
    })
      .catch(() => {
        return null;
    })
}

export async function GetUserProfile() {
  return axios.get('/user/me')
    .then((r) => {
    return r.data as Response
  })
    .catch(() => {
    return null
  });
}

export async function UpdateUserInfo (form: any) {
  return axios.put('/user/update', form)
    .then((r) => {
    return r.data as Response
  })
    .catch(() => {
    return null
  })
}

export async function GetUserCount() {
  return await axios.get('/user/count')
    .then((r) => {
      if (!r.data.success) return NaN
      return r.data.data as number
  })
    .catch(() => {
    return NaN
  })
}

export async function FetchUserByPage(page: number, size: number) {
  return axios.get('/user/', {
    params: {
      page: page-1,
      size: size
    }
  }).then((r) => {
    return r.data as Response
  }).catch(() => {
    return null
  })
}

export async function FetchStudentByPage(page: number, size: number) {
  return axios.get('/student/', {
    params: {
      page: page-1,
      size: size
    }
  }).then((r) => {
    return r.data as Response
  }).catch(() => {
    return null
  })
}

export async function GetStudentCount() {
  return await axios.get('/student/count')
    .then((r) => {
      if (!r.data.success) return NaN
      return r.data.data as number
  })
    .catch(() => {
    return NaN
  })
}

export async function UpdateStudentInfo(form: any) {
  return axios.put('/student/update', form)
    .then((r) => {
    return r.data as Response
  })
    .catch(() => {
    return null
  })
}

export async function FetchClass() {
  return await axios.get('/class/all')
    .then((r) => {
    return r.data as Response
  })
    .catch((reason) => {
    return null
  })
}

export async function FetchClassByPage(page: number, size: number) {
  return axios.get('/class/', {
    params: {
      page: page-1,
      size: size
    }
  }).then((r) => {
    return r.data as Response
  }).catch(() => {
    return null
  })
}

export async function GetClassCount() {
  return await axios.get('/class/count')
    .then((r) => {
    if (!r.data.success) return NaN
    return r.data.data as number
  })
    .catch(() => {
    return NaN
  })
}

export async function FetchTeacherByPage(page: number, size: number) {
  return axios.get('/teacher/', {
    params: {
      page: page-1,
      size: size
    }
  }).then((r) => {
    return r.data as Response
  }).catch(() => {
    return null
  })
}

export async function GetTeacherCount() {
  return await axios.get('/teacher/count')
    .then((r) => {
    if (!r.data.success) return NaN
    return r.data.data as number
  })
    .catch(() => {
    return NaN
  })
}

export async function FetchMajorByPage(page: number, size: number) {
  return axios.get('/major/', {
    params: {
      page: page-1,
      size: size
    }
  }).then((r) => {
    return r.data as Response
  }).catch(() => {
    return null
  })
}

export async function GetMajorCount() {
  return await axios.get('/major/count')
    .then((r) => {
    if (!r.data.success) return NaN
    return r.data.data as number
  })
    .catch(() => {
    return NaN
  })
}

export async function FetchCourse() {
  return await axios.get('/course/all')
    .then((r) => {
    return r.data as Response
  })
    .catch(() => {
    return null
  })
}

export async function FetchCourseByPage(page: number, size: number) {
  return axios.get('/course/', {
    params: {
      page: page-1,
      size: size
    }
  }).then((r) => {
    return r.data as Response
  }).catch(() => {
    return null
  })
}

export async function GetCourseCount() {
  return await axios.get('/course/count')
    .then((r) => {
    if (!r.data.success) return NaN
    return r.data.data as number
  })
    .catch(() => {
    return NaN
  })
}


