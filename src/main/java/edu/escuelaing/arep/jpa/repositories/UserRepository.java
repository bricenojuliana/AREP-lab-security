package edu.escuelaing.arep.jpa.repositories;

import edu.escuelaing.arep.jpa.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

        User findByUsername(String username);

        User findById(long id);

        User findByUsernameAndPasswordHash(String username, String passwordHash);
}
