package daos;

import java.util.List;
import entities.Cidade;
import entities.PagamentoTaxa;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

public class PagamentoTaxaDao {

    public void cadastrarPagamentoTaxa(PagamentoTaxa pg) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(pg);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void editarPagamentoTaxa(PagamentoTaxa pg) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(pg);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void apagarPagamentoTaxa(PagamentoTaxa pg) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(pg);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public List<PagamentoTaxa> listarPagamentoTaxa() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<PagamentoTaxa> lista = session.createQuery("from PagamentoTaxa").list();
        session.close();
        return lista;
    }
    
    public PagamentoTaxa encontrarPagamentoTaxaPeloCodigo(int codigo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(PagamentoTaxa.class);
        PagamentoTaxa taxa = (PagamentoTaxa) criteria.add(Restrictions.eq("codPagamento", codigo)).uniqueResult();
        session.close();
        return taxa;
    }
}
