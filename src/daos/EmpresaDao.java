package daos;

import java.util.List;
import entities.Empresa;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

public class EmpresaDao {

    public void cadastrarEmpresa(Empresa empresa) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(empresa);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void editarEmpresa(Empresa empresa) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(empresa);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void apagarEmpresa(Empresa empresa) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(empresa);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<EmpresaDao> listarEmpresas() {
        Session session = HibernateUtil.getSessionFactory().openSession();
		List<EmpresaDao> lista = session.createQuery("from Empresa").list();
        session.close();
        return lista;
    }
    
    public Empresa encontrarEmpresaPeloCnpj(String cnpj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Empresa.class);
        Empresa empresa = (Empresa) criteria.add(Restrictions.eq("cnpj", cnpj)).uniqueResult();
        session.close();
        return empresa;
    }
    
    public Empresa encontrarEmpresaPeloNome(String nome) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Empresa.class);
        Empresa empresa = (Empresa) criteria.add(Restrictions.eq("nome", nome)).uniqueResult();
        session.close();
        return empresa;
    }
}
