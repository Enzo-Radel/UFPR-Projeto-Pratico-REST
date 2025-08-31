package com.enzoradel.atividade_rest.dto;

import com.enzoradel.atividade_rest.model.Livro;
import com.enzoradel.atividade_rest.model.Autor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class LivroResponseDTO {
    
    private String id;
    private String titulo;
    private String isbn;
    private Integer anoPublicacao;
    private Boolean disponivel;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Informações do autor
    private String autorId;
    private String autorNome;
    private String autorBiografia;
    
    // Campos calculados
    private Boolean disponivelParaEmprestimo;
    private Integer totalEmprestimos;
    private Boolean temEmprestimosAtivos;
    private String statusEmprestimo;
    
    // Construtores
    public LivroResponseDTO() {}
    
    public LivroResponseDTO(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.isbn = livro.getIsbn();
        this.anoPublicacao = livro.getAnoPublicacao();
        this.disponivel = livro.getDisponivel();
        this.createdAt = livro.getCreatedAt();
        this.updatedAt = livro.getUpdatedAt();
        
        // Informações do autor
        if (livro.getAutor() != null) {
            this.autorId = livro.getAutor().getId();
            this.autorNome = livro.getAutor().getNome();
            this.autorBiografia = livro.getAutor().getBiografia();
        }
        
        // Campos calculados
        this.disponivelParaEmprestimo = livro.isDisponivelParaEmprestimo();
        this.totalEmprestimos = livro.getEmprestimos() != null ? livro.getEmprestimos().size() : 0;
        this.temEmprestimosAtivos = livro.getEmprestimos() != null && 
                                   livro.getEmprestimos().stream().anyMatch(e -> e.getDataDevolucaoEfetiva() == null);
        
        // Status do empréstimo
        if (this.temEmprestimosAtivos) {
            this.statusEmprestimo = "EMPRESTADO";
        } else if (this.disponivel) {
            this.statusEmprestimo = "DISPONÍVEL";
        } else {
            this.statusEmprestimo = "INDISPONÍVEL";
        }
    }
    
    // Método estático para converter lista
    public static List<LivroResponseDTO> fromLivroList(List<Livro> livros) {
        return livros.stream()
                .map(LivroResponseDTO::new)
                .collect(Collectors.toList());
    }
    
    // Getters e Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }
    
    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }
    
    public Boolean getDisponivel() {
        return disponivel;
    }
    
    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
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
    
    public String getAutorId() {
        return autorId;
    }
    
    public void setAutorId(String autorId) {
        this.autorId = autorId;
    }
    
    public String getAutorNome() {
        return autorNome;
    }
    
    public void setAutorNome(String autorNome) {
        this.autorNome = autorNome;
    }
    
    public String getAutorBiografia() {
        return autorBiografia;
    }
    
    public void setAutorBiografia(String autorBiografia) {
        this.autorBiografia = autorBiografia;
    }
    
    public Boolean getDisponivelParaEmprestimo() {
        return disponivelParaEmprestimo;
    }
    
    public void setDisponivelParaEmprestimo(Boolean disponivelParaEmprestimo) {
        this.disponivelParaEmprestimo = disponivelParaEmprestimo;
    }
    
    public Integer getTotalEmprestimos() {
        return totalEmprestimos;
    }
    
    public void setTotalEmprestimos(Integer totalEmprestimos) {
        this.totalEmprestimos = totalEmprestimos;
    }
    
    public Boolean getTemEmprestimosAtivos() {
        return temEmprestimosAtivos;
    }
    
    public void setTemEmprestimosAtivos(Boolean temEmprestimosAtivos) {
        this.temEmprestimosAtivos = temEmprestimosAtivos;
    }
    
    public String getStatusEmprestimo() {
        return statusEmprestimo;
    }
    
    public void setStatusEmprestimo(String statusEmprestimo) {
        this.statusEmprestimo = statusEmprestimo;
    }
    
    @Override
    public String toString() {
        return "LivroResponseDTO{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", isbn='" + isbn + '\'' +
                ", anoPublicacao=" + anoPublicacao +
                ", disponivel=" + disponivel +
                ", autorNome='" + autorNome + '\'' +
                ", disponivelParaEmprestimo=" + disponivelParaEmprestimo +
                ", statusEmprestimo='" + statusEmprestimo + '\'' +
                '}';
    }
}
