# Relatório de Design - API REST de Biblioteca

## Decisões de Arquitetura

### **1. Linguagem de Programação: Java**
- **Motivação:** Aumentar a prática e familiaridade com a linguagem Java
- **Benefícios:** 
  - Forte tipagem e orientação a objetos
  - Ecossistema robusto com Spring Boot
  - Facilita manutenção e escalabilidade
  - Padrões de mercado bem estabelecidos

### **2. Padrão Arquitetural: Controller-Service-Repository**
- **Separação de Responsabilidades:**
  - **Controllers:** Responsáveis pelo roteamento HTTP e validação de entrada
  - **Services:** Contêm toda a lógica de negócio e regras de validação
  - **Repositories:** Gerenciam a comunicação com o banco de dados
- **Benefícios:**
  - Código organizado e fácil de manter
  - Testabilidade melhorada (cada camada pode ser testada isoladamente)
  - Reutilização de código entre diferentes controllers
  - Facilita futuras expansões sem aumentar complexidade desnecessária

### **3. Containerização: Docker**
- **Motivação:** Facilitar testes por diferentes pessoas e ambientes
- **Benefícios:**
  - Ambiente isolado e reproduzível
  - Configuração simplificada (Java + MySQL + phpMyAdmin)
  - Elimina problemas de "funciona na minha máquina"
  - Facilita deploy em diferentes ambientes
  - Reduz tempo de setup para novos desenvolvedores

## Tecnologias Utilizadas

| **Tecnologia** | **Versão** | **Propósito** |
|----------------|------------|---------------|
| **Java** | 24 | Linguagem principal |
| **Spring Boot** | 3.5.5 | Framework web e injeção de dependências |
| **Spring Data JPA** | - | Persistência e acesso a dados |
| **MySQL** | 8.0 | Banco de dados relacional |
| **Docker** | - | Containerização |
| **Maven** | - | Gerenciamento de dependências |

## Estrutura da API

### **Entidades Principais:**
- **Usuários:** Gestão de usuários da biblioteca
- **Autores:** Cadastro de autores dos livros
- **Livros:** Controle do acervo bibliográfico
- **Empréstimos:** Gestão de empréstimos e devoluções

### **Endpoints por Entidade:**
- **GET** `/entidade` - Listar todos
- **GET** `/entidade/{id}` - Buscar por ID
- **POST** `/entidade` - Criar novo
- **PUT** `/entidade/{id}` - Atualizar existente
- **DELETE** `/entidade/{id}` - Remover

**Total: 20 endpoints (5 por entidade)**
