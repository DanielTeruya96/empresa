# Cadastro basico de empresa
Sistema para cadastro de empresa

# Tecnologias utilizadas
- Springboot
- H2
- Swagger

# Requisitos
- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven](https://maven.apache.org)

# Como executar a Aplicação
Dentro da pasta /empresa execute o seguitne comando para instalar as dependencias

```sh
 $ mvn clean install
```
Depois de instaladas as dependências. Entre na pasta target e execute a aplicação com o seguinte comando

```sh
 $ java -jar empresa-0.0.1-SNAPSHOT.jar
```

após executar o comando a aplicação estará rodando na porta 8080

# Swagger
Documentação dos endpoints estão em:
http://localhost:8080//swagger-ui.html#/