<template>
  <div class="container">
    <h2>Informações do Imóvel</h2>
    <!-- Renderiza a propriedade se ela estiver carregada -->
    <div v-if="property" class="property-card">
      <div class="carousel">
        <button class="prev-button" @click="prevImage">&#10094;</button>
        <img
          :src="getImageUrl(property.images[currentImageIndex])"
          :alt="`Imagem ${currentImageIndex + 1}`"
          class="carousel-image"
          width="800"  
          height="800"
        />
        <button class="next-button" @click="nextImage">&#10095;</button>
      </div>
      <div class="property-header">
        <h3>{{ property.propertyType }}</h3>
      </div>
      <div class="property-details">
        <p><strong>Endereço:</strong> {{ property.address.street }}, {{ property.address.number }}, {{ property.address.neighborhood }}, {{ property.address.city }}/{{ property.address.state }} - {{ property.address.cep }}</p>
        <p><strong>Valor do Aluguel:</strong> R$ {{ property.rentValue.toFixed(2) }}</p>
        <p><strong>Valor do Condomínio:</strong> {{ property.condominiumValue ? property.condominiumValue.toFixed(2) : 'N/A' }}</p>
        <p><strong>Descrição:</strong> {{ property.description }}</p>
        <p><strong>Portaria 24h:</strong> {{ property.concierge24h ? 'Sim' : 'N/A' }}</p>
        <p><strong>Andar:</strong> {{ property.floor ? property.floor : 'N/A' }}</p>
        <p><strong>Área:</strong> {{ property.area }} m²</p>
        <p><strong>Quartos:</strong> {{ property.rooms }}</p>
        <p><strong>Suítes:</strong> {{ property.suits }}</p>
        <p><strong>Salas de Estar:</strong> {{ property.livingRoom }}</p>
        <p><strong>Salas de Jantar:</strong> {{ property.diningRoom ? property.diningRoom : 'N/A' }}</p>
        <p><strong>Vagas de Garagem:</strong> {{ property.vacanciesGarage }}</p>
      </div>

      <!-- Modal de transferência -->
      <div class="modal" tabindex="-1" role="dialog" ref="scheduleModel">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Transferências</h5>
              <button
                type="button"
                class="close"
                data-dismiss="modal"
                aria-label="Close"
                @click="closeScheduleModal"
              >
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
              <ul v-if="schedule && schedule.availableTimeSlots && schedule.availableTimeSlots.length > 0">
                <li v-for="(slot, index) in schedule.availableTimeSlots" :key="index">
                  <button
                    :class="{ unavailable: !slot.available, active: activeSlot === index }"
                    @click="selectSlot(slot, index)"
                    :disabled="!slot.available"
                  >
                    {{ slot.startTime }} - {{ slot.endTime }}
                  </button>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <button
        @click="openScheduleModal"
        class="btn btn-primary float-end"
      >
        Agendar um horário
      </button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "PropertyView",
  data() {
    return {
      property: null,
      schedule: null,
      currentImageIndex: 0,
      activeSlot: null, // Adiciona uma propriedade para rastrear o botão ativo
    };
  },
  mounted() {
    const propertyId = this.$route.params.id;
    this.fetchProperty(propertyId);
    this.fetchSchedule(propertyId);
  },
  methods: {
    async fetchProperty(id) {
      try {
        const response = await axios.get(`http://localhost:8080/properties/${id}`);
        this.property = response.data; 
      } catch (error) {
        console.error('Erro ao buscar os dados do imóvel:', error);
      }
    },
    selectSlot(slot, index) {
      if (!slot.available) {
        this.modalData = slot;
        this.showModal = true;
      } else {
        this.activeSlot = index; // Atualiza o índice do botão ativo
      }
    },
    async fetchSchedule(id) {
      try {
        const response = await axios.get(`http://localhost:8080/calendar/propertyId/${id}`);
        this.schedule = response.data; 
        console.log(this.schedule)
      } catch (error) {
        console.error('Erro ao buscar os dados do imóvel:', error);
      }
    },
    getImageUrl(image) {
      if (image && image.imageData) {
        return `data:${image.imageType};base64,${image.imageData}`;
      }
      return '';
    },
    nextImage() {
      if (this.property && this.property.images) {
        this.currentImageIndex = (this.currentImageIndex + 1) % this.property.images.length;
      }
    },
    prevImage() {
      if (this.property && this.property.images) {
        this.currentImageIndex =
          (this.currentImageIndex - 1 + this.property.images.length) % this.property.images.length;
      }
    },
    closeScheduleModal() {
      this.$refs.scheduleModel.style.display = "none";
    },
    openScheduleModal() {
      this.$refs.scheduleModel.style.display = "block";
    }
  },
};
</script>

<style scoped>
/* Estilos da página (conforme fornecido anteriormente) */
.container {
  padding: 20px;
  max-width: 1200px;
  margin: auto;
}
button {
  padding: 10px 20px;
  border: none;
  cursor: pointer;
  margin-right: 10px;
}

button.unavailable {
  background-color: red;
  color: white;
}
button:disabled {
  cursor: not-allowed;
}
button.active {
  background-color: rgb(81, 81, 255);
  color: white;
}
ul {
  list-style-type: none;
  padding: 0;
}

li {
  margin-bottom: 10px;
}
/* Resto do estilo */
</style>
