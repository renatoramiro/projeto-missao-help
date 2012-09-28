package daos;

import java.util.List;
import entities.Cidade;
import entities.Vaga;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

public class VagaDao {

    public void cadastrarVaga(Vaga vaga) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(vaga);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void editarVaga(Vaga vaga) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(vaga);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void apagarVaga(Vaga vaga) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(vaga);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public List<Vaga> listarVagas() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Vaga> lista = session.createQuery("from Vaga").list();
        session.close();
        return lista;
    }
    
    public Vaga encontrarVagaPeloCodigo(int codigo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Vaga.class);
        Vaga vaga = (Vaga) criteria.add(Restrictions.eq("codVaga", codigo)).uniqueResult();
        session.close();
        return vaga;
    }
}
