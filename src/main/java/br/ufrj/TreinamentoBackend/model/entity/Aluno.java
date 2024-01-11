package br.ufrj.TreinamentoBackend.model.entity;

import jakarta.persistence.*;
@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String matricula;

    private String hobbies;

    @ManyToOne
    @JoinColumn(name = "situacaoMatricula_id")
    private SituacaoMatricula situacaoMatricula;

    public Aluno(Long id, String nome, String matricula, String hobbies, SituacaoMatricula situacaoMatricula) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.hobbies = hobbies;
        this.situacaoMatricula = situacaoMatricula;
    }

    public Aluno(){

    }
    public SituacaoMatricula getSituacaoMatricula() {
        return situacaoMatricula;
    }

    public void setSituacaoMatricula(SituacaoMatricula situacaoMatricula) {
        this.situacaoMatricula = situacaoMatricula;
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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }
}
