package daos;

import java.util.List;
import entities.Experiencia;
import entities.Vaga;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import util.HibernateUtil;

public class ExperienciaDao {

    public void cadastrarExperiencia(Experiencia experiencia) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(experiencia);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void editarExperiencia(Experiencia experiencia) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(experiencia);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void apagarExperiencia(Experiencia experiencia) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(experiencia);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public List<ExperienciaDao> listarExperiencia() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ExperienciaDao> lista = session.createQuery("from Experiencia").list();
        session.close();
        return lista;
    }
    
    public Experiencia encontrarExperienciaPeloId(int id) {
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Experiencia.class);
        Experiencia experiencia = (Experiencia) criteria.add(Restrictions.eq("id", id)).uniqueResult();
        session.close();
        return experiencia;
    }
}
