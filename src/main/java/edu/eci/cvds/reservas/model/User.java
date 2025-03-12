package edu.eci.cvds.reservas.model;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

/**
 * Represents a User entity in the system.
 * This class is mapped to the "users" collection in MongoDB.
 */
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String username;
    private String email;
    private String password;
    private String fullName;
    private LocalDate creationDate;
    private LocalDateTime lastLogin;
    private Role role;

    /**
     * Default constructor for User.
     */
    public User() {}

    /**
     * Constructs a User with the specified details.
     *
     * @param username the username of the user
     * @param email the email of the user
     * @param password the password of the user
     * @param fullName the full name of the user
     * @param role the role of the user
     * @param creationDate the creation date of the user
     * @param lastLogin the last login date and time of the user
     */
    public User(String username, String email, String password, String fullName, Role role, LocalDate creationDate, LocalDateTime lastLogin) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.creationDate = creationDate;
        this.lastLogin = lastLogin;
        this.role = role;
    }

    /**
     * Returns the role of the user.
     *
     * @return the role of the user
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     *
     * @param role the new role of the user
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Returns the ID of the user.
     *
     * @return the ID of the user
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the user.
     *
     * @param id the new ID of the user
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the username of the user.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the new username of the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the email of the user.
     *
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email the new email of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the new password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the full name of the user.
     *
     * @return the full name of the user
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the full name of the user.
     *
     * @param fullName the new full name of the user
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Returns the creation date of the user.
     *
     * @return the creation date of the user
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the creation date of the user.
     *
     * @param creationDate the new creation date of the user
     */
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Returns the last login date and time of the user.
     *
     * @return the last login date and time of the user
     */
    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    /**
     * Sets the last login date and time of the user.
     *
     * @param lastLogin the new last login date and time of the user
     */
    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }
}