import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/views/Login'
import Framework from '@/components/AppFramework'
import Dashboard from '@/views/Dashboard'
import RoleList from '@/views/role/RoleList'
import UserList from '@/views/user/UserList'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/app',
      component: Framework,
      children: [
        {
          path: 'dashboard',
          component: Dashboard
        },
        {
          path: 'admin/role/list',
          component: RoleList
        },
        {
          path: 'admin/user/list',
          component: UserList
        }
      ]
    }
  ]
})
