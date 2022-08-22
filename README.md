<br>
<h1 align="center">
Sistema Autorizador de Transações 💳
</h1>
<br>

## 💬 Sobre o repositório

O Sistema Autorizador de Transações é um "mini autorizador" de transações que realiza uma série de verificações e análises com uma interface REST.

Ao final do processo, o autorizador toma uma decisão, aprovando ou não a transação: 
* se aprovada, o valor da transação é debitado do saldo disponível do benefício, e informamos à maquininha que tudo ocorreu bem.
* senão, apenas informamos o que impede a transação de ser feita e o processo se encerra.

Esta aplicação possui 3 serviços, dentre eles:
* Criação de novos cartões;
* Obtenção de saldo do cartão;
* Autorização de transações realizadas usando os cartões previamente criados como meio de pagamento;

## ⚠ Pré-requisitos para execução do projeto

* Java 11 ou versões superiores
* Maven
* Docker

## 📌 Como utilizar?

Para rodar o banco de dados MySQL via Docker, navegue ao diretório do docker (**mini-autorizador/src/docker**) e digite no terminal:

```shell script
docker-compose up
```

Para executar o projeto, digite o seguinte comando no diretório raiz (**mini-autorizador/**):

```
mvn spring-boot:run 
```

## 📲 Serviços disponíveis para testes

### Criar novo cartão
```
Method: POST
URL: http://localhost:8080/cartoes/criar-cartao
Body (json):
{
    "numeroCartao": "6549873025634501",
    "senha": "1234"
}
```

### Obter saldo do cartão
```
Method: GET
URL: http://localhost:8080/cartoes/obter-saldo/{numeroCartao} , onde {numeroCartao} é o número do cartão que se deseja consultar
```

### Realizar uma transação
```
Method: POST
URL: http://localhost:8080/cartoes/transacoes
Body (json):
{
    "numeroCartao": "6549873025634501",
    "senhaCartao": "1234",
    "valor": 10.00
}
```

Para conferir se os serviços estão funcionando, utilize uma ferramenta para testes de API Clients, como o Postman, [saiba mais sobre!](https://medium.com/@thi_carva/testando-servi%C3%A7os-web-api-com-postman-874ac81b20a3)

## 🧠 Links importantes

* [Documentação oficial do Lombok](https://projectlombok.org/)
* [Documentação oficial do Map Struct](https://mapstruct.org/)
* [Referência para o padrão arquitetural REST](https://restfulapi.net/)
* [Palheta de atalhos de comandos do IntelliJ](https://resources.jetbrains.com/storage/products/intellij-idea/docs/IntelliJIDEA_ReferenceCard.pdf)
* [Site oficial do Spring](https://spring.io/)
* [Site oficial do Spring Initialzr para setup do projeto](https://start.spring.io/)
* [SDKMan! para gerenciamento e instalação do Java e Maven](https://sdkman.io/)

---
