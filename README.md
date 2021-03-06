# Cadastro basico de empresa
Sistema para cadastro de empresa

[![Run in Insomnia}](https://insomnia.rest/images/run.svg)](https://insomnia.rest/run/?label=Empresa&uri=https%3A%2F%2Fgithub.com%2FDanielTeruya96%2Fempresa%2Fblob%2Fmaster%2FPostman%2520collection%2FInsomnia_2022-05-25.json)

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


# Sobre a API
Crud simples de uma empresa. 

uma empresa tem nome fantasia,cnpj, razão social e o nome do proprietario.

Para fins de auditoria é registrado o usuario que credenciou a empresa e a data e tambem é registrado o usario que efetuou a alteracao e o horário.

Essa API exige que tenha um basic auth porém não valida essas credencias.

Essa API usa utiliza o banco h2 para persistir os dados, por isso os dados são salvos apenas em tempo de execução, apos desligar a aplicação os dados não serao salvos.

## validações da API
A API não deixa credenciar mais de uma empresa com o mesmo CNPJ, 
o CPNJ precisa de ser informado no padrão XX.XXX.XXX/XXXX-XX


### Dicionario de dados da API 

id: identificador único da empresa

nome_fantasia: Nome fantasia da empresa

cnpj: cnpj da empresa

razao_social: Razão social da empresa

nome_proprietario: Nome do Proprietario da empresa

usuario_criacao: Usuario que efetivou o credenciamento da empresa

data_criacao: Data que foi efetivada o credenciamento da empresa

usuario_alteraco: Usuario que efetivou uma mudança nos registro da empresa

data_alteracao: Data em que foi efetivada a alteracao

situacao: Exclusão lógica do registro (1 - criado, visivel para o consumidor da API, 2- apagado, não visicel para o consumidor da API)


### Adicional
Dentro da pasta Postman collection, no arquivo Basico Empresa.postman_collection.json esta uma collectiondo postman para testar a pi

e dentro da pasta Teste integracao esta uma collection para realizar o teste integrado, e com as variaveis de ambiente
