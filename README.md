# Projeto CRUD de Usuários com Docker
## Descrição do Projeto
Este projeto é um CRUD básico de usuários, desenvolvido com o objetivo principal de praticar o uso de Docker. O projeto inclui a criação de uma imagem Docker para o aplicativo Java, configuração do Maven e Java 21, e a utilização de Docker Compose para orquestrar os contêineres da aplicação e do banco de dados PostgreSQL.

### Funcionalidades
- CRUD de Usuários:
- Criação de novos usuários
- Leitura de dados de usuários
- Atualização de dados de usuários
- Exclusão de usuários

### Tecnologias Utilizadas
- Linguagem de Programação: Java 21
- Build Tool: Maven
- Banco de Dados: PostgreSQL
- Containerização: Docker

### Estrutura do Projeto
#### Dockerfile
 - O Dockerfile é usado para criar uma imagem Docker do projeto. 

#### Esta imagem contém:
- Maven para gerenciamento de dependências e build do projeto
- Java 21 para execução da aplicação
## Dockerfile
```bash
FROM maven:3.9.8-amazoncorretto-21-al2023 AS build

WORKDIR /app

COPY src /app/src
COPY pom.xml /app

RUN mvn clean install -DskipTests

FROM eclipse-temurin:21

COPY --from=build /app/target/testing-docker-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
```
## docker-compose
O arquivo docker-compose.yml é usado para definir e gerenciar os contêineres da aplicação e do banco de dados PostgreSQL.
```bash
version: "3.8"

services:
  postgres-db:
    image: postgres
    container_name: postgres-db
    environment:
      - POSTGRES_PASSWORD=pg1234
      - POSTGRES_USERNAME=postgres
      - POSTGRES_DB=postgres-db
    ports:
      - 5432:5432

  testing-docker:
    image: testing-docker:1.0
    container_name: testing-docker
    ports:
      - 8080:8080
    depends_on:
      - postgres-db
```
