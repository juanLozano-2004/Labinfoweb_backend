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
        private LocalDateTime dateHour;
        private User user;
        private String className;
        private LocalDateTime creationDate;

        /**
         * Default constructor for Reservation.
         */
        public Reservation() {
        }

        /**
         * Constructs a Reservation with the specified details.
         *
         * @param creationDate the creation date of the reservation
         * @param laboratory the laboratory for the reservation
         * @param dateHour the date and time of the reservation
         * @param user the user who made the reservation
         * @param className the class name associated with the reservation
         */
        public Reservation(LocalDateTime creationDate, Laboratory laboratory, LocalDateTime dateHour, User user, String className) {
            this.laboratory = laboratory;
            this.dateHour = dateHour;
            this.user = user;
            this.className = className;
            this.creationDate = creationDate;
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
         * Returns the date and time of the reservation.
         *
         * @return the date and time
         */
        public LocalDateTime getDateHour() {
            return dateHour;
        }

        /**
         * Sets the date and time of the reservation.
         *
         * @param dateHour the new date and time
         */
        public void setDateHour(LocalDateTime dateHour) {
            this.dateHour = dateHour;
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
         * Returns a string representation of the reservation.
         *
         * @return a string representation of the reservation
         */
        @Override
        public String toString() {
            return "Reservation [id=" + idReservation + ", laboratory=" + laboratory + ", userName=" + user + ", date="
                    + dateHour + ", Class=" + className + "]";
        }
    }