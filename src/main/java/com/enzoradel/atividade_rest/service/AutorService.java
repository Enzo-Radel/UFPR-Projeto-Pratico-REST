package com.enzoradel.atividade_rest.service;

import com.enzoradel.atividade_rest.model.Autor;
import com.enzoradel.atividade_rest.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AutorService {
    
    private final AutorRepository autorRepository;
    
    @Autowired
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }
    
    /**
     * Lista todos os autores
     * @return lista de todos os autores
     */
    @Transactional(readOnly = true)
    public List<Autor> listarTodos() {
        return autorRepository.findAllByOrderByNomeAsc();
    }
    
    /**
     * Lista autores com paginação
     * @param pageable configurações de paginação
     * @return página de autores
     */
    @Transactional(readOnly = true)
    public Page<Autor> listarComPaginacao(Pageable pageable) {
        return autorRepository.findAll(pageable);
    }
    
    /**
     * Busca autor por ID
     * @param id ID do autor
     * @return Optional contendo o autor se encontrado
     */
    @Transactional(readOnly = true)
    public Optional<Autor> buscarPorId(String id) {
        return autorRepository.findById(id);
    }
    
    /**
     * Busca autor por nome
     * @param nome nome ou parte do nome
     * @return lista de autores encontrados
     */
    @Transactional(readOnly = true)
    public List<Autor> buscarPorNome(String nome) {
        return autorRepository.findByNomeContainingIgnoreCase(nome);
    }
    
    /**
     * Busca autor por nome com paginação
     * @param nome nome ou parte do nome
     * @param pageable configurações de paginação
     * @return página de autores encontrados
     */
    @Transactional(readOnly = true)
    public Page<Autor> buscarPorNomeComPaginacao(String nome, Pageable pageable) {
        return autorRepository.findByNomeContainingIgnoreCase(nome, pageable);
    }
    
    /**
     * Busca autor por nome exato
     * @param nome nome exato do autor
     * @return Optional contendo o autor se encontrado
     */
    @Transactional(readOnly = true)
    public Optional<Autor> buscarPorNomeExato(String nome) {
        return autorRepository.findByNomeIgnoreCase(nome);
    }
    
    /**
     * Busca flexível por nome ou biografia
     * @param termo termo de busca
     * @return lista de autores encontrados
     */
    @Transactional(readOnly = true)
    public List<Autor> buscarPorNomeOuBiografia(String termo) {
        return autorRepository.findByNomeOrBiografiaContainingIgnoreCase(termo);
    }
    
    /**
     * Busca flexível por nome ou biografia com paginação
     * @param termo termo de busca
     * @param pageable configurações de paginação
     * @return página de autores encontrados
     */
    @Transactional(readOnly = true)
    public Page<Autor> buscarPorNomeOuBiografiaComPaginacao(String termo, Pageable pageable) {
        return autorRepository.findByNomeOrBiografiaContainingIgnoreCase(termo, pageable);
    }
    
    /**
     * Cria um novo autor
     * @param autor dados do autor
     * @return autor criado
     * @throws IllegalArgumentException se nome já existir
     */
    public Autor criar(Autor autor) {
        validarAutor(autor);
        
        if (autorRepository.existsByNomeIgnoreCase(autor.getNome())) {
            throw new IllegalArgumentException("Autor com este nome já existe: " + autor.getNome());
        }
        
        autor.setCreatedAt(LocalDateTime.now());
        autor.setUpdatedAt(LocalDateTime.now());
        
        return autorRepository.save(autor);
    }
    
    /**
     * Atualiza um autor existente
     * @param id ID do autor
     * @param autor dados atualizados
     * @return autor atualizado
     * @throws IllegalArgumentException se autor não encontrado ou nome já existir
     */
    public Autor atualizar(String id, Autor autor) {
        validarAutor(autor);
        
        Autor autorExistente = autorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Autor não encontrado com ID: " + id));
        
        // Verifica se o nome foi alterado e se já existe
        if (!autorExistente.getNome().equalsIgnoreCase(autor.getNome()) && 
            autorRepository.existsByNomeIgnoreCase(autor.getNome())) {
            throw new IllegalArgumentException("Autor com este nome já existe: " + autor.getNome());
        }
        
        autorExistente.setNome(autor.getNome());
        autorExistente.setBiografia(autor.getBiografia());
        autorExistente.setUpdatedAt(LocalDateTime.now());
        
        return autorRepository.save(autorExistente);
    }
    
    /**
     * Remove um autor
     * @param id ID do autor
     * @throws IllegalArgumentException se autor não encontrado ou tiver livros
     */
    public void remover(String id) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Autor não encontrado com ID: " + id));
        
        // Verifica se o autor tem livros
        long totalLivros = autorRepository.countLivrosByAutorId(id);
        if (totalLivros > 0) {
            throw new IllegalArgumentException("Não é possível remover autor com livros publicados. " +
                    "Total de livros: " + totalLivros);
        }
        
        autorRepository.deleteById(id);
    }
    
    /**
     * Busca autores com livros
     * @return lista de autores com livros
     */
    @Transactional(readOnly = true)
    public List<Autor> buscarComLivros() {
        return autorRepository.findAutoresComLivros();
    }
    
    /**
     * Busca autores sem livros
     * @return lista de autores sem livros
     */
    @Transactional(readOnly = true)
    public List<Autor> buscarSemLivros() {
        return autorRepository.findAutoresSemLivros();
    }
    
    /**
     * Busca autores com livros disponíveis
     * @return lista de autores com livros disponíveis
     */
    @Transactional(readOnly = true)
    public List<Autor> buscarComLivrosDisponiveis() {
        return autorRepository.findAutoresComLivrosDisponiveis();
    }
    
    /**
     * Busca autores com livros emprestados
     * @return lista de autores com livros emprestados
     */
    @Transactional(readOnly = true)
    public List<Autor> buscarComLivrosEmprestados() {
        return autorRepository.findAutoresComLivrosEmprestados();
    }
    
    /**
     * Conta livros de um autor
     * @param autorId ID do autor
     * @return número de livros
     */
    @Transactional(readOnly = true)
    public long contarLivros(String autorId) {
        return autorRepository.countLivrosByAutorId(autorId);
    }
    
    /**
     * Verifica se um autor tem livros
     * @param autorId ID do autor
     * @return true se tem livros, false caso contrário
     */
    @Transactional(readOnly = true)
    public boolean temLivros(String autorId) {
        return autorRepository.countLivrosByAutorId(autorId) > 0;
    }
    
    /**
     * Busca autores criados em um período
     * @param dataInicio data de início
     * @param dataFim data de fim
     * @return lista de autores criados no período
     */
    @Transactional(readOnly = true)
    public List<Autor> buscarPorPeriodoCriacao(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return autorRepository.findByCreatedAtBetween(dataInicio, dataFim);
    }
    
    /**
     * Conta autores criados em um período
     * @param dataInicio data de início
     * @param dataFim data de fim
     * @return número de autores criados no período
     */
    @Transactional(readOnly = true)
    public long contarPorPeriodoCriacao(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return autorRepository.countByCreatedAtBetween(dataInicio, dataFim);
    }
    
    /**
     * Busca autores com biografia
     * @return lista de autores com biografia
     */
    @Transactional(readOnly = true)
    public List<Autor> buscarComBiografia() {
        return autorRepository.findByBiografiaIsNotNullAndBiografiaNot("");
    }
    
    /**
     * Busca autores sem biografia
     * @return lista de autores sem biografia
     */
    @Transactional(readOnly = true)
    public List<Autor> buscarSemBiografia() {
        return autorRepository.findByBiografiaIsNullOrBiografiaEquals("");
    }
    
    /**
     * Valida os dados do autor
     * @param autor autor a ser validado
     * @throws IllegalArgumentException se dados inválidos
     */
    private void validarAutor(Autor autor) {
        if (autor == null) {
            throw new IllegalArgumentException("Autor não pode ser nulo");
        }
        
        if (autor.getNome() == null || autor.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        
        if (autor.getNome().trim().length() < 2) {
            throw new IllegalArgumentException("Nome deve ter pelo menos 2 caracteres");
        }
        
        if (autor.getNome().trim().length() > 255) {
            throw new IllegalArgumentException("Nome deve ter no máximo 255 caracteres");
        }
        
        // Validação opcional da biografia
        if (autor.getBiografia() != null && autor.getBiografia().trim().length() > 10000) {
            throw new IllegalArgumentException("Biografia deve ter no máximo 10.000 caracteres");
        }
    }
}
