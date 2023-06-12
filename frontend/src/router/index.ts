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
import type { User } from '@/internal/types';
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
  const resp = await UserInfoApi.get('/user/me')
    .then((r) => {
      return r.data.data as User
  })
    .catch((reason) => {
      return reason as string
  })
  if (isString(resp)) {
    ElMessage.error("登陆已过期，请重新登陆。")
    return '/'
  }
  return true
})

export default router;
