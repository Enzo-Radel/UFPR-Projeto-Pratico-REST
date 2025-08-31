package com.enzoradel.atividade_rest.service;

import com.enzoradel.atividade_rest.dto.EmprestimoDTO;
import com.enzoradel.atividade_rest.dto.EmprestimoResponseDTO;
import com.enzoradel.atividade_rest.model.Emprestimo;
import com.enzoradel.atividade_rest.model.Livro;
import com.enzoradel.atividade_rest.model.Usuario;
import com.enzoradel.atividade_rest.repository.EmprestimoRepository;
import com.enzoradel.atividade_rest.repository.LivroRepository;
import com.enzoradel.atividade_rest.repository.UsuarioRepository;
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
public class EmprestimoService {
    
    @Autowired
    private EmprestimoRepository emprestimoRepository;
    
    @Autowired
    private LivroRepository livroRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    // CREATE
    public EmprestimoResponseDTO criarEmprestimo(EmprestimoDTO emprestimoDTO) {
        // Validações
        validarEmprestimoDTO(emprestimoDTO);
        
        // Verificar se o livro existe
        Livro livro = livroRepository.findById(emprestimoDTO.getLivroId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com ID: " + emprestimoDTO.getLivroId()));
        
        // Verificar se o usuário existe
        Usuario usuario = usuarioRepository.findById(emprestimoDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + emprestimoDTO.getUsuarioId()));
        
        // Verificar se o livro está disponível
        if (!livro.getDisponivel()) {
            throw new RuntimeException("Livro não está disponível para empréstimo");
        }
        
        // Verificar se já existe empréstimo ativo para este livro
        if (emprestimoRepository.existsByLivroIdAndUsuarioIdAndDevolvidoFalse(emprestimoDTO.getLivroId(), emprestimoDTO.getUsuarioId())) {
            throw new RuntimeException("Já existe um empréstimo ativo para este livro e usuário");
        }
        
        // Verificar se o livro não tem empréstimos ativos
        long emprestimosAtivos = emprestimoRepository.countByLivroIdAndDevolvidoFalse(emprestimoDTO.getLivroId());
        if (emprestimosAtivos > 0) {
            throw new RuntimeException("Livro já está emprestado");
        }
        
        // Criar empréstimo
        Emprestimo emprestimo = new Emprestimo(livro, usuario, emprestimoDTO.getDataDevolucaoPrevista());
        
        // Marcar livro como indisponível
        livro.setDisponivel(false);
        livroRepository.save(livro);
        
        Emprestimo emprestimoSalvo = emprestimoRepository.save(emprestimo);
        return new EmprestimoResponseDTO(emprestimoSalvo);
    }
    
    // READ - Buscar por ID
    @Transactional(readOnly = true)
    public EmprestimoResponseDTO buscarPorId(String id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado com ID: " + id));
        return new EmprestimoResponseDTO(emprestimo);
    }
    
    // READ - Listar todos com paginação
    @Transactional(readOnly = true)
    public Page<EmprestimoResponseDTO> listarTodos(Pageable pageable) {
        Page<Emprestimo> emprestimos = emprestimoRepository.findAll(pageable);
        return emprestimos.map(EmprestimoResponseDTO::new);
    }
    
    // READ - Listar todos sem paginação
    @Transactional(readOnly = true)
    public List<EmprestimoResponseDTO> listarTodos() {
        List<Emprestimo> emprestimos = emprestimoRepository.findAll();
        return emprestimos.stream()
                .map(EmprestimoResponseDTO::new)
                .collect(Collectors.toList());
    }
    
    // UPDATE - Devolver livro
    public EmprestimoResponseDTO devolverLivro(String id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado com ID: " + id));
        
        if (emprestimo.getDevolvido()) {
            throw new RuntimeException("Empréstimo já foi devolvido");
        }
        
        // Devolver o livro
        emprestimo.devolver();
        
        // Marcar livro como disponível
        Livro livro = emprestimo.getLivro();
        livro.setDisponivel(true);
        livroRepository.save(livro);
        
        Emprestimo emprestimoDevolvido = emprestimoRepository.save(emprestimo);
        return new EmprestimoResponseDTO(emprestimoDevolvido);
    }
    
    // DELETE
    public void deletarEmprestimo(String id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado com ID: " + id));
        
        // Se o empréstimo não foi devolvido, marcar o livro como disponível
        if (!emprestimo.getDevolvido()) {
            Livro livro = emprestimo.getLivro();
            livro.setDisponivel(true);
            livroRepository.save(livro);
        }
        
        emprestimoRepository.delete(emprestimo);
    }
    
