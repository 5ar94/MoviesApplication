<template>
  <div id="app">
    <div class="header">
      <div id="title">
        <img id="hollywood" src="@/assets/hollywood.jpg" alt="">
      </div>
      <div id="nav">
        <div class="navDiv"><router-link to="/">Movies</router-link></div>
        <div class="navDiv">
          <router-link to="/AddMovie">Add Movie</router-link>
        </div>
        <div class="navDiv"><router-link to="/Actors">Actors</router-link></div>
        <div class="navDiv">
          <router-link to="/AddActor">Add Actor</router-link>
        </div>
        <div class="navDiv"><router-link to="/prijava">Login</router-link></div>
      </div>
    </div>
    <router-view />
  </div>
</template>
<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#nav {
  color: white;
  display: flex;
  flex-direction: row;
  justify-content: center;
  height: 80px;
}
#nav a {
  font-weight: bold;
  color: #2c3e50;
  text-decoration: none;
}
#nav a:hover {
  color: rgb(106, 168, 219);
}
.header {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  width: 100%;
  height: 80px;
  background: rgb(255, 255, 255);
  border-bottom: 2px solid rgb(216, 212, 212);
  margin-bottom: 30px;
}
#title {
  font-size: 70px;
  text-align: center;
}
.navDiv {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0;
  padding-right: 10px;
  padding-left: 10px;
  transition: 0.7s background ease;
}
.navDiv:hover{
  border-bottom: 3px solid rgb(106, 168, 219);
}
#hollywood{
  width: 400px;
  height: 80px;
}
</style>
