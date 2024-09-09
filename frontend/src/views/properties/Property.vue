<template>
  <div class="container">
    <h2>Informações do Imóvel</h2>
    <!-- Renderiza a propriedade se ela estiver carregada -->
    <div v-if="property" class="property-card">
      <div class="carousel">
        <img
        :src="getImageUrl(property.images[currentImageIndex])"
        :alt="`Imagem ${currentImageIndex + 1}`"
          class="carousel-image"
        />
        <button class="prev-button" @click="prevImage">&#10094;</button>
        <button class="next-button" @click="nextImage">&#10095;</button>
      </div>
      <div class="property-header">
        <h3>{{ property.propertyType }}</h3>
        <span class="rent-status">{{ property.isRented ? 'Alugado' : 'Disponível' }}</span>
      </div>
      <div class="property-details">
        <p><strong>Endereço:</strong> {{ property.address }}</p>
        <p><strong>Valor do Aluguel:</strong> R$ {{ property.rentValue.toFixed(2) }}</p>
        <p><strong>Descrição:</strong> {{ property.description }}</p>
        <p><strong>Área:</strong> {{ property.area }} m²</p>
        <p><strong>Quartos:</strong> {{ property.rooms }}</p>
        <p><strong>Suítes:</strong> {{ property.suits }}</p>
        <p><strong>Salas de Estar:</strong> {{ property.livingRoom }}</p>
        <p><strong>Vagas de Garagem:</strong> {{ property.vacanciesGarage }}</p>
        <p><strong>Armários Embutidos:</strong> {{ property.closets ? 'Sim' : 'Não' }}</p>
      </div>
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
      currentImageIndex: 0,
    };
  },
  mounted() {
    // Captura o ID da rota e chama a função para buscar os dados do imóvel
    const propertyId = this.$route.params.id;
    this.fetchProperty(propertyId);
  },
  methods: {
    // Faz a requisição para buscar os dados do imóvel pelo ID
    async fetchProperty(id) {
      try {
        // Substitua a URL pela sua API ou backend que retorna os dados do imóvel
        const response = await axios.get(`http://localhost:8080/properties/${id}`);
        this.property = response.data; 
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

/* Resto do estilo */
</style>
