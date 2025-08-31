package com.enzoradel.atividade_rest.controller;

import com.enzoradel.atividade_rest.dto.LivroDTO;
import com.enzoradel.atividade_rest.dto.LivroResponseDTO;
import com.enzoradel.atividade_rest.service.LivroService;
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
@RequestMapping("/livros")
@CrossOrigin(origins = "*")
public class LivroController {
    
    @Autowired
    private LivroService livroService;
    
    // CREATE
    @PostMapping
    public ResponseEntity<LivroResponseDTO> criarLivro(@Valid @RequestBody LivroDTO livroDTO) {
        try {
            LivroResponseDTO livroCriado = livroService.criarLivro(livroDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(livroCriado);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar livro: " + e.getMessage());
        }
    }
    
    // READ - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> buscarPorId(@PathVariable String id) {
        try {
            LivroResponseDTO livro = livroService.buscarPorId(id);
            return ResponseEntity.ok(livro);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar livro: " + e.getMessage());
        }
    }
    
    // READ - Listar todos
    @GetMapping
    public ResponseEntity<Page<LivroResponseDTO>> listarTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "titulo") String sort,
            @RequestParam(defaultValue = "asc") String direction) {
        try {
            Sort.Direction sortDirection = Sort.Direction.fromString(direction.toUpperCase());
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sort));
            Page<LivroResponseDTO> livros = livroService.listarTodos(pageable);
            return ResponseEntity.ok(livros);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar livros: " + e.getMessage());
        }
    }
    
    // UPDATE
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
    
    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable String id) {
        try {
            livroService.deletarLivro(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar livro: " + e.getMessage());
        }
    }
    
    // Endpoints de busca
    @GetMapping("/buscar")
    public ResponseEntity<Page<LivroResponseDTO>> buscarPorTitulo(
            @RequestParam String titulo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<LivroResponseDTO> livros = livroService.buscarPorTitulo(titulo, pageable);
            return ResponseEntity.ok(livros);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar livros por título: " + e.getMessage());
        }
    }
    
    @GetMapping("/buscar/exato")
    public ResponseEntity<LivroResponseDTO> buscarPorTituloExato(@RequestParam String titulo) {
        try {
            return livroService.buscarPorTituloExato(titulo)
                    .map(livro -> ResponseEntity.ok(new LivroResponseDTO(livro)))
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar livro por título exato: " + e.getMessage());
        }
    }
    
    @GetMapping("/buscar/isbn")
    public ResponseEntity<Page<LivroResponseDTO>> buscarPorIsbn(
            @RequestParam String isbn,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<LivroResponseDTO> livros = livroService.buscarPorIsbn(isbn, pageable);
            return ResponseEntity.ok(livros);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar livro por ISBN: " + e.getMessage());
        }
    }
    
    @GetMapping("/buscar/autor")
    public ResponseEntity<Page<LivroResponseDTO>> buscarPorAutor(
            @RequestParam String autorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<LivroResponseDTO> livros = livroService.buscarPorAutor(autorId, pageable);
            return ResponseEntity.ok(livros);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar livros por autor: " + e.getMessage());
        }
    }
    
    @GetMapping("/buscar/autor/nome")
    public ResponseEntity<Page<LivroResponseDTO>> buscarPorNomeAutor(
            @RequestParam String nomeAutor,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<LivroResponseDTO> livros = livroService.buscarPorNomeAutor(nomeAutor, pageable);
            return ResponseEntity.ok(livros);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar livros por nome do autor: " + e.getMessage());
        }
    }
    
    @GetMapping("/buscar/ano")
    public ResponseEntity<Page<LivroResponseDTO>> buscarPorAno(
            @RequestParam Integer ano,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<LivroResponseDTO> livros = livroService.buscarPorAno(ano, pageable);
            return ResponseEntity.ok(livros);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar livros por ano: " + e.getMessage());
        }
    }
    
    @GetMapping("/buscar/ano/maior-igual")
    public ResponseEntity<Page<LivroResponseDTO>> buscarPorAnoMaiorOuIgual(
            @RequestParam Integer ano,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<LivroResponseDTO> livros = livroService.buscarPorAnoMaiorOuIgual(ano, pageable);
            return ResponseEntity.ok(livros);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar livros por ano maior ou igual: " + e.getMessage());
        }
    }
    
    @GetMapping("/buscar/ano/entre")
    public ResponseEntity<Page<LivroResponseDTO>> buscarPorAnoEntre(
            @RequestParam Integer anoInicio,
            @RequestParam Integer anoFim,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<LivroResponseDTO> livros = livroService.buscarPorAnoEntre(anoInicio, anoFim, pageable);
            return ResponseEntity.ok(livros);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar livros por ano entre: " + e.getMessage());
        }
    }
    
    @GetMapping("/disponiveis")
    public ResponseEntity<Page<LivroResponseDTO>> buscarDisponiveis(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<LivroResponseDTO> livros = livroService.buscarDisponiveis(pageable);
            return ResponseEntity.ok(livros);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar livros disponíveis: " + e.getMessage());
        }
    }
    
    @GetMapping("/indisponiveis")
    public ResponseEntity<Page<LivroResponseDTO>> buscarIndisponiveis(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<LivroResponseDTO> livros = livroService.buscarIndisponiveis(pageable);
            return ResponseEntity.ok(livros);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar livros indisponíveis: " + e.getMessage());
        }
    }
    
    @GetMapping("/disponiveis-emprestimo")
    public ResponseEntity<Page<LivroResponseDTO>> buscarDisponiveisParaEmprestimo(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<LivroResponseDTO> livros = livroService.buscarDisponiveisParaEmprestimo(pageable);
            return ResponseEntity.ok(livros);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar livros disponíveis para empréstimo: " + e.getMessage());
        }
    }
    
    @GetMapping("/emprestados")
    public ResponseEntity<Page<LivroResponseDTO>> buscarEmprestados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<LivroResponseDTO> livros = livroService.buscarEmprestados(pageable);
            return ResponseEntity.ok(livros);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar livros emprestados: " + e.getMessage());
        }
    }
    
    @GetMapping("/buscar/avancada")
    public ResponseEntity<Page<LivroResponseDTO>> buscarAvancada(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String nomeAutor,
            @RequestParam(required = false) Integer ano,
            @RequestParam(required = false) Boolean disponivel,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<LivroResponseDTO> livros;
            
            if (titulo != null && nomeAutor != null) {
                livros = livroService.buscarPorTituloEAutor(titulo, nomeAutor, pageable);
            } else if (titulo != null && ano != null) {
                livros = livroService.buscarPorTituloEAno(titulo, ano, pageable);
            } else if (titulo != null && disponivel != null) {
                livros = livroService.buscarPorTituloEDisponibilidade(titulo, disponivel, pageable);
            } else if (titulo != null) {
                livros = livroService.buscarPorTitulo(titulo, pageable);
            } else if (nomeAutor != null) {
                livros = livroService.buscarPorNomeAutor(nomeAutor, pageable);
            } else if (ano != null) {
                livros = livroService.buscarPorAno(ano, pageable);
            } else if (disponivel != null) {
                livros = livroService.buscarPorDisponibilidade(disponivel, pageable);
            } else {
                livros = livroService.listarTodos(pageable);
            }
            
            return ResponseEntity.ok(livros);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar livros avançada: " + e.getMessage());
        }
    }
    
    @GetMapping("/buscar/criados-apos")
    public ResponseEntity<Page<LivroResponseDTO>> buscarCriadosApos(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime data,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<LivroResponseDTO> livros = livroService.buscarPorDataCriacao(data, pageable);
            return ResponseEntity.ok(livros);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar livros criados após: " + e.getMessage());
        }
    }
    
    @GetMapping("/buscar/criados-entre")
    public ResponseEntity<Page<LivroResponseDTO>> buscarCriadosEntre(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<LivroResponseDTO> livros = livroService.buscarPorDataCriacaoEntre(dataInicio, dataFim, pageable);
            return ResponseEntity.ok(livros);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar livros criados entre: " + e.getMessage());
        }
    }
    
    @GetMapping("/mais-recentes")
    public ResponseEntity<Page<LivroResponseDTO>> buscarMaisRecentes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<LivroResponseDTO> livros = livroService.buscarMaisRecentes(pageable);
            return ResponseEntity.ok(livros);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar livros mais recentes: " + e.getMessage());
        }
    }
    
    @GetMapping("/mais-antigos")
    public ResponseEntity<Page<LivroResponseDTO>> buscarMaisAntigos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<LivroResponseDTO> livros = livroService.buscarMaisAntigos(pageable);
            return ResponseEntity.ok(livros);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar livros mais antigos: " + e.getMessage());
        }
    }
    
    @GetMapping("/ordenados/titulo")
    public ResponseEntity<Page<LivroResponseDTO>> buscarOrdenadosPorTitulo(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<LivroResponseDTO> livros = livroService.buscarPorTituloOrdenado(pageable);
            return ResponseEntity.ok(livros);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar livros ordenados por título: " + e.getMessage());
        }
    }
    
    @GetMapping("/ordenados/ano")
    public ResponseEntity<Page<LivroResponseDTO>> buscarOrdenadosPorAno(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<LivroResponseDTO> livros = livroService.buscarPorAnoOrdenado(pageable);
            return ResponseEntity.ok(livros);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar livros ordenados por ano: " + e.getMessage());
        }
    }
    
    // Endpoints de estatísticas
    @GetMapping("/estatisticas/autor/{autorId}")
    public ResponseEntity<Map<String, Object>> estatisticasPorAutor(@PathVariable String autorId) {
        try {
            long totalLivros = livroService.contarLivrosPorAutor(autorId);
            Map<String, Object> estatisticas = new HashMap<>();
            estatisticas.put("autorId", autorId);
            estatisticas.put("totalLivros", totalLivros);
            return ResponseEntity.ok(estatisticas);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar estatísticas do autor: " + e.getMessage());
        }
    }
    
    @GetMapping("/verificar/isbn/{isbn}")
    public ResponseEntity<Map<String, Object>> verificarIsbn(@PathVariable String isbn) {
        try {
            boolean existe = livroService.existePorIsbn(isbn);
            Map<String, Object> resultado = new HashMap<>();
            resultado.put("isbn", isbn);
            resultado.put("existe", existe);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao verificar ISBN: " + e.getMessage());
        }
    }
    
    // Health check
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Livros API está funcionando!");
    }
}
