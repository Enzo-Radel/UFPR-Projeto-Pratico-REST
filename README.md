# Sistema de Biblioteca - API REST

Este projeto implementa uma API REST para um sistema de biblioteca usando Java 24 com Spring Boot e MySQL.

## 🐳 Configuração do Ambiente com Docker

### Pré-requisitos
- Docker
- Docker Compose

### 🚀 Como Executar

1. **Iniciar os containers:**
```bash
docker-compose up -d
```

2. **Verificar se os containers estão rodando:**
```bash
docker-compose ps
```

3. **Acessar os serviços:**
   - **MySQL**: `localhost:3306`
   - **phpMyAdmin**: `http://localhost:8081`
   - **Java Dev Container**: `localhost:8080` (quando a aplicação estiver rodando)

### 📊 Configurações do Banco de Dados

- **Host**: `localhost` (ou `mysql` dentro da rede Docker)
- **Porta**: `3306`
- **Database**: `biblioteca`
- **Usuário**: `biblioteca_user`
- **Senha**: `biblioteca123`
- **Root Password**: `root123`

### 🔧 Comandos Úteis

**Parar os containers:**
```bash
docker-compose down
```

**Parar e remover volumes:**
```bash
docker-compose down -v
```

**Ver logs dos containers:**
```bash
docker-compose logs -f
```

**Acessar o container Java:**
```bash
docker exec -it biblioteca-java-dev bash
```

**Acessar o MySQL:**
```bash
docker exec -it biblioteca-mysql mysql -u biblioteca_user -p biblioteca
```

### 📁 Estrutura do Projeto

```
atividade-rest/
├── docker-compose.yml      # Configuração dos containers
├── init.sql               # Script de inicialização do banco
├── README.md              # Este arquivo
└── src/                   # Código fonte da aplicação (será criado)
```

### 🗄️ Estrutura do Banco de Dados

O banco de dados será criado automaticamente com as seguintes tabelas:

- **autores**: Informações dos autores
- **usuarios**: Cadastro de usuários
- **livros**: Catálogo de livros
- **emprestimos**: Registro de empréstimos

### 🔍 Verificação do Ambiente

1. **Verificar se o MySQL está funcionando:**
```bash
docker exec -it biblioteca-mysql mysql -u root -proot123 -e "SHOW DATABASES;"
```

2. **Verificar se as tabelas foram criadas:**
```bash
docker exec -it biblioteca-mysql mysql -u biblioteca_user -pbiblioteca123 -e "USE biblioteca; SHOW TABLES;"
```

3. **Acessar o phpMyAdmin:**
   - Abra `http://localhost:8081` no navegador
   - Login: `root` / `root123`

### 🐛 Debug Remoto

O container Java está configurado para aceitar conexões de debug remoto na porta `5005`. Para conectar:

- **Host**: `localhost`
- **Porta**: `5005`

### 📝 Próximos Passos

1. ✅ Configurar containers Docker
2. 🔄 Criar projeto Spring Boot
3. 🔄 Implementar arquitetura Controller → Service → Repository
4. 🔄 Desenvolver endpoints REST
5. 🔄 Implementar HATEOAS
6. 🔄 Adicionar testes
7. 🔄 Documentar API

---

## 🆘 Solução de Problemas

### Container não inicia
```bash
docker-compose logs [nome-do-container]
```

### Problemas de conexão com MySQL
```bash
docker-compose restart mysql
```

### Limpar tudo e recomeçar
```bash
docker-compose down -v
docker-compose up -d
```
