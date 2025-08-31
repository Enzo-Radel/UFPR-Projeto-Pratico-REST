package com.enzoradel.atividade_rest.dto;

import com.enzoradel.atividade_rest.model.Emprestimo;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class EmprestimoResponseDTO {
    
    private String id;
    private LocalDateTime dataEmprestimo;
    private LocalDateTime dataDevolucaoPrevista;
    private LocalDateTime dataDevolucaoEfetiva;
    private Boolean devolvido;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Informações do livro
    private String livroId;
    private String livroTitulo;
    private String livroIsbn;
    private String livroAutorNome;
    
    // Informações do usuário
    private String usuarioId;
    private String usuarioNome;
    private String usuarioEmail;
    
    // Campos calculados
    private Boolean atrasado;
    private Long diasAtraso;
    private Long diasRestantes;
    private String status;
    
    // Construtores
    public EmprestimoResponseDTO() {}
    
    public EmprestimoResponseDTO(Emprestimo emprestimo) {
        this.id = emprestimo.getId();
        this.dataEmprestimo = emprestimo.getDataEmprestimo();
        this.dataDevolucaoPrevista = emprestimo.getDataDevolucaoPrevista();
        this.dataDevolucaoEfetiva = emprestimo.getDataDevolucaoEfetiva();
        this.devolvido = emprestimo.getDevolvido();
        this.createdAt = emprestimo.getCreatedAt();
        this.updatedAt = emprestimo.getUpdatedAt();
        
        // Informações do livro
        if (emprestimo.getLivro() != null) {
            this.livroId = emprestimo.getLivro().getId();
            this.livroTitulo = emprestimo.getLivro().getTitulo();
            this.livroIsbn = emprestimo.getLivro().getIsbn();
            if (emprestimo.getLivro().getAutor() != null) {
                this.livroAutorNome = emprestimo.getLivro().getAutor().getNome();
            }
        }
        
        // Informações do usuário
        if (emprestimo.getUsuario() != null) {
            this.usuarioId = emprestimo.getUsuario().getId();
            this.usuarioNome = emprestimo.getUsuario().getNome();
            this.usuarioEmail = emprestimo.getUsuario().getEmail();
        }
        
        // Campos calculados
        this.atrasado = emprestimo.isAtrasado();
        this.diasAtraso = emprestimo.getDiasAtraso();
        
        // Calcular dias restantes
        if (!this.devolvido && this.dataDevolucaoPrevista != null) {
            LocalDateTime agora = LocalDateTime.now();
            if (agora.isBefore(this.dataDevolucaoPrevista)) {
                this.diasRestantes = java.time.Duration.between(agora, this.dataDevolucaoPrevista).toDays();
            } else {
                this.diasRestantes = 0L;
            }
        } else {
            this.diasRestantes = null;
        }
        
        // Status do empréstimo
        if (this.devolvido) {
            this.status = "DEVOLVIDO";
        } else if (this.atrasado) {
            this.status = "ATRASADO";
        } else {
            this.status = "ATIVO";
        }
    }
    
    // Método estático para converter lista
    public static List<EmprestimoResponseDTO> fromEmprestimoList(List<Emprestimo> emprestimos) {
        return emprestimos.stream()
                .map(EmprestimoResponseDTO::new)
                .collect(Collectors.toList());
    }
    
    // Getters e Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public LocalDateTime getDataEmprestimo() {
        return dataEmprestimo;
    }
    
    public void setDataEmprestimo(LocalDateTime dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
    
    public LocalDateTime getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }
    
    public void setDataDevolucaoPrevista(LocalDateTime dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }
    
    public LocalDateTime getDataDevolucaoEfetiva() {
        return dataDevolucaoEfetiva;
    }
    
    public void setDataDevolucaoEfetiva(LocalDateTime dataDevolucaoEfetiva) {
        this.dataDevolucaoEfetiva = dataDevolucaoEfetiva;
    }
    
    public Boolean getDevolvido() {
        return devolvido;
    }
    
    public void setDevolvido(Boolean devolvido) {
        this.devolvido = devolvido;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public String getLivroId() {
        return livroId;
    }
    
    public void setLivroId(String livroId) {
        this.livroId = livroId;
    }
    
    public String getLivroTitulo() {
        return livroTitulo;
    }
    
    public void setLivroTitulo(String livroTitulo) {
        this.livroTitulo = livroTitulo;
    }
    
    public String getLivroIsbn() {
        return livroIsbn;
    }
    
    public void setLivroIsbn(String livroIsbn) {
        this.livroIsbn = livroIsbn;
    }
    
    public String getLivroAutorNome() {
        return livroAutorNome;
    }
    
    public void setLivroAutorNome(String livroAutorNome) {
        this.livroAutorNome = livroAutorNome;
    }
    
    public String getUsuarioId() {
        return usuarioId;
    }
    
    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }
    
    public String getUsuarioNome() {
        return usuarioNome;
    }
    
    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }
    
    public String getUsuarioEmail() {
        return usuarioEmail;
    }
    
    public void setUsuarioEmail(String usuarioEmail) {
        this.usuarioEmail = usuarioEmail;
    }
    
    public Boolean getAtrasado() {
        return atrasado;
    }
    
    public void setAtrasado(Boolean atrasado) {
        this.atrasado = atrasado;
    }
    
    public Long getDiasAtraso() {
        return diasAtraso;
    }
    
    public void setDiasAtraso(Long diasAtraso) {
        this.diasAtraso = diasAtraso;
    }
    
    public Long getDiasRestantes() {
        return diasRestantes;
    }
    
    public void setDiasRestantes(Long diasRestantes) {
        this.diasRestantes = diasRestantes;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "EmprestimoResponseDTO{" +
                "id='" + id + '\'' +
                ", livroTitulo='" + livroTitulo + '\'' +
                ", usuarioNome='" + usuarioNome + '\'' +
                ", devolvido=" + devolvido +
                ", atrasado=" + atrasado +
                ", status='" + status + '\'' +
                '}';
    }
}
