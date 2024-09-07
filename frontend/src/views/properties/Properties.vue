<template>
  <div class="container">
    <div class="card" style="margin-top: 3.5%">
      <div class="card-header">
        <h4>
          Propriedades
          <button @click="openModal" class="btn btn-primary float-end">
            Cadastrar Imóvel
          </button>
        </h4>
      </div>
      <div class="card-body">
        <table class="table table-bordered">
          <thead align="center">
            <tr>
              <th class="tituloTable">Tipo</th>
              <th class="tituloTable">Descrição</th>
              <th class="tituloTable">Endereço</th>
              <th class="tituloTable">Valor</th>
              <th class="tituloTable">Informações extras</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(property, index) in properties" :key="index">
              <td>{{ property.propertyType }}</td>
              <td>{{ property.description }}</td>
              <td>{{ property.address.street }}</td>
              <td>R$ {{ property.rentValue }}</td>
              <td align="center">
                <button
                  type="button"
                  class="btn btn-primary"
                  @click="openTransfersModel(property.transacoes)"
                  style="
                    background-color: rgb(255, 255, 255);
                    border-color: rgb(141, 159, 209);
                  "
                >
                  📝
                </button>
              </td>
            </tr>
          </tbody>
        </table>

        <!-- Modal de exemplo -->
        <div v-if="isModalOpen" class="modal-overlay">
          <div class="modal">
            <h2>Formulário</h2>
            <form @submit.prevent="handleSubmit">
              <label for="name">Nome:</label>
              <input type="text" v-model="formData.name" />

              <label for="email">Email:</label>
              <input type="email" v-model="formData.email" />

              <label for="message">Mensagem:</label>
              <textarea v-model="formData.message"></textarea>

              <button type="submit">Enviar</button>
              <button type="button" @click="closeModal">Fechar</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';
import axios from "axios";

export default {
  name: "properties",
  setup() {
    const isModalOpen = ref(false);
    const formData = ref({
      name: '',
      email: '',
      message: '',
    });

    const openModal = () => {
      isModalOpen.value = true;
    };

    const closeModal = () => {
      isModalOpen.value = false;
    };

    const handleSubmit = () => {
      console.log('Dados do formulário:', formData.value);
      closeModal();
    };

    return {
      isModalOpen,
      formData,
      openModal,
      closeModal,
      handleSubmit
    };
  },
  data() {
    return {
      properties: [],
      modalData: [],
    };
  },
  mounted() {
    this.getAllProperties();
  },
  methods: {
    getAllProperties() {
      axios
        .get("http://localhost:8080/properties")
        .then((res) => {
          this.properties = res.data;
        })
        .catch((err) => {
          console.error("Error fetching properties", err);
        });
    },
    openTransfersModel(transacoes) {
      this.modalData = transacoes;
    },
  },
};
</script>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000; /* Garanta que o z-index seja alto o suficiente */
}

.modal {
  background: white;
  padding: 20px;
  border-radius: 8px;
  width: 400px;
  max-width: 80%;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* Adiciona sombra para destacar o modal */
  z-index: 1100; /* Z-index maior que o overlay */
}
</style>
