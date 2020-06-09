import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import home from '@/components/home'
import login from '@/components/login'
import attention from '@/components/attention'
import register from '@/components/Register'
import changepassword from '@/components/ChangePassword'
import search from '@/components/search'
import AttentionFri from '@/components/AttentionFri'
import FansFri from '@/components/FansFri'
import personal from '@/components/Personal'
import own from '@/components/Own'
import HotWei from '@/components/HotWei'
import WeiboIntroduce from '@/components/WeiboIntroduce'
Vue.use(Router)

export default new Router({
  mode : 'history',
  routes: [
    {
      path: '/HelloWorld',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/home',
      name: 'home',
      component: home,
    }
    ,
    {
      path: '/',
      name: 'login',
      component: login
    }
    ,
    {
      path: '/register',
      name: 'register',
      component: register
    }
    ,
    {
      path: '/changepassword',
      name: 'changepassword',
      component: changepassword
    }
    ,
    // {
    //   path: '/message',
    //   name: 'message',
    //   component: message
    // }
    // ,
    {
      path: '/attention',
      name: 'attention',
      component: attention
    }
    ,
    {
      path: '/search/:input',
      name: 'search',
      component: search
    }
    ,
    {
      path: '/AttentionFri',
      name: 'AttentionFri',
      component: AttentionFri
    },
    {
      path: '/FansFri',
      name: 'FansFri',
      component: FansFri
    },
    {
      path: '/personal/:id',
      name: 'personal',
      component: personal
    },
    {
      path: '/own/:id',
      name: 'own',
      component: own
    },
    {
      path: '/HotWei/:dat',
      name: 'HotWei',
      component: HotWei
    },
    {
      path: '/WeiboIntroduce',
      name: 'WeiboIntroduce',
      component: WeiboIntroduce
    }
  ]
})
