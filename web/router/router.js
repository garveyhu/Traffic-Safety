import Vue from 'vue';
import VueRouter from 'vue-router';
import Map from '@/pages/tabbar/map/map.nvue';

Vue.use(VueRouter);

const routes = [
  {
    path: '/map/:taskInfo',
    name: 'map',
    component: Map,
  },
];

const router = new VueRouter({
  routes,
});

export default router;
