package entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "experiencia")
public class Experiencia implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "descricao", nullable = false)
    private String descricao;
    @ManyToOne
    @JoinColumn(name="id_curriculo", nullable=false)
    private Curriculo curriculo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Curriculo getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(Curriculo curriculo) {
        this.curriculo = curriculo;
    }
}