import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)


export default new Vuex.Store({
  state: {
    //movies: [],
    isLoggedIn: false,
    users: [
      { username: 'jumbo', password: 123 },
      { username: 'pera', password: 234 },
      { username: 'mimi', password: 345 }
    ]
  },
  mutations: {
    CHANGE_LOGIN(state, payload) {
      state.isLoggedIn = payload;
    },
    CHANGE_MOVIES(state,payload){
      state.movies = payload;
    },
    CHANGE_USERS(state,payload){
      state.users.push(payload);
    }
  },
  actions: {
    changeLogin(store, payload) {
      store.commit('CHANGE_LOGIN', payload);
    },
    changeMovies(store, payload){
      store.commit('CHANGE_MOVIES',payload);
    },
    changeUsers(store, payload){
      store.commit('CHANGE_USERS',payload);
    }

    
  },
  // getters: {
  //   isLoggedIn: state => state.isLoggedIn
  //   }
    
})
