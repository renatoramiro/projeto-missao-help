package entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "estado")
public class Estado implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "uf", nullable = false, length=2)
    private String uf;
    @Column(name = "nome", nullable = false)
    private String nome;

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String id) {
        this.uf = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o){
            return true;
        }
        
        if (!(o instanceof Estado)) {
            return false;
        }
        
        Estado estado = (Estado)o;
        return id == estado.getId() && nome.equals(estado.getNome()) && uf.equals(estado.getUf());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
    
}
