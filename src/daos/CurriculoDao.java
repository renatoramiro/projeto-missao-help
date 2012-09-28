package daos;

import java.util.List;
import entities.Curriculo;
import entities.Curso;
import entities.Vaga;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import util.HibernateUtil;

public class CurriculoDao {

    public void cadastrarCurriculo(Curriculo curriculo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(curriculo);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void editarCurriculo(Curriculo curriculo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(curriculo);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void apagarCurrriculo(Curriculo currculo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(currculo);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public List<Curriculo> listarCurriculos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Curriculo> lista = session.createQuery("from Curriculo").list();
        session.close();
        return lista;
    }

    public Curriculo encontrarCurriculoPeloId(int id) {
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Curriculo.class);
        Curriculo curriculo = (Curriculo) criteria.add(Restrictions.eq("id", id)).uniqueResult();
        session.close();
        return curriculo;
    }
}
