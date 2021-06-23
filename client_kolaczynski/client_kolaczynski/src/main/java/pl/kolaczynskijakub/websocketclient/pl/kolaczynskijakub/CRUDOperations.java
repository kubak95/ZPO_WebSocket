package pl.kolaczynskijakub.websocketclient.pl.kolaczynskijakub;

import pl.kolaczynskijakub.websocketclient.pl.kolaczynskijakub.DataEntity;

public class CRUDOperations {
    public String operation;
    public DataEntity data;

    public void crudCreate() {
        System.out.println("crudCreate");
    }

    public String crudRead() {
        System.out.println("crudRead");
        String output = "ID - " + String.valueOf(data.id) + "  Name " + data.name + "  Surname - " + data.surname
                + "  Email - " + data.email;
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
