<template>
  <div id="movie">
    <div id="movieInfo">
      <div id="img">
        <img :src="baseURL + 'actors/images/' + actor.actImage" alt="" />
      </div>
      <div id="info">
        <h1>{{ actor.actFirstName }} {{ actor.actLastName }}</h1>
        <p>{{ actor.actBiography}}</p>
      </div>
    </div>
    <h3>Movies</h3>
    <div id="movies">
      <div class="movies" v-for="movie in movies" :key="movie.movId">
        <span>{{ movie.movName }}</span>
        <router-link v-bind:to="'/movie/' + movie.movId">
          <img
            :src="baseURL + 'movies/images/' + movie.movImage"
            alt=""
            class="movieImg"
          />
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      baseURL: "http://localhost:8081/",
      movies: [],
      actor: [],
    };
  },
  methods: {
    getActor() {
      axios
        .get(this.baseURL + "actors/" + this.$route.params.id)
        .then((res) => {
          this.actor = res.data;
        });
    },
    getMovies() {
      axios
        .get(this.baseURL + "actors/" + this.$route.params.id + "/movies")
        .then((res) => {
          this.movies = res.data;
        });
    },
  },
  mounted() {
    this.getMovies();
    this.getActor();
  },
};
</script>

<style scoped>
#movie {
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 900px;
}
#movieInfo {
  display: flex;
  flex-direction: row;
  justify-content: center;
}
#info {
  width: 400px;
  margin-left: 100px;
}
img {
  width: 250px;
  height: 350px;
}
#movies {
  display: flex;
  flex-direction: row;
  justify-content: center;
}
.movies {
  position: relative;
  width: 180px;
  height: 230px;
  border: 1px solid rgb(198, 198, 206);
  margin: 5px;
  border-radius: 5px;
}
.movieImg {
  width: 180px;
  height: 230px;
}
.movies:hover {
  border: 5px solid rgb(106, 168, 219);
}
span {
  padding: 10px;
  font-size: 17px;
  position: absolute;
  width: 160px;
  background-color: rgba(0, 0, 0, 0.6);
  color: #fff;
  bottom: 0;
}
</style>