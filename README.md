# agendamento-tarefas
# agendamento-tarefas-web

Desafio de desenvolvimento proposto pela CI&T.

### Tecnologias

  - Spring Boot
  - Spring Batch
  - H2 
  - AngularJs
  - Postman
  - Swagger
  - Docker
  - Junit
  
  
### Build / Testes 

 - Requer jdk 8 instalado.
 - Requer Maven instalado.


Para execução dos testes:

```sh
$ cd agendamento-tarefas
$ mvn test
```

Para fazer o build do projeto.

```sh
$ cd agendamento-tarefas
$ mvn clean install
```

### Execução API


 - Requer jre 8 instalado.
 - Porta configurada 8080

```sh
$ cd agendamento-tarefas
$ java -jar target/agendamento-tarefas-0.0.1-SNAPSHOT.jar
Acesso: http://localhost:8088/desafio
```

### Execução WEB


 - Requer node/npm instalado.
 - Porta configurada 8000

```sh
$ cd agendamento-tarefas-web
$ npm start
```

### Funcionamento

 - Imagem Docker disponibilizada
 
	docker pull bernardons/desafio-ciandt:0.0.1
	docker run -d --name desafio-ciandt -p 8080:8080 desafio-ciandt:0.0.1 

 - Acesso Aplicação:
 
	http://localhost:8080/desafio

 - Acesso ao Baco de dados H2 Console
 
	http://localhost:8080/desafio/h2
	JDBC URL: jdbc:h2:/tmp/desafiociandt/dbh2

 - Acesso a Documentação da API (Swagger)
 
	http://localhost:8080/desafio/swagger-ui.html
 
 - Dump H2
 
	agendamento-tarefas/src/main/resources/scripts/schema-h2.sql

 - Postman Collection (Teste de API)
 
	agendamento-tarefas/desafio-ciandt.postman_collection.json










