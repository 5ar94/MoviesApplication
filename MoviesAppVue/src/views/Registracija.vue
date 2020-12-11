<template>
  <div class="registracija">
    <div class="registracija-triangle"></div>

    <h2 class="registracija-header">Sign up</h2>

    <div class="registracija-container">
      <p>
        <input
          type="text"
          placeholder="Username"
          v-model="newUsername"
          v-bind:class="{ crveno: this.obaveznoIme }"
        />
      </p>
      <p>
        <input
          type="password"
          placeholder="Password"
          v-model="newPassword"
          v-bind:class="{ crveno: this.obaveznoSifra }"
        />
      </p>
      <p class="greska" v-show="!this.sifra">
        ***Sifra mora da sadrži minimum 4 karaktera, od toga jedan ili više: broj,
        veliko slovo i malo slovo***
      </p>
      <p class="greska" v-show="this.greska">***Uneto korisničko ime je zauzeto***</p>
      <p><input type="submit" @click="registracija()" value="Sign up" /></p>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
export default {
  name: "Registracija",
  data() {
    return {
      newUsername: "",
      newPassword: "",
      obaveznoIme: false,
      obaveznoSifra: false,
      greska: false,
      sifra: true,
      pattern: new RegExp("(?=.*)(?=.*[a-z])(?=.*[A-Z]).{4,}"),
    };
  },
  methods: {
    ...mapActions(["changeUsers"]),
    registracija() {
      if (this.newPassword.trim() != "") {
        if (this.pattern.test(this.newPassword)) {
          console.log("dobra sifra");
          this.sifra = true;
        } else {
          this.sifra = false;
        }
      }
      if (this.newUsername == "") {
        this.obaveznoIme = true;
      } else {
        this.obaveznoIme = false;
      }
      if (this.newPassword == "") {
        this.obaveznoSifra = true;
      } else {
        this.obaveznoSifra = false;
      }
      if (this.newUsername != "" && this.newPassword != "") {
        for (let i = 0; i < this.users.length; i++) {
          if (this.newUsername === this.users[i].username) {
            this.greska = true;
            break;
          } else {
            this.greska = false;
          }
        }
      }
      if (
        !this.greska &&
        this.newUsername != "" &&
        this.newPassword != "" &&
        this.pattern.test(this.newPassword)
      ) {
        let newUser = {
          username: this.newUsername,
          password: this.newPassword,
        };
        this.changeUsers(newUser);
        alert("uspeno si se registrovao");
        this.$router.push("/prijava");
      }
    },
  },
  computed: {
    ...mapState(["isLoggedIn", "users", "users.username"]),
  },
};
</script>

<style scoped>
.registracija {
  width: 400px;
  margin: 16px auto;
  font-size: 16px;
}

.registracija-header,
.registracija p {
  margin-top: 0;
  margin-bottom: 0;
}

.registracija-triangle {
  width: 0;
  margin-right: auto;
  margin-left: auto;
  border: 12px solid transparent;
  border-bottom-color: rgb(184, 151, 223);
}

.registracija-header {
  background: rgb(184, 151, 223);
  padding: 20px;
  font-size: 1.4em;
  font-weight: normal;
  text-align: center;
  text-transform: uppercase;
  color: #fff;
  box-shadow: 4px 4px 6px #777;
}

.registracija-container {
  background: #ebebeb;
  padding: 12px;
  box-shadow: 4px 4px 6px #777;
}

.registracija p {
  padding: 12px;
}

.registracija input {
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

.registracija input:focus {
  border-color: #888;
}
.registracija input[type="submit"] {
  background: rgb(184, 151, 223);
  border-color: transparent;
  color: #fff;
  cursor: pointer;
}
.registracija input:focus {
  border-color: #05a;
}
.crveno {
  border: 6px solid rgb(216, 61, 61);
}
.greska {
  margin: 0 auto;
  color: rgb(216, 61, 61);
  font-size: 15px;
}
</style>