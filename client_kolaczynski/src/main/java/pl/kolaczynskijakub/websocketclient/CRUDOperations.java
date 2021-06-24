package pl.kolaczynskijakub.websocketclient;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
// import pl.kolaczynskijakub.websocketclient.DataEntity;
// import pl.kolaczynskijakub.websocketclient.HibernateUtil;

public class CRUDOperations {
    public String operation;
    public DataEntity data;
}
