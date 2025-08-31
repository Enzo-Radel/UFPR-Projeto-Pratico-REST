package com.enzoradel.atividade_rest.repository;

import com.enzoradel.atividade_rest.model.Emprestimo;
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
public interface EmprestimoRepository extends JpaRepository<Emprestimo, String> {
    
    // Busca por livro
    Page<Emprestimo> findByLivroId(String livroId, Pageable pageable);
    
    // Busca por usuário
    Page<Emprestimo> findByUsuarioId(String usuarioId, Pageable pageable);
    
    // Busca por livro e usuário
    Page<Emprestimo> findByLivroIdAndUsuarioId(String livroId, String usuarioId, Pageable pageable);
    
    // Busca por status de devolução
    Page<Emprestimo> findByDevolvido(Boolean devolvido, Pageable pageable);
    
    // Busca empréstimos ativos (não devolvidos)
    Page<Emprestimo> findByDevolvidoFalse(Pageable pageable);
    
    // Busca empréstimos devolvidos
    Page<Emprestimo> findByDevolvidoTrue(Pageable pageable);
    
    // Busca por data de empréstimo após
    Page<Emprestimo> findByDataEmprestimoAfter(LocalDateTime data, Pageable pageable);
    
    // Busca por data de empréstimo entre
    Page<Emprestimo> findByDataEmprestimoBetween(LocalDateTime dataInicio, LocalDateTime dataFim, Pageable pageable);
    
    // Busca por data de devolução prevista após
    Page<Emprestimo> findByDataDevolucaoPrevistaAfter(LocalDateTime data, Pageable pageable);
    
    // Busca por data de devolução prevista entre
    Page<Emprestimo> findByDataDevolucaoPrevistaBetween(LocalDateTime dataInicio, LocalDateTime dataFim, Pageable pageable);
    
    // Busca empréstimos atrasados
    @Query("SELECT e FROM Emprestimo e WHERE e.devolvido = false AND e.dataDevolucaoPrevista < :dataAtual")
    Page<Emprestimo> findEmprestimosAtrasados(@Param("dataAtual") LocalDateTime dataAtual, Pageable pageable);
    
    // Busca empréstimos que vencem hoje
    @Query("SELECT e FROM Emprestimo e WHERE e.devolvido = false AND DATE(e.dataDevolucaoPrevista) = DATE(:dataAtual)")
    Page<Emprestimo> findEmprestimosVencemHoje(@Param("dataAtual") LocalDateTime dataAtual, Pageable pageable);
    
    // Busca empréstimos que vencem em X dias
    @Query("SELECT e FROM Emprestimo e WHERE e.devolvido = false AND e.dataDevolucaoPrevista BETWEEN :dataInicio AND :dataFim")
    Page<Emprestimo> findEmprestimosVencemEntre(@Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim, Pageable pageable);
    
    // Busca por nome do livro
    @Query("SELECT e FROM Emprestimo e JOIN e.livro l WHERE LOWER(l.titulo) LIKE LOWER(CONCAT('%', :tituloLivro, '%'))")
    Page<Emprestimo> findByTituloLivroContainingIgnoreCase(@Param("tituloLivro") String tituloLivro, Pageable pageable);
    
    // Busca por nome do usuário
    @Query("SELECT e FROM Emprestimo e JOIN e.usuario u WHERE LOWER(u.nome) LIKE LOWER(CONCAT('%', :nomeUsuario, '%'))")
    Page<Emprestimo> findByNomeUsuarioContainingIgnoreCase(@Param("nomeUsuario") String nomeUsuario, Pageable pageable);
    
    // Busca por email do usuário
    @Query("SELECT e FROM Emprestimo e JOIN e.usuario u WHERE LOWER(u.email) LIKE LOWER(CONCAT('%', :emailUsuario, '%'))")
    Page<Emprestimo> findByEmailUsuarioContainingIgnoreCase(@Param("emailUsuario") String emailUsuario, Pageable pageable);
    
    // Busca por nome do autor do livro
    @Query("SELECT e FROM Emprestimo e JOIN e.livro l JOIN l.autor a WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', :nomeAutor, '%'))")
    Page<Emprestimo> findByNomeAutorContainingIgnoreCase(@Param("nomeAutor") String nomeAutor, Pageable pageable);
    
    // Busca empréstimos ativos por livro
    Page<Emprestimo> findByLivroIdAndDevolvidoFalse(String livroId, Pageable pageable);
    
    // Busca empréstimos ativos por usuário
    Page<Emprestimo> findByUsuarioIdAndDevolvidoFalse(String usuarioId, Pageable pageable);
    
    // Busca empréstimos devolvidos por livro
    Page<Emprestimo> findByLivroIdAndDevolvidoTrue(String livroId, Pageable pageable);
    
    // Busca empréstimos devolvidos por usuário
    Page<Emprestimo> findByUsuarioIdAndDevolvidoTrue(String usuarioId, Pageable pageable);
    
    // Conta empréstimos ativos por livro
    long countByLivroIdAndDevolvidoFalse(String livroId);
    
    // Conta empréstimos ativos por usuário
    long countByUsuarioIdAndDevolvidoFalse(String usuarioId);
    
    // Conta empréstimos totais por livro
    long countByLivroId(String livroId);
    
    // Conta empréstimos totais por usuário
    long countByUsuarioId(String usuarioId);
    
    // Busca empréstimos mais recentes
    Page<Emprestimo> findAllByOrderByDataEmprestimoDesc(Pageable pageable);
    
    // Busca empréstimos mais antigos
    Page<Emprestimo> findAllByOrderByDataEmprestimoAsc(Pageable pageable);
    
    // Busca por data de criação após
    Page<Emprestimo> findByCreatedAtAfter(LocalDateTime data, Pageable pageable);
    
    // Busca por data de criação entre
    Page<Emprestimo> findByCreatedAtBetween(LocalDateTime dataInicio, LocalDateTime dataFim, Pageable pageable);
    
    // Busca empréstimos criados mais recentemente
    Page<Emprestimo> findAllByOrderByCreatedAtDesc(Pageable pageable);
    
    // Busca empréstimos criados mais antigamente
    Page<Emprestimo> findAllByOrderByCreatedAtAsc(Pageable pageable);
    
    // Verifica se existe empréstimo ativo para livro e usuário
    boolean existsByLivroIdAndUsuarioIdAndDevolvidoFalse(String livroId, String usuarioId);
    
    // Busca empréstimo ativo por livro e usuário
    Optional<Emprestimo> findByLivroIdAndUsuarioIdAndDevolvidoFalse(String livroId, String usuarioId);
}
