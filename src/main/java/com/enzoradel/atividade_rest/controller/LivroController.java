package com.enzoradel.atividade_rest.controller;

import com.enzoradel.atividade_rest.dto.LivroDTO;
import com.enzoradel.atividade_rest.dto.LivroResponseDTO;
import com.enzoradel.atividade_rest.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/livros")
@CrossOrigin(origins = "*")
public class LivroController {
    
    @Autowired
    private LivroService livroService;
    
    /**
     * GET /api/livros - Lista todos os livros
     */
    @GetMapping
    public ResponseEntity<List<LivroResponseDTO>> listarTodos() {
        try {
            List<LivroResponseDTO> livros = livroService.listarTodos();
            return ResponseEntity.ok(livros);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar livros: " + e.getMessage());
        }
    }
    
    /**
     * GET /api/livros/{id} - Busca livro por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> buscarPorId(@PathVariable String id) {
        try {
            LivroResponseDTO livro = livroService.buscarPorId(id);
            return ResponseEntity.ok(livro);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar livro: " + e.getMessage());
        }
    }
    
    /**
     * POST /api/livros - Cria um novo livro
     */
    @PostMapping
    public ResponseEntity<LivroResponseDTO> criarLivro(@Valid @RequestBody LivroDTO livroDTO) {
        try {
            LivroResponseDTO livroCriado = livroService.criarLivro(livroDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(livroCriado);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar livro: " + e.getMessage());
        }
    }
    
    /**
     * PUT /api/livros/{id} - Atualiza um livro existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> atualizarLivro(
            @PathVariable String id, 
            @Valid @RequestBody LivroDTO livroDTO) {
        try {
            LivroResponseDTO livroAtualizado = livroService.atualizarLivro(id, livroDTO);
            return ResponseEntity.ok(livroAtualizado);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar livro: " + e.getMessage());
        }
    }
    
    /**
     * DELETE /api/livros/{id} - Remove um livro
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable String id) {
        try {
            livroService.deletarLivro(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar livro: " + e.getMessage());
        }
    }
}
