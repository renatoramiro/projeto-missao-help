package entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "curriculo")
public class Curriculo implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "objetivo", nullable = false)
    private String objetivo;
    @ManyToOne
    @JoinColumn(name="cpf_estudante", nullable=false)
    private Estudante estudante;
    @ManyToOne
    @JoinColumn(name="cnpj_sge", nullable=false)
    private SGE sge;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public SGE getSge() {
        return sge;
    }

    public void setSge(SGE sge) {
        this.sge = sge;
    }
}