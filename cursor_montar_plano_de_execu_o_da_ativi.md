# Montar plano de execução da atividade
_Exported on 30/08/2025 at 8:15:10 BRT from Cursor (1.4.3)_

---

**User**

Esse arquivo contem os requisitos funcionais e não funcionais de uma atividade que preciso fazer.

Gostaria que você lesse atentamente e me ajudasse a montar um plano de como executar ela

---

**Cursor**

Vou analisar o arquivo para entender os requisitos da atividade e ajudá-lo a criar um plano de execução.


O arquivo é um documento Word (.docx) que não pode ser lido diretamente como texto. Vou tentar extrair o conteúdo usando uma busca para encontrar informações sobre os requisitos.


Como o arquivo é um documento Word binário, não consigo extrair diretamente o conteúdo. No entanto, posso ajudá-lo a criar um plano estruturado baseado em boas práticas para desenvolvimento de software. 

Para que eu possa ajudá-lo melhor, você poderia:

1. **Copiar e colar o conteúdo** do documento Word aqui, ou
2. **Converter o arquivo** para formato de texto (.txt) ou PDF, ou
3. **Descrever os requisitos** principais que você precisa implementar

Enquanto isso, posso sugerir um **plano geral de execução** que você pode adaptar aos seus requisitos específicos:

## 📋 Plano Geral de Execução de Atividade de Desenvolvimento

### **Fase 1: Análise e Planejamento**
- [ ] Leitura e compreensão dos requisitos funcionais
- [ ] Análise dos requisitos não funcionais
- [ ] Definição da arquitetura do sistema
- [ ] Escolha das tecnologias
- [ ] Cronograma de desenvolvimento

### **Fase 2: Design e Modelagem**
- [ ] Modelagem de dados (se aplicável)
- [ ] Design da interface do usuário
- [ ] Definição da estrutura do código
- [ ] Criação de diagramas (se necessário)

### **Fase 3: Implementação**
- [ ] Configuração do ambiente de desenvolvimento
- [ ] Implementação das funcionalidades principais
- [ ] Implementação das funcionalidades secundárias
- [ ] Integração dos componentes

### **Fase 4: Testes e Validação**
- [ ] Testes unitários
- [ ] Testes de integração
- [ ] Testes de usabilidade
- [ ] Validação dos requisitos não funcionais

### **Fase 5: Documentação e Entrega**
- [ ] Documentação do código
- [ ] Manual do usuário (se necessário)
- [ ] Documentação técnica
- [ ] Preparação para entrega

**Você poderia compartilhar os requisitos específicos da sua atividade para que eu possa criar um plano mais detalhado e personalizado?**

---

**User**

@atividade2.pdf aqui está a versão pdf

---

**Cursor**

Perfeito! Agora vou ler o arquivo PDF para entender os requisitos da atividade e ajudá-lo a criar um plano detalhado.


O arquivo PDF contém informações sobre requisitos funcionais e não funcionais de uma atividade. Vou analisar o conteúdo para extrair os requisitos específicos.


Vou tentar extrair o texto legível do PDF para entender os requisitos:


Como o arquivo PDF está em formato binário e não consigo extrair diretamente o conteúdo textual, vou criar um plano de execução baseado em boas práticas para desenvolvimento de software. Você poderia me fornecer os requisitos específicos da atividade?

## 📋 Plano de Execução para Atividade de Desenvolvimento

### **Fase 1: Análise e Compreensão dos Requisitos**
- [ ] **Leitura detalhada** dos requisitos funcionais
- [ ] **Análise** dos requisitos não funcionais
- [ ] **Identificação** de dependências e restrições
- [ ] **Clarificação** de dúvidas com o professor

### **Fase 2: Planejamento e Arquitetura**
- [ ] **Definição da arquitetura** do sistema
- [ ] **Escolha das tecnologias** adequadas
- [ ] **Modelagem de dados** (se aplicável)
- [ ] **Design da interface** do usuário
- [ ] **Cronograma detalhado** de desenvolvimento

### **Fase 3: Implementação Estruturada**
- [ ] **Configuração do ambiente** de desenvolvimento
- [ ] **Implementação incremental** das funcionalidades
- [ ] **Desenvolvimento de testes** unitários
- [ ] **Integração contínua** dos componentes

### **Fase 4: Testes e Validação**
- [ ] **Testes unitários** para cada componente
- [ ] **Testes de integração** entre módulos
- [ ] **Testes de usabilidade** e interface
- [ ] **Validação** dos requisitos não funcionais
- [ ] **Correção de bugs** identificados

### **Fase 5: Documentação e Entrega**
- [ ] **Documentação técnica** do código
- [ ] **Manual do usuário** (se necessário)
- [ ] **Documentação de instalação** e configuração
- [ ] **Preparação da apresentação** (se solicitada)
- [ ] **Entrega final** da atividade

### **Ferramentas Recomendadas:**
- **Controle de versão**: Git
- **IDE**: VS Code, IntelliJ, ou similar
- **Documentação**: Markdown, Javadoc, ou similar
- **Testes**: Framework de testes da linguagem escolhida

### **Boas Práticas:**
- ✅ **Código limpo** e bem documentado
- ✅ **Interface intuitiva** e responsiva
- ✅ **Testes abrangentes** para garantir qualidade
- ✅ **Documentação clara** e completa
- ✅ **Cumprimento dos prazos** estabelecidos

**Você poderia compartilhar os requisitos específicos da sua atividade para que eu possa criar um plano mais detalhado e personalizado?**

---

**User**

@atividade2.md aqui está uma versão em markdown do documento

---

**Cursor**

Perfeito! Agora posso ver claramente os requisitos da atividade. Vou criar um plano detalhado de execução para o **Sistema de Biblioteca com Arquitetura REST - Microsserviços**.

## 📋 Plano de Execução Detalhado

### **Fase 1: Análise e Design da API (1-2 dias)**

#### **1.1 Identificação de Recursos e URIs**
- [ ] **Definir estrutura de recursos:**
  - Livros: `/livros` e `/livros/{id}`
  - Autores: `/autores` e `/autores/{id}`
  - Empréstimos: `/emprestimos` e `/emprestimos/{id}`
  - Usuários: `/usuarios` e `/usuarios/{id}`

#### **1.2 Mapeamento HTTP Completo**
- [ ] **Criar tabela de mapeamento** para todos os recursos:
  - GET (listar/obter)
  - POST (criar)
  - PUT (atualizar)
  - DELETE (remover)

#### **1.3 Estrutura JSON**
- [ ] **Definir schemas JSON** para cada recurso
- [ ] **Criar exemplos** de requisições e respostas
- [ ] **Documentar relacionamentos** entre recursos

#### **1.4 Códigos de Status**
- [ ] **Mapear códigos HTTP** para cada operação
- [ ] **Definir tratamento de erros** específicos

### **Fase 2: Configuração do Ambiente (0.5 dia)**

#### **2.1 Setup do Projeto**
- [ ] **Escolher tecnologia** (recomendo Node.js/Express ou Spring Boot)
- [ ] **Configurar estrutura** de pastas
- [ ] **Instalar dependências** necessárias
- [ ] **Configurar banco de dados** (SQLite para simplicidade)

#### **2.2 Estrutura de Pastas Sugerida**
```
biblioteca-api/
├── src/
│   ├── controllers/
│   ├── models/
│   ├── routes/
│   ├── middleware/
│   ├── utils/
│   └── app.js
├── tests/
├── docs/
├── package.json
└── README.md
```

