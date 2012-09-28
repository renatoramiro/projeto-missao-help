package daos;

import entities.Estado;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

public class EstadoDao {

    public void cadatrarEstado(Estado estado) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();
            sessao.save(estado);
            sessao.flush();
            sessao.getTransaction().commit();
        } catch (Exception ex) {
            sessao.getTransaction().rollback();
            System.err.println("Erro no cadastro do estado");
        } finally {
            sessao.close();
        }
    }

    public void editarEstado(Estado estado) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(estado);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Erro na atualizacao do estado");
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void apagarEstado(Estado estado) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Estado auxiliar = estado;
            session.beginTransaction();
            session.delete(auxiliar);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Erro ao apagar estado");
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public List<Estado> listarEstados() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Estado> lista = session.createQuery("from Estado").list();
        session.close();
        return lista;
    }
    
    public Estado encontrarEstadoPeloId(Long id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Estado.class);
        Estado estado = (Estado) criteria.add(Restrictions.eq("id", id)).uniqueResult();
        session.close();
        return estado;
    }
    
    public Estado encontrarEstadoPeloNome(String nome) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Estado.class);
        Estado estado = (Estado) criteria.add(Restrictions.eq("nome", nome)).uniqueResult();
        session.close();
        return estado;
    }
    
    public Estado encontrarEstadoPeloUf(String uf) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Estado.class);
        Estado estado = (Estado) criteria.add(Restrictions.eq("uf", uf)).uniqueResult();
        session.close();
        return estado;
    }
}
