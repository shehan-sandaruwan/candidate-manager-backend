package lk.syscolabs.candidatemanager.service;

import lk.syscolabs.candidatemanager.model.Field;
import lk.syscolabs.candidatemanager.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldService {
    @Autowired
    private FieldRepository fieldRepository;

    public List<Field> findAll() {
        return fieldRepository.findAll();
    }

    public Field getOne(Integer id) {

        if (fieldRepository.existsById(id)) return fieldRepository.getOne(id);
        else return null;
    }

    public Field save(Field field) {
        return fieldRepository.saveAndFlush(field);
    }

    public List<Field> findAllByName(String name) {
        return fieldRepository.findAllByName(name);
    }

}