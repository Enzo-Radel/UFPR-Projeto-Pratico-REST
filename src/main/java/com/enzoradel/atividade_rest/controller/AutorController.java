package com.enzoradel.atividade_rest.controller;

import com.enzoradel.atividade_rest.model.Autor;
import com.enzoradel.atividade_rest.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * GET /api/autores - Lista todos os autores
     */
    @GetMapping
    public ResponseEntity<List<Autor>> listarTodos() {
        List<Autor> autores = autorService.listarTodos();
        return ResponseEntity.ok(autores);
    }
    
    /**
     * GET /api/autores/{id} - Busca autor por ID
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
     * POST /api/autores - Cria um novo autor
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
     * PUT /api/autores/{id} - Atualiza um autor existente
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
     * DELETE /api/autores/{id} - Remove um autor
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
}
