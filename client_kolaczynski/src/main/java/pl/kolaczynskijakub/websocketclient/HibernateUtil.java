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

public class HibernateUtil {

    private static SessionFactory sessionFactory = null;
    // private static ServiceRegistry serviceRegistry = null;
    private static Configuration configuration = new Configuration();

    public static void crudCreate(DataEntity data) {

        // System.out.println("\n\n\n\n\n\n\n Linia 21 crudCreate - jest
        // git\n\n\n\n\n\n");
        // configuration.configure();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(DataEntity.class);
        // System.out.println("\n\n\n\n\n\n\n Linia 23 crudCreate - jest
        // git\n\n\n\n\n\n");

        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        // System.out.println("\n\n\n\n\n\n\n Linia 27 crudCreate - jest
        // git\n\n\n\n\n\n");
        // sessionFactory = new
        // AnnotationConfiguration().configure().buildSessionFactory();
        sessionFactory = configuration.buildSessionFactory(ssrb.build());
        // System.out.println("\n\n\n\n\n\n\n Linia 29 crudCreate - jest
        // git\n\n\n\n\n\n");

        // Properties properties = configuration.getProperties();
        // serviceRegistry = new
        // StandardServiceRegistryBuilder().applySettings(properties).build();
        // sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Transaction transaction = null;
        Session session = null;
        // System.out.println("\n\n\n\n\n\n\n Linia 38 crudCreate - jest
        // git\n\n\n\n\n\n");

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
