<template>
  <div class="container">
    <h2>Informações do Imóvel</h2>
    <div class="property-card" v-for="(property, index) in properties" :key="index">
      <div class="carousel">
        <img :src="property.images[currentImageIndex[index]]" :alt="`Imagem ${currentImageIndex[index] + 1}`" class="carousel-image" />
        <button class="prev-button" @click="prevImage(index)">&#10094;</button>
        <button class="next-button" @click="nextImage(index)">&#10095;</button>
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
export default {
  name: "ImovelCard",
  data() {
    return {
      currentImageIndex: [],
      properties: [
        {
          propertyType: "Apartamento",
          address: "Rua das Flores, 123, São Paulo - SP",
          isRented: false,
          rentValue: 2500.0,
          description: "Apartamento bem iluminado com varanda.",
          area: 85.0,
          rooms: 3,
          suits: 1,
          livingRoom: 1,
          vacanciesGarage: 2,
          closets: true,
          images: [
            "https://via.placeholder.com/400x200?text=Imagem+1",
            "https://via.placeholder.com/400x200?text=Imagem+2",
            "https://via.placeholder.com/400x200?text=Imagem+3",
          ],
        },
        // Adicione mais imóveis conforme necessário
      ],
    };
  },
  mounted() {
    // Inicializa o índice de imagens para cada propriedade
    this.currentImageIndex = this.properties.map(() => 0);
  },
  methods: {
    nextImage(index) {
      const property = this.properties[index];
      this.currentImageIndex[index] =
        (this.currentImageIndex[index] + 1) % property.images.length;
    },
    prevImage(index) {
      const property = this.properties[index];
      this.currentImageIndex[index] =
        (this.currentImageIndex[index] - 1 + property.images.length) %
        property.images.length;
    },
  },
};
</script>

<style scoped>
.container {
  padding: 20px;
  max-width: 1200px;
  margin: auto;
}

h2 {
  text-align: center;
  margin-bottom: 20px;
  color: #333;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.property-card {
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
  padding: 20px;
  transition: transform 0.3s;
}

.property-card:hover {
  transform: scale(1.02);
}

.carousel {
  position: relative;
  max-width: 100%;
  margin-bottom: 15px;
  border-radius: 8px;
  overflow: hidden;
}

.carousel-image {
  width: 100%;
  height: 500px;
  object-fit: cover;
  display: block;
}

.prev-button,
.next-button {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  background-color: rgba(0, 0, 0, 0.5);
  color: white;
  border: none;
  padding: 10px;
  cursor: pointer;
  border-radius: 50%;
  font-size: 18px;
}

.prev-button {
  left: 10px;
}

.next-button {
  right: 10px;
}

.property-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}

.property-header h3 {
  margin: 0;
  font-size: 20px;
  color: #4CAF50;
}

.rent-status {
  padding: 5px 10px;
  background-color: #f44336;
  color: white;
  border-radius: 5px;
  font-size: 12px;
  font-weight: bold;
}

.property-details p {
  margin: 8px 0;
  color: #555;
  font-family: Arial, Helvetica, sans-serif;
}

.property-details p strong {
  color: #333;
}
</style>
