<template>
  <div class="home">
    <div class="search">
      <select id="selectCategory" name="selectCategory" v-model="cate">
        <option value="">All movies</option>
        <option value="Action">Action</option>
        <option value="Comedy">Comedy</option>
        <option value="Drama">Drama</option>
        <option value="Crime">Crime</option>
        <option value="Horror">Horror</option>
        <option value="Trailer">Trailer</option>
      </select>
      <input type="text" placeholder="Search movie..." id="search" v-model="search">
      <button id="button"><i class="fa fa-search" style="font-size:14px"></i></button>
    </div>
  <div class="movies" v-for="movie in filteredCategory" :key="movie.movId">
    <span>{{movie.movName}}</span>
    <span @click="deleteMovie(movie.movId, movie.movName)" class="delete">Delete</span>
    <router-link v-bind:to="'/EditMovie/'+movie.movId">
    <span class="edit">Edit</span>
    </router-link>
    <router-link v-bind:to="'/movie/' + movie.movId">
    <img :src="baseURL +'movies/images/'+ movie.movImage" alt="">
    </router-link>
  </div>
  </div>
</template>

<script>
import axios from 'axios'
//import { mapState, mapActions } from "vuex"
export default {
  name: 'Movie',
  data(){
    return{
      movies:[],
      baseURL: 'http://localhost:8081/',
      search:"",
      cate:""
    }
  },
  methods:{
    //...mapActions(['changeMovies']),
    deleteMovie(id, movieName){
      if (confirm("Do you want to delete movie "+movieName+"?")) {
        axios.delete(this.baseURL+'movies/'+id);
      }
      this.$router.go()
    },
    getMovies(){
      axios.get(this.baseURL+'movies').then(res=>{
        console.log(res)
        this.movies = res.data;
        //this.changeMovies(res.data.data);
      });
    }
  },
  mounted(){
    this.getMovies();
  },
  computed: {
    //...mapState(['movies']),
    filteredCategory: function() {
        return this.movies.filter((movie) => {
          return movie.movCategory.match(this.cate)&&movie.movName.toLowerCase().match(this.search.toLowerCase())
        });
      }
  },

}
</script>

<style scoped>
.home{
  box-sizing: border-box;
  margin: 0 auto;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  width: 1300px;
}
.movies{
  position: relative;
  width: 240px;
  height: 330px;
  border: 1px solid rgb(198, 198, 206);
  margin: 5px;
  border-radius: 5px;
}
.movies:hover{
  border: 5px solid rgb(106, 168, 219);
  
}
img{
  width: 240px;
  height: 330px;
}
span {
    padding: 10px;
    font-size: 19px;
    position: absolute;
    width: 220px;
    background-color: rgba(0,0,0,.6);
    color: #fff;
    bottom: 0;
}
.delete{
  padding: 10px;
    font-size: 19px;
    position: absolute;
    width: 50px;
    height: 20px;
    background-color: rgba(0,0,0,.6);
    color: #fff;
    top: 0;
    right: 0;
}
.edit{
  padding: 10px;
    font-size: 19px;
    position: absolute;
    width: 50px;
    height: 20px;
    background-color: rgba(0,0,0,.6);
    color: #fff;
    top: 0;
    left: 0;
}
.search{
  width: 1200px;
  margin-bottom: 20px;
}
#search{
  width: 400px;
  height: 35px;
  border: 2px solid rgb(114, 112, 230);
  border-right: 0px solid;
  border-left: 0px solid;
  outline: none;
  padding-left: 10px;
}
#selectCategory{
  height: 41px;
  border: 1px solid rgb(114, 112, 230);
  outline: none;
  padding-left: 10px;
  border-top-left-radius: 15px;
  border-bottom-left-radius: 15px;
  background: rgb(114, 112, 230);
  color:white;
}
#button{
  background: white;
  height: 41px;
  border: 2px solid rgb(114, 112, 230);
  outline: none;
  border-left: 0px solid;
  border-top-right-radius: 15px;
  border-bottom-right-radius: 15px;
}
#button:hover{
  color: rgb(106, 168, 219);
}
@import url("https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css");
</style>
