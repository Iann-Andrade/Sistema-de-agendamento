# 📅 Sistema de Agendamento Online

Aplicação web desenvolvida para gerenciamento de agendamentos, permitindo que clientes visualizem horários disponíveis e realizem reservas de forma simples e organizada.

## 🚀 Tecnologias Utilizadas

### Backend
- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- Maven

### Banco de Dados
- MySQL

### Frontend
- HTML5
- CSS3
- JavaScript

### Controle de Versão
- Git
- GitHub

---

## 📋 Funcionalidades

- Cadastro de agendamentos
- Consulta de horários disponíveis
- Bloqueio de horários já reservados
- Comunicação entre Frontend e Backend através de API REST
- Persistência dos dados em banco MySQL
- Atualização dinâmica da agenda com JavaScript

### Funcionalidades em Desenvolvimento

- Sistema de login e autenticação de usuários
- Histórico de agendamentos por usuário
- Cancelamento de agendamentos
- Painel administrativo
- Recuperação de senha

---

## 🏗️ Arquitetura do Projeto

```text
agenda/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   ├── repository/
│   │   │   └── model/
│   │   │
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── css/
│   │       │   ├── js/
│   │       │   └── images/
│   │       └── application.properties
│
└── pom.xml
```

---

## ⚙️ Como Executar o Projeto

### 1. Clonar o Repositório

```bash
git clone https://github.com/seuusuario/agenda.git
```

### 2. Entrar na Pasta

```bash
cd agenda
```

### 3. Configurar o Banco de Dados

No arquivo:

```properties
src/main/resources/application.properties
```

Configure:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/agenda
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
```

### 4. Executar o Projeto

```bash
mvn spring-boot:run
```

Ou execute pela sua IDE (IntelliJ ou VS Code).

---

## 🔌 API

### Criar Agendamento

```http
POST /agendamentos
```

Exemplo:

```json
{
  "nomeCliente": "João Silva",
  "data": "2026-06-13",
  "hora": "14:00"
}
```

### Listar Agendamentos

```http
GET /agendamentos
```

### Buscar Agendamentos por Data

```http
GET /agendamentos/data/{data}
```

---

## 🎯 Objetivo do Projeto

Este projeto foi desenvolvido com o objetivo de aplicar conhecimentos em:

- Desenvolvimento Backend com Spring Boot
- APIs REST
- Persistência de dados com JPA/Hibernate
- Integração Frontend e Backend
- Manipulação do DOM com JavaScript
- Controle de versão com Git e GitHub

---

## 📚 Aprendizados

Durante o desenvolvimento foram praticados conceitos como:

- Arquitetura MVC
- REST APIs
- CRUD completo
- Relacionamento entre entidades
- Consumo de APIs via Fetch
- Organização de código em camadas
- Boas práticas de versionamento

---

## 👨‍💻 Autor

**Iann Andrade**

Desenvolvedor Java em formação, focado em desenvolvimento web com Spring Boot e construção de soluções práticas para automatização e organização de processos.
