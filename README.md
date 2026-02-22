# 📦 Gestão de Usuários — Bootcamp Java

Sistema de cadastro de usuários desenvolvido em Java, utilizando banco de dados H2 em memória, aplicando os princípios de Programação Orientada a Objetos e arquitetura em camadas.

---

## 🎯 Objetivo

Desenvolver um CRUD completo em Java como base de projeto que será utilizada e evoluída durante todo o bootcamp.

---

## 🚀 Tecnologias

- Java 21
- JPA + Hibernate
- Banco de dados H2 (em memória)
- Maven
- IntelliJ IDEA

---

## 🏗️ Estrutura do Projeto
```
src/main/
├── java/
│   └── com.ramires.gestaousuarios/
│       ├── App.java                        # Ponto de entrada, menu interativo
│       ├── model/
│       │   ├── Pessoa.java                 # Classe abstrata base (Herança)
│       │   └── Usuario.java                # Herda Pessoa, adiciona email
│       ├── repository/
│       │   └── UsuarioRepository.java      # Operações com banco de dados via JPA
│       └── service/
│           └── UsuarioService.java         # Regras de negócio e coordenação
└── resources/
    └── META-INF/
        └── persistence.xml                 # Configuração do JPA e H2
```

---

## 🧠 Conceitos de POO Aplicados

### Abstração
`Pessoa` é uma classe abstrata que representa o conceito de uma pessoa no sistema — ela não pode ser instanciada diretamente, apenas serve de modelo para suas subclasses.

### Encapsulamento
Atributos privados com acesso controlado por getters e setters em todas as classes de modelo.

### Herança
`Usuario` herda de `Pessoa`, aproveitando os atributos `id` e `nome` e adicionando `email`.

### Polimorfismo
Método abstrato `exibirDados()` definido em `Pessoa` e implementado de forma específica em `Usuario`. Permite que futuras classes como `Admin` ou `Cliente` exibam seus dados de maneiras diferentes sem alterar o serviço.

---

## ⚙️ Funcionalidades (CRUD)

- [x] Cadastrar novo usuário com validação de nome e e-mail
- [x] Listar todos os usuários
- [x] Buscar usuário por ID
- [x] Atualizar dados de um usuário com confirmação
- [x] Remover usuário com confirmação

---

## 🗄️ Banco de Dados

Utiliza H2 em memória — os dados existem enquanto a aplicação está rodando e são apagados ao encerrar.
```
URL: jdbc:h2:mem:banco-h2
```

---

## ▶️ Como Executar

1. Clone o repositório
```bash
git clone https://github.com/RamiresFilho/crud-java.git
```

2. Abra no IntelliJ IDEA

3. Aguarde o Maven baixar as dependências

4. Execute a classe `App.java`

---

## 📈 Próximas Evoluções

- [ ] Integração com Spring Boot
---

## 👨‍💻 Autor

Feito por **RamiresFilho** durante o Bootcamp Java