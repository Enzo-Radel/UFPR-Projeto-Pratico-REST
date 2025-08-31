package com.enzoradel.atividade_rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class EmprestimoDTO {
    
    @NotBlank(message = "ID do livro é obrigatório")
    private String livroId;
    
    @NotBlank(message = "ID do usuário é obrigatório")
    private String usuarioId;
    
    @NotNull(message = "Data de devolução prevista é obrigatória")
    private LocalDateTime dataDevolucaoPrevista;
    
    // Construtores
    public EmprestimoDTO() {}
    
    public EmprestimoDTO(String livroId, String usuarioId, LocalDateTime dataDevolucaoPrevista) {
        this.livroId = livroId;
        this.usuarioId = usuarioId;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }
    
    // Getters e Setters
    public String getLivroId() {
        return livroId;
    }
    
    public void setLivroId(String livroId) {
        this.livroId = livroId;
    }
    
    public String getUsuarioId() {
        return usuarioId;
    }
    
    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }
    
    public LocalDateTime getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }
    
    public void setDataDevolucaoPrevista(LocalDateTime dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }
    
    @Override
    public String toString() {
        return "EmprestimoDTO{" +
                "livroId='" + livroId + '\'' +
                ", usuarioId='" + usuarioId + '\'' +
                ", dataDevolucaoPrevista=" + dataDevolucaoPrevista +
                '}';
    }
}
