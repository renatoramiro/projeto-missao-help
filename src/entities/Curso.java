package entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "cursos")
public class Curso implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "matricula", nullable = false)
    private int matricula;
    @Column(name = "nome_curso", length = 100, nullable=false)
    private String nomeCurso;
    @Column(name = "periodo", nullable=false)
    private int periodo;
    @ManyToOne
    @JoinColumn(name="id_curriculo", nullable=false)
    private Curriculo curriculo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nome_curso) {
        this.nomeCurso = nome_curso;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public Curriculo getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(Curriculo curriculo) {
        this.curriculo = curriculo;
    }
}