package entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "empresa")
public class Empresa implements Serializable {

    @Id
    @Column(name = "cnpj", nullable = false, length = 14)
    private String cnpj;
    @Column(name = "nome", nullable = false, length=100)
    private String nome;
    @Column(name = "endereco", nullable = false)
    private String endereco;
    @ManyToOne
    @JoinColumn(name="id_cidade", nullable=false)
    private Cidade cidade;
    @ManyToOne
    @JoinColumn(name="cnpj_sge", nullable=false)
    private SGE sge;

    public SGE getSge() {
        return sge;
    }

    public void setSge(SGE sge) {
        this.sge = sge;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCNPJ(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