### **Fase 3: Implementação Básica (3-4 dias)**

#### **3.1 CRUD de Livros (Dia 1)**
- [ ] **Modelo de dados** para Livro
- [ ] **Controller** com operações CRUD
- [ ] **Rotas** para todos os endpoints
- [ ] **Validação** de entrada
- [ ] **Testes básicos**

#### **3.2 CRUD de Autores (Dia 2)**
- [ ] **Modelo de dados** para Autor
- [ ] **Controller** com operações CRUD
- [ ] **Relacionamento** com Livros
- [ ] **Validação** e testes

#### **3.3 CRUD de Usuários (Dia 3)**
- [ ] **Modelo de dados** para Usuário
- [ ] **Controller** com operações CRUD
- [ ] **Validação** e testes

#### **3.4 CRUD de Empréstimos (Dia 4)**
- [ ] **Modelo de dados** para Empréstimo
- [ ] **Controller** com operações CRUD
- [ ] **Lógica de negócio** (disponibilidade de livros)
- [ ] **Relacionamentos** com Livros e Usuários

### **Fase 4: Funcionalidades Avançadas (2-3 dias)**

#### **4.1 Query Parameters (Dia 1)**
- [ ] **Filtros** por autor, disponibilidade
- [ ] **Paginação** (page, size)
- [ ] **Ordenação** (sort, order)

#### **4.2 Tratamento de Erros (Dia 2)**
- [ ] **Middleware** de tratamento de erros
- [ ] **Códigos de status** apropriados
- [ ] **Mensagens** descritivas em JSON
- [ ] **Validação** robusta

#### **4.3 HATEOAS (Dia 3)**
- [ ] **Links básicos** (self, update, delete)
- [ ] **Links contextuais** (emprestar, devolver)
- [ ] **Middleware** para adicionar links automaticamente

### **Fase 5: Testes e Validação (2 dias)**

#### **5.1 Testes Unitários**
- [ ] **Testes** para cada controller
- [ ] **Testes** de validação
- [ ] **Testes** de lógica de negócio

#### **5.2 Testes de Integração**
- [ ] **Testes** de endpoints completos
- [ ] **Testes** de fluxos de empréstimo
- [ ] **Testes** de HATEOAS

#### **5.3 Testes com Postman/Insomnia**
- [ ] **Coleção** de testes
- [ ] **Validação** de respostas JSON
- [ ] **Testes** de códigos de status

### **Fase 6: Documentação (1-2 dias)**

#### **6.1 Documentação da API**
- [ ] **README.md** detalhado
- [ ] **Exemplos** de uso
- [ ] **Códigos de erro**
- [ ] **Guia** de HATEOAS

#### **6.2 Relatório de Design**
- [ ] **Decisões** arquiteturais
- [ ] **Justificativas** técnicas
- [ ] **Alternativas** consideradas

### **Fase 7: Finalização (0.5 dia)**

#### **7.1 Preparação para Entrega**
- [ ] **Revisão** final do código
- [ ] **Testes** finais
- [ ] **Organização** do repositório
- [ ] **Commit** final

---

## ��️ Stack Tecnológica Recomendada

### **Opção 1: Node.js/Express**
```bash
npm init -y
npm install express cors helmet morgan
npm install --save-dev jest supertest
```

### **Opção 2: Spring Boot (Java)**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

### **Opção 3: Python/Flask**
```bash
pip install flask flask-restful
pip install pytest
```

---

## 📅 Cronograma Sugerido

| Dia | Atividade | Entregável |
|-----|-----------|------------|
| 1 | Análise e Design | Documentos de design |
| 2 | Setup + CRUD Livros | API básica funcionando |
| 3 | CRUD Autores + Usuários | Mais recursos implementados |
| 4 | CRUD Empréstimos | Lógica de negócio |
| 5 | Query Parameters | Funcionalidades avançadas |
| 6 | Tratamento de Erros | API robusta |
| 7 | HATEOAS | Nível 3 Richardson |
| 8 | Testes | Cobertura completa |
| 9 | Documentação | README + relatório |
| 10 | Finalização | Entrega |

---

## �� Pontos Críticos de Atenção

### **1. HATEOAS (Nível 3 Richardson)**
- Implementar links contextuais baseados no estado
- Links diferentes para livros disponíveis vs. emprestados

### **2. Códigos de Status HTTP**
- Usar 201 para criação, 200 para sucesso, 404 para não encontrado
- 409 para conflitos (livro já emprestado)

### **3. Validação de Dados**
- Validar entrada em todos os endpoints
- Retornar erros descritivos

### **4. Testes Abrangentes**
- Testar todos os cenários de erro
- Validar formato JSON das respostas

---

## �� Checklist de Entrega

- [ ] **Código-fonte** completo e funcional
- [ ] **Documentação** da API (README.md)
- [ ] **Coleção de testes** (Postman/Insomnia)
- [ ] **Relatório** de design (2 páginas)
- [ ] **Instruções** de instalação e execução
- [ ] **Testes** passando
- [ ] **HATEOAS** implementado
- [ ] **Códigos de status** corretos

**Quer que eu detalhe alguma fase específica ou ajude com a implementação de alguma parte?**

```plaintext
biblioteca-api/
├── src/
│   ├── controllers/
│   ├── models/
│   ├── routes/
│   ├── middleware/
│   ├── utils/
│   └── app.js
├── tests/
├── docs/
├── package.json
└── README.md
```

```shellscript
npm init -y
npm install express cors helmet morgan
npm install --save-dev jest supertest
```

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

```shellscript
pip install flask flask-restful
pip install pytest
```

---

**User**

Com relação ao design e arquitetura, quero implementar uma arquitetura com controllers, services e repositories, evitando de aumentar a complexidade além disso.

Levando isso em consideração, monte a arquitetura do sistema, com os arquivos necessários.

---

**Cursor**

Perfeito! Vou criar uma arquitetura limpa e organizada seguindo o padrão **Controller → Service → Repository**. Vou usar **Node.js/Express** como exemplo, mas a estrutura pode ser adaptada para outras tecnologias.

## 🏗️ Arquitetura do Sistema

### **Estrutura de Pastas**

```
biblioteca-api/
├── src/
│   ├── controllers/          # Controladores REST
│   ├── services/            # Lógica de negócio
│   ├── repositories/        # Acesso a dados
│   ├── models/              # Modelos de dados
│   ├── routes/              # Definição de rotas
│   ├── middleware/          # Middlewares customizados
│   ├── utils/               # Utilitários e helpers
│   ├── config/              # Configurações
│   └── app.js               # Aplicação principal
├── tests/
│   ├── unit/
│   ├── integration/
│   └── fixtures/
├── docs/
├── package.json
└── README.md
```

---

## 📁 Estrutura Detalhada dos Arquivos

### **1. Configuração Base**

#### **`package.json`**
```json
{
  "name": "biblioteca-api",
  "version": "1.0.0",
  "description": "Sistema de Biblioteca REST API",
  "main": "src/app.js",
  "scripts": {
    "start": "node src/app.js",
    "dev": "nodemon src/app.js",
    "test": "jest",
    "test:watch": "jest --watch"
  },
  "dependencies": {
    "express": "^4.18.2",
    "cors": "^2.8.5",
    "helmet": "^7.0.0",
    "morgan": "^1.10.0",
    "sqlite3": "^5.1.6",
    "uuid": "^9.0.0"
  },
  "devDependencies": {
    "jest": "^29.5.0",
    "supertest": "^6.3.3",
    "nodemon": "^3.0.1"
  }
}
```

