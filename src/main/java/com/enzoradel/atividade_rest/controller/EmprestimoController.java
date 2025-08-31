package com.enzoradel.atividade_rest.controller;

import com.enzoradel.atividade_rest.dto.EmprestimoDTO;
import com.enzoradel.atividade_rest.dto.EmprestimoResponseDTO;
import com.enzoradel.atividade_rest.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/emprestimos")
@CrossOrigin(origins = "*")
public class EmprestimoController {
    
    @Autowired
    private EmprestimoService emprestimoService;
    
    // CREATE
    @PostMapping
    public ResponseEntity<EmprestimoResponseDTO> criarEmprestimo(@Valid @RequestBody EmprestimoDTO emprestimoDTO) {
        try {
            EmprestimoResponseDTO emprestimoCriado = emprestimoService.criarEmprestimo(emprestimoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(emprestimoCriado);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar empréstimo: " + e.getMessage());
        }
    }
    
    // READ - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoResponseDTO> buscarPorId(@PathVariable String id) {
        try {
            EmprestimoResponseDTO emprestimo = emprestimoService.buscarPorId(id);
            return ResponseEntity.ok(emprestimo);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar empréstimo: " + e.getMessage());
        }
    }
    
    // READ - Listar todos
    @GetMapping
    public ResponseEntity<Page<EmprestimoResponseDTO>> listarTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "dataEmprestimo") String sort,
            @RequestParam(defaultValue = "desc") String direction) {
        try {
            Sort.Direction sortDirection = Sort.Direction.fromString(direction.toUpperCase());
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sort));
            Page<EmprestimoResponseDTO> emprestimos = emprestimoService.listarTodos(pageable);
            return ResponseEntity.ok(emprestimos);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar empréstimos: " + e.getMessage());
        }
    }
    
    // UPDATE - Devolver livro
    @PutMapping("/{id}/devolver")
    public ResponseEntity<EmprestimoResponseDTO> devolverLivro(@PathVariable String id) {
        try {
            EmprestimoResponseDTO emprestimoDevolvido = emprestimoService.devolverLivro(id);
            return ResponseEntity.ok(emprestimoDevolvido);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao devolver livro: " + e.getMessage());
        }
    }
    
    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmprestimo(@PathVariable String id) {
        try {
            emprestimoService.deletarEmprestimo(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar empréstimo: " + e.getMessage());
        }
    }
    
    // Endpoints de busca
    @GetMapping("/buscar/livro")
    public ResponseEntity<Page<EmprestimoResponseDTO>> buscarPorLivro(
            @RequestParam String livroId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<EmprestimoResponseDTO> emprestimos = emprestimoService.buscarPorLivro(livroId, pageable);
            return ResponseEntity.ok(emprestimos);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar empréstimos por livro: " + e.getMessage());
        }
    }
    
    @GetMapping("/buscar/usuario")
    public ResponseEntity<Page<EmprestimoResponseDTO>> buscarPorUsuario(
            @RequestParam String usuarioId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<EmprestimoResponseDTO> emprestimos = emprestimoService.buscarPorUsuario(usuarioId, pageable);
            return ResponseEntity.ok(emprestimos);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar empréstimos por usuário: " + e.getMessage());
        }
    }
    
    @GetMapping("/buscar/livro-usuario")
    public ResponseEntity<Page<EmprestimoResponseDTO>> buscarPorLivroEUsuario(
            @RequestParam String livroId,
            @RequestParam String usuarioId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<EmprestimoResponseDTO> emprestimos = emprestimoService.buscarPorLivroEUsuario(livroId, usuarioId, pageable);
            return ResponseEntity.ok(emprestimos);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar empréstimos por livro e usuário: " + e.getMessage());
        }
    }
    
    @GetMapping("/buscar/status")
    public ResponseEntity<Page<EmprestimoResponseDTO>> buscarPorStatus(
            @RequestParam Boolean devolvido,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<EmprestimoResponseDTO> emprestimos = emprestimoService.buscarPorStatus(devolvido, pageable);
            return ResponseEntity.ok(emprestimos);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar empréstimos por status: " + e.getMessage());
        }
    }
    
    @GetMapping("/ativos")
    public ResponseEntity<Page<EmprestimoResponseDTO>> buscarAtivos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<EmprestimoResponseDTO> emprestimos = emprestimoService.buscarAtivos(pageable);
            return ResponseEntity.ok(emprestimos);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar empréstimos ativos: " + e.getMessage());
        }
    }
    
    @GetMapping("/devolvidos")
    public ResponseEntity<Page<EmprestimoResponseDTO>> buscarDevolvidos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<EmprestimoResponseDTO> emprestimos = emprestimoService.buscarDevolvidos(pageable);
            return ResponseEntity.ok(emprestimos);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar empréstimos devolvidos: " + e.getMessage());
        }
    }
    
    @GetMapping("/atrasados")
    public ResponseEntity<Page<EmprestimoResponseDTO>> buscarAtrasados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<EmprestimoResponseDTO> emprestimos = emprestimoService.buscarAtrasados(pageable);
            return ResponseEntity.ok(emprestimos);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar empréstimos atrasados: " + e.getMessage());
        }
    }
    
    @GetMapping("/vencem-hoje")
    public ResponseEntity<Page<EmprestimoResponseDTO>> buscarVencemHoje(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<EmprestimoResponseDTO> emprestimos = emprestimoService.buscarVencemHoje(pageable);
            return ResponseEntity.ok(emprestimos);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar empréstimos que vencem hoje: " + e.getMessage());
        }
    }
    
    @GetMapping("/vencem-entre")
    public ResponseEntity<Page<EmprestimoResponseDTO>> buscarVencemEntre(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<EmprestimoResponseDTO> emprestimos = emprestimoService.buscarVencemEntre(dataInicio, dataFim, pageable);
            return ResponseEntity.ok(emprestimos);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar empréstimos que vencem entre: " + e.getMessage());
        }
    }
    
    @GetMapping("/buscar/titulo-livro")
    public ResponseEntity<Page<EmprestimoResponseDTO>> buscarPorTituloLivro(
            @RequestParam String tituloLivro,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<EmprestimoResponseDTO> emprestimos = emprestimoService.buscarPorTituloLivro(tituloLivro, pageable);
            return ResponseEntity.ok(emprestimos);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar empréstimos por título do livro: " + e.getMessage());
        }
    }
    
    @GetMapping("/buscar/nome-usuario")
    public ResponseEntity<Page<EmprestimoResponseDTO>> buscarPorNomeUsuario(
            @RequestParam String nomeUsuario,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<EmprestimoResponseDTO> emprestimos = emprestimoService.buscarPorNomeUsuario(nomeUsuario, pageable);
            return ResponseEntity.ok(emprestimos);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar empréstimos por nome do usuário: " + e.getMessage());
        }
    }
    
    @GetMapping("/buscar/email-usuario")
    public ResponseEntity<Page<EmprestimoResponseDTO>> buscarPorEmailUsuario(
            @RequestParam String emailUsuario,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<EmprestimoResponseDTO> emprestimos = emprestimoService.buscarPorEmailUsuario(emailUsuario, pageable);
            return ResponseEntity.ok(emprestimos);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar empréstimos por email do usuário: " + e.getMessage());
        }
    }
    
    @GetMapping("/buscar/nome-autor")
    public ResponseEntity<Page<EmprestimoResponseDTO>> buscarPorNomeAutor(
            @RequestParam String nomeAutor,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<EmprestimoResponseDTO> emprestimos = emprestimoService.buscarPorNomeAutor(nomeAutor, pageable);
            return ResponseEntity.ok(emprestimos);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar empréstimos por nome do autor: " + e.getMessage());
        }
    }
    
    @GetMapping("/buscar/ativos/livro")
    public ResponseEntity<Page<EmprestimoResponseDTO>> buscarAtivosPorLivro(
            @RequestParam String livroId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<EmprestimoResponseDTO> emprestimos = emprestimoService.buscarAtivosPorLivro(livroId, pageable);
            return ResponseEntity.ok(emprestimos);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar empréstimos ativos por livro: " + e.getMessage());
        }
    }
    
    @GetMapping("/buscar/ativos/usuario")
    public ResponseEntity<Page<EmprestimoResponseDTO>> buscarAtivosPorUsuario(
            @RequestParam String usuarioId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<EmprestimoResponseDTO> emprestimos = emprestimoService.buscarAtivosPorUsuario(usuarioId, pageable);
            return ResponseEntity.ok(emprestimos);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar empréstimos ativos por usuário: " + e.getMessage());
        }
    }
    
    @GetMapping("/buscar/devolvidos/livro")
    public ResponseEntity<Page<EmprestimoResponseDTO>> buscarDevolvidosPorLivro(
            @RequestParam String livroId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<EmprestimoResponseDTO> emprestimos = emprestimoService.buscarDevolvidosPorLivro(livroId, pageable);
            return ResponseEntity.ok(emprestimos);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar empréstimos devolvidos por livro: " + e.getMessage());
        }
    }
    
    @GetMapping("/buscar/devolvidos/usuario")
    public ResponseEntity<Page<EmprestimoResponseDTO>> buscarDevolvidosPorUsuario(
            @RequestParam String usuarioId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<EmprestimoResponseDTO> emprestimos = emprestimoService.buscarDevolvidosPorUsuario(usuarioId, pageable);
            return ResponseEntity.ok(emprestimos);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar empréstimos devolvidos por usuário: " + e.getMessage());
        }
    }
    
    @GetMapping("/buscar/data-emprestimo")
    public ResponseEntity<Page<EmprestimoResponseDTO>> buscarPorDataEmprestimo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime data,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<EmprestimoResponseDTO> emprestimos = emprestimoService.buscarPorDataEmprestimo(data, pageable);
            return ResponseEntity.ok(emprestimos);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar empréstimos por data de empréstimo: " + e.getMessage());
        }
    }
    
    @GetMapping("/buscar/data-emprestimo-entre")
    public ResponseEntity<Page<EmprestimoResponseDTO>> buscarPorDataEmprestimoEntre(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<EmprestimoResponseDTO> emprestimos = emprestimoService.buscarPorDataEmprestimoEntre(dataInicio, dataFim, pageable);
            return ResponseEntity.ok(emprestimos);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar empréstimos por data de empréstimo entre: " + e.getMessage());
        }
    }
    
    @GetMapping("/mais-recentes")
    public ResponseEntity<Page<EmprestimoResponseDTO>> buscarMaisRecentes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<EmprestimoResponseDTO> emprestimos = emprestimoService.buscarMaisRecentes(pageable);
            return ResponseEntity.ok(emprestimos);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar empréstimos mais recentes: " + e.getMessage());
        }
    }
    
    @GetMapping("/mais-antigos")
    public ResponseEntity<Page<EmprestimoResponseDTO>> buscarMaisAntigos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<EmprestimoResponseDTO> emprestimos = emprestimoService.buscarMaisAntigos(pageable);
            return ResponseEntity.ok(emprestimos);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar empréstimos mais antigos: " + e.getMessage());
        }
    }
    
    @GetMapping("/buscar/criados-apos")
    public ResponseEntity<Page<EmprestimoResponseDTO>> buscarCriadosApos(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime data,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<EmprestimoResponseDTO> emprestimos = emprestimoService.buscarPorDataCriacao(data, pageable);
            return ResponseEntity.ok(emprestimos);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar empréstimos criados após: " + e.getMessage());
        }
    }
    
    @GetMapping("/buscar/criados-entre")
    public ResponseEntity<Page<EmprestimoResponseDTO>> buscarCriadosEntre(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<EmprestimoResponseDTO> emprestimos = emprestimoService.buscarPorDataCriacaoEntre(dataInicio, dataFim, pageable);
            return ResponseEntity.ok(emprestimos);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar empréstimos criados entre: " + e.getMessage());
        }
    }
    
    @GetMapping("/criados-mais-recentes")
    public ResponseEntity<Page<EmprestimoResponseDTO>> buscarCriadosMaisRecentes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<EmprestimoResponseDTO> emprestimos = emprestimoService.buscarCriadosMaisRecentes(pageable);
            return ResponseEntity.ok(emprestimos);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar empréstimos criados mais recentes: " + e.getMessage());
        }
    }
    
    @GetMapping("/criados-mais-antigos")
    public ResponseEntity<Page<EmprestimoResponseDTO>> buscarCriadosMaisAntigos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<EmprestimoResponseDTO> emprestimos = emprestimoService.buscarCriadosMaisAntigos(pageable);
            return ResponseEntity.ok(emprestimos);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar empréstimos criados mais antigos: " + e.getMessage());
        }
    }
    
    // Endpoints de estatísticas
    @GetMapping("/estatisticas/livro/{livroId}")
    public ResponseEntity<Map<String, Object>> estatisticasPorLivro(@PathVariable String livroId) {
        try {
            long totalEmprestimos = emprestimoService.contarEmprestimosTotaisPorLivro(livroId);
            long emprestimosAtivos = emprestimoService.contarEmprestimosAtivosPorLivro(livroId);
            long emprestimosDevolvidos = totalEmprestimos - emprestimosAtivos;
            
            Map<String, Object> estatisticas = new HashMap<>();
            estatisticas.put("livroId", livroId);
            estatisticas.put("totalEmprestimos", totalEmprestimos);
            estatisticas.put("emprestimosAtivos", emprestimosAtivos);
            estatisticas.put("emprestimosDevolvidos", emprestimosDevolvidos);
            return ResponseEntity.ok(estatisticas);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar estatísticas do livro: " + e.getMessage());
        }
    }
    
    @GetMapping("/estatisticas/usuario/{usuarioId}")
    public ResponseEntity<Map<String, Object>> estatisticasPorUsuario(@PathVariable String usuarioId) {
        try {
            long totalEmprestimos = emprestimoService.contarEmprestimosTotaisPorUsuario(usuarioId);
            long emprestimosAtivos = emprestimoService.contarEmprestimosAtivosPorUsuario(usuarioId);
            long emprestimosDevolvidos = totalEmprestimos - emprestimosAtivos;
            
            Map<String, Object> estatisticas = new HashMap<>();
            estatisticas.put("usuarioId", usuarioId);
            estatisticas.put("totalEmprestimos", totalEmprestimos);
            estatisticas.put("emprestimosAtivos", emprestimosAtivos);
            estatisticas.put("emprestimosDevolvidos", emprestimosDevolvidos);
            return ResponseEntity.ok(estatisticas);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar estatísticas do usuário: " + e.getMessage());
        }
    }
    
    // Health check
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Empréstimos API está funcionando!");
    }
}
