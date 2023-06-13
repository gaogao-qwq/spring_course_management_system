import profileView from "@/views/ProfileView.vue";
import adminView from "@/views/admin/AdminView.vue"
import homeView from "@/views/HomeView.vue";
import { createRouter, createWebHistory } from "vue-router";
import studentView from "@/views/student/StudentView.vue";
import teacherView from "@/views/teacher/TeacherView.vue";
import studentManagementView from "@/views/admin/StudentManagementView.vue";
import adminHomeView from "@/views/admin/AdminHomeView.vue";
import classManagementView from "@/views/admin/ClassManagementView.vue";
import majorManagementView from "@/views/admin/MajorManagementView.vue";
import courseManagementView from "@/views/admin/CourseManagementView.vue";
import userManagementView from "@/views/admin/UserManagementView.vue";
import teacherManagementView from "@/views/admin/TeacherManagementView.vue";
import { UserInfoApi } from "@/internal/apis";
import type { Response } from '@/internal/types';
import { isString } from "lodash";
import { ElMessage } from "element-plus";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      component: homeView,
    },
    {
      path: "/admin",
      component: adminView,
      children: [
        {
          path: "home",
          component: adminHomeView
        },
        {
          path: "student",
          component: studentManagementView
        },
        {
          path: "class",
          component: classManagementView
        },
        {
          path: "teacher",
          component: teacherManagementView
        },
        {
          path: "major",
          component: majorManagementView
        },
        {
          path: "course",
          component: courseManagementView
        },
        {
          path: "user",
          component: userManagementView
        },
        {
          path: "profile",
          component: profileView
        }
      ]
    },
    {
      path: "/teacher",
      component: teacherView,
      children: [
        {
          path: "profile",
          component: profileView,
        }
      ]
    },
    {
      path: "/student",
      component: studentView,
      children: [
        {
          path: "profile",
          component: profileView,
        }
      ]
    }
  ],
});

router.beforeEach(async (to, from) => {
  if (from.path === '' && document.cookie.split('; ').length !== 0) {
    return true;
  }
  if (to.path === '' || to.path === '/') {
    return true;
  }

  let cookies = document.cookie.split('; ')
  let token: string = ""
  for (var i = 0; i < cookies.length; i++) {
    let key = cookies[i].slice(0, cookies[i].indexOf('='))
    if (key === "token") {
      token = cookies[i].slice(cookies[i].indexOf('=')+1)
    }
  }

  if (token === "") {
    ElMessage.error("登陆已过期，请重新登陆。")
    return to.path === '' || to.path === '/' 
      ? false
      : '/'
  }

  const resp = await UserInfoApi.get('/user/me')
    .then((r) => {
      return r.data as Response
  })
    .catch((reason) => {
      return reason as string
  })

  if (isString(resp)) {
    ElMessage.error("服务器连接失败。")
    return to.path === '' || to.path === '/' 
      ? false
      : { path: '/' }
  }
  if ((resp as Response).code >= 400 && (resp as Response).code < 500) {
    ElMessage.error("登陆已过期，请重新登陆。")
    return { path: '/' }
  }
  if ((resp as Response).code >= 500 && (resp as Response).code < 600) {
    ElMessage.error("服务器内部错误。")
    return { path: '/' }
  }
  return true
})

export default router;
