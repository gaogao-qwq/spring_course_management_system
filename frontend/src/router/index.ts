import profileView from "@/views/ProfileView.vue";
import adminView from "@/views/admin/AdminView.vue"
import HomeView from "@/views/HomeView.vue";
import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/profile",
      name: "profile",
      component: profileView,
    },
    {
      path: "/admin",
      name: "admin",
      component: adminView,
    }
  ],
});

export default router;
