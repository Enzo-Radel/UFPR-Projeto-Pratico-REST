package com.enzoradel.atividade_rest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "emprestimos")
public class Emprestimo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @NotNull(message = "Livro é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;
    
    @NotNull(message = "Usuário é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    
    @Column(name = "data_emprestimo", nullable = false, updatable = false)
    private LocalDateTime dataEmprestimo;
    
    @Column(name = "data_devolucao_prevista")
    private LocalDateTime dataDevolucaoPrevista;
    
    @Column(name = "data_devolucao_efetiva")
    private LocalDateTime dataDevolucaoEfetiva;
    
    @Column(name = "devolvido", nullable = false)
    private Boolean devolvido = false;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Construtores
    public Emprestimo() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.dataEmprestimo = LocalDateTime.now();
        this.devolvido = false;
    }
    
    public Emprestimo(Livro livro, Usuario usuario) {
        this();
        this.livro = livro;
        this.usuario = usuario;
        // Define data de devolução prevista como 15 dias após o empréstimo
        this.dataDevolucaoPrevista = this.dataEmprestimo.plusDays(15);
    }
    
    public Emprestimo(Livro livro, Usuario usuario, LocalDateTime dataDevolucaoPrevista) {
        this();
        this.livro = livro;
        this.usuario = usuario;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }
    
    // Getters e Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Livro getLivro() {
        return livro;
    }
    
    public void setLivro(Livro livro) {
        this.livro = livro;
        this.updatedAt = LocalDateTime.now();
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        this.updatedAt = LocalDateTime.now();
    }
    
    public LocalDateTime getDataEmprestimo() {
        return dataEmprestimo;
    }
    
    public void setDataEmprestimo(LocalDateTime dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
        this.updatedAt = LocalDateTime.now();
    }
    
    public LocalDateTime getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }
    
    public void setDataDevolucaoPrevista(LocalDateTime dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.updatedAt = LocalDateTime.now();
    }
    
    public LocalDateTime getDataDevolucaoEfetiva() {
        return dataDevolucaoEfetiva;
    }
    
    public void setDataDevolucaoEfetiva(LocalDateTime dataDevolucaoEfetiva) {
        this.dataDevolucaoEfetiva = dataDevolucaoEfetiva;
        this.updatedAt = LocalDateTime.now();
    }
    
    public Boolean getDevolvido() {
        return devolvido;
    }
    
    public void setDevolvido(Boolean devolvido) {
        this.devolvido = devolvido;
        this.updatedAt = LocalDateTime.now();
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
    
    // Métodos auxiliares
    public boolean isDevolvido() {
        return devolvido != null && devolvido;
    }
    
    public boolean isAtrasado() {
        if (isDevolvido()) {
            return false;
        }
        return dataDevolucaoPrevista != null && 
               LocalDateTime.now().isAfter(dataDevolucaoPrevista);
    }
    
    public long getDiasAtraso() {
        if (!isAtrasado()) {
            return 0;
        }
        return java.time.Duration.between(dataDevolucaoPrevista, LocalDateTime.now()).toDays();
    }
    
    public void devolver() {
        this.devolvido = true;
        this.dataDevolucaoEfetiva = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
    
    @Override
    public String toString() {
        return "Emprestimo{" +
                "id='" + id + '\'' +
                ", livro=" + (livro != null ? livro.getTitulo() : "null") +
                ", usuario=" + (usuario != null ? usuario.getNome() : "null") +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataDevolucaoPrevista=" + dataDevolucaoPrevista +
                ", dataDevolucaoEfetiva=" + dataDevolucaoEfetiva +
                ", devolvido=" + devolvido +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Emprestimo emprestimo = (Emprestimo) o;
        
        return id != null ? id.equals(emprestimo.id) : emprestimo.id == null;
    }
    
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
