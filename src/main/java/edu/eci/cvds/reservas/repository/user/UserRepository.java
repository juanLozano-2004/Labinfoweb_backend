package edu.eci.cvds.reservas.repository.user;

import edu.eci.cvds.reservas.model.User;

import java.util.List;


public interface UserRepository {

    User saveUser(User user);
    User findUserById(String id);
    User updateUser(User user);
    List<User> findAllUsers();
    boolean existsById(String id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    void deleteUserById(String id);
    User findUserByUsername(String username);

}
