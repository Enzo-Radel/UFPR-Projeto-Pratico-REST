package com.enzoradel.atividade_rest.service;

import com.enzoradel.atividade_rest.dto.LivroDTO;
import com.enzoradel.atividade_rest.dto.LivroResponseDTO;
import com.enzoradel.atividade_rest.model.Livro;
import com.enzoradel.atividade_rest.model.Autor;
import com.enzoradel.atividade_rest.repository.LivroRepository;
import com.enzoradel.atividade_rest.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class LivroService {
    
    @Autowired
    private LivroRepository livroRepository;
    
    @Autowired
    private AutorRepository autorRepository;
    
    // CREATE
    public LivroResponseDTO criarLivro(LivroDTO livroDTO) {
        // Validações
        validarLivroDTO(livroDTO);
        
        // Verificar se o autor existe
        Autor autor = autorRepository.findById(livroDTO.getAutorId())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado com ID: " + livroDTO.getAutorId()));
        
        // Verificar se ISBN já existe
        if (livroDTO.getIsbn() != null && livroRepository.existsByIsbn(livroDTO.getIsbn())) {
            throw new RuntimeException("ISBN já cadastrado: " + livroDTO.getIsbn());
        }
        
        // Criar livro
        Livro livro = new Livro();
        livro.setTitulo(livroDTO.getTitulo());
        livro.setIsbn(livroDTO.getIsbn());
        livro.setAnoPublicacao(livroDTO.getAnoPublicacao());
        livro.setDisponivel(livroDTO.getDisponivel());
        livro.setAutor(autor);
        
        Livro livroSalvo = livroRepository.save(livro);
        return new LivroResponseDTO(livroSalvo);
    }
    
    // READ - Buscar por ID
    @Transactional(readOnly = true)
    public LivroResponseDTO buscarPorId(String id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com ID: " + id));
        return new LivroResponseDTO(livro);
    }
    
    // READ - Listar todos com paginação
    @Transactional(readOnly = true)
    public Page<LivroResponseDTO> listarTodos(Pageable pageable) {
        Page<Livro> livros = livroRepository.findAll(pageable);
        return livros.map(LivroResponseDTO::new);
    }
    
    // UPDATE
    public LivroResponseDTO atualizarLivro(String id, LivroDTO livroDTO) {
        // Validações
        validarLivroDTO(livroDTO);
        
        // Buscar livro existente
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com ID: " + id));
        
        // Verificar se o autor existe
        Autor autor = autorRepository.findById(livroDTO.getAutorId())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado com ID: " + livroDTO.getAutorId()));
        
        // Verificar se ISBN já existe (exceto para o próprio livro)
        if (livroDTO.getIsbn() != null && !livroDTO.getIsbn().equals(livro.getIsbn())) {
            if (livroRepository.existsByIsbn(livroDTO.getIsbn())) {
                throw new RuntimeException("ISBN já cadastrado: " + livroDTO.getIsbn());
            }
        }
        
        // Atualizar dados
        livro.setTitulo(livroDTO.getTitulo());
        livro.setIsbn(livroDTO.getIsbn());
        livro.setAnoPublicacao(livroDTO.getAnoPublicacao());
        livro.setDisponivel(livroDTO.getDisponivel());
        livro.setAutor(autor);
        
        Livro livroAtualizado = livroRepository.save(livro);
        return new LivroResponseDTO(livroAtualizado);
    }
    
    // DELETE
    public void deletarLivro(String id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com ID: " + id));
        
        // Verificar se tem empréstimos ativos
        if (livro.getEmprestimos() != null && 
            livro.getEmprestimos().stream().anyMatch(e -> e.getDataDevolucaoEfetiva() == null)) {
            throw new RuntimeException("Não é possível excluir livro com empréstimos ativos");
        }
        
        livroRepository.delete(livro);
    }
    
    // Métodos de busca
    @Transactional(readOnly = true)
    public Page<LivroResponseDTO> buscarPorTitulo(String titulo, Pageable pageable) {
        Page<Livro> livros = livroRepository.findByTituloContainingIgnoreCase(titulo, pageable);
        return livros.map(LivroResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<LivroResponseDTO> buscarPorIsbn(String isbn, Pageable pageable) {
        Optional<Livro> livro = livroRepository.findByIsbn(isbn);
        if (livro.isPresent()) {
            return Page.empty(pageable).map(l -> new LivroResponseDTO(livro.get()));
        }
        return Page.empty(pageable);
    }
    
    @Transactional(readOnly = true)
    public Page<LivroResponseDTO> buscarPorAutor(String autorId, Pageable pageable) {
        Page<Livro> livros = livroRepository.findByAutorId(autorId, pageable);
        return livros.map(LivroResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<LivroResponseDTO> buscarPorNomeAutor(String nomeAutor, Pageable pageable) {
        Page<Livro> livros = livroRepository.findByAutorNomeContainingIgnoreCase(nomeAutor, pageable);
        return livros.map(LivroResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<LivroResponseDTO> buscarPorAno(Integer ano, Pageable pageable) {
        Page<Livro> livros = livroRepository.findByAnoPublicacao(ano, pageable);
        return livros.map(LivroResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<LivroResponseDTO> buscarPorAnoMaiorOuIgual(Integer ano, Pageable pageable) {
        Page<Livro> livros = livroRepository.findByAnoPublicacaoGreaterThanEqual(ano, pageable);
        return livros.map(LivroResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<LivroResponseDTO> buscarPorAnoEntre(Integer anoInicio, Integer anoFim, Pageable pageable) {
        Page<Livro> livros = livroRepository.findByAnoPublicacaoBetween(anoInicio, anoFim, pageable);
        return livros.map(LivroResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<LivroResponseDTO> buscarPorDisponibilidade(Boolean disponivel, Pageable pageable) {
        Page<Livro> livros = livroRepository.findByDisponivel(disponivel, pageable);
        return livros.map(LivroResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<LivroResponseDTO> buscarDisponiveis(Pageable pageable) {
        Page<Livro> livros = livroRepository.findByDisponivelTrue(pageable);
        return livros.map(LivroResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<LivroResponseDTO> buscarIndisponiveis(Pageable pageable) {
        Page<Livro> livros = livroRepository.findByDisponivelFalse(pageable);
        return livros.map(LivroResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<LivroResponseDTO> buscarDisponiveisParaEmprestimo(Pageable pageable) {
        Page<Livro> livros = livroRepository.findLivrosDisponiveisParaEmprestimo(pageable);
        return livros.map(LivroResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<LivroResponseDTO> buscarEmprestados(Pageable pageable) {
        Page<Livro> livros = livroRepository.findLivrosEmprestados(pageable);
        return livros.map(LivroResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<LivroResponseDTO> buscarPorTituloEAutor(String titulo, String nomeAutor, Pageable pageable) {
        Page<Livro> livros = livroRepository.findByTituloAndAutorNome(titulo, nomeAutor, pageable);
        return livros.map(LivroResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<LivroResponseDTO> buscarPorTituloEAno(String titulo, Integer ano, Pageable pageable) {
        Page<Livro> livros = livroRepository.findByTituloContainingIgnoreCaseAndAnoPublicacao(titulo, ano, pageable);
        return livros.map(LivroResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<LivroResponseDTO> buscarPorTituloEDisponibilidade(String titulo, Boolean disponivel, Pageable pageable) {
        Page<Livro> livros = livroRepository.findByTituloContainingIgnoreCaseAndDisponivel(titulo, disponivel, pageable);
        return livros.map(LivroResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<LivroResponseDTO> buscarPorDataCriacao(LocalDateTime data, Pageable pageable) {
        Page<Livro> livros = livroRepository.findByCreatedAtAfter(data, pageable);
        return livros.map(LivroResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<LivroResponseDTO> buscarPorDataCriacaoEntre(LocalDateTime dataInicio, LocalDateTime dataFim, Pageable pageable) {
        Page<Livro> livros = livroRepository.findByCreatedAtBetween(dataInicio, dataFim, pageable);
        return livros.map(LivroResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<LivroResponseDTO> buscarMaisRecentes(Pageable pageable) {
        Page<Livro> livros = livroRepository.findAllByOrderByCreatedAtDesc(pageable);
        return livros.map(LivroResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<LivroResponseDTO> buscarMaisAntigos(Pageable pageable) {
        Page<Livro> livros = livroRepository.findAllByOrderByCreatedAtAsc(pageable);
        return livros.map(LivroResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<LivroResponseDTO> buscarPorTituloOrdenado(Pageable pageable) {
        Page<Livro> livros = livroRepository.findAllByOrderByTituloAsc(pageable);
        return livros.map(LivroResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<LivroResponseDTO> buscarPorAnoOrdenado(Pageable pageable) {
        Page<Livro> livros = livroRepository.findAllByOrderByAnoPublicacaoDesc(pageable);
        return livros.map(LivroResponseDTO::new);
    }
    
    // Métodos auxiliares
    private void validarLivroDTO(LivroDTO livroDTO) {
        if (livroDTO == null) {
            throw new RuntimeException("Dados do livro não podem ser nulos");
        }
        
        if (livroDTO.getTitulo() == null || livroDTO.getTitulo().trim().isEmpty()) {
            throw new RuntimeException("Título é obrigatório");
        }
        
        if (livroDTO.getAutorId() == null || livroDTO.getAutorId().trim().isEmpty()) {
            throw new RuntimeException("ID do autor é obrigatório");
        }
        
        if (livroDTO.getDisponivel() == null) {
            throw new RuntimeException("Disponibilidade é obrigatória");
        }
        
        if (livroDTO.getIsbn() != null && !livroDTO.getIsbn().matches("^(?:\\d{10}|\\d{13})$")) {
            throw new RuntimeException("ISBN deve ter 10 ou 13 dígitos");
        }
        
        if (livroDTO.getAnoPublicacao() != null && 
            (livroDTO.getAnoPublicacao() < 1000 || livroDTO.getAnoPublicacao() > 2100)) {
            throw new RuntimeException("Ano de publicação deve estar entre 1000 e 2100");
        }
    }
    
    @Transactional(readOnly = true)
    public long contarLivrosPorAutor(String autorId) {
        return livroRepository.countByAutorId(autorId);
    }
    
    @Transactional(readOnly = true)
    public boolean existePorIsbn(String isbn) {
        return livroRepository.existsByIsbn(isbn);
    }
    
    @Transactional(readOnly = true)
    public Optional<Livro> buscarPorTituloExato(String titulo) {
        return livroRepository.findByTituloIgnoreCase(titulo);
    }
}
