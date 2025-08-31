# 📚 API de Biblioteca - Mapeamento Completo de Rotas (Simplificado)

## 🌐 Base URL
```
http://localhost:8080/api
```

## 📋 Índice
- [Usuários](#usuários)
- [Autores](#autores)
- [Livros](#livros)
- [Empréstimos](#empréstimos)

---

## 👥 Usuários

### **GET** `/usuarios`
**Descrição:** Lista todos os usuários
**Parâmetros:** Nenhum
**Resposta:** Lista de usuários

### **GET** `/usuarios/{id}`
**Descrição:** Busca usuário por ID
**Parâmetros:**
- `id` (string): ID do usuário

### **POST** `/usuarios`
**Descrição:** Cria um novo usuário
**Body:**
```json
{
  "nome": "João Silva",
  "email": "joao@email.com",
  "telefone": "(11) 99999-9999"
}
```

### **PUT** `/usuarios/{id}`
**Descrição:** Atualiza um usuário existente
**Parâmetros:**
- `id` (string): ID do usuário
**Body:** Mesmo formato do POST

### **DELETE** `/usuarios/{id}`
**Descrição:** Remove um usuário
**Parâmetros:**
- `id` (string): ID do usuário

---

## ✍️ Autores

### **GET** `/autores`
**Descrição:** Lista todos os autores

### **GET** `/autores/{id}`
**Descrição:** Busca autor por ID

### **POST** `/autores`
**Descrição:** Cria um novo autor
**Body:**
```json
{
  "nome": "Machado de Assis",
  "biografia": "Escritor brasileiro do século XIX"
}
```

### **PUT** `/autores/{id}`
**Descrição:** Atualiza um autor existente

### **DELETE** `/autores/{id}`
**Descrição:** Remove um autor

---

## 📖 Livros

### **GET** `/livros`
**Descrição:** Lista todos os livros

### **GET** `/livros/{id}`
**Descrição:** Busca livro por ID

### **POST** `/livros`
**Descrição:** Cria um novo livro
**Body:**
```json
{
  "titulo": "Dom Casmurro",
  "isbn": "9788535902778",
  "anoPublicacao": 1899,
  "disponivel": true,
  "autorId": "uuid-do-autor"
}
```

### **PUT** `/livros/{id}`
**Descrição:** Atualiza um livro existente

### **DELETE** `/livros/{id}`
**Descrição:** Remove um livro

---

## 📚 Empréstimos

### **GET** `/emprestimos`
**Descrição:** Lista todos os empréstimos

### **GET** `/emprestimos/{id}`
**Descrição:** Busca empréstimo por ID

### **POST** `/emprestimos`
**Descrição:** Cria um novo empréstimo
**Body:**
```json
{
  "livroId": "uuid-do-livro",
  "usuarioId": "uuid-do-usuario",
  "dataDevolucaoPrevista": "2024-12-31T23:59:59"
}
```

### **PUT** `/emprestimos/{id}`
**Descrição:** Atualiza um empréstimo existente (devolve o livro)

### **DELETE** `/emprestimos/{id}`
**Descrição:** Remove um empréstimo

---

## 📊 Resumo de Endpoints

| **Módulo** | **Endpoints** | **Funcionalidades** |
|------------|---------------|---------------------|
| **👥 Usuários** | 5 | CRUD básico |
| **✍️ Autores** | 5 | CRUD básico |
| **📖 Livros** | 5 | CRUD básico |
| **📚 Empréstimos** | 5 | CRUD básico |
| **🎯 TOTAL** | **20 endpoints** | **Sistema simplificado de biblioteca** |

## 🔧 Como usar a Collection do Postman

1. **Importe o arquivo:** `Biblioteca_API_Collection.json`
2. **Configure a variável:** `base_url` para `http://localhost:8080/api`
3. **Substitua os placeholders:** `{id}`, `{autorId}`, `{livroId}`, `{usuarioId}`
4. **Teste os endpoints:** Comece pelos GET para listar e depois POST para criar

## 📝 Observações

- **CRUD Completo:** Cada entidade tem as 5 operações básicas (Create, Read, Update, Delete)
- **Validação:** Todos os endpoints POST/PUT têm validação Bean Validation
- **CORS:** API configurada para aceitar requisições de qualquer origem
- **JSON:** Todas as respostas são em formato JSON
- **Status Codes:** Uso apropriado dos códigos HTTP (200, 201, 404, etc.)

## 🚀 Fluxo de Teste Recomendado

1. **Criar Autor:** `POST /autores`
2. **Criar Usuário:** `POST /usuarios`
3. **Criar Livro:** `POST /livros` (usando o ID do autor criado)
4. **Criar Empréstimo:** `POST /emprestimos` (usando IDs do livro e usuário)
5. **Listar todos:** `GET /usuarios`, `GET /autores`, `GET /livros`, `GET /emprestimos`
6. **Buscar por ID:** `GET /usuarios/{id}`, etc.
7. **Atualizar:** `PUT /usuarios/{id}`, etc.
8. **Deletar:** `DELETE /usuarios/{id}`, etc.
