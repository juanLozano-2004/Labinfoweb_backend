package edu.eci.cvds.reservas.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Laboratories")
public class Laboratory {
    @Id
    private String idLabortatory;
    private String name;
    private String location;

    public Laboratory() {
    }

    public Laboratory(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getIdLabortatory() {
        return idLabortatory;
    }

    public void setIdLabortatory(String idLabortatory) {
        this.idLabortatory = idLabortatory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    
    
    

}
