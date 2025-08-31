package com.enzoradel.atividade_rest.controller;

import com.enzoradel.atividade_rest.model.Usuario;
import com.enzoradel.atividade_rest.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {
    
    private final UsuarioService usuarioService;
    
    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    /**
     * Lista todos os usuários
     * GET /api/usuarios
     */
    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        List<Usuario> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }
    
    /**
     * Lista usuários com paginação
     * GET /api/usuarios?page=0&size=10&sort=nome,asc
     */
    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<Usuario>> listarComPaginacao(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String sort,
            @RequestParam(defaultValue = "asc") String direction) {
        
        Sort.Direction sortDirection = Sort.Direction.fromString(direction.toUpperCase());
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sort));
        
        Page<Usuario> usuarios = usuarioService.listarComPaginacao(pageable);
        return ResponseEntity.ok(usuarios);
    }
    
    /**
     * Busca usuário por ID
     * GET /api/usuarios/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable String id) {
        Optional<Usuario> usuario = usuarioService.buscarPorId(id);
        
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Busca usuário por email
     * GET /api/usuarios/email/{email}
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> buscarPorEmail(@PathVariable String email) {
        Optional<Usuario> usuario = usuarioService.buscarPorEmail(email);
        
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Busca usuários por nome
     * GET /api/usuarios/nome?nome=João
     */
    @GetMapping("/nome")
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam String nome) {
        List<Usuario> usuarios = usuarioService.buscarPorNome(nome);
        return ResponseEntity.ok(usuarios);
    }
    
    /**
     * Busca usuários por nome com paginação
     * GET /api/usuarios/nome?nome=João&page=0&size=10
     */
    @GetMapping(value = "/nome", params = {"nome", "page", "size"})
    public ResponseEntity<Page<Usuario>> buscarPorNomeComPaginacao(
            @RequestParam String nome,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Usuario> usuarios = usuarioService.buscarPorNomeComPaginacao(nome, pageable);
        return ResponseEntity.ok(usuarios);
    }
    
    /**
     * Busca flexível por nome ou email
     * GET /api/usuarios/busca?termo=joão
     */
    @GetMapping("/busca")
    public ResponseEntity<List<Usuario>> buscarPorNomeOuEmail(@RequestParam String termo) {
        List<Usuario> usuarios = usuarioService.buscarPorNomeOuEmail(termo);
        return ResponseEntity.ok(usuarios);
    }
    
    /**
     * Busca flexível por nome ou email com paginação
     * GET /api/usuarios/busca?termo=joão&page=0&size=10
     */
    @GetMapping(value = "/busca", params = {"termo", "page", "size"})
    public ResponseEntity<Page<Usuario>> buscarPorNomeOuEmailComPaginacao(
            @RequestParam String termo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Usuario> usuarios = usuarioService.buscarPorNomeOuEmailComPaginacao(termo, pageable);
        return ResponseEntity.ok(usuarios);
    }
    
    /**
     * Cria um novo usuário
     * POST /api/usuarios
     */
    @PostMapping
    public ResponseEntity<Usuario> criar(@Valid @RequestBody Usuario usuario) {
        try {
            Usuario usuarioCriado = usuarioService.criar(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * Atualiza um usuário existente
     * PUT /api/usuarios/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable String id, @Valid @RequestBody Usuario usuario) {
        try {
            Usuario usuarioAtualizado = usuarioService.atualizar(id, usuario);
            return ResponseEntity.ok(usuarioAtualizado);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("não encontrado")) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        }
    }
    
    /**
     * Remove um usuário
     * DELETE /api/usuarios/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable String id) {
        try {
            usuarioService.remover(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("não encontrado")) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        }
    }
    
    /**
     * Busca usuários com empréstimos ativos
     * GET /api/usuarios/emprestimos-ativos
     */
    @GetMapping("/emprestimos-ativos")
    public ResponseEntity<List<Usuario>> buscarComEmprestimosAtivos() {
        List<Usuario> usuarios = usuarioService.buscarComEmprestimosAtivos();
        return ResponseEntity.ok(usuarios);
    }
    
    /**
     * Busca usuários com empréstimos atrasados
     * GET /api/usuarios/emprestimos-atrasados
     */
    @GetMapping("/emprestimos-atrasados")
    public ResponseEntity<List<Usuario>> buscarComEmprestimosAtrasados() {
        List<Usuario> usuarios = usuarioService.buscarComEmprestimosAtrasados();
        return ResponseEntity.ok(usuarios);
    }
    
    /**
     * Busca usuários sem empréstimos ativos
     * GET /api/usuarios/sem-emprestimos-ativos
     */
    @GetMapping("/sem-emprestimos-ativos")
    public ResponseEntity<List<Usuario>> buscarSemEmprestimosAtivos() {
        List<Usuario> usuarios = usuarioService.buscarSemEmprestimosAtivos();
        return ResponseEntity.ok(usuarios);
    }
    
    /**
     * Conta empréstimos ativos de um usuário
     * GET /api/usuarios/{id}/emprestimos-ativos/contagem
     */
    @GetMapping("/{id}/emprestimos-ativos/contagem")
    public ResponseEntity<Long> contarEmprestimosAtivos(@PathVariable String id) {
        long contagem = usuarioService.contarEmprestimosAtivos(id);
        return ResponseEntity.ok(contagem);
    }
    
    /**
     * Verifica se um usuário pode fazer empréstimos
     * GET /api/usuarios/{id}/pode-emprestar
     */
    @GetMapping("/{id}/pode-emprestar")
    public ResponseEntity<Boolean> podeFazerEmprestimos(@PathVariable String id) {
        boolean podeEmprestar = usuarioService.podeFazerEmprestimos(id);
        return ResponseEntity.ok(podeEmprestar);
    }
    
    /**
     * Busca usuários criados em um período
     * GET /api/usuarios/periodo?inicio=2024-01-01T00:00:00&fim=2024-12-31T23:59:59
     */
    @GetMapping("/periodo")
    public ResponseEntity<List<Usuario>> buscarPorPeriodoCriacao(
            @RequestParam LocalDateTime inicio,
            @RequestParam LocalDateTime fim) {
        
        List<Usuario> usuarios = usuarioService.buscarPorPeriodoCriacao(inicio, fim);
        return ResponseEntity.ok(usuarios);
    }
    
    /**
     * Conta usuários criados em um período
     * GET /api/usuarios/periodo/contagem?inicio=2024-01-01T00:00:00&fim=2024-12-31T23:59:59
     */
    @GetMapping("/periodo/contagem")
    public ResponseEntity<Long> contarPorPeriodoCriacao(
            @RequestParam LocalDateTime inicio,
            @RequestParam LocalDateTime fim) {
        
        long contagem = usuarioService.contarPorPeriodoCriacao(inicio, fim);
        return ResponseEntity.ok(contagem);
    }
    
    /**
     * Health check para usuários
     * GET /api/usuarios/health
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Usuários API está funcionando!");
    }
}
