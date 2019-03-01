package lk.syscolabs.candidatemanager.repository;

import lk.syscolabs.candidatemanager.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Integer> {

    public List<Position> findAllByName(String name);
}