package edu.eci.cvds.reservas.model;

    import org.springframework.data.annotation.Id;
    import org.springframework.data.mongodb.core.mapping.Document;

    /**
     * Represents a Laboratory entity in the system.
     * This class is mapped to the "Laboratories" collection in MongoDB.
     */
    @Document(collection = "Laboratories")
    public class Laboratory {

        @Id
        private String idLabortatory;
        private String name;
        private String location;

        /**
         * Default constructor for Laboratory.
         */
        public Laboratory() {
        }

        /**
         * Constructs a Laboratory with the specified name and location.
         *
         * @param name the name of the laboratory
         * @param location the location of the laboratory
         */
        public Laboratory(String name, String location) {
            this.name = name;
            this.location = location;
        }

        /**
         * Returns the ID of the laboratory.
         *
         * @return the ID of the laboratory
         */
        public String getIdLabortatory() {
            return idLabortatory;
        }

        /**
         * Sets the ID of the laboratory.
         *
         * @param idLabortatory the new ID of the laboratory
         */
        public void setIdLabortatory(String idLabortatory) {
            this.idLabortatory = idLabortatory;
        }

        /**
         * Returns the name of the laboratory.
         *
         * @return the name of the laboratory
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the name of the laboratory.
         *
         * @param name the new name of the laboratory
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * Returns the location of the laboratory.
         *
         * @return the location of the laboratory
         */
        public String getLocation() {
            return location;
        }

        /**
         * Sets the location of the laboratory.
         *
         * @param location the new location of the laboratory
         */
        public void setLocation(String location) {
            this.location = location;
        }
    }