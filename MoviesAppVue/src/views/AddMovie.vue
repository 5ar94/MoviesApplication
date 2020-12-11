<template>
  <div id="newMovie">
    <h1>Add a new movie</h1>
    <label for="">Movie title</label>
    <input type="text" v-model="name" />
    <label for="">Description</label>
    <textarea id="descTxt" cols="30" rows="10" v-model="description"></textarea>
    <div>
      <label for="">Movie category</label>
      <select id="selectCategory" name="selectCategory" v-model="category">
        <option value="Action">Action</option>
        <option value="Comedy">Comedy</option>
        <option value="Drama">Drama</option>
        <option value="Crime">Crime</option>
        <option value="Horror">Horror</option>
        <option value="Trailer">Trailer</option>
      </select>
    </div>

    <div id="multiSelect">
      <multiselect
        v-model="value"
        :options="actors"
        placeholder="Choose actors..."
        track-by="actId"
        :custom-label="fullName"
        label="actFirstName"
        :multiple="true"
      ></multiselect>
      {{value}} ovo je izabrano
    </div>
    <label for="file">Choose image</label>
    <input type="file" id="file" name="file" @change="addImage" />
    <div v-if="url">
      <img id="preview" :src="url" />
    </div>
    <button id="addBtn" @click="addMovie">Add movie</button>
  </div>
</template>

<script>
import Multiselect from "vue-multiselect";
import axios from "axios";
export default {
  name: "AddMovie",
  data() {
    return {
      name: "",
      description: "",
      image: "",
      category: "Action",
      formData: new FormData(),
      url: null,
      value: [],
      actors: [],
    };
  },
  methods: {
    getActors() {
      axios.get("http://localhost:8081/actors").then((res) => {
        console.log(res), (this.actors = res.data);
      });
    },

    addImage(e) {
      this.image = e.target.files[0];
      this.formData.append("image", e.target.files[0]);
      this.url = URL.createObjectURL(this.image);
    },
    addMovie() {
      const data = {
        movName: this.name,
        movDescription: this.description,
        movImage: this.name.replaceAll(" ", "").toLowerCase() + this.image.name,
        movCategory: this.category,
      };
      this.formData.append("json", JSON.stringify(data));
      this.formData.append("array", this.indexActor);
      axios
        .post("http://localhost:8081/movies", this.formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((res) => {
          console.log(res);
          alert("Successfully added a movie");
          this.$router.push("/");
        });
    },
    fullName ({ actFirstName, actLastName }) {
      return `${actFirstName} ${actLastName}`
    }
  },
  components: { Multiselect },
  computed: {
        indexActor() {
            var array = [];
            for (var i = 0; i < this.value.length; i++) {
                array[i] = this.value[i].actId;
            }
            return array;
        }
    },
  mounted() {
    this.getActors();
  },
};
</script>
<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>
<style scoped>
#newMovie {
  display: flex;
  flex-direction: column;
  width: 500px;
  padding: 40px;
  box-sizing: border-box;
  border: 1px solid rgba(0, 0, 0, 0.1);
  background-color: #f4f4f4;
  border-radius: 3px;
  margin: 20px auto;
}
input {
  width: 300px;
  margin: 10px auto 10px;
  height: 30px;
  font-size: 18px;
}
#file {
  margin: 10px auto;
  width: 250px;
  content: "Select some files";
  display: inline-block;
}
textarea {
  max-width: 300px;
  margin: 10px auto;
}
#addBtn {
  margin: 10px auto;
  outline: none;
  width: 130px;
  height: 50px;
  border-radius: 20px;
  color: white;
  background: rgb(106, 168, 219);
}
#selectCategory {
  margin: 10px 10px;
  width: 100px;
}
#preview {
  margin: 10px auto;
  width: 100px;
  height: 150px;
}
#multiSelect {
  width: 300px;
  margin: 10px auto 20px;
}
</style>

