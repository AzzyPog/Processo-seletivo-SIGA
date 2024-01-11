package br.ufrj.TreinamentoBackend.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class SituacaoMatricula {

    @Id
    private Long id;
    private String nome;
    private String codigo;
    public final static String ATIVO = "A01";
    public final static String CANCELADO = "C01";
    public final static String TRANCADO = "T01";
    public final static String FORMADO = "F01";

    public SituacaoMatricula() {

    }

    public SituacaoMatricula(Long id, String nome, String codigo) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
