package com.enzoradel.atividade_rest.controller;

import com.enzoradel.atividade_rest.dto.EmprestimoDTO;
import com.enzoradel.atividade_rest.dto.EmprestimoResponseDTO;
import com.enzoradel.atividade_rest.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/emprestimos")
@CrossOrigin(origins = "*")
public class EmprestimoController {
    
    @Autowired
    private EmprestimoService emprestimoService;
    
    /**
     * GET /api/emprestimos - Lista todos os empréstimos
     */
    @GetMapping
    public ResponseEntity<List<EmprestimoResponseDTO>> listarTodos() {
        try {
            List<EmprestimoResponseDTO> emprestimos = emprestimoService.listarTodos();
            return ResponseEntity.ok(emprestimos);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar empréstimos: " + e.getMessage());
        }
    }
    
    /**
     * GET /api/emprestimos/{id} - Busca empréstimo por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoResponseDTO> buscarPorId(@PathVariable String id) {
        try {
            EmprestimoResponseDTO emprestimo = emprestimoService.buscarPorId(id);
            return ResponseEntity.ok(emprestimo);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar empréstimo: " + e.getMessage());
        }
    }
    
    /**
     * POST /api/emprestimos - Cria um novo empréstimo
     */
    @PostMapping
    public ResponseEntity<EmprestimoResponseDTO> criarEmprestimo(@Valid @RequestBody EmprestimoDTO emprestimoDTO) {
        try {
            EmprestimoResponseDTO emprestimoCriado = emprestimoService.criarEmprestimo(emprestimoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(emprestimoCriado);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar empréstimo: " + e.getMessage());
        }
    }
    
    /**
     * PUT /api/emprestimos/{id} - Atualiza um empréstimo existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<EmprestimoResponseDTO> atualizarEmprestimo(
            @PathVariable String id, 
            @Valid @RequestBody EmprestimoDTO emprestimoDTO) {
        try {
            // Para empréstimos, a atualização principal é a devolução
            // Vamos manter a funcionalidade de devolução como PUT
            EmprestimoResponseDTO emprestimoAtualizado = emprestimoService.devolverLivro(id);
            return ResponseEntity.ok(emprestimoAtualizado);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar empréstimo: " + e.getMessage());
        }
    }
    
    /**
     * DELETE /api/emprestimos/{id} - Remove um empréstimo
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmprestimo(@PathVariable String id) {
        try {
            emprestimoService.deletarEmprestimo(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar empréstimo: " + e.getMessage());
        }
    }
}
