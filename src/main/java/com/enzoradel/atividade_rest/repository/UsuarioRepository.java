package com.enzoradel.atividade_rest.repository;

import com.enzoradel.atividade_rest.model.Usuario;
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
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    
    /**
     * Busca usuário por email
     * @param email email do usuário
     * @return Optional contendo o usuário se encontrado
     */
    Optional<Usuario> findByEmail(String email);
    
    /**
     * Verifica se existe usuário com o email informado
     * @param email email a ser verificado
     * @return true se existe, false caso contrário
     */
    boolean existsByEmail(String email);
    
    /**
     * Busca usuários por nome (ignorando maiúsculas/minúsculas)
     * @param nome nome ou parte do nome do usuário
     * @return lista de usuários encontrados
     */
    List<Usuario> findByNomeContainingIgnoreCase(String nome);
    
    /**
     * Busca usuários por nome com paginação
     * @param nome nome ou parte do nome do usuário
     * @param pageable configurações de paginação
     * @return página de usuários encontrados
     */
    Page<Usuario> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
    
    /**
     * Busca usuários criados após uma data específica
     * @param data data limite
     * @return lista de usuários criados após a data
     */
    List<Usuario> findByCreatedAtAfter(LocalDateTime data);
    
    /**
     * Busca usuários criados entre duas datas
     * @param dataInicio data de início
     * @param dataFim data de fim
     * @return lista de usuários criados no período
     */
    List<Usuario> findByCreatedAtBetween(LocalDateTime dataInicio, LocalDateTime dataFim);
    
    /**
     * Busca usuários que têm empréstimos ativos (não devolvidos)
     * @return lista de usuários com empréstimos ativos
     */
    @Query("SELECT DISTINCT u FROM Usuario u " +
           "JOIN u.emprestimos e " +
           "WHERE e.devolvido = false")
    List<Usuario> findUsuariosComEmprestimosAtivos();
    
    /**
     * Busca usuários que têm empréstimos atrasados
     * @return lista de usuários com empréstimos atrasados
     */
    @Query("SELECT DISTINCT u FROM Usuario u " +
           "JOIN u.emprestimos e " +
           "WHERE e.devolvido = false " +
           "AND e.dataDevolucaoPrevista < :dataAtual")
    List<Usuario> findUsuariosComEmprestimosAtrasados(@Param("dataAtual") LocalDateTime dataAtual);
    
    /**
     * Conta quantos empréstimos ativos um usuário possui
     * @param usuarioId ID do usuário
     * @return número de empréstimos ativos
     */
    @Query("SELECT COUNT(e) FROM Emprestimo e " +
           "WHERE e.usuario.id = :usuarioId " +
           "AND e.devolvido = false")
    long countEmprestimosAtivosByUsuarioId(@Param("usuarioId") String usuarioId);
    
    /**
     * Busca usuários ordenados por nome
     * @return lista de usuários ordenados alfabeticamente
     */
    List<Usuario> findAllByOrderByNomeAsc();
    
    /**
     * Busca usuários ordenados por data de criação (mais recentes primeiro)
     * @return lista de usuários ordenados por data de criação
     */
    List<Usuario> findAllByOrderByCreatedAtDesc();
    
    /**
     * Busca usuários com paginação e ordenação
     * @param pageable configurações de paginação e ordenação
     * @return página de usuários
     */
    Page<Usuario> findAll(Pageable pageable);
    
    /**
     * Busca usuários por nome ou email (busca flexível)
     * @param termo termo de busca
     * @return lista de usuários que contêm o termo no nome ou email
     */
    @Query("SELECT u FROM Usuario u " +
           "WHERE LOWER(u.nome) LIKE LOWER(CONCAT('%', :termo, '%')) " +
           "OR LOWER(u.email) LIKE LOWER(CONCAT('%', :termo, '%'))")
    List<Usuario> findByNomeOrEmailContainingIgnoreCase(@Param("termo") String termo);
    
    /**
     * Busca usuários por nome ou email com paginação
     * @param termo termo de busca
     * @param pageable configurações de paginação
     * @return página de usuários que contêm o termo no nome ou email
     */
    @Query("SELECT u FROM Usuario u " +
           "WHERE LOWER(u.nome) LIKE LOWER(CONCAT('%', :termo, '%')) " +
           "OR LOWER(u.email) LIKE LOWER(CONCAT('%', :termo, '%'))")
    Page<Usuario> findByNomeOrEmailContainingIgnoreCase(@Param("termo") String termo, Pageable pageable);
    
    /**
     * Busca usuários que não possuem empréstimos ativos
     * @return lista de usuários sem empréstimos ativos
     */
    @Query("SELECT u FROM Usuario u " +
           "WHERE u.id NOT IN " +
           "(SELECT DISTINCT e.usuario.id FROM Emprestimo e WHERE e.devolvido = false)")
    List<Usuario> findUsuariosSemEmprestimosAtivos();
    
    /**
     * Conta total de usuários
     * @return número total de usuários
     */
    long count();
    
    /**
     * Conta usuários criados em um período específico
     * @param dataInicio data de início
     * @param dataFim data de fim
     * @return número de usuários criados no período
     */
    long countByCreatedAtBetween(LocalDateTime dataInicio, LocalDateTime dataFim);
}
