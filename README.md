# Projeto microservice

	Projeto desenvolvido para o processo seletivo de Arquiteto de Software - Diogo Carvalho de Matos

## Tecnologias utilizadas

- Java (11)
- Spring boot (2.4.1)
- Maven 3.x
- Docker 
- Docker Compose

## Instalação

A versão - Spring - utilizada neste projeto possui, de modo nativo, suporte para Docker. Por essa razão, basta executar as instruções descritas abaixo para a construção e execução da imagem, respectivamente:

```bash
./mvnw spring-boot:build-image

docker container run -p 8080:8080 docker.io/library/client-api:0.0.1-SNAPSHOT
```

Ou então, podemos flexibilizar a construção e execução - Docker - utilizando os mecanismos de *build* - Dockerfile - e execução - docker-compose, executando a instrução descrita abaixo:

```bash
mvn package

docker-compose up -d
```

## Instruções para testes

Após realizar a instalação e execução da API

**1** - Selecione o link [Swagger](http://client-api-builders.azurewebsites.net/api/v1/swagger-ui/index.html)

**2** - Selecione o item **client-resource** 

**3** - Realize as operações conforme exemplos configurados em cada endpoint
