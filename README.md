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


#Sobre a API
Crud simples de uma empresa. 

uma empresa tem nome fantasia,cnpj, razão social e o nome do proprietario.

Para fins de auditoria é registrado o usuario que credenciou a empresa e a data e tambem é registrado o usario que efetuou a alteracao e o horário.

Essa API exige que tenha um basic auth porém não valida essas credencias.

##validações da API
A API não deixa credenciar mais de uma empresa com o mesmo CNPJ, 
o CPNJ precisa de ser informado no padrão XX.XXX.XXX/XXXX-XX


###Dicionario de dados da API 

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




