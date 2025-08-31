package com.enzoradel.atividade_rest.repository;

import com.enzoradel.atividade_rest.model.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, String> {
    
    // Busca por título (contém)
    Page<Livro> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);
    
    // Busca por título exato
    Optional<Livro> findByTituloIgnoreCase(String titulo);
    
    // Busca por ISBN
    Optional<Livro> findByIsbn(String isbn);
    
    // Verifica se existe por ISBN
    boolean existsByIsbn(String isbn);
    
    // Busca por autor
    Page<Livro> findByAutorId(String autorId, Pageable pageable);
    
    // Busca por nome do autor
    @Query("SELECT l FROM Livro l JOIN l.autor a WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', :nomeAutor, '%'))")
    Page<Livro> findByAutorNomeContainingIgnoreCase(@Param("nomeAutor") String nomeAutor, Pageable pageable);
    
    // Busca por ano de publicação
    Page<Livro> findByAnoPublicacao(Integer anoPublicacao, Pageable pageable);
    
    // Busca por ano de publicação maior ou igual
    Page<Livro> findByAnoPublicacaoGreaterThanEqual(Integer anoPublicacao, Pageable pageable);
    
    // Busca por ano de publicação entre
    Page<Livro> findByAnoPublicacaoBetween(Integer anoInicio, Integer anoFim, Pageable pageable);
    
    // Busca por disponibilidade
    Page<Livro> findByDisponivel(Boolean disponivel, Pageable pageable);
    
    // Busca livros disponíveis
    Page<Livro> findByDisponivelTrue(Pageable pageable);
    
    // Busca livros indisponíveis
    Page<Livro> findByDisponivelFalse(Pageable pageable);
    
    // Busca por data de criação após
    Page<Livro> findByCreatedAtAfter(LocalDateTime data, Pageable pageable);
    
    // Busca por data de criação entre
    Page<Livro> findByCreatedAtBetween(LocalDateTime dataInicio, LocalDateTime dataFim, Pageable pageable);
    
    // Busca livros que não têm empréstimos ativos
    @Query("SELECT l FROM Livro l WHERE l.id NOT IN (SELECT DISTINCT e.livro.id FROM Emprestimo e WHERE e.devolvido = false)")
    Page<Livro> findLivrosDisponiveisParaEmprestimo(Pageable pageable);
    
    // Busca livros que têm empréstimos ativos
    @Query("SELECT l FROM Livro l WHERE l.id IN (SELECT DISTINCT e.livro.id FROM Emprestimo e WHERE e.devolvido = false)")
    Page<Livro> findLivrosEmprestados(Pageable pageable);
    
    // Busca por título e autor
    @Query("SELECT l FROM Livro l JOIN l.autor a WHERE LOWER(l.titulo) LIKE LOWER(CONCAT('%', :titulo, '%')) AND LOWER(a.nome) LIKE LOWER(CONCAT('%', :nomeAutor, '%'))")
    Page<Livro> findByTituloAndAutorNome(@Param("titulo") String titulo, @Param("nomeAutor") String nomeAutor, Pageable pageable);
    
    // Busca por título e ano
    Page<Livro> findByTituloContainingIgnoreCaseAndAnoPublicacao(String titulo, Integer anoPublicacao, Pageable pageable);
    
    // Busca por título e disponibilidade
    Page<Livro> findByTituloContainingIgnoreCaseAndDisponivel(String titulo, Boolean disponivel, Pageable pageable);
    
    // Conta livros por autor
    @Query("SELECT COUNT(l) FROM Livro l WHERE l.autor.id = :autorId")
    long countByAutorId(@Param("autorId") String autorId);
    
    // Busca livros mais recentes
    Page<Livro> findAllByOrderByCreatedAtDesc(Pageable pageable);
    
    // Busca livros mais antigos
    Page<Livro> findAllByOrderByCreatedAtAsc(Pageable pageable);
    
    // Busca por título ordenado alfabeticamente
    Page<Livro> findAllByOrderByTituloAsc(Pageable pageable);
    
    // Busca por ano de publicação ordenado
    Page<Livro> findAllByOrderByAnoPublicacaoDesc(Pageable pageable);
}
