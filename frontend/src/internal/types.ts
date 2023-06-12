export interface Student {
  id: number
  codeName: string
  name: string
  gender: number
  birthDate: string
  admissionDate: string
  fkClass: Class
}

export interface Teacher {
  id: number
  codeNmae: string
  name: string
  gender: number
  birthDate: string
  admissionDate: string
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
