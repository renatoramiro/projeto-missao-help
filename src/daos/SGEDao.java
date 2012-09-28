package daos;

import java.util.List;
import entities.SGE;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

public class SGEDao {

    public void cadastrarSGE(SGE sge) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(sge);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void editarSGE(SGE sge) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(sge);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void apagarSGE(SGE sge) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(sge);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public List<SGE> listarSGE() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<SGE> lista = session.createQuery("from SGE").list();
        session.close();
        return lista;
    }
    
    public SGE encontrarSgePeloNome(String nome) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(SGE.class);
        SGE sge = (SGE) criteria.add(Restrictions.eq("nome", nome)).uniqueResult();
        session.close();
        return sge;
    }
    
    public SGE encontrarSgePeloCnpj(String cnpj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(SGE.class);
        SGE sge = (SGE) criteria.add(Restrictions.eq("cnpj", cnpj)).uniqueResult();
        session.close();
        return sge;
    }
}
