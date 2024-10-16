package edu.escuelaing.arep.jpa.repositories;

import edu.escuelaing.arep.jpa.entities.Property;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PropertyRepository extends CrudRepository<Property, Long> {

    Property findById(long id);

    List<Property> findAll();


}
