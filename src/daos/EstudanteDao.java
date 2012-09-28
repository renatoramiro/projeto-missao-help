package daos;

import java.util.Date;
import java.util.List;
import entities.Estudante;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

public class EstudanteDao {

    public void cadastrarEstudante(Estudante estudante) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(estudante);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void editarEstudante(Estudante estudante) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(estudante);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void apagarEstudante(Estudante estudante) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(estudante);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public List<EstudanteDao> listarEstudantes() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<EstudanteDao> lista = session.createQuery("from Estudante").list();
        session.close();
        return lista;
    }
    
    public Estudante encontrarEstudantePeloCpf(String cpf) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Estudante.class);
        Estudante estudante = (Estudante) criteria.add(Restrictions.eq("cpf", cpf)).uniqueResult();
        session.close();
        return estudante;
    }
    
}
