package pl.kolaczynskijakub.websocketclient.pl.kolaczynskijakub;

import com.google.gson.Gson;

import pl.kolaczynskijakub.websocketclient.pl.kolaczynskijakub.CRUD;

public class CRUDOperations {
    public String operation;
    public CRUD data;

    public void crudCreate() {
        Gson gson = new Gson();
        // CRUD crud = gson.fromJson(data, CRUD.class);
        System.out.println("crudCreate");
        System.out.println(data.name);

    }

    public void crudRead() {
        Gson gson = new Gson();
        // CRUD crud = gson.fromJson(data, CRUD.class);
        System.out.println("crudRead");
    }

    public void crudDelete() {
        Gson gson = new Gson();
        // CRUD crud = gson.fromJson(data, CRUD.class);
        System.out.println("crudDelete");
    }

    public void crudUpdate() {
        Gson gson = new Gson();
        // CRUD crud = gson.fromJson(data, CRUD.class);
        System.out.println("crudUpdate");
        System.out.println("ID - " + data.id);
        System.out.println("Name - " + data.name);
        System.out.println("Surname - " + data.surname);
        System.out.println("Email - " + data.email);

    }
}
