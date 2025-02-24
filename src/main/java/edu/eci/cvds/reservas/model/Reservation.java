package edu.eci.cvds.reservas.model;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


public class Reservation {
    @Id
    private String idReservation;
    private String laboratory;
    private LocalDateTime dateHour;
    private String user;
    private String className;

    public Reservation(){}

    public Reservation(String laboratory, LocalDateTime dateHour, String user, String className) {
        this.laboratory = laboratory;
        this.dateHour = dateHour;
        this.user = user;
        this.className = className;
    }

    public String getIdReservation() {
        return idReservation;
    }
    public void setIdReservation(String idReservation) {
        this.idReservation = idReservation;
    }
    public String getLaboratory() {
        return laboratory;
    }
    public void setLaboratory(String laboratory) {
        this.laboratory = laboratory;
    }

    public LocalDateTime getDateHour() {
        return dateHour;
    }
    public void setDateHour(LocalDateTime dateHour) {
        this.dateHour = dateHour;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "Reservation [id=" + idReservation + ", laboratory=" + laboratory + ", userName=" + user + ", date=" + dateHour + ", Class=" + className + "]";
    }
}
