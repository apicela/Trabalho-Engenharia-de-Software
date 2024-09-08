## ⚙️ Como configurar aplicação para rodar frontend + backend juntos
 - Requisitos obrigatórios: Docker
1. Clone este repositório: ``` git clone git@github.com:apicela/Trabalho-Engenharia-de-Software.git ```
2. Acesse a pasta raíz
3. Execute o seguinte comando no terminal: `docker-compose up --build`
4. Acesse o link: http://localhost:3000/ 


## Grupo: André Henrique, Jamil Apicela, Laura Campos, Vitor Martins

Neste trabalho prático, o aluno deverá criar uma aplicação Web que contenha
um formulário para cadastramento dos imóveis disponíveis para locação de uma
imobiliária. O formulário deverá ser apresentado de maneira organizada e elegante. Os
dados dos imóveis deverão ser inseridos adequadamente no banco de dados.

O sistema deverá considerar dois tipos de imóveis: casa e apartamento. Para os
imóveis da categoria casa, as seguintes informações devem ser armazenadas:
quantidade de quartos, quantidade de suítes, quantidade de salas de estar, número de
vagas na garagem, área (em metros quadrados), se possui armário embutido e descrição
(algum detalhe a mais que se deseja informar sobre a casa). Já para os imóveis da
categoria apartamento, as seguintes informações devem ser armazenadas: quantidade
de quartos, quantidade de suítes, quantidade de salas de estar, quantidade de salas de
jantar, número de vagas na garagem, área (em metros quadrados), se possui armário
embutido, descrição (algum detalhe a mais que se deseja informar sobre o
apartamento), andar, valor do condomínio e indicativo se o condomínio possui portaria
24 horas. Além disso, a aplicação deverá permitir cadastrar e visualizar fotos do imóvel.

Para cada imóvel cadastrado no sistema é importante saber o seu endereço
completo, sendo necessário cadastrar o bairro no qual o imóvel está localizado. O
usuário deverá selecionar o bairro dentre uma lista de bairros disponíveis. Além disso, é
obrigatório armazenar o valor de aluguel de cada imóvel. A aplicação deverá permitir o
agendamento de visita no imóvel. Neste caso, ao clicar no botão ‘Agendar Visita’, a
aplicação deverá permitir selecionar uma data no calendário. Para uma determinada
data, o usuário deverá selecionar um horário disponível para visita e clicar em ‘Finalizar’.

O trabalho deverá ser feito pelos mesmos grupos do Trabalho 1. O grupo poderá
implementar o trabalho usando a tecnologia e frameworks de sua preferência. Em
relação ao banco de dados, o grupo também é livre para escolher o banco de dados
relacional ou NoSQL.
