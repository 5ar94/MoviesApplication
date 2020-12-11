import Vue from 'vue'
import VueRouter from 'vue-router'
import Movie from '../views/Movie.vue'
import Actor from '../views/Actor.vue'
import AddMovie from '../views/AddMovie.vue'
import EditMovie from '../views/EditMovie.vue'
import AddActor from '../views/AddActor.vue'
import EditActor from '../views/EditActor.vue'
import Prijava from '../views/Prijava.vue'
import NotFound from '../views/NotFound.vue'
import MovieDetails from '../components/MovieDetails.vue'
import ActorDetails from '../components/ActorDetails.vue'
import Registracija from '../views/Registracija.vue'
//import store from '../store'


Vue.use(VueRouter)


const routes = [
  {
    path:'*',
    name: 'NotFound',
    component: NotFound
  },
  {
    path: '/',
    name: 'Movie',
    component: Movie
  },
  {
    path: '/Actors',
    name: 'Actor',
    component: Actor
  },
  {
    path: '/AddMovie',
    name: 'AddMovie',
    component: AddMovie,
    /*beforeEnter:(to,from,next)=>{
      if(store.state.isLoggedIn){
      next()
      } else{
        next('/prijava')
      }
      }*/
      
  },
  {
    path: '/EditMovie/:id',
    name: 'EditMovie',
    component: EditMovie,
  },
  {
    path: '/AddActor',
    name: 'AddActor',
    component: AddActor, 
  },
  {
    path: '/EditActor/:id',
    name: 'EditActor',
    component: EditActor,
  },
  {
    path: '/movie/:id',
    name: 'MovieDetails',
    component: MovieDetails,
  },
  {
    path: '/actor/:id',
    name: 'ActorDetails',
    component: ActorDetails 
  },
  {
    path: '/prijava',
    name: 'Prijava',
    component: Prijava
  },
  {
    path: '/registracija',
    name: 'Registracija',
    component: Registracija
  },
  
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
