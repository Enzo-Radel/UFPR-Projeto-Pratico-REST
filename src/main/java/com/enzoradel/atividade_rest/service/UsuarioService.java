package com.enzoradel.atividade_rest.service;

import com.enzoradel.atividade_rest.model.Usuario;
import com.enzoradel.atividade_rest.repository.UsuarioRepository;
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
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    /**
     * Lista todos os usuários
     * @return lista de todos os usuários
     */
    @Transactional(readOnly = true)
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAllByOrderByNomeAsc();
    }
    
    /**
     * Lista usuários com paginação
     * @param pageable configurações de paginação
     * @return página de usuários
     */
    @Transactional(readOnly = true)
    public Page<Usuario> listarComPaginacao(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }
    
    /**
     * Busca usuário por ID
     * @param id ID do usuário
     * @return Optional contendo o usuário se encontrado
     */
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorId(String id) {
        return usuarioRepository.findById(id);
    }
    
    /**
     * Busca usuário por email
     * @param email email do usuário
     * @return Optional contendo o usuário se encontrado
     */
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
    
    /**
     * Busca usuários por nome
     * @param nome nome ou parte do nome
     * @return lista de usuários encontrados
     */
    @Transactional(readOnly = true)
    public List<Usuario> buscarPorNome(String nome) {
        return usuarioRepository.findByNomeContainingIgnoreCase(nome);
    }
    
    /**
     * Busca usuários por nome com paginação
     * @param nome nome ou parte do nome
     * @param pageable configurações de paginação
     * @return página de usuários encontrados
     */
    @Transactional(readOnly = true)
    public Page<Usuario> buscarPorNomeComPaginacao(String nome, Pageable pageable) {
        return usuarioRepository.findByNomeContainingIgnoreCase(nome, pageable);
    }
    
    /**
     * Busca flexível por nome ou email
     * @param termo termo de busca
     * @return lista de usuários encontrados
     */
    @Transactional(readOnly = true)
    public List<Usuario> buscarPorNomeOuEmail(String termo) {
        return usuarioRepository.findByNomeOrEmailContainingIgnoreCase(termo);
    }
    
    /**
     * Busca flexível por nome ou email com paginação
     * @param termo termo de busca
     * @param pageable configurações de paginação
     * @return página de usuários encontrados
     */
    @Transactional(readOnly = true)
    public Page<Usuario> buscarPorNomeOuEmailComPaginacao(String termo, Pageable pageable) {
        return usuarioRepository.findByNomeOrEmailContainingIgnoreCase(termo, pageable);
    }
    
    /**
     * Cria um novo usuário
     * @param usuario dados do usuário
     * @return usuário criado
     * @throws IllegalArgumentException se email já existir
     */
    public Usuario criar(Usuario usuario) {
        validarUsuario(usuario);
        
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado: " + usuario.getEmail());
        }
        
        usuario.setCreatedAt(LocalDateTime.now());
        usuario.setUpdatedAt(LocalDateTime.now());
        
        return usuarioRepository.save(usuario);
    }
    
    /**
     * Atualiza um usuário existente
     * @param id ID do usuário
     * @param usuario dados atualizados
     * @return usuário atualizado
     * @throws IllegalArgumentException se usuário não encontrado ou email já existir
     */
    public Usuario atualizar(String id, Usuario usuario) {
        validarUsuario(usuario);
        
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + id));
        
        // Verifica se o email foi alterado e se já existe
        if (!usuarioExistente.getEmail().equals(usuario.getEmail()) && 
            usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado: " + usuario.getEmail());
        }
        
        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setUpdatedAt(LocalDateTime.now());
        
        return usuarioRepository.save(usuarioExistente);
    }
    
    /**
     * Remove um usuário
     * @param id ID do usuário
     * @throws IllegalArgumentException se usuário não encontrado ou tiver empréstimos ativos
     */
    public void remover(String id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + id));
        
        // Verifica se o usuário tem empréstimos ativos
        long emprestimosAtivos = usuarioRepository.countEmprestimosAtivosByUsuarioId(id);
        if (emprestimosAtivos > 0) {
            throw new IllegalArgumentException("Não é possível remover usuário com empréstimos ativos. " +
                    "Total de empréstimos ativos: " + emprestimosAtivos);
        }
        
        usuarioRepository.deleteById(id);
    }
    
    /**
     * Busca usuários com empréstimos ativos
     * @return lista de usuários com empréstimos ativos
     */
    @Transactional(readOnly = true)
    public List<Usuario> buscarComEmprestimosAtivos() {
        return usuarioRepository.findUsuariosComEmprestimosAtivos();
    }
    
    /**
     * Busca usuários com empréstimos atrasados
     * @return lista de usuários com empréstimos atrasados
     */
    @Transactional(readOnly = true)
    public List<Usuario> buscarComEmprestimosAtrasados() {
        return usuarioRepository.findUsuariosComEmprestimosAtrasados(LocalDateTime.now());
    }
    
    /**
     * Busca usuários sem empréstimos ativos
     * @return lista de usuários sem empréstimos ativos
     */
    @Transactional(readOnly = true)
    public List<Usuario> buscarSemEmprestimosAtivos() {
        return usuarioRepository.findUsuariosSemEmprestimosAtivos();
    }
    
    /**
     * Conta empréstimos ativos de um usuário
     * @param usuarioId ID do usuário
     * @return número de empréstimos ativos
     */
    @Transactional(readOnly = true)
    public long contarEmprestimosAtivos(String usuarioId) {
        return usuarioRepository.countEmprestimosAtivosByUsuarioId(usuarioId);
    }
    
    /**
     * Verifica se um usuário pode fazer empréstimos
     * @param usuarioId ID do usuário
     * @return true se pode fazer empréstimos, false caso contrário
     */
    @Transactional(readOnly = true)
    public boolean podeFazerEmprestimos(String usuarioId) {
        long emprestimosAtivos = usuarioRepository.countEmprestimosAtivosByUsuarioId(usuarioId);
        // Limite de 3 empréstimos ativos por usuário
        return emprestimosAtivos < 3;
    }
    
    /**
     * Busca usuários criados em um período
     * @param dataInicio data de início
     * @param dataFim data de fim
     * @return lista de usuários criados no período
     */
    @Transactional(readOnly = true)
    public List<Usuario> buscarPorPeriodoCriacao(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return usuarioRepository.findByCreatedAtBetween(dataInicio, dataFim);
    }
    
    /**
     * Conta usuários criados em um período
     * @param dataInicio data de início
     * @param dataFim data de fim
     * @return número de usuários criados no período
     */
    @Transactional(readOnly = true)
    public long contarPorPeriodoCriacao(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return usuarioRepository.countByCreatedAtBetween(dataInicio, dataFim);
    }
    
    /**
     * Valida os dados do usuário
     * @param usuario usuário a ser validado
     * @throws IllegalArgumentException se dados inválidos
     */
    private void validarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }
        
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        
        if (usuario.getNome().trim().length() < 2) {
            throw new IllegalArgumentException("Nome deve ter pelo menos 2 caracteres");
        }
        
        if (usuario.getNome().trim().length() > 255) {
            throw new IllegalArgumentException("Nome deve ter no máximo 255 caracteres");
        }
        
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email é obrigatório");
        }
        
        if (!isEmailValido(usuario.getEmail())) {
            throw new IllegalArgumentException("Email inválido: " + usuario.getEmail());
        }
    }
    
    /**
     * Valida formato de email
     * @param email email a ser validado
     * @return true se válido, false caso contrário
     */
    private boolean isEmailValido(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }
}
