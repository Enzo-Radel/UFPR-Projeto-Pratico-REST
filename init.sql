-- Script de inicialização do banco de dados da Biblioteca
-- Este script será executado automaticamente quando o container MySQL for criado

USE biblioteca;

-- Tabela de Autores
CREATE TABLE IF NOT EXISTS autores (
    id VARCHAR(36) PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    biografia TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tabela de Usuários
CREATE TABLE IF NOT EXISTS usuarios (
    id VARCHAR(36) PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tabela de Livros
CREATE TABLE IF NOT EXISTS livros (
    id VARCHAR(36) PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    isbn VARCHAR(20) UNIQUE,
    ano_publicacao INT,
    disponivel BOOLEAN DEFAULT TRUE,
    autor_id VARCHAR(36),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (autor_id) REFERENCES autores(id) ON DELETE SET NULL
);

-- Tabela de Empréstimos
CREATE TABLE IF NOT EXISTS emprestimos (
    id VARCHAR(36) PRIMARY KEY,
    livro_id VARCHAR(36) NOT NULL,
    usuario_id VARCHAR(36) NOT NULL,
    data_emprestimo TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_devolucao_prevista TIMESTAMP,
    data_devolucao_efetiva TIMESTAMP NULL,
    devolvido BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (livro_id) REFERENCES livros(id) ON DELETE CASCADE,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

-- Índices para melhor performance
CREATE INDEX idx_livros_autor ON livros(autor_id);
CREATE INDEX idx_livros_disponivel ON livros(disponivel);
CREATE INDEX idx_emprestimos_livro ON emprestimos(livro_id);
CREATE INDEX idx_emprestimos_usuario ON emprestimos(usuario_id);
CREATE INDEX idx_emprestimos_devolvido ON emprestimos(devolvido);

-- Dados de exemplo (opcional)
INSERT INTO autores (id, nome, biografia) VALUES 
('a1b2c3d4-e5f6-7890-abcd-ef1234567890', 'Machado de Assis', 'Escritor brasileiro, considerado o maior nome da literatura nacional.'),
('b2c3d4e5-f6g7-8901-bcde-f23456789012', 'Clarice Lispector', 'Escritora brasileira de origem ucraniana, uma das mais importantes do século XX.'),
('c3d4e5f6-g7h8-9012-cdef-345678901234', 'Jorge Amado', 'Escritor brasileiro, autor de obras como "Gabriela, Cravo e Canela".')
ON DUPLICATE KEY UPDATE nome = VALUES(nome);

INSERT INTO usuarios (id, nome, email) VALUES 
('u1b2c3d4-e5f6-7890-abcd-ef1234567890', 'João Silva', 'joao.silva@email.com'),
('u2c3d4e5-f6g7-8901-bcde-f23456789012', 'Maria Santos', 'maria.santos@email.com'),
('u3d4e5f6-g7h8-9012-cdef-345678901234', 'Pedro Oliveira', 'pedro.oliveira@email.com')
ON DUPLICATE KEY UPDATE nome = VALUES(nome);

INSERT INTO livros (id, titulo, isbn, ano_publicacao, disponivel, autor_id) VALUES 
('l1b2c3d4-e5f6-7890-abcd-ef1234567890', 'Dom Casmurro', '978-8535902778', 1899, TRUE, 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'),
('l2c3d4e5-f6g7-8901-bcde-f23456789012', 'A Hora da Estrela', '978-8535902779', 1977, TRUE, 'b2c3d4e5-f6g7-8901-bcde-f23456789012'),
('l3d4e5f6-g7h8-9012-cdef-345678901234', 'Gabriela, Cravo e Canela', '978-8535902780', 1958, TRUE, 'c3d4e5f6-g7h8-9012-cdef-345678901234')
ON DUPLICATE KEY UPDATE titulo = VALUES(titulo);
