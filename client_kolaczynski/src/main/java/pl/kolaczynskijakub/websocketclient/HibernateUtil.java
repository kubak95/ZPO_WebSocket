package pl.kolaczynskijakub.websocketclient;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
// import pl.kolaczynskijakub.websocketclient.DataEntity;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

    static Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
    private final static Properties properties = configuration.getProperties();
    private final static ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(properties)
            .buildServiceRegistry();
    private final static SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

    public void crudCreate(DataEntity data) {

        // StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder()
        // .applySettings(configuration.getProperties());
        Transaction transaction = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            // start a transaction
            session.save(data);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    public String crudRead(DataEntity data) {
        System.out.println("crudRead");

        Transaction transaction = null;
        Session session = null;
        String output = "";
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            // start a transaction
            DataEntity dataEntity = (DataEntity) session.get(DataEntity.class, data.id);
            // commit transaction
            output = "ID - " + String.valueOf(dataEntity.id) + " Name " + dataEntity.name + " Surname - "
                    + dataEntity.surname + " Email - " + dataEntity.email;
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return output;
    }

    public void crudDelete(DataEntity data) {
        System.out.println("crudDelete");

        Transaction transaction = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            // start a transaction
            session.delete(data);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void crudUpdate(DataEntity data) {
        System.out.println("crudUpdate");
        Transaction transaction = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            // start a transaction
            session.saveOrUpdate(data);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

        }
    }
}
