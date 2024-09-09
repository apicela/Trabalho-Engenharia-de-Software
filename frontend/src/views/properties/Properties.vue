<template>
  <div class="container">
    <div class="card" style="margin-top: 3.5%">
      <div class="card-header">
        <h4>
          Propriedades
          <button
            @click="openRegisterPropertyModel"
            class="btn btn-primary float-end"
          >
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
            <tr v-for="(properties, index) in this.properties" :key="index">
              <td>{{ properties.propertyType }}</td>
              <td>{{ properties.description }}</td>
              <td>{{ properties.address.street }}</td>
              <td>R$ {{ properties.rentValue }}</td>
              <td align="center">
                <RouterLink class="nav-link" 
                      :to="{ name: 'property', params: { id: properties.id } }">
                  <button
                    type="button"
                    class="btn btn-primary"
                    style="
                      background-color: rgb(255, 255, 255);
                      border-color: rgb(141, 159, 209);
                    "
                  >
                    📝
                  </button>
                </RouterLink>
              </td>
            </tr>
          </tbody>
        </table>

        <!-- Modal para Registrar Imóvel -->
        <div
          class="modal"
          tabindex="-1"
          role="dialog"
          ref="registerPropertyModel"
        >
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Registrar Imóvel</h5>
                <button
                  type="button"
                  class="close"
                  data-dismiss="modal"
                  aria-label="Close"
                  @click="closeRegisterPropertyModel"
                >
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <form @submit.prevent="submitProperty">
                  <div class="form-group">
                    <label for="propertyType">Tipo de Imóvel</label>
                    <select
                      class="form-control"
                      id="propertyType"
                      v-model="property.propertyType"
                      required
                    >
                      <option value="" disabled selected>
                        Selecione o tipo
                      </option>
                      <option value="CASA">Casa</option>
                      <option value="APARTAMENTO">Apartamento</option>
                    </select>
                  </div>
                  <div class="form-group">
                    <label for="propertyValue">CEP</label>
                    <input
                      type="number"
                      class="form-control"
                      id="propertyValue"
                      v-model="property.value"
                      required
                      placeholder="Ex: 300000"
                    />
                  </div>

                  <div class="form-group">
                    <label for="description">Descrição</label>
                    <input
                      type="text"
                      class="form-control"
                      id="description"
                      v-model="property.description"
                      required
                      placeholder="Ex: Bem localizado, próximo ao parque X"
                    />
                  </div>

                  <div class="form-group row">
                    <div class="col-md-6">
                      <label for="rentValue">Valor do Aluguel</label>
                      <input
                        type="number"
                        class="form-control"
                        id="rentValue"
                        v-model="property.rentValue"
                        required
                        placeholder="Ex: 1500"
                      />
                    </div>
                    <div
                      class="col-md-6"
                      v-if="property.type === 'APARTAMENTO'"
                    >
                      <label for="condominiumValue">Valor do Condomínio</label>
                      <input
                        type="number"
                        class="form-control"
                        id="condominiumValue"
                        required
                        v-model="property.condominiumValue"
                        placeholder="Digite o valor do condomínio"
                      />
                    </div>
                    <div v-if="property.type === 'APARTAMENTO'">
                      <label for="concierge24h">Porteiro 24h</label>
                      <select
                        class="form-control"
                        id="concierge24h"
                        v-model="property.concierge24h"
                        required
                      >
                        <option :value="true">Sim</option>
                        <option :value="false">Não</option>
                      </select>
                    </div>

                    <div
                      class="col-md-6"
                      v-if="property.type === 'APARTAMENTO'"
                    >
                      <label for="floorNumber">Número do Andar</label>
                      <input
                        type="number"
                        required
                        class="form-control"
                        id="floor"
                        v-model="property.floor"
                        placeholder="Digite o número do andar"
                      />
                    </div>
                    <div class="col-md-6">
                      <label for="area">Area (m²)</label>
                      <input
                        type="text"
                        class="form-control"
                        id="area"
                        v-model="property.area"
                        required
                        placeholder="Ex: 70"
                      />
                    </div>
                    <div class="col-md-3">
                      <label for="rooms">Quartos</label>
                      <input
                        type="number"
                        class="form-control"
                        id="rooms"
                        v-model="property.rooms"
                        required
                        placeholder="Ex: 3"
                      />
                    </div>
                    <div class="col-md-3">
                      <label for="suits">Suites</label>
                      <input
                        type="number"
                        class="form-control"
                        id="suits"
                        v-model="property.suits"
                        required
                        placeholder="Ex: 1"
                      />
                    </div>
                    <div class="col-md-3">
                      <label for="livingRoom">Salas de estar</label>
                      <input
                        type="number"
                        class="form-control"
                        id="livingRoom"
                        v-model="property.livingRoom"
                        required
                        placeholder="Ex: 1"
                      />
                    </div>
                    <div
                      class="col-md-3"
                      v-if="property.type === 'APARTAMENTO'"
                    >
                      <label for="diningRoom">Salas jantar</label>
                      <input
                        type="number"
                        class="form-control"
                        id="diningRoom"
                        v-model="property.diningRoom"
                        placeholder="Ex: 1"
                      />
                    </div>
                    <div class="col-md-6">
                      <label for="vacanciesGarage">Vagas na Garagem</label>
                      <input
                        type="number"
                        class="form-control"
                        id="vacanciesGarage"
                        v-model="property.vacanciesGarage"
                        required
                        placeholder="Ex: 3"
                      />
                    </div>
                    <div class="col-md-6">
                      <label for="closets">Armarios Embutidos</label>
                      <select
                        class="form-control"
                        id="closets"
                        v-model="property.closets"
                        required
                      >
                        <option :value="true">Sim</option>
                        <option :value="false">Não</option>
                      </select>
                    </div>
                  </div>
                  <label for="propertyValue">Imagens</label>

                  <div class="form-group">
                    <input
                      type="file"
                      accept="image/jpeg, image/png"
                      id="images"
                      multiple
                      @change="handleFileSelect"
                      class="file-input"
                    />
                  </div>
                  <button type="submit" class="btn btn-primary">Salvar</button>
                </form>
              </div>
            </div>
          </div>
        </div>

        <!-- Modal de transferencia -->
        <div class="modal" tabindex="-1" role="dialog" ref="transfersModel">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Transferencias</h5>
                <button
                  type="button"
                  class="close"
                  data-dismiss="modal"
                  aria-label="Close"
                  @click="closeTransfersModel"
                >
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <ul v-for="(transacao, index) in modalData" :key="index">
                  <li>{{ index + 1 }}:</li>
                  <p style="margin-left: 6%">
                    Tipo de Transação: {{ transacao.tipoTransacao }}
                  </p>
                  <p style="margin-left: 6%">
                    Valor da Transação: {{ transacao.valorTransacao }}
                  </p>
                  <p style="margin-left: 6%">
                    Conta de Origem: {{ transacao.contaOrigem }}
                  </p>
                  <p style="margin-left: 6%">
                    Conta de Destino: {{ transacao.contaDestino }}
                  </p>
                  <br />
                </ul>
              </div>
            </div>
          </div>
        </div>

        <!-- Modal de sucesso -->
        <div class="modal" tabindex="-1" role="dialog" ref="sucessModel">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Detalhes da Duplicata</h5>
                <button
                  type="button"
                  class="close"
                  data-dismiss="modal"
                  aria-label="Close"
                  @click="closeSucessModel"
                >
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <p>Arquivo CNAB enviado e processado com sucesso!</p>
                <br />
                <h6>Transferências realizadas:</h6>

                <ul
                  v-if="
                    modalData &&
                    modalData.duplicata &&
                    modalData.duplicata.transacoes
                  "
                  v-for="(transacao, index) in modalData.duplicata.transacoes"
                  :key="index"
                >
                  <li>{{ index + 1 }}:</li>
                  <p style="margin-left: 6%">
                    Tipo de Transação: {{ transacao.tipoTransacao }}
                  </p>
                  <p style="margin-left: 6%">
                    Valor da Transação: {{ transacao.valorTransacao }}
                  </p>
                  <p style="margin-left: 6%">
                    Conta de Origem: {{ transacao.contaOrigem }}
                  </p>
                  <p style="margin-left: 6%">
                    Conta de Destino: {{ transacao.contaDestino }}
                  </p>
                  <br />
                </ul>
              </div>
            </div>
          </div>
        </div>

        <!-- Modal de error -->
        <div class="modal" tabindex="-1" role="dialog" ref="errorModel">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Erro ao inserir duplicata!</h5>
                <button
                  type="button"
                  class="close"
                  data-dismiss="modal"
                  aria-label="Close"
                  @click="closeErrorModel"
                >
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <p>status: {{ modalData.status }}</p>
                <p>mensagem: {{ modalData.message }}</p>
                <p>lista de erros:</p>
                <ul>
                  <p v-for="(erro, index) in modalData.errors" :key="index">
                    {{ erro }}
                  </p>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "properties",
  data() {
    return {
      properties: [
        {
          id: "283c3ab8-b091-4670-b741-eafcc17dee06",
          propertyType: "APARTAMENTO",
          address: {
            street: "Rua dos bobos",
            neighborhood: null,
            city: null,
            state: null,
            region: null,
            cep: null,
            complement: null,
          },
          rentValue: 3520.97,
          description: "Martins Penthouse",
          area: 0,
          rooms: 0,
          suits: 0,
          livingRoom: 0,
          vacanciesGarage: 0,
          closets: false,
          images: [],
          floor: 0,
          diningRoom: 0,
          condominiumValue: 0,
          concierge24h: false,
          rented: false,
        },
        {
          id: "e58d53c3-9014-4d48-9c33-33fdc8b78738",
          propertyType: "CASA",
          address: {
            street: "Rua dos bobos",
            neighborhood: null,
            city: null,
            state: null,
            region: null,
            cep: null,
            complement: null,
          },
          rentValue: 3592.63,
          description: "Apicela House",
          area: 0,
          rooms: 0,
          suits: 0,
          livingRoom: 0,
          vacanciesGarage: 0,
          closets: false,
          images: [],
          rented: false,
        },
        {
          id: "4b64b2ed-1293-4c8c-9840-dfaf0f21b68a",
          propertyType: "APARTAMENTO",
          address: {
            street: "Rua dos bobos",
            neighborhood: null,
            city: null,
            state: null,
            region: null,
            cep: null,
            complement: null,
          },
          rentValue: 1094.27,
          description: "Costa Penthouse",
          area: 0,
          rooms: 0,
          suits: 0,
          livingRoom: 0,
          vacanciesGarage: 0,
          closets: false,
          images: [],
          floor: 0,
          diningRoom: 0,
          condominiumValue: 0,
          concierge24h: false,
          rented: false,
        },
        {
          id: "a867e492-83fa-47de-bd59-1eb62e683797",
          propertyType: "CASA",
          address: {
            street: "Rua dos bobos",
            neighborhood: null,
            city: null,
            state: null,
            region: null,
            cep: null,
            complement: null,
          },
          rentValue: 3706.91,
          description: "Campos House",
          area: 0,
          rooms: 0,
          suits: 0,
          livingRoom: 0,
          vacanciesGarage: 0,
          closets: false,
          images: [],
          rented: false,
        },
      ],
      modalData: [],
      property: {
        propertyType: "",
        address: {
          street: "",
          neighborhood: "",
          city: "",
          state: "",
          region: "",
          cep: "",
          complement: "",
        },
        rentValue: "",
        description: "",
        area: "",
        rooms: "",
        suits: "",
        livingRoom: "",
        vacanciesGarage: "",
        closets: "",
        images: "",
        floor: "",
        diningRoom: "",
        condominiumValue: "",
        concierge24h: "",
      },
    };
  },
  mounted() {
    this.getAllProperties();
  },
  methods: {
    handleFileSelect(event) {
      const files = event.target.files;
      this.property.images = Array.from(files);
    },
    createFormData(property) {
  const formData = new FormData();

  // Adiciona campos simples
  formData.append('propertyType', property.propertyType);
  formData.append('rentValue', property.rentValue);
  formData.append('description', property.description);
  formData.append('area', property.area);
  formData.append('rooms', property.rooms);
  formData.append('suits', property.suits);
  formData.append('livingRoom', property.livingRoom);
  formData.append('vacanciesGarage', property.vacanciesGarage);
  formData.append('closets', property.closets);
  formData.append('floor', property.floor);
  formData.append('diningRoom', property.diningRoom);
  formData.append('condominiumValue', property.condominiumValue);
  formData.append('concierge24h', property.concierge24h);

  // Adiciona endereço (campos aninhados)
  formData.append('address.street', property.address.street);
  formData.append('address.neighborhood', property.address.neighborhood);
  formData.append('address.city', property.address.city);
  formData.append('address.state', property.address.state);
  formData.append('address.region', property.address.region);
  formData.append('address.cep', property.address.cep);
  formData.append('address.complement', property.address.complement);

  // Adiciona imagens (assumindo que são arquivos)
  for (let i = 0; i < property.images.length; i++) {
    formData.append('images', property.images[i]);
  }

  return formData;
},
    getAllProperties() {
      axios
        .get("http://localhost:8080/properties")
        .then((res) => {
          console.log("res:" + JSON.stringify(res));
          this.properties = res.data;
        })
        .catch((err) => {
          console.error("Error fetching properties", err);
        });
    },
    openTransfersModel(transacoes) {
      this.modalData = transacoes;
      this.$refs.transfersModel.style.display = "block";
    },
    openRegisterPropertyModel() {
      this.$refs.registerPropertyModel.style.display = "block";
    },
    closeTransfersModel() {
      this.$refs.transfersModel.style.display = "none";
    },
    openSucessModel(duplicata) {
      this.modalData = duplicata;
      this.$refs.sucessModel.style.display = "block";
    },
    closeSucessModel() {
      this.$refs.sucessModel.style.display = "none";
    },
    openErrorModel(transacoes) {
      this.modalData = transacoes;
      this.$refs.errorModel.style.display = "block";
    },
    closeErrorModel() {
      this.$refs.errorModel.style.display = "none";
    },
    closeRegisterPropertyModel() {
      // Lógica para fechar o modal
      this.$refs.registerPropertyModel.style.display = "none";
    },
    submitProperty() {
      let formData = this.createFormData(this.property)
      let API_URL = this.property.propertyType == "CASA" ? "http://localhost:8080/houses" : "http://localhost:8080/apartments"
      axios
          .post(API_URL, formData, {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          })
          .then((response) => {
            this.openSucessModel(response.data);
            this.getAllProperties();
            console.log(response.data);
          })
          .catch((error) => {
            console.log("modal data: ", error.response.data);
            this.openErrorModel(error.response.data);
          });
      console.log(this.property);
      this.closeRegisterPropertyModel();
    },
  },
};
</script>

<style>
th.tituloTable {
  background-color: #e6e6e6;
}
</style>