#### **`src/config/database.js`**
```javascript
const sqlite3 = require('sqlite3').verbose();
const path = require('path');

class Database {
    constructor() {
        this.db = new sqlite3.Database(path.join(__dirname, '../../data/biblioteca.db'));
        this.init();
    }

    init() {
        this.db.serialize(() => {
            // Tabela de Autores
            this.db.run(`
                CREATE TABLE IF NOT EXISTS autores (
                    id TEXT PRIMARY KEY,
                    nome TEXT NOT NULL,
                    biografia TEXT,
                    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
                )
            `);

            // Tabela de Livros
            this.db.run(`
                CREATE TABLE IF NOT EXISTS livros (
                    id TEXT PRIMARY KEY,
                    titulo TEXT NOT NULL,
                    isbn TEXT UNIQUE,
                    ano_publicacao INTEGER,
                    disponivel BOOLEAN DEFAULT true,
                    autor_id TEXT,
                    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (autor_id) REFERENCES autores (id)
                )
            `);

            // Tabela de Usuários
            this.db.run(`
                CREATE TABLE IF NOT EXISTS usuarios (
                    id TEXT PRIMARY KEY,
                    nome TEXT NOT NULL,
                    email TEXT UNIQUE,
                    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
                )
            `);

            // Tabela de Empréstimos
            this.db.run(`
                CREATE TABLE IF NOT EXISTS emprestimos (
                    id TEXT PRIMARY KEY,
                    livro_id TEXT NOT NULL,
                    usuario_id TEXT NOT NULL,
                    data_emprestimo DATETIME DEFAULT CURRENT_TIMESTAMP,
                    data_devolucao DATETIME,
                    devolvido BOOLEAN DEFAULT false,
                    FOREIGN KEY (livro_id) REFERENCES livros (id),
                    FOREIGN KEY (usuario_id) REFERENCES usuarios (id)
                )
            `);
        });
    }

    getConnection() {
        return this.db;
    }
}

module.exports = new Database();
```

---

### **2. Modelos de Dados**

#### **`src/models/Livro.js`**
```javascript
class Livro {
    constructor(id, titulo, isbn, anoPublicacao, disponivel, autorId, createdAt) {
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
        this.ano_publicacao = anoPublicacao;
        this.disponivel = disponivel;
        this.autor_id = autorId;
        this.created_at = createdAt;
    }

    toJSON() {
        return {
            id: this.id,
            titulo: this.titulo,
            isbn: this.isbn,
            ano_publicacao: this.ano_publicacao,
            disponivel: this.disponivel,
            autor_id: this.autor_id,
            created_at: this.created_at
        };
    }

    static fromRow(row) {
        return new Livro(
            row.id,
            row.titulo,
            row.isbn,
            row.ano_publicacao,
            row.disponivel === 1,
            row.autor_id,
            row.created_at
        );
    }
}

module.exports = Livro;
```

#### **`src/models/Autor.js`**
```javascript
class Autor {
    constructor(id, nome, biografia, createdAt) {
        this.id = id;
        this.nome = nome;
        this.biografia = biografia;
        this.created_at = createdAt;
    }

    toJSON() {
        return {
            id: this.id,
            nome: this.nome,
            biografia: this.biografia,
            created_at: this.created_at
        };
    }

    static fromRow(row) {
        return new Autor(
            row.id,
            row.nome,
            row.biografia,
            row.created_at
        );
    }
}

module.exports = Autor;
```

---

### **3. Repositories (Acesso a Dados)**

#### **`src/repositories/LivroRepository.js`**
```javascript
const { v4: uuidv4 } = require('uuid');
const database = require('../config/database');
const Livro = require('../models/Livro');

class LivroRepository {
    constructor() {
        this.db = database.getConnection();
    }

    async findAll(options = {}) {
        return new Promise((resolve, reject) => {
            let query = `
                SELECT l.*, a.nome as autor_nome 
                FROM livros l 
                LEFT JOIN autores a ON l.autor_id = a.id
                WHERE 1=1
            `;
            const params = [];

            // Filtros
            if (options.autor) {
                query += ` AND a.nome LIKE ?`;
                params.push(`%${options.autor}%`);
            }

            if (options.disponivel !== undefined) {
                query += ` AND l.disponivel = ?`;
                params.push(options.disponivel ? 1 : 0);
            }

            // Ordenação
            if (options.sort) {
                query += ` ORDER BY l.${options.sort} ${options.order || 'ASC'}`;
            }

            // Paginação
            if (options.page && options.size) {
                const offset = (options.page - 1) * options.size;
                query += ` LIMIT ? OFFSET ?`;
                params.push(parseInt(options.size), offset);
            }

            this.db.all(query, params, (err, rows) => {
                if (err) reject(err);
                else resolve(rows.map(row => ({
                    ...Livro.fromRow(row).toJSON(),
                    autor: row.autor_nome ? { id: row.autor_id, nome: row.autor_nome } : null
                })));
            });
        });
    }

    async findById(id) {
        return new Promise((resolve, reject) => {
            const query = `
                SELECT l.*, a.nome as autor_nome 
                FROM livros l 
                LEFT JOIN autores a ON l.autor_id = a.id 
                WHERE l.id = ?
            `;
            
            this.db.get(query, [id], (err, row) => {
                if (err) reject(err);
                else if (!row) resolve(null);
                else resolve({
                    ...Livro.fromRow(row).toJSON(),
                    autor: row.autor_nome ? { id: row.autor_id, nome: row.autor_nome } : null
                });
            });
        });
    }

    async create(livroData) {
        return new Promise((resolve, reject) => {
            const id = uuidv4();
            const query = `
                INSERT INTO livros (id, titulo, isbn, ano_publicacao, disponivel, autor_id)
                VALUES (?, ?, ?, ?, ?, ?)
            `;
            
            this.db.run(query, [
                id,
                livroData.titulo,
                livroData.isbn,
                livroData.ano_publicacao,
                livroData.disponivel !== false,
                livroData.autor_id
            ], function(err) {
                if (err) reject(err);
                else resolve({ id, ...livroData });
            });
        });
    }

    async update(id, livroData) {
        return new Promise((resolve, reject) => {
            const query = `
                UPDATE livros 
                SET titulo = ?, isbn = ?, ano_publicacao = ?, disponivel = ?, autor_id = ?
                WHERE id = ?
            `;
            
            this.db.run(query, [
                livroData.titulo,
                livroData.isbn,
                livroData.ano_publicacao,
                livroData.disponivel !== false,
                livroData.autor_id,
                id
            ], function(err) {
                if (err) reject(err);
                else resolve({ id, ...livroData });
            });
        });
    }

    async delete(id) {
        return new Promise((resolve, reject) => {
            this.db.run('DELETE FROM livros WHERE id = ?', [id], function(err) {
                if (err) reject(err);
                else resolve(this.changes > 0);
            });
        });
    }

    async updateDisponibilidade(id, disponivel) {
        return new Promise((resolve, reject) => {
            this.db.run(
                'UPDATE livros SET disponivel = ? WHERE id = ?',
                [disponivel ? 1 : 0, id],
                function(err) {
                    if (err) reject(err);
                    else resolve(this.changes > 0);
                }
            );
        });
    }
}

module.exports = LivroRepository;
```

