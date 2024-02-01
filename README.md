<div align="center">
    
# 💰 Credit System

![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)

Back-End de um App de empréstimo bancário.

</div>

## 🔧 Funcionalidades
O projeto permite criar clientes, onde cada cliente pode fazer uma solicitação de empréstimo, verificar todos os 
seus empréstimos e analisar um empréstimo específico com mais detalhes.

## ↔️ End-Points

### Cliente
#### Criar
    Método: post
    Rota: /api/customers
    Body:
    {
        "firstName": String,
        "lastName": String,
        "document": String,
        "income": BigDecimal,
        "email": String,
        "password": Stirng,
        "zipCode": String,
        "street": String
    }

#### Retornar
    Método: get
    Rota: /api/customers/{id}

#### Atualizar
    Método: patch
    Rota: /api/customers
    Body:
    {
        "firstName": String,
        "lastName": String,
        "income": BigDecimal,
        "zipCode": String,
        "street": String
    }

#### Remover
    Método: delete
    Rota: /api/customers/{id}

### Empréstimo
#### Criar
    Método: post
    Rota: /api/credits
    Body:
    {
        "creditValue": BigDecimal,
        "dayFirstOfInstallment": LocalDate,
        "numberOfInstallments": Int,
        "customerId": Long
    }

#### Retornar por Cliente
    Método: get
    Rota: /api/credits?customerId={id}

#### Retornar por Código do Empréstimo
    Método: get
    Rota: /api/credits/{código}?customerId={id}

## 🛠️ Técnicas e tecnologias utilizadas
 - `Spring Boot` 
   - Starter data JPA
   - Starter Validation
   - Starter Web
 - `H2 Database`
 - `Flyway`
 - `Tratamento de Exceções customizadas`
 - `Validação de campos`


<div align="center">
Feito por Raphael da Silva 🚀 <br/>
  Formação Kotlin Back-end Developer DIO
</div>

