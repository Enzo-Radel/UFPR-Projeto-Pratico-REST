package com.enzoradel.atividade_rest.dto;

import java.time.LocalDateTime;

public class AutorResponseDTO {
    
    private String id;
    private String nome;
    private String biografia;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private long totalLivros;
    private boolean temLivros;
    private boolean temBiografia;
    
    // Construtores
    public AutorResponseDTO() {}
    
    public AutorResponseDTO(String id, String nome, String biografia, 
                           LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.nome = nome;
        this.biografia = biografia;
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
    
    public String getBiografia() {
        return biografia;
    }
    
    public void setBiografia(String biografia) {
        this.biografia = biografia;
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
    
    public long getTotalLivros() {
        return totalLivros;
    }
    
    public void setTotalLivros(long totalLivros) {
        this.totalLivros = totalLivros;
    }
    
    public boolean isTemLivros() {
        return temLivros;
    }
    
    public void setTemLivros(boolean temLivros) {
        this.temLivros = temLivros;
    }
    
    public boolean isTemBiografia() {
        return temBiografia;
    }
    
    public void setTemBiografia(boolean temBiografia) {
        this.temBiografia = temBiografia;
    }
    
    @Override
    public String toString() {
        return "AutorResponseDTO{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", biografia='" + biografia + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", totalLivros=" + totalLivros +
                ", temLivros=" + temLivros +
                ", temBiografia=" + temBiografia +
                '}';
    }
}
