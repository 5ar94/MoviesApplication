<template>
  <div class="home">
    <div class="search">
      <input type="text" placeholder="Search actor..." id="search" v-model="search">
      <button id="button"><i class="fa fa-search" style="font-size:14px"></i></button>
    </div>
  <div class="actors" v-for="actor in filtered" :key="actor.actId">
    <span>{{actor.actFirstName}} {{actor.actLastName}}</span>
    <span @click="deleteActor(actor.actId, actor.actFirstName, actor.actLastName)" class="delete">Delete</span>
    <router-link v-bind:to="'/EditActor/'+actor.actId">
    <span class="edit">Edit</span>
    </router-link>
    <router-link v-bind:to="'/actor/' + actor.actId">
    <img :src="baseURL +'actors/images/'+ actor.actImage" alt="">
    </router-link>
  </div>
  </div>
</template>

<script>
import axios from 'axios'
//import { mapState, mapActions } from "vuex"
export default {
  name: 'Actor',
  data(){
    return{
      actors:[],
      baseURL: 'http://localhost:8081/',
      search:"",
    }
  },
  methods:{
    //...mapActions(['changeMovies']),
    deleteActor(id, actorFirstName, actorLastName){
      if (confirm("Do you want to delete the actor "+actorFirstName+" "+actorLastName+"?")) {
        axios.delete(this.baseURL+'actors/'+id);
      }
      this.$router.go()
    },
    getActors(){
      axios.get('http://localhost:8081/actors').then(res=>{
        console.log(res)
        this.actors = res.data;
        //this.changeMovies(res.data.data);
      });
    }
  },
  mounted(){
    this.getActors();
  },
  computed: {
    //...mapState(['movies']),
    filtered: function () {
      return this.actors.filter((actor)=>{
        return actor.actFirstName.toLowerCase().match(this.search.toLowerCase())
      })
    },
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
.actors{
  position: relative;
  width: 240px;
  height: 330px;
  border: 1px solid rgb(198, 198, 206);
  margin: 5px;
  border-radius: 5px;
}
.actors:hover{
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
  border: 1px solid rgb(174, 182, 178);
  border-right: 0px solid;
  border-top-left-radius: 15px;
  border-bottom-left-radius: 15px;
  outline: none;
  padding-left: 20px;
}
#button{
  background: white;
  height: 39px;
  border: 1px solid rgb(174, 182, 178);
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
