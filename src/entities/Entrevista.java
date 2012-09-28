package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "entrevista")
public class Entrevista implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name = "data_entrevista", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date dataEntrevista;
    @Column(name = "horario", nullable=false)
    @Temporal(TemporalType.TIME)
    private Date horarioEntrevista;
    @ManyToOne
    @JoinColumn(name="cpf_estudante", nullable=false)
    private Estudante estudante;
    @ManyToOne
    @JoinColumn(name="cnpj_empresa", nullable=false)
    private Empresa empresa;
    @ManyToOne
    @JoinColumn(name="cnpj_sge", nullable=false)
    private SGE sge;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataEntrevista() {
        return dataEntrevista;
    }

    public void setDataEntrevista(Date data_entrevista) {
        this.dataEntrevista = data_entrevista;
    }

    public Date getHorarioEntrevista() {
        return horarioEntrevista;
    }

    public void setHorarioEntrevista(Date horario_entrevista) {
        this.horarioEntrevista = horario_entrevista;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    public SGE getSge() {
        return sge;
    }

    public void setSge(SGE sge) {
        this.sge = sge;
    }
}