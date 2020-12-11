<template>
  <div id="movie">
    <div id="movieInfo">
      <div id="img">
        <img :src="baseURL + 'movies/images/' + movie.movImage" alt="" />
      </div>
      <div id="info">
        <h1>{{ movie.movName }}</h1>
        <p><b>Genres:</b> {{ movie.movCategory }}</p>
        <p>{{ movie.movDescription }}</p>
      </div>
    </div>
    <h3>Main actors</h3>
    <div id="actors">
      <div class="actors" v-for="actor in actors" :key="actor.actId">
        <span>{{ actor.actFirstName }} {{ actor.actLastName }}</span>
        <router-link v-bind:to="'/actor/' + actor.actId">
          <img
            :src="baseURL + 'actors/images/' + actor.actImage"
            alt=""
            class="actorImg"
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
      movie: [],
      actors: [], 
    };
  },
  methods: {
    getMovie() {
      axios
        .get(this.baseURL + "movies/" + this.$route.params.id)
        .then((res) => {
          this.movie = res.data;
        });
    },
    getActors() {
      axios
        .get(this.baseURL + "movies/" + this.$route.params.id + "/actors")
        .then((res) => {
          this.actors = res.data;
        });
    },
  },
  mounted() {
    this.getMovie();
    this.getActors();
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
#actors {
  display: flex;
  flex-direction: row;
  justify-content: center;
}
.actors {
  position: relative;
  width: 180px;
  height: 230px;
  border: 1px solid rgb(198, 198, 206);
  margin: 5px;
  border-radius: 5px;
}
.actorImg {
  width: 180px;
  height: 230px;
}
.actors:hover {
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