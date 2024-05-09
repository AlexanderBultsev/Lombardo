package com.pristine.lombardo.service;

import com.pristine.lombardo.dto.PropertyDTO;
import com.pristine.lombardo.entity.Property;
import com.pristine.lombardo.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Transactional
    public Property createProperty(PropertyDTO propertyDTO) {
        Property property = new Property(
                propertyDTO.getName(),
                propertyDTO.getType(),
                propertyDTO.getPrice());

        return propertyRepository.save(property);
    }

    @Transactional(readOnly = true)
    public Property findById(Long id) throws NoSuchElementException {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Property with ID: " + id + " not found"));
    }

    @Transactional(readOnly = true)
    public List<Property> findAll() {
        return propertyRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) throws NoSuchElementException {
        if (!propertyRepository.existsById(id)) {
            throw new NoSuchElementException("Property with ID: " + id + " not found");
        }
        propertyRepository.deleteById(id);
    }
}
