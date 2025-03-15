package edu.eci.cvds.reservas.repository.user;

import edu.eci.cvds.reservas.model.User;

import java.util.List;

/**
 * Repository interface for User entities.
 * Provides methods for performing CRUD operations on User entities.
 */
public interface UserRepository {

    /**
     * Saves a User entity.
     *
     * @param user the User entity to save
     * @return the saved User entity
     */
    User saveUser(User user);

    /**
     * Finds a User entity by its ID.
     *
     * @param id the ID of the User entity
     * @return the found User entity, or null if not found
     */
    User findUserById(String id);

    /**
     * Updates a User entity.
     *
     * @param user the User entity to update
     * @return the updated User entity
     */
    User updateUser(User user);

    /**
     * Finds all User entities.
     *
     * @return a list of all User entities
     */
    List<User> findAllUsers();

    /**
     * Checks if a User entity exists by its ID.
     *
     * @param id the ID of the User entity
     * @return true if the User entity exists, false otherwise
     */
    boolean existsById(String id);

    /**
     * Checks if a User entity exists by its username.
     *
     * @param username the username of the User entity
     * @return true if the User entity exists, false otherwise
     */
    boolean existsByUsername(String username);

    /**
     * Checks if a User entity exists by its email.
     *
     * @param email the email of the User entity
     * @return true if the User entity exists, false otherwise
     */
    boolean existsByEmail(String email);

    /**
     * Deletes a User entity by its ID.
     *
     * @param id the ID of the User entity to delete
     */
    void deleteUserById(String id);

    /**
     * Finds a User entity by its username.
     *
     * @param username the username of the User entity
     * @return the found User entity, or null if not found
     */
    User findUserByUsername(String username);

    /**
     * Deletes all User entities.
     */
    void deleteAllUsers();
}