    // Métodos de busca
    @Transactional(readOnly = true)
    public Page<EmprestimoResponseDTO> buscarPorLivro(String livroId, Pageable pageable) {
        Page<Emprestimo> emprestimos = emprestimoRepository.findByLivroId(livroId, pageable);
        return emprestimos.map(EmprestimoResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<EmprestimoResponseDTO> buscarPorUsuario(String usuarioId, Pageable pageable) {
        Page<Emprestimo> emprestimos = emprestimoRepository.findByUsuarioId(usuarioId, pageable);
        return emprestimos.map(EmprestimoResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<EmprestimoResponseDTO> buscarPorLivroEUsuario(String livroId, String usuarioId, Pageable pageable) {
        Page<Emprestimo> emprestimos = emprestimoRepository.findByLivroIdAndUsuarioId(livroId, usuarioId, pageable);
        return emprestimos.map(EmprestimoResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<EmprestimoResponseDTO> buscarPorStatus(Boolean devolvido, Pageable pageable) {
        Page<Emprestimo> emprestimos = emprestimoRepository.findByDevolvido(devolvido, pageable);
        return emprestimos.map(EmprestimoResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<EmprestimoResponseDTO> buscarAtivos(Pageable pageable) {
        Page<Emprestimo> emprestimos = emprestimoRepository.findByDevolvidoFalse(pageable);
        return emprestimos.map(EmprestimoResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<EmprestimoResponseDTO> buscarDevolvidos(Pageable pageable) {
        Page<Emprestimo> emprestimos = emprestimoRepository.findByDevolvidoTrue(pageable);
        return emprestimos.map(EmprestimoResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<EmprestimoResponseDTO> buscarAtrasados(Pageable pageable) {
        Page<Emprestimo> emprestimos = emprestimoRepository.findEmprestimosAtrasados(LocalDateTime.now(), pageable);
        return emprestimos.map(EmprestimoResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<EmprestimoResponseDTO> buscarVencemHoje(Pageable pageable) {
        Page<Emprestimo> emprestimos = emprestimoRepository.findEmprestimosVencemHoje(LocalDateTime.now(), pageable);
        return emprestimos.map(EmprestimoResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<EmprestimoResponseDTO> buscarVencemEntre(LocalDateTime dataInicio, LocalDateTime dataFim, Pageable pageable) {
        Page<Emprestimo> emprestimos = emprestimoRepository.findEmprestimosVencemEntre(dataInicio, dataFim, pageable);
        return emprestimos.map(EmprestimoResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<EmprestimoResponseDTO> buscarPorTituloLivro(String tituloLivro, Pageable pageable) {
        Page<Emprestimo> emprestimos = emprestimoRepository.findByTituloLivroContainingIgnoreCase(tituloLivro, pageable);
        return emprestimos.map(EmprestimoResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<EmprestimoResponseDTO> buscarPorNomeUsuario(String nomeUsuario, Pageable pageable) {
        Page<Emprestimo> emprestimos = emprestimoRepository.findByNomeUsuarioContainingIgnoreCase(nomeUsuario, pageable);
        return emprestimos.map(EmprestimoResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<EmprestimoResponseDTO> buscarPorEmailUsuario(String emailUsuario, Pageable pageable) {
        Page<Emprestimo> emprestimos = emprestimoRepository.findByEmailUsuarioContainingIgnoreCase(emailUsuario, pageable);
        return emprestimos.map(EmprestimoResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<EmprestimoResponseDTO> buscarPorNomeAutor(String nomeAutor, Pageable pageable) {
        Page<Emprestimo> emprestimos = emprestimoRepository.findByNomeAutorContainingIgnoreCase(nomeAutor, pageable);
        return emprestimos.map(EmprestimoResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<EmprestimoResponseDTO> buscarAtivosPorLivro(String livroId, Pageable pageable) {
        Page<Emprestimo> emprestimos = emprestimoRepository.findByLivroIdAndDevolvidoFalse(livroId, pageable);
        return emprestimos.map(EmprestimoResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<EmprestimoResponseDTO> buscarAtivosPorUsuario(String usuarioId, Pageable pageable) {
        Page<Emprestimo> emprestimos = emprestimoRepository.findByUsuarioIdAndDevolvidoFalse(usuarioId, pageable);
        return emprestimos.map(EmprestimoResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<EmprestimoResponseDTO> buscarDevolvidosPorLivro(String livroId, Pageable pageable) {
        Page<Emprestimo> emprestimos = emprestimoRepository.findByLivroIdAndDevolvidoTrue(livroId, pageable);
        return emprestimos.map(EmprestimoResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<EmprestimoResponseDTO> buscarDevolvidosPorUsuario(String usuarioId, Pageable pageable) {
        Page<Emprestimo> emprestimos = emprestimoRepository.findByUsuarioIdAndDevolvidoTrue(usuarioId, pageable);
        return emprestimos.map(EmprestimoResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<EmprestimoResponseDTO> buscarPorDataEmprestimo(LocalDateTime data, Pageable pageable) {
        Page<Emprestimo> emprestimos = emprestimoRepository.findByDataEmprestimoAfter(data, pageable);
        return emprestimos.map(EmprestimoResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<EmprestimoResponseDTO> buscarPorDataEmprestimoEntre(LocalDateTime dataInicio, LocalDateTime dataFim, Pageable pageable) {
        Page<Emprestimo> emprestimos = emprestimoRepository.findByDataEmprestimoBetween(dataInicio, dataFim, pageable);
        return emprestimos.map(EmprestimoResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<EmprestimoResponseDTO> buscarMaisRecentes(Pageable pageable) {
        Page<Emprestimo> emprestimos = emprestimoRepository.findAllByOrderByDataEmprestimoDesc(pageable);
        return emprestimos.map(EmprestimoResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<EmprestimoResponseDTO> buscarMaisAntigos(Pageable pageable) {
        Page<Emprestimo> emprestimos = emprestimoRepository.findAllByOrderByDataEmprestimoAsc(pageable);
        return emprestimos.map(EmprestimoResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<EmprestimoResponseDTO> buscarPorDataCriacao(LocalDateTime data, Pageable pageable) {
        Page<Emprestimo> emprestimos = emprestimoRepository.findByCreatedAtAfter(data, pageable);
        return emprestimos.map(EmprestimoResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<EmprestimoResponseDTO> buscarPorDataCriacaoEntre(LocalDateTime dataInicio, LocalDateTime dataFim, Pageable pageable) {
        Page<Emprestimo> emprestimos = emprestimoRepository.findByCreatedAtBetween(dataInicio, dataFim, pageable);
        return emprestimos.map(EmprestimoResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<EmprestimoResponseDTO> buscarCriadosMaisRecentes(Pageable pageable) {
        Page<Emprestimo> emprestimos = emprestimoRepository.findAllByOrderByCreatedAtDesc(pageable);
        return emprestimos.map(EmprestimoResponseDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<EmprestimoResponseDTO> buscarCriadosMaisAntigos(Pageable pageable) {
        Page<Emprestimo> emprestimos = emprestimoRepository.findAllByOrderByCreatedAtAsc(pageable);
        return emprestimos.map(EmprestimoResponseDTO::new);
    }
    
    // Métodos auxiliares
    private void validarEmprestimoDTO(EmprestimoDTO emprestimoDTO) {
        if (emprestimoDTO == null) {
            throw new RuntimeException("Dados do empréstimo não podem ser nulos");
        }
        
        if (emprestimoDTO.getLivroId() == null || emprestimoDTO.getLivroId().trim().isEmpty()) {
            throw new RuntimeException("ID do livro é obrigatório");
        }
        
        if (emprestimoDTO.getUsuarioId() == null || emprestimoDTO.getUsuarioId().trim().isEmpty()) {
            throw new RuntimeException("ID do usuário é obrigatório");
        }
        
        if (emprestimoDTO.getDataDevolucaoPrevista() == null) {
            throw new RuntimeException("Data de devolução prevista é obrigatória");
        }
        
        if (emprestimoDTO.getDataDevolucaoPrevista().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Data de devolução prevista não pode ser no passado");
        }
    }
    
    @Transactional(readOnly = true)
    public long contarEmprestimosAtivosPorLivro(String livroId) {
        return emprestimoRepository.countByLivroIdAndDevolvidoFalse(livroId);
    }
    
    @Transactional(readOnly = true)
    public long contarEmprestimosAtivosPorUsuario(String usuarioId) {
        return emprestimoRepository.countByUsuarioIdAndDevolvidoFalse(usuarioId);
    }
    
    @Transactional(readOnly = true)
    public long contarEmprestimosTotaisPorLivro(String livroId) {
        return emprestimoRepository.countByLivroId(livroId);
    }
    
    @Transactional(readOnly = true)
    public long contarEmprestimosTotaisPorUsuario(String usuarioId) {
        return emprestimoRepository.countByUsuarioId(usuarioId);
    }
    
    @Transactional(readOnly = true)
    public Optional<Emprestimo> buscarEmprestimoAtivoPorLivroEUsuario(String livroId, String usuarioId) {
        return emprestimoRepository.findByLivroIdAndUsuarioIdAndDevolvidoFalse(livroId, usuarioId);
    }
}
