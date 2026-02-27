# 📦 Gestão de Usuários — Bootcamp Java

Sistema de cadastro de usuários desenvolvido em Java, utilizando banco de dados H2 em memória, aplicando os princípios de Programação Orientada a Objetos e arquitetura em camadas com Spring Boot e interface web com Thymeleaf e Bootstrap.

---

## 🎯 Objetivo

Desenvolver um CRUD completo em Java como base de projeto que será utilizada e evoluída durante todo o bootcamp.

---

## 🚀 Tecnologias

- Java 21
- Spring Boot 3.4.2
- JPA + Hibernate
- Banco de dados H2 (em memória)
- Thymeleaf
- Bootstrap 5.3
- Maven
- IntelliJ IDEA

---

## 🏗️ Estrutura do Projeto
```
src/main/
├── java/
│   └── com.ramires.gestaousuarios/
│       ├── App.java                        # Ponto de entrada da aplicação
│       ├── controller/
│       │   └── UsuarioController.java      # Rotas e requisições web
│       ├── model/
│       │   ├── Pessoa.java                 # Classe abstrata base (Herança)
│       │   └── Usuario.java                # Herda Pessoa, adiciona email
│       ├── repository/
│       │   └── UsuarioRepository.java      # Operações com banco de dados via JPA
│       └── service/
│           └── UsuarioService.java         # Regras de negócio e coordenação
└── resources/
    ├── templates/
    │   ├── lista.html                      # Tela principal com tabela de usuários
    │   ├── formulario.html                 # Tela de cadastro e edição
    │   └── buscar.html                     # Tela de busca por ID
    └── application.properties              # Configuração do Spring Boot, JPA e H2
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

## 🍃 Arquitetura Spring Boot

### Anotações utilizadas

| Anotação | Classe | Função |
|---|---|---|
| `@SpringBootApplication` | `App` | Inicializa o Spring Boot e escaneia os pacotes automaticamente |
| `@Controller` | `UsuarioController` | Recebe requisições do navegador e retorna telas HTML |
| `@Service` | `UsuarioService` | Indica a camada de regras de negócio |
| `@Repository` | `UsuarioRepository` | Indica a camada de acesso a dados |
| `@PersistenceContext` | `UsuarioRepository` | Injeta o EntityManager gerenciado pelo Spring |
| `@Transactional` | `UsuarioService` | Gerencia transações automaticamente |

---

## 🌐 Interface Web

Telas desenvolvidas com **Thymeleaf** e **Bootstrap 5.3** com identidade visual inspirada na Deloitte.

| Rota | Descrição |
|---|---|
| `GET /usuarios` | Lista todos os usuários |
| `GET /usuarios/novo` | Formulário de cadastro |
| `POST /usuarios/salvar` | Salva novo usuário |
| `GET /usuarios/editar/{id}` | Formulário de edição |
| `POST /usuarios/atualizar` | Atualiza usuário |
| `GET /usuarios/remover/{id}` | Remove usuário |
| `GET /usuarios/buscar?id=` | Busca usuário por ID |

---

## ⚙️ Funcionalidades (CRUD)

- [x] Cadastrar novo usuário com validação de nome e e-mail
- [x] Listar todos os usuários
- [x] Buscar usuário por ID
- [x] Atualizar dados de um usuário
- [x] Remover usuário com confirmação

---

## 🔒 Segurança

| Proteção | Como foi implementado |
|---|---|
| SQL Injection | JPA + Hibernate usa Prepared Statements automaticamente |
| XSS | Thymeleaf escapa caracteres HTML automaticamente |
| Validação de nome | Apenas letras e espaços são aceitos |
| Validação de e-mail | Verificação de formato — parte local, `@` único e domínio válido |

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

5. Acesse no navegador
```
http://localhost:8080/usuarios
```

---

## 👨‍💻 Autor

Feito por **RamiresFilho** durante o Bootcamp Java Deloitte
