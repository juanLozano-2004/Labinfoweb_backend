package edu.eci.cvds.reservas.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a Reservation entity in the system.
 * This class is mapped to the "reservations" collection in MongoDB.
 */
@Document(collection = "reservations")
public class Reservation {
    @Id
    private String idReservation;
    private Laboratory laboratory;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private User user;
    private String className;
    private LocalDateTime creationDate;
    private String professorName = "Generic Professor";

    /**
     * Default constructor for Reservation.
     */
    public Reservation() {
    }

    /**
     * Constructs a Reservation with the specified details.
     *
     * @param creationDate the creation date of the reservation
     * @param laboratory   the laboratory for the reservation
     * @param startTime    the start time of the reservation
     * @param endTime      the end time of the reservation
     * @param user         the user who made the reservation
     * @param className    the class name associated with the reservation
     */
    public Reservation(LocalDateTime creationDate, Laboratory laboratory, LocalDateTime startTime, LocalDateTime endTime, User user, String className, String professorName) {
        this.laboratory = laboratory;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.className = className;
        this.creationDate = creationDate;
        this.professorName = professorName;
    }

    /**
     * Sets the creation date of the reservation.
     *
     * @param creationDate the new creation date
     */
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Returns the creation date of the reservation.
     *
     * @return the creation date
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Returns the ID of the reservation.
     *
     * @return the ID of the reservation
     */
    public String getIdReservation() {
        return idReservation;
    }

    /**
     * Sets the ID of the reservation.
     *
     * @param idReservation the new ID of the reservation
     */
    public void setIdReservation(String idReservation) {
        this.idReservation = idReservation;
    }

    /**
     * Returns the laboratory of the reservation.
     *
     * @return the laboratory
     */
    public Laboratory getLaboratory() {
        return laboratory;
    }

    /**
     * Sets the laboratory of the reservation.
     *
     * @param laboratory the new laboratory
     */
    public void setLaboratory(Laboratory laboratory) {
        this.laboratory = laboratory;
    }

    /**
     * Returns the start time of the reservation.
     *
     * @return the start time
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time of the reservation.
     *
     * @param startTime the new start time
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Returns the end time of the reservation.
     *
     * @return the end time
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Sets the end time of the reservation.
     *
     * @param endTime the new end time
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Returns the user who made the reservation.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user who made the reservation.
     *
     * @param user the new user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Returns the class name associated with the reservation.
     *
     * @return the class name
     */
    public String getClassName() {
        return className;
    }

    /**
     * Sets the class name associated with the reservation.
     *
     * @param className the new class name
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Returns the ID of the reservation.
     *
     * @return the ID
     */
    public String getId() {
        return idReservation;
    }

    /**
     * Sets the ID of the reservation.
     *
     * @param id the new ID
     */
    public void setId(String id) {
        this.idReservation = id;
    }

    /**
     * Returns the professor name associated with the reservation.
     *
     * @return the professor name
     */
    public String getProfessorName() {
        return professorName;
    }

    /**
     * Sets the professor name associated with the reservation.
     *
     * @param professorName the new professor name
     */

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    /**
     * Returns a string representation of the reservation.
     *
     * @return a string representation of the reservation
     */
    @Override
    public String toString() {
        return "Reservation [id=" + idReservation + ", laboratory=" + laboratory + ", userName=" + user + ", date="
                + startTime + ", Class=" + className + "]";
    }
}