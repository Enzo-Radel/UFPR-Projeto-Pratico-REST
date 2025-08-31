package com.enzoradel.atividade_rest.repository;

import com.enzoradel.atividade_rest.model.Autor;
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
public interface AutorRepository extends JpaRepository<Autor, String> {
    
    /**
     * Busca autor por nome (ignorando maiúsculas/minúsculas)
     * @param nome nome ou parte do nome do autor
     * @return lista de autores encontrados
     */
    List<Autor> findByNomeContainingIgnoreCase(String nome);
    
    /**
     * Busca autor por nome com paginação
     * @param nome nome ou parte do nome do autor
     * @param pageable configurações de paginação
     * @return página de autores encontrados
     */
    Page<Autor> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
    
    /**
     * Busca autor por nome exato (ignorando maiúsculas/minúsculas)
     * @param nome nome exato do autor
     * @return Optional contendo o autor se encontrado
     */
    Optional<Autor> findByNomeIgnoreCase(String nome);
    
    /**
     * Verifica se existe autor com o nome informado
     * @param nome nome a ser verificado
     * @return true se existe, false caso contrário
     */
    boolean existsByNomeIgnoreCase(String nome);
    
    /**
     * Busca autores criados após uma data específica
     * @param data data limite
     * @return lista de autores criados após a data
     */
    List<Autor> findByCreatedAtAfter(LocalDateTime data);
    
    /**
     * Busca autores criados entre duas datas
     * @param dataInicio data de início
     * @param dataFim data de fim
     * @return lista de autores criados no período
     */
    List<Autor> findByCreatedAtBetween(LocalDateTime dataInicio, LocalDateTime dataFim);
    
    /**
     * Busca autores que têm livros publicados
     * @return lista de autores com livros
     */
    @Query("SELECT DISTINCT a FROM Autor a " +
           "JOIN a.livros l " +
           "WHERE l.id IS NOT NULL")
    List<Autor> findAutoresComLivros();
    
    /**
     * Busca autores que não têm livros publicados
     * @return lista de autores sem livros
     */
    @Query("SELECT a FROM Autor a " +
           "WHERE a.id NOT IN " +
           "(SELECT DISTINCT l.autor.id FROM Livro l)")
    List<Autor> findAutoresSemLivros();
    
    /**
     * Conta quantos livros um autor possui
     * @param autorId ID do autor
     * @return número de livros
     */
    @Query("SELECT COUNT(l) FROM Livro l " +
           "WHERE l.autor.id = :autorId")
    long countLivrosByAutorId(@Param("autorId") String autorId);
    
    /**
     * Busca autores ordenados por nome
     * @return lista de autores ordenados alfabeticamente
     */
    List<Autor> findAllByOrderByNomeAsc();
    
    /**
     * Busca autores ordenados por data de criação (mais recentes primeiro)
     * @return lista de autores ordenados por data de criação
     */
    List<Autor> findAllByOrderByCreatedAtDesc();
    
    /**
     * Busca autores com paginação e ordenação
     * @param pageable configurações de paginação e ordenação
     * @return página de autores
     */
    Page<Autor> findAll(Pageable pageable);
    
    /**
     * Busca autores por nome ou biografia (busca flexível)
     * @param termo termo de busca
     * @return lista de autores que contêm o termo no nome ou biografia
     */
    @Query("SELECT a FROM Autor a " +
           "WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', :termo, '%')) " +
           "OR LOWER(a.biografia) LIKE LOWER(CONCAT('%', :termo, '%'))")
    List<Autor> findByNomeOrBiografiaContainingIgnoreCase(@Param("termo") String termo);
    
    /**
     * Busca autores por nome ou biografia com paginação
     * @param termo termo de busca
     * @param pageable configurações de paginação
     * @return página de autores que contêm o termo no nome ou biografia
     */
    @Query("SELECT a FROM Autor a " +
           "WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', :termo, '%')) " +
           "OR LOWER(a.biografia) LIKE LOWER(CONCAT('%', :termo, '%'))")
    Page<Autor> findByNomeOrBiografiaContainingIgnoreCase(@Param("termo") String termo, Pageable pageable);
    
    /**
     * Busca autores com livros disponíveis para empréstimo
     * @return lista de autores com livros disponíveis
     */
    @Query("SELECT DISTINCT a FROM Autor a " +
           "JOIN a.livros l " +
           "WHERE l.disponivel = true")
    List<Autor> findAutoresComLivrosDisponiveis();
    
    /**
     * Busca autores com livros emprestados
     * @return lista de autores com livros emprestados
     */
    @Query("SELECT DISTINCT a FROM Autor a " +
           "JOIN a.livros l " +
           "JOIN l.emprestimos e " +
           "WHERE e.devolvido = false")
    List<Autor> findAutoresComLivrosEmprestados();
    
    /**
     * Conta total de autores
     * @return número total de autores
     */
    long count();
    
    /**
     * Conta autores criados em um período específico
     * @param dataInicio data de início
     * @param dataFim data de fim
     * @return número de autores criados no período
     */
    long countByCreatedAtBetween(LocalDateTime dataInicio, LocalDateTime dataFim);
    
    /**
     * Busca autores com biografia não vazia
     * @return lista de autores com biografia
     */
    List<Autor> findByBiografiaIsNotNullAndBiografiaNot(String biografia);
    
    /**
     * Busca autores sem biografia
     * @return lista de autores sem biografia
     */
    List<Autor> findByBiografiaIsNullOrBiografiaEquals(String biografia);
}
