<template>
  <div id="newActor">
    <h1>Edit Actor</h1>
    <label for="">First name</label>
    <input type="text" v-model="firstName" />
    <label for="">Last name</label>
    <input type="text" v-model="lastName" />
    <label for="">Biography</label>
    <textarea id="descTxt" cols="30" rows="10" v-model="biography"></textarea>
    <div>
    </div>
    <label for="file">Choose image</label>
    <input type="file" id="file" name="file" @change="addImage" />
    <div  v-if="url">
      <img id="preview" :src="url" />
    </div>
    <button id="addBtn" @click="editActor">Edit actor</button>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "EditActor",
  data() {
    return {
      id: "",
      firstName: "",
      lastName: "",
      biography: "",
      image: "",
      imageName: "",
      formData: new FormData(),
      url: null,
    };
  },
  methods: {
    addImage(e) {
      this.image = e.target.files[0];
      this.formData.append("image", e.target.files[0]);
      this.url = URL.createObjectURL(this.image);
    },
    getActor() {
      axios.get("http://localhost:8081/actors/"+this.$route.params.id).then((res)=>{
        this.id = res.data.actId;
        this.firstName = res.data.actFirstName;
        this.lastName = res.data.actLastName;
        this.biography = res.data.actBiography;
        this.imageName = res.data.actImage;
        axios.get("http://localhost:8081/actors/images/"+res.data.actImage).then((res)=>{
          this.url = res.config.url;
        })
      })
    },
    editActor() {
      if(this.image.name!==undefined){
      const data = {
        actId: this.id,
        actFirstName: this.firstName,
        actLastName: this.lastName,
        actBiography: this.biography,
        actImage: this.firstName.replace(" ", "").toLowerCase() + this.image.name,
      };
      this.formData.append("actor", JSON.stringify(data));
      }else{
        const data = {
        actId: this.id,
        actFirstName: this.firstName,
        actLastName: this.lastName,
        actBiography: this.biography,
        actImage: this.imageName
      };
      this.formData.append("actor", JSON.stringify(data));
      }
      axios
        .put("http://localhost:8081/actors", this.formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((res) => {
          console.log(res);
          alert("Successfully modified actor");
          this.$router.push("/Actors");
        });
    },
  },
  mounted() {
    this.getActor();
  }
};
</script>

<style scoped>
#newActor {
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
   content: 'Select some files';
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
</style>

