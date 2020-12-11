<template>
  <div v-if="!this.isLoggedIn" class="login">
    <div class="login-triangle"></div>

    <h2 class="login-header">Log in</h2>

    <div class="login-container">
      <p><input type="text" placeholder="Username" v-model="newUsername" v-bind:class="{'crveno': this.obaveznoIme}"/></p>
      <p>
        <input type="password" placeholder="Password" v-model="newPassword" v-bind:class="{'crveno': this.obaveznoSifra}"/>
      </p>
      <p id="greska" v-show="this.greska">Uneli ste pogresno korisnicko ime ili lozinku</p>
      <p><input type="submit" @click="login" value="Log in"/></p>
      <p>Ukoliko nemaš nalog <router-link to="/registracija">Registruj se</router-link></p>
    </div>
  </div>
  <div id="dobrodosli" v-else> 
   <div id="header"> Dobro došao {{this.users.username}}!<button id="odjava" @click="logout">Odjavi se</button></div>
  <div id="pocetna">
  <div class="divPocetna">
    <p>Pogledaj listu najboljih filmova svih vremena i ukratko se upoznaj sa njihovom radnjom. Ovo ne smeš da propustiš!</p>
    <button class="prebaci" @click="()=>this.$router.push('/')">Filmovi</button>
  </div>
  <div class="divPocetna">
     <p>Preporuči svoj omiljeni film drugima! Potrebno je da uneseš naziv filma, kratak opis, kao i sliku :)</p>
    <button class="prebaci" @click="()=>this.$router.push('/dodajFilm')">Dodaj film</button>
  </div>
  </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
export default {
  name: "Prijava",
  data() {
    return {
      newUsername: "",
      newPassword: "",
      obaveznoIme: false,
      obaveznoSifra: false,
      greska: false,
      ime:""
    };
  },
  methods: {
    ...mapActions(["changeLogin"]),
    login() {
      if(this.newUsername===""){
        this.obaveznoIme=true;
      } else{
        this.obaveznoIme=false;
      }
      if(this.newPassword==""){
        this.obaveznoSifra=true;
      } else{
        this.obaveznoSifra=false;
      }
      if (this.newUsername!=""&&this.newPassword!="") {
        for (let i = 0; i < this.users.length; i++) {
        if (
          this.newUsername === this.users[i].username &&
          this.newPassword == this.users[i].password
        ) {
          this.changeLogin(true);
          this.users.username=this.users[i].username;
          //this.$router.push('/');
          this.greska = false;
        }
      }
      if (!this.isLoggedIn) {
        this.greska = true;
      }
      }
    },
    logout(){
      this.changeLogin(false);
    }
  },
  computed: {
    ...mapState(["isLoggedIn", "users","users.username"]),
  },
};
</script>

<style scoped>
.login {
  width: 400px;
  margin: 16px auto;
  font-size: 16px;
}

.login-header,
.login p {
  margin-top: 0;
  margin-bottom: 0;
}

.login-triangle {
  width: 0;
  margin-right: auto;
  margin-left: auto;
  border: 12px solid transparent;
  border-bottom-color: rgb(102, 164, 214);
}

.login-header {
  background: rgb(102, 164, 214);
  padding: 20px;
  font-size: 1.4em;
  font-weight: normal;
  text-align: center;
  text-transform: uppercase;
  color: #fff;
  box-shadow: 4px 4px 6px #777;
}

.login-container {
  background: #ebebeb;
  padding: 12px;
  box-shadow: 4px 4px 6px #777;
}

.login p {
  padding: 12px;
}

.login input {
  box-sizing: border-box;
  display: block;
  width: 100%;
  border-width: 1px;
  border-style: solid;
  padding: 16px;
  outline: 0;
  font-family: inherit;
  font-size: 0.95em;
  background: #fff;
  color: #555;
}

.login input:focus {
  border-color: #888;
}
.login input[type="submit"] {
  background: rgb(102, 164, 214);
  border-color: transparent;
  color: #fff;
  cursor: pointer;
}
.login input:focus {
  border-color: #05a;
}
.crveno{
  border: 6px solid rgb(216, 61, 61);
}
#greska{
  margin: 0 auto;
  color: rgb(216, 61, 61);
}
#dobrodosli{
  font-size: 50px;
  margin-left: 0 auto
}
#odjava{
  width: 80px;
  height: 40px;
  position: relative;
  left: 20%;
  bottom: 17px;
  background:rgb(129, 183, 228);
  color: white;
  border: 0px solid;
  outline: none;
}
#odjava:hover, .prebaci:hover {
  border-radius: 10px;
  border: 2px solid gray;
}
#pocetna{
  display: flex;
  width: 50%;
  margin: 40px auto;
  font-size: 20px;

  justify-content: space-around;
  height: 250px;
}
.divPocetna{
  width: 200px;
}
.prebaci{
   width: 80px;
  height: 40px;
  background:rgb(129, 183, 228);
  color: white;
  border: 0px solid;
  outline: none;
}

</style>