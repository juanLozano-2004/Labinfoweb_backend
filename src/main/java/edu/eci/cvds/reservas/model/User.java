package edu.eci.cvds.reservas.model;


import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

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

    public User() {}

    public User(String username, String email, String password, String fullName, Role role, LocalDate creationDate, LocalDateTime lastLogin) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.creationDate = creationDate;
        this.lastLogin = lastLogin;
        this.role = role;

    }

    public String getId() {
        return id;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;

    }

    public LocalDate getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;

    }
    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

}
