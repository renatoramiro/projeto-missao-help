package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sge")
public class SGE implements Serializable {

    @Id
    @Column(name = "cnpj", nullable = false, length=14)
    private String cnpj;
    @Column(name = "nome", nullable = false)
    private String nome;

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
}
