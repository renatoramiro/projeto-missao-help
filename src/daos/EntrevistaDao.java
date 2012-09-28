package daos;

import java.util.Date;
import java.util.List;
import entities.Entrevista;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

public class EntrevistaDao {

    public void cadastrarEntrevista(Entrevista entrevista) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(entrevista);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void editarEntrevista(Entrevista entrevista) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(entrevista);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void apagarEntrevista(Entrevista entrevista) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(entrevista);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public List<EntrevistaDao> listarEntrevistas() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<EntrevistaDao> lista = session.createQuery("from Entrevista").list();
        session.close();
        return lista;
    }
    
    public Entrevista encontrarEntrevistaPeloId(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Entrevista.class);
        Entrevista entrevista = (Entrevista) criteria.add(Restrictions.eq("id", id)).uniqueResult();
        session.close();
        return entrevista;
    }
}
