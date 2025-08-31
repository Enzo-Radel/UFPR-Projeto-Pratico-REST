package com.enzoradel.atividade_rest.controller;

import com.enzoradel.atividade_rest.model.Autor;
import com.enzoradel.atividade_rest.service.AutorService;
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
@RequestMapping("/autores")
@CrossOrigin(origins = "*")
public class AutorController {
    
    private final AutorService autorService;
    
    @Autowired
    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }
    
    /**
     * Lista todos os autores
     * GET /api/autores
     */
    @GetMapping
    public ResponseEntity<List<Autor>> listarTodos() {
        List<Autor> autores = autorService.listarTodos();
        return ResponseEntity.ok(autores);
    }
    
    /**
     * Lista autores com paginação
     * GET /api/autores?page=0&size=10&sort=nome,asc
     */
    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<Autor>> listarComPaginacao(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String sort,
            @RequestParam(defaultValue = "asc") String direction) {
        
        Sort.Direction sortDirection = Sort.Direction.fromString(direction.toUpperCase());
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sort));
        
        Page<Autor> autores = autorService.listarComPaginacao(pageable);
        return ResponseEntity.ok(autores);
    }
    
    /**
     * Busca autor por ID
     * GET /api/autores/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscarPorId(@PathVariable String id) {
        Optional<Autor> autor = autorService.buscarPorId(id);
        
        if (autor.isPresent()) {
            return ResponseEntity.ok(autor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Busca autor por nome
     * GET /api/autores/nome?nome=Machado
     */
    @GetMapping("/nome")
    public ResponseEntity<List<Autor>> buscarPorNome(@RequestParam String nome) {
        List<Autor> autores = autorService.buscarPorNome(nome);
        return ResponseEntity.ok(autores);
    }
    
    /**
     * Busca autor por nome com paginação
     * GET /api/autores/nome?nome=Machado&page=0&size=10
     */
    @GetMapping(value = "/nome", params = {"nome", "page", "size"})
    public ResponseEntity<Page<Autor>> buscarPorNomeComPaginacao(
            @RequestParam String nome,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Autor> autores = autorService.buscarPorNomeComPaginacao(nome, pageable);
        return ResponseEntity.ok(autores);
    }
    
    /**
     * Busca autor por nome exato
     * GET /api/autores/nome-exato?nome=Machado de Assis
     */
    @GetMapping("/nome-exato")
    public ResponseEntity<Autor> buscarPorNomeExato(@RequestParam String nome) {
        Optional<Autor> autor = autorService.buscarPorNomeExato(nome);
        
        if (autor.isPresent()) {
            return ResponseEntity.ok(autor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Busca flexível por nome ou biografia
     * GET /api/autores/busca?termo=romance
     */
    @GetMapping("/busca")
    public ResponseEntity<List<Autor>> buscarPorNomeOuBiografia(@RequestParam String termo) {
        List<Autor> autores = autorService.buscarPorNomeOuBiografia(termo);
        return ResponseEntity.ok(autores);
    }
    
    /**
     * Busca flexível por nome ou biografia com paginação
     * GET /api/autores/busca?termo=romance&page=0&size=10
     */
    @GetMapping(value = "/busca", params = {"termo", "page", "size"})
    public ResponseEntity<Page<Autor>> buscarPorNomeOuBiografiaComPaginacao(
            @RequestParam String termo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Autor> autores = autorService.buscarPorNomeOuBiografiaComPaginacao(termo, pageable);
        return ResponseEntity.ok(autores);
    }
    
    /**
     * Cria um novo autor
     * POST /api/autores
     */
    @PostMapping
    public ResponseEntity<Autor> criar(@Valid @RequestBody Autor autor) {
        try {
            Autor autorCriado = autorService.criar(autor);
            return ResponseEntity.status(HttpStatus.CREATED).body(autorCriado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * Atualiza um autor existente
     * PUT /api/autores/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Autor> atualizar(@PathVariable String id, @Valid @RequestBody Autor autor) {
        try {
            Autor autorAtualizado = autorService.atualizar(id, autor);
            return ResponseEntity.ok(autorAtualizado);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("não encontrado")) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        }
    }
    
    /**
     * Remove um autor
     * DELETE /api/autores/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable String id) {
        try {
            autorService.remover(id);
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
     * Busca autores com livros
     * GET /api/autores/com-livros
     */
    @GetMapping("/com-livros")
    public ResponseEntity<List<Autor>> buscarComLivros() {
        List<Autor> autores = autorService.buscarComLivros();
        return ResponseEntity.ok(autores);
    }
    
    /**
     * Busca autores sem livros
     * GET /api/autores/sem-livros
     */
    @GetMapping("/sem-livros")
    public ResponseEntity<List<Autor>> buscarSemLivros() {
        List<Autor> autores = autorService.buscarSemLivros();
        return ResponseEntity.ok(autores);
    }
    
    /**
     * Busca autores com livros disponíveis
     * GET /api/autores/com-livros-disponiveis
     */
    @GetMapping("/com-livros-disponiveis")
    public ResponseEntity<List<Autor>> buscarComLivrosDisponiveis() {
        List<Autor> autores = autorService.buscarComLivrosDisponiveis();
        return ResponseEntity.ok(autores);
    }
    
    /**
     * Busca autores com livros emprestados
     * GET /api/autores/com-livros-emprestados
     */
    @GetMapping("/com-livros-emprestados")
    public ResponseEntity<List<Autor>> buscarComLivrosEmprestados() {
        List<Autor> autores = autorService.buscarComLivrosEmprestados();
        return ResponseEntity.ok(autores);
    }
    
    /**
     * Conta livros de um autor
     * GET /api/autores/{id}/livros/contagem
     */
    @GetMapping("/{id}/livros/contagem")
    public ResponseEntity<Long> contarLivros(@PathVariable String id) {
        long contagem = autorService.contarLivros(id);
        return ResponseEntity.ok(contagem);
    }
    
    /**
     * Verifica se um autor tem livros
     * GET /api/autores/{id}/tem-livros
     */
    @GetMapping("/{id}/tem-livros")
    public ResponseEntity<Boolean> temLivros(@PathVariable String id) {
        boolean temLivros = autorService.temLivros(id);
        return ResponseEntity.ok(temLivros);
    }
    
    /**
     * Busca autores criados em um período
     * GET /api/autores/periodo?inicio=2024-01-01T00:00:00&fim=2024-12-31T23:59:59
     */
    @GetMapping("/periodo")
    public ResponseEntity<List<Autor>> buscarPorPeriodoCriacao(
            @RequestParam LocalDateTime inicio,
            @RequestParam LocalDateTime fim) {
        
        List<Autor> autores = autorService.buscarPorPeriodoCriacao(inicio, fim);
        return ResponseEntity.ok(autores);
    }
    
    /**
     * Conta autores criados em um período
     * GET /api/autores/periodo/contagem?inicio=2024-01-01T00:00:00&fim=2024-12-31T23:59:59
     */
    @GetMapping("/periodo/contagem")
    public ResponseEntity<Long> contarPorPeriodoCriacao(
            @RequestParam LocalDateTime inicio,
            @RequestParam LocalDateTime fim) {
        
        long contagem = autorService.contarPorPeriodoCriacao(inicio, fim);
        return ResponseEntity.ok(contagem);
    }
    
    /**
     * Busca autores com biografia
     * GET /api/autores/com-biografia
     */
    @GetMapping("/com-biografia")
    public ResponseEntity<List<Autor>> buscarComBiografia() {
        List<Autor> autores = autorService.buscarComBiografia();
        return ResponseEntity.ok(autores);
    }
    
    /**
     * Busca autores sem biografia
     * GET /api/autores/sem-biografia
     */
    @GetMapping("/sem-biografia")
    public ResponseEntity<List<Autor>> buscarSemBiografia() {
        List<Autor> autores = autorService.buscarSemBiografia();
        return ResponseEntity.ok(autores);
    }
    
    /**
     * Health check para autores
     * GET /api/autores/health
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Autores API está funcionando!");
    }
}
