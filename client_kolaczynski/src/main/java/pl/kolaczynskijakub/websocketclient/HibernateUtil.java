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
    private final static ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();
    private final static SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

    public void crudCreate(DataEntity data) {

        // StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder()
        // .applySettings(configuration.getProperties());
        Transaction transaction = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            System.out.println("\n\n\nsessionFactory.openSession");
            transaction = session.beginTransaction();
            System.out.println("\n\n\nsession.beginTransaction");
            // start a transaction
            System.out.println("ID - " + String.valueOf(data.id) + "  email - " + data.email);
            session.save(data);
            System.out.println("\n\n\nsave(data)");
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
        // String output = "ID - " + String.valueOf(data.id) + " Name " + data.name + "
        // Surname - " + data.surname + " Email - " + data.email;
        // return output;
        SessionFactory sessionFactory = null;
        ServiceRegistry serviceRegistry = null;
        Configuration configuration = new Configuration();
        configuration.configure();
        Properties properties = configuration.getProperties();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(properties).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        System.out.println("crudCreate");
        Transaction transaction = null;
        Session session = null;
        String output = "";
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            // start a transaction
            DataEntity dataEntity = (DataEntity) session.get(DataEntity.class, data.id);
            // commit transaction
            output = "ID - " + String.valueOf(dataEntity.id) + " Name " + dataEntity.name + "Surname - "
                    + dataEntity.surname + " Email - " + dataEntity.email;
            transaction.commit();
            System.out.println("\n\n\n\n\n\n\nTest Print inside Try \n\n\n\n\n\n");

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        System.out.println("\n\n\n\n\n\n\nTest Print\n" + output + "\n\n\n\n\n");
        return output;
    }

    public void crudDelete() {
        System.out.println("crudDelete");
    }

    public void crudUpdate() {
        System.out.println("crudUpdate");
        ;

    }
}
