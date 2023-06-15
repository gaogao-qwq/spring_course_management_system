export interface Student {
  id: number
  codeName: string
  name: string
  gender: number
  birthDate: Date
  admissionDate: Date
  fkClass: Class
}

export interface Teacher {
  id: number
  codeNmae: string
  name: string
  gender: number
  birthDate: Date
  admissionDate: Date
}

export interface Class {
  id: number
  codeNmae: string
  name: string
  fkMajor: Major
}

export interface Major {
  id: number
  codeNmae: string
  name: string
}

export interface Course {
  id: number
  codeNmae: string
  name: string
  fkTeacher: Teacher
  fkClass: Class
}

export interface User {
  username: string
  roles: string[]
}

export interface Response {
  success: boolean
  code: number
  message: string
  timestamp: number
  data: any
}
