package entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "pagamentoTaxa")
public class PagamentoTaxa implements Serializable {

    @Id
    @Column(name = "codPagamento", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int codPagamento;
    @Column(name = "valor", nullable=false, precision=2)
    private double valor;
    @ManyToOne
    @JoinColumn(name="cnpj_empresa", nullable=false)
    private Empresa empresa;
    @ManyToOne
    @JoinColumn(name="cnpj_sge", nullable=false)
    private SGE sge;

    public int getCodPagamento() {
        return codPagamento;
    }

    public void setCodPagamento(int codPagamento) {
        this.codPagamento = codPagamento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public SGE getSge() {
        return sge;
    }

    public void setSge(SGE sge) {
        this.sge = sge;
    }
}