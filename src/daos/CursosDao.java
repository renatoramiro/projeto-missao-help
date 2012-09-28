package daos;

import java.util.List;
import entities.Curso;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

public class CursosDao {

    public void cadastrarCurso(Curso curso) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(curso);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void editarCurso(Curso curso) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(curso);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void apagarCurso(Curso curso) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(curso);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public List<CursosDao> listarCursos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<CursosDao> lista = session.createQuery("from Curso").list();
        session.close();
        return lista;
    }
    
    public Curso encontrarCursoPelaMatricula(int matricula) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Curso.class);
        Curso curso = (Curso) criteria.add(Restrictions.eq("matricula", matricula)).uniqueResult();
        session.close();
        return curso;
    }
    
    public Curso encontrarCursoPeloId(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Curso.class);
        Curso curso = (Curso) criteria.add(Restrictions.eq("id", id)).uniqueResult();
        session.close();
        return curso;
    }
}
