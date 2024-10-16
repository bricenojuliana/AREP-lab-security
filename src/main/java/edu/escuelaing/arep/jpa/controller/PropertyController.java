package edu.escuelaing.arep.jpa.controller;

import edu.escuelaing.arep.jpa.entities.Property;
import edu.escuelaing.arep.jpa.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyController(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }


    @GetMapping
    public Iterable<Property> getProperties() {
        return propertyRepository.findAll();
    }

    @GetMapping("/{id}")
    public Property getPropertyById(long id) {
        return propertyRepository.findById(id);
    }

    @PostMapping
    public Property saveProperty(@RequestBody Property property) {
        return propertyRepository.save(property);
    }

    @DeleteMapping("/{id}")
    public void deleteProperty(@PathVariable long id) {
        propertyRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Property updateProperty(@PathVariable long id, @RequestBody Property property) {
        Property propertyToUpdate = propertyRepository.findById(id);
        if (propertyToUpdate == null) {
            return null;
        }
        propertyToUpdate.setAddress(property.getAddress());
        propertyToUpdate.setPrice(property.getPrice());
        propertyToUpdate.setSize(property.getSize());
        propertyToUpdate.setDescription(property.getDescription());
        return propertyRepository.save(propertyToUpdate);
    }



}