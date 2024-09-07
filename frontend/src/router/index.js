import { createRouter, createWebHistory } from 'vue-router'
import PropertiesView from '../views/properties/Properties.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: PropertiesView
    },
    {
      path: '/swagger',
      name: 'swagger',

      component: () => import('../views/Swagger.vue')
    },
    {
      path: '/properties',
      name: 'properties',
      component: PropertiesView
    }
  ]
})

export default router
