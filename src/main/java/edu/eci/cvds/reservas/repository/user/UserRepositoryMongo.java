package edu.eci.cvds.reservas.repository.user;

import edu.eci.cvds.reservas.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

/**
 * Repository interface for User entities using MongoDB.
 * Extends UserRepository and MongoRepository to provide CRUD operations.
 */
@Repository
public interface UserRepositoryMongo extends UserRepository, MongoRepository<User, String> {

    /**
     * Checks if a User entity exists by its ID.
     *
     * @param id the ID of the User entity
     * @return true if the User entity exists, false otherwise
     */
    @Override
    default boolean existsById(String id) {
        User user = findUserById(id);
        return user != null;
    }

    /**
     * Saves and creates a new User.
     * If the User does not have an ID, MongoDB will generate it automatically.
     * Sets the creation date of the User to the current date.
     *
     * @param user the User to create
     * @return the created User
     */
    @Override
    default User saveUser(User user) {
        if (user.getCreationDate() == null) {
            user.setCreationDate(java.time.LocalDate.now());
        }
        return save(user);
    }

    /**
     * Finds a User by their ID.
     *
     * @param id the ID of the User to find
     * @return the User if found, null otherwise
     */
    @Override
    default User findUserById(String id) {
        return findById(id).orElse(null);
    }

    /**
     * Finds a User by their username.
     *
     * @param username the username of the User to find
     * @return the User if found, null otherwise
     */
    @Override
    default User findUserByUsername(String username) {
        return findByUsername(username);
    }

    /**
     * Checks if a User exists by username.
     *
     * @param username the username to check
     * @return true if a User with the username exists, false otherwise
     */
    @Override
    default boolean existsByUsername(String username) {
        return findByUsername(username) != null;
    }

    /**
     * Checks if a User exists by email.
     *
     * @param email the email to check
     * @return true if a User with the email exists, false otherwise
     */
    @Override
    default boolean existsByEmail(String email) {
        return findByEmail(email) != null;
    }

    /**
     * Retrieves all Users.
     *
     * @return a list of all Users
     */
    @Override
    default List<User> findAllUsers() {
        return findAll();
    }

    /**
     * Deletes a User by their ID.
     *
     * @param id the ID of the User to delete
     * @throws RuntimeException if the User entity does not exist
     */
    @Override
    default void deleteUserById(String id) {
        if (!existsById(id)) {
            throw new RuntimeException("User not found");
        }
        deleteById(id);
    }

    /**
     * Updates an existing User.
     *
     * @param user the User with updated data
     * @return the updated User
     * @throws RuntimeException if the User entity does not exist
     */
    @Override
    default User updateUser(User user) {
        if (!existsById(user.getId())) {
            throw new RuntimeException("User not found");
        }
        return save(user);
    }

    /**
     * Deletes all User entities.
     */
    @Override
    default void deleteAllUsers() {
        deleteAll();
    }

    // Custom methods for Spring Data MongoDB

    /**
     * Finds a User by their username.
     *
     * @param username the username of the User to find
     * @return the User if found, null otherwise
     */
    User findByUsername(String username);

    /**
     * Finds a User by their email.
     *
     * @param email the email of the User to find
     * @return the User if found, null otherwise
     */
    User findByEmail(String email);
}