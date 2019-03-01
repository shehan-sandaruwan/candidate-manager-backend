package lk.syscolabs.candidatemanager.repository;

import lk.syscolabs.candidatemanager.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldRepository extends JpaRepository<Field, Integer> {

    public List<Field> findAllByName(String name);
}