#### **`src/repositories/AutorRepository.js`**
```javascript
const { v4: uuidv4 } = require('uuid');
const database = require('../config/database');
const Autor = require('../models/Autor');

class AutorRepository {
    constructor() {
        this.db = database.getConnection();
    }

    async findAll() {
        return new Promise((resolve, reject) => {
            this.db.all('SELECT * FROM autores ORDER BY nome', (err, rows) => {
                if (err) reject(err);
                else resolve(rows.map(row => Autor.fromRow(row).toJSON()));
            });
        });
    }

    async findById(id) {
        return new Promise((resolve, reject) => {
            this.db.get('SELECT * FROM autores WHERE id = ?', [id], (err, row) => {
                if (err) reject(err);
                else if (!row) resolve(null);
                else resolve(Autor.fromRow(row).toJSON());
            });
        });
    }

    async create(autorData) {
        return new Promise((resolve, reject) => {
            const id = uuidv4();
            const query = 'INSERT INTO autores (id, nome, biografia) VALUES (?, ?, ?)';
            
            this.db.run(query, [id, autorData.nome, autorData.biografia], function(err) {
                if (err) reject(err);
                else resolve({ id, ...autorData });
            });
        });
    }

    async update(id, autorData) {
        return new Promise((resolve, reject) => {
            const query = 'UPDATE autores SET nome = ?, biografia = ? WHERE id = ?';
            
            this.db.run(query, [autorData.nome, autorData.biografia, id], function(err) {
                if (err) reject(err);
                else resolve({ id, ...autorData });
            });
        });
    }

    async delete(id) {
        return new Promise((resolve, reject) => {
            this.db.run('DELETE FROM autores WHERE id = ?', [id], function(err) {
                if (err) reject(err);
                else resolve(this.changes > 0);
            });
        });
    }
}

module.exports = AutorRepository;
```

---

### **4. Services (Lógica de Negócio)**

#### **`src/services/LivroService.js`**
```javascript
const LivroRepository = require('../repositories/LivroRepository');

class LivroService {
    constructor() {
        this.livroRepository = new LivroRepository();
    }

    async listarLivros(options = {}) {
        try {
            const livros = await this.livroRepository.findAll(options);
            return this.adicionarLinksHATEOAS(livros);
        } catch (error) {
            throw new Error(`Erro ao listar livros: ${error.message}`);
        }
    }

    async obterLivro(id) {
        try {
            const livro = await this.livroRepository.findById(id);
            if (!livro) {
                throw new Error('Livro não encontrado');
            }
            return this.adicionarLinksHATEOAS([livro])[0];
        } catch (error) {
            throw new Error(`Erro ao obter livro: ${error.message}`);
        }
    }

    async criarLivro(livroData) {
        try {
            this.validarLivro(livroData);
            const livro = await this.livroRepository.create(livroData);
            return this.adicionarLinksHATEOAS([livro])[0];
        } catch (error) {
            throw new Error(`Erro ao criar livro: ${error.message}`);
        }
    }

    async atualizarLivro(id, livroData) {
        try {
            this.validarLivro(livroData);
            const livroExistente = await this.livroRepository.findById(id);
            if (!livroExistente) {
                throw new Error('Livro não encontrado');
            }
            
            const livro = await this.livroRepository.update(id, livroData);
            return this.adicionarLinksHATEOAS([livro])[0];
        } catch (error) {
            throw new Error(`Erro ao atualizar livro: ${error.message}`);
        }
    }

    async removerLivro(id) {
        try {
            const livroExistente = await this.livroRepository.findById(id);
            if (!livroExistente) {
                throw new Error('Livro não encontrado');
            }
            
            const removido = await this.livroRepository.delete(id);
            if (!removido) {
                throw new Error('Erro ao remover livro');
            }
            return { message: 'Livro removido com sucesso' };
        } catch (error) {
            throw new Error(`Erro ao remover livro: ${error.message}`);
        }
    }

    validarLivro(livroData) {
        if (!livroData.titulo || livroData.titulo.trim().length === 0) {
            throw new Error('Título é obrigatório');
        }
        
        if (livroData.isbn && !this.validarISBN(livroData.isbn)) {
            throw new Error('ISBN inválido');
        }
        
        if (livroData.ano_publicacao && (livroData.ano_publicacao < 1000 || livroData.ano_publicacao > new Date().getFullYear())) {
            throw new Error('Ano de publicação inválido');
        }
    }

    validarISBN(isbn) {
        // Validação básica de ISBN
        const isbnRegex = /^(?:\d{10}|\d{13})$/;
        return isbnRegex.test(isbn.replace(/[-\s]/g, ''));
    }

    adicionarLinksHATEOAS(livros) {
        return livros.map(livro => {
            const links = [
                { rel: 'self', href: `/livros/${livro.id}`, method: 'GET' },
                { rel: 'update', href: `/livros/${livro.id}`, method: 'PUT' },
                { rel: 'delete', href: `/livros/${livro.id}`, method: 'DELETE' }
            ];

            // Links contextuais baseados no estado
            if (livro.disponivel) {
                links.push({ rel: 'emprestar', href: '/emprestimos', method: 'POST' });
            } else {
                links.push({ rel: 'devolver', href: `/emprestimos/${livro.id}/devolucao`, method: 'PUT' });
            }

            return {
                ...livro,
                _links: links
            };
        });
    }
}

module.exports = LivroService;
```

#### **`src/services/AutorService.js`**
```javascript
const AutorRepository = require('../repositories/AutorRepository');

class AutorService {
    constructor() {
        this.autorRepository = new AutorRepository();
    }

    async listarAutores() {
        try {
            const autores = await this.autorRepository.findAll();
            return this.adicionarLinksHATEOAS(autores);
        } catch (error) {
            throw new Error(`Erro ao listar autores: ${error.message}`);
        }
    }

    async obterAutor(id) {
        try {
            const autor = await this.autorRepository.findById(id);
            if (!autor) {
                throw new Error('Autor não encontrado');
            }
            return this.adicionarLinksHATEOAS([autor])[0];
        } catch (error) {
            throw new Error(`Erro ao obter autor: ${error.message}`);
        }
    }

    async criarAutor(autorData) {
        try {
            this.validarAutor(autorData);
            const autor = await this.autorRepository.create(autorData);
            return this.adicionarLinksHATEOAS([autor])[0];
        } catch (error) {
            throw new Error(`Erro ao criar autor: ${error.message}`);
        }
    }

    async atualizarAutor(id, autorData) {
        try {
            this.validarAutor(autorData);
            const autorExistente = await this.autorRepository.findById(id);
            if (!autorExistente) {
                throw new Error('Autor não encontrado');
            }
            
            const autor = await this.autorRepository.update(id, autorData);
            return this.adicionarLinksHATEOAS([autor])[0];
        } catch (error) {
            throw new Error(`Erro ao atualizar autor: ${error.message}`);
        }
    }

    async removerAutor(id) {
        try {
            const autorExistente = await this.autorRepository.findById(id);
            if (!autorExistente) {
                throw new Error('Autor não encontrado');
            }
            
            const removido = await this.autorRepository.delete(id);
            if (!removido) {
                throw new Error('Erro ao remover autor');
            }
            return { message: 'Autor removido com sucesso' };
        } catch (error) {
            throw new Error(`Erro ao remover autor: ${error.message}`);
        }
    }

    validarAutor(autorData) {
        if (!autorData.nome || autorData.nome.trim().length === 0) {
            throw new Error('Nome é obrigatório');
        }
        
        if (autorData.nome.trim().length < 2) {
            throw new Error('Nome deve ter pelo menos 2 caracteres');
        }
    }

    adicionarLinksHATEOAS(autores) {
        return autores.map(autor => ({
            ...autor,
            _links: [
                { rel: 'self', href: `/autores/${autor.id}`, method: 'GET' },
                { rel: 'update', href: `/autores/${autor.id}`, method: 'PUT' },
                { rel: 'delete', href: `/autores/${autor.id}`, method: 'DELETE' },
                { rel: 'livros', href: `/livros?autor_id=${autor.id}`, method: 'GET' }
            ]
        }));
    }
}

module.exports = AutorService;
```

