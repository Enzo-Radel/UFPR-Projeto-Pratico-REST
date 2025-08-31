package com.enzoradel.atividade_rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

public class LivroDTO {
    
    @NotBlank(message = "Título é obrigatório")
    @Size(min = 1, max = 255, message = "Título deve ter entre 1 e 255 caracteres")
    private String titulo;
    
    @Pattern(regexp = "^(?:\\d{10}|\\d{13})$", message = "ISBN deve ter 10 ou 13 dígitos")
    private String isbn;
    
    @Min(value = 1000, message = "Ano de publicação deve ser maior ou igual a 1000")
    @Max(value = 2100, message = "Ano de publicação deve ser menor ou igual a 2100")
    private Integer anoPublicacao;
    
    @NotNull(message = "Disponibilidade é obrigatória")
    private Boolean disponivel;
    
    @NotBlank(message = "ID do autor é obrigatório")
    private String autorId;
    
    // Construtores
    public LivroDTO() {}
    
    public LivroDTO(String titulo, String isbn, Integer anoPublicacao, Boolean disponivel, String autorId) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.anoPublicacao = anoPublicacao;
        this.disponivel = disponivel;
        this.autorId = autorId;
    }
    
    // Getters e Setters
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
    
    public String getAutorId() {
        return autorId;
    }
    
    public void setAutorId(String autorId) {
        this.autorId = autorId;
    }
    
    @Override
    public String toString() {
        return "LivroDTO{" +
                "titulo='" + titulo + '\'' +
                ", isbn='" + isbn + '\'' +
                ", anoPublicacao=" + anoPublicacao +
                ", disponivel=" + disponivel +
                ", autorId='" + autorId + '\'' +
                '}';
    }
}
