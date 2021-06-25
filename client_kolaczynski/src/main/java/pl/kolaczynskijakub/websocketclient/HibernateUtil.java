package pl.kolaczynskijakub.websocketclient;

import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

    static Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
    private final static Properties properties = configuration.getProperties();
    private final static ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(properties)
            .buildServiceRegistry();
    private final static SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

    public void crudCreate(DataEntity data) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            // start a transaction
            transaction = session.beginTransaction();
            session.save(data);
            session.flush();
            // commit transaction
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("ERROR: " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public String crudRead(DataEntity data) {
        Transaction transaction = null;
        Session session = null;
        String output = "";
        try {
            session = sessionFactory.openSession();
            // start a transaction
            transaction = session.beginTransaction();
            DataEntity dataEntity = (DataEntity) session.get(DataEntity.class, data.id);
            output = "ID - " + dataEntity.id;
            if (dataEntity.name != null) {
                output = output + " ; Name - " + dataEntity.name;
            }
            if (dataEntity.surname != null) {
                output = output + " ; Surname - " + dataEntity.surname;
            }
            if (dataEntity.email != null) {
                output = output + " ; Email - " + dataEntity.email;

            }
            session.flush();
            // commit transaction
            transaction.commit();

        } catch (HibernateException e) {
            System.out.println("ERROR: " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return output;
    }

    public void crudDelete(DataEntity data) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            // start a transaction
            transaction = session.beginTransaction();
            session.delete(data);
            session.flush();
            // commit transaction
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("ERROR: " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void crudUpdate(DataEntity data) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            // start a transaction
            transaction = session.beginTransaction();
            session.saveOrUpdate(data);
            session.flush();
            // commit transaction
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("ERROR: " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