---

### **5. Controllers (Camada REST)**

#### **`src/controllers/LivroController.js`**
```javascript
const LivroService = require('../services/LivroService');

class LivroController {
    constructor() {
        this.livroService = new LivroService();
    }

    async listarLivros(req, res) {
        try {
            const options = {
                autor: req.query.autor,
                disponivel: req.query.disponivel === 'true',
                sort: req.query.sort,
                order: req.query.order,
                page: parseInt(req.query.page) || 1,
                size: parseInt(req.query.size) || 10
            };

            const livros = await this.livroService.listarLivros(options);
            
            res.status(200).json({
                data: livros,
                pagination: {
                    page: options.page,
                    size: options.size,
                    total: livros.length
                }
            });
        } catch (error) {
            res.status(500).json({
                erro: error.message,
                codigo: 500,
                timestamp: new Date().toISOString(),
                caminho: req.path
            });
        }
    }

    async obterLivro(req, res) {
        try {
            const { id } = req.params;
            const livro = await this.livroService.obterLivro(id);
            
            res.status(200).json(livro);
        } catch (error) {
            if (error.message.includes('não encontrado')) {
                res.status(404).json({
                    erro: error.message,
                    codigo: 404,
                    timestamp: new Date().toISOString(),
                    caminho: req.path
                });
            } else {
                res.status(500).json({
                    erro: error.message,
                    codigo: 500,
                    timestamp: new Date().toISOString(),
                    caminho: req.path
                });
            }
        }
    }

    async criarLivro(req, res) {
        try {
            const livro = await this.livroService.criarLivro(req.body);
            
            res.status(201).json(livro);
        } catch (error) {
            res.status(400).json({
                erro: error.message,
                codigo: 400,
                timestamp: new Date().toISOString(),
                caminho: req.path
            });
        }
    }

    async atualizarLivro(req, res) {
        try {
            const { id } = req.params;
            const livro = await this.livroService.atualizarLivro(id, req.body);
            
            res.status(200).json(livro);
        } catch (error) {
            if (error.message.includes('não encontrado')) {
                res.status(404).json({
                    erro: error.message,
                    codigo: 404,
                    timestamp: new Date().toISOString(),
                    caminho: req.path
                });
            } else {
                res.status(400).json({
                    erro: error.message,
                    codigo: 400,
                    timestamp: new Date().toISOString(),
                    caminho: req.path
                });
            }
        }
    }

    async removerLivro(req, res) {
        try {
            const { id } = req.params;
            const resultado = await this.livroService.removerLivro(id);
            
            res.status(200).json(resultado);
        } catch (error) {
            if (error.message.includes('não encontrado')) {
                res.status(404).json({
                    erro: error.message,
                    codigo: 404,
                    timestamp: new Date().toISOString(),
                    caminho: req.path
                });
            } else {
                res.status(500).json({
                    erro: error.message,
                    codigo: 500,
                    timestamp: new Date().toISOString(),
                    caminho: req.path
                });
            }
        }
    }
}

module.exports = LivroController;
```

---

### **6. Rotas**

#### **`src/routes/livros.js`**
```javascript
const express = require('express');
const LivroController = require('../controllers/LivroController');

const router = express.Router();
const livroController = new LivroController();

// GET /livros - Listar todos os livros
router.get('/', (req, res) => livroController.listarLivros(req, res));

// GET /livros/:id - Obter um livro específico
router.get('/:id', (req, res) => livroController.obterLivro(req, res));

// POST /livros - Criar um novo livro
router.post('/', (req, res) => livroController.criarLivro(req, res));

// PUT /livros/:id - Atualizar um livro
router.put('/:id', (req, res) => livroController.atualizarLivro(req, res));

// DELETE /livros/:id - Remover um livro
router.delete('/:id', (req, res) => livroController.removerLivro(req, res));

module.exports = router;
```

---

### **7. Aplicação Principal**

#### **`src/app.js`**
```javascript
const express = require('express');
const cors = require('cors');
const helmet = require('helmet');
const morgan = require('morgan');

// Importar rotas
const livrosRoutes = require('./routes/livros');
const autoresRoutes = require('./routes/autores');
const usuariosRoutes = require('./routes/usuarios');
const emprestimosRoutes = require('./routes/emprestimos');

// Middleware de tratamento de erros
const errorHandler = require('./middleware/errorHandler');

const app = express();
const PORT = process.env.PORT || 3000;

// Middlewares de segurança e logging
app.use(helmet());
app.use(cors());
app.use(morgan('combined'));

// Middleware para parsing JSON
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

// Rotas
app.use('/livros', livrosRoutes);
app.use('/autores', autoresRoutes);
app.use('/usuarios', usuariosRoutes);
app.use('/emprestimos', emprestimosRoutes);

// Rota raiz
app.get('/', (req, res) => {
    res.json({
        message: 'API de Biblioteca - Sistema REST',
        version: '1.0.0',
        endpoints: {
            livros: '/livros',
            autores: '/autores',
            usuarios: '/usuarios',
            emprestimos: '/emprestimos'
        }
    });
});

// Middleware de tratamento de erros
app.use(errorHandler);

// Rota para recursos não encontrados
app.use('*', (req, res) => {
    res.status(404).json({
        erro: 'Recurso não encontrado',
        codigo: 404,
        timestamp: new Date().toISOString(),
        caminho: req.originalUrl
    });
});

app.listen(PORT, () => {
    console.log(`🚀 Servidor rodando na porta ${PORT}`);
    console.log(`📚 API de Biblioteca disponível em http://localhost:${PORT}`);
});

module.exports = app;
```

---

### **8. Middleware de Tratamento de Erros**

#### **`src/middleware/errorHandler.js`**
```javascript
const errorHandler = (err, req, res, next) => {
    console.error('Erro:', err);

    // Erro de validação
    if (err.name === 'ValidationError') {
        return res.status(400).json({
            erro: 'Dados inválidos',
            detalhes: err.message,
            codigo: 400,
            timestamp: new Date().toISOString(),
            caminho: req.path
        });
    }

    // Erro de sintaxe JSON
    if (err instanceof SyntaxError && err.status === 400 && 'body' in err) {
        return res.status(400).json({
            erro: 'JSON inválido',
            codigo: 400,
            timestamp: new Date().toISOString(),
            caminho: req.path
        });
    }

    // Erro genérico
    res.status(500).json({
        erro: 'Erro interno do servidor',
        codigo: 500,
        timestamp: new Date().toISOString(),
        caminho: req.path
    });
};

