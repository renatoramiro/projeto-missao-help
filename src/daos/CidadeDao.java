package daos;

import java.util.List;
import entities.Cidade;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

public class CidadeDao {

    public void cadatrarCidade(Cidade cidade) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();
            sessao.save(cidade);
            sessao.flush();
            sessao.getTransaction().commit();
        } catch (Exception ex) {
            sessao.getTransaction().rollback();
            System.err.println("Erro no cadastro da cidade");
        } finally {
            sessao.close();
        }
    }

    public void editarCidade(Cidade cidade) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(cidade);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Erro na atualizacao da cidade");
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void apagarCidade(Cidade cidade) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Cidade auxiliar = cidade;
            session.beginTransaction();
            session.delete(auxiliar);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Erro ao apagar cidade");
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public List<Cidade> listarCidades() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Cidade> lista = session.createQuery("from Cidade").list();
        session.close();
        return lista;
    }
    
    public Cidade encontrarCidadePeloNome(String nome) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Cidade.class);
        Cidade cidade = (Cidade) criteria.add(Restrictions.eq("nome", nome)).uniqueResult();
        session.close();
        return cidade;
    }
}
