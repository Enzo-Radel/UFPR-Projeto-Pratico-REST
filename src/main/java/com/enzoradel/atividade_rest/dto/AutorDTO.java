package com.enzoradel.atividade_rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AutorDTO {
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 255, message = "Nome deve ter entre 2 e 255 caracteres")
    private String nome;
    
    @Size(max = 10000, message = "Biografia deve ter no máximo 10.000 caracteres")
    private String biografia;
    
    // Construtores
    public AutorDTO() {}
    
    public AutorDTO(String nome, String biografia) {
        this.nome = nome;
        this.biografia = biografia;
    }
    
    // Getters e Setters
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
    
    @Override
    public String toString() {
        return "AutorDTO{" +
                "nome='" + nome + '\'' +
                ", biografia='" + biografia + '\'' +
                '}';
    }
}