module.exports = errorHandler;
```

---

## 🚀 Como Executar

1. **Instalar dependências:**
```bash
npm install
```

2. **Executar em desenvolvimento:**
```bash
npm run dev
```

3. **Executar testes:**
```bash
npm test
```

---

## �� Próximos Passos

1. **Implementar** os repositories e services para Usuários e Empréstimos
2. **Criar** os controllers e rotas correspondentes
3. **Adicionar** validações mais robustas
4. **Implementar** testes unitários e de integração
5. **Adicionar** documentação com Swagger/OpenAPI

**Quer que eu implemente alguma parte específica ou ajude com os próximos passos?**

```plaintext
biblioteca-api/
├── src/
│   ├── controllers/          # Controladores REST
│   ├── services/            # Lógica de negócio
│   ├── repositories/        # Acesso a dados
│   ├── models/              # Modelos de dados
│   ├── routes/              # Definição de rotas
│   ├── middleware/          # Middlewares customizados
│   ├── utils/               # Utilitários e helpers
│   ├── config/              # Configurações
│   └── app.js               # Aplicação principal
├── tests/
│   ├── unit/
│   ├── integration/
│   └── fixtures/
├── docs/
├── package.json
└── README.md
```

```json
{
  "name": "biblioteca-api",
  "version": "1.0.0",
  "description": "Sistema de Biblioteca REST API",
  "main": "src/app.js",
  "scripts": {
    "start": "node src/app.js",
    "dev": "nodemon src/app.js",
    "test": "jest",
    "test:watch": "jest --watch"
  },
  "dependencies": {
    "express": "^4.18.2",
    "cors": "^2.8.5",
    "helmet": "^7.0.0",
    "morgan": "^1.10.0",
    "sqlite3": "^5.1.6",
    "uuid": "^9.0.0"
  },
  "devDependencies": {
    "jest": "^29.5.0",
    "supertest": "^6.3.3",
    "nodemon": "^3.0.1"
  }
}
```

```javascript
const sqlite3 = require('sqlite3').verbose();
const path = require('path');

class Database {
    constructor() {
        this.db = new sqlite3.Database(path.join(__dirname, '../../data/biblioteca.db'));
        this.init();
    }

    init() {
        this.db.serialize(() => {
            // Tabela de Autores
            this.db.run(`
                CREATE TABLE IF NOT EXISTS autores (
                    id TEXT PRIMARY KEY,
                    nome TEXT NOT NULL,
                    biografia TEXT,
                    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
                )
            `);

            // Tabela de Livros
            this.db.run(`
                CREATE TABLE IF NOT EXISTS livros (
                    id TEXT PRIMARY KEY,
                    titulo TEXT NOT NULL,
                    isbn TEXT UNIQUE,
                    ano_publicacao INTEGER,
                    disponivel BOOLEAN DEFAULT true,
                    autor_id TEXT,
                    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (autor_id) REFERENCES autores (id)
                )
            `);

            // Tabela de Usuários
            this.db.run(`
                CREATE TABLE IF NOT EXISTS usuarios (
                    id TEXT PRIMARY KEY,
                    nome TEXT NOT NULL,
                    email TEXT UNIQUE,
                    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
                )
            `);

            // Tabela de Empréstimos
            this.db.run(`
                CREATE TABLE IF NOT EXISTS emprestimos (
                    id TEXT PRIMARY KEY,
                    livro_id TEXT NOT NULL,
                    usuario_id TEXT NOT NULL,
                    data_emprestimo DATETIME DEFAULT CURRENT_TIMESTAMP,
                    data_devolucao DATETIME,
                    devolvido BOOLEAN DEFAULT false,
                    FOREIGN KEY (livro_id) REFERENCES livros (id),
                    FOREIGN KEY (usuario_id) REFERENCES usuarios (id)
                )
            `);
        });
    }

    getConnection() {
        return this.db;
    }
}

module.exports = new Database();
```

```javascript
class Livro {
    constructor(id, titulo, isbn, anoPublicacao, disponivel, autorId, createdAt) {
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
        this.ano_publicacao = anoPublicacao;
        this.disponivel = disponivel;
        this.autor_id = autorId;
        this.created_at = createdAt;
    }

    toJSON() {
        return {
            id: this.id,
            titulo: this.titulo,
            isbn: this.isbn,
            ano_publicacao: this.ano_publicacao,
            disponivel: this.disponivel,
            autor_id: this.autor_id,
            created_at: this.created_at
        };
    }

    static fromRow(row) {
        return new Livro(
            row.id,
            row.titulo,
            row.isbn,
            row.ano_publicacao,
            row.disponivel === 1,
            row.autor_id,
            row.created_at
        );
    }
}

module.exports = Livro;
```

```javascript
class Autor {
    constructor(id, nome, biografia, createdAt) {
        this.id = id;
        this.nome = nome;
        this.biografia = biografia;
        this.created_at = createdAt;
    }

    toJSON() {
        return {
            id: this.id,
            nome: this.nome,
            biografia: this.biografia,
            created_at: this.created_at
        };
    }

    static fromRow(row) {
        return new Autor(
            row.id,
            row.nome,
            row.biografia,
            row.created_at
        );
    }
}

module.exports = Autor;
```

```javascript
const { v4: uuidv4 } = require('uuid');
const database = require('../config/database');
const Livro = require('../models/Livro');

class LivroRepository {
    constructor() {
        this.db = database.getConnection();
    }

    async findAll(options = {}) {
        return new Promise((resolve, reject) => {
            let query = `
                SELECT l.*, a.nome as autor_nome 
                FROM livros l 
                LEFT JOIN autores a ON l.autor_id = a.id
                WHERE 1=1
            `;
            const params = [];

            // Filtros
            if (options.autor) {
                query += ` AND a.nome LIKE ?`;
                params.push(`%${options.autor}%`);
            }

            if (options.disponivel !== undefined) {
                query += ` AND l.disponivel = ?`;
                params.push(options.disponivel ? 1 : 0);
            }

            // Ordenação
            if (options.sort) {
                query += ` ORDER BY l.${options.sort} ${options.order || 'ASC'}`;
            }

            // Paginação
            if (options.page && options.size) {
                const offset = (options.page - 1) * options.size;
                query += ` LIMIT ? OFFSET ?`;
                params.push(parseInt(options.size), offset);
            }

            this.db.all(query, params, (err, rows) => {
                if (err) reject(err);
                else resolve(rows.map(row => ({
                    ...Livro.fromRow(row).toJSON(),
                    autor: row.autor_nome ? { id: row.autor_id, nome: row.autor_nome } : null
                })));
            });
        });
    }

    async findById(id) {
        return new Promise((resolve, reject) => {
            const query = `
                SELECT l.*, a.nome as autor_nome 
                FROM livros l 
                LEFT JOIN autores a ON l.autor_id = a.id 
                WHERE l.id = ?
            `;
            
            this.db.get(query, [id], (err, row) => {
                if (err) reject(err);
                else if (!row) resolve(null);
                else resolve({
                    ...Livro.fromRow(row).toJSON(),
                    autor: row.autor_nome ? { id: row.autor_id, nome: row.autor_nome } : null
                });
            });
        });
    }

    async create(livroData) {
        return new Promise((resolve, reject) => {
            const id = uuidv4();
            const query = `
                INSERT INTO livros (id, titulo, isbn, ano_publicacao, disponivel, autor_id)
                VALUES (?, ?, ?, ?, ?, ?)
            `;
            
            this.db.run(query, [
                id,
                livroData.titulo,
                livroData.isbn,
                livroData.ano_publicacao,
                livroData.disponivel !== false,
                livroData.autor_id
            ], function(err) {
                if (err) reject(err);
                else resolve({ id, ...livroData });
            });
        });
    }

    async update(id, livroData) {
        return new Promise((resolve, reject) => {
            const query = `
                UPDATE livros 
                SET titulo = ?, isbn = ?, ano_publicacao = ?, disponivel = ?, autor_id = ?
                WHERE id = ?
            `;
            
            this.db.run(query, [
                livroData.titulo,
                livroData.isbn,
                livroData.ano_publicacao,
                livroData.disponivel !== false,
                livroData.autor_id,
                id
            ], function(err) {
                if (err) reject(err);
                else resolve({ id, ...livroData });
            });
        });
    }

