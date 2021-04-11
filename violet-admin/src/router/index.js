import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'Dashboard',
    hidden: true,
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index')
    }]
  },

  // 主页
  {
    path: '/details',
    component: Layout,
    redirect: '/details/table',
    name: '',
    meta: { title: '博客详情', icon: 'example' },
    children: [
      {
        path: 'table',
        name: '博客详情',
        component: () => import('@/views/details/table'),
        meta: { title: '博客详情', icon: 'details' }
      }
    ]
  },

  // 博客管理
  {
    path: '/blog',
    component: Layout,
    redirect: '/blog/table',
    name: '博客管理',
    meta: { title: '博客管理', icon: 'example' },
    children: [
      {
        path: 'table',
        name: '博客列表',
        component: () => import('@/views/blog/list'),
        meta: { title: '博客列表', icon: 'table' }
      }
    ]
  },

  // 用户管理
  {
    path: '/user',
    component: Layout,
    redirect: '/user/table',
    name: '用户管理',
    meta: { title: '用户管理', icon: 'user' },
    children: [
      {
        path: 'table',
        name: '用户列表',
        component: () => import('@/views/user/list'),
        meta: { title: '用户列表', icon: 'user' }
      }
    ]
  },

  // 分类管理
  {
    path: '/category',
    component: Layout,
    redirect: '/category/table',
    name: '分类管理',
    meta: {
      title: '分类管理',
      icon: 'category'
    },
    children: [{
      path: 'table',
      name: '分类列表',
      component: () => import('@/views/category/list'),
      meta: {
        title: '分类列表',
        icon: 'category1'
      }
    },
    {
      path: 'save',
      name: '添加分类',
      component: () => import('@/views/category/save'),
      meta: {
        title: '添加分类',
        icon: 'category2'
      }
    },
    {
      path: 'edit/:id', // 占位符  /:id   传参数  隐藏路由写法
      name: '修改分类',
      component: () => import('@/views/category/save'),
      meta: {
        title: '修改分类',
        icon: 'category2'
      },
      hidden: true
    }
    ]
  },

  // 标签管理
  {
    path: '/tag',
    component: Layout,
    redirect: '/tag/table',
    name: '标签管理',
    meta: {
      title: '标签管理',
      icon: 'tag'
    },
    children: [{
      path: 'table',
      name: '标签列表',
      component: () => import('@/views/tag/list'),
      meta: {
        title: '标签列表',
        icon: 'tag1'
      }
    },
    {
      path: 'save',
      name: '添加标签',
      component: () => import('@/views/tag/save'),
      meta: {
        title: '添加标签',
        icon: 'tag2'
      }
    },
    {
      path: 'edit/:id', // 占位符  /:id   传参数  隐藏路由写法
      name: '修改标签',
      component: () => import('@/views/tag/save'),
      meta: {
        title: '修改标签',
        icon: 'tag2'
      },
      hidden: true
    }
    ]
  },

  // 公告管理
  {
    path: '/say',
    component: Layout,
    redirect: '/say/table',
    name: '公告管理',
    meta: {
      title: '公告管理',
      icon: 'say'
    },
    children: [{
      path: 'table',
      name: '公告列表',
      component: () => import('@/views/say/list'),
      meta: {
        title: '公告列表',
        icon: 'say1'
      }
    },
    {
      path: 'save',
      name: '添加公告',
      component: () => import('@/views/say/save'),
      meta: {
        title: '添加公告',
        icon: 'say2'
      }
    },
    {
      path: 'edit/:id', // 占位符  /:id   传参数  隐藏路由写法
      name: '修改公告',
      component: () => import('@/views/say/save'),
      meta: {
        title: '修改公告',
        icon: 'say2'
      },
      hidden: true
    }
    ]
  },

  // 评论管理
  {
    path: '/comment',
    component: Layout,
    redirect: '/comment/table',
    name: '评论管理',
    meta: { title: '评论管理', icon: 'example' },
    children: [
      {
        path: 'table',
        name: '评论列表',
        component: () => import('@/views/comment/list'),
        meta: { title: '评论列表', icon: 'comment' }
      }
    ]
  },

  // 友链管理
  {
    path: '/friend',
    component: Layout,
    redirect: '/friend/table',
    name: '友链管理',
    meta: {
      title: '友链管理',
      icon: 'friend'
    },
    children: [{
      path: 'table',
      name: '友链列表',
      component: () => import('@/views/friend/list'),
      meta: {
        title: '友链列表',
        icon: 'friend1'
      }
    },
    {
      path: 'save',
      name: '添加友链',
      component: () => import('@/views/friend/save'),
      meta: {
        title: '添加友链',
        icon: 'friend2'
      }
    },
    {
      path: 'edit/:id', // 占位符  /:id   传参数  隐藏路由写法
      name: '修改友链',
      component: () => import('@/views/friend/save'),
      meta: {
        title: '修改友链',
        icon: 'friend2'
      },
      hidden: true
    }
    ]
  },

  {
    path: '/sta',
    component: Layout,
    redirect: '/sta/table',
    name: '统计分析',
    meta: {
      title: '统计分析',
      icon: 'example'
    },
    children: [{
      path: 'create',
      name: '生成数据',
      component: () => import('@/views/sta/create'),
      meta: {
        title: '生成数据',
        icon: 'table'
      }
    },
    {
      path: 'show',
      name: '图表显示',
      component: () => import('@/views/sta/show'),
      meta: {
        title: '图表显示',
        icon: 'tree'
      }
    },
    ]
  },

  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
