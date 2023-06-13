import profileView from "@/views/ProfileView.vue";
import adminView from "@/views/admin/AdminView.vue"
import homeView from "@/views/HomeView.vue";
import { createRouter, createWebHistory } from "vue-router";
import studentView from "@/views/student/StudentView.vue";
import teacherView from "@/views/teacher/TeacherView.vue";
import studentManagementView from "@/views/admin/management/StudentManagementView.vue";
import adminHomeView from "@/views/admin/AdminHomeView.vue";
import classManagementView from "@/views/admin/management/ClassManagementView.vue";
import majorManagementView from "@/views/admin/management/MajorManagementView.vue";
import courseManagementView from "@/views/admin/management/CourseManagementView.vue";
import userManagementView from "@/views/admin/management/UserManagementView.vue";
import teacherManagementView from "@/views/admin/management/TeacherManagementView.vue";

const routes = [
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
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: routes
})

export default router
  