    async delete(id) {
        return new Promise((resolve, reject) => {
            this.db.run('DELETE FROM livros WHERE id = ?', [id], function(err) {
                if (err) reject(err);
                else resolve(this.changes > 0);
            });
        });
    }

    async updateDisponibilidade(id, disponivel) {
        return new Promise((resolve, reject) => {
            this.db.run(
                'UPDATE livros SET disponivel = ? WHERE id = ?',
                [disponivel ? 1 : 0, id],
                function(err) {
                    if (err) reject(err);
                    else resolve(this.changes > 0);
                }
            );
        });
    }
}

module.exports = LivroRepository;
```

```javascript
const { v4: uuidv4 } = require('uuid');
const database = require('../config/database');
const Autor = require('../models/Autor');

class AutorRepository {
    constructor() {
        this.db = database.getConnection();
    }

    async findAll() {
        return new Promise((resolve, reject) => {
            this.db.all('SELECT * FROM autores ORDER BY nome', (err, rows) => {
                if (err) reject(err);
                else resolve(rows.map(row => Autor.fromRow(row).toJSON()));
            });
        });
    }

    async findById(id) {
        return new Promise((resolve, reject) => {
            this.db.get('SELECT * FROM autores WHERE id = ?', [id], (err, row) => {
                if (err) reject(err);
                else if (!row) resolve(null);
                else resolve(Autor.fromRow(row).toJSON());
            });
        });
    }

    async create(autorData) {
        return new Promise((resolve, reject) => {
            const id = uuidv4();
            const query = 'INSERT INTO autores (id, nome, biografia) VALUES (?, ?, ?)';
            
            this.db.run(query, [id, autorData.nome, autorData.biografia], function(err) {
                if (err) reject(err);
                else resolve({ id, ...autorData });
            });
        });
    }

    async update(id, autorData) {
        return new Promise((resolve, reject) => {
            const query = 'UPDATE autores SET nome = ?, biografia = ? WHERE id = ?';
            
            this.db.run(query, [autorData.nome, autorData.biografia, id], function(err) {
                if (err) reject(err);
                else resolve({ id, ...autorData });
            });
        });
    }

    async delete(id) {
        return new Promise((resolve, reject) => {
            this.db.run('DELETE FROM autores WHERE id = ?', [id], function(err) {
                if (err) reject(err);
                else resolve(this.changes > 0);
            });
        });
    }
}

module.exports = AutorRepository;
```

```javascript
const LivroRepository = require('../repositories/LivroRepository');

class LivroService {
    constructor() {
        this.livroRepository = new LivroRepository();
    }

    async listarLivros(options = {}) {
        try {
            const livros = await this.livroRepository.findAll(options);
            return this.adicionarLinksHATEOAS(livros);
        } catch (error) {
            throw new Error(`Erro ao listar livros: ${error.message}`);
        }
    }

    async obterLivro(id) {
        try {
            const livro = await this.livroRepository.findById(id);
            if (!livro) {
                throw new Error('Livro não encontrado');
            }
            return this.adicionarLinksHATEOAS([livro])[0];
        } catch (error) {
            throw new Error(`Erro ao obter livro: ${error.message}`);
        }
    }

    async criarLivro(livroData) {
        try {
            this.validarLivro(livroData);
            const livro = await this.livroRepository.create(livroData);
            return this.adicionarLinksHATEOAS([livro])[0];
        } catch (error) {
            throw new Error(`Erro ao criar livro: ${error.message}`);
        }
    }

    async atualizarLivro(id, livroData) {
        try {
            this.validarLivro(livroData);
            const livroExistente = await this.livroRepository.findById(id);
            if (!livroExistente) {
                throw new Error('Livro não encontrado');
            }
            
            const livro = await this.livroRepository.update(id, livroData);
            return this.adicionarLinksHATEOAS([livro])[0];
        } catch (error) {
            throw new Error(`Erro ao atualizar livro: ${error.message}`);
        }
    }

    async removerLivro(id) {
        try {
            const livroExistente = await this.livroRepository.findById(id);
            if (!livroExistente) {
                throw new Error('Livro não encontrado');
            }
            
            const removido = await this.livroRepository.delete(id);
            if (!removido) {
                throw new Error('Erro ao remover livro');
            }
            return { message: 'Livro removido com sucesso' };
        } catch (error) {
            throw new Error(`Erro ao remover livro: ${error.message}`);
        }
    }

    validarLivro(livroData) {
        if (!livroData.titulo || livroData.titulo.trim().length === 0) {
            throw new Error('Título é obrigatório');
        }
        
        if (livroData.isbn && !this.validarISBN(livroData.isbn)) {
            throw new Error('ISBN inválido');
        }
        
        if (livroData.ano_publicacao && (livroData.ano_publicacao < 1000 || livroData.ano_publicacao > new Date().getFullYear())) {
            throw new Error('Ano de publicação inválido');
        }
    }

    validarISBN(isbn) {
        // Validação básica de ISBN
        const isbnRegex = /^(?:\d{10}|\d{13})$/;
        return isbnRegex.test(isbn.replace(/[-\s]/g, ''));
    }

    adicionarLinksHATEOAS(livros) {
        return livros.map(livro => {
            const links = [
                { rel: 'self', href: `/livros/${livro.id}`, method: 'GET' },
                { rel: 'update', href: `/livros/${livro.id}`, method: 'PUT' },
                { rel: 'delete', href: `/livros/${livro.id}`, method: 'DELETE' }
            ];

            // Links contextuais baseados no estado
            if (livro.disponivel) {
                links.push({ rel: 'emprestar', href: '/emprestimos', method: 'POST' });
            } else {
                links.push({ rel: 'devolver', href: `/emprestimos/${livro.id}/devolucao`, method: 'PUT' });
            }

            return {
                ...livro,
                _links: links
            };
        });
    }
}

module.exports = LivroService;
```

```javascript
const AutorRepository = require('../repositories/AutorRepository');

class AutorService {
    constructor() {
        this.autorRepository = new AutorRepository();
    }

    async listarAutores() {
        try {
            const autores = await this.autorRepository.findAll();
            return this.adicionarLinksHATEOAS(autores);
        } catch (error) {
            throw new Error(`Erro ao listar autores: ${error.message}`);
        }
    }

    async obterAutor(id) {
        try {
            const autor = await this.autorRepository.findById(id);
            if (!autor) {
                throw new Error('Autor não encontrado');
            }
            return this.adicionarLinksHATEOAS([autor])[0];
        } catch (error) {
            throw new Error(`Erro ao obter autor: ${error.message}`);
        }
    }

    async criarAutor(autorData) {
        try {
            this.validarAutor(autorData);
            const autor = await this.autorRepository.create(autorData);
            return this.adicionarLinksHATEOAS([autor])[0];
        } catch (error) {
            throw new Error(`Erro ao criar autor: ${error.message}`);
        }
    }

    async atualizarAutor(id, autorData) {
        try {
            this.validarAutor(autorData);
            const autorExistente = await this.autorRepository.findById(id);
            if (!autorExistente) {
                throw new Error('Autor não encontrado');
            }
            
            const autor = await this.autorRepository.update(id, autorData);
            return this.adicionarLinksHATEOAS([autor])[0];
        } catch (error) {
            throw new Error(`Erro ao atualizar autor: ${error.message}`);
        }
    }

