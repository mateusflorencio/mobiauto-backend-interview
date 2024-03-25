# Mobiauto Backend Interview

Teste técnico com a proposta de desenvolvimento de um negócio que envolvem
questões práticas e teóricas relacionadas à arquitetura de sistemas e desenvolvimento
backend em Java

> Status do projeto: Em Desenvolvimento

## Tópicos

🔹[Descrição do projeto](#descrição)

🔹[Funcionalidades](#funcionalidades)

🔹[Imagens da Aplicação](#imagens-da-aplicação)

🔹[Pré-requisitos](#pré-requisitos)

🔹[Libs](#libs)

🔹[Rodando o Back End (servidor)](#rodando-o-back-end-servidor)

🔹[Como rodas os testes](#como-rodas-os-testes)

🔹[Banco de Dados](#banco-de-dados)

🔹[Motivação de arquitetura](#motivação-de-arquitetura)

🔹[Testes](#testes)

🔹[Proposta das Pastas](#proposta-das-pastas)

🔹[Documentação Técnica](#documentação-técnica)

🔹[Issues](#issues)

🔹[Contato](#contato)

🔹[Desenvolvedores](#desenvolvedores)

## Descrição

O sistema Mobiauto tem como objetivo fornecer uma poderosa ferramenta de gestão de Revendas
de veículos, incluindo ferramentas como:

* Cadastro de Revenda
* Gestão de funcionários de cada revenda, com suas respectivas permissões
* Gestão de oportunidade

## Funcionalidades

O projeto ainda está em desenvolvimento e as próximas atualizações serão voltadas nas seguintes tarefas:

* Gestão de revenda(Cadastro)
  * Criação de Revenda
  * Atribuição de usuário para revenda
* Usuários
  * Gerenciamento de niveis de acessos
  * Criação de usuários e clientes
* Gestão de Oportunidades
  * Overview de oportunides
  * Status da oportunidades
  * Atribuição de responsavel pela oportunidades
  * Distribuição inteligente de oportunidades sem resposáveis
  * Transferencia de oportunidade para outro responsavel

## Imagens da Aplicação

> Será disponibilizado em breve

## Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:

* [Git](https://git-scm.com)
* [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [Maven 3.9.6](https://maven.apache.org/download.cgi)
* [Docker](https://www.docker.com/products/docker-desktop)
* [Docker Compose](https://docs.docker.com/compose/install/)
* [IntelliJ IDEA](https://www.jetbrains.com/pt-br/idea/download/)
* [VsCode](https://code.visualstudio.com/)
* [Postman](https://www.postman.com/downloads/)

Obs.: Pode optar ou pelo IntelliJ IDEA ou VsCode, ambos são IDEs/Editor com muitas funcionalidades.

## Libs

* [Spring Boot 3.2.4](https://spring.io/projects/spring-boot)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* [Spring Security](https://spring.io/projects/spring-security)
* [Lombok](https://projectlombok.org/)
* [H2 Database](https://www.h2database.com/html/main.html)
* [JUnit 5](https://junit.org/junit5/)
* [Mockito](https://site.mockito.org/)
* [Swagger](https://swagger.io/)
* [Docker](https://www.docker.com/)
* [Docker Compose](https://docs.docker.com/compose/)

## Rodando o Back End (servidor)

### Opcao 1 - Local

```bash
# Clone este repositório

$ git clone https://github.com/mateusflorencio/mobiauto-backend-interview.git

# Acesse a pasta do projeto no terminal/cmd

$ cd mobiauto-backend

# Instale as dependências

$ mvn clean install

# Execute a aplicação

$ mvn spring-boot:run

# O servidor inciará na porta:8080 - acesse http://localhost:8080

```

### Opcao 2 - Docker

```bash
# Clone este repositório

$ git clone https://github.com/mateusflorencio/mobiauto-backend-interview.git

# Acesse a pasta do projeto no terminal/cmd

$ cd mobiauto-backend

# Instale as dependências

$ mvn clean install

# Execute a aplicação

$ docker-compose up

# O servidor inciará na porta:8080 - acesse http://localhost:8080

```

## Como rodas os testes

Assumindo que o projeto já foi clonado e as dependências já foram instaladas, basta executar o comando abaixo:

```bash

mvn test

```

## Banco de Dados

O banco de dados utilizado é o H2, um banco de dados em memória, para acessar o console do banco de dados, acesse:

```bash

http://localhost:8080/h2-console

```

### Configurações do banco de dados H2

```bash

Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:mem:testdb
User Name: sa
Password: password

```

## Motivação de arquitetura

A arquitetura do projeto foi pensada para ser escalável e de fácil manutenção, com isso foi utilizado o padrão de arquitetura de software Clean Architecture, que é uma arquitetura de software que visa separar as responsabilidades de cada camada do projeto, tornando o projeto mais organizado e de fácil manutenção.
Mesmo não sendo um projeto grande, a ideia é que ele possa crescer e se tornar um projeto grande, com isso a arquitetura foi pensada para ser escalável e com um possivel mudança de framework ou banco de dados, a mudança seja feita de forma mais fácil e rápida.

Além disso foi escolhido o ecosistema spring, que é um dos mais utilizados no mercado, por ser um framework robusto e com uma grande comunidade, o que facilita a resolução de problemas e a implementação de novas funcionalidades, propriamente o Spring Web MVC, Spring Data JPA e Spring Security.

Dependedo da disponibilidade e escalabilidade do projeto pode ser estudado a mudança para o Spring webflux, que é uma versão reativa do spring boot, que é mais performático e escalável.

A arquitetura do projeto foi dividida em 4 grandes camadas e uma apenas de contracts, sendo elas: Application, Domain, External, Main e Data para abstrair a camada de persistência.

### Application

A camada de Application é responsável por orquestrar as chamadas de serviços, ela é a camada mais externa da aplicação, onde as requisições HTTP são recebidas e os dados são enviados para a camada de Domain.

### Domain

A camada de Domain é a camada mais interna da aplicação, ela é responsável por toda a lógica de negócio da aplicação, nela estão contidos os modelos de dados, os casos de usos e as exceções além das contratos de serviços.

### External

A camada de External é responsável por toda a comunicação com o mundo externo, nela estão contidos todos os adaptadores, como repositories.

### Main

A camada de Main é responsável por inicializar a aplicação, nela estão contidos os controllers, os mappers e as configurações de segurança, construções de beans e classes de configuração com suas respectivas factories.

### Data

A camada de Data é responsável por abstrair a camada de persistência, nela estão contidos as interfaces de repositórios.

## Testes

Os testes foram feitos utilizando o JUnit 5 e mockito.
Foi focado o desenvolvimento de testes unitários, testando os casos de uso e os serviços.
E com o teste de integração, testando a integração entre as camadas de Application e Domain.

## Proposta das Pastas

```bash

├── src
│   ├── main
│   │   ├── java
│   │   │   ├── br.com.mobiauto
│   │   │   │   ├── application
│   │   │   │   │   ├── controllers
│   │   │   │   │   ├── mappers
│   │   │   │   ├── domain
│   │   │   │   │   ├── contracts
│   │   │   │   │   ├── exceptions
│   │   │   │   │   ├── models
│   │   │   │   │   ├── services
│   │   │   │   │   ├── usecases
│   │   │   │   ├── external
│   │   │   │   │   ├── adapters
│   │   │   │   │   │   ├── repositories
│   │   │   │   ├── main
│   │   │   │   │   ├── config
│   │   │   │   │   ├── controllers
│   │   │   │   ├── data
│   │   │   │   │   ├── repositories
│   │   │   │   │   ├── dtos
│   │   │   │   ├── MobiautoApplication.java
│   ├── test
│   │   ├── java
│   │   │   ├── br.com.mobiauto
│   │   │   │   ├── application
│   │   │   │   │   ├── controllers
│   │   │   │   ├── domain
│   │   │   │   │   ├── services
│   │   │   │   │   ├── usecases
│   │   │   │   ├── external
│   │   │   │   │   ├── adapters
│   │   │   │   │   │   ├── repositories
│   │   │   │   ├── integration

```

## Documentação Técnica

Para mais informações sobre o projeto, acesse a documentação técnica.

[Documentação Técnica](./docs/technical-documentation.md)

## Issues

Sinta-se a vontade para contribuir com o projeto, abra uma issue ou envie um pull request.

## Contato

Mateus Florêncio - <mateusflorencio@outlook.com.br>

## Desenvolvedores

| [<img src="https://avatars.githubusercontent.com/u/84187950?v=4" width=115><br><sub>Mateus Florêncio</sub>](https://github.com/mateusflorencio) |
| :---------------------------------------------------------------------------------------------------------------------------------------------: |
