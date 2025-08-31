package com.enzoradel.atividade_rest.dto;

import java.time.LocalDateTime;

public class UsuarioResponseDTO {
    
    private String id;
    private String nome;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private long totalEmprestimosAtivos;
    private boolean podeFazerEmprestimos;
    
    // Construtores
    public UsuarioResponseDTO() {}
    
    public UsuarioResponseDTO(String id, String nome, String email, 
                             LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    // Getters e Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
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
    
    public long getTotalEmprestimosAtivos() {
        return totalEmprestimosAtivos;
    }
    
    public void setTotalEmprestimosAtivos(long totalEmprestimosAtivos) {
        this.totalEmprestimosAtivos = totalEmprestimosAtivos;
    }
    
    public boolean isPodeFazerEmprestimos() {
        return podeFazerEmprestimos;
    }
    
    public void setPodeFazerEmprestimos(boolean podeFazerEmprestimos) {
        this.podeFazerEmprestimos = podeFazerEmprestimos;
    }
    
    @Override
    public String toString() {
        return "UsuarioResponseDTO{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", totalEmprestimosAtivos=" + totalEmprestimosAtivos +
                ", podeFazerEmprestimos=" + podeFazerEmprestimos +
                '}';
    }
}