    async removerAutor(id) {
        try {
            const autorExistente = await this.autorRepository.findById(id);
            if (!autorExistente) {
                throw new Error('Autor não encontrado');
            }
            
            const removido = await this.autorRepository.delete(id);
            if (!removido) {
                throw new Error('Erro ao remover autor');
            }
            return { message: 'Autor removido com sucesso' };
        } catch (error) {
            throw new Error(`Erro ao remover autor: ${error.message}`);
        }
    }

    validarAutor(autorData) {
        if (!autorData.nome || autorData.nome.trim().length === 0) {
            throw new Error('Nome é obrigatório');
        }
        
        if (autorData.nome.trim().length < 2) {
            throw new Error('Nome deve ter pelo menos 2 caracteres');
        }
    }

    adicionarLinksHATEOAS(autores) {
        return autores.map(autor => ({
            ...autor,
            _links: [
                { rel: 'self', href: `/autores/${autor.id}`, method: 'GET' },
                { rel: 'update', href: `/autores/${autor.id}`, method: 'PUT' },
                { rel: 'delete', href: `/autores/${autor.id}`, method: 'DELETE' },
                { rel: 'livros', href: `/livros?autor_id=${autor.id}`, method: 'GET' }
            ]
        }));
    }
}

module.exports = AutorService;
```

```javascript
const LivroService = require('../services/LivroService');

class LivroController {
    constructor() {
        this.livroService = new LivroService();
    }

    async listarLivros(req, res) {
        try {
            const options = {
                autor: req.query.autor,
                disponivel: req.query.disponivel === 'true',
                sort: req.query.sort,
                order: req.query.order,
                page: parseInt(req.query.page) || 1,
                size: parseInt(req.query.size) || 10
            };

            const livros = await this.livroService.listarLivros(options);
            
            res.status(200).json({
                data: livros,
                pagination: {
                    page: options.page,
                    size: options.size,
                    total: livros.length
                }
            });
        } catch (error) {
            res.status(500).json({
                erro: error.message,
                codigo: 500,
                timestamp: new Date().toISOString(),
                caminho: req.path
            });
        }
    }

    async obterLivro(req, res) {
        try {
            const { id } = req.params;
            const livro = await this.livroService.obterLivro(id);
            
            res.status(200).json(livro);
        } catch (error) {
            if (error.message.includes('não encontrado')) {
                res.status(404).json({
                    erro: error.message,
                    codigo: 404,
                    timestamp: new Date().toISOString(),
                    caminho: req.path
                });
            } else {
                res.status(500).json({
                    erro: error.message,
                    codigo: 500,
                    timestamp: new Date().toISOString(),
                    caminho: req.path
                });
            }
        }
    }

    async criarLivro(req, res) {
        try {
            const livro = await this.livroService.criarLivro(req.body);
            
            res.status(201).json(livro);
        } catch (error) {
            res.status(400).json({
                erro: error.message,
                codigo: 400,
                timestamp: new Date().toISOString(),
                caminho: req.path
            });
        }
    }

    async atualizarLivro(req, res) {
        try {
            const { id } = req.params;
            const livro = await this.livroService.atualizarLivro(id, req.body);
            
            res.status(200).json(livro);
        } catch (error) {
            if (error.message.includes('não encontrado')) {
                res.status(404).json({
                    erro: error.message,
                    codigo: 404,
                    timestamp: new Date().toISOString(),
                    caminho: req.path
                });
            } else {
                res.status(400).json({
                    erro: error.message,
                    codigo: 400,
                    timestamp: new Date().toISOString(),
                    caminho: req.path
                });
            }
        }
    }

    async removerLivro(req, res) {
        try {
            const { id } = req.params;
            const resultado = await this.livroService.removerLivro(id);
            
            res.status(200).json(resultado);
        } catch (error) {
            if (error.message.includes('não encontrado')) {
                res.status(404).json({
                    erro: error.message,
                    codigo: 404,
                    timestamp: new Date().toISOString(),
                    caminho: req.path
                });
            } else {
                res.status(500).json({
                    erro: error.message,
                    codigo: 500,
                    timestamp: new Date().toISOString(),
                    caminho: req.path
                });
            }
        }
    }
}

module.exports = LivroController;
```

```javascript
const express = require('express');
const LivroController = require('../controllers/LivroController');

const router = express.Router();
const livroController = new LivroController();

// GET /livros - Listar todos os livros
router.get('/', (req, res) => livroController.listarLivros(req, res));

// GET /livros/:id - Obter um livro específico
router.get('/:id', (req, res) => livroController.obterLivro(req, res));

// POST /livros - Criar um novo livro
router.post('/', (req, res) => livroController.criarLivro(req, res));

// PUT /livros/:id - Atualizar um livro
router.put('/:id', (req, res) => livroController.atualizarLivro(req, res));

// DELETE /livros/:id - Remover um livro
router.delete('/:id', (req, res) => livroController.removerLivro(req, res));

module.exports = router;
```

```javascript
const express = require('express');
const cors = require('cors');
const helmet = require('helmet');
const morgan = require('morgan');

// Importar rotas
const livrosRoutes = require('./routes/livros');
const autoresRoutes = require('./routes/autores');
const usuariosRoutes = require('./routes/usuarios');
const emprestimosRoutes = require('./routes/emprestimos');

// Middleware de tratamento de erros
const errorHandler = require('./middleware/errorHandler');

const app = express();
const PORT = process.env.PORT || 3000;

// Middlewares de segurança e logging
app.use(helmet());
app.use(cors());
app.use(morgan('combined'));

// Middleware para parsing JSON
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

// Rotas
app.use('/livros', livrosRoutes);
app.use('/autores', autoresRoutes);
app.use('/usuarios', usuariosRoutes);
app.use('/emprestimos', emprestimosRoutes);

// Rota raiz
app.get('/', (req, res) => {
    res.json({
        message: 'API de Biblioteca - Sistema REST',
        version: '1.0.0',
        endpoints: {
            livros: '/livros',
            autores: '/autores',
            usuarios: '/usuarios',
            emprestimos: '/emprestimos'
        }
    });
});

// Middleware de tratamento de erros
app.use(errorHandler);

// Rota para recursos não encontrados
app.use('*', (req, res) => {
    res.status(404).json({
        erro: 'Recurso não encontrado',
        codigo: 404,
        timestamp: new Date().toISOString(),
        caminho: req.originalUrl
    });
});

app.listen(PORT, () => {
    console.log(`🚀 Servidor rodando na porta ${PORT}`);
    console.log(`📚 API de Biblioteca disponível em http://localhost:${PORT}`);
});

module.exports = app;
```

```javascript
const errorHandler = (err, req, res, next) => {
    console.error('Erro:', err);

    // Erro de validação
    if (err.name === 'ValidationError') {
        return res.status(400).json({
            erro: 'Dados inválidos',
            detalhes: err.message,
            codigo: 400,
            timestamp: new Date().toISOString(),
            caminho: req.path
        });
    }

    // Erro de sintaxe JSON
    if (err instanceof SyntaxError && err.status === 400 && 'body' in err) {
        return res.status(400).json({
            erro: 'JSON inválido',
            codigo: 400,
            timestamp: new Date().toISOString(),
            caminho: req.path
        });
    }

    // Erro genérico
    res.status(500).json({
        erro: 'Erro interno do servidor',
        codigo: 500,
        timestamp: new Date().toISOString(),
        caminho: req.path
    });
};

module.exports = errorHandler;
```

```shellscript
npm install
```

```shellscript
npm run dev
```

```shellscript
npm test
```

