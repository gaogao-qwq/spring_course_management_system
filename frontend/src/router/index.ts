import profileView from "@/views/ProfileView.vue";
import adminView from "@/views/admin/AdminView.vue"
import HomeView from "@/views/HomeView.vue";
import { createRouter, createWebHistory } from "vue-router";
import StudentView from "@/views/student/StudentView.vue";
import TeacherView from "@/views/teacher/TeacherView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      component: HomeView,
    },
    {
      path: "/admin",
      component: adminView,
      children: [
        {
          path: "profile",
          component: profileView,
        }
      ]
    },
    {
      path: "/teacher",
      component: TeacherView,
      children: [
        {
          path: "profile",
          component: profileView,
        }
      ]
    },
    {
      path: "/student",
      component: StudentView,
      children: [
        {
          path: "profile",
          component: profileView,
        }
      ]
    }
  ],
});

router.beforeEach((to) => {
  
})

export default router;
