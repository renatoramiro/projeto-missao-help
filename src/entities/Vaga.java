package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "vaga")
public class Vaga implements Serializable {

    @Id
    @Column(name = "codVaga", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int codVaga;
    @Column(name = "funcao", nullable = false)
    private String funcao;
    @Temporal(TemporalType.TIME)
    @Column(name = "jornadaTrabalho")
    private Date jornadaTrabalho;
    @Column(name = "areaConhecimento", nullable = false)
    private String areaConhecimento;
    @Column(name = "salario", nullable = false)
    private double salario;
    @ManyToOne
    @JoinColumn(name="cnpj_empresa", nullable=false)
    private Empresa empresa;

    public String getAreaConhecimento() {
        return areaConhecimento;
    }

    public void setAreaConhecimento(String areaConhecimento) {
        this.areaConhecimento = areaConhecimento;
    }

    public int getCodVaga() {
        return codVaga;
    }

    public void setCodVaga(int codVaga) {
        this.codVaga = codVaga;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Date getJornadaTrabalho() {
        return jornadaTrabalho;
    }

    public void setJornadaTrabalho(Date jornadaTrabalho) {
        this.jornadaTrabalho = jornadaTrabalho;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

